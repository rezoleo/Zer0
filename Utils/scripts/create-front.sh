#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

########################################################################################################
# File      : ./scripts/create-front.sh                                                                #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com), Nymous                                               #
# Function  : Script to compile the front pages in the different applications or services              #
# Version   : 1.1.0                                                                                    #
########################################################################################################


SCRIPT_DIR=$(dirname "$0")
# shellcheck source=sources/sources.sh
source "$SCRIPT_DIR/sources/sources.sh"
# shellcheck source=sources/logger.sh
source "$SCRIPT_DIR/sources/logger.sh"

info "Compiling front-end apps"
info "-------------------------------"
for service_path in "${dir_tab[@]}"
do
  (
    cd "$DEV_DIR/$service_path" || exit
    if [[ -x front/scripts/compile.sh ]]
    then
      info "Creation in the directory $DEV_DIR/$service_path"
      cd front/ || exit
      ./scripts/compile.sh
      info "-------------------------------"
    fi
  )
done
