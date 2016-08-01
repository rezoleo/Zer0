/* 
 * File 	: ./mongo/generate-token.js
 * Author(s)	: Zidmann
 * Function 	: Script used to generate a token used for webservice.
 * Version  	: 1.0.0
 */

// Loading of the dependance
var 	hashMap   = require('hashmap'),
 	jwt	  = require('jwt-simple'),
	moment	  = require('moment'),
	util      = require('util'),
	generator = require('token_generator')('CRYPTOR'),
	Token	  = require('toolbox')('TOKEN')(jwt);

// Definition of the token
var token    = new Token();
token.access = [];

// Definition of the text print to the console
var console_txt = new hashMap();
console_txt.set("secretkey", 	  { console : "Clé secrète du WebService" });
console_txt.set("source_ip", 	  { console : "Adresse IP de la source" });
console_txt.set("source_service", { console : "Nom attribué au client" });
console_txt.set("dest_service",   { console : "Nom du webservice" });
console_txt.set("end_date", 	  { console : "Durée de vie (en jours) du jeton" });
console_txt.set("access", 	  { console : "Liste des rôles" });
console_txt.set("extra", 	  { console : "Données supplémentaires" });


// Definition of the hash settings
var rest = {	//Settings for token encryption
		token :{ secretkey  :'' 	//Secret key to encrypt token
		},
		//Settings for token hash
		hash : { algorithm  : 'sha512',	//Type of hash
			 saltLength :  10,	//Size of the salt
			 iterations :  5	//Number of iterations
		}
	};

// Definition of the mongo information template
var mongo = {
	adress       : 'mongodb://+<adresse machine>+:+<port>+/',
	databasename : '<nom de la bdd>'
}


// 
function ask(step){
	if(step!="" && step!="exit"){
		console.log('* '+console_txt.get(step).console+' :');
	}
}

// Definition of the last function called to print the token
function close() {
	console.log('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!');	
	console.log('token='+util.inspect(token, { showHidden: false, depth: 1 }));
	console.log('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!');
	generator(mongo, rest, [{name	:'token',
				 token  : token,
				 action :'hash'}]);
	console.log('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!');
	process.exit();
}


// 
process.stdin.resume();
process.stdin.setEncoding('utf8');

console.log('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!');
console.log('!!      TOKEN GENERATOR      !!');
console.log('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n');
console.log('Paramètres de hashage :\n * Algorithme='+rest.algorithm+'\n * Taille du salt='+rest.saltLength+'\n * Nombre d\'itérations='+rest.iterations+'\n');
console.log('Renseigner les différentes informations nécessaire pour générer le jeton :');

//Definition of the first step
var step = "secretkey";
ask(step);

//Next function called
process.stdin.on('data', function (data) {
	text=data.replace(/(\r\n|\n|\r)/gm,"");

	if(step=="exit" && data==='\n')	 { close(); }
	else if(step=="secretkey")	 { rest.token.secretkey=text; 			step="source_ip"; }
	else if(step=="source_ip")	 { token.source_ip=text; 			step="source_service"; }
	else if(step=="source_service")	 { token.source_service=text; 			step="dest_service"; }
	else if(step=="dest_service")	 { token.dest_service=text; 			step="end_date"; }
	else if(step=="end_date")	 { token.end_date=moment().add(text, 'days'); 	step="access"; }
	else if(step=="access" ||
		step==""){ 
		if(!text || text===""){
			step="extra";
		}
		else{
			token.access.push(text);
			step="";
		}
	}
	else if(step=="extra"){
		var json = null;
		try{
			json = JSON.parse(text);
			token.extra=json;
		}
		catch(e){

		}
		step="exit";
	}

	ask(step);
});

