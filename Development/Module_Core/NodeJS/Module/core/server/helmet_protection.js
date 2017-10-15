/* 
 * File 	: ./server/helmet_protection.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the function to secure the communication with helmet module
 * Version  	: 1.0.0
 */

var helmet = require('helmet');


function helmetProtectionFunc(app, mode){
	// Helmet protection
	app.use(helmet());
	app.use(helmet.frameguard('deny'));
	app.use(helmet.hidePoweredBy());
	if(mode==='HTTPS'){
		app.use(helmet.hsts({
			maxAge: 10886400000,		// 18 weeks
			includeSubdomains: true,
			preload: true
		}));
	}
	app.use(helmet.ieNoOpen());
	app.use(helmet.noCache());
	app.use(helmet.noSniff());
	app.use(helmet.xssFilter({ setOnOldIE: true }));
}


module.exports = helmetProtectionFunc;
