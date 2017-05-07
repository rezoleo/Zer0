#!/bin/bash

########################################################################################################
# File      : ./scripts/watch.sh                                                                       #
# Author(s) : Shogi31 (shogi31@gmail.com)                                                              #
# Function  : Script to start brunch server                                                            #
# Version   : 1.0.0                                                                                    #
########################################################################################################


rm -rf _public
"$(npm bin)/brunch" watch --server

