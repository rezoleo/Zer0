/* 
 * File 	: ./server/controllers/authentification.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage an authentification object stored in the Authentification service
 * Version  	: 1.0.0
 */

var	_            = require('underscore'),
	isEmpty      = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	filter       = require('../utils/filter.js'),
	ws_client    = require('../models/ws_authentification.js');


function getFunc(req, res, next){
	ws_client.get(function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(filter.researchBarFilter(elmt, {researchStr : req.query.research,
											     fields      : function(item){
											     	return [item.login];
											     }, 
											     first       : req.query.first, 
											     last        : req.query.last,
											     sortBy      : 'login'}));
				}
		      });
}

function getOneFunc(req, res, next){
	ws_client.getOne({ login : req.params.login },
			 function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(elmt);
				}
		      });
}

function signupFunc(req, res, next){
	ws_client.signup({ login : req.body.login, password : req.body.password, mail : req.body.mail, status : req.body.status, creator : session_info.getLogin(req) },
			 function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(elmt);
				}
			 });
}

function updateFunc(req, res, next){
	ws_client.update({ login : req.params.login, password : req.body.password, mail : req.body.mail, status : req.body.status, updator : session_info.getLogin(req) },
			 function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(elmt);
				}
			 });
}

function deleteFunc(req, res, next){
	ws_client.delete({ login : req.params.login },
			 function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(elmt);
				}
			 });
}


module.exports.authentification = {
	get	: getFunc,
	getOne	: getOneFunc,
	signup 	: signupFunc,
	update 	: updateFunc,
	delete 	: deleteFunc
};
