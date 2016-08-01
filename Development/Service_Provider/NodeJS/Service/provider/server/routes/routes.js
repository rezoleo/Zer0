/* 
 * File 	: ./server/routes/routes.js
 * Author(s)	: Zidmann
 * Function 	: This file manages NodeJS server routes for provider service
 * Version  	: 1.0.0
 */

var	_    = require('underscore'),
	ctrl = _.extend({}, require('../controllers/authentification.js'),
			    require('../controllers/card.js'),
			    require('../controllers/contributor.js'),
			    require('../controllers/group.js'),
			    require('../controllers/people.js'),
			    require('../controllers/picture.js'));

var routes = [];
var prefix = null;

prefix = '/api/authentification';
routes.push({method: 'GET',    path: prefix+'/:login', ctrl: ctrl.authentification.getOne, key: 'GETAuth' });
routes.push({method: 'POST',   path: prefix+'/:login', ctrl: ctrl.authentification.check,  key: 'POSTAuthLogin' });

prefix = '/api/card';
routes.push({method: 'GET',    path: prefix+'/',           ctrl: ctrl.card.get,        key: 'GETCard' });
routes.push({method: 'GET',    path: prefix+'/:id',        ctrl: ctrl.card.getOne,     key: 'GETCardId' });
routes.push({method: 'GET',    path: prefix+'/code/:code', ctrl: ctrl.card.getOneCard, key: 'GETCardCode' });

prefix = '/api/contributor';
routes.push({method: 'GET',    path: prefix+'/',             ctrl: ctrl.contributor.get,         key: 'GETContributor' });
routes.push({method: 'GET',    path: prefix+'/:id',          ctrl: ctrl.contributor.getOne,      key: 'GETContributorId' });
routes.push({method: 'GET',    path: prefix+'/login/:login', ctrl: ctrl.contributor.getOneLogin, key: 'GETContributorLogin' });

prefix = '/api/group';
routes.push({method: 'GET',    path: prefix+'/',              ctrl: ctrl.group.get,            key: 'GETGroup' });
routes.push({method: 'GET',    path: prefix+'/:id',           ctrl: ctrl.group.getOne,         key: 'GETGroupId' });
routes.push({method: 'GET',    path: prefix+'/name/:name',    ctrl: ctrl.group.getOneByName,   key: 'GETGroupName' });
routes.push({method: 'GET',    path: prefix+'/search/:login', ctrl: ctrl.group.searchByLogin,  key: 'GETGroupSearch' });

prefix = '/api/people';
routes.push({method: 'GET',    path: prefix+'/',             ctrl: ctrl.people.get,         key: 'GETPeople' });
routes.push({method: 'GET',    path: prefix+'/:id',          ctrl: ctrl.people.getOne,      key: 'GETPeopleId' });
routes.push({method: 'GET',    path: prefix+'/login/:login', ctrl: ctrl.people.getOneLogin, key: 'GETPeopleLogin' });
routes.push({method: 'GET',    path: prefix+'/mail/:mail',   ctrl: ctrl.people.getOneMail,  key: 'GETPeopleMail' });

prefix = '/api/file';
routes.push({method: 'GET',    path: prefix+'/:dir/:file', ctrl: ctrl.picture.get,    key: 'GETPicture' });


module.exports = routes;
