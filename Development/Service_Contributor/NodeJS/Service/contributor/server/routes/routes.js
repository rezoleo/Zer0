/* 
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes for contributor service
 * Version  	: 1.0.0
 */

var	_    = require('underscore'),
	ctrl = _.extend({}, require('../controllers/contributor.js'));

var prefix = '/api/contributor';
var routes = [];
routes.push({method: 'GET',    path: prefix+'/',             ctrl: ctrl.contributor.get,         key: 'GET' });
routes.push({method: 'GET',    path: prefix+'/:id',          ctrl: ctrl.contributor.getOne,      key: 'GETid' });
routes.push({method: 'GET',    path: prefix+'/login/:login', ctrl: ctrl.contributor.getOneLogin, key: 'GETlogin' });
routes.push({method: 'POST',   path: prefix+'/',             ctrl: ctrl.contributor.post,        key: 'POST' });
routes.push({method: 'DELETE', path: prefix+'/:id',          ctrl: ctrl.contributor.delete,      key: 'DELETE' });


module.exports = routes;
