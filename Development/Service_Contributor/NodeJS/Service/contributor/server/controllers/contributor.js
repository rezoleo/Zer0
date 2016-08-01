/* 
 * File 	: ./server/controllers/contributor.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage contributors stored in the Mongoose database
 * Version  	: 1.0.0
 */

var	_        = require('underscore'),
	cache	 = require('cache')('INFOS')(require('mongoose')),
	isEmpty	 = require('toolbox')('ISEMPTY'),
	objectDB = require('../models/contributor.js'),
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
		return next(findError("CONTRIBUTOR.1.1.1"));
	}
	else {
		var myObject = new objectDB({ _id : req.params.id });
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("CONTRIBUTOR.1.1.2"));
						}
						else{
	                                                res.status(200).json(_.omit(elmt.toJSON(), 'creatorIP', 'updatorIP'));
						}
					});
	}
}

function getOneLoginFunc(req, res, next){
	if(isEmpty(req.params.login)){
	        return next(findError("CONTRIBUTOR.1.2.1"));
	}
	else if(req.query.action==="check"){
        var myObject = new objectDB({ login : req.params.login });
        myObject.countElmtByLogin(function (err, count){
                                        if (err){
                                                return next(err);
                                        }
                                        else if(isEmpty(count)){
                                                return next(findError("CONTRIBUTOR.1.2.2"));
                                        }
										else{
											res.status(200).json({login : req.params.login, isRegistered : (count>0)});
										}
		});
	}
	else {
        var myObject = new objectDB({ login : req.params.login });
        myObject.findElmtByLogin(function (err, elmt){
                                        if (err){
                                                return next(err);
                                        }
                                        else if(isEmpty(elmt)){
                                                return next(findError("CONTRIBUTOR.1.2.3"));
                                        }
										else{
											res.status(200).json(_.omit(elmt.toJSON(), 'creatorIP', 'updatorIP'));
										}
        });
	}
}

function postFunc(req, res, next){
	if(isEmpty(req.body.login)){
		return next(findError("CONTRIBUTOR.1.3.1"));
	}
	else {
		var myObject = new objectDB({ 	login          : req.body.login,
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
							return next(findError("CONTRIBUTOR.1.3.2"));
						}
						else{
							myObject.addElmt(function (err2, elmt2){
										if (err2){
											return next(err2);
										}
										else if(isEmpty(elmt2)){
											return next(findError("CONTRIBUTOR.1.3.3"));
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

function deleteFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("CONTRIBUTOR.1.4.1"));
	}
	else {
		var myObject = new objectDB({ _id : req.params.id });
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("CONTRIBUTOR.1.4.2"));
						}
						else{
							myObject.removeElmtByID(function (err2){
											if(err2){
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


module.exports.contributor = {
	get         : getFunc,
	getOne      : getOneFunc,
	getOneLogin : getOneLoginFunc,
	post        : postFunc,
	delete      : deleteFunc
};
