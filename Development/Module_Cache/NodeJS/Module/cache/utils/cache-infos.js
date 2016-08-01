/* 
 * File 	: ./utils/cache-infos.js
 * Author(s)	: Zidmann
 * Function 	: Functions to interact with cache information
 * Note		: mongoose        - refers to the mongoose module used to communicate with Mongo database
 *                collection_name - refers to the name of the collection used in MongoDB to store the cache information
 * Version  	: 1.0.0
 */

var data=[];


function dynamicExport(mongoose, collection_name){
	var	Err	 = require('toolbox')('ERR'),
		isEmpty	 = require('toolbox')('ISEMPTY'),
		objectDB = require('../models/cache-infos.js')(mongoose, collection_name),
		_ 	 = require('underscore');

	function getCacheInfoFunc(req, res, next){
		var myObject = new objectDB({});
		myObject.getOne(function (err, elmt){
					if(err){
						return next(err);
					}
					else if(isEmpty(elmt)){
						myObject.addElmt(function (err2, elmt2){
							if(err2){
								return next(err2);
							}
							else{
								res.status(200).json({lastUpdate       : elmt2.lastUpdate,
										      lastPostUpdate   : elmt2.lastPostUpdate,
										      lastPutUpdate    : elmt2.lastPutUpdate,
										      lastDeleteUpdate : elmt2.lastDeleteUpdate});	
							}
						});
					}
					else {
						res.status(200).json({lastUpdate       : elmt.lastUpdate,
								      lastPostUpdate   : elmt.lastPostUpdate,
								      lastPutUpdate    : elmt.lastPutUpdate,
								      lastDeleteUpdate : elmt.lastDeleteUpdate});	
					}
				});
	}

	function notifyAuxi(date, next, callback){
		var myObject = new objectDB({});
		myObject.getOne(function (err, elmt){
					if(err){
						return next(err);
					}
					else if(isEmpty(elmt)){
						myObject.addElmt(function (err2, elmt2){
							if(err2){
								return next(err2);
							}
							else {
								callback(elmt2);
							}
						});
					}
					else {
						callback(elmt);
					}
				});
	}

	function postNotifyFunc(req, res, next){
		var date = new Date();
		notifyAuxi(date, next, function (elmt){
					elmt.lastPostUpdate = date;
					elmt.lastUpdate     = date;
					elmt.updateElmt(function (err){
								if(err){
									return next(err);
								}
							});
			});
	}

	function putNotifyFunc(req, res, next){
		var date = new Date();
		notifyAuxi(date, next, function (elmt){
					elmt.lastPutUpdate = date;
					elmt.lastUpdate    = date;
					elmt.updateElmt(function (err){
								if(err){
									return next(err);
								}
							});
			});
	}

	function deleteNotifyFunc(req, res, next){
		var date = new Date();
		notifyAuxi(date, next, function (elmt){
					elmt.lastDeleteUpdate = date;
					elmt.lastUpdate       = date;
					elmt.updateElmt(function (err){
								if(err){
									return next(err);
								}
							});
			});
	}
	
	return {
		getCacheInfo : getCacheInfoFunc,
		postNotify   : postNotifyFunc,
		putNotify    : putNotifyFunc,
		deleteNotify : deleteNotifyFunc
	};
}


module.exports = dynamicExport;
