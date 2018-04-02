#!/bin/bash

########################################################################################################
# File      : ./scripts/create-module-links.sh                                                         #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to create the links inside node_modules inside all NodeJS servers                 #
# Version   : 1.1.0                                                                                    #
########################################################################################################


# Root Id
ROOT_UID=0

#Check if user is not root
if [ $UID == $ROOT_UID ]; then
	echo -e "[-]Error : User must NOT be root to create symbolic links to the modules."
	exit 0
fi

SCRIPT_DIR=$(dirname "$0")
source $SCRIPT_DIR"/sources/sources.sh"
CURRENT_DIR=`pwd`


cd $DEV_DIR
mkdir node_modules/ 2>&1 1>/dev/null

for i in ${!dir_module_path[@]};
do
	ln -s ../${dir_module_path[i]} node_modules/${dir_module_name[i]} 2>&1 1>/dev/null
done

cd $CURRENT_DIR
