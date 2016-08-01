/* 
 * File 	: ./server/controllers/gate.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to test gate management in ApplicationCore module
 * Version  	: 1.0.0
 */

var session_info = require('toolbox')('SESSION_INFO');

function checkFunc(req, res, next){
	res.status(200).json({system : 'OK',
			      gate   : session_info.getGate(req)});
}

function gateInFunc(req, res, next){
	res.status(200).json({system : 'OK',
			      gate   : session_info.setGate(req, 'my_gate')});
}

function gateOutFunc(req, res, next){
	res.status(200).json({system : 'OK',
			      gate   : session_info.setGate(req, null)});
}


module.exports = {
	check   : checkFunc,
	gatein  : gateInFunc,
	gateout : gateOutFunc
}
