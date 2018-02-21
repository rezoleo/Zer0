#!/bin/bash

########################################################################################################
# File      : ./scripts/create-jar-dir.sh                                                              #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to create the JAR directory for each Java Project                                #
# Version   : 1.0.0                                                                                    #
########################################################################################################


SCRIPT_DIR=$(dirname "$0")
source $SCRIPT_DIR"/sources/sources.sh"
CURRENT_DIR=`pwd`


echo "-------------------------------"
for i in ${!dir_jar_path[@]};
do
	cd $DEV_DIR
	cd ${dir_jar_path[i]};
	echo "Creation in the directory ${dir_jar_path[i]}"
	mkdir -p JAR/
	echo "-------------------------------"
	cd $CURRENT_DIR
done
