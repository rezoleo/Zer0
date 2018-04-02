#!/bin/bash

########################################################################################################
# File      : ./scripts/stop-servers.sh                                                                #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to stop all NodeJS servers                                                        #
# Version   : 1.1.0                                                                                    #
########################################################################################################


# Root Id
ROOT_UID=0

#Check if user is not root
if [ $UID == $ROOT_UID ]; then
	echo -e "[-]Error : User must NOT be root to stop NodeJs servers."
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
	npm stop & > /dev/null
	sleep 0.5s
	echo "-------------------------------"
	cd $CURRENT_DIR
done

killall npm;
pm2 list;
