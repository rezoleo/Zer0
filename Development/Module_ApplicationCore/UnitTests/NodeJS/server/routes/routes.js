/* 
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes
 * Version  	: 1.1.0
 */

var	_    		   = require('underscore'),
	ctrlFlood          = require('../controllers/floodtest.js'),
	ctrlAgent	   = require('../controllers/userAgent.js'),
	ctrl 		   = require('../controllers/controller.js')('OFF'),
	ctrlToken	   = require('../controllers/controller.js')('ON'),
	ctrlGate	   = require('../controllers/gate.js'),
	ctrlIdentification = require('../controllers/identification.js'),
	ctrlRoles	   = require('../controllers/roles.js');

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
routes.push({method: 'GET',   path: prefix, ctrl: ctrlAgent.getUserAgent });

// Routes to check the communication between an application and a service with and without a token
var prefix = '/api/object';
routes.push({method:'GET',    path: prefix+'/',    ctrl: ctrl.get });
routes.push({method:'GET',    path: prefix+'/:id', ctrl: ctrl.getOne });
routes.push({method:'POST',   path: prefix+'/',    ctrl: ctrl.post });
routes.push({method:'PUT',    path: prefix+'/',    ctrl: ctrl.put });
routes.push({method:'DELETE', path: prefix+'/',    ctrl: ctrl.delete });

var prefix = '/api/objectToken';
routes.push({method:'GET',    path: prefix+'/',    ctrl: ctrlToken.get});
routes.push({method:'GET',    path: prefix+'/:id', ctrl: ctrlToken.getOne});
routes.push({method:'POST',   path: prefix+'/',    ctrl: ctrlToken.post});
routes.push({method:'PUT',    path: prefix+'/',    ctrl: ctrlToken.put});
routes.push({method:'DELETE', path: prefix+'/',    ctrl: ctrlToken.delete});

// Routes to check the identification management
var prefix = '/api/login';
routes.push({method:'GET',    path: prefix+'/', ctrl: ctrlIdentification.check,  isLogged : true });
routes.push({method:'POST',   path: prefix+'/', ctrl: ctrlIdentification.login,  isLogged : false });
routes.push({method:'DELETE', path: prefix+'/', ctrl: ctrlIdentification.logout, isLogged : true });

// Routes to check the gate management
var prefix = '/api/gate';
routes.push({method:'GET',    path: prefix+'/', ctrl: ctrlGate.check,   gates : ['my_gate'] });
routes.push({method:'POST',   path: prefix+'/', ctrl: ctrlGate.gatein });
routes.push({method:'DELETE', path: prefix+'/', ctrl: ctrlGate.gateout, gates : ['my_gate'] });

// Routes to check the authorization management
var prefix = '/api/roles';
routes.push({method:'GET',    path: prefix+'/', ctrl: ctrlRoles.check,   roles : ['my_role'] });
routes.push({method:'POST',   path: prefix+'/', ctrl: ctrlRoles.rolein });
routes.push({method:'DELETE', path: prefix+'/', ctrl: ctrlRoles.roleout, roles : ['my_role'] });

// Routes to check the Flood protection
var prefix = '/api/floodtest';
routes.push({method: 'GET',    path: prefix+'/increment_route1', ctrl: ctrlFlood.sayhello });
routes.push({method: 'GET',    path: prefix+'/increment_route2', ctrl: ctrlFlood.sayhello, floodprotection: {all : local_conf,     byRoute : local_conf,     byIP : local_conf,     byRouteIP : local_conf} });
routes.push({method: 'GET',    path: prefix+'/increment_route3', ctrl: ctrlFlood.sayhello, floodprotection: {all : local_bis_conf, byRoute : local_bis_conf, byIP : local_bis_conf, byRouteIP : local_bis_conf} });
routes.push({method: 'GET',    path: prefix+'/reset',            ctrl: ctrlFlood.reset,    floodprotection: {all : local_bis_conf, byRoute : local_bis_conf, byIP : local_bis_conf, byRouteIP : local_bis_conf} });


module.exports = routes;
