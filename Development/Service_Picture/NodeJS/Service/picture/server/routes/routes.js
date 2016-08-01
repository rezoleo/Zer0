/*
 * File 		: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes for picture storage service
 * Version  	: 1.0.0
 */

var	_    = require('underscore'),
	ctrl = _.extend({}, require('../controllers/file.js'));

var prefix = '/api/file/:dir';
var routes = [];


routes.push({method: 'GET',    path: prefix+'/:file', ctrl: ctrl.file.get,    key: 'GET' });
routes.push({method: 'POST',   path: prefix+'/',      ctrl: ctrl.file.post,   key: 'POST' });
routes.push({method: 'DELETE', path: prefix+'/:file', ctrl: ctrl.file.delete, key: 'DELETE' });


module.exports = routes;
