/* 
 * File 	: ./server/controllers/link.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to send a password regeneration link to a user
 * Version  	: 1.0.0
 */


var conf       = require('../conf/conf.js'),
    isEmpty    = require('toolbox')('ISEMPTY'),
    findError  = require('../errors/referential.js'),
    ws_mail    = require('../models/ws_mail.js'),
    mail       = require('../utils/mail.js'),
    objectDB   = require('../models/link.js'),
    token      = require('../utils/token.js'),
    ws_auth    = require('../models/ws_authentification.js');


function getOneFunc(req, res, next){
	if(isEmpty(req.params.token)){
		return next(findError("PASSWDRESET.1.1.1"));
	}
	else {
		var myObject = new objectDB({ secretkey : req.params.token });
		myObject.findElmtBySecret(function (err, elmt){
						if(err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("PASSWDRESET.1.1.2"));
						}
						else{
	                                                res.status(200).json({login : elmt.login, endDate : elmt.endDate});
						}
					});
	}
}

function sendMailWithLinkFunc(req, res, next){
	if(isEmpty(req.params.login)){
		return next(findError("PASSWDRESET.1.2.1"));
	}
	else {
		var myObject = new objectDB({});
		myObject.removeOldElmts(function (err){
			if(err){
				return next(err);
			}
			else{
				var myObject = new objectDB({ login : req.params.login });
				myObject.findElmtByLogin(function (err2, elmt2){
					if(err2){
						return next(err2);
					}
					else if(!isEmpty(elmt2)){
						return next(findError("PASSWDRESET.1.2.2"));
					}
					else{
						ws_auth.getOne({ login : req.params.login },
						       function(err3, elmt3){
								if(err3){
									return next(err3);
								}
								else if(isEmpty(elmt3)){
									return next(findError("PASSWDRESET.1.2.3"));
								}
								else if(isEmpty(elmt3.mail)){
									return next(findError("PASSWDRESET.1.2.4"));
								}
								else{
									var secretkey = token.generate(conf.mail.token.length);
									var myObject = new objectDB({ login 	: req.params.login,
												      mail	: elmt3.mail,
												      secretkey	: secretkey,
												      lifespan	: conf.mail.token.lifespan });
									myObject.addElmt(function (err4, elmt4){
										if(err4){
											return next(err4);
										}
										else if(isEmpty(elmt4)){
											return next(findError("PASSWDRESET.1.2.5"));
										}
										else{
											ws_mail.postLinkByMail(
												  mail.sendLinkInfos({ login : elmt3.login, mail : elmt3.mail,
														       url   : conf.http.rootUrl+'#?token='+secretkey }),
												  function(err5){
													if(err5){
														return next(findError("PASSWDRESET.1.2.6"));
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
}


module.exports.link = {
	getOne		   : getOneFunc,
	sendMailWithLink   : sendMailWithLinkFunc
}
