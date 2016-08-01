/* 
 * File 	: ./server/models/ws_group.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the model used to call Group Service
 * Version  	: 1.0.0
 */

var conf           = require('../conf/distant.js').group,
	findError      = require('../errors/referential.js'),
	checkAttribute = require('toolbox')('CHECKATTRIBUTE'),
    isEmpty        = require('toolbox')('ISEMPTY'),
    ws_client      = require('toolbox')('WSCLIENT')({ server_ca   : conf.security.ca,
											          client_cert : conf.security.cert,
											          client_key  : conf.security.key});


function getCacheInfoFunc(callback){
	var final_uri = conf.uri+'/?action=get-cache-infos';
	if(!isEmpty(conf.token)){
		final_uri+='&token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function getFunc(callback){
	var final_uri = conf.uri+'/';
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function getOneFunc(data, callback){
	if(!checkAttribute(data.id)){
		return callback(findError("ADMIN.D.1.1"));
	}

	var final_uri = conf.uri+'/'+data.id;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function getOneByNameFunc(data, callback){
	if(!checkAttribute(data.name)){
		return callback(findError("ADMIN.D.2.1"));
	}

	var final_uri = conf.uri+'/name/'+data.name;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function getAllWithLoginFunc(data, callback){
	if(!checkAttribute(data.login)){
		return callback(findError("ADMIN.D.3.1"));
	}

	var final_uri = conf.uri+'/search/'+data.login;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.get({ uri : final_uri}, callback);
}

function postFunc(data, callback){
	var final_uri = conf.uri+'/';
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.post({ uri  : final_uri,
			 form : { name        : data.name,
				  type        : data.type,
				  description : data.description,
				  mail        : data.mail,
				  logo        : data.logo,
				  picture     : data.picture,
				  creator     : data.creator}}, callback);
}

function putFunc(data, callback){
	if(!checkAttribute(data.id)){
		return callback(findError("ADMIN.D.4.1"));
	}

	var final_uri = conf.uri+'/'+data.id;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.put({ uri  : final_uri,
			form : { id	     : data.id,
				 name        : data.name,
		                 type        : data.type,
				 description : data.description,
				 mail        : data.mail,
				 logo        : data.logo,
				 picture     : data.picture,
				 updator     : data.updator}}, callback);
}

function deleteFunc(data, callback){
	if(!checkAttribute(data.id)){
		return callback(findError("ADMIN.D.5.1"));
	}

	var final_uri = conf.uri+'/'+data.id;
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.delete({ uri : final_uri}, callback);
}

function addMemberFunc(data, callback){
	if(!checkAttribute(data.id)){
		return callback(findError("ADMIN.D.6.1"));
	}

	var final_uri = conf.uri+'/'+data.id+'/member';
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.post({ uri  : final_uri,
			 form : {login   : data.login,
				 updator : data.updator}}, callback);
}

function delMemberFunc(data, callback){
	if(!checkAttribute(data.id)){
		return callback(findError("ADMIN.D.7.1"));
	}

	var final_uri = conf.uri+'/'+data.id+'/member';
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.put({ uri  : final_uri,
			form : {login   : data.login,
				updator : data.updator}}, callback);
}

function addResponsabilityFunc(data, callback){
	if(!checkAttribute(data.id)){
		return callback(findError("ADMIN.D.8.1"));
	}

	var final_uri = conf.uri+'/'+data.id+'/member';
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.post({ uri  : final_uri,
			 form : {login          : data.login,
				 responsability : data.responsability,
				 updator        : data.updator}}, callback);
}

function delResponsabilityFunc(data, callback){
	if(!checkAttribute(data.id)){
		return callback(findError("ADMIN.D.9.1"));
	}

	var final_uri = conf.uri+'/'+data.id+'/member';
	if(!isEmpty(conf.token)){
		final_uri+='?token='+conf.token;
	}
	ws_client.put({ uri  : final_uri,
			form : {login          : data.login,
				responsability : data.responsability,
				updator        : data.updator}}, callback);

}


module.exports = {
	getCacheInfo      : getCacheInfoFunc,
	get               : getFunc,
	getOne            : getOneFunc,
	getOneByName      : getOneByNameFunc,
	getAllWithLogin   : getAllWithLoginFunc,
	post              : postFunc,
	put               : putFunc,
	delete            : deleteFunc,
	addMember         : addMemberFunc,
	addResponsability : addResponsabilityFunc,
	delMember         : delMemberFunc,
	delResponsability : delResponsabilityFunc,
};
