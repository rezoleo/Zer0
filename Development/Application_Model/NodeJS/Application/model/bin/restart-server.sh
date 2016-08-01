#!/bin/bash

########################################################################################################
# File      : ./bin/restart-server.sh                                                                  #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to restart NodeJS server running this application                                 #
# Version   : 1.0.0                                                                                    #
########################################################################################################


CURRENT_DIR=`pwd`
SCRIPT_DIR=`dirname "$0"`

cd $SCRIPT_DIR/../
if [ -n "$1" ];
	then pm2 restart $1 > /dev/null
	else echo "[ERROR] No name was given for NodeJS server"
fi


cd $CURRENT_DIR

