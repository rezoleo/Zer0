/* 
 * File 	: ./server/conf/mail.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the mail settings to send mail
 * Version  	: 1.0.0
 */


module.exports = {
	transport : {
		secure	 : true,
		ignoreTLS: false,
		tls	 : { rejectUnauthorized: true },
		service	 : 'Gmail',
		auth : {
			user: 'services.eleves.eclille',
			pass: 'achanger'
		}
	},
	from : 'services.eleves.eclille@gmail.com'
};
