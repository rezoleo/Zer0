/* 
 * File 	: ./server/models/floodprotection.js
 * Author(s)	: Zidmann
 * Function 	: This file manages the protection against bruteforce attacks
 * Note		: mongoose   - refers to the mongoose module used to communicate with Mongo database
 *                collection - refers to the name of the collection used in MongoDB to store the connection information
 * Version  	: 1.1.0
 */

var 	isEmpty   = require('toolbox')('ISEMPTY');

function dynamicExport(mongoose, collection){
	var schema   = mongoose.Schema;

	//Schema definition
	var floodSchema = new schema({ hash       : { type : String,  required : true,  index : { unique : false, sparse : true } },
								   start_time : { type : Date,    required : true },
								   end_time   : { type : Date,    required : true , index : { unique : false, sparse : true } },
								   count      : { type : Number,  required : true,   min   : 0 },
								   state      : { type : Boolean, required : true }
								 });

	//Set the default collection name
	var collection_name='floods';
	if(!isEmpty(collection)){
		collection_name=collection;
	}

	//Model definition
	var floodModel = mongoose.model(collection_name, floodSchema);

	// FindOne
	floodModel.prototype.findElmtByHash = function(callback){
		floodModel.findOne({hash : this.hash}, null, {sort: {count: -1 }}, callback);
	};

	// Add
	floodModel.prototype.addElmt = function(callback){
		this.count      = 0;
		this.state      = false;
		this.save(callback);
	};

	// Incrementation
	floodModel.prototype.increment = function(callback){
		floodModel.update({hash : this.hash}, { $inc: {count : 1}}, null, callback);
	};

	// Reset counter
	floodModel.prototype.reset = function(callback){
		floodModel.update({hash : this.hash}, { $set: { count : 0, state : false, end_time: this.end_time}}, null, callback);
	};

	// Locking
	floodModel.prototype.lock = function(callback){
		floodModel.update({hash : this.hash}, { $set: { state : true, end_time: this.end_time}}, null, callback);
	};

	// Removing the oldest
	floodModel.prototype.removeOldElmts = function(callback){
		floodModel.remove({end_time : {$lte : new Date()}}, callback);
	};


	return floodModel;
}


module.exports = dynamicExport;
