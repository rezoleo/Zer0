/* 
 * File 	: ./server/controllers/contributor.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to read information about contributor
 * Version  	: 1.0.0
 */

var	_            = require('underscore'),
	isArray	     = require('toolbox')('ISARRAY'),
	isEmpty	     = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	ws_client    = require('../models/ws_contributor.js');


function getOneLoginFunc(req, res, next){
	if(isEmpty(req.params.login)){
	        return next(findError("CHECKER.5.1.1"));
	}
	else {
		ws_client.getOneLogin({ login : req.params.login },
				     function(err, elmt){
						if(req.params.login!==session_info.getLogin(req) &&
						   !_.contains(session_info.getRoles(req), 'CONTRIBUTOR_UNLOCKED')){
						        return next(findError("CHECKER.5.1.2"));
						}
						else if(err){
							return next(err);
						}
						else if(_.contains(session_info.getRoles(req), 'CONTRIBUTOR_UNLOCKED')){
							if(!isEmpty(elmt)){
								elmt.registered = true;
							}
							res.status(200).json(elmt);
						}
						else{
							res.status(200).json({registered : true,
									      login	 : elmt.login});
						}
			  	     });
	}
}


module.exports.contributor = {
	getOneLogin : getOneLoginFunc
};
