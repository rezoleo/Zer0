#!/bin/bash

########################################################################################################
# File      : ./copy-jar.sh                                                                            #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to copy the JAR sources from Development directory                                #
# Version   : 1.0.0                                                                                    #
#             The script was tested on Ubuntu 14.04                                                    #
########################################################################################################


SCRIPT_DIR=$(dirname "$0")
source $SCRIPT_DIR"/../../Utils/scripts/sources/sources.sh"
CURRENT_DIR=`pwd`

cd $DEV_DIR/

echo "-------------------------------"
echo "[+] Copying the JAR file from Development to Integration directory"
find . -name *.jar | grep JAR | xargs -i cp {} $INT_DIR/packages/All/JAR/
echo "-------------------------------"

cd $CURRENT_DIR
