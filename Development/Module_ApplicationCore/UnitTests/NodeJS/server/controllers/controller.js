/* 
 * File 	: ./server/controllers/controller.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to test communication between an application and a service
 * Version  	: 1.0.0
 */

var isEmpty = require('toolbox')('ISEMPTY');

function dynamicExport(security){
	var ws_client = require('../models/ws_client.js')(security);

	function getFunc(req, res, next){
		ws_client.get(function (err, response){
					if (err){
						return next(err);
					}
					else if(isEmpty(response)){
						return next(err);
					}
					else{
						res.status(200).json(response);
					}
			});
	}

	function getOneFunc(req, res, next){
		ws_client.getOne(req.params.id,
				 function (err, response){
					if (err){
						return next(err);
					}
					else if(isEmpty(response)){
						return next(err);
					}
					else{
						res.status(200).json(response);
					}
			});
	}

	function postFunc(req, res, next){
		ws_client.post(function (err, response){
					if (err){
						return next(err);
					}
					else if(isEmpty(response)){
						return next(err);
					}
					else{
						res.status(200).json(response);
					}
			});
	}

	function putFunc(req, res, next){
		ws_client.put(function (err, response){
					if (err){
						return next(err);
					}
					else{
						res.status(200).json();
					}
			});
	}

	function deleteFunc(req, res, next){
		ws_client.delete(function (err, response){
					if (err){
						return next(err);
					}
					else{
						res.status(200).json();
					}
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
