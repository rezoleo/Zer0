#!/bin/bash

########################################################################################################
# File      : ./scripts/purge-log.sh                                                                   #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to start NodeJS unit test servers                                                 #
# Version   : 1.1.0                                                                                    #
########################################################################################################


# Root Id
ROOT_UID=0

#Check if user is not root
if [ $UID == $ROOT_UID ]; then
	echo -e "[-]Error : User must NOT be root to clean log files."
	exit 0
fi

SCRIPT_DIR=$(dirname "$0")
source $SCRIPT_DIR"/sources/sources.sh"
CURRENT_DIR=`pwd`


echo "--------------------------------------------"
for i in ${!dir_tab[@]};
do
	cd $DEV_DIR
	cd ${dir_tab[i]};

	echo "Deletion in the directory ${dir_tab[i]}"
	rm -f log/*.log
	rm -f log/*.err
	echo "--------------------------------------------"
	cd $CURRENT_DIR
done
