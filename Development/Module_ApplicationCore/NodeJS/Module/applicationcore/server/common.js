/* 
 * File 	: ./server/common.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the commons functions between 'http_server.js' and 'https_server.js' files for ApplicationCore module
 * Version  	: 1.0.0
 */

var	bodyParser     = require('body-parser'),
	connectTimeout = require('connect-timeout'),
	express        = require('express'),
	fs             = require('fs'),
	path           = require('path'),
	session        = require('express-session'),
	store	       = require('connect-mongo')(session),

	check            = require('core')('CHECK'),
	connect_mongoose = require('core')('CONNECT_MONGOOSE'),
	helmet_protection= require('core')('HELMET_PROTECTION'),
	isArray          = require('toolbox')('ISARRAY'),
	isEmpty          = require('toolbox')('ISEMPTY'),
	isString         = require('toolbox')('ISSTRING'),
	log_requests     = require('core')('WRITE_LOG');


//Function to test the connection with database
function testDatabase(conf){
	check(conf.mongo.adress,       '[-] Incomplete settings : mongo.adress is empty');
	check(conf.mongo.databasename, '[-] Incomplete settings : mongo.databasename is empty');
}

//Function to test the HTTP settings to start the HTTP server
function testHttp(conf, mode){
	check(conf.http.host,       '[-] Incomplete settings : http.host is empty');
	check(conf.http.static_dir, '[-] Incomplete settings : http.static_dir is empty');
	check(conf.http.index_file, '[-] Incomplete settings : http.index_file is empty');
	check(conf.http.port,       '[-] Incomplete settings : http.port is empty');
	check(conf.http.secret,     '[-] Incomplete settings : http.secret is empty');
	if(mode==='HTTPS'){
		check(conf.ssl.key,   '[-] Incomplete settings : ssl.key is empty');
		check(conf.ssl.cert,  '[-] Incomplete settings : ssl.cert is empty');
	}
}

//Function to test the log settings to store logs
function testLog(conf){
	check(conf.log.file, '[-] Incomplete settings : log.file is empty');
}

//Function to define the HTTP server
function setupFunc(conf, mongoose, routes, mode){
	var add_route = require('./routes/add_route.js')(mongoose);
	var secure=false;
	if(!isEmpty(mode) && isString(mode)){
		mode=mode.toUpperCase();
	}
	if(mode==='HTTPS'){
		secure=true;
	}

	console.log('['+(new Date())+']');

	// test mongodb database connection
	testDatabase(conf);
	connect_mongoose(mongoose, conf.mongo.adress+conf.mongo.databasename);

	// test http settings
	testHttp(conf, mode);

	// test log settings
	testLog(conf);

	var app = express();

	// logs requests
	log_requests(app, { filepath : conf.log.file});

	// set the timeout
	if(!isEmpty(conf.http.timeout)){
		app.use(connectTimeout(conf.http.timeout));
	}

	// Helmet protection
	helmet_protection(app, mode);

	// static files
	app.use('/', express.static(path.join('.', conf.http.static_dir)));

	// access to POST data
	app.use(bodyParser.json());
	app.use(bodyParser.urlencoded({
		extended: true
	}));

	// session support
	app.use(session({cookie : {secure  : secure,
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

	// API definition with routes
	if(!isEmpty(routes) && isArray(routes)){
		for(var i=0; i<routes.length; i++){
			var route = routes[i];
			if(!isEmpty(route) && !isEmpty(route.method) && !isEmpty(route.path) && !isEmpty(route.ctrl)){
				add_route(app, { method : route.method, path : route.path, ctrl : route.ctrl, isLogged : route.isLogged, gates : route.gates, roles : route.roles, restrict : route.extended_restriction }, 
							   { global : conf.floodprotection, local : route.floodprotection });
			}
		}
	}

	return app;
}

module.exports = {
	setup : setupFunc
};
