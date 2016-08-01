/* 
 * File 	: ./server/controllers/group.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage people information stored in the Group service
 * Version  	: 1.0.0
 */

var	csvwriter    = require('csvwriter'),
	_            = require('underscore'),
	isArray	     = require('toolbox')('ISARRAY'),
	isEmpty	     = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	filter       = require('../utils/filter.js'),
	ws_client    = require('../models/ws_group.js');


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
									elmt[i]={};
								}
							}
						}
						elmt = filter.researchBarFilter(elmt,{researchStr : req.query.research,
										      fields      : function(item){
										     	return [item.name, item.type, item.description, item.mail];
										      }, 
										      first       : req.query.first, 
										      last        : req.query.last,
										      sortBy      : 'name'});

						if(req.query.format==="csv"){
							csvwriter(elmt, {delimiter: ';', decimalSeparator: ','}, function(err, csv) {
								if(err){
									return next(err);
								}
								else{
									res.attachment('list-of-groups.csv');
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

function getOneByNameFunc(req, res, next){
	ws_client.getOneByName({ name : req.params.name },
			 function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(elmt);
				}
		      	 });
}

function searchByLoginFunc(req, res, next){
	ws_client.getAllWithLogin({ login : req.params.login },
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
	ws_client.post({name        : req.body.name,
                        type        : req.body.type,
			description : req.body.description,
			mail        : req.body.mail,
			logo        : req.body.logo,
			picture     : req.body.picture,
			creator	    : session_info.getLogin(req)},
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
	ws_client.put({ id	    : req.params.id,
			name        : req.body.name,
                        type        : req.body.type,
			description : req.body.description,
			mail        : req.body.mail,
			logo        : req.body.logo,
			picture     : req.body.picture,
			updator     : session_info.getLogin(req)},
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

function addMemberFunc(req, res, next){
	ws_client.addMember({ id      : req.params.id,
			      login   : req.body.login,
			      updator : session_info.getLogin(req)},
			function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(elmt);
				}
			});
}


function addresponsibleFunc(req, res, next){
	ws_client.addResponsability({ id             : req.params.id,
				      login          : req.body.login,
				      responsability : req.body.responsability,
				      updator        : session_info.getLogin(req)},
				function(err, elmt){
					if(err){
						return next(err);
					}
					else{
						res.status(200).json(elmt);
					}
				});
}

function deleteMemberFunc(req, res, next){
	ws_client.delMember({ id      : req.params.id,
			      login   : req.body.login,
			      updator : session_info.getLogin(req)},
			function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(elmt);
				}
			});
}


function deleteResponsibleFunc(req, res, next){
	ws_client.delResponsability({ id             : req.params.id,
				      login          : req.body.login,
				      responsability : req.body.responsability,
				      updator        : session_info.getLogin(req)},
				function(err, elmt){
					if(err){
						return next(err);
					}
					else{
						res.status(200).json(elmt);
					}
				});
}


module.exports.group = {
	get            : getFunc,
	getOne         : getOneFunc,
        getOneByName   : getOneByNameFunc,
        searchByLogin  : searchByLoginFunc,
	post           : postFunc,
	put            : putFunc,
	delete         : deleteFunc,
	addMember      : addMemberFunc,
	addresponsible : addresponsibleFunc,
	delMember      : deleteMemberFunc,
	delresponsible : deleteResponsibleFunc,
};
