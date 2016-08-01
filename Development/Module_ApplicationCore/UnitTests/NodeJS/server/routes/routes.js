/* 
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes
 * Version  	: 1.0.0
 */

var	_    		   = require('underscore'),
	ctrlAgent	   = require('../controllers/userAgent.js'),
	ctrl 		   = require('../controllers/controller.js')('OFF'),
	ctrlToken	   = require('../controllers/controller.js')('ON'),
	ctrlGate	   = require('../controllers/gate.js'),
	ctrlIdentification = require('../controllers/identification.js'),
	ctrlRoles	   = require('../controllers/roles.js');

var routes = [];
//Routes to check the useragent
var prefix = '/api/useragent';
routes.push({method: 'GET',   path: prefix, ctrl: ctrlAgent.getUserAgent });

//Routes to check the communication between an application and a service with and without a token
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

//Routes to check the identification management
var prefix = '/api/login';
routes.push({method:'GET',    path: prefix+'/', ctrl: ctrlIdentification.check,  isLogged : true });
routes.push({method:'POST',   path: prefix+'/', ctrl: ctrlIdentification.login,  isLogged : false });
routes.push({method:'DELETE', path: prefix+'/', ctrl: ctrlIdentification.logout, isLogged : true });

//Routes to check the gate management
var prefix = '/api/gate';
routes.push({method:'GET',    path: prefix+'/', ctrl: ctrlGate.check,   gates : ['my_gate'] });
routes.push({method:'POST',   path: prefix+'/', ctrl: ctrlGate.gatein });
routes.push({method:'DELETE', path: prefix+'/', ctrl: ctrlGate.gateout, gates : ['my_gate'] });

//Routes to check the authorization management
var prefix = '/api/roles';
routes.push({method:'GET',    path: prefix+'/', ctrl: ctrlRoles.check,   roles : ['my_role'] });
routes.push({method:'POST',   path: prefix+'/', ctrl: ctrlRoles.rolein });
routes.push({method:'DELETE', path: prefix+'/', ctrl: ctrlRoles.roleout, roles : ['my_role'] });


module.exports = routes;
