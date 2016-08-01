#!/bin/bash

########################################################################################################
# File      : ./scripts/install.sh                                                                     #
# Author(s) : Shogi31 (shogi31@gmail.com)                                                              #
# Function  : Script to install the different modules for npm and bower                                #
# Version   : 1.0.0                                                                                    #
########################################################################################################


rm -rf node_modules bower_components
npm install
./node_modules/.bin/bower install

