/* 
 * File 	: ./utils/floodprotection.js
 * Author(s)	: Zidmann
 * Function 	: Manage information about the connections for the brutus protection
 * Note		: mongoose        - refers to the mongoose module used to communicate with Mongo database
 *                collection_name - refers to the name of the collection used in MongoDB to store the connection information
 * Version  	: 1.1.0
 */

function dynamicExport(mongoose, collection_name){
	var	findError = require('./errors/referential.js'),
		isArray	  = require('toolbox')('ISARRAY'),
		isEmpty	  = require('toolbox')('ISEMPTY'),
		isString  = require('toolbox')('ISSTRING'),
		objectDB  = require('../models/floodprotection.js')(mongoose, collection_name),
		sha512    = require('hash.js/lib/hash/sha/512'),
		_         = require('underscore');


	function hashFunc(data){
		var hash=sha512().update("").digest('hex');
		if(!isEmpty(data)){
			if(isString(data)){
				hash=sha512().update(data).digest('hex');
			}
			else if(isArray(data)){
				for(var i=0; i<data.length; i++){
					var elmt="";
					if(isString(data[i])){
						elmt=data[i];
					}
					hash+=elmt;
					hash=sha512().update(hash).digest('hex');
				}
			}
		}
		return hash;
	}

	function reportAndCheckFunc(data, conf, callback){
		if(isEmpty(conf) || 
		   isEmpty(conf.free_retries) || isEmpty(conf.burst_retries) || 
		   isEmpty(conf.lifetime) || 
		   isEmpty(conf.waitFunction) || isEmpty(conf.minWait) || isEmpty(conf.maxWait) || 
		   conf.disabled===true){
			return callback();
		}
		else if(isEmpty(data.hash)){
			return callback(findError("FLOODPROTECTION.1.1.1"));
		}
		else if(!isString(data.hash)){
			return callback(findError("FLOODPROTECTION.1.1.2"));
		}
		else {
			var myObject = new objectDB({ hash : data.hash });
			myObject.removeOldElmts(function(err){
				if(!isEmpty(err)){
					return callback(findError("FLOODPROTECTION.1.1.3"));
				}
				else{
					myObject.findElmtByHash(function(err2, elmt2){
						if(!isEmpty(err2)){
							return callback(findError("FLOODPROTECTION.1.1.4"));
						}

						auxi = function(elmt_auxi){
							if(elmt_auxi.state){
								return callback(findError("FLOODPROTECTION.1.1.5"));
							}
							else if(elmt_auxi.count>=conf.burst_retries){
								elmt_auxi.end_time=new Date((new Date()).getTime()+conf.lifetime);
								elmt_auxi.lock(function(err3){
									if(!isEmpty(err3)){
										return callback(err3);
									}
									else{
										return callback(findError("FLOODPROTECTION.1.1.6"));
									}
								});
							}
							else{
								elmt_auxi.end_time=new Date((new Date()).getTime()+conf.lifetime);
								elmt_auxi.increment(function(err5, elmt5){
									if(!isEmpty(err5)){
										return callback(findError("FLOODPROTECTION.1.1.7"));
									}
									else if(isEmpty(elmt5)){
										return callback(findError("FLOODPROTECTION.1.1.8"));
									}
									else{
										var timeout=0;
										if(elmt_auxi.count>=conf.free_retries){
											timeout=conf.waitFunction(elmt_auxi.count);
											if(timeout>conf.maxWait){
												timeout=conf.maxWait;
											}
											if(timeout<conf.minWait){
												timeout=conf.minWait;
											}
										}

										setTimeout(function(err6){
											if(!isEmpty(err6)){
												return callback(findError("FLOODPROTECTION.1.1.9"));
											}
											else{
												return callback();
											}
										}, timeout);
									}
								});
							}
						}

						if(isEmpty(elmt2)){
							myObject.start_time=new Date();
							myObject.end_time=new Date(myObject.start_time.getTime()+conf.lifetime);

							myObject.addElmt(function(err7, elmt7){
								if(!isEmpty(err7)){
									return callback(findError("FLOODPROTECTION.1.1.10"));
								}
								else if(isEmpty(elmt7)){
									return callback(findError("FLOODPROTECTION.1.1.11"));
								}
								else{
									auxi(elmt7);
								}
							});
						}
						else{
							auxi(elmt2);
						}
					});
				}
			});
		}
	}

	function resetFunc(data, callback){
		if(isEmpty(data.hash)){
			return callback(findError("FLOODPROTECTION.1.2.1"));
		}
		else if(!isString(data.hash)){
			return callback(findError("FLOODPROTECTION.1.2.2"));
		}
		else{
			return callback();

			var myObject = new objectDB({ hash : data.hash });
			myObject.reset(function(err){
				if(!isEmpty(err)){
					return callback(findError("FLOODPROTECTION.1.2.3"));
				}
				else{
					return callback();
				}
			});
		}
	}

	return {
		hash           : hashFunc,
		reportAndCheck : reportAndCheckFunc,
		reset          : resetFunc
	};
}


module.exports = dynamicExport;
