/* 
 * File 	: ./utils/floodprotection.js
 * Author(s)	: Zidmann
 * Function 	: Manage information about the connections for the brutus protection
 * Note		: mongoose        - refers to the mongoose module used to communicate with Mongo database
 *                collection_name - refers to the name of the collection used in MongoDB to store the connection information
 * Version  	: 1.0.0
 */

function dynamicExport(mongoose, collection_name){
	var	findError= require('./errors/referential.js'),
		isEmpty	 = require('toolbox')('ISEMPTY'),
		isString = require('toolbox')('ISSTRING'),
		objectDB = require('../models/floodprotection.js')(mongoose, collection_name),
		_ 	 = require('underscore');


	function checkFunc(data, conf, callback){
		if(isEmpty(data.hash)){
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
							else if(elmt_auxi.count>=conf.burst_limit){
								elmt_auxi.end_time=new Date()+conf.ban_time;
								elmt_auxi.lock(function(err3, elmt3){
									if(!isEmpty(err3)){
										return callback(err3);
									}
									else{
										return callback(findError("FLOODPROTECTION.1.1.6"));
									}
								});
							}
							else{
								elmt_auxi.increment(function(err4, elmt4){
									if(!isEmpty(err4)){
										return callback(findError("FLOODPROTECTION.1.1.7"));
									}
									else if(isEmpty(elmt4)){
										return callback(findError("FLOODPROTECTION.1.1.8"));
									}
									else{
										callback();
									}
								});
							}
						}

						if(isEmpty(elmt2)){
							myObject.start_time=new Date();
							myObject.end_time=myObject.start_time+conf.normal_period;
							myObject.addElmt(function(err5, elmt5){
								if(!isEmpty(err5)){
									return callback(findError("FLOODPROTECTION.1.1.9"));
								}
								else if(isEmpty(elmt5)){
									return callback(findError("FLOODPROTECTION.1.1.10"));
								}
								else{
									auxi(elmt5);
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


	return {
		check : checkFunc
	};
}


module.exports = dynamicExport;
