#!/bin/bash

########################################################################################################
# File      : ./scripts/prepare-mongo.sh                                                               #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to prepare data inside MongoDB databases                                          #
# Version   : 1.1.0                                                                                    #
########################################################################################################


# Root Id
ROOT_UID=0

#Check if user is not root
if [ $UID == $ROOT_UID ]; then
	echo -e "[-]Error : User must NOT be root to update information in MongoDB databases."
	exit 0
fi	

CURRENT_DIR=`pwd`
SCRIPTS_DIR=$(dirname "$0")"/prepare-mongo/"

cd $SCRIPTS_DIR;

# Scripts to update data in MongoDB databases
echo "-------------------------------"
mongo Application_Administrator app-administrator.js
echo "-------------------------------"
mongo Application_Checker       app-checker.js
echo "-------------------------------"
mongo Service_Alert             serv-alert.js
echo "-------------------------------"
mongo Service_Authentification  serv-authentification.js
echo "-------------------------------"
mongo Service_Card              serv-card.js
echo "-------------------------------"
mongo Service_Group             serv-group.js
echo "-------------------------------"
mongo Service_Contributor       serv-contributor.js
echo "-------------------------------"
mongo Service_Mail              serv-mail.js
echo "-------------------------------"
mongo Service_PasswordReset     serv-passwordreset.js
echo "-------------------------------"
mongo Service_People            serv-people.js
echo "-------------------------------"
mongo Service_Provider          serv-provider.js
echo "-------------------------------"
mongo Service_Picture           serv-picture.js
echo "-------------------------------"
mongo Testing_Applicationcore   module-applicationcore.js
echo "-------------------------------"
mongo Testing_Webservicecore    module-webservicecore.js
echo "-------------------------------"

cd $CURRENT_DIR;
