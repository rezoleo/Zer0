/* 
 * File 	: ./utils/main.js
 * Author(s)	: Zidmann
 * Function 	: File which contains all the functions of the authorization (access part and roles part)
 * Note		: mongoose        - refers to the mongoose module used to communicate with Mongo database
 *                roleHierarchy   - refers to the list of roles possible
 *                collection_name - refers to the name of the collection used in MongoDB to store the authorization information
 * Version  	: 1.0.0
 */

function dynamicExport(mongoose, roleHierarchy, collection_name){
	var access = require('./access.js')(mongoose, roleHierarchy, collection_name),
	    roles  = require('./roles.js')(access.common, roleHierarchy);

	return{
		access : {
			get            : access.get,
			getOne         : access.getOne,
			post           : access.post,
			delete         : access.delete,
			deleteIfNoRole : access.deleteIfNoRole
		},
		roles : {
			addRole            : roles.addRole,
			delRole            : roles.delRole,
			overwriteRole      : roles.overwriteRole,
			hasCategory        : roles.hasCategory,
			getRolesInCategory : roles.getRolesInCategory
		}
	};
}


module.exports = dynamicExport;
