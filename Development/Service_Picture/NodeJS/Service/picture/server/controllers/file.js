/*
 * File 	: ./server/controllers/file.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to store pictures
 * Version  	: 1.0.0
 */

var crypto     = require('crypto'),
    formidable = require('formidable'),
    fs         = require('fs'),
    path       = require('path'),
    _          = require('underscore'),

    conf      = require('../conf/file.js'),
    findError = require('../errors/referential.js'),
    ws_client = require('../models/ws_picture.js'),
    isArray   = require('toolbox')('ISARRAY'),
    isEmpty   = require('toolbox')('ISEMPTY'),
    isString  = require('toolbox')('ISSTRING'),
    Token     = require('toolbox')('TOKEN');

// Function to get the prefix and the extension of the picture filename
function extractFileNameInfos(filename){
	if(isEmpty(filename)){
		filename='';
	}
	var	index      = filename.lastIndexOf('.'),
		file_start = '',
		file_ext   = '';
	if(index>=0){
		file_start = filename.substring(0, index);
		file_ext   = filename.split('.').pop();
	}
	else {
		file_start = filename;
	}

	return {
		file_start : file_start,
		file_ext   : file_ext
	}
}

// Function to calculate the checksum
function getChecksum(str){
	return crypto.createHash('md5')
		     .update(str, 'utf8')
		     .digest('hex');
}

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

// Function to get information on one picture
function getFunc(req, res, next){
	if(isEmpty(req.params.dir)){
		return next(findError("PICTURE.1.1.1"));
	}
	else if(isEmpty(req.params.file)){
		return next(findError("PICTURE.1.1.2"));
	}
	else if(!conf.dirRegexp.test(req.params.dir)){
		return next(findError("PICTURE.1.1.3"));
	}
	else {
		var auxi       = extractFileNameInfos(req.params.file);
		var file_start = auxi.file_start;
		var file_ext   = auxi.file_ext;

		if(!isEmpty(conf.nameRegexp) &&
		   !conf.nameRegexp.test(file_start)){
				return next(findError("PICTURE.1.1.4"));
		}
		else if(!isEmpty(conf.extRegexp) &&
			!conf.extRegexp.test(file_ext)){
			return next(findError("PICTURE.1.1.5"));
		}
		else if(!isEmpty(req.query.token) &&
				(isEmpty(req.query.token.extra) ||
				 isEmpty(req.query.token.extra.read) ||
				 !isArray(req.query.token.extra.read) ||
				 !_.contains(req.query.token.extra.read, req.params.dir))){
			return next(findError("PICTURE.1.1.6"));
		}
		else {
			var file_name=path.join(req.params.dir, req.params.file);
			if(req.query.action==="get-cache-infos"){
				var file_path=path.join(__dirname, '..', '..', 'front', 'public', file_name);

				fs.readFile(file_path, function (err, data){
					if (err){
						return next(findError("PICTURE.1.1.7"));
					}
					else {
						var sum=getChecksum(data);
						if(isEmpty(sum)){
							return next(findError("PICTURE.1.1.8"));
						}
						else {
							res.status(200).json({'file_path': file_name, 'checksum': sum});
						}
					}
				});
			}
			else{
				ws_client.get({ dir : req.params.dir, file : req.params.file }, res,
					        function(err2){
							return next(err2);
						});
			}
		}
	}
}

function postFunc(req, res, next){
	if(isEmpty(req.params.dir)){
		return next(findError("PICTURE.1.2.1"));
	}
	else if(!conf.dirRegexp.test(req.params.dir)){
		return next(findError("PICTURE.1.2.2"));
	}
	else if(!isEmpty(req.query.token) &&
			(isEmpty(req.query.token.extra) ||
			 isEmpty(req.query.token.extra.add) ||
			 !isArray(req.query.token.extra.add) ||
			 !_.contains(req.query.token.extra.add, req.params.dir))){
		return next(findError("PICTURE.1.2.3"));
	}
	else{
		var form = new formidable.IncomingForm();
		if(isEmpty(form)){
			return next(findError("PICTURE.1.2.4"));
		}
		else{
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

				if(err){
					return errorReturnAuxi(tmp_filepaths, next, err);
				}
				else if(isEmpty(files) || isEmpty(files.file)){
					return errorReturnAuxi(tmp_filepaths, next, findError("PICTURE.1.2.5"));
				}
				else if(isEmpty(files.file.path) ||
					isEmpty(files.file.size)){
					return errorReturnAuxi(tmp_filepaths, next, findError("PICTURE.1.2.6"));
				}
				else {
					var auxi = extractFileNameInfos(files.file.name);
					var file_start = auxi.file_start;
					var file_ext   = auxi.file_ext;

					if(!isEmpty(req.query.extension)){
						file_ext = req.query.extension;
					}

					if(!isEmpty(conf.maxsize) &&
					   files.file.size>conf.maxsize){
						return errorReturnAuxi(tmp_filepaths, next, findError("PICTURE.1.2.7"));
					}
					else if(!isEmpty(conf.nameRegexp) &&
						!conf.nameRegexp.test(file_name)){
						return errorReturnAuxi(tmp_filepaths, next, findError("PICTURE.1.2.8"));
					}
					else if(!isEmpty(conf.extRegexp) &&
						!conf.extRegexp.test(file_ext)){
						return errorReturnAuxi(tmp_filepaths, next, findError("PICTURE.1.2.9"));
					}
					else {
						var file_name = getChecksum(Math.random().toString());
						file_name=path.join(req.params.dir, file_name);
						if(file_ext!=""){
							file_name = file_name+'.'+file_ext;
						}

						var file_path=path.join(__dirname, '..', '..', 'front', 'public', file_name);

						fs.readFile(files.file.path, function (err2, data2){
							if (err2){
								return errorReturnAuxi(tmp_filepaths, next, findError("PICTURE.1.2.10"));
							}
							else {
								fs.writeFile(file_path, data2, function(err3){
									if (err3){
										return errorReturnAuxi(tmp_filepaths, next, findError("PICTURE.1.2.11"));
									}
									else {
										var sum=getChecksum(data2);
										if(isEmpty(sum)){
											return errorReturnAuxi(tmp_filepaths, next, findError("PICTURE.1.2.12"));
										}
										else {
											return deleteTemporaryFiles(tmp_filepaths,
															function(err4, elmt4){
																if(err4){
																	return next(err4);
																}
																else{
																	res.status(200).json({'file_path': file_name, 'checksum': sum});
																}
															});
										}
									}
								});

							}
						});
					}
				}
			});
		}
	}
}

function deleteFunc(req, res, next){
	if(isEmpty(req.params.dir)){
		return next(findError("PICTURE.1.3.1"));
	}
	else if(isEmpty(req.params.file)){
		return next(findError("PICTURE.1.3.2"));
	}
	else if(!conf.dirRegexp.test(req.params.dir)){
		return next(findError("PICTURE.1.3.3"));
	}
	else {
		var	auxi = extractFileNameInfos(req.params.file);
		var file_start = auxi.file_start;
		var	file_ext   = auxi.file_ext;

		if(!isEmpty(conf.nameRegexp) &&
		   !conf.nameRegexp.test(file_start)){
				return next(findError("PICTURE.1.3.4"));
		}
		else if(!isEmpty(conf.extRegexp) &&
			!conf.extRegexp.test(file_ext)){
			return next(findError("PICTURE.1.3.5"));
		}
		else if(!isEmpty(req.query.token) &&
				(isEmpty(req.query.token.extra) ||
				 isEmpty(req.query.token.extra.delete) ||
				 !isArray(req.query.token.extra.delete) ||
				 !_.contains(req.query.token.extra.delete, req.params.dir))){
			return next(findError("PICTURE.1.3.6"));
		}
		else {
			var file_name=path.join(req.params.dir, req.params.file);
			var file_path=path.join(__dirname, '..', '..', 'front', 'public', file_name);

			fs.readFile(file_path, function (err, data){
				if (err){
					return next(findError("PICTURE.1.3.7"));
				}
				else {
					var sum=getChecksum(data);
					if(isEmpty(sum)){
						return next(findError("PICTURE.1.3.8"));
					}
					else {
						fs.unlink(file_path, function(err2){
							if (err2){
								return next(findError("PICTURE.1.3.9"));
							}
							else {
								res.status(200).json({'file_path': file_name, 'checksum': sum});
							}
						});
					}
				}
			});
		}
	}
}


module.exports.file = {
	get     : getFunc,
	post    : postFunc,
	delete  : deleteFunc
};
