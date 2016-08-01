/* 
 * File 	: ./server/models/authentification.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model of an authentification object stored in the Mongoose database
 * Version  	: 1.0.0
 */

var	isEmpty	    = require('toolbox')('ISEMPTY'),
	mongoose    = require('mongoose'),
	passhash    = require('password-hash'),
	schema      = mongoose.Schema;

//Hash settings
var hash_settings={algorithm  : 'sha512',
		   saltLength :  10,
		   iterations :  5};

//Schema definition
var authentificationSchema = new schema({
		login     	: { type : String, required : true, trim : true, match : /^([a-z0-9-_]){1,8}$/, index: { unique: true, sparse: true } },
		mail      	: { type : String, trim : true, match : /^[_a-zA-Z0-9+-]+[_.a-zA-Z0-9+-]*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*(\.[a-z]{2,4})$/, index: { unique: true, sparse: true } },
		password  	: { type : String, required : true, trim : true},
		status    	: { type : String, required : true, enum : ['ON', 'OFF'] },
		creator   	: { type : String, trim : true, match : /^([a-z0-9-_]){1,8}$/ },
		created   	: { type : Date,   required: true },
		creatorIP 	: { type : String, trim : true },
		creatorService 	: { type : String, trim : true },
		updator   	: { type : String, trim : true, match : /^([a-z0-9-_]){1,8}$/ },
		updated   	: { type : Date },
		updatorIP 	: { type : String, trim : true },
		updatorService 	: { type : String, trim : true }
		});

//Model definition
var authentificationModel = mongoose.model('authentification', authentificationSchema);


//Get element(s)
authentificationModel.prototype.findElmtByLogin = function(callback){
        authentificationModel.findOne({login : this.login}, function (err, elmt){
							if (err){
								callback(err,null);
							}
							else if(isEmpty(elmt)){
								callback(null,elmt);
							}
							else {
								elmt.password="";
								callback(err,elmt);
							}
						});
};

authentificationModel.prototype.findElmtByLoginAndPass = function(callback){
	var password=this.password;
	authentificationModel.findOne({login : this.login}, function (err, elmt){
							if (err){
								callback(err,null);
							}
							else if(isEmpty(elmt)){
								callback(null,elmt);
							}
							else if(passhash.verify(password, elmt.password)){
								elmt.password="";
								callback(err,elmt);
							}
							else {
								callback(err,null);
							}
						});
};

authentificationModel.prototype.findElmtByMail = function(callback){
        authentificationModel.findOne({mail : this.mail}, callback);
};


authentificationModel.prototype.getAll = function(callback){
	authentificationModel.find({}, callback);
};

//Add, remove elements
authentificationModel.prototype.addElmt = function(callback){
	this.password  = passhash.generate(this.password, hash_settings);

	this.created        = new Date();
	this.updator        = null;
	this.updated        = null;
	this.updatorIP 	    = null;
	this.updatorService = null;
	this.save(function (err, elmt){
			if (err){
				callback(err,null);
			}
			else if(isEmpty(elmt)){
				callback(null,elmt);
			}
			else {
				elmt.password="";
				callback(err,elmt);
			}
		});
};

authentificationModel.prototype.removeElmtByLogin = function(callback){
	authentificationModel.remove({login : this.login}, function (err, elmt){
							if (err){
								callback(err,null);
							}
							else if(isEmpty(elmt)){
								callback(null,elmt);
							}
							else {
								elmt.password="";
								callback(err,elmt);
							}
						});
};


//Update elements
authentificationModel.prototype.updateElmtByLogin = function(options, callback){
	this.updated  = new Date();

	var auxi_function = function (err, elmt){
					if (err){
						callback(err,null);
					}
					else if(isEmpty(elmt)){
						callback(null,elmt);
					}
					else {
						elmt.password="";
						callback(err,elmt);
					}
				};

	if(!isEmpty(options) && 
	   options.keepPassword===true){
		authentificationModel.update({login : this.login}, {$set:{ mail           : this.mail,
				                                     	   status         : this.status,
				                                     	   updator	  : this.updator,
				                                     	   updated	  : this.updated,
						  	             	   updatorIP 	  : this.updatorIP,
				                                     	   updatorService : this.updatorService}}, null, auxi_function);
	}
	else{
		this.password = passhash.generate(this.password, hash_settings);
		authentificationModel.update({login : this.login}, { mail           : this.mail,
								     password	    : this.password,
								     status         : this.status,
				                                     updator	    : this.updator,
				                                     updated	    : this.updated,
						  	             updatorIP 	    : this.updatorIP,
				                                     updatorService : this.updatorService}, null, auxi_function);
	}
};


module.exports = authentificationModel;
