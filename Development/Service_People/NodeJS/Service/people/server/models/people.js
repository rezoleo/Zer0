/* 
 * File 	: ./server/models/people.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model of one person stored in the Mongoose database
 * Version  	: 1.0.0
 */

var	mongoose = require('mongoose'),
	schema   = mongoose.Schema;

//Schema definition
var peopleSchema = new schema({
		login     	: { type : String, required : true, trim : true, match : /^([a-z0-9-_]){1,8}$/, index: { unique: true, sparse: true } },
		lastname  	: { type : String, required : true, trim : true, match : /^([A-Z])+([A-Z- ]){0,}$/, uppercase : true },
		firstname 	: { type : String, required : true, trim : true, match : /^([a-zA-Z])+([a-z- ]){0,}$/ },
		sex 	   	: { type : String, enum : ['M', 'F'], uppercase : true, index: { unique: false, sparse: false } },
		birthdate 	: { type : Date },
		major      	: { type : Boolean },
		mail      	: { type : String, trim : true, match : /^[_a-zA-Z0-9+-]+[_.a-zA-Z0-9+-]*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*(\.[a-z]{2,4})$/, index: { unique: true, sparse: true } },
		tel       	: { type : String, trim : true, match : /^((0|(\\+[0-9]{0,3}[-. ]?))[1-9]([-. ]?[0-9]{2}){4})?$/ },
                picture   	: { type : String, trim : true, match : /^([a-zA-Z0-9-_])*\.(jpg|JPG|jpeg|JPEG|png|PNG)$/ },
		tags		:[{ type : String, trim : true, match : /^([A-Z])+([A-Z- ]){0,}$/, uppercase : true }],
		creator   	: { type : String, trim : true, match : /^([a-z0-9-_]){1,8}$/ },
		created   	: { type : Date,   required : true },
		creatorIP 	: { type : String, trim : true },
		creatorService 	: { type : String, trim : true },
		updator   	: { type : String, trim : true, match : /^([a-z0-9-_]){1,8}$/ },
		updated   	: { type : Date },
		updatorIP 	: { type : String, trim : true },
		updatorService 	: { type : String, trim : true }
		});

//Model definition
var peopleModel = mongoose.model('people', peopleSchema);

//Get element(s)
peopleModel.prototype.findElmtByID = function(callback){
	peopleModel.findOne({_id : this._id}, callback);
};

peopleModel.prototype.findElmtByLogin = function(callback){
	peopleModel.findOne({login : this.login}, callback);
};

peopleModel.prototype.findElmtByMail = function(callback){
        peopleModel.findOne({mail : this.mail}, callback);
};

peopleModel.prototype.getAll = function(callback){
        peopleModel.find({}, callback);
};


//Add, remove elements
peopleModel.prototype.addElmt = function(callback){
	this.tags	    = [];
	this.created        = new Date();
	this.updator        = null;
	this.updated        = null;
	this.updatorIP      = null;
	this.updatorService = null;
	this.save(callback);	
};

peopleModel.prototype.removeElmtByID = function(callback){
	peopleModel.remove({_id : this.id}, callback);
};


//Update elements
peopleModel.prototype.updateElmtByID = function(callback){
	this.updated = new Date();
	peopleModel.update({_id : this.id}, { login          : this.login,
                                       	      lastname       : this.lastname,
                                              firstname      : this.firstname,
					      sex	     : this.sex,
                                              birthdate      : this.birthdate,
                                              major          : this.major,
                                              mail           : this.mail,
                                              tel            : this.tel,
					      picture        : this.picture,
                                              updator        : this.updator,
                                              updated        : this.updated,
					      updatorIP      : this.updatorIP, 
                                              updatorService : this.updatorService}, null, callback);
};


peopleModel.prototype.updateTags = function(callback){
	this.updated = new Date();
	peopleModel.update({_id : this.id}, { tags           : this.tags,
                                              updator        : this.updator,
                                              updated        : this.updated,
					      updatorIP      : this.updatorIP, 
                                              updatorService : this.updatorService}, null, callback);
};


module.exports = peopleModel;
