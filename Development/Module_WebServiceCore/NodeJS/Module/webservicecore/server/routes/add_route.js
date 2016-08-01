/* 
 * File 	: ./server/routes/add_routes.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the function which add a route in the REST webservice
 * Note		: mongoose   - refers to the mongoose module used to communicate with Mongo database
 *                collection - refers to the name of the collection used in MongoDB to store the token information
 * Version  	: 1.0.0
 */

var	flood     = require('floodprotection'),
	isEmpty	  = require('toolbox')('ISEMPTY'),
	findError = require('../errors/referential.js');


function dynamicExport(mongoose, collection){
	var Token = require('../models/token.js')(mongoose, collection);
	function addRouteFunc(app, conf, route){
		auxi=function auxi_function(req, res, next){
			try{
				var flag = false;

				var trycatch_function = function(req, res, next){
					try{
						// Use the flood protection system
						if(!isEmpty(route.flood)){
							var hash = route.flood.hash(req);
							route.flood.hash=hash;

							flood.check(route.flood, conf.flood, function(err){
								if(err){
									return next(err);
								}
								else{
									return route.ctrl(req, res, next);
								}
							});
						}
						else{
							return route.ctrl(req, res, next);
						}
					}
					catch(err){
						return next(findError("WSCORE.3.1.10", err.toString()));
					}
				}

				if(!isEmpty(route.key)){
					var token   = new Token(),
					    current = new Date();

					token.decryptToken(req.query.token, conf.token.secretkey);

					if(isEmpty(token) || isEmpty(token.readable) || token.readable!=true){
						return next(findError("WSCORE.3.1.1"));
					}
					else if(isEmpty(token.source_ip) || (token.source_ip!=req.connection.remoteAddress && token.source_ip!='0.0.0.0')){
						return next(findError("WSCORE.3.1.2"));
					}
					else if(isEmpty(token.source_service)){
						return next(findError("WSCORE.3.1.3"));
					}
					else if(isEmpty(token.dest_service) || token.dest_service!=conf.token.service_name){
						return next(findError("WSCORE.3.1.4"));
					}/*
					else if(isEmpty(token.end_date) || current<=(new Date(token.end_date)) || current.getTime()+conf.token.maximum_lifespan<=(new Date(token.end_date)).getTime()){
						return next(findError("WSCORE.3.1.5"));
					}*/
					else if(isEmpty(token.access)){
						return next(findError("WSCORE.3.1.6"));
					}
					else{
						var i = -1;
						while (++i<token.access.length){
							if (route.key === token.access[i]){
								flag = true;
								break;
							}
						}
					}

					if(flag){
						token.checkInDB(req.query.token, function (err, elmt){
													if(err){
														return next(err);
													}
													else if(isEmpty(elmt)){
														return next(findError("WSCORE.3.1.7"));
													}
													req.query.token=token;
									        		        return trycatch_function(req, res, next);
												});
					}
					else{
						return next(findError("WSCORE.3.1.8"));
					}
				}
				else{
					return trycatch_function(req, res, next);
				}
			}
			catch(err){
				return next(findError("WSCORE.3.1.9", err.toString()));
			}
		}

		switch(route.method.toUpperCase()){
			case "GET":
				app.get(route.path, auxi);
				break;
			case "POST":
				app.post(route.path, auxi);
				break;
			case "PUT":
				app.put(route.path, auxi);
				break;
			case "DELETE":
				app.delete(route.path, auxi);
				break;
			default:
				break;
		}
	}
	return addRouteFunc;
}


module.exports = dynamicExport;
