/* 
 * File 	: ./server/controllers/people.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to read information about people
 * Version  	: 1.0.0
 */

var	_            = require('underscore'),
	isArray	     = require('toolbox')('ISARRAY'),
	isEmpty	     = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	ws_client    = require('../models/ws_people.js');


function check(req, res, next){
	if(isEmpty(req.params.login)){
	        return next(findError("CHECKER.4.1.1"));
	}
	else {
		ws_client.getOneLogin({ login : req.params.login },
				     function(err, elmt){
						if(req.params.login!==session_info.getLogin(req) &&
						   !_.contains(session_info.getRoles(req), 'PEOPLE_UNLOCKED')){
						        return next(findError("CHECKER.4.1.2"));
						}
						else if(err){
							return next(err);
						}
						else if(_.contains(session_info.getRoles(req), 'PEOPLE_UNLOCKED')){
							if(!isEmpty(elmt)){
								elmt.registered = true;
							}
							res.status(200).json(elmt);
						}
						else{
							res.status(200).json({registered : true,
									      login	 : elmt.login,
									      firstname	 : elmt.firstname,
									      lastname	 : elmt.lastname,
									      major	 : elmt.major,
									      sex	 : elmt.sex,
									      mail	 : elmt.mail});
						}
			  	     });
	}
}


module.exports.people = {
	check : check
}
