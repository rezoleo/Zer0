/* 
 * File 	: ./server/flood_management.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the function to manage the connexions and prevent flood attacks
 * Version  	: 1.0.0
 */

var isEmpty = require('toolbox')('ISEMPTY');


/* Bruteforce protection function */
function floodManagementFunc(floodprotection){
	// Auxilary function to check if the request must be blocked by using the hash
	var floodprotection_auxi=function(hash, conf, next, callback){
		var auxi_conf=null;
		if(!isEmpty(conf.local)){
			auxi_conf=conf.local;
		}
		else if(!isEmpty(conf.global)){
			auxi_conf=conf.global;
		}
		floodprotection.reportAndCheck({ hash : hash }, auxi_conf, function(err){
			if(!isEmpty(err)){
				return next(err);
			}
			else{
				return callback();
			}
		});
	}

	// Function to manage if the functionnality is enabled or disabled
	var main_function=function(client_ip, path, flood_conf, next, callback){
		var global_conf=null,
		    local_conf=null
		if(!isEmpty(flood_conf)){
			if(!isEmpty(flood_conf.global) && !isEmpty(flood_conf.global.disabled)){
				global_conf=flood_conf.global.disabled;
			}
			if(!isEmpty(flood_conf.local) && !isEmpty(flood_conf.local.disabled)){
				local_conf=flood_conf.local.disabled;
			}
		}
		if(local_conf===true || (global_conf===true && local_conf!==false)){
			return callback();
		}
		else{
			return all_protection_auxi(client_ip, path, flood_conf, next, callback);
		}
	}

	// Protection for all requests
	var all_protection_auxi=function(client_ip, path, flood_conf, next, callback){
		var hash_line=floodprotection.hash('1'),
		    global_conf=null,
		    local_conf=null;
		if(!isEmpty(flood_conf)){
			if(!isEmpty(flood_conf.global) && !isEmpty(flood_conf.global.all)){
				global_conf=flood_conf.global.all;
			}
			if(!isEmpty(flood_conf.local) && !isEmpty(flood_conf.local.all)){
				local_conf=flood_conf.local.all;
			}
		}
		return floodprotection_auxi(hash_line, { global : global_conf, local : local_conf }, next, function(){return route_protection_auxi(client_ip, path, flood_conf, next, callback)});
	}

	// Protection for the requests for a same route (for all the IP)
	var route_protection_auxi=function(client_ip, path, flood_conf, next, callback){
		var hash_line=floodprotection.hash(path),
		    global_conf=null,
		    local_conf=null;
		if(!isEmpty(flood_conf)){
			if(!isEmpty(flood_conf.global) && !isEmpty(flood_conf.global.byRoute)){
				global_conf=flood_conf.global.byRoute;
			}
			if(!isEmpty(flood_conf.local) && !isEmpty(flood_conf.local.byRoute)){
				local_conf=flood_conf.local.byRoute;
			}
		}
		return floodprotection_auxi(hash_line, { global : global_conf, local : local_conf }, next, function(){return ip_protection_auxi(client_ip, path, flood_conf, next, callback)});
	}

	// Protection for the requests for a same IP (for all the routes)
	var ip_protection_auxi=function(client_ip, path, flood_conf, next, callback){
		var hash_line=floodprotection.hash(client_ip),
		    global_conf=null,
		    local_conf=null;
		if(!isEmpty(flood_conf)){
			if(!isEmpty(flood_conf.global) && !isEmpty(flood_conf.global.byIP)){
				global_conf=flood_conf.global.byIP;
			}
			if(!isEmpty(flood_conf.local) && !isEmpty(flood_conf.local.byIP)){
				local_conf=flood_conf.local.byIP;
			}
		}
		return floodprotection_auxi(hash_line, { global : global_conf, local : local_conf }, next, function(){return route_ip_protection_auxi(client_ip, path, flood_conf, next, callback)});
	}

	// Protection for the requests for a same IP and a same route
	var route_ip_protection_auxi=function(client_ip, path, flood_conf, next, callback){
		var hash_line=floodprotection.hash([path, client_ip]),
		    global_conf=null,
		    local_conf=null;
		if(!isEmpty(flood_conf)){
			if(!isEmpty(flood_conf.global) && !isEmpty(flood_conf.global.byRouteIP)){
				global_conf=flood_conf.global.byRouteIP;
			}
			if(!isEmpty(flood_conf.local) && !isEmpty(flood_conf.local.byRouteIP)){
				local_conf=flood_conf.local.byRouteIP;
			}
		}
		return floodprotection_auxi(hash_line, { global : global_conf, local : local_conf }, next, function(){return callback()});
	}

	return main_function;
}


module.exports = floodManagementFunc;
