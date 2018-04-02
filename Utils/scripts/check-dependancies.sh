#!/bin/bash

########################################################################################################
# File      : ./scripts/check-dependancies.sh                                                          #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to check if the different ressources (NODEJS/MONGODB/NPM/BOWER) necessary         #
#             for the project are available (installed and enabled)                                    #
# Version   : 1.1.0                                                                                    #
########################################################################################################


# Root Id
ROOT_UID=0

#Check if user is not root
if [ $UID == $ROOT_UID ]; then
	echo -e "[-]Error : User must NOT be root to check dependancies."
	exit 0
fi

# déclaration dune fonction
checkFunction() 
{
	"$1" --version 1>/dev/null 2>&1
	if [ $? -ne 0 ]; then
		echo "[-] $2 NOT INSTALLED"
	else
		echo "[+] $2 INSTALLED"
	fi
}

# Step 1 : Check BASH program
checkFunction bash BASH

# Step 2 : Check OPENSSH program
checkFunction openssl OPENSSL

# Step 3 : Check MONGODB program
checkFunction mongo MONGODB

# Step 4 : Check NANO program
checkFunction nano NANO

# Step 5 : Check NODEJS program
checkFunction nodejs NODEJS

# Step 6 : Check NPM program
checkFunction npm NPM

# Step 7 : Check BOWER program
checkFunction bower BOWER

