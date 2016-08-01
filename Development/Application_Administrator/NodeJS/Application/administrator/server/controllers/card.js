/* 
 * File 	: ./server/controllers/card.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage cards stored in the Card service
 * Version  	: 1.0.0
 */

var	csvwriter    = require('csvwriter'),
	_            = require('underscore'),
	isArray	     = require('toolbox')('ISARRAY'),
	isEmpty	     = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	filter       = require('../utils/filter.js'),
	ws_client    = require('../models/ws_card.js');


function getFunc(req, res, next){
	if(req.query.action=="get-cache-infos"){
		ws_client.getCacheInfo(function(err, elmt){
					if(err){
						return next(err);
					}
					else{
						res.status(200).json(elmt);
					}
			      });
	}
	else {
		ws_client.get(function(err, elmt){
					if(err){
						return next(err);
					}
					else{
						if(req.query.format==="csv"){
							if(isArray(elmt)){
								for(var i=0; i<elmt.length; i++){
									elmt[i]={owner : elmt[i].owner, code : elmt[i].code, status : elmt[i].status};
								}
							}
						}
						elmt = filter.researchBarFilter(elmt, {researchStr : req.query.research,
										       fields      : function(item){
												return [item.owner, item.code];
										       }, 
										       first       : req.query.first, 
										       last        : req.query.last,
										       sortBy      : 'owner'});

						if(req.query.format==="csv"){
							csvwriter(elmt, {delimiter: ';', decimalSeparator: ','}, function(err, csv) {
								if(err){
									return next(err);
								}
								else{
									res.attachment('list-of-cards.csv');
									res.status(200).send(csv);
								}
							});
						}
						else{
							res.status(200).json(elmt);
						}
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
						res.status(200).json(elmt);
					}
			  	 });
}

function getOneCardFunc(req, res, next){
	ws_client.getOneCard({ code : req.params.code },
			     function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(elmt);
				}
		  	     });
}

function postFunc(req, res, next){
	ws_client.post({ owner   : req.body.owner,
			 code    : req.body.code,
		 	 status  : req.body.status,
			 creator : session_info.getLogin(req) },
				function(err, elmt){
					if(err){
						return next(err);
					}
					else{
						res.status(200).json(elmt);
					}
			  	});
}

function putFunc(req, res, next){
	ws_client.put({ id	: req.params.id,
			owner   : req.body.owner,
			code    : req.body.code,
		 	status  : req.body.status,
			updator : session_info.getLogin(req) },
			      function(err, elmt){
					if(err){
						return next(err);
					}
					else{
						res.status(200).json(elmt);
					}
			      });
}

function deleteFunc(req, res, next){
	ws_client.delete({ id : req.params.id },
				 function(err, elmt){
					if(err){
						return next(err);
					}
					else{
						res.status(200).json(elmt);
					}
			  	 });
}


module.exports.card = {
	get        : getFunc,
	getOne     : getOneFunc,
	getOneCard : getOneCardFunc,
	post       : postFunc,
	put        : putFunc,
	delete     : deleteFunc
};
