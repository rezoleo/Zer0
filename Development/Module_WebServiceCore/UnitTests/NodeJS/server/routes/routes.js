/* 
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes
 * Version  	: 1.1.0
 */

var	_		= require('underscore'),
	ctrlFlood	= require('../controllers/floodtest.js'),
	ctrl 	 	= require('../controllers/objectDB.js')('ObjectDB'),
	ctrlToken	= require('../controllers/objectDB.js')('ObjectDBToken'),
	ctrlAgent	= require('../controllers/userAgent.js');

var local_conf={ free_retries: 120, burst_retries: 125,
		 lifetime: 10*60*1000, 
		 minWait: 4*100, maxWait: 7*100 };
local_conf.waitFunction=function(count){
	return ((local_conf.maxWait-local_conf.minWait)/(local_conf.burst_retries-local_conf.free_retries)*(count-local_conf.free_retries))+local_conf.minWait;
}

var local_bis_conf={ free_retries: 105, burst_retries: 110,
		     lifetime: 10*60*1000, 
		     minWait: 1*10, maxWait: 3*100,
		     disabled:true };
local_bis_conf.waitFunction=function(count){
	return ((local_bis_conf.maxWait-local_bis_conf.minWait)/(local_bis_conf.burst_retries-local_bis_conf.free_retries)*(count-local_bis_conf.free_retries))+local_bis_conf.minWait;
}


var routes = [];

// Routes to check the useragent
var prefix = '/api/useragent';
routes.push({method: 'GET',    path: prefix, ctrl: ctrlAgent.getUserAgent });

// Routes to check the communication between an application and a service with and without a token
var prefix = '/api/object';
routes.push({method: 'GET',    path: prefix+'/',    ctrl: ctrl.get });
routes.push({method: 'GET',    path: prefix+'/:id', ctrl: ctrl.getOne });
routes.push({method: 'POST',   path: prefix+'/',    ctrl: ctrl.post });
routes.push({method: 'PUT',    path: prefix+'/',    ctrl: ctrl.put });
routes.push({method: 'DELETE', path: prefix+'/',    ctrl: ctrl.delete });

var prefix = '/api/objectToken';
routes.push({method: 'GET',    path: prefix+'/',    ctrl: ctrlToken.get,    key : 'GET' });
routes.push({method: 'GET',    path: prefix+'/:id', ctrl: ctrlToken.getOne, key : 'GETid' });
routes.push({method: 'POST',   path: prefix+'/',    ctrl: ctrlToken.post,   key : 'POST' });
routes.push({method: 'PUT',    path: prefix+'/',    ctrl: ctrlToken.put,    key : 'PUT' });
routes.push({method: 'DELETE', path: prefix+'/',    ctrl: ctrlToken.delete, key : 'DELETE' });

// Routes to check the Flood protection
var prefix = '/api/floodtest';
routes.push({method: 'GET',    path: prefix+'/increment_route1', ctrl: ctrlFlood.sayhello });
routes.push({method: 'GET',    path: prefix+'/increment_route2', ctrl: ctrlFlood.sayhello, floodprotection: {all : local_conf,     byRoute : local_conf,     byIP : local_conf,     byRouteIP : local_conf} });
routes.push({method: 'GET',    path: prefix+'/increment_route3', ctrl: ctrlFlood.sayhello, floodprotection: {all : local_bis_conf, byRoute : local_bis_conf, byIP : local_bis_conf, byRouteIP : local_bis_conf} });
routes.push({method: 'GET',    path: prefix+'/reset',            ctrl: ctrlFlood.reset,    floodprotection: {all : local_bis_conf, byRoute : local_bis_conf, byIP : local_bis_conf, byRouteIP : local_bis_conf} });


module.exports = routes;
