/* 
 * File 	: ./server/models/alert.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model of an alert object stored in the Mongoose database
 * Version  	: 1.0.0
 */

var	isEmpty	 = require('toolbox')('ISEMPTY'),
	mongoose = require('mongoose'),
	schema   = mongoose.Schema;

//Schema definition
var alertSchema = new schema({
		message : { type : String, required : true },
		level   : { type : String, required : true, enum : ['INFO', 'WARNING', 'ERROR', 'CRITICAL'] },
		created        : { type : Date,   required: true },
		creatorIP      : { type : String, trim : true },
		creatorService : { type : String, trim : true },
		});

//Model definition
var alertModel = mongoose.model('alert', alertSchema);

//Get element(s)
alertModel.prototype.findElmtByID = function(callback){
	alertModel.findOne({_id : this._id}, callback);
};

alertModel.prototype.getAll = function(callback){
	alertModel.find({}, callback);
};

alertModel.prototype.getAllByLevelList = function(callback){
	alertModel.find({level : {$in: this.levels}}, callback);
};

//Add, remove elements
alertModel.prototype.addElmt = function(callback){
	this.created = new Date();
	this.save(callback);
};


module.exports = alertModel;
