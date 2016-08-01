/* 
 * File 	: ./server/models/contributor.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model of contributor stored in the Mongoose database
 * Version  	: 1.0.0
 */

var	mongoose = require('mongoose'),
	schema   = mongoose.Schema;

//Schema definition
var contributorSchema = new schema({
				login     	: { type : String, required : true, trim : true, match : /^([a-z0-9-_]){1,8}$/, index: { unique: true, sparse: true } },
				creator   	: { type : String, trim : true, match : /^([a-z0-9-_]){1,8}$/ },
				created   	: { type : Date,   required: true },
				creatorIP 	: { type : String, trim : true },
				creatorService 	: { type : String, trim : true }
			});

//Model definition
var contributorModel = mongoose.model('contributor', contributorSchema);

//Get element(s)
contributorModel.prototype.findElmtByID = function(callback){
	contributorModel.findOne({_id : this._id}, callback);
};

contributorModel.prototype.findElmtByLogin = function(callback){
	contributorModel.findOne({login : this.login}, callback);
};

contributorModel.prototype.countElmtByLogin = function(callback){
	contributorModel.count({login : this.login}, callback);
};

contributorModel.prototype.getAll = function(callback){
        contributorModel.find({}, callback);
};


//Add, remove elements
contributorModel.prototype.addElmt = function(callback){
	this.created        = new Date();
	this.save(callback);	
};

contributorModel.prototype.removeElmtByID = function(callback){
	contributorModel.remove({_id : this.id}, callback);
};


module.exports = contributorModel;
