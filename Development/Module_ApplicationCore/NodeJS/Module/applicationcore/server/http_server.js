/* 
 * File 	: ./server/http_server.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the main functions of the NodeJS module for managing the web application
 * Note		: mongoose - refers to the mongoose module used to communicate with Mongo database
 * Version  	: 1.0.0
 */

var	bodyParser     = require('body-parser'),
	connectTimeout = require('connect-timeout'),
	express        = require('express'),
	fs             = require('fs'),
	helmet         = require('helmet'),
	path           = require('path'),
	session        = require('express-session'),
	store	       = require('connect-mongo')(session),

	check	  	 = require('core')('CHECK'),
	connect_mongoose = require('core')('CONNECT_MONGOOSE'),
	error_handler	 = require('core')('APPLICATION_ERROR_HANDLER'),
	http_server	 = require('core')('HTTP_SERVER_BASICS'),
	floodprotection  = require('floodprotection'),
	isArray	  	 = require('toolbox')('ISARRAY'),
	isEmpty	  	 = require('toolbox')('ISEMPTY'),
	log_requests	 = require('core')('WRITE_LOG');


function dynamicExport(mongoose){
	var add_route = require('./routes/add_route.js'),
	    findError = require('./errors/referential.js');

	//Definition of the server and the configuration
	var conf   = null;

	//Function to test the connection with database
	function testDatabase(){
		check(conf.mongo.adress,       '[-] Incomplete settings : mongo.adress is empty');
		check(conf.mongo.databasename, '[-] Incomplete settings : mongo.databasename is empty');
	}

	//Function to test the HTTP settings to start the HTTP server
	function testHttp(){
		check(conf.http.host,       '[-] Incomplete settings : http.host is empty');
		check(conf.http.static_dir, '[-] Incomplete settings : http.static_dir is empty');
		check(conf.http.index_file, '[-] Incomplete settings : http.index_file is empty');
		check(conf.http.port,       '[-] Incomplete settings : http.port is empty');
		check(conf.http.secret,     '[-] Incomplete settings : http.secret is empty');
	}

	//Function to test the log settings to store logs
	function testLog(){
		check(conf.log.file, '[-] Incomplete settings : log.file is empty');
	}

	//Setup function
	function setupFunc(conf_param, routes){
		console.log('['+(new Date())+']');

		conf=conf_param;

		// test mongodb database connection
		testDatabase();
		connect_mongoose(mongoose, conf.mongo.adress+conf.mongo.databasename);

		// test http settings
		testHttp();

		// test log settings
		testLog();

		var app = express();

		// logs requests
		log_requests(app, { filepath : conf.log.file});

		// set the timeout
		if(!isEmpty(conf.http.timeout)){
			app.use(connectTimeout(conf.http.timeout));
		}

		// Helmet protection
		app.use(helmet());
		app.use(helmet.xssFilter({ setOnOldIE: true }));
		app.use(helmet.frameguard('deny'));
		app.use(helmet.xframe());
		app.use(helmet.hidePoweredBy());
		app.use(helmet.ieNoOpen());
		app.use(helmet.noSniff());

		// static files
		app.use('/', express.static(path.join('.', conf.http.static_dir)));

		// access to POST data
		app.use(bodyParser.json());
		app.use(bodyParser.urlencoded({
			extended: true
		}));

		// session support
		app.use(session({cookie : {secure  : false,
					   httpOnly: true,
					   maxAge  : 3600000},
				 name   : conf.application.name,
				 resave : false,
				 saveUninitialized : true,
				 secret : conf.http.secret,
				 store  : new store({mongooseConnection : mongoose.connection,
						     autoRemove         : 'native' }) }));

		// launcher
		app.use(function(req, res, next){
			if(req.url === '/api' ||
			   req.url.indexOf('/api/') === 0){
				return next();
			}
			fs.readFile(path.join('.', conf.http.index_file), 'utf8', function(err, txt){
				if(err){
					return next(err);
				}
				res.send(txt);
			});
		});

		// TODO : Finish the bruteforce protection here
		if(!isEmpty(conf.floodprotection)){
			var hashFunction = function(){
				return req.connection.remoteAddress;
			}
			floodprotection.check({ hash : '' }, conf.floodprotection, function(err){
				if(!isEmpty(err)){
					return next(err);
				}
			});
		}

		// API definition with routes
		if(!isEmpty(routes) && isArray(routes)){
			for(var i=0; i<routes.length; i++){
				var route = routes[i];
				if(!isEmpty(route) && !isEmpty(route.method) && !isEmpty(route.path) && !isEmpty(route.ctrl)){
					add_route(app, {method : route.method, path : route.path, ctrl : route.ctrl, isLogged : route.isLogged, gates : route.gates, roles : route.roles, restrict : route.extended_restriction}, conf.floodprotection);
				}
			}
		}


		return app;
	}

	//Start function
	function startFunc(app, callback){
		// if no matching api
		app.use('/api', function(req, res, next){
			next(findError("APPCORE.1.1.1"));
		});

		// error handler
		app.use(function(err, req, res, next){
			error_handler(req, res, err, { name : conf.application.name, version : conf.application.version}, next);
		});

		http_server.start(app, { host : conf.http.host, port : conf.http.port}, callback);
	}

	//Stop function
	function stopFunc(callback){
		http_server.stop(callback);
	}

	return{
		start : startFunc,
		stop  : stopFunc,
		setup : setupFunc
	};
}


module.exports = dynamicExport;
