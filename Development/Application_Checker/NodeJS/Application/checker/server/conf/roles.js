/* 
 * File 	: ./server/conf/roles.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the list of all the roles in the application
 * Version  	: 1.0.0
 */


var listRoles = {
	card		: ['CARD_UNLOCKED'],
	contributor	: ['CONTRIBUTOR_UNLOCKED'],
	notification	: ['ROLE_NOTIFICATION'],
	people		: ['PEOPLE_UNLOCKED'],
	role		: ['ROLE_MANAGER']
};

module.exports = listRoles;
