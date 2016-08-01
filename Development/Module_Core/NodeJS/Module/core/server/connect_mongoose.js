/* 
 * File 	: ./server/connect_mongoose.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the function to connect the mongoose module to the MongoDB database and to check the connection
 * Version  	: 1.0.0
 */


function connectMongooseFunc(mongoose, adress){
	mongoose.connect(adress).connection.on('error',  function(){
								console.log('[-] Could not connect to database');
								process.exit(1);
							 })
					   .once('open', function(){
								console.log('[+] Established connection to database');
							 });
}


module.exports = connectMongooseFunc;
