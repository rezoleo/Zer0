/* 
 * File 	: ./server/conf/roles.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the list of all the roles in the application
 * Version  	: 1.0.0
 */


var listRoles = {
	authentification : ['AUTH_READ',	'AUTH_WRITE',		'AUTH_ADMIN'],
	card		 : ['CARD_READ',	'CARD_WRITE',		'CARD_ADMIN'],
	contributor	 : ['CONTRIBUTOR_READ',	'CONTRIBUTOR_WRITE',	'CONTRIBUTOR_ADMIN'],
	group		 : ['GROUP_READ',	'GROUP_WRITE',		'GROUP_ADMIN' ],
	people		 : ['PEOPLE_READ',	'PEOPLE_WRITE',		'PEOPLE_ADMIN']
};

module.exports = listRoles;
