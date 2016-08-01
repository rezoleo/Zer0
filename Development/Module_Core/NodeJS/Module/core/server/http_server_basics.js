/* 
 * File 	: ./server/http_server_basics.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the start and stop functions of an HTTP server under NodeJS
 * Version  	: 1.0.0
 */

var http = require('http');


//Definition of the server and the configuration
var server = null;

//Start function
function startFunc(app, options, callback){
	if(process.getuid && 0===process.getuid()){
		console.log('['+(new Date())+']');
		console.log('[-] Disapproved user : using root user is insecure');
		process.exit(1);
	}
	else{
		// start server
		server = http.createServer(app);

		// listen a specific port and expect a specific source adress
		server.listen(options.port,
				options.host,
				function(err){
					console.log('['+(new Date())+']');

					if(process.getuid){
						console.log('[+] Starting HTTP server on ' + server.address().address + ':' + server.address().port+' with user '+process.getuid());
					}
					else{
						console.log('[+] Starting HTTP server on ' + server.address().address + ':' + server.address().port);
					}

					if(callback){
						callback(err);
					}
			});
	}
}

//Stop function
function stopFunc(callback){
	console.log('['+(new Date())+']');
	console.log('[+] Stopping HTTP server');
	var auxi_function = function(err, elmt){
		if(callback){
			callback(err, elmt);
		}
		process.exit(0);
	}

	server.close(auxi_function);
}


module.exports = {
	start : startFunc,
	stop  : stopFunc
};
