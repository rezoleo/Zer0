/* 
 * File 	: ./models/cache-infos.js
 * Author(s)	: Zidmann
 * Function 	: Model of cache information
 * Note		: mongoose   - refers to the mongoose module used to communicate with Mongo database
 *                collection - refers to the name of the collection used in MongoDB to store the cache information
 * Version  	: 1.0.0
 */

function dynamicExport(mongoose, collection){
	var isEmpty = require('toolbox')('ISEMPTY');
	if(isEmpty(mongoose)){
		return null;
	}
	var schema = mongoose.Schema;

	//Schema definition
	var cacheInfosSchema = new schema({
				lastUpdate	 : { type : Date },
				lastPostUpdate	 : { type : Date },
				lastPutUpdate	 : { type : Date },
				lastDeleteUpdate : { type : Date }
			});

	//Set the default collection name
	var collection_name='cache.info';
	if(!isEmpty(collection)){
		collection_name=collection;
	}

	//Model definition
	var cacheInfosModel = mongoose.model(collection_name, cacheInfosSchema);

	//Get one line
	cacheInfosModel.prototype.getOne = function(callback){
		cacheInfosModel.findOne({}, callback);
	};

	//Add element
	cacheInfosModel.prototype.addElmt = function(callback){
		this.lastUpdate       = new Date();
		this.lastPostUpdate   = null;
		this.lastPutUpdate    = null;
		this.lastDeleteUpdate = null;

		this.save(callback);	
	};

	//Update elements
	cacheInfosModel.prototype.updateElmt = function(callback){
		cacheInfosModel.update({}, {lastUpdate       : this.lastUpdate,
					    lastPostUpdate   : this.lastPostUpdate,
					    lastPutUpdate    : this.lastPutUpdate,
					    lastDeleteUpdate : this.lastDeleteUpdate}, null, callback);
	};
	
	return cacheInfosModel;
}


module.exports = dynamicExport;
