#!/bin/bash
set -euo pipefail
IFS=$'\n\t'

########################################################################################################
# File      : ./scripts/dependancy-installation.sh                                                     #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com), Nymous                                               #
# Function  : Script to install nodeJS, npm, pm2, bower and mongoDB on a machine                       #
# Version   : 1.1.0                                                                                    #
# Note      : The server can not be started without root rights                                        #
#             The script was tested on Ubuntu 14.04                                                    #
########################################################################################################

SCRIPT_DIR=$(dirname "$0")
# shellcheck source=sources/logger.sh
source "$SCRIPT_DIR/sources/logger.sh"

# Root Id
ROOT_UID=0

#Check if user is root
if [ $UID != $ROOT_UID ]; then
  fatal "User must be root to install dependancies"
fi

info "Update repositories"
apt-get update
# apt-get upgrade

info "Install mongodb, nodejs and npm"
apt-get install mongodb
apt-get install nodejs
apt-get install npm

info "Install PM2, a node process management"
npm install pm2 -g

info "Install bower, a package manager"
npm install bower -g

info "Start mongodb database"
/etc/init.d/mongodb start

info "Creation of the symlink 'nodejs'"
ln -s /usr/bin/nodejs /usr/local/bin/node

