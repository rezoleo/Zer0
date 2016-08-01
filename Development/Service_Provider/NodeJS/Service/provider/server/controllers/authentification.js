/* 
 * File 	: ./server/controllers/authentification.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage an authentification object stored in the Authentification service
 * Version  	: 1.0.0
 */

var	_            = require('underscore'),
	isEmpty      = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	ws_client    = require('../models/ws_authentification.js');


function getOneFunc(req, res, next){
	ws_client.getOne({ login : req.params.login },
			 function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(_.omit(elmt, 'password', '__v', 'creator', 'created', 'creatorIP', 'creatorService', 'updator', 'updated', 'updatorIP', 'updatorService'));
				}
			 });
}

function checkFunc(req, res, next){
	ws_client.check({ login : req.params.login, password : req.body.password },
			 function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(_.omit(elmt, 'password', '__v', 'creator', 'created', 'creatorIP', 'creatorService', 'updator', 'updated', 'updatorIP', 'updatorService'));
				}
			 });
}


module.exports.authentification = {
	getOne : getOneFunc,
	check  : checkFunc
};
