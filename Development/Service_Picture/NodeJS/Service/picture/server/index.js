/*
 * File 		: ./server/index.js
 * Author(s)	: Zidmann
 * Function 	: This file starts NodeJS server for using the picture storage service
 * Version  	: 1.0.0
 */

var	express  = require('express'),
	mongoose = require('mongoose'),
	server	 = require('webservicecore')('HTTPS')(mongoose),
	path	 = require('path'),

	conf   = require('./conf/conf.js'),
	routes = require('./routes/routes.js');

// Defining the NodeJS server settings
var app = server.setup(conf, routes);

// Define static files directory
app.use('/', express.static(path.join(__dirname, '..', 'front', 'public')));

// Starting NodeJS server
server.start(app);
