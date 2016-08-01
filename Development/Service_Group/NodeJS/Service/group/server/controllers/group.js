/* 
 * File 	: ./server/controllers/group.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage group information stored in the Mongoose database
 * Version  	: 1.0.0
 */

var	_        = require('underscore'),
	cache	 = require('cache')('INFOS')(require('mongoose')),
	findError= require('../errors/referential.js'),
	isEmpty	 = require('toolbox')('ISEMPTY'),
	objectDB = require('../models/group.js');



function getFunc(req, res, next){
	if(req.query.action==="get-cache-infos"){
		cache.getCacheInfo(req, res, next);
	}
	else {
		new objectDB().getAll(function (err, elmt){
						if (err){
							return next(err);
						}
						else{
							var elmt2 = [];
							for(var i=0; i<elmt.length; i++){
								elmt2.push(_.omit(elmt[i].toJSON(), 'creatorIP', 'updatorIP'))
							}
							res.status(200).json(elmt2);
						}
					});
	}
}

function getOneFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("GROUP.1.1.1"));
	}
	else {
		var myObject = new objectDB({ _id : req.params.id });
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("GROUP.1.1.2"));
						}
						else{
	                                                res.status(200).json(_.omit(elmt.toJSON(), 'creatorIP', 'updatorIP'));
						}
					});
	}
}

function getOneByNameFunc(req, res, next){
        if(isEmpty(req.params.name)){
                return next(findError("GROUP.1.2.1"));
        }
        else {
                var myObject = new objectDB({ name : req.params.name });
                myObject.findElmtByName(function (err, elmt){
                                                if (err){
                                                        return next(err);
                                                }
                                                else if(isEmpty(elmt)){
                                                        return next(findError("GROUP.1.2.2"));
                                                }
						else{
	                                                res.status(200).json(_.omit(elmt.toJSON(), 'creatorIP', 'updatorIP'));
						}
                                        });
        }
}

function searchByLoginFunc(req, res, next){
	if(isEmpty(req.params.login)){
		return next(findError("GROUP.1.3.1"));
	}
	else {
		new objectDB().getAllWithLogin(req.params.login, function (err, elmt){
						if (err){
							return next(err);
						}
						else{
							var elmt2 = [];
							for(var i=0; i<elmt.length; i++){
								elmt2.push(_.omit(elmt[i].toJSON(), 'creatorIP', 'updatorIP'))
							}
							res.status(200).json(elmt2);
						}
					});
	}
}

function postFunc(req, res, next){
	if(isEmpty(req.body.name)){
		return next(findError("GROUP.1.4.1"));
	}
	else if(isEmpty(req.body.type)){
		return next(findError("GROUP.1.4.2"));
	}
	else {
		var myObject = new objectDB({ 	name           : req.body.name,
                                                type           : req.body.type,
						description    : req.body.description,
						mail           : req.body.mail,
						logo           : req.body.logo,
						picture        : req.body.picture,
						members        : [],
						responsibles   : [],
						created        : new Date(),
						creator        : req.body.creator,
						creatorIP      : req.connection.remoteAddress,
						creatorService : req.query.token.source_service,
						updator        : null,
						updated        : null,
						updatorIP      : null,
						updatorService : null});
		myObject.findElmtByName(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(!isEmpty(elmt)){
							return next(findError("GROUP.1.4.3"));
						}
						else{
							myObject.addElmt(function (err2, elmt2){
								if (err2){
									return next(err2);
								}
								else if(isEmpty(elmt2)){
									return next(findError("GROUP.1.4.4"));
								}
								else{
									cache.postNotify();
						                        res.status(200).json(_.omit(elmt2.toJSON(), 'creatorIP', 'updatorIP'));
								}
							});
						}
					});
	}
}

function putFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("GROUP.1.5.1"));
	}
	else if(isEmpty(req.body.name)){
		return next(findError("GROUP.1.5.2"));
	}
	else if(isEmpty(req.body.type)){
		return next(findError("GROUP.1.5.3"));
	}
	else {
		var myObject = new objectDB({	_id            : req.params.id,
						name           : req.body.name,
                                                type           : req.body.type,
						description    : req.body.description,
						mail           : req.body.mail,
						logo           : req.body.logo,
						picture        : req.body.picture,
						updator        : req.body.updator,
						updated        : new Date(),
						updatorIP      : req.connection.remoteAddress,
						updatorService : req.query.token.source_service});
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("GROUP.1.5.4"));
						}
						else{
							myObject.findElmtByName(function (err2, elmt2){
											if (err2){
												return next(err2);
											}
											else if(!isEmpty(elmt2) && elmt2._id.toString()!=myObject._id.toString()){
												return next(findError("GROUP.1.5.5"));
											}
											else{
												myObject.findElmtByMail(function (err3, elmt3){
													if (err3){
														return next(err3);
													}
													else if(!isEmpty(elmt3) && elmt3._id.toString()!=myObject._id.toString()){
														return next(findError("GROUP.1.5.6"));
													}
													else{
														myObject.updateElmtByID(function (err4){
																		if (err4){
																			return next(err4);
																		}
																		else{
																			myObject.findElmtByID(function (err5, elmt4){
																							if (err5){
																								return next(err5);
																							}
																							else if(isEmpty(elmt4)){
																								return next(findError("GROUP.1.5.7"));
																							}
																							else{
																								cache.putNotify();
																								res.status(200).json(_.omit(elmt4.toJSON(), 'creatorIP', 'updatorIP'));
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

function deleteFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("GROUP.1.6.1"));
	}
	else {
		var myObject = new objectDB({ _id : req.params.id });
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("GROUP.1.6.2"));
						}
						else{
							myObject.removeElmtByID(function (err2){
											if (err2){ 
												return next(err2);
											}
											cache.deleteNotify();
						                                        res.status(200).json(_.omit(elmt.toJSON(), 'creatorIP', 'updatorIP'));
							});
						}
					});
	}
}

function addMemberFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("GROUP.1.7.1"));
	}
	else if(isEmpty(req.body.login)){
		return next(findError("GROUP.1.7.2"));
	}
	else{
		var myObject = new objectDB({	_id            : req.params.id,
						members        : [],
						updator        : req.body.updator,
						updated        : new Date(),
						updatorIP      : req.connection.remoteAddress,
						updatorService : req.query.token.source_service});
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("GROUP.1.7.3"));
						}
						else{
							var member = req.body.login.toLowerCase();
							if(_.contains(elmt.members, member)){
								return next(findError("GROUP.1.7.4"));
							}
							else{
								elmt.members.push(member);
								elmt.members.sort();
								myObject.members = elmt.members;
								myObject.updateMembers(function (err2){
											if (err2){
												return next(err2);
											}
											else{
												myObject.findElmtByID(function (err3, elmt3){
																if (err3){
																	return next(err3);
																}
																else if(isEmpty(elmt3)){
																	return next(findError("GROUP.1.7.5"));
																}
																else{
																	cache.putNotify();
																	res.status(200).json(_.omit(elmt3.toJSON(), 'creatorIP', 'updatorIP'));
																}
															});
											}
								});
							}
						}
					});

	}
}


function addResponsibleFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("GROUP.1.8.1"));
	}
	else if(isEmpty(req.body.login)){
		return next(findError("GROUP.1.8.2"));
	}
	else if(isEmpty(req.body.responsability)){
		return next(findError("GROUP.1.8.3"));
	}
	else{
		var myObject = new objectDB({	_id            : req.params.id,
						responsibles   : [],
						updator        : req.body.updator,
						updated        : new Date(),
						updatorIP      : req.connection.remoteAddress,
						updatorService : req.query.token.source_service});
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("GROUP.1.8.4"));
						}
						else{
							var login	   = req.body.login.toLowerCase();
							var responsability = req.body.responsability.toLowerCase();

							//Determine if the login/responsability is in the group
							var flag = false;
							var auxi = null;
							for(var i=0; i<elmt.responsibles.length; i++){
								auxi = elmt.responsibles[i];
								if(auxi.login === login &&
								   auxi.responsability === responsability){
									flag = true;
									break;
								}
							}

							if(flag){
								return next(findError("GROUP.1.8.5"));
							}
							else{
								elmt.responsibles.push({ login : login, 
											 responsability : responsability });
								elmt.responsibles.sort();
								myObject.responsibles = elmt.responsibles;
								myObject.updateResponsibles(function (err2){
											if (err2){
												return next(err2);
											}
											else{
												myObject.findElmtByID(function (err3, elmt3){
																if (err3){
																	return next(err3);
																}
																else if(isEmpty(elmt3)){
																	return next(findError("GROUP.1.8.6"));
																}
																else{
																	cache.putNotify();
																	res.status(200).json(_.omit(elmt3.toJSON(), 'creatorIP', 'updatorIP'));
																}
															});
											}
								});
							}
						}
					});

	}
}

function deleteMemberFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("GROUP.1.9.1"));
	}
	else if(isEmpty(req.body.login)){
		return next(findError("GROUP.1.9.2"));
	}
	else{
		var myObject = new objectDB({	_id            : req.params.id,
						members        : [],
						updator        : req.body.updator,
						updated        : new Date(),
						updatorIP      : req.connection.remoteAddress,
						updatorService : req.query.token.source_service});
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("GROUP.1.9.3"));
						}
						else{
							var login = req.body.login.toLowerCase();

							if(!_.contains(elmt.members, login)){
								return next(findError("GROUP.1.9.4"));
							}
							else{
								elmt.members.remove(login);
								elmt.members.sort();
								myObject.members = elmt.members;
								myObject.updateMembers(function (err2){
											if (err2){
												return next(err2);
											}
											else{
												myObject.findElmtByID(function (err3, elmt3){
																if (err3){
																	return next(err3);
																}
																else if(isEmpty(elmt3)){
																	return next(findError("GROUP.1.9.5"));
																}
																else{
																	cache.putNotify();
																	res.status(200).json(_.omit(elmt3.toJSON(), 'creatorIP', 'updatorIP'));
																}
															});
											}
								});
							}
						}
					});

	}
}


function deleteResponsibleFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("GROUP.1.10.1"));
	}
	else if(isEmpty(req.body.login)){
		return next(findError("GROUP.1.10.2"));
	}
	else if(isEmpty(req.body.responsability)){
		return next(findError("GROUP.1.10.3"));
	}
	else{
		var myObject = new objectDB({	_id            : req.params.id,
						responsibles   : [],
						updator        : req.body.updator,
						updated        : new Date(),
						updatorIP      : req.connection.remoteAddress,
						updatorService : req.query.token.source_service});
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("GROUP.1.10.4"));
						}
						else{
							var login          = req.body.login.toLowerCase();
							var responsability = req.body.responsability.toLowerCase();

							//Determine if the login/responsability is in the group
							var resp = null;
							var auxi = null;
							for(var i=0; i<elmt.responsibles.length; i++){
								auxi = elmt.responsibles[i];
								if(auxi.login === login &&
								   auxi.responsability === responsability){
									resp = auxi;
									break;
								}
							}

							if(isEmpty(resp)){
								return next(findError("GROUP.1.10.5"));
							}
							else{
								elmt.responsibles = _.without(elmt.responsibles, resp);
								elmt.responsibles.sort();
								myObject.responsibles = elmt.responsibles;
								myObject.updateResponsibles(function (err2){
											if (err2){
												return next(err2);
											}
											else{
												myObject.findElmtByID(function (err3, elmt3){
																if (err3){
																	return next(err3);
																}
																else if(isEmpty(elmt3)){
																	return next(findError("GROUP.1.10.6"));
																}
																else{
																	cache.putNotify();
																	res.status(200).json(_.omit(elmt3.toJSON(), 'creatorIP', 'updatorIP'));
																}
															});
											}
								});
							}
						}
					});

	}
}


module.exports.group = {
	get            : getFunc,
	getOne         : getOneFunc,
        getOneByName   : getOneByNameFunc,
        searchByLogin  : searchByLoginFunc,
	post           : postFunc,
	put            : putFunc,
	delete         : deleteFunc,
	addMember      : addMemberFunc,
	addResponsible : addResponsibleFunc,
	delMember      : deleteMemberFunc,
	delResponsible : deleteResponsibleFunc,
};
