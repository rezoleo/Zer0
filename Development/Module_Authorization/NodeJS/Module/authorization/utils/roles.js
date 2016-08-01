/* 
 * File 	: ./utils/roles.js
 * Author(s)	: Zidmann
 * Function 	: Functions to interact with authorization information more exactly with roles
 * Note		: objectDB - model of access where to put the different roles
 * Version  	: 1.0.0
 */

function dynamicExport(common, roleHierarchy){
	var	findError= require('./errors/referential.js'),
		isEmpty	 = require('toolbox')('ISEMPTY'),
		isArray	 = require('toolbox')('ISARRAY'),
		_        = require('underscore');

	var	objectDB = common.objectDB;

	function addRoleFunc(data, callback){
		if(isEmpty(data.login)){
			return callback(findError("AUTHORIZATION.2.1.1"));
		}
		else if(isEmpty(data.role)){
			return callback(findError("AUTHORIZATION.2.1.2"));
		}
		else if(!isEmpty(data.category) && !common.hasCategory(data.category)){
			return callback(findError("AUTHORIZATION.2.1.3"));
		}

		var role = data.role.toUpperCase();
		if(!isEmpty(data.category) && !_.contains(common.getRolesInCategory(data.category), role)){
			return callback(findError("AUTHORIZATION.2.1.4"));
		}
		else{
			var myObject = new objectDB({ login   : data.login,
										  roles   : [],
										  updator : data.updator,
										  updated : new Date()});
			myObject.findElmtByLogin(function (err, elmt){
							if (err){
								return callback(err);
							}
							else if(isEmpty(elmt)){
								return callback(findError("AUTHORIZATION.2.1.5"));
							}
							else if(_.contains(elmt.roles, role)){
								return callback(findError("AUTHORIZATION.2.1.6"));
							}
							else{
								elmt.roles.push(role);
								elmt.roles=common.shapeRoles(elmt.roles);

								myObject.roles 	 = elmt.roles;
								myObject.updated = new Date()

								myObject.updateElmtByLogin(function (err2){
												if (err2){
													return callback(err2);
												}
												else{
													myObject.findElmtByLogin(function (err3, elmt3){
																	if (err3){
																		return callback(err3);
																	}
																	else if(isEmpty(elmt3)){
																		return callback(findError("AUTHORIZATION.2.1.7"));
																	}
																	else if(!isEmpty(data.category)){
																		elmt3.roles=intersectRolesAuxiFunc(elmt3.roles, data.category);
																		elmt3.roles=common.shapeRoles(elmt.roles);
																		return callback(err3, elmt3);
																	}
																	else{
																		elmt3.roles=common.shapeRoles(elmt.roles);
																		return callback(err3, elmt3);
																	}
																});
												}
								});
							}
						});

		}
	}

	function deleteRoleFunc(data, callback){
		if(isEmpty(data.login)){
			return callback(findError("AUTHORIZATION.2.2.1"));
		}
		else if(isEmpty(data.role)){
			return callback(findError("AUTHORIZATION.2.2.2"));
		}
		else if(!isEmpty(data.category) && !common.hasCategory(data.category)){
			return callback(findError("AUTHORIZATION.2.2.3"));
		}

		var role = data.role.toUpperCase();
		if(!isEmpty(data.category) && !_.contains(common.getRolesInCategory(data.category), role)){
			return callback(findError("AUTHORIZATION.2.2.4"));
		}
		else{
			var myObject = new objectDB({ login   : data.login,
										  roles   : [],
										  updator : data.updator,
										  updated : new Date()});
			myObject.findElmtByLogin(function (err, elmt){
							if (err){
								return callback(err);
							}
							else if(isEmpty(elmt)){
								return callback(findError("AUTHORIZATION.2.2.5"));
							}
							else if(!_.contains(elmt.roles, role)){
								return callback(findError("AUTHORIZATION.2.2.6"));
							}
							else{
								elmt.roles.remove(role);
								elmt.roles=common.shapeRoles(elmt.roles);

								myObject.roles   = elmt.roles;
								myObject.updated = new Date();

								myObject.updateElmtByLogin(function (err2){
												if (err2){
													return callback(err2);
												}
												else{
													myObject.findElmtByLogin(function (err3, elmt3){
																	if (err3){
																		return callback(err3);
																	}
																	else if(isEmpty(elmt3)){
																		return callback(findError("AUTHORIZATION.2.2.7"));
																	}
																	else if(!isEmpty(data.category)){
																		elmt3.roles=intersectRolesAuxiFunc(elmt3.roles, data.category);
																		elmt3.roles=common.shapeRoles(elmt.roles);
																		return callback(err3, elmt3);
																	}
																	else{
																		elmt3.roles=common.shapeRoles(elmt.roles);
																		return callback(err3, elmt3);
																	}
																});
												}
								});
							}
						});

		}
	}

	function deleteSeveralRolesFunc(data, callback){
		if(isEmpty(data.login)){
			return callback(findError("AUTHORIZATION.2.3.1"));
		}
		else if(isEmpty(data.roles)){
			return callback(findError("AUTHORIZATION.2.3.2"));
		}
		else if(!isArray(data.roles)){
			return callback(findError("AUTHORIZATION.2.3.3"));
		}
		else if(data.roles.length==0){
			return callback(findError("AUTHORIZATION.2.3.4"));
		}
		else if(data.roles.length>1){
			var myObject = new objectDB({ login   : data.login,
										  roles   : [],
										  updator : data.updator,
										  updated : new Date()});
			myObject.findElmtByLogin(function (err, elmt){
							if (err){
								return callback(err);
							}
							else if(isEmpty(elmt)){
								return callback(findError("AUTHORIZATION.2.3.5"));
							}
							else{
								var auxi_callback = function(err, elmt){
									if(err){
										return callback(err);
									}
									else if(data.roles.length>1){
										deleteSeveralRolesFunc({ login  : data.login,
																 roles  : _.omit(data.roles, data.roles[0]),
																 updator: data.updator },
												    callback);
									}
									else{
										return callback(err, elmt);
									}
								}

								deleteRoleFunc({ login  : data.login,
												 role   : data.roles[0],
												 updator: data.updator },
												 auxi_callback);
							}
						});
		}
		else{
			deleteRoleFunc({ login  : data.login,
							 role   : data.roles[0],
							 updator: data.updator },
							callback);
		}
	}

	function whichCategoryFunc(role){
		if(isEmpty(role)){
			return null;
		}
		for(var key in roleHierarchy){
			var array = roleHierarchy[key];
			for(var i=0; i<array.length; i++){
				array[i]=array[i].toUpperCase()
			}
			if(_.contains(array, role.toUpperCase())){
				return key;
			}
		}
		return null;
	}

	function overwriteRoleFunc(data, callback){
		if(isEmpty(data.login)){
			return callback(findError("AUTHORIZATION.2.4.1"));
		}
		else if(isEmpty(data.role) && data.removeRole!==true){
			return callback(findError("AUTHORIZATION.2.4.2"));
		}
		else if(!isEmpty(data.category) && !common.hasCategory(data.category)){
			return callback(findError("AUTHORIZATION.2.4.3"));
		}

		var role = null;
		if(data.removeRole!==true){
			role = data.role.toUpperCase();
		}

		if(!isEmpty(data.category) && !_.contains(common.getRolesInCategory(data.category), role) && data.removeRole!==true){
			return callback(findError("AUTHORIZATION.2.4.4"));
		}
		else{
			if(data.removeRole!==true){
				var category = whichCategoryFunc(role);
				if(isEmpty(category)){
					return callback(findError("AUTHORIZATION.2.4.5"));
				}
				else if(!isEmpty(data.category) && data.category!==category){
					return callback(findError("AUTHORIZATION.2.4.6"));
				}
			}

			var myObject = new objectDB({ login   : data.login,
										  roles   : [],
										  updator : data.updator,
										  updated : new Date()});
			myObject.findElmtByLogin(function (err, elmt){
				if (err){
					return callback(err);
				}
				else if(isEmpty(elmt)){
					return callback(findError("AUTHORIZATION.2.4.7"));
				}
				else{
					var rolesInCat = common.getRolesInCategory(data.category);

					var rolesToRemove = [];
					for(var i=0; i<rolesInCat.length; i++){
						if(_.contains(elmt.roles, rolesInCat[i])){
							rolesToRemove.push(rolesInCat[i]);
						}
					}

					var addRoleAuxi=function(err2, elmt2){
						if(err2){
							return callback(err2);
						}
						else if(isEmpty(elmt2)){
							return callback(findError("AUTHORIZATION.2.4.8"));
						}
						else if(data.removeRole===true){
							if(!isEmpty(data.category)){
								elmt2.roles=common.intersectRolesAuxi(elmt2.roles, data.category);
							}
							return callback(null, elmt2);
						}
						else{
							addRoleFunc({ login   : data.login,
										  role    : role,
										  updator : data.updator },
									function(err3, elmt3){
										if(err3){
											return callback(err3);
										}
										else if(isEmpty(elmt3)){
											return callback(findError("AUTHORIZATION.2.4.9"));
										}
										else if(!isEmpty(data.category)){
											elmt3.roles=common.intersectRolesAuxi(elmt3.roles, data.category);
											return callback(null, elmt3);
										}
										else{
											return callback(null, elmt3);
										}
									});
						}
					}

					if(rolesToRemove.length==0){
						addRoleAuxi(null, elmt);
					}
					else{
						deleteSeveralRolesFunc({ login  : data.login,
												 roles  : rolesToRemove,
												 updator: data.updator },
												 addRoleAuxi);
					}
				}
			});
		}
	}

	return{
		addRole            : addRoleFunc,
		delRole            : deleteRoleFunc,
		overwriteRole      : overwriteRoleFunc,
		hasCategory        : common.hasCategory,
		getRolesInCategory : common.getRolesInCategory
	};
}


module.exports = dynamicExport;
