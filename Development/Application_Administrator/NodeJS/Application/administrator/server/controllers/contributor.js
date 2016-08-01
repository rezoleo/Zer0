/* 
 * File 	: ./server/controllers/contributor.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage contributor stored in the Contributor service
 * Version  	: 1.0.0
 */

var	csvwriter    = require('csvwriter'),
	_            = require('underscore'),
	isArray	     = require('toolbox')('ISARRAY'),
	isEmpty	     = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	filter       = require('../utils/filter.js'),
	ws_client    = require('../models/ws_contributor.js');


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
									elmt[i]={login : elmt[i].login};
								}
							}
						}
						elmt = filter.researchBarFilter(elmt, { researchStr : req.query.research,
											fields      : function(item){
											return [item.login];
											}, 
											first       : req.query.first, 
											last        : req.query.last,
											sortBy      : 'login'});

						if(req.query.format==="csv"){
							csvwriter(elmt, {delimiter: ';', decimalSeparator: ','}, function(err, csv) {
								if(err){
									return next(err);
								}
								else{
									res.attachment('list-of-contributors.csv');
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

function getOneLoginFunc(req, res, next){
	ws_client.getOneLogin({ login : req.params.login },
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
	ws_client.post({ login   : req.body.login,
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


module.exports.contributor = {
	get         : getFunc,
	getOne      : getOneFunc,
	getOneLogin : getOneLoginFunc,
	post        : postFunc,
	delete      : deleteFunc
};
