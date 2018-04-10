#!/bin/bash

########################################################################################################
# File      : ./scripts/purge-modules.sh                                                               #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to remove the different dependancies in node_modules/ and bower_components/       #
#             directories                                                                              #
# Version   : 1.1.0                                                                                    #
########################################################################################################


# Root Id
ROOT_UID=0

#Check if user is not root
if [ $UID == $ROOT_UID ]; then
	echo -e "[-]Error : User must NOT be root to delete nodejs and bower modules."
	exit 0
fi

SCRIPT_DIR=$(dirname "$0")
source $SCRIPT_DIR"/sources/sources.sh"
CURRENT_DIR=`pwd`


echo "-------------------------------"
for i in ${!dir_tab[@]};
do
	cd $DEV_DIR
	cd ${dir_tab[i]};
	CHECK=`ls node_modules 2>/dev/null | wc -l`
	if [ $CHECK -ne 0 ]
	then
		echo "Remove node_modules/ in the directory ${dir_tab[i]}"
		rm -r node_modules/
	fi
	echo "-------------------------------"
	cd $CURRENT_DIR
done

for j in ${!dir_module_path[@]};
do
	cd $DEV_DIR
	cd ${dir_module_path[j]};
	CHECK=`ls node_modules 2>/dev/null | wc -l`
	if [ $CHECK -ne 0 ]
	then
		echo "Remove node_modules/ in the directory ${dir_module_path[j]}"
		rm -r node_modules/
	fi
	echo "-------------------------------"
	cd $CURRENT_DIR
done

for k in ${!dir_front_path[@]};
do
	cd $DEV_DIR
	cd ${dir_front_path[k]};
	CHECK=`ls node_modules 2>/dev/null | wc -l`
	if [ $CHECK -ne 0 ]
	then
		echo "Remove node_modules/ in the directory ${dir_front_path[k]}"
		rm -r node_modules/
	fi
	echo ""
	CHECK=`ls bower_components 2>/dev/null | wc -l`
	if [ $CHECK -ne 0 ]
	then
		echo "Remove bower_components/ in the directory ${dir_front_path[k]}"
		rm -r bower_components/
	fi
	echo "-------------------------------"
	cd $CURRENT_DIR
done

