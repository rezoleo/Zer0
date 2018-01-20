/* 
 * File 	: ./server/models/card.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model of card stored in the Mongoose database
 * Version  	: 1.1.0
 */

var	mongoose = require('mongoose'),
	schema   = mongoose.Schema;

//Schema definition
var cardSchema = new schema({
		owner     	: { type : String, required : true, trim : true, match : /^([a-z0-9-_]){1,8}$/ },
		code      	: { type : String, required : true, trim : true, match : /^([a-f0-9]){14}$/, index : { unique: true, sparse: true } },
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
var cardModel = mongoose.model('card', cardSchema);

//Get element(s)
cardModel.prototype.findElmtByID = function(callback){
	cardModel.findOne({_id : this._id}, callback);
};

cardModel.prototype.findElmtByCode = function(callback){
	cardModel.findOne({code : this.code}, callback);
};

cardModel.prototype.getAll = function(callback){
        cardModel.find({}, callback);
};


//Add, remove elements
cardModel.prototype.addElmt = function(callback){
	this.created        = new Date();
	this.updator        = null;
	this.updated        = null;
	this.updatorIP      = null;
	this.updatorService = null;
	this.save(callback);	
};

cardModel.prototype.removeElmtByID = function(callback){
	cardModel.remove({_id : this.id}, callback);
};


//Update elements
cardModel.prototype.updateElmtByID = function(callback){
	this.updated = new Date();
	cardModel.update({_id : this.id}, { owner          : this.owner,
                                       	    code           : this.code,
                                            status         : this.status,
                                            updator        : this.updator,
                                            updated        : this.updated,
					    updatorIP      : this.updatorIP, 
					    updatorService : this.updatorService}, null, callback);
};


module.exports = cardModel;
