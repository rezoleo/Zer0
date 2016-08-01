/* 
 * File 	: ./server/routes/category_condition.js
 * Author(s)	: Zidmann
 * Function 	: This file provides a function to control the access to role management
 * Version  	: 1.0.0
 */


var isArray       = require('toolbox')('ISARRAY'),
    isEmpty       = require('toolbox')('ISEMPTY'),
    session_info  = require('toolbox')('SESSION_INFO'),
    _		  = require('underscore');

var conditionFunc = function(req){
	if(isEmpty(req.params.category)){
		return false;
	}
	else{
		var expected_role = null,
		    user_roles 	  = session_info.getRoles(req);

		switch(req.params.category){
			case 'authentification':
				expected_role = 'AUTH_ADMIN';
				break;
			case 'card':
				expected_role = 'CARD_ADMIN';
				break;
			case 'contributor':
				expected_role = 'CONTRIBUTOR_ADMIN';
				break;
			case 'group':
				expected_role = 'GROUP_ADMIN';
				break;
			case 'people':
				expected_role = 'PEOPLE_ADMIN';
				break;
			default:
				expected_role = null;
				break;
		}
		if(isEmpty(expected_role)){
			return false;
		}
		else if(isEmpty(user_roles) || !isArray(user_roles)){
			return false;
		}
		return _.contains(user_roles, expected_role);
	}
}


module.exports = conditionFunc;
