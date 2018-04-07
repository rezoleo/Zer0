#!/bin/bash

########################################################################################################
# File      : ./scripts/restart-servers.sh                                                             #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to restart all NodeJS servers                                                     #
# Version   : 1.1.0                                                                                    #
########################################################################################################


# Root Id
ROOT_UID=0

#Check if user is not root
if [ $UID == $ROOT_UID ]; then
	echo -e "[-]Error : User must NOT be root to restart NodeJs servers."
	exit 0
fi

CURRENT_DIR=`pwd`
SCRIPT_DIR=$(dirname "$0")

$SCRIPT_DIR/stop-servers.sh > /dev/null
$SCRIPT_DIR/start-servers.sh

cd $CURRENT_DIR

