/* 
 * File 	: ./server/index.js
 * Author(s)	: Zidmann
 * Function 	: This file starts NodeJS server for communicating with other webservices (authentification/card/people/picture)
 * Version  	: 1.1.0
 */

var	mongoose= require('mongoose'),
	server	= require('webservicecore')('HTTPS')(mongoose),

	conf	     = require('./conf/conf.js'),
	interceptors = require('./errors/interceptors.js'),
	routes       = require('./routes/routes.js');

// Defining the NodeJS server settings
var app = server.setup(conf, routes, interceptors);

// Starting NodeJS server
server.start(app);
