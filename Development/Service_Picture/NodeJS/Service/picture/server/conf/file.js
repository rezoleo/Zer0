module.exports = {
	//WARNING : It is better that the regexps reject the special caracters (é, à, !, §, ...) more specially the '.' or '/' in the name or the extension of the file

  	dirRegexp  : /^([a-zA-Z0-9-_]){0,}$/,		//Regex to check the directory name

	nameRegexp : /^([a-zA-Z0-9-_]){0,}$/,		//Regex to check the file name
	extRegexp  : /^(|jpeg|JPEG|jpg|JPG|png|PNG)$/,	//Regex to check the file extension

	maxsize    : 1*1024*1024 			//Size limit in bytes (1MB)
}
