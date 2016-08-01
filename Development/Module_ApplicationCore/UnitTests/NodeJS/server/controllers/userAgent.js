/* 
 * File 	: ./server/controllers/userAgent.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the controller used to test the JAR code which defines the user agent
 * Version  	: 1.0.0
 */


function getUserAgentFunc(req, res, next){
	res.send({user_agent : req.headers['user-agent']});
}


module.exports = {
	getUserAgent : getUserAgentFunc
};
