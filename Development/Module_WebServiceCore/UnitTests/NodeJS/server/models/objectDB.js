/* 
 * File 	: ./server/models/objectDB.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model of objects stored on the Mongoose database which is used to test communication
 * Version  	: 1.0.0
 */

var 	mongoose = require('mongoose'),
	schema   = mongoose.Schema;

//Schema definition
var objectDBSchema = new schema({
			field0 : schema.ObjectId,
			field1 : { type : String,  match: /^[a-zA-Z0-9-_]+$/ },
			field2 : { type : String,  index: { unique: false, sparse: true } },
			field3 : { type : String,  required: true, uppercase: true, trim: true},
			field4 : { type : String,  enum: ['A', 'AB', 'ABCD', 'E'] },
			field5 : { type : Date },
			field6 : { type : Boolean },
			field7 : {
				field8 : { type : Number, min : 0 },
				field9 : { type : Number, max : 0 }
			}
		});

function dynamicExport(collection_name){
	//Model definition
	var objectDBModel = mongoose.model(collection_name, objectDBSchema);

	//Get element(s)
	objectDBModel.prototype.getAll		    = function(callback){
		objectDBModel.find(null, callback);
	};

	objectDBModel.prototype.findElmtByID     = function(id, callback){
		objectDBModel.find({_id : id}, callback);
	};

	objectDBModel.prototype.findElmtByField1 = function(field1, callback){
		objectDBModel.find({field1 : field1}, callback);
	};

	//Add, remove element
	objectDBModel.prototype.saveElmt = function(callback){
		this.save(callback);
	};

	objectDBModel.prototype.removeAll = function(callback){
		objectDBModel.remove(null, callback);
	};

	//Update elements
	objectDBModel.prototype.updateAll = function(callback){
		var date=new Date();
		objectDBModel.update({}, { field5 : date }, { multi : true }, callback);
	};

	return objectDBModel;
}


module.exports = dynamicExport;
