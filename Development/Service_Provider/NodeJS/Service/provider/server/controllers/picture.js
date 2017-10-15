/* 
 * File 	: ./server/controllers/file.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage files stored in the Picture service
 * Version  	: 1.0.1
 */

var	_            = require('underscore'),
    	isArray	     = require('toolbox')('ISARRAY'),
    	isEmpty	     = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	findError    = require('../errors/referential.js'),
	ws_client    = require('../models/ws_picture.js');


function getFunc(req, res, next){
	if(!isEmpty(req.query.token) && 
		(isEmpty(req.params.dir) ||
		 isEmpty(req.query.token.extra) ||
		 isEmpty(req.query.token.extra.read) ||
		 !isArray(req.query.token.extra.read) ||
		 !_.contains(req.query.token.extra.read, req.params.dir))){
		return next(findError("PROVIDER.1.1.1"));
	}
	else if(req.query.action==="get-cache-infos"){
		ws_client.getInfos({ dir : req.params.dir, file : req.params.file },
			function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(_.omit(elmt, '__v', 'creator', 'created', 'creatorIP', 'creatorService', 'updator', 'updated', 'updatorIP', 'updatorService'));
				}
			});
	}
	else{
		ws_client.get({ dir : req.params.dir, file : req.params.file }, res,
			function(err2){
				return next(err2);
			});
	}
}


module.exports.picture = {
	get    : getFunc
};
