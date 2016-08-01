/* 
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes for group registration service
 * Version  	: 1.0.0
 */

var	_    = require('underscore'),
	ctrl = _.extend({}, require('../controllers/group.js'));

var prefix = '/api/group';
var routes = [];

routes.push({method: 'GET',    path: prefix+'/',                ctrl: ctrl.group.get,            key: 'GET' });
routes.push({method: 'GET',    path: prefix+'/:id',             ctrl: ctrl.group.getOne,         key: 'GETid' });
routes.push({method: 'GET',    path: prefix+'/name/:name',      ctrl: ctrl.group.getOneByName,   key: 'GETname' });
routes.push({method: 'GET',    path: prefix+'/search/:login',   ctrl: ctrl.group.searchByLogin,  key: 'GETsearch' });
routes.push({method: 'POST',   path: prefix+'/',                ctrl: ctrl.group.post,           key: 'POST' });
routes.push({method: 'POST',   path: prefix+'/:id/member',      ctrl: ctrl.group.addMember,      key: 'POSTmember' });
routes.push({method: 'POST',   path: prefix+'/:id/responsible', ctrl: ctrl.group.addResponsible, key: 'POSTresponsability' });
routes.push({method: 'PUT',    path: prefix+'/:id',             ctrl: ctrl.group.put,            key: 'PUT' });
routes.push({method: 'PUT',    path: prefix+'/:id/member',      ctrl: ctrl.group.delMember,      key: 'DELETEmember' });
routes.push({method: 'PUT',    path: prefix+'/:id/responsible', ctrl: ctrl.group.delResponsible, key: 'DELETEresponsability' });
routes.push({method: 'DELETE', path: prefix+'/:id',             ctrl: ctrl.group.delete,         key: 'DELETE' });


module.exports = routes;
