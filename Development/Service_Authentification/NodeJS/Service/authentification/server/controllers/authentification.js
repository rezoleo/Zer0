/* 
 * File 	: ./server/controllers/authentification.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage an authentification object stored in the Mongoose database
 * Version  	: 1.0.0
 */

var	_        = require('underscore'),
	findError= require('../errors/referential.js'),
	isEmpty	 = require('toolbox')('ISEMPTY'),
	objectDB = require('../models/authentification.js');


function getFunc(req, res, next){
	new objectDB().getAll(function (err, elmt){
					if (err){
						return next(err);
					}
					else{
						var elmt2 = [];
						for(var i=0; i<elmt.length; i++){
							elmt2.push(_.omit(elmt[i].toJSON(), 'password', 'creatorIP', 'updatorIP'))
						}
	                                        res.status(200).json(elmt2);
					}
				});
}

function getOneFunc(req, res, next){
	if(isEmpty(req.params.login)){
		return next(findError("AUTH.1.1.1"));
	}
	else {
		var myObject = new objectDB({ login : req.params.login });
		myObject.findElmtByLogin(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("AUTH.1.1.2"));
						}
						else{
							res.status(200).json(_.omit(elmt.toJSON(), 'password', 'creatorIP', 'updatorIP'));
						}
					});
	}
}

function signupFunc(req, res, next){
	if(isEmpty(req.body.login)){
		return next(findError("AUTH.1.2.1"));
	}
	else if(isEmpty(req.body.mail)){
		return next(findError("AUTH.1.2.2"));
	}
	else if(isEmpty(req.body.password)){
		return next(findError("AUTH.1.2.3"));
	}
	else if(isEmpty(req.body.status)){
		return next(findError("AUTH.1.2.4"));
	}
	else {
		var myObject = new objectDB({ 	login          : req.body.login,
						mail           : req.body.mail,
						password       : req.body.password,
						status         : req.body.status,
						created        : new Date(),
						creator        : req.body.creator,
						creatorIP      : req.connection.remoteAddress,
						creatorService : req.query.token.source_service,
						updator        : null,
						updated        : null,
						updatorIP      : null,
						updatorService : null});
		myObject.findElmtByLogin(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(!isEmpty(elmt)){
							return next(findError("AUTH.1.2.5"));
						}
						else{
							myObject.findElmtByMail(function (err2, elmt2){
								if (err2){
									return next(err2);
								}
								else if(!isEmpty(elmt2)){
									return next(findError("AUTH.1.2.6"));
								}
								else{
									myObject.addElmt(function (err3, elmt3){
												if (err3){
													return next(err3);
												}
												else if(isEmpty(elmt3)){
													return next(findError("AUTH.1.2.7"));
												}
												else{
													res.status(200).json(_.omit(elmt3.toJSON(), 'password', 'creatorIP', 'updatorIP'));
												}
											  });
								}
							});
						}
					});
	}
}

function signinFunc(req, res, next){
        if(isEmpty(req.params.login)){
                return next(findError("AUTH.1.3.1"));
        }
        if(isEmpty(req.body.password)){
                return next(findError("AUTH.1.3.2"));
        }
        else {
                var myObject = new objectDB({ login : req.params.login, password : req.body.password });
                myObject.findElmtByLoginAndPass(function (err, elmt){
                                                if (err){
                                                        return next(err);
                                                }
                                                else if(isEmpty(elmt)){
                                                        return next(findError("AUTH.1.3.3"));
                                                }
                                                else if(elmt.status!=="ON"){
                                                        return next(findError("AUTH.1.3.4"));
                                                }
						else{
	                                                res.status(200).json(_.omit(elmt.toJSON(), 'password', 'creatorIP', 'updatorIP'));
						}
                                        });
        }
}

function updateFunc(req, res, next){
	if(isEmpty(req.params.login)){
		return next(findError("AUTH.1.4.1"));
	}
	else if(isEmpty(req.body.mail)){
		return next(findError("AUTH.1.4.2"));
	}
	else if(isEmpty(req.body.password) && req.query.action!=="keep-old-password"){
		return next(findError("AUTH.1.4.3"));
	}
	else if(isEmpty(req.body.status)){
		return next(findError("AUTH.1.4.4"));
	}
	else {
		var myObject = new objectDB({	login          : req.params.login,
						mail           : req.body.mail,
						password       : req.body.password,
						status         : req.body.status,
						updator        : req.body.updator,
						updated        : new Date(),
						updatorIP      : req.connection.remoteAddress,
						updatorService : req.query.token.source_service});
		myObject.findElmtByLogin(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("AUTH.1.4.5"));
						}
						else{
							myObject.findElmtByMail(function (err2, elmt2){
								if (err2){
									return next(err2);
								}
								else if(!isEmpty(elmt2) && elmt2.login.toString()!=myObject.login.toString()){
									return next(findError("AUTH.1.4.6"));
								}
								else{
									var options = {keepPassword : false};
									if(req.query.action==="keep-old-password"){
										options.keepPassword=true;
									}
									myObject.updateElmtByLogin(options, function (err3){
										if (err3){
											return next(err3);
										}
										else{
											myObject.findElmtByLogin(function (err4, elmt3){
															if (err4){
																return next(err4);
															}
															else if(isEmpty(elmt3)){
																return next(findError("AUTH.1.4.7"));
															}
															else{
														                res.status(200).json(_.omit(elmt3.toJSON(), 'password', 'creatorIP', 'updatorIP'));
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

function deleteFunc(req, res, next){
	if(isEmpty(req.params.login)){
		return next(findError("AUTH.1.5.1"));
	}
	else {
		var myObject = new objectDB({ login : req.params.login });
		myObject.findElmtByLogin(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("AUTH.1.5.2"));
						}
						else{
							myObject.removeElmtByLogin(function (err2){
											if (err2){ 
												return next(err2);
											}
											else{
							                                        res.status(200).json(_.omit(elmt.toJSON(), 'password', 'creatorIP', 'updatorIP'));
											}
							});
						}
					});
	}
}


module.exports.authentification = {
	get    : getFunc,
	getOne : getOneFunc,
	signup : signupFunc,
	signin : signinFunc,
	update : updateFunc,
	delete : deleteFunc
};
