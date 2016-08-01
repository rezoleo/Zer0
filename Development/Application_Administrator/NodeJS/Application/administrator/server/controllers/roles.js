/* 
 * File 	: ./server/controllers/roles_model.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to add or remove roles to an access which is associated to a user
 * Version  	: 1.0.0
 */

var loginCtrl	 = require('./login.js'),
    access	     = loginCtrl.login.roles_common.access,
    roles_model  = loginCtrl.login.roles_common.roles,

    findError	 = require('../errors/referential.js'),
    isEmpty      = require('toolbox')('ISEMPTY'),
    session_info = require('toolbox')('SESSION_INFO'),
    _			 = require('underscore');


function getFunc(req, res, next){
	if(isEmpty(req.params.category)){
		return next(findError("ADMIN.3.1.1"));
	}
	else if(!roles_model.hasCategory(req.params.category)){
		return next(findError("ADMIN.3.1.2"));
	}
	else{
		access.get({ category : req.params.category }, function(err, elmt){
			if(err){
				return next(err);
			}
			else if(isEmpty(elmt)){
				return next(findError("ADMIN.3.1.3"));
			}
			else{
				var elmt2 = [];
				for(var i=0; i<elmt.length; i++){
					elmt2.push({ login   : elmt[i].login,
								 roles   : elmt[i].roles,
								 creator : elmt[i].creator,
								 created : elmt[i].created,
								 updator : elmt[i].updator,
								 updated : elmt[i].updated
					});
				}
				res.status(200).json(elmt2);
			}
		});
	}
}

function postFunc(req, res, next){
	if(isEmpty(req.params.category)){
		return next(findError("ADMIN.3.2.1"));
	}
	else if(!roles_model.hasCategory(req.params.category)){
		return next(findError("ADMIN.3.2.2"));
	}
	else if(isEmpty(req.params.login)){
		return next(findError("ADMIN.3.2.3"));
	}
	else if(isEmpty(req.body.role)){
		return next(findError("ADMIN.3.2.4"));
	}
	else if(!_.contains(roles_model.getRolesInCategory(req.params.category), req.body.role)){
		return next(findError("ADMIN.3.2.5"));
	}
	else{
		access.getOne({ login : req.params.login },
				     function(err, elmt){
						if(err){
							return next(err);
						}

						updateFunction = function(err2, elmt2){
							if(err2){
								return next(err2);
							}
							else{
								roles_model.overwriteRole({ login      : req.params.login,
															category   : req.params.category,
															role       : req.body.role,
															updator    : session_info.getLogin(req) },
														function(err3, elmt3){
															if(err3){
																return next(err3);
															}
															else{
																res.status(200).json(elmt3);
															}
														});
							}
						}

						if(isEmpty(elmt)){
							access.post({ login    : req.params.login,
										  category : req.params.category,
										  role     : req.body.role,
									      creator  : session_info.getLogin(req)},
										updateFunction);
						}
						else{
							updateFunction(err, elmt);
						}
				     });
	}
}

function deleteFunc(req, res, next){
	if(isEmpty(req.params.category)){
		return next(findError("ADMIN.3.3.1"));
	}
	else if(!roles_model.hasCategory(req.params.category)){
		return next(findError("ADMIN.3.3.2"));
	}
	else if(isEmpty(req.params.login)){
		return next(findError("ADMIN.3.3.3"));
	}
	else{
		access.getOne({ login    : req.params.login,
						category : req.params.category },
				     function(err, elmt){
						if(err){
							return next(err);
						}
						else if(isEmpty(elmt)){
							return next(findError("ADMIN.3.3.4"));
						}
						else{
							roles_model.overwriteRole({ login      : req.params.login,
														removeRole : true,
														category   : req.params.category,
														updator    : session_info.getLogin(req) },
													function(err2, elmt2){
														if(err2){
															return next(err2);
														}
														else{
															access.deleteIfNoRole({ login    : elmt2.login,
																					category : req.params.category },
																	function(err3, elmt3){
																		if(err3){
																			return next(err3);
																		}
																		else{
																			res.status(200).json(elmt3);
																		}
																	});
														}
													});
						}
				     });
	}
}


module.exports.roles = {
	getRoles    : getFunc,
	updateRole  : postFunc,
	removeRoles : deleteFunc
}
