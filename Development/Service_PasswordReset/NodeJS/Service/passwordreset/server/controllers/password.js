/* 
 * File 	: ./server/controllers/password.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to change the password of a user
 * Version  	: 1.0.0
 */


var conf	= require('../conf/conf.js'),
    isEmpty	= require('toolbox')('ISEMPTY'),
    findError	= require('../errors/referential.js'),
    objectDB	= require('../models/link.js'),
    ws_mail     = require('../models/ws_mail.js'),
    mail        = require('../utils/mail.js'),
    ws_client	= require('../models/ws_authentification.js');


function changePasswordFunc(req, res, next){
	if(isEmpty(req.params.token)){
		return next(findError("PASSWDRESET.2.1.1"));
	}
	else if(isEmpty(req.body.new_password)){
		return next(findError("PASSWDRESET.2.1.2"));
	}
	else{
		var myObject = new objectDB({ secretkey : req.params.token });
		myObject.removeOldElmts(function (err){
			if(err){
				return next(err);
			}
			else{
				myObject.findElmtBySecret(function (err2, elmt2){
					if(err2){
						return next(err2);
					}
					else if(isEmpty(elmt2)){
						return next(findError("PASSWDRESET.2.1.3"));
					}
					else{
						ws_client.getOne({ login : elmt2.login },
							       function(err3, elmt3){
									if(err3){
										return next(err3);
									}
									if(isEmpty(elmt3)){
										return next(findError("PASSWDRESET.2.1.4"));
									}
									else{
										ws_client.update({ login    : elmt2.login, 
												   mail     : elmt2.mail, 
												   password : req.body.new_password,
												   status   : elmt3.status,
												   updator  : elmt2.login },
												 function(err4, elmt4){
													if(err4){
														return next(err4);
													}
													else{
														myObject.removeElmtBySecret(function (err5, elmt5){
															if(err5){
																return next(err5);
															}
															else{
																ws_mail.postConfirmationByMail(
																	  mail.sendConfirmationInfos({ login : elmt2.login, mail : elmt2.mail }),
																	  function(err6){
																		if(err6){
																			return next(findError("PASSWDRESET.2.1.5"));
																		}
																		else{
																			res.status(200).json({status : "success"});
																		}
																});
															}
														});
													}
											 });
									}
							});
					}
				});
			}
		});
	}
}


module.exports.password = {
	changePassword : changePasswordFunc
}
