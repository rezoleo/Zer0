/* 
 * File 	: ./server/utils/mail.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the functions to send a mail
 * Version  	: 1.0.0
 */


var	conf        = require('../conf/conf.js').mail,
	findError   = require('../errors/referential.js'),
	isEmpty     = require('toolbox')('ISEMPTY'),
	nodemailer  = require('nodemailer'),
	transporter = nodemailer.createTransport(conf.transport);

var error = findError("MAIL.1.1.5");

function sendFunc(req, res, next){
	if(isEmpty(req.query.token.extra.from)){
		return next(findError("MAIL.1.1.1"));
	}
	else if(isEmpty(req.query.token.extra.subject)){
		return next(findError("MAIL.1.1.2"));
	}
	else if(isEmpty(req.body.to)){
		return next(findError("MAIL.1.1.3"));
	}
	else if(isEmpty(req.body.content)){
		return next(findError("MAIL.1.1.4"));
	}
	else{
		var mailOptions = {
			from	: req.query.token.extra.from,
			to	: req.body.to, 
			subject	: req.query.token.extra.subject,
			text	: req.body.content
		};
		transporter.sendMail(mailOptions, function(err, info){
			if(!isEmpty(err)){
				error.stack = err;
				return next(error);
			}
			else{
				res.status(200).json({status: 'success'});
			}
		});
	}
}


module.exports.mail = {
	send : sendFunc
}
