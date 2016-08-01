/* 
 * File 	: ./server/http_server.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the main functions of the NodeJS module for managing REST webservice
 * Note		: mongoose - refers to the mongoose module used to communicate with Mongo database
 * Version  	: 1.0.0
 */

var	bodyParser     = require('body-parser'),
	connectTimeout = require('connect-timeout'),
	express        = require('express'),
	fs             = require('fs'),
	helmet         = require('helmet'),
	path           = require('path'),

	check	  	 = require('core')('CHECK'),
	connect_mongoose = require('core')('CONNECT_MONGOOSE'),
	error_handler	 = require('core')('SERVICE_ERROR_HANDLER'),
	header_handler	 = require('core')('HEADER_HANDLER'),
	http_server	 = require('core')('HTTP_SERVER_BASICS')
	isArray	  	 = require('toolbox')('ISARRAY'),
	isEmpty	  	 = require('toolbox')('ISEMPTY'),
	log_requests	 = require('core')('WRITE_LOG');


function dynamicExport(mongoose){
	var add_route = require('./routes/add_route.js')(mongoose),
	    findError = require('./errors/referential.js');

	//Definition of the server and the configuration
	var conf   = null;

	//Function to test the connection with database
	function testDatabase(){
		check(conf.mongo.adress,       '[-] Incomplete settings : mongo.adress is empty');
		check(conf.mongo.databasename, '[-] Incomplete settings : mongo.databasename is empty');
	}

	//Function to test the token settings to use correctly REST API
	function testRestSettings(){
		check(conf.rest.token.secretkey,        '[-] Incomplete settings : rest.token.secretkey is empty');
		check(conf.rest.token.minimum_size,     '[-] Incomplete settings : rest.token.minimum_size is empty');
		check(conf.rest.token.maximum_lifespan, '[-] Incomplete settings : rest.token.maximum_lifespan is empty');

		check(conf.rest.hash.algorithm,         '[-] Incomplete settings : rest.hash.algorithm is empty');
		check(conf.rest.hash.saltLength,        '[-] Incomplete settings : rest.hash.saltLength is empty');
		check(conf.rest.hash.algorithm,         '[-] Incomplete settings : rest.hash.algorithm is empty');

		if(!(conf.rest.token.secretkey.length>=conf.rest.token.minimum_size)){
			console.log('[-] Rest settings disapproved : Key length shorter than minimum size');
			process.exit(1);
		}
		else if(!(conf.rest.token.maximum_lifespan>0)){
			console.log('[-] Rest settings disapproved : Unvalid lifespan value');
			process.exit(1);
		}
		else if(!(conf.rest.hash.saltLength>0)){
			console.log('[-] Rest settings disapproved : Unvalid salt length value');
			process.exit(1);
		}
		else if(!(conf.rest.hash.iterations>0)){
			console.log('[-] Rest settings disapproved : Unvalid iteration value');
			process.exit(1);
		}
		else{
			console.log('[+] Rest settings checked and approved');
		}
	}

	//Function to test the HTTP settings to start the HTTP server
	function testHttp(){
		check(conf.http.host, '[-] Incomplete settings : http.host is empty');
		check(conf.http.port, '[-] Incomplete settings : http.port is empty');
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

		// test rest settings
		testRestSettings();

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

		if(!isEmpty(conf.http) && 
		   !isEmpty(conf.http.index_file) && !isEmpty(conf.http.static_dir)){
			// launcher
			app.use(function(req, res, next){
				if(req.url !== '/'){
					return next();
				}
				fs.readFile(path.join('.', conf.http.index_file), 'utf8', function(err, txt){
					if(err){
						return next(err);
					}
					res.send(txt);
				});
			});

			// static files
			app.use('/', express.static(path.join('.', conf.http.static_dir)));
		}

		// change allow origin
		app.use('/api', function (req, res, next){
			header_handler(req, res, { allowed_origin : conf.http.rootUrl }, next);
		});
	 
		// access to POST data
		app.use(bodyParser.json());
		app.use(bodyParser.urlencoded({
			extended: true
		}));

		// API definition with routes
		if(!isEmpty(routes) && isArray(routes)){
			var token_conf={ secretkey        : conf.rest.token.secretkey,
					 service_name     : conf.service.name,
					 maximum_lifespan : conf.rest.token.maximum_lifespan
			};
			var flood_conf={};

			for(var i=0; i<routes.length; i++){
				var route = routes[i];
				if(!isEmpty(route) && !isEmpty(route.method) && !isEmpty(route.path) && !isEmpty(route.ctrl)){
					if(isEmpty(route.key)){
						add_route(app, {token : token_conf, flood : flood_conf}, {method : route.method, path : route.path, ctrl : route.ctrl});
					}
					else{
						add_route(app, {token : token_conf, flood : flood_conf}, {method : route.method, path : route.path, ctrl : route.ctrl, key : route.key});
					}
				}
			}
		}

		return app;
	}

	//Start function
	function startFunc(app, callback){
		// if no matching api
		app.use('/api', function(req, res, next){
			next(findError("WSCORE.1.1.1"));
		});

		// error handler
		app.use(function(err, req, res, next){
			error_handler(req, res, err, {name : conf.service.name, version : conf.service.version}, next);
		});

		http_server.start(app, { host : conf.http.host, port : conf.http.port} , callback);
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
