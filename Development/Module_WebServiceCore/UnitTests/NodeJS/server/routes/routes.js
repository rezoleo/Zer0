/* 
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes
 * Version  	: 1.0.0
 */

var	_		  = require('underscore'),
	ctrl 	  = require('../controllers/objectDB.js')('ObjectDB')
	ctrlToken = require('../controllers/objectDB.js')('ObjectDBToken'),
	ctrlAgent = require('../controllers/userAgent.js');

var routes = [];
var prefix = '/api/useragent';
routes.push({method: 'GET',    path: prefix,        ctrl: ctrlAgent.getUserAgent });

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


module.exports = routes;
