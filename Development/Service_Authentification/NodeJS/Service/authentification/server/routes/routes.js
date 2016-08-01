/* 
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes for authentification service
 * Version  	: 1.0.0
 */

var	_    = require('underscore'),
	ctrl = _.extend({}, require('../controllers/authentification.js'));

var prefix = '/api/authentification';
var routes = [];
routes.push({method: 'GET',    path: prefix+'/',       ctrl: ctrl.authentification.get,    key: 'GET' });
routes.push({method: 'GET',    path: prefix+'/:login', ctrl: ctrl.authentification.getOne, key: 'GETOne' });
routes.push({method: 'POST',   path: prefix+'/',       ctrl: ctrl.authentification.signup, key: 'POST' });
routes.push({method: 'POST',   path: prefix+'/:login', ctrl: ctrl.authentification.signin, key: 'POSTlogin' });
routes.push({method: 'PUT',    path: prefix+'/:login', ctrl: ctrl.authentification.update, key: 'PUT' });
routes.push({method: 'DELETE', path: prefix+'/:login', ctrl: ctrl.authentification.delete, key: 'DELETE' });


module.exports = routes;
