var isEmpty=require('toolbox')('ISEMPTY');

var username = null;
var password = null;

var host = 'localhost';
var port = '27017';

var adress = '';
if(!isEmpty(username) && 
   !isEmpty(password)) {
	adress = 'mongodb://'+username+':'+password+'@'+host+':'+port+'/';
}
else {
	adress = 'mongodb://'+host+':'+port+'/';
}

module.exports = {
	adress       : adress,
	databasename : 'Service_Authentification'
}
