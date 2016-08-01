/* 
 * File 	: ./server/conf/mail.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the mail settings to send mail
 * Version  	: 1.0.0
 */


module.exports = {
	msg : {
		from   	           : 'password-reset@ec-lille.fr',
		subject_request    : 'Réinitialisation mot de passe',
		subject_validation : 'Changement de mot de passe effectué'
	},
	token : {
		length 	 : 50,
		lifespan : 21600000 // 6hours
	}
};
