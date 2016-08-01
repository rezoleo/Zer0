/* 
 * File 	: ./server/controllers/card.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to read information about cards
 * Version  	: 1.0.0
 */

var	_            = require('underscore'),
	isArray	     = require('toolbox')('ISARRAY'),
	isEmpty	     = require('toolbox')('ISEMPTY'),
	session_info = require('toolbox')('SESSION_INFO'),
	ws_client    = require('../models/ws_card.js');


function check(req, res, next){
	if(isEmpty(req.params.code)){
	        return next(findError("CHECKER.3.1.1"));
	}
	else {
		ws_client.getOneCard({ code : req.params.code },
				     function(err, elmt){
						if(err){
							return next(err);
						}
						else if(_.contains(session_info.getRoles(req), 'CARD_UNLOCKED')){
							if(!isEmpty(elmt)){
								elmt.registered = true;
							}
							res.status(200).json(elmt);
						}
						else if(elmt.owner===session_info.getLogin(req)){
							res.status(200).json({registered : true,
									      code	 : elmt.code,
									      owner	 : elmt.owner,
									      status	 : elmt.status});
						}
						else{
							res.status(200).json({registered : true,
									      code	 : elmt.code,
									      owner	 : null,
									      status	 : null});
						}
			  	     });
	}
}


module.exports.card = {
	check : check
}
