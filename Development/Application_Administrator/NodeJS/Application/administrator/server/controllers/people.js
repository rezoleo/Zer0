/* 
 * File 	: ./server/controllers/people.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage people information stored in the People service
 * Version  	: 1.0.0
 */

var	csvwriter    = require('csvwriter'),
	_            = require('underscore'),
	isArray	     = require('toolbox')('ISARRAY'),
	isEmpty	     = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	filter       = require('../utils/filter.js'),
	ws_client    = require('../models/ws_people.js');


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
									elmt[i]={login : elmt[i].login, lastname : elmt[i].lastname, firstname : elmt[i].firstname, sex : elmt[i].sex, mail : elmt[i].mail};
								}
							}
						}
						elmt = filter.researchBarFilter(elmt,  {researchStr : req.query.research,
											fields      : function(item){
												return [item.login, item.lastname, item.firstname, item.mail];
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
									res.attachment('list-of-people.csv');
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

function getOneMailFunc(req, res, next){
	ws_client.getOneMail({ mail : req.params.mail },
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
	ws_client.post({login     : (req.body.login)     ? req.body.login     : null,
			lastname  : (req.body.lastname)  ? req.body.lastname  : null,
			firstname : (req.body.firstname) ? req.body.firstname : null,
			sex       : (req.body.sex)       ? req.body.sex       : null,
                        birthdate : (req.body.birthdate) ? req.body.birthdate : null,
                        major     : (req.body.major)     ? req.body.major     : null,
                        mail      : (req.body.mail)      ? req.body.mail      : null,
                        tel       : (req.body.tel)       ? req.body.tel       : null,
			picture   : (req.body.picture)   ? req.body.picture   : null,
			creator   : session_info.getLogin(req)},
			 function(err, elmt){
				if(err){
					return next(err);
				}
				else{
					res.status(200).json(elmt);
				}
		      	 });
}

function addTagFunc(req, res, next){
	ws_client.addTag({id      : req.params.id,
			  tag     : req.params.tag,
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

function putFunc(req, res, next){
	ws_client.put( {id	  :  req.params.id,
			login     : (req.body.login)     ? req.body.login     : null,
			lastname  : (req.body.lastname)  ? req.body.lastname  : null,
			firstname : (req.body.firstname) ? req.body.firstname : null,
			sex       : (req.body.sex)       ? req.body.sex       : null,
                        birthdate : (req.body.birthdate) ? req.body.birthdate : null,
                        major     : (req.body.major)     ? req.body.major     : null,
                        mail      : (req.body.mail)      ? req.body.mail      : null,
                        tel       : (req.body.tel)       ? req.body.tel       : null,
			picture   : (req.body.picture)   ? req.body.picture   : null,
			updator   : session_info.getLogin(req)},
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

function deleteTagFunc(req, res, next){
	ws_client.delTag({id      : req.params.id,
			  tag     : req.params.tag,
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


module.exports.people = {
	addTag      : addTagFunc,
	delTag      : deleteTagFunc,
	get         : getFunc,
	getOne      : getOneFunc,
        getOneLogin : getOneLoginFunc,
        getOneMail  : getOneMailFunc,
	post        : postFunc,
	put         : putFunc,
	delete      : deleteFunc
};
