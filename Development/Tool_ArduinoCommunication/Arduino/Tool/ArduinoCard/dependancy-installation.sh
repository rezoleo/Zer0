#!/bin/bash

########################################################################################################
# File      : ./dependancy-installation.sh                                                             #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to install arduino libraries on a machine                                        #
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

#Install Arduino libraries
apt-get install arduino;
