#!/bin/bash

########################################################################################################
# File      : ./bin/dependancy-installation.sh                                                         #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to install NodeJS, npm pm2 and MongoDB on a machine                               #
# Version   : 1.0.0                                                                                    #
# Note      : The server can not be started without root rights                                        #
#             The script was tested on Ubuntu 14.04                                                    #
########################################################################################################


# Root Id
ROOT_UID=0

#Check if user is root
if [ $UID != $ROOT_UID ]; then
	echo -e "[-]Error : User must be root to install dependancies."
	exit 0
fi

#Need to upgrade the computer
apt-get update;
apt-get upgrade;

#Install Mongodb and Nodejs
apt-get install mongodb;
apt-get install nodejs;
apt-get install npm;

#Install PM2 a node process management
npm install pm2 -g;

#Start Mongodb database
/etc/init.d/mongodb start;

#Creation of the command 'nodejs'
ln -s /usr/bin/nodejs /usr/local/bin/node;

