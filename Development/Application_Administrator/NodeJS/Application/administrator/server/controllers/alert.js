/* 
 * File 	: ./server/controllers/alert.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage alerts stored in the Alert service
 * Version  	: 1.0.0
 */

var	csvwriter    = require('csvwriter'),
	_            = require('underscore'),
	isArray	     = require('toolbox')('ISARRAY'),
	isEmpty	     = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	filter       = require('../utils/filter.js'),
	ws_client    = require('../models/ws_alert.js');


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
		ws_client.get({ minimumlevel : req.query.minimumlevel,
				month        : req.query.month},
				function(err, elmt){
					if(err){
						return next(err);
					}
					else{
						if(req.query.format==="csv"){
							if(isArray(elmt)){
								for(var i=0; i<elmt.length; i++){
									elmt[i]={created : elmt[i].created, creatorService : elmt[i].creatorService, level : elmt[i].level, message : elmt[i].message};
								}
							}
						}
						elmt = filter.researchBarFilter(elmt, {researchStr : req.query.research,
										       fields      : function(item){
												return [item.message, item.creatorService];
										       },
										       first       : req.query.first,
										       last        : req.query.last,
										       sortBy      : 'created'});

						if(req.query.format==="csv"){
							csvwriter(elmt, {delimiter: ';', decimalSeparator: ','}, function(err, csv) {
								if(err){
									return next(err);
								}
								else{
									res.attachment('list-of-alerts.csv');
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


module.exports.alert = {
	get    : getFunc,
	getOne : getOneFunc,
};
