/* 
 * File 	: ./server/index.js
 * Author(s)	: Zidmann
 * Function 	: This file starts NodeJS server for using the person and card checking application
 * Version  	: 1.0.0
 */

var	mongoose= require('mongoose'),
	server	= require('applicationcore')('HTTPS')(mongoose),

	conf	= require('./conf/conf.js'),
	routes  = require('./routes/routes.js');

// Defining the NodeJS server settings
var app = server.setup(conf, routes);

// Starting NodeJS server
server.start(app);
