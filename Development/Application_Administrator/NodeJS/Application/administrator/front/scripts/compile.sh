#!/bin/bash

########################################################################################################
# File      : ./scripts/compile.sh                                                                     #
# Author(s) : Shogi31 (shogi31@gmail.com)                                                              #
# Function  : Script to compile the front pages                                                        #
# Version   : 1.0.0                                                                                    #
########################################################################################################


rm -rf _public
"$(npm bin)/brunch" build --production

