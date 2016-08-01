 /* 
 * File 	: ./server/routes/add_routes.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the function which adds a route in the web application
 * Version  	: 1.0.0
 */

var	isArray      = require('toolbox')('ISARRAY'),
	isEmpty      = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	findError    = require('../errors/referential.js');


function addRouteFunc(app, route, flood_conf){
	auxi=function auxi_function(req, res, next){
		try{
			var login = session_info.getLogin(req);
			var gate  = session_info.getGate(req);
			var roles = session_info.getRoles(req);

			var trycatch_function = function(req, res, next){
				try{
					return route.ctrl(req, res, next);
				}
				catch(err){
					return next(findError("APPCORE.3.1.6", err.toString()));
				}
			}

			// TODO : Finish the bruteforce protection here
			if(!isEmpty(flood_conf) || !isEmpty(route.flood)){
				floodprotection.check({ hash : route.flood.hashfunction(req) }, (route.flood.conf ? route.flood.conf : flood_conf),
					function(err){
						if(!isEmpty(err)){
							return next(err);
						}
				});
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


module.exports = addRouteFunc;
