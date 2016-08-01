/* 
 * File 	: ./server/controllers/card.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage an cards stored in the Mongoose database
 * Version  	: 1.0.0
 */

var	_        = require('underscore'),
	cache	 = require('cache')('INFOS')(require('mongoose')),
	isEmpty	 = require('toolbox')('ISEMPTY'),
	objectDB = require('../models/card.js'),
	findError= require('../errors/referential.js');



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
		return next(findError("CARD.1.1.1"));
	}
	else {
		var myObject = new objectDB({ _id : req.params.id });
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("CARD.1.1.2"));
						}
						else{
	                                                res.status(200).json(_.omit(elmt.toJSON(), 'creatorIP', 'updatorIP'));
						}
					});
	}
}

function getOneCodeFunc(req, res, next){
	if(isEmpty(req.params.code)){
	        return next(findError("CARD.1.2.1"));
	}
	else {
	        var myObject = new objectDB({ code : req.params.code });
	        myObject.findElmtByCode(function (err, elmt){
	                                        if (err){
	                                                return next(err);
	                                        }
	                                        else if(isEmpty(elmt)){
	                                                return next(findError("CARD.1.2.2"));
	                                        }
						else{
	                                                res.status(200).json(_.omit(elmt.toJSON(), 'creatorIP', 'updatorIP'));
						}
	                                });
	}
}

function postFunc(req, res, next){
	if(isEmpty(req.body.owner)){
		return next(findError("CARD.1.3.1"));
	}
	else if(isEmpty(req.body.code)){
		return next(findError("CARD.1.3.2"));
	}
	else if(isEmpty(req.body.status)){
		return next(findError("CARD.1.3.3"));
	}
	else {
		var myObject = new objectDB({ 	owner          : req.body.owner,
						code           : req.body.code,
						status         : req.body.status,
						created        : new Date(),
						creator        : req.body.creator,
						creatorIP      : req.connection.remoteAddress,
						creatorService : req.query.token.source_service,
						updator        : null,
						updated        : null,
						updatorIP      : null,
						updatorService : null});
		myObject.findElmtByCode(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(!isEmpty(elmt)){
							return next(findError("CARD.1.3.4"));
						}
						else{
							myObject.addElmt(function (err2, elmt2){
										if (err2){
											return next(err2);
										}
										else if(isEmpty(elmt2)){
											return next(findError("CARD.1.3.5"));
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
		return next(findError("CARD.1.4.1"));
	}
	if(isEmpty(req.body.owner)){
		return next(findError("CARD.1.4.2"));
	}
	else if(isEmpty(req.body.code)){
		return next(findError("CARD.1.4.3"));
	}
	else if(isEmpty(req.body.status)){
		return next(findError("CARD.1.4.4"));
	}
	else {
		var myObject = new objectDB({	_id            : req.params.id,
						owner          : req.body.owner,
						code           : req.body.code,
						status         : req.body.status,
						updator        : req.body.updator,
						updated        : new Date(),
						updatorIP      : req.connection.remoteAddress,
						updatorService : req.query.token.source_service});
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("CARD.1.4.5"));
						}
						else{
							myObject.findElmtByCode(function (err2, elmt2){
											if (err2){
												return next(err2);
											}
											else if(!isEmpty(elmt2) && elmt2._id.toString()!=myObject._id.toString()){
												return next(findError("CARD.1.4.6"));
											}
											else{
												myObject.updateElmtByID(function (err3){
																if (err3){
																	return next(err3);
																}
																else{
																	myObject.findElmtByID(function (err4, elmt3){
																					if (err4){
																						return next(err4);
																					}
																					else if(isEmpty(elmt3)){
																						return next(findError("CARD.1.4.7"));
																					}
																					else{
																						cache.putNotify();
																						res.status(200).json(_.omit(elmt3.toJSON(), 'creatorIP', 'updatorIP'));
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
		return next(findError("CARD.1.5.1"));
	}
	else {
		var myObject = new objectDB({ _id : req.params.id });
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("CARD.1.5.2"));
						}
						else{
							myObject.removeElmtByID(function (err2){
											if (err2){ 
												return next(err2);
											}
											else{
												cache.deleteNotify();
								                                res.status(200).json(_.omit(elmt.toJSON(), 'creatorIP', 'updatorIP'));						
											}
						      });
						}
					});
	}
}


module.exports.card = {
	get        : getFunc,
	getOne     : getOneFunc,
	getOneCard : getOneCodeFunc,
	post       : postFunc,
	put        : putFunc,
	delete     : deleteFunc
};
