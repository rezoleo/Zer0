/* 
 * File 	: ./server/common.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the commons functions between 'http_server.js' and 'https_server.js' files for WebserviceCore module
 * Version  	: 1.0.0
 */

var	bodyParser     = require('body-parser'),
	connectTimeout = require('connect-timeout'),
	express        = require('express'),
	fs             = require('fs'),
	path           = require('path'),

	check            = require('core')('CHECK'),
	connect_mongoose = require('core')('CONNECT_MONGOOSE'),
	header_handler   = require('core')('HEADER_HANDLER'),
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
	check(conf.http.host, '[-] Incomplete settings : http.host is empty');
	check(conf.http.port, '[-] Incomplete settings : http.port is empty');
	if(mode==='HTTPS'){
		check(conf.ssl.key,   '[-] Incomplete settings : ssl.key is empty');
		check(conf.ssl.cert,  '[-] Incomplete settings : ssl.cert is empty');
	}
}

//Function to test the token settings to use correctly REST API
function testRestSettings(conf){
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

//Function to test the log settings to store logs
function testLog(conf){
	check(conf.log.file, '[-] Incomplete settings : log.file is empty');
}

//Function to define the HTTP server
function setupFunc(conf, mongoose, routes, mode){
	if(!isEmpty(mode) && isString(mode)){
		mode=mode.toUpperCase();
	}
	var add_route = require('./routes/add_route.js')(mongoose);

	console.log('['+(new Date())+']');

	// test mongodb database connection
	testDatabase(conf);
	connect_mongoose(mongoose, conf.mongo.adress+conf.mongo.databasename);

	// test http settings
	testHttp(conf, mode);

	// test rest settings
	testRestSettings(conf);

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

		for(var i=0; i<routes.length; i++){
			var route = routes[i];
			if(!isEmpty(route) && !isEmpty(route.method) && !isEmpty(route.path) && !isEmpty(route.ctrl)){
				if(isEmpty(route.key)){
					add_route(app, { token : token_conf }, {method : route.method, path : route.path, ctrl : route.ctrl },
								   { global : conf.floodprotection, local : route.floodprotection });
				}
				else{
					add_route(app, { token : token_conf }, {method : route.method, path : route.path, ctrl : route.ctrl, key : route.key },
								   { global : conf.floodprotection, local : route.floodprotection });
				}
			}
		}
	}

	return app;
}

module.exports = {
	setup : setupFunc
};
