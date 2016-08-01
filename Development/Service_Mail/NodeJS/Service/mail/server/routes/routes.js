/* 
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes for mailer service
 * Version  	: 1.0.0
 */

var	_    = require('underscore'),
	ctrl = _.extend({}, require('../controllers/mail.js'));

var routes = [];
var prefix = '/api';

//Route to enable the user to send a mail
var prefix_mail=prefix+'/mail';
routes.push({method: 'POST',  path: prefix_mail+'/', ctrl: ctrl.mail.send, key: 'POSTmail' });


module.exports = routes;
