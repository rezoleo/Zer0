/* 
 * File 	: ./server/controllers/objectDB.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to test communication with or without token
 * Version  	: 1.0.0
 */

function dynamicExport(collection_name){
	var objectDB = require('../models/objectDB.js')(collection_name);

	function getFunc(req, res, next){
		new objectDB().getAll(function (err, elmt){
					if (err){ 
						return next(err);
					}
			    		res.send(elmt);
				});
	}

	function getOneFunc(req, res, next){
		new objectDB().findElmtByID(req.params.id, function (err, elmt){
					if (err){ 
						return next(err);
					}
			    		res.send(elmt);
				});
	}

	function postFunc(req, res, next){
		var myObject = new objectDB({ 	field2 : 'field2', 
						field3 :  'YES', 
						field4 : 'A',
						field5 : new Date(),
						field6 : true,
						field7 : {field8 : 0, field9 : 0}});
		myObject.saveElmt(function (err, elmt){
					if (err){ 
						return next(err);
					}
			    		res.send(elmt);
				});
	}

	function putFunc(req, res, next){
		new objectDB().updateAll(function (err){
					if (err){ 
						return next(err);
					}
			    		res.send();
				});
	}

	function deleteFunc(req, res, next){
		new objectDB().removeAll(function (err){
					if (err){
						return next(err);
					}
			    		res.send();
				});
	}


	return{
		get    : getFunc,
		getOne : getOneFunc,
		post   : postFunc,
		put    : putFunc,
		delete : deleteFunc
	};
}


module.exports = dynamicExport;
