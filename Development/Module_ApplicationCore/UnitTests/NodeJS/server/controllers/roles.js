/* 
 * File 	: ./server/controllers/roles.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to test role management in ApplicationCore module
 * Version  	: 1.0.0
 */

var session_info = require('toolbox')('SESSION_INFO');

function checkFunc(req, res, next){
	res.status(200).json({system : 'OK',
			      roles  : session_info.getRoles(req)});
}

function roleInFunc(req, res, next){
	res.status(200).json({system : 'OK',
			      roles  : session_info.setRoles(req, ['my_role', 'my_role_bis'])});
}

function roleOutFunc(req, res, next){
	res.status(200).json({system : 'OK',
			      roles  : session_info.setRoles(req, null)});
}


module.exports = {
	check   : checkFunc,
	rolein  : roleInFunc,
	roleout : roleOutFunc
}
