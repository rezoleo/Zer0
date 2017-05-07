#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

########################################################################################################
# File      : ./scripts/install-modules.sh                                                             #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com), Nymous                                               #
# Function  : Script to install the different dependancies in node_modules/ and bower_components/      #
#             directories                                                                              #
# Version   : 1.1.0                                                                                    #
########################################################################################################


SCRIPT_DIR=$(dirname "$0")
# shellcheck source=sources/sources.sh
source "$SCRIPT_DIR/sources/sources.sh"
# shellcheck source=sources/logger.sh
source "$SCRIPT_DIR/sources/logger.sh"

export bower_command

if [[ -x $(cd "$SCRIPT_DIR/../.."; npm bin)/bower ]]
  then bower_command=$(cd "$SCRIPT_DIR/../.."; npm bin)/bower
elif hash bower 2>/dev/null
  then bower_command=bower
else
  fatal "Bower was not found!"
fi

info "Installing npm and bower modules"
info "-------------------------------"

export counter
export length

info "Installing npm modules for servers"
info "-------------------------------"
counter=1
length=${#dir_tab[@]}
for server_path in "${dir_tab[@]}"
do
  (
    cd "$DEV_DIR/$server_path" || exit
    info "[$counter/$length] - Installation in the directory $DEV_DIR/$server_path"
    npm i
  )
  ((counter++))
done

info "-------------------------------"
info "Installing npm modules for modules"
info "-------------------------------"
counter=1
length=${#dir_module_path[@]}
for module_path in "${dir_module_path[@]}"
do
  (
    cd "$DEV_DIR/$module_path" || exit
    info "[$counter/$length] - Installation in the directory $DEV_DIR/$module_path"
    npm i
  )
  ((counter++))
done

info "-------------------------------"
info "Installing npm and bower modules for front-end apps"
info "-------------------------------"
counter=1
length=${#dir_front_path[@]}
for front_app_path in "${dir_front_path[@]}"
do
  (
    cd "$DEV_DIR/$front_app_path" || exit
    info "[$counter/$length] - Installation in the directory $DEV_DIR/$front_app_path"
    npm i
    $bower_command i
  )
  ((counter++))
done
