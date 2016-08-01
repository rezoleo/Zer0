/* 
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes for card service
 * Version  	: 1.0.0
 */

var	_    = require('underscore'),
	ctrl = _.extend({}, require('../controllers/card.js'));

var prefix = '/api/card';
var routes = [];
routes.push({method: 'GET',    path: prefix+'/',           ctrl: ctrl.card.get,        key: 'GET' });
routes.push({method: 'GET',    path: prefix+'/:id',        ctrl: ctrl.card.getOne,     key: 'GETid' });
routes.push({method: 'GET',    path: prefix+'/code/:code', ctrl: ctrl.card.getOneCard, key: 'GETcard' });
routes.push({method: 'POST',   path: prefix+'/',           ctrl: ctrl.card.post,       key: 'POST' });
routes.push({method: 'PUT',    path: prefix+'/:id',        ctrl: ctrl.card.put,        key: 'PUT' });
routes.push({method: 'DELETE', path: prefix+'/:id',        ctrl: ctrl.card.delete,     key: 'DELETE' });


module.exports = routes;
