/* 
 * File 	: ./server/controllers/alert.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage an alerts stored in the Mongoose database
 * Version  	: 1.0.0
 */

var	_         = require('underscore'),
	cache	  = require('cache')('INFOS')(require('mongoose')),
	isEmpty	  = require('toolbox')('ISEMPTY'),
	objectDB  = require('../models/alert.js'),
	findError = require('../errors/referential.js');



function getFunc(req, res, next){
	if(req.query.action==="get-cache-infos"){
		cache.getCacheInfo(req, res, next);
	}
	else {
		if(!isEmpty(req.query.minimumlevel) && !_.contains(['INFO', 'WARNING', 'ERROR', 'CRITICAL'], req.query.minimumlevel.toUpperCase())){
			return next(findError("ALERT.1.1.1"));
		}
		else{
			var auxi=function (err, elmt){
				if (err){
					return next(err);
				}
				else{
					var flag=false;
					if(!isEmpty(req.query.month)){
						flag=true;
					}

					var elmt2 = [];
					var json    = null;
					var created = null;
					for(var i=0; i<elmt.length; i++){
						json=elmt[i].toJSON()
						if(flag){
							if(!isEmpty(json) && !isEmpty(json.created)){
								created=json.created
								if((created.getFullYear())+'_'+(created.getMonth()+1)!=req.query.month){
									continue;
								}
							}
						}
						elmt2.push(_.omit(json, 'creatorIP'));
					}

	                                res.status(200).json(elmt2);
				}
			}

			if(isEmpty(req.query.minimumlevel)){
				new objectDB().getAll(auxi);				
			}
			else{
				var levels=[];
				if(req.query.minimumlevel.toUpperCase()==='INFO'){
					levels=['CRITICAL', 'ERROR', 'WARNING', 'INFO'];
				}
				else if(req.query.minimumlevel.toUpperCase()==='WARNING'){
					levels=['CRITICAL', 'ERROR', 'WARNING'];
				}
				else if(req.query.minimumlevel.toUpperCase()==='ERROR'){
					levels=['CRITICAL', 'ERROR'];
				}
				else if(req.query.minimumlevel.toUpperCase()==='CRITICAL'){
					levels=['CRITICAL'];
				}

				var myObject = new objectDB({});
				myObject.levels=levels;

				myObject.getAllByLevelList(auxi);				
			}
		}
	}
}

function getOneFunc(req, res, next){
	if(isEmpty(req.params.id)){
		return next(findError("ALERT.1.2.1"));
	}
	else {
		var myObject = new objectDB({ _id : req.params.id });
		myObject.findElmtByID(function (err, elmt){
						if (err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("ALERT.1.2.2"));
						}
						else{
	                                                res.status(200).json(_.omit(elmt.toJSON(), 'creatorIP'));
						}
					});
	}
}

function postFunc(req, res, next){
	if(isEmpty(req.body.message)){
		return next(findError("ALERT.1.3.1"));
	}
	else if(isEmpty(req.body.level)){
		return next(findError("ALERT.1.3.2"));
	}
	else {
		var myObject = new objectDB({ 	message        : req.body.message,
						level          : req.body.level,
						created        : new Date(),
						creatorIP      : req.connection.remoteAddress,
						creatorService : req.query.token.source_service});
		myObject.addElmt(function (err, elmt){
					if (err){
						return next(err);
					}
					else if(isEmpty(elmt)){
						return next(findError("ALERT.1.3.3"));
					}
					else{
						cache.postNotify();
		                                res.status(200).json(_.omit(elmt.toJSON(), 'creatorIP'));
					}
				  });
	}
}


module.exports.alert = {
	get    : getFunc,
	getOne : getOneFunc,
	post   : postFunc,
};
