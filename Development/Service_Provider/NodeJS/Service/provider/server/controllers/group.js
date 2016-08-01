/* 
 * File 	: ./server/controllers/group.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage groups stored in the Group service
 * Version  	: 1.0.0
 */

var	_            = require('underscore'),
	isEmpty	     = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	ws_client    = require('../models/ws_group.js');


function getFunc(req, res, next){
	if(req.query.action=="get-cache-infos"){
		ws_client.getCacheInfo(function(err, elmt){
					if(err){
						return next(err);
					}
					else{
						res.status(200).json(_.omit(elmt, '__v'));
					}
			      });
	}
	else {
		ws_client.get(function(err, elmt){
					if(err){
						return next(err);
					}
					else{
						var elmt2 = [];
						for(var i=0; i<elmt.length; i++){
							elmt2.push(_.omit(elmt[i], '__v', 'creator', 'created', 'creatorIP', 'creatorService', 'updator', 'updated', 'updatorIP', 'updatorService'));
						}
	                                        res.status(200).json(elmt2);
					}
			      });
	}
}

function getOneFunc(req, res, next){
	ws_client.getOne({ id : req.params.id },
				 function(err, elmt){
					if(err){
						return next(err);
					}
					else{
						res.status(200).json(_.omit(elmt, '__v', 'creator', 'created', 'creatorIP', 'creatorService', 'updator', 'updated', 'updatorIP', 'updatorService'));
					}
			  	 });
}

function getOneByNameFunc(req, res, next){
	ws_client.getOneByName({ name : req.params.name },
			     function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(_.omit(elmt, '__v', 'creator', 'created', 'creatorIP', 'creatorService', 'updator', 'updated', 'updatorIP', 'updatorService'));
				}
		  	     });
}

function searchByLoginFunc(req, res, next){
	ws_client.searchByLogin({ login : req.params.login },
			     function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					var elmt2 = [];
					for(var i=0; i<elmt.length; i++){
						elmt2.push(_.omit(elmt[i], '__v', 'creator', 'created', 'creatorIP', 'creatorService', 'updator', 'updated', 'updatorIP', 'updatorService'));
					}
                                        res.status(200).json(elmt2);
				}
		      });
}


module.exports.group = {
	get           : getFunc,
	getOne        : getOneFunc,
	getOneByName  : getOneByNameFunc,
	searchByLogin : searchByLoginFunc
};
