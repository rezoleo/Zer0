/*
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes for administration application
 * Version  	: 1.1.0
 */ 

var	_    = require('underscore'),
	cond = require('./category_condition.js'),
	ctrl = _.extend({}, require('../controllers/login.js'),
			    require('../controllers/alert.js'),
			    require('../controllers/authentification.js'),
			    require('../controllers/card.js'),
			    require('../controllers/contributor.js'),
			    require('../controllers/group.js'),
			    require('../controllers/people.js'),
			    require('../controllers/picture.js'),
			    require('../controllers/roles.js'));

var routes = [];
var prefix = '/api';


//Routes to manage signin, signout in the application
var prefix_login=prefix+'/login';
routes.push({method: 'GET',    path: prefix_login+'/', 	     ctrl: ctrl.login.getLogin, isLogged : false });
routes.push({method: 'POST',   path: prefix_login+'/:login', ctrl: ctrl.login.signin,   isLogged : false });
routes.push({method: 'DELETE', path: prefix_login+'/', 	     ctrl: ctrl.login.signout,  isLogged : true  });

//Routes to manage authentification information
var prefix_auth=prefix+'/authentification';
routes.push({method: 'GET',    path: prefix_auth+'/',       ctrl: ctrl.authentification.get,    isLogged : true, roles : ['AUTH_READ', 'AUTH_WRITE', 'AUTH_ADMIN'] });
routes.push({method: 'GET',    path: prefix_auth+'/:login', ctrl: ctrl.authentification.getOne, isLogged : true, roles : ['AUTH_READ', 'AUTH_WRITE', 'AUTH_ADMIN'] });
routes.push({method: 'POST',   path: prefix_auth+'/',       ctrl: ctrl.authentification.signup, isLogged : true, roles : ['AUTH_WRITE', 'AUTH_ADMIN'] });
routes.push({method: 'PUT',    path: prefix_auth+'/:login', ctrl: ctrl.authentification.update, isLogged : true, roles : ['AUTH_WRITE', 'AUTH_ADMIN'] });
routes.push({method: 'DELETE', path: prefix_auth+'/:login', ctrl: ctrl.authentification.delete, isLogged : true, roles : ['AUTH_WRITE', 'AUTH_ADMIN'] });

//Routes to manage roles of user for specific categories (authentification/card/group/people)
var prefix_roles=prefix+'/roles';
routes.push({method: 'GET',    path: prefix_roles+'/:category',	       ctrl: ctrl.roles.getRoles,    isLogged : true, roles : ['ALERT_ADMIN', 'AUTH_ADMIN', 'CARD_ADMIN', 'CONTRIBUTOR_ADMIN', 'GROUP_ADMIN', 'PEOPLE_ADMIN'], extended_restriction : cond });
routes.push({method: 'POST',   path: prefix_roles+'/:category/:login', ctrl: ctrl.roles.updateRole,  isLogged : true, roles : ['ALERT_ADMIN', 'AUTH_ADMIN', 'CARD_ADMIN', 'CONTRIBUTOR_ADMIN', 'GROUP_ADMIN', 'PEOPLE_ADMIN'], extended_restriction : cond });
routes.push({method: 'DELETE', path: prefix_roles+'/:category/:login', ctrl: ctrl.roles.removeRoles, isLogged : true, roles : ['ALERT_ADMIN', 'AUTH_ADMIN', 'CARD_ADMIN', 'CONTRIBUTOR_ADMIN', 'GROUP_ADMIN', 'PEOPLE_ADMIN'], extended_restriction : cond });

//Routes to manage alerts
var prefix_alert=prefix+'/alert';
routes.push({method: 'GET', path: prefix_alert+'/',    ctrl: ctrl.alert.get,    isLogged : true, roles : ['ALERT_READ', 'ALERT_ADMIN'] });
routes.push({method: 'GET', path: prefix_alert+'/:id', ctrl: ctrl.alert.getOne, isLogged : true, roles : ['ALERT_READ', 'ALERT_ADMIN'] });

//Routes to manage cards
var prefix_card=prefix+'/card';
routes.push({method: 'GET',    path: prefix_card+'/',           ctrl: ctrl.card.get,        isLogged : true, roles : ['CARD_READ', 'CARD_WRITE', 'CARD_ADMIN'] });
routes.push({method: 'GET',    path: prefix_card+'/:id',        ctrl: ctrl.card.getOne,     isLogged : true, roles : ['CARD_READ', 'CARD_WRITE', 'CARD_ADMIN'] });
routes.push({method: 'GET',    path: prefix_card+'/code/:code', ctrl: ctrl.card.getOneCard, isLogged : true, roles : ['CARD_READ', 'CARD_WRITE', 'CARD_ADMIN'] });
routes.push({method: 'POST',   path: prefix_card+'/',           ctrl: ctrl.card.post,       isLogged : true, roles : ['CARD_WRITE', 'CARD_ADMIN'] });
routes.push({method: 'PUT',    path: prefix_card+'/:id',        ctrl: ctrl.card.put,        isLogged : true, roles : ['CARD_WRITE', 'CARD_ADMIN'] });
routes.push({method: 'DELETE', path: prefix_card+'/:id',        ctrl: ctrl.card.delete,     isLogged : true, roles : ['CARD_WRITE', 'CARD_ADMIN'] });

//Routes to manage contributors
var prefix_contributor=prefix+'/contributor';
routes.push({method: 'GET',    path: prefix_contributor+'/',             ctrl: ctrl.contributor.get,	     isLogged : true, roles : ['CONTRIBUTOR_READ', 'CONTRIBUTOR_WRITE', 'CONTRIBUTOR_ADMIN'] });
routes.push({method: 'GET',    path: prefix_contributor+'/:id',          ctrl: ctrl.contributor.getOne,	     isLogged : true, roles : ['CONTRIBUTOR_READ', 'CONTRIBUTOR_WRITE', 'CONTRIBUTOR_ADMIN'] });
routes.push({method: 'GET',    path: prefix_contributor+'/login/:login', ctrl: ctrl.contributor.getOneLogin, isLogged : true, roles : ['CONTRIBUTOR_READ', 'CONTRIBUTOR_WRITE', 'CONTRIBUTOR_ADMIN'] });
routes.push({method: 'POST',   path: prefix_contributor+'/',             ctrl: ctrl.contributor.post,	     isLogged : true, roles : ['CONTRIBUTOR_WRITE', 'CONTRIBUTOR_ADMIN'] });
routes.push({method: 'DELETE', path: prefix_contributor+'/:id',          ctrl: ctrl.contributor.delete,	     isLogged : true, roles : ['CONTRIBUTOR_WRITE', 'CONTRIBUTOR_ADMIN'] });

//Routes to manage groups
var prefix_group=prefix+'/group';
routes.push({method: 'GET',    path: prefix_group+'/',                ctrl: ctrl.group.get,            isLogged : true, roles : ['GROUP_READ', 'GROUP_WRITE', 'GROUP_ADMIN'] });
routes.push({method: 'GET',    path: prefix_group+'/:id',             ctrl: ctrl.group.getOne,         isLogged : true, roles : ['GROUP_READ', 'GROUP_WRITE', 'GROUP_ADMIN'] });
routes.push({method: 'GET',    path: prefix_group+'/name/:name',      ctrl: ctrl.group.getOneByName,   isLogged : true, roles : ['GROUP_READ', 'GROUP_WRITE', 'GROUP_ADMIN'] });
routes.push({method: 'GET',    path: prefix_group+'/search/:login',   ctrl: ctrl.group.searchByLogin,  isLogged : true, roles : ['GROUP_READ', 'GROUP_WRITE', 'GROUP_ADMIN'] });
routes.push({method: 'POST',   path: prefix_group+'/',                ctrl: ctrl.group.post,           isLogged : true, roles : ['GROUP_WRITE', 'GROUP_ADMIN'] });
routes.push({method: 'POST',   path: prefix_group+'/:id/member',      ctrl: ctrl.group.addMember,      isLogged : true, roles : ['GROUP_WRITE', 'GROUP_ADMIN'] });
routes.push({method: 'POST',   path: prefix_group+'/:id/responsible', ctrl: ctrl.group.addResponsible, isLogged : true, roles : ['GROUP_WRITE', 'GROUP_ADMIN'] });
routes.push({method: 'PUT',    path: prefix_group+'/:id',             ctrl: ctrl.group.put,            isLogged : true, roles : ['GROUP_WRITE', 'GROUP_ADMIN'] });
routes.push({method: 'PUT',    path: prefix_group+'/:id/member',      ctrl: ctrl.group.delMember,      isLogged : true, roles : ['GROUP_WRITE', 'GROUP_ADMIN'] });
routes.push({method: 'PUT',    path: prefix_group+'/:id/responsible', ctrl: ctrl.group.delResponsible, isLogged : true, roles : ['GROUP_WRITE', 'GROUP_ADMIN'] });
routes.push({method: 'DELETE', path: prefix_group+'/:id',             ctrl: ctrl.group.delete,         isLogged : true, roles : ['GROUP_WRITE', 'GROUP_ADMIN'] });

//Routes to manage people
var prefix_people=prefix+'/people';
routes.push({method: 'GET',    path: prefix_people+'/',             ctrl: ctrl.people.get,         isLogged : true, roles : ['PEOPLE_READ',  'PEOPLE_WRITE', 'PEOPLE_ADMIN'] });
routes.push({method: 'GET',    path: prefix_people+'/:id',          ctrl: ctrl.people.getOne,      isLogged : true, roles : ['PEOPLE_READ',  'PEOPLE_WRITE', 'PEOPLE_ADMIN'] });
routes.push({method: 'GET',    path: prefix_people+'/login/:login', ctrl: ctrl.people.getOneLogin, isLogged : true, roles : ['PEOPLE_READ',  'PEOPLE_WRITE', 'PEOPLE_ADMIN'] });
routes.push({method: 'GET',    path: prefix_people+'/mail/:mail',   ctrl: ctrl.people.getOneMail,  isLogged : true, roles : ['PEOPLE_READ',  'PEOPLE_WRITE', 'PEOPLE_ADMIN'] });
routes.push({method: 'POST',   path: prefix_people+'/',             ctrl: ctrl.people.post,        isLogged : true, roles : ['PEOPLE_WRITE', 'PEOPLE_ADMIN'] });
routes.push({method: 'POST',   path: prefix_people+'/:id/tag/:tag', ctrl: ctrl.people.addTag,      isLogged : true, roles : ['PEOPLE_WRITE', 'PEOPLE_ADMIN'] });
routes.push({method: 'PUT',    path: prefix_people+'/:id',          ctrl: ctrl.people.put,         isLogged : true, roles : ['PEOPLE_WRITE', 'PEOPLE_ADMIN'] });
routes.push({method: 'PUT',    path: prefix_people+'/:id/tag/:tag', ctrl: ctrl.people.delTag,      isLogged : true, roles : ['PEOPLE_WRITE', 'PEOPLE_ADMIN'] });
routes.push({method: 'DELETE', path: prefix_people+'/:id',          ctrl: ctrl.people.delete,      isLogged : true, roles : ['PEOPLE_WRITE', 'PEOPLE_ADMIN'] });

//Routes to manage pictures
var prefix_picture=prefix+'/picture';
routes.push({method: 'GET',    path: prefix_picture+'/:id',         ctrl: ctrl.picture.get,    isLogged : true, roles : ['PEOPLE_READ',  'PEOPLE_WRITE', 'PEOPLE_ADMIN']});
routes.push({method: 'GET',    path: prefix_picture+'/:id/:random', ctrl: ctrl.picture.get,    isLogged : true, roles : ['PEOPLE_READ',  'PEOPLE_WRITE', 'PEOPLE_ADMIN']});
routes.push({method: 'POST',   path: prefix_picture+'/:id',         ctrl: ctrl.picture.post,   isLogged : true, roles : ['PEOPLE_WRITE', 'PEOPLE_ADMIN'] });
routes.push({method: 'DELETE', path: prefix_picture+'/:id',         ctrl: ctrl.picture.delete, isLogged : true, roles : ['PEOPLE_WRITE', 'PEOPLE_ADMIN'] });


module.exports = routes;
