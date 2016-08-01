module.exports = {
	//Settings for token encryption
	token : {maximum_lifespan :  3600000,	//Maximum lifespan of a token (in ms)
		 minimum_size     :  20,	//Minimum size of the encryption key
		 secretkey        : 'd2s42dggjfqlry6Jfs9shrgrxjthty' //Secret key to encrypt token
	},

	//Settings for token hash
	hash : {algorithm  : 'sha512',	//Type of hash
		saltLength :  10,	//Size of the salt
		iterations :  5		//Number of iterations
	}
}
