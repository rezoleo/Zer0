#!/bin/bash

########################################################################################################
# File      : ./bin/generate-token.sh                                                                  #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to launch the token generator to test webservicecore in a JUnit project           #
# Version   : 1.0.0                                                                                    #
########################################################################################################


CURRENT_DIR=`pwd`
SCRIPT_DIR=`dirname "$0"`

cd $SCRIPT_DIR/../
nodejs ./scripts/generate-token.js
cd $CURRENT_DIR

