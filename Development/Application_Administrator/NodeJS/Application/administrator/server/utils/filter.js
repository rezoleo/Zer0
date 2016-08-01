/* 
 * File 	: ./utils/filter.js
 * Author(s)	: Zidmann
 * Function 	: Functions to filter information coming from webservices
 * Version  	: 1.0.0
 */

var	_            = require('underscore'),
	isEmpty      = require('toolbox')('ISEMPTY');

// Function to reduce the number of results got from a request
function researchBarFilterFunc(list, options){
	var reduced = _.filter(list, function(item){
		if(!isEmpty(options.researchStr)){
			// Create the list of words to have in the item
			var words = _.without(options.researchStr.toLowerCase().split(' '), '');

			// Extract all the attributes which are interesting, concate them and lower the final string
			var hash = options.fields(item).join(' ').toLowerCase();

			//Check if all the words are found
			for(var i=0; i<words.length; i++){
				if (hash.indexOf(words[i])<0){
					return false;
				}
			}
		}
		return true;
	});

	// Sort the array
	reduced = _.sortBy(reduced, options.sortBy);

	// Filter the array in function of the position of each element
	reduced = reduced.slice(options.first, options.last);

	return reduced;
}


module.exports = {
	researchBarFilter : researchBarFilterFunc
};
