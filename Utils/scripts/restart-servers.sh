#!/bin/bash

########################################################################################################
# File      : ./scripts/restart-servers.sh                                                             #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to restart all NodeJS servers                                                     #
# Version   : 1.0.0                                                                                    #
########################################################################################################

CURRENT_DIR=`pwd`
SCRIPT_DIR=$(dirname "$0")

$SCRIPT_DIR/stop-servers.sh > /dev/null
$SCRIPT_DIR/start-servers.sh

cd $CURRENT_DIR

