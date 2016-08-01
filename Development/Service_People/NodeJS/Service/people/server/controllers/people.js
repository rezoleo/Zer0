/* 
 * File 	: ./server/controllers/people.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage people information stored in the Mongoose database
 * Version  	: 1.0.0
 */

var	moment	 = require('moment'),
	_	 = require('underscore'),
	_str	 = require('underscore.string'),
	cache	 = require('cache')('INFOS')(require('mongoose')),
	findError= require('../errors/referential.js'),
	isEmpty	 = require('toolbox')('ISEMPTY'),
	objectDB = require('../models/people.js');



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
								var elmt_bis = _.omit(elmt[i].toJSON(), 'creatorIP', 'updatorIP');

								if(!isEmpty(elmt_bis.birthdate)){
									if(moment(elmt_bis.birthdate).add(18, 'year').add(1, 'day').diff(moment())<0){
										elmt_bis.major = true;
									}
									else{
										elmt_bis.major = false;
									}
								}
								else if(isEmpty(elmt_bis.major)){
									elmt_bis.major = false;
								}

								elmt2.push(elmt_bis);
							}
		                                        res.status(200).json(elmt2);
						}
					});
	}
}

function getOneFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("PEOPLE.1.1.1"));
	}
	else {
		var myObject = new objectDB({ _id : req.params.id });
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("PEOPLE.1.1.2"));
						}
						else{
							var elmt_bis = _.omit(elmt.toJSON(), 'creatorIP', 'updatorIP');

							if(!isEmpty(elmt_bis.birthdate)){
								if(moment(elmt_bis.birthdate).add(18, 'year').add(1, 'day').diff(moment())<0){
									elmt_bis.major = true;
								}
								else{
									elmt_bis.major = false;
								}
							}
							else if(isEmpty(elmt_bis.major)){
								elmt_bis.major = false;
							}

	                                                res.status(200).json(elmt_bis);
						}
					});
	}
}

function getOneLoginFunc(req, res, next){
        if(isEmpty(req.params.login)){
                return next(findError("PEOPLE.1.2.1"));
        }
        else {
                var myObject = new objectDB({ login : req.params.login });
                myObject.findElmtByLogin(function (err, elmt){
                                                if (err){
                                                        return next(err);
                                                }
                                                else if(isEmpty(elmt)){
                                                        return next(findError("PEOPLE.1.2.2"));
                                                }
						else{
							var elmt_bis = _.omit(elmt.toJSON(), 'creatorIP', 'updatorIP');

							if(!isEmpty(elmt_bis.birthdate)){
								if(moment(elmt_bis.birthdate).add(18, 'year').add(1, 'day').diff(moment())<0){
									elmt_bis.major = true;
								}
								else{
									elmt_bis.major = false;
								}
							}
							else if(isEmpty(elmt_bis.major)){
								elmt_bis.major = false;
							}

	                                                res.status(200).json(elmt_bis);
						}
                                        });
        }
}

function getOneMailFunc(req, res, next){
        if(isEmpty(req.params.mail)){
                return next(findError("PEOPLE.1.3.1"));
        }
        else {
                var myObject = new objectDB({ mail : req.params.mail });
                myObject.findElmtByMail(function (err, elmt){
                                                if (err){
                                                        return next(err);
                                                }
                                                else if(isEmpty(elmt)){
                                                        return next(findError("PEOPLE.1.3.2"));
                                                }
						else{
							var elmt_bis = _.omit(elmt.toJSON(), 'creatorIP', 'updatorIP');

							if(!isEmpty(elmt_bis.birthdate)){
								if(moment(elmt_bis.birthdate).add(18, 'year').add(1, 'day').diff(moment())<0){
									elmt_bis.major = true;
								}
								else{
									elmt_bis.major = false;
								}
							}
							else if(isEmpty(elmt_bis.major)){
								elmt_bis.major = false;
							}

	                                                res.status(200).json(elmt_bis);
						}
                                        });
        }
}

function postFunc(req, res, next){
	if(isEmpty(req.body.login)){
		return next(findError("PEOPLE.1.4.1"));
	}
	else if(isEmpty(req.body.lastname)){
		return next(findError("PEOPLE.1.4.2"));
	}
	else if(isEmpty(req.body.firstname)){
		return next(findError("PEOPLE.1.4.3"));
	}
	else {
		var myObject = new objectDB({ 	login          : req.body.login,
						lastname       : req.body.lastname,
                                                firstname      : _str(req.body.firstname).trim().capitalize().value(),
						sex            : req.body.sex,
                                                birthdate      :(req.body.birthdate !== '') ? req.body.birthdate: null,
                                                major          : req.body.major,
                                                mail           : req.body.mail,
                                                tel            : req.body.tel,
						picture        : req.body.picture,
						tags           : [],
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
							return next(findError("PEOPLE.1.4.4"));
						}
						else{
							myObject.findElmtByMail(function (err2, elmt2){
		                                                                                if (err2){
		                                                                                        return next(err2);
		                                                                                }
		                                                                                else if(!isEmpty(elmt2)){
		                                                                                        return next(findError("PEOPLE.1.4.5"));
		                                                                                }
												else{
													myObject.addElmt(function (err3, elmt3){
														if (err3){
															return next(err3);
														}
														else if(isEmpty(elmt3)){
															return next(findError("PEOPLE.1.4.6"));
														}
														else{
															cache.postNotify();
												                        res.status(200).json(_.omit(elmt3.toJSON(), 'creatorIP', 'updatorIP'));
														}
													});
												}
										});
						}
					});
	}
}

function addTagFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("PEOPLE.1.5.1"));
	}
	else if(isEmpty(req.params.tag)){
		return next(findError("PEOPLE.1.5.2"));
	}
	else{
		var myObject = new objectDB({	_id            : req.params.id,
						tags           : [],
						updator        : req.body.updator,
						updated        : new Date(),
						updatorIP      : req.connection.remoteAddress,
						updatorService : req.query.token.source_service});
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("PEOPLE.1.5.3"));
						}
						else{
							var tag = req.params.tag.toUpperCase();
							if(_.contains(elmt.tags, tag)){
								return next(findError("PEOPLE.1.5.4"));
							}
							else{
								elmt.tags.push(tag);
								elmt.tags.sort();
								myObject.tags = elmt.tags;
								myObject.updateTags(function (err2){
											if (err2){
												return next(err2);
											}
											else{
												myObject.findElmtByID(function (err3, elmt3){
																if (err3){
																	return next(err3);
																}
																else if(isEmpty(elmt3)){
																	return next(findError("PEOPLE.1.5.5"));
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

function putFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("PEOPLE.1.6.1"));
	}
	else if(isEmpty(req.body.login)){
		return next(findError("PEOPLE.1.6.2"));
	}
	else if(isEmpty(req.body.lastname)){
		return next(findError("PEOPLE.1.6.3"));
	}
	else if(isEmpty(req.body.firstname)){
		return next(findError("PEOPLE.1.6.4"));
	}
	else {
		var myObject = new objectDB({	_id            : req.params.id,
						login          : req.body.login,
                                                lastname       : req.body.lastname,
                                                firstname      : _str(req.body.firstname).trim().capitalize().value(),
						sex            : req.body.sex,
                                                birthdate      :(req.body.birthdate !== '') ? req.body.birthdate: null,
                                                major          : req.body.major,
                                                mail           : req.body.mail,
                                                tel            : req.body.tel,
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
							return next(findError("PEOPLE.1.6.5"));
						}
						else{
							myObject.findElmtByLogin(function (err2, elmt2){
											if (err2){
												return next(err2);
											}
											else if(!isEmpty(elmt2) && elmt2._id.toString()!=myObject._id.toString()){
												return next(findError("PEOPLE.1.6.6"));
											}
											else{
												myObject.findElmtByMail(function (err3, elmt3){
					                                                                if (err3){
					                                                                        return next(err3);
					                                                                }
				        	                                                        else if(!isEmpty(elmt3) && elmt3._id.toString()!=myObject._id.toString()){
				                	                                                        return next(findError("PEOPLE.1.6.7"));
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
																								return next(findError("PEOPLE.1.6.8"));
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

function deleteTagFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("PEOPLE.1.7.1"));
	}
	else if(isEmpty(req.params.tag)){
		return next(findError("PEOPLE.1.7.2"));
	}
	else{
		var myObject = new objectDB({	_id            : req.params.id,
						tags           : [],
						updator        : req.body.updator,
						updated        : new Date(),
						updatorIP      : req.connection.remoteAddress,
						updatorService : req.query.token.source_service});
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("PEOPLE.1.7.3"));
						}
						else{
							var tag = req.params.tag.toUpperCase();

							if(!_.contains(elmt.tags, tag)){
								return next(findError("PEOPLE.1.7.4"));
							}
							else{
								elmt.tags.remove(tag);
								elmt.tags.sort();
								myObject.tags = elmt.tags;
								myObject.updateTags(function (err2){
											if (err2){
												return next(err2);
											}
											else{
												myObject.findElmtByID(function (err3, elmt3){
																if (err3){
																	return next(err3);
																}
																else if(isEmpty(elmt3)){
																	return next(findError("PEOPLE.1.7.5"));
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

function deleteFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("PEOPLE.1.8.1"));
	}
	else {
		var myObject = new objectDB({ _id : req.params.id });
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("PEOPLE.1.8.2"));
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


module.exports.people = {
	addTag      : addTagFunc,
	delTag      : deleteTagFunc,
	get         : getFunc,
	getOne      : getOneFunc,
        getOneLogin : getOneLoginFunc,
        getOneMail  : getOneMailFunc,
	post        : postFunc,
	put         : putFunc,
	delete      : deleteFunc
};
