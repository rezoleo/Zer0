#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

########################################################################################################
# File      : ./scripts/create-log-dir.sh                                                              #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com), Nymous                                               #
# Function  : Script to create the log directory for each NodeJS server                                #
# Version   : 1.1.0                                                                                    #
########################################################################################################


SCRIPT_DIR=$(dirname "$0")
# shellcheck source=sources/sources.sh
source "$SCRIPT_DIR/sources/sources.sh"
# shellcheck source=sources/logger.sh
source "$SCRIPT_DIR/sources/logger.sh"

info "Creating logs directories"
info "-------------------------------"
for log_dir in "${dir_tab[@]}";
do
  (
    cd "$DEV_DIR/$log_dir" || exit
    info "Creation in the directory $log_dir"
    mkdir -p log/
    info "-------------------------------"
  )
done
