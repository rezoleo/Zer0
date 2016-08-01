/* 
 * File 	: ./server/models/group.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model of one group stored in the Mongoose database
 * Version  	: 1.0.0
 */

var	mongoose = require('mongoose'),
	schema   = mongoose.Schema;

//Schema definition
var groupSchema = new schema({
		name     	: { type : String, required : true, trim : true, match : /^([a-z0-9-_]){1,8}$/, index: { unique: true, sparse: true } },
		type    	: { type : String, required : true },
		description  	: { type : String },
		mail      	: { type : String, trim : true, match : /^[_a-zA-Z0-9+-]+[_.a-zA-Z0-9+-]*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*(\.[a-z]{2,4})$/, index: { unique: true, sparse: true } },
		logo		: { type : String, trim : true, match : /^([a-zA-Z0-9-_])*\.(jpg|JPG|jpeg|JPEG|png|PNG)$/ },
		picture		: { type : String, trim : true, match : /^([a-zA-Z0-9-_])*\.(jpg|JPG|jpeg|JPEG|png|PNG)$/ },
		members		:[{ type : String, trim : true, match : /^([a-z0-9-_]){1,8}$/ }],
		responsibles	:[{	login		: { type : String, required : true, trim : true, match : /^([a-z0-9-_]){1,8}$/ },
					responsability 	: { type : String, required : true, trim : true }	
				  }],
		creator   	: { type : String, trim : true, match : /^([a-z0-9-_]){1,8}$/ },
		created   	: { type : Date,   required : true },
		creatorIP 	: { type : String, trim : true },
		creatorService	: { type : String, trim : true },
		updator   	: { type : String, trim : true, match : /^([a-z0-9-_]){1,8}$/ },
		updated   	: { type : Date },
		updatorIP 	: { type : String, trim : true },
		updatorService 	: { type : String, trim : true }
		});

//Model definition
var groupModel = mongoose.model('group', groupSchema);

//Get element(s)
groupModel.prototype.findElmtByID = function(callback){
	groupModel.findOne({_id : this._id}, callback);
};

groupModel.prototype.findElmtByName = function(callback){
	groupModel.findOne({name : this.name}, callback);
};

groupModel.prototype.findElmtByMail = function(callback){
	groupModel.findOne({mail : this.mail}, callback);
};

groupModel.prototype.getAll = function(callback){
        groupModel.find({}, callback);
};

groupModel.prototype.getAllWithLogin = function(login, callback){
	groupModel.find({members : {"$in" : [login]} }, callback);
};

//Add, remove elements
groupModel.prototype.addElmt = function(callback){
	this.members	    = [];
	this.responsibles   = [];
	this.created        = new Date();
	this.updator        = null;
	this.updated        = null;
	this.updatorIP      = null;
	this.updatorService = null;
	this.save(callback);	
};

groupModel.prototype.removeElmtByID = function(callback){
	groupModel.remove({_id : this.id}, callback);
};


//Update elements
groupModel.prototype.updateElmtByID = function(callback){
	this.updated = new Date();
	groupModel.update({_id : this.id}, { name           : this.name,
                                             type           : this.type,
                                             description    : this.description,
                                             mail           : this.mail,
                                             logo           : this.logo,
                                             picture        : this.picture,
                                             updator        : this.updator,
                                             updated        : this.updated,
					     updatorIP      : this.updatorIP, 
                                             updatorService : this.updatorService }, null, callback);
};

groupModel.prototype.updateMembers = function(callback){
	this.updated = new Date();
	groupModel.update({_id : this.id}, { members        : this.members,
                                             updator        : this.updator,
                                             updated        : this.updated,
					     updatorIP      : this.updatorIP, 
                                             updatorService : this.updatorService }, null, callback);
};

groupModel.prototype.updateResponsibles = function(callback){
	this.updated = new Date();
	groupModel.update({_id : this.id}, { responsibles   : this.responsibles,
                                             updator        : this.updator,
                                             updated        : this.updated,
					     updatorIP      : this.updatorIP, 
                                             updatorService : this.updatorService }, null, callback);
};


module.exports = groupModel;
