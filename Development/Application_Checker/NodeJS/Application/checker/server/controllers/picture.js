/*
 * File 	: ./server/controllers/file.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage picture of people stored in the Picture service
 * Version  	: 1.0.0
 */

var	_                 = require('underscore'),
	findError         = require('../errors/referential.js'),
	isEmpty	          = require('toolbox')('ISEMPTY'),
	session_info      = require('../models/session.js'),
	ws_client_people  = require('../models/ws_people.js'),
	ws_client_picture = require('../models/ws_picture.js');


function getFunc(req, res, next){
	if(isEmpty(req.params.login)){
	        return next(findError("CHECKER.7.1.1"));
	}
	else {
		ws_client_people.getOneLogin({ login : req.params.login },
			 function(err, elmt){
			 	session_info.getInfos({sid : req.query.sid},
			 		function(err2, infos){
						if(req.params.login!==infos.login &&
						   		!_.contains(infos.roles, 'PEOPLE_UNLOCKED')){
							return next(findError("CHECKER.7.1.2"));
						}
						else if(!isEmpty(err)){
							return next(err);
						}
				 		else if(!isEmpty(err2)){
							return next(err2);
						}
						else if(isEmpty(elmt) || isEmpty(elmt.picture)){
							return next(findError("CHECKER.7.1.3"));
						}
						else{
							ws_client_picture.get({ filepath : elmt.picture }, res,
										function(err2){
											return next(err2);
										});
						}
			 	});
		});
	}
}


module.exports.picture = {
	get : getFunc
};
