/* 
 * File 	: ./models/authorization.js
 * Author(s)	: Zidmann
 * Function 	: Model of authorization information
 * Note		: mongoose   - refers to the mongoose module used to communicate with Mongo database
 *                collection - refers to the name of the collection used in MongoDB to store the authorization information
 * Version  	: 1.0.0
 */

function dynamicExport(mongoose, collection){
	var isEmpty = require('toolbox')('ISEMPTY');
	if(isEmpty(mongoose)){
		return null;
	}
	var schema = mongoose.Schema;

	//Schema definition
	var authorizationSchema = new schema({
				login   : { type : String, required : true, trim : true, match : /^([a-z0-9-_]){1,8}$/, index: { unique: true, sparse: true } },
				roles 	:[{ type : String, trim : true, match : /^([A-Z])+([A-Z- ]){0,}$/, uppercase : true }],
				creator : { type : String, trim : true, match : /^([a-z0-9-_]){1,8}$/ },
				created : { type : Date,   required: true },
				updator : { type : String, trim : true, match : /^([a-z0-9-_]){1,8}$/ },
				updated : { type : Date }
			});

	//Set the default collection name
	var collection_name='authorization';
	if(!isEmpty(collection)){
		collection_name=collection;
	}

	//Model definition
	var authorizationModel = mongoose.model(collection_name, authorizationSchema);

	//Get authorization(s)
	authorizationModel.prototype.findElmtByLogin = function(callback){
		authorizationModel.findOne({login : this.login}, callback);
	};

	authorizationModel.prototype.getAll = function(callback){
		authorizationModel.find({}, callback);
	};

	//Add authorization
	authorizationModel.prototype.addElmt = function(callback){
		this.roles  = [];
		this.created = new Date();
		this.updator = null;
		this.updated = null;
		this.save(callback);
	};

	//Update an authorization
	authorizationModel.prototype.updateElmtByLogin = function(callback){
		this.updated = new Date();
		authorizationModel.update({login : this.login}, { roles   : this.roles,
														  updator : this.updator,
														  updated : this.updated}, null, callback);
	};

	//Delete an authorization
	authorizationModel.prototype.removeElmtByLogin = function(callback){
		authorizationModel.remove({login : this.login}, callback);
	};

	return authorizationModel;
}


module.exports = dynamicExport;
