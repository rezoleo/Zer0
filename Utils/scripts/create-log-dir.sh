#!/bin/bash

########################################################################################################
# File      : ./scripts/create-log-dir.sh                                                              #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to create the log directory for each NodeJS server                                #
# Version   : 1.0.0                                                                                    #
########################################################################################################


SCRIPT_DIR=$(dirname "$0")
source $SCRIPT_DIR"/sources/sources.sh"
CURRENT_DIR=`pwd`


echo "-------------------------------"
for i in ${!dir_tab[@]};
do
	cd $DEV_DIR
	cd ${dir_tab[i]};
	echo "Creation in the directory ${dir_tab[i]}"
	mkdir -p log/
	echo "-------------------------------"
	cd $CURRENT_DIR
done
