/* 
 * File 	: ./server/controllers/identification.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to test identification management in ApplicationCore module
 * Version  	: 1.0.0
 */

var session_info = require('toolbox')('SESSION_INFO');

function checkFunc(req, res, next){
	res.status(200).json({system : 'OK',
			      login  : session_info.getLogin(req)});
}

function loginFunc(req, res, next){
	res.status(200).json({system : 'OK',
			      login  : session_info.setLogin(req, 'my_login')});
}

function logoutFunc(req, res, next){
	res.status(200).json({system : 'OK',
			      login  : session_info.setLogin(req, null)});
}


module.exports = {
	check  : checkFunc,
	login  : loginFunc,
	logout : logoutFunc
}
