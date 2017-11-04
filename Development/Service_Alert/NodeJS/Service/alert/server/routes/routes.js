/* 
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes for alert service
 * Version  	: 1.0.0
 */

var	_    = require('underscore'),
	ctrl = _.extend({}, require('../controllers/alert.js'));

var prefix = '/api/alert';
var routes = [];
routes.push({method: 'GET',  path: prefix+'/',    ctrl: ctrl.alert.get,    key: 'GET' });
routes.push({method: 'GET',  path: prefix+'/:id', ctrl: ctrl.alert.getOne, key: 'GETid' });
routes.push({method: 'POST', path: prefix+'/',    ctrl: ctrl.alert.post,   key: 'POST' });


module.exports = routes;
