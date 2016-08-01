/* 
 * File 	: ./server/models/link.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model of a link stored in the Mongoose database
 * Version  	: 1.0.0
 */

var	mongoose = require('mongoose'),
	schema   = mongoose.Schema;

//Schema definition
var linkSchema = new schema({
		login     : { type : String, required : true, trim : true, match : /^([a-z0-9-_]){1,8}$/, index : { unique: true, sparse: true } },
		mail      : { type : String, trim : true, match : /^[_a-zA-Z0-9+-]+[_.a-zA-Z0-9+-]*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*(\.[a-z]{2,4})$/, index: { unique: true, sparse: true } },
		secretkey : { type : String, required : true, trim : true, match : /^([a-z0-9]){8,}$/,    index : { unique: true, sparse: true } },
		lifespan  : { type : Number, required : true, min: 0 },
		created   : { type : Date,   required : true },
		endDate   : { type : Date,   required : true, index : { unique: false, sparse: true }  }
		});

//Model definition
var linkModel = mongoose.model('link', linkSchema);

//Get element(s)
linkModel.prototype.findElmtByLogin = function(callback){
	linkModel.findOne({login : this.login}, callback);
};

linkModel.prototype.findElmtBySecret = function(callback){
	linkModel.findOne({secretkey : this.secretkey}, callback);
};

//Add, remove elements
linkModel.prototype.addElmt = function(callback){
	var now = new Date();

	this.created = now;
	this.endDate = new Date(now.getTime()+this.lifespan);
	this.save(callback);	
};

linkModel.prototype.removeElmtBySecret = function(callback){
	linkModel.remove({secretkey : this.secretkey}, callback);
};

linkModel.prototype.removeOldElmts = function(callback){
	linkModel.remove({endDate : {$lte : new Date()}}, callback);
};


module.exports = linkModel;
