/*
 * File 	: ./models/notification.js
 * Author(s)	: Zidmann
 * Function 	: Model of a notification
 * Note		: mongoose   - refers to the mongoose module used to communicate with Mongo database
 *                collection - refers to the name of the collection used in MongoDB to store the notification information
 * Version  	: 1.0.0
 */


function dynamicExport(mongoose, collection){
	var isEmpty = require('toolbox')('ISEMPTY');
	if(isEmpty(mongoose)){
		return null;
	}
	var schema = mongoose.Schema;

	//Schema definition
	var notificationSchema = new schema({
				message	  : { type : String, required : true, trim : true },
				level	  : { type : String, required : true, enum : ['1_EMERGENCY', '2_HIGH', '3_NORMAL', '4_LOW', '5_VERY_LOW'] },
				updator   : { type : String, trim : true, match : /^([a-z0-9-_]){1,8}$/ },
				updated   : { type : Date }
			});

	//Set the default collection name
	var collection_name='notification';
	if(!isEmpty(collection)){
		collection_name = collection;
	}

	//Model definition
	var notificationModel = mongoose.model(collection_name, notificationSchema);

	//Get one line
	notificationModel.prototype.getOne = function(callback){
		notificationModel.findOne({}, callback);
	};

	//Add element
	notificationModel.prototype.addElmt = function(callback){
		this.updated = new Date();
		this.save(callback);
	};

	//Update elements
	notificationModel.prototype.updateElmt = function(callback){
		notificationModel.update({}, { message : this.message,
					       level   : this.level,
					       updator : this.updator,
					       updated : new Date() }, null, callback);
	};

	//Remove elements
	notificationModel.prototype.removeElmt = function(callback){
		notificationModel.remove(null, callback);
	};

	return notificationModel;
}


module.exports = dynamicExport;
