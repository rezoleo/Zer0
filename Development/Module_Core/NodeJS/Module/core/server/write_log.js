/* 
 * File 	: ./server/write_log.js
 * Author(s)	: Zidmann
 * Function 	: This file defines the function to write logs
 * Version  	: 1.0.0
 */

var isEmpty	= require('toolbox')('ISEMPTY'),
	fs	    = require('fs'),
    morgan	= require('morgan');


function logRequestsFunc(app, options){
	if(!isEmpty(options.filepath)){
		// create a write stream with append mode
		var stream = fs.createWriteStream(options.filepath, {flags: 'a'});

		app.use(morgan('combined', {stream: stream}));
	}
}


module.exports = logRequestsFunc;
