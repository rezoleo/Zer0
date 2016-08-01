#!/bin/bash

########################################################################################################
# File      : ./scripts/start-servers.sh                                                               #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to start all NodeJS servers                                                       #
# Version   : 1.0.0                                                                                    #
########################################################################################################


SCRIPT_DIR=$(dirname "$0")
source $SCRIPT_DIR"/sources/sources.sh"
CURRENT_DIR=`pwd`

pm2 list > /dev/null

echo "-------------------------------"
for i in ${!dir_tab[@]};
do
	cd $DEV_DIR
	cd ${dir_tab[i]};
	npm start & > /dev/null
	sleep 0.8s
	echo "-------------------------------"
	cd $CURRENT_DIR
done

pm2 list;
