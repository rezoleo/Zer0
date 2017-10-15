/* 
 * File 	: ./server/controllers/floodtest.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to test test flood protection module
 * Version  	: 1.0.0
 */


var floodDB = require('../models/flood.js');

function sayhelloFunc(req, res, next){
	res.send({message : 'hello'});
}

function resetFunc(req, res, next){
	floodDB.removeAll(function (err){
		if (err){
			return next(err);
		}
    		res.send();
	});
}

module.exports = {
	sayhello : sayhelloFunc,
	reset    : resetFunc
};
