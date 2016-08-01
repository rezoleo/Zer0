/*
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes for personn and card checking application
 * Version  	: 1.0.0
 */

var	_    = require('underscore'),
	ctrl = _.extend({}, require('../controllers/login.js'),
                            require('../controllers/card.js'),
                            require('../controllers/contributor.js'),
                            require('../controllers/notification.js'),
                            require('../controllers/people.js'),
                            require('../controllers/picture.js'),
                            require('../controllers/roles.js'));

var routes = [];
var prefix = '/api';


//Routes to manage signin, signout in the application
var prefix_login=prefix+'/login';
routes.push({method: 'GET',    path: prefix_login+'/', 	     ctrl: ctrl.login.getLogin, isLogged : true  });
routes.push({method: 'POST',   path: prefix_login+'/:login', ctrl: ctrl.login.signin,   isLogged : false });
routes.push({method: 'DELETE', path: prefix_login+'/', 	     ctrl: ctrl.login.signout,  isLogged : true  });

//Routes to manage roles of user for specific categories (card/contributor/notification/people/role)
var prefix_role=prefix+'/roles';
routes.push({method: 'GET',    path: prefix_role+'/:category',	      ctrl: ctrl.roles.getRoles,    isLogged : true, roles : ['ROLE_MANAGER'] });
routes.push({method: 'POST',   path: prefix_role+'/:category/:login', ctrl: ctrl.roles.updateRole,  isLogged : true, roles : ['ROLE_MANAGER'] });
routes.push({method: 'DELETE', path: prefix_role+'/:category/:login', ctrl: ctrl.roles.removeRoles, isLogged : true, roles : ['ROLE_MANAGER'] });

//Routes to read information about cards
var prefix_card=prefix+'/card';
routes.push({method: 'GET',   path: prefix_card+'/:code', ctrl: ctrl.card.check, isLogged : true });

//Routes to read information about people
var prefix_people=prefix+'/people';
routes.push({method: 'GET',   path: prefix_people+'/:login', ctrl: ctrl.people.check, isLogged : true});

//Routes to read information about picture
var prefix_picture=prefix+'/picture';
routes.push({method: 'GET',   path: prefix_picture+'/:login', ctrl: ctrl.picture.get, isLogged : false});

//Routes to read information about contributor
var prefix_contributor=prefix+'/contributor';
routes.push({method: 'GET',    path: prefix_contributor+'/:login', ctrl: ctrl.contributor.getOneLogin, isLogged : true});

//Routes to manage notifications
var prefix_notification=prefix+'/notification';
routes.push({method: 'GET',    path: prefix_notification+'/', ctrl: ctrl.notification.getOne, isLogged : false });
routes.push({method: 'POST',   path: prefix_notification+'/', ctrl: ctrl.notification.write,  isLogged : true, roles : ['ROLE_NOTIFICATION'] });
routes.push({method: 'DELETE', path: prefix_notification+'/', ctrl: ctrl.notification.remove, isLogged : true, roles : ['ROLE_NOTIFICATION'] });


module.exports = routes;
