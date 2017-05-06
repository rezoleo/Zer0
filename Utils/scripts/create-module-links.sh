#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

########################################################################################################
# File      : ./scripts/create-module-links.sh                                                         #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com), Nymous                                               #
# Function  : Script to create the links inside node_modules inside all NodeJS servers                 #
# Version   : 1.1.0                                                                                    #
########################################################################################################


SCRIPT_DIR=$(dirname "$0")
# shellcheck source=sources/sources.sh
source $SCRIPT_DIR"/sources/sources.sh"
# shellcheck source=sources/logger.sh
source "$SCRIPT_DIR/sources/logger.sh"

info "Linking modules"
(
  cd $DEV_DIR || exit
  info "Creating node_modules directory"
  mkdir -p node_modules/ 1>/dev/null 2>&1

  for module_path in "${dir_module_path[@]}";
  do
    module_name=$(basename "$module_path")
    if [[ -h node_modules/$module_name ]]
      then warning "Symbolic link $DEV_DIR/node_modules/$module_name already exists"
    else
      info "Creating link called $DEV_DIR/node_modules/$module_name pointing to $DEV_DIR/../$module_path"
      ln -s "../$module_path" "node_modules/$module_name" 1>/dev/null 2>&1
    fi
  done
)
