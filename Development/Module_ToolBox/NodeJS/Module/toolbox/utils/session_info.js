/* 
 * File 	: ./utils/session_info.js
 * Author(s)	: Zidmann
 * Function 	: This file defines some functions to store information in session data (like login or roles)
 * Version  	: 1.0.0
 */

var isArray = require('./isArray.js'),
    isEmpty = require('./isEmpty.js');


function getLoginFunc(req){
	var s = req.session;
	if(isEmpty(s.login)){
		s.login=null;
	}
	return s.login;
}
function setLoginFunc(req, login){
	var s = req.session;
	s.login = login;
	if(isEmpty(s.login)){
		s.login=null;
	}
	return s.login;
}

function getGateFunc(req){
	var s = req.session;
	if(isEmpty(s.gate)){
		s.gate=null;
	}
	return s.gate;
}
function setGateFunc(req, gate){
	var s = req.session;
	s.gate = gate;
	if(isEmpty(s.gate)){
		s.gate=null;
	}
	return s.gate;
}

function getRolesFunc(req){
	var s = req.session;
	if(isEmpty(s.roles) ||
          !isArray(s.roles)){
		s.roles=[];
	}
	return s.roles;
}
function setRolesFunc(req, roles){
	var s = req.session;
	s.roles = roles;
	if(isEmpty(s.roles) ||
          !isArray(s.roles)){
		s.roles=[];
	}
	return s.roles;
}


module.exports = {
	getLogin : getLoginFunc,
	setLogin : setLoginFunc,
	getGate  : getGateFunc,
	setGate  : setGateFunc,
	getRoles : getRolesFunc,
	setRoles : setRolesFunc
};
