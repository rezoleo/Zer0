/* 
 * File 	: ./server/controllers/card.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage cards stored in the Card service
 * Version  	: 1.0.0
 */

var	_            = require('underscore'),
	isEmpty	     = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	ws_client    = require('../models/ws_card.js');


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
							// Remove owner information for inactive cards
							if(elmt[i].status==="ON"){
								elmt2.push(_.omit(elmt[i], '__v', 'creator', 'created', 'creatorIP', 'creatorService', 'updator', 'updated', 'updatorIP', 'updatorService'));
							}
							else{
								elmt2.push(_.omit(elmt[i], '__v', 'owner', 'creator', 'created', 'creatorIP', 'creatorService', 'updator', 'updated', 'updatorIP', 'updatorService'));
							}
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
					// Remove owner information if the card is not active
					else if(elmt.status==="ON"){
						res.status(200).json(_.omit(elmt, '__v', 'creator', 'created', 'creatorIP', 'creatorService', 'updator', 'updated', 'updatorIP', 'updatorService'));
					}
					else{
						res.status(200).json(_.omit(elmt, '__v', 'owner', 'creator', 'created', 'creatorIP', 'creatorService', 'updator', 'updated', 'updatorIP', 'updatorService'));
					}
			  	 });
}

function getOneCardFunc(req, res, next){
	ws_client.getOneCard({ code : req.params.code },
			     function(err, elmt){
					if(err){
						return next(err);
					}
					// Remove owner information if the card is not active
					else if(elmt.status==="ON"){
						res.status(200).json(_.omit(elmt, '__v', 'creator', 'created', 'creatorIP', 'creatorService', 'updator', 'updated', 'updatorIP', 'updatorService'));
					}
					else{
						res.status(200).json(_.omit(elmt, '__v', 'owner', 'creator', 'created', 'creatorIP', 'creatorService', 'updator', 'updated', 'updatorIP', 'updatorService'));
					}
		  	     });
}


module.exports.card = {
	get        : getFunc,
	getOne     : getOneFunc,
	getOneCard : getOneCardFunc
};
