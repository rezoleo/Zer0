/* 
 * File 	: ./server/models/token.js
 * Author(s)	: Zidmann
 * Function 	: This file defines a token for WebServiceCore module
 * Note		: mongoose   - refers to the mongoose module used to communicate with Mongo database
 *                collection - refers to the name of the collection used in MongoDB to store the token information
 * Version  	: 1.0.0
 */

var 	jwt	 = require('jwt-simple'),
	passhash = require('password-hash'),

	isEmpty   = require('toolbox')('ISEMPTY'),
	Token	  = require('toolbox')('TOKEN')(jwt);

function dynamicExport(mongoose, collection){
	var schema   = mongoose.Schema;

	//Schema definition
	var tokenSchema = new schema({source  : { type : String, required : true, trim : true, index: { unique: true, sparse: true } },
				      hash    : { type : String, required : true, trim : true, index: { unique: true, sparse: true } },
		                      created : { type : Date,   required : true }
		                     });

	//Set the default collection name
	var collection_name='tokens';
	if(!isEmpty(collection)){
		collection_name=collection;
	}

	//Model definition
	var tokenModel = mongoose.model(collection_name, tokenSchema);

	//Find the element in the database
	Token.prototype.checkInDB = function(tokenString, callback){
		var source_service = this.source_service;
		tokenModel.findOne({source : source_service}, function (err, elmt){
								if (err || isEmpty(elmt)){
									callback(err,elmt);
								}
								else if(passhash.verify(tokenString, elmt.hash)){
									elmt.hash="";
									callback(err,elmt);
								}
								else {
									callback(err,null);
								}
							});
	}
	return Token;
}


module.exports = dynamicExport;
