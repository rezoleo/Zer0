/* 
 * File 	: ./utils/access.js
 * Author(s)	: Zidmann
 * Function 	: Functions to interact with authorization information more exactly with access data
 * Note		: mongoose        - refers to the mongoose module used to communicate with Mongo database
 *                collection_name - refers to the name of the collection used in MongoDB to store the authorization information
 * Version  	: 1.0.0
 */

function dynamicExport(mongoose, roleHierarchy, collection_name){
	var	findError = require('./errors/referential.js'),
		isEmpty	  = require('toolbox')('ISEMPTY'),
		isArray	  = require('toolbox')('ISARRAY'),
		objectDB  = require('../models/authorization.js')(mongoose, collection_name),
		_ 	      = require('underscore');


	//Auxi functions
	function hasCategoryFunc(category){
		if(isEmpty(category)){
			return false;
		}
		return !isEmpty(roleHierarchy[category]);
	}

	function getRolesInCategoryFunc(category){
		if(isEmpty(category)){
			return [];
		}
		else if(!hasCategoryFunc(category)){
			return [];
		}
		else{
			var array = roleHierarchy[category];
			for(i=0; i<array.length; i++){
				array[i]=array[i].toUpperCase();
			}
			return array;
		}
	}

	function intersectRolesAuxiFunc(elmt_roles, category){
		var array=[];

		if(!isEmpty(elmt_roles) && elmt_roles.length>0){
			for(i=0; i<elmt_roles.length; i++){
				array[i]=elmt_roles[i].toUpperCase();
			}
		}
		return _.intersection(array, getRolesInCategoryFunc(category));
	}

	function shapeRolesFunc(roles){
		for(i=0; i<roles.length; i++){
			roles[i]=roles[i].toUpperCase();
		}
		roles.sort();
		return roles;
	}

	// Shared functions
	function getFunc(data, callback){
		new objectDB().getAll(function(err, elmt){
			if(err){
				return callback(err, null);
			}
			else if(!isEmpty(data.category)){
				if(!hasCategoryFunc(data.category)){
					return callback(findError("AUTHORIZATION.1.1.1"));
				}
				else{
					var elmt2 = [];
					for(var i=0; i<elmt.length; i++){
						elmt[i].roles=intersectRolesAuxiFunc(elmt[i].roles, data.category);
						elmt[i].roles=shapeRolesFunc(elmt[i].roles);
						if(elmt[i].roles.length>0){
							elmt2.push(elmt[i]);
						}
					}
					return callback(null, elmt2);
				}
			}
			else{
				var elmt2 = [];
				for(var i=0; i<elmt.length; i++){
					elmt[i].roles=shapeRolesFunc(elmt[i].roles);
					elmt2.push(elmt[i]);
				}
				return callback(null, elmt2);
			}
		});
	}

	function getOneFunc(data, callback){
		if(isEmpty(data.login)){
			return callback(findError("AUTHORIZATION.1.2.1"));
		}
		else {
			var myObject = new objectDB({ login : data.login });
			myObject.findElmtByLogin(function(err, elmt){
				if(err){
					return callback(err);
				}
				else if(!isEmpty(data.category)){
					if(!hasCategoryFunc(data.category)){
						return callback(findError("AUTHORIZATION.1.2.2"));
					}
					else{
						if(!isEmpty(elmt)){
							elmt.roles=intersectRolesAuxiFunc(elmt.roles, data.category);
							elmt.roles=shapeRolesFunc(elmt.roles);
						}
						return callback(null, elmt);
					}
				}
				else{
					if(!isEmpty(elmt)){
						elmt.roles=shapeRolesFunc(elmt.roles);
					}
					return callback(null, elmt);
				}
			});
		}
	}

	function postFunc(data, callback){
		if(isEmpty(data.login)){
			return callback(findError("AUTHORIZATION.1.3.1"));
		}
		else {
			var myObject = new objectDB({ 	login   : data.login,
											roles   : [],
											created : new Date(),
											creator : data.creator,
											updator : null,
											updated : null});
			myObject.findElmtByLogin(function (err, elmt){
							if (err){
								return callback(err);
							}
							else if(!isEmpty(elmt)){
								return callback(findError("AUTHORIZATION.1.3.2"));
							}
							else{
								myObject.addElmt(function (err2, elmt2){
											if (err2){
												return callback(err2);
											}
											else if(isEmpty(elmt2)){
												return callback(findError("AUTHORIZATION.1.2.3"));
											}
											else{
												elmt2.roles=shapeRolesFunc(elmt2.roles);
												return callback(null, elmt2);
											}
										  });
							}
						});
		}
	}

	function deleteFunc(data, callback){
		if(isEmpty(data.login)){
			return callback(findError("AUTHORIZATION.1.4.1"));
		}
		else {
			var myObject = new objectDB({ login : data.login });
			myObject.findElmtByLogin(function (err, elmt){
							if (err){
								return callback(err);
							}
							else if(isEmpty(elmt)){
								return callback(findError("AUTHORIZATION.1.4.2"));
							}
							else{
								myObject.removeElmtByLogin(function (err2, elmt2){
												if (err2){
													return callback(err2);
												}
												else if(!isEmpty(data.category)){
													if(!hasCategoryFunc(data.category)){
														return callback(findError("AUTHORIZATION.1.4.3"));
													}
													else{
														elmt2.roles=intersectRolesAuxiFunc(elmt2.roles, data.category);
														elmt2.roles=shapeRolesFunc(elmt2.roles);
														return callback(null, elmt2);
													}
												}
												else{
													elmt2.roles=shapeRolesFunc(elmt2.roles);
													return callback(null, elmt2);
												}
										  });
							}
						});
		}
	}

	function deleteIfNoRoleFunc(data, callback){
		if(isEmpty(data.login)){
			return callback(findError("AUTHORIZATION.1.5.1"));
		}
		else{
			var myObject = new objectDB({ login : data.login });
			myObject.findElmtByLogin(function (err, elmt){
							if (err){
								return callback(err);
							}
							else if(isEmpty(elmt)){
								return callback(findError("AUTHORIZATION.1.5.2"));
							}
							else if(elmt.roles.length===0){
								myObject.removeElmtByLogin(function (err2, elmt2){
												if (err2){
													return callback(err2);
												}
												else if(!isEmpty(data.category)){
													if(!hasCategoryFunc(data.category)){
														return callback(findError("AUTHORIZATION.1.5.3"));
													}
													else{
														elmt2.roles=intersectRolesAuxiFunc(elmt2.roles, data.category);
														elmt2.roles=shapeRolesFunc(elmt2.roles);
														return callback(null, elmt2);
													}
												}
												else{
													elmt2.roles=shapeRolesFunc(elmt2.roles);
													return callback(null, elmt2);
												}
										  });
							}
							else{
								elmt.roles=shapeRolesFunc(elmt.roles);
								return callback(null, elmt);
							}
						});
		}
	}

	return{
		common : {	objectDB           : objectDB,
					intersectRolesAuxi : intersectRolesAuxiFunc,
					hasCategory        : hasCategoryFunc,
					getRolesInCategory : getRolesInCategoryFunc,
					shapeRoles         : shapeRolesFunc },
		get            : getFunc,
		getOne         : getOneFunc,
		post           : postFunc,
		delete         : deleteFunc,
		deleteIfNoRole : deleteIfNoRoleFunc
	};
}


module.exports = dynamicExport;
