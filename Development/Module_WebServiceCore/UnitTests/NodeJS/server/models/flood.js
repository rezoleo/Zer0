/* 
 * File 	: ./server/models/flood.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model of the floods stored on the Mongoose database which is used to test flood protection module
 * Version  	: 1.0.0
 */

var 	conf   = require('../conf/conf.js'),
	Db     = require('mongodb').Db,
	Server = require('mongodb').Server;

function removeAllFunc(callback){
	var db = new Db(conf.mongo.databasename, new Server('localhost', 27017));
	db.open(function(err, db) {
		db.collection("floods", function(err, collection) {
			collection.remove({}, {}, function(err){
				db.close();
				callback(err);
			});
		});
	});
}


module.exports = { removeAll : removeAllFunc };
