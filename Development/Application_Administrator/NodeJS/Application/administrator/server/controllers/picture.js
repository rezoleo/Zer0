/* 
 * File 	: ./server/controllers/file.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to manage picture of people stored in the Picture service
 * Version  	: 1.0.0
 */

var	_                 = require('underscore'),
	dir               = require('../conf/distant.js').picture.people_dir,
    formidable        = require('formidable'),
	fs                = require('fs'),
	findError         = require('../errors/referential.js'),
    isArray	          = require('toolbox')('ISARRAY'),
    isEmpty	          = require('toolbox')('ISEMPTY'),
    isString          = require('toolbox')('ISSTRING'),
	session_info      = require('toolbox')('SESSION_INFO'),
	ws_client_people  = require('../models/ws_people.js'),
	ws_client_picture = require('../models/ws_picture.js');


// Functions to delete the temporary files stored in /tmp
function deleteTemporaryFiles(path_list, callback){
	if(isEmpty(path_list) || !isArray(path_list) || (path_list.length==0)){
		callback();
	}
	else{
		var path = path_list.pop();
		deleteTemporaryFileAuxi(path, function(err){
			deleteTemporaryFiles(path_list, function(err2){
				if(!isEmpty(err)){
					callback(err);
				}
				else if(!isEmpty(err2)){
					callback(err2);
				}
				else{
					callback();
				}
			});
		});
	}
}

function deleteTemporaryFileAuxi(path, callback){
	if(!isEmpty(path)){
		fs.unlink(path, callback);
	}
	else{
		callback();
	}
}

// Function to delete temporary files then return the error message
function errorReturnAuxi(path_list, next, err){
	return deleteTemporaryFiles(path_list, function(err2){
		if(!isEmpty(err)){
			return next(err);
		}
		else{
			return next(err2);
		}
	});
}

// Function to update the 'picture' attribute of the person
function updateProfile(data, callback){
	ws_client_people.getOne({ id : data.id },
		function(err, elmt){
			if(!isEmpty(err)){
				return callback(err, null);
			}
			else{
				elmt.id=elmt._id;
				elmt.picture=data.picture;
				elmt.updator=data.updator;

				ws_client_people.put(elmt,
					 function(err2, elmt2){
						if(!isEmpty(err2)){
							return callback(err2, null);
						}
						else{
							return callback(null, elmt2);
						}
				});
			}
  	});
}

// Function to delete one picture
function removePicture(data, callback){
	ws_client_picture.getInfos({ filepath : data.picture },
	    function(err, elmt){
			if(!isEmpty(err)){
				callback(err, null);
			}
			else{
				ws_client_picture.delete({ filepath : data.picture },
					function(err2, elmt2){
						if(!isEmpty(err2)){
							callback(err2, null);
						}
						else{
							callback(null, elmt2);
						}
				});
			}
	});
}

function getFunc(req, res, next){
	ws_client_people.getOne({ id : req.params.id },
		 function(err, elmt){
			if(!isEmpty(err)){
				return next(err);
			}
			else if(isEmpty(elmt) || isEmpty(elmt.picture)){
				return next(findError("ADMIN.2.1.1"));
			}
			else{
				ws_client_picture.get({ filepath : elmt.picture }, res,
						        function(err2){
								return next(err2);
							});
			}
		});
}

function postFunc(req, res, next){
	var form = new formidable.IncomingForm();
	if(isEmpty(form)){
		return next(findError("ADMIN.2.2.1"));
	}
	else {
		form.parse(req, function(err, fields, files){
			// Extract the list of temporary files
			var tmp_filepaths=[];
			if(!isEmpty(files)){
				var keys = Object.keys(files);
				for(var i=0; i<keys.length; i++){
					var key = keys[i];
					var filepath=files[key].path;

					if(!isEmpty(filepath) && typeof(filepath)==='string'){
						tmp_filepaths.push(filepath);
					}
				}
			}

			if(!isEmpty(err)){
				return errorReturnAuxi(tmp_filepaths, next, err);
			}
			else if(isEmpty(files) || isEmpty(files.file)){
				return errorReturnAuxi(tmp_filepaths, next, findError("ADMIN.2.2.2"));
			}
			else if(isEmpty(files.file.path) ||
					isEmpty(files.file.size)){
				return errorReturnAuxi(tmp_filepaths, next, findError("ADMIN.2.2.3"));
			}
			else{
				ws_client_people.getOne({ id : req.params.id },
					 function(err2, elmt2){
					 	// DEFINITION OF AUXI FUNCTIONS
					 	// Function to update the information in the People service
					 	auxi_function = function(data, callback){
							updateProfile({id: data.id, picture: null, updator: data.updator},
										function(err_auxi, elmt_auxi){
											if(!isEmpty(err_auxi)){
												return callback(err_auxi, null);
											}
											else{
												return add_picture({id: req.params.id, file_path: files.file.path, updator: data.updator}, callback);
											}
							});
					 	}

					 	// Function to add a picture in the Picture serviec
					 	add_picture = function(data, callback){
					 		ws_client_picture.upload({ dir : dir, file_path : data.file_path, extension : req.query.extension },
					 			function(err_add, elmt_add){
					 				if(!isEmpty(err_add)){
					 					return callback(err_add, null);
					 				}
					 				else{
										updateProfile({id: data.id, picture: elmt_add.file_path, updator: data.updator},
													function(err_update, elmt_update){
														if(!isEmpty(err_update)){
															return callback(err_update, null);
														}
														else{
															return callback(null, elmt_update);
														}
										});
					 				}
					 		});
					 	}

					 	// NEXT
						if(!isEmpty(err2)){
							return errorReturnAuxi(tmp_filepaths, next, err2);
						}
						else if(isEmpty(elmt2.picture)){
							return auxi_function({id: req.params.id, updator: session_info.getLogin(req)},
										function(err3, elmt3){
											if(!isEmpty(err3)){
												return errorReturnAuxi(tmp_filepaths, next, err3);
											}
											else{
												return deleteTemporaryFileAuxi(files.file.path,
													function(err4){
														if(!isEmpty(err4)){
															return next(err4);
														}
														else{
															res.status(200).json(elmt3);
														}
												});
											}
										});
						}
						else{
							removePicture({picture : elmt2.picture},
								function(err5, elmt5){
									return auxi_function({id: req.params.id, updator: session_info.getLogin(req)},
										function(err6, elmt6){
											if(!isEmpty(err6)){
												return errorReturnAuxi(tmp_filepaths, next, err6);
											}
											else if(!isEmpty(err5)){
												return errorReturnAuxi(tmp_filepaths, next, err5);
											}
											else{
												return deleteTemporaryFileAuxi(files.file.path,
													function(err7){
														if(!isEmpty(err7)){
															return next(err7);
														}
														else{
															res.status(200).json(elmt6);
														}
												});
											}
										});
							});
						}
				});
			}
		});
	}
}

function deleteFunc(req, res, next){
	ws_client_people.getOne({ id : req.params.id },
		 function(err, elmt){
			if(!isEmpty(err)){
				return next(err);
			}
			else if(isEmpty(elmt) || isEmpty(elmt.picture)){
				return next(findError("ADMIN.2.3.1"));
			}
			else{
				removePicture({picture : elmt.picture},
					function(err2, elmt2){
						updateProfile({id: req.params.id, picture: null, updator: session_info.getLogin(req)},
							function(err3, elmt3){
								if(!isEmpty(err2)){
									return next(err2);
								}
								else if(!isEmpty(err3)){
									return next(err3);
								}
								else if(!isEmpty(err2)){
									return next(err2);
								}
								else{
									res.status(200).json(elmt3);
								}
					});
				});
			}
	});
}


module.exports.picture = {
	get    : getFunc,
	post   : postFunc,
	delete : deleteFunc
};
