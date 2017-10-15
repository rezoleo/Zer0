 /* 
 * File 	: ./server/routes/add_routes.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the function which adds a route in the web application
 * Note		: mongoose   - refers to the mongoose module used to communicate with Mongo database
 *                collection - refers to the name of the collection used in MongoDB to store the token information
 * Version  	: 1.1.0
 */

var	isArray         = require('toolbox')('ISARRAY'),
	isEmpty         = require('toolbox')('ISEMPTY'),
	session_info    = require('toolbox')('SESSION_INFO'),
	findError       = require('../errors/referential.js');


function dynamicExport(mongoose, collection_name){
	var floodprotection = require('floodprotection')(mongoose);
	var floodmanagement = require('core')('FLOOD_MANAGEMENT')(floodprotection);

	function addRouteFunc(app, route, flood_conf){
		auxi=function auxi_function(req, res, next){
			try{
				var login = session_info.getLogin(req);
				var gate  = session_info.getGate(req);
				var roles = session_info.getRoles(req);

				var trycatch_function = function(req, res, next){
					try{
						return floodmanagement(req.connection.remoteAddress, route.path, flood_conf, next, function(){return route.ctrl(req, res, next);});
					}
					catch(err){
						return next(findError("APPCORE.3.1.6", err.toString()));
					}
				}


				// Check if route requires an identification
				if(!isEmpty(route.isLogged) && route.isLogged === true &&
				   isEmpty(login)){
					return next(findError("APPCORE.3.1.1"));
				}
				// Check if route requires a specific gate
				else if(!isEmpty(route.gates) && isArray(route.gates)){
					if(!isEmpty(gate)){
						for(var i=0; i<route.gates.length; i++){
							if (gate === route.gates[i]){
								return trycatch_function(req, res, next);
							}
						}
					}
					return next(findError("APPCORE.3.1.2"));
				}
				// Check if route requires a specific role
				else if(!isEmpty(route.roles) && isArray(route.roles)){
					if(!isEmpty(roles) && isArray(roles)){
						for(var i=0; i<route.roles.length; i++){
							for(var j=0; j<roles.length; j++){
								if (route.roles[i] === roles[j]){
									return trycatch_function(req, res, next);
								}
							}
						}
					}
					return next(findError("APPCORE.3.1.3"));
				}
				else if(!isEmpty(route.restrict) &&
					!(route.restrict(req)===true)){
					return next(findError("APPCORE.3.1.4"));
				}
				else{
					return trycatch_function(req, res, next);
				}
			}
			catch(err){
				return next(findError("APPCORE.3.1.5", err.toString()));
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
