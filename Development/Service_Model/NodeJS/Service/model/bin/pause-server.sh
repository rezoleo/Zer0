#!/bin/bash

########################################################################################################
# File      : ./bin/pause-server.sh                                                                    #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to pause NodeJS server running this webservice                                    #
# Version   : 1.0.0                                                                                    #
########################################################################################################


CURRENT_DIR=`pwd`
SCRIPT_DIR=`dirname "$0"`

cd $SCRIPT_DIR/../
if [ -n "$1" ];
	then pm2 stop $1 > /dev/null
	else echo "[ERROR] No name was given for NodeJS server"
fi


cd $CURRENT_DIR

