#!/bin/bash

########################################################################################################
# File      : ./bin/stop-server.sh                                                                     #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to stop NodeJS server                                                             #
# Version   : 1.0.0                                                                                    #
########################################################################################################


CURRENT_DIR=`pwd`
SCRIPT_DIR=`dirname "$0"`

cd $SCRIPT_DIR/../
if [ -n "$1" ];
	then pm2 delete $1 > /dev/null
	else echo "[ERROR] No name was given for NodeJS server"
fi


cd $CURRENT_DIR

