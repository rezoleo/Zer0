/* 
 * File 	: ./controllers/notification.js
 * Author(s)	: Zidmann
 * Function 	: Functions to interact with notification information
 * Note		: mongoose        - refers to the mongoose module used to communicate with Mongo database
 *                collection_name - refers to the name of the collection used in MongoDB to store the notification information
 * Version  	: 1.0.0
 */

var data=[];


function dynamicExport(mongoose, collection_name){
	var	findError    = require('../errors/referential.js'),
		isEmpty      = require('toolbox')('ISEMPTY'),
		session_info = require('toolbox')('SESSION_INFO'),
		objectDB     = require('../models/notification.js')(mongoose, collection_name);

	data[collection_name] = {
		message	: null,
		level	: null,
		flag 	: false
	}

	function getOneFunc(req, res, next){
		if(!data[collection_name].flag){
			var myObject = new objectDB({});
			myObject.getOne(function (err, elmt){
						if(err){
							return next(err);
						}
						else{
							data[collection_name].message = (!isEmpty(elmt.message)) ? elmt.message : null;
							data[collection_name].level   = (!isEmpty(elmt.level))   ? elmt.level   : null;

							data[collection_name].flag = true;
						}
					});
		}
		res.status(200).json({ message : data[collection_name].message,
				       level   : data[collection_name].level });
	}

	function writeFunc(req, res, next){
		if(isEmpty(req.body.message)){
			return next(findError("CHECKER.6.1.1"));
		}
		else if(isEmpty(req.body.level)){
			return next(findError("CHECKER.6.1.2"));
		}
		else{
			var myObject = new objectDB({ message : req.body.message,
						      level   : req.body.level,
						      updator : session_info.getLogin(req) });
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
									data[collection_name].message = (!isEmpty(elmt2.message)) ? elmt2.message : null;
									data[collection_name].level   = (!isEmpty(elmt2.level))   ? elmt2.level   : null;

									data[collection_name].flag = true;
									getOneFunc(req, res, next);
								}
							});
						}
						else {
							myObject.updateElmt(function (err3, elmt3){
								if(err3){
									return next(err3);
								}
								else{
									data[collection_name].message = (!isEmpty(elmt3.message)) ? elmt3.message : null;
									data[collection_name].level   = (!isEmpty(elmt3.level))   ? elmt3.level   : null;

									data[collection_name].flag = true;
									getOneFunc(req, res, next);
								}
							});
						}
					});
		}
	}

	function removeFunc(req, res, next){
		var myObject = new objectDB({});
		myObject.removeElmt(function(err){
			if(err){
				next(err);
			}
			else{
				data[collection_name].message = null;
				data[collection_name].level   = null;

				getOneFunc(req, res, next);
			}
		});
	}

	return {
		getOne : getOneFunc,
		write  : writeFunc,
		remove : removeFunc
	};
}


module.exports.notification = dynamicExport;
