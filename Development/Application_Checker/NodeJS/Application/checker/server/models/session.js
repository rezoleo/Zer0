/*
 * File 	 : ./server/models/session.js
 * Author(s) : Zidmann
 * Function  : This file defines the model used to read the session information of one user
 * Note      : This file is necessary since the session of Picasso library used in the mobile application is different of the JAR library; the session can not be switched
 * Version   : 1.0.0
 */

var isEmpty	= require('toolbox')('ISEMPTY'),
	session = require('express-session'),
	storage = require('connect-mongo')(session),
	store   =  new storage({ mongooseConnection : require('mongoose').connection,
							 autoRemove         : 'native' });

function getInfosFunc(data, callback){
	store.get(data.sid, function(err, elmt){
		if(!isEmpty(err)){
			callback(err, null);
		}
		else if(!isEmpty(elmt)){
			callback(null, { login : elmt.login, roles : elmt.roles });
		}
		else{
			callback(null, { login : null, roles : null });
		}
	});
}


module.exports={
	getInfos  : getInfosFunc
};
