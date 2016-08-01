/* 
 * File 	: ./utils/token.js
 * Author(s)	: Zidmann
 * Function 	: This file defines a simple 'Token' model used by a client to describe token for communicating with REST API
 * Note		: jwt - refers to the module to generate and manage tokens
 * TODO		: Using the 'certificate' attribute to store the hash of the public key of the certificate to proove the identity of the person
 * Version  	: 1.0.0
 */

var isEmpty = require('./isEmpty.js');

function dynamicExport(jwt){
	if(isEmpty(jwt)){
		return null;
	}

	function Payload() {
		this.source_ip      = null;
		this.source_service = null;
		this.dest_service   = null;
		this.end_date       = null;
/*		this.certificate    = null; */
		this.access         = [];
		this.extra	    = null;
	};

	function Token() {
		this.source_ip      = null;
		this.source_service = null;
		this.dest_service   = null;
		this.end_date       = null;
/*		this.certificate    = null; */
		this.access         = [];
		this.extra	    = null;

		this.readable	    = true;
	};

	Token.prototype.encrypt = function(secretKey) {
		var payload = new Payload();

		payload.source_ip      = this.source_ip;
		payload.source_service = this.source_service;
		payload.dest_service   = this.dest_service;
		payload.end_date       = this.end_date;
/*		payload.certificate    = this.certificate; */
		payload.access         = this.access;
		payload.extra	       = this.extra;

		return jwt.encode(payload, secretKey);
	}

	Token.prototype.decryptToken = function(tokenString, secretKey) {
		try {
			var payload = jwt.decode(tokenString, secretKey);

			this.source_ip      = payload.source_ip;
			this.source_service = payload.source_service;
			this.dest_service   = payload.dest_service;
			this.end_date       = payload.end_date;
/*			this.certificate    = payload.certificate; */
			this.access         = payload.access;
			this.extra	    = payload.extra;

			this.readable	    = true;
		} 
		catch (err) {
			this.readable	    = false;

			this.source_ip      = null;
			this.source_service = null;
			this.dest_service   = null;
			this.end_date       = null;
/*			this.certificate    = null; */
			this.access         = [];
			this.extra	    = null;
		}
	};

	return Token;
}


module.exports = dynamicExport;
