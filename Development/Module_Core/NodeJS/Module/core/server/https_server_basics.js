/* 
 * File 	: ./server/https_server_basics.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the start and stop functions of an HTTPS server under NodeJS
 * Version  	: 1.0.0
 */

var https = require('https'),
    fs	  = require('fs');


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
		var cert_options = {
			requestCert        : true,	//force the client to have a certificate
			rejectUnauthorized : false,
			key  : fs.readFileSync(options.key_path),
			cert : fs.readFileSync(options.cert_path)
		};

		// start server
		server = https.createServer(cert_options, app);

		// listen a specific port and expect a specific source adress
		server.listen(options.port,
				options.host,
				function(err){
					console.log('['+(new Date())+']');

					if(process.getuid){
						console.log('[+] Starting HTTPS server on ' + server.address().address + ':' + server.address().port+' with user '+process.getuid());
					}
					else{
						console.log('[+] Starting HTTPS server on ' + server.address().address + ':' + server.address().port);
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
	console.log('[+] Stopping HTTPS server');
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
