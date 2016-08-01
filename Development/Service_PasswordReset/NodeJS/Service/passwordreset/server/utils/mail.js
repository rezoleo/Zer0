/* 
 * File 	: ./server/utils/mail.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the functions to generate the text of the mails to send
 * Version  	: 1.0.0
 */


function generateLinkMsg(data){
	var str = "Bonjour,\n\n";
	str+="Vous recevez ce message suite à une demande de changement de mot de passe depuis le service PasswordReset pour l\'identifiant '"+data.login+"'.\n\n";
	str+="Si vous êtes à l'origine de cette requête et souhaitez toujours changer de mot de passe, cliquez sur le lien ci-dessous ou recopiez le dans votre navigateur pour accéder à une page sécurisée où vous pourrez redéfinir votre mot de passe.\n";
	str+=data.url;
	str+="\n\n";
	str+="-\n";
	str+="Attention:\n";
	str+="Ce lien est valable uniquement six heures. Passé ce délai, vous devrez faire une nouvelle demande depuis la page du service PasswordReset.\n";
	str+="-\n\n";
	str+="Cordialement\n\n";
	str+="Password Reset (automate dédié à la régénération des mots de passe)";
	return str;
}
function generateConfirmationMsg(data){
	var str = "Bonjour,\n\n";
	str+="Vous recevez ce message pour vous confirmer votre changement de mot de passe réalisé depuis le service PasswordReset pour l\'identifiant '"+data.login+"'.\n\n";
	str+="-\n";
	str+="Attention:\n";
	str+="Si vous n'avez fait aucune demande de changement de mot de passe, ceci peut signifier qu'une tierce personne est parvenue à utiliser votre mail.\nDans ce cas, par mesure de précaution, nous vous conseillons de changer le mot de passe de votre boîte mail et de contacter l'administrateur gérant les mots de passe des services informatiques des étudiants de centrale Lille.\n";
	str+="-\n\n";
	str+="Cordialement\n\n";
	str+="Password Reset (automate dédié à la régénération des mots de passe)";
	return str;
}

function sendLinkInfosFunc(data, callback){
	return {
		to	: data.mail,
		content	: generateLinkMsg({ login : data.login, url : data.url })
	};
}
function sendConfirmationInfosFunc(data, callback){
	return {
		to	: data.mail,
		content : generateConfirmationMsg({ login : data.login })
	};
}

module.exports = {
	sendLinkInfos         : sendLinkInfosFunc,
	sendConfirmationInfos : sendConfirmationInfosFunc
};

