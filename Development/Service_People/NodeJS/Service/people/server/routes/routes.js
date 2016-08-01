/* 
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes for people registration service
 * Version  	: 1.0.0
 */

var	_    = require('underscore'),
	ctrl = _.extend({}, require('../controllers/people.js'));

var prefix = '/api/people';
var routes = [];
routes.push({method: 'GET',    path: prefix+'/',             ctrl: ctrl.people.get,         key: 'GET' });
routes.push({method: 'GET',    path: prefix+'/:id',          ctrl: ctrl.people.getOne,      key: 'GETid' });
routes.push({method: 'GET',    path: prefix+'/login/:login', ctrl: ctrl.people.getOneLogin, key: 'GETlogin' });
routes.push({method: 'GET',    path: prefix+'/mail/:mail',   ctrl: ctrl.people.getOneMail,  key: 'GETmail' });
routes.push({method: 'POST',   path: prefix+'/',             ctrl: ctrl.people.post,        key: 'POST' });
routes.push({method: 'POST',   path: prefix+'/:id/tag/:tag', ctrl: ctrl.people.addTag,      key: 'POSTtag' });
routes.push({method: 'PUT',    path: prefix+'/:id',          ctrl: ctrl.people.put,         key: 'PUT' });
routes.push({method: 'PUT',    path: prefix+'/:id/tag/:tag', ctrl: ctrl.people.delTag,      key: 'DELETEtag' });
routes.push({method: 'DELETE', path: prefix+'/:id',          ctrl: ctrl.people.delete,      key: 'DELETE' });


module.exports = routes;
