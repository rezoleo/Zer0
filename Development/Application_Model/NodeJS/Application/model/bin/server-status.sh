#!/bin/bash

########################################################################################################
# File           : ./bin/server-status.sh                                                              #
# Author(s)      : Zidmann (emmanuel.zidel@gmail.com)                                                  #
# Contributor(s) : Nymous (t.goudine@gmail.com), Denof (tarikmegzari@gmail.com)                        #
# Function       : Script to know NodeJS server status                                                 #
# Version        : 1.0.3                                                                               #
########################################################################################################


CURRENT_DIR=`pwd`
SCRIPT_PATH="/tmp/status-server.sh"

cd /
if [ -n "$1" ]; then
        echo "pm2 show $1 | awk 'BEGIN { FS = \" \" } ; {print \$4}' | awk 'match(\$1, /^((online)|(errored)|(stopped))/) {print \$1}'" > $SCRIPT_PATH
        chmod 700 $SCRIPT_PATH;
        STATUS=`bash $SCRIPT_PATH 2>/dev/null`

        case "$STATUS" in
            online)
                STATUS=" running";
                ;;
            errored)
                STATUS=" errored";
                ;;
            stopped)
                STATUS=" paused";
                ;;
            "")
                STATUS=" stopped";
                ;;
            *)
                ;;
        esac

        echo "Status of the service $1 :$STATUS"

        rm $SCRIPT_PATH;
else
        echo "[ERROR] No name was given for NodeJS server"
fi


cd $CURRENT_DIR

