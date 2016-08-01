/* 
 * File 	: ./server/controllers/login.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to connect or disconnect the user to the application
 * Version  	: 1.0.0
 */

var roles_common  = require('authorization')(require('mongoose'), require('../conf/roles.js')),
    access	      = roles_common.access,
    roles	      = roles_common.roles,
    findError	  = require('../errors/referential.js'),
    isEmpty       = require('toolbox')('ISEMPTY'),
    session_info  = require('toolbox')('SESSION_INFO'),
    ws_client     = require('../models/ws_authentification');


function getLoginFunc(req, res, next){
	res.status(200).json({	login : session_info.getLogin(req),
							gate  : session_info.getGate(req),
							roles : session_info.getRoles(req)});
}

function signinFunc(req, res, next){
	if(isEmpty(req.params.login)){
		return next(findError("CHECKER.1.1.1"));
	}
	else if(isEmpty(req.body.password)){
		return next(findError("CHECKER.1.1.2"));
	}
	else{
		access.getOne({ login : req.params.login },
				     function(err, elmt){
					  if(err){
					  	return next(err);
					  }
					  else if(isEmpty(elmt)){
					  	return next(findError("CHECKER.1.1.3"));
					  }
					  else{
						ws_client.check({login    : req.params.login,
										 password : req.body.password},
								function(err2, elmt2){
									if(err2){
										return next(err2);
									}
									else if(isEmpty(elmt2)){
										return next(findError("CHECKER.1.1.4"));
									}
									else{
										res.status(200).json({	login : session_info.setLogin(req, elmt.login),
																gate  : session_info.setGate(req, 'classical'),
																roles : session_info.setRoles(req, elmt.roles) });
									}
								 });
					  }
				     });
	}
}

function signoutFunc(req, res, next){
	res.status(200).json({	login : session_info.setLogin(req, null),
							gate  : session_info.setGate(req,  null),
							roles : session_info.setRoles(req, null)});
}


module.exports.login = {
	roles_common   : { access : roles_common.access,
					   roles  : roles_common.roles},
	getLogin : getLoginFunc,
	signin 	 : signinFunc,
	signout	 : signoutFunc
}
