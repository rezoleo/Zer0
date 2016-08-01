/* 
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes for password regeneration service
 * Version  	: 1.0.0
 */

var	_    = require('underscore'),
	ctrl = _.extend({}, require('../controllers/link.js'),
			    require('../controllers/password.js'));

var routes = [];
var prefix = '/api';

//Route to enable the user to know if the link is still valid and to change his/her password
var prefix_password=prefix+'/passwd-reset/link';
routes.push({method: 'GET',  path: prefix_password+'/:token', ctrl: ctrl.link.getOne });
routes.push({method: 'POST', path: prefix_password+'/:token', ctrl: ctrl.password.changePassword });

//Route to send a link by mail to the user
var prefix_link=prefix+'/passwd-reset/request';
routes.push({method: 'POST',  path: prefix_link+'/:login', ctrl: ctrl.link.sendMailWithLink });


module.exports = routes;
