#!/bin/bash

########################################################################################################
# File      : ./scripts/create-front.sh                                                                #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to compile the front pages in the different applications or services              #
# Version   : 1.1.0                                                                                    #
########################################################################################################


# Root Id
ROOT_UID=0

#Check if user is not root
if [ $UID == $ROOT_UID ]; then
	echo -e "[-]Error : User must NOT be root to compile front pages."
	exit 0
fi

SCRIPT_DIR=$(dirname "$0")
source $SCRIPT_DIR"/sources/sources.sh"
CURRENT_DIR=`pwd`


echo "-------------------------------"
for i in ${!dir_tab[@]};
do
	cd $DEV_DIR
	cd ${dir_tab[i]};
	CHECK=`ls front/scripts/compile.sh 2>/dev/null | wc -l`
	if [ $CHECK -ne 0 ]
        then
		echo "Creation in the directory ${dir_tab[i]}"
		cd front/
		./scripts/compile.sh
		cd ../../
		echo "-------------------------------"
	fi;

	cd $CURRENT_DIR
done
