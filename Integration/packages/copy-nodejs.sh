#!/bin/bash

########################################################################################################
# File      : ./copy-nodejs.sh                                                                         #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to copy NodeJS sources from Development directory                                 #
# Version   : 1.1.0                                                                                    #
#             The script was tested on Ubuntu 14.04                                                    #
########################################################################################################


# Root Id
ROOT_UID=0

#Check if user is not root
if [ $UID == $ROOT_UID ]; then
	echo -e "[-]Error : User must NOT be root to copy NodeJs files."
	exit 0
fi

SCRIPT_DIR=$(dirname "$0")
source $SCRIPT_DIR"/../../Utils/scripts/sources/sources.sh"
CURRENT_DIR=`pwd`

########################################################################################################
echo "-------------------------------"
echo "[+] Starting copying the NodeJS file from Development to Integration directory"
echo "-------------------------------"

cd $DEV_DIR/

function copy_dir {
	src_dir_auxi=$1
	dest_dir_auxi=$2

	for name in `ls "$src_dir_auxi"`
	do
		word=`ls "$src_dir_auxi/$name" -la | head -n 1 | awk '{}{print $1}'`
		letter=`echo $word | cut -c1`

		if [ "$letter" = "-" ]
		then
			# simple file
			cp "$src_dir_auxi/$name" "$dest_dir_auxi/"
		elif [ "$word" = "total" ]
		then
			# directory
			cp -r "$src_dir_auxi/$name" "$dest_dir_auxi/"
		elif [ "$letter" = "l" ]
		then
			# symbolic link
			link_path=`ls "$src_dir_auxi/$name" -la | head -n 1 | awk '{}{ print $11 }'`
			if [ "$name" = "certificates" ]
			then
				cp -rf "$src_dir_auxi/$name/" "$dest_dir_auxi/"
			else
				cp -rf "$src_dir_auxi/$name/../$link_path/" "$dest_dir_auxi/"
			fi
		fi
	done
}

########################################################################################################
echo "-------------------------------"
echo "[+] Preparing the NodeJS modules"
echo "-------------------------------"

dest_dir="$INT_DIR/packages/Modules/Module_ProjectZer0/usr/local/lib/node_modules/"

echo " ==>Create the directories (if they are not already created)"
mkdir -p "$dest_dir/"

for i in ${!dir_module_path[@]};
do
	echo " ==>Process the "${dir_module_name[i]}" module"
	echo " ====>Removing the old files"
	rm -rf "$dest_dir/${dir_module_name[i]}"

	echo " ====>Copying the files"
	cp -r "$DEV_DIR/${dir_module_path[i]}" "$dest_dir/${dir_module_name[i]}"

	echo ""
done

########################################################################################################
echo "-------------------------------"
echo "[+] Preparing the NodeJS services"
echo "-------------------------------"

for path in ${dir_service_path[@]};
do
	first_dir=`echo $path | awk -F/ '{print $1}'`
	serv_name=`echo $path | awk -F/ '{print $(NF-1)}'`
	echo " ==>Process the "$serv_name" service"

	src_dir="$DEV_DIR/$path"
	dest_dir="$INT_DIR/packages/Services/$first_dir/home/serv_$serv_name/service"

	echo " ====>Create the directories (if they are not already created)"
	mkdir -p "$dest_dir/"

	echo " ====>Removing the old files"
	rm -rf "$dest_dir/"*
	rm -f "$dest_dir/"*

	echo " ====>Copying the files"
	copy_dir $src_dir $dest_dir

	echo " ====>Removing the useless files in certificates directory"
	rm -f "$dest_dir/certificates/keystore.jks"
	if [ "$serv_name" = "passwordreset" ]
	then
		cd "$dest_dir/certificates/distant"
		ls | grep -v auth | grep -v mail | xargs rm
		cd $DEV_DIR/
	elif [ "$serv_name" = "provider" ]
	then
		cd "$dest_dir/certificates/distant"
		ls | grep -v alert | grep -v auth | grep -v card | grep -v contributor | grep -v group | grep -v people | grep -v picture | xargs rm
		cd $DEV_DIR/
	else
		rm -rf "$dest_dir/certificates/distant"
	fi

	echo " ====>Removing the log files"
	rm -f "$dest_dir/log/"*

	if [ "$serv_name" = "picture" ]
	then
		echo " ====>Removing the picture files"
		rm -f "$dest_dir/front/public/JunitTests/"*
	fi

	echo ""
done

########################################################################################################
echo "-------------------------------"
echo "[+] Preparing the NodeJS applications"
echo "-------------------------------"

for path in ${dir_application_path[@]};
do
	first_dir=`echo $path | awk -F/ '{print $1}'`
	app_name=`echo $path | awk -F/ '{print $(NF-1)}'`
	echo " ==>Process the "$app_name" application"

	src_dir="$DEV_DIR/$path"
	dest_dir="$INT_DIR/packages/Applications/$first_dir/home/app_$app_name/application"

	echo " ====>Create the directories (if they are not already created)"
	mkdir -p "$dest_dir/"

	echo " ====>Removing the old files"
	rm -rf "$dest_dir/"*
	rm -f "$dest_dir/"*

	echo " ====>Copying the files"
	copy_dir $src_dir $dest_dir

	echo " ====>Removing the useless files in certificates directory"
	rm -f "$dest_dir/certificates/keystore.jks"
	if [ "$app_name" = "administrator" ]
	then
		cd "$dest_dir/certificates/distant"
		ls | grep -v alert | grep -v auth | grep -v card | grep -v contributor | grep -v group | grep -v people | grep -v picture | xargs rm
		cd $DEV_DIR/
	elif [ "$app_name" = "checker" ]
	then
		cd "$dest_dir/certificates/distant"
		ls | grep -v provider | xargs rm
		cd $DEV_DIR/
	else
		rm -rf "$dest_dir/certificates/distant"
	fi

	echo " ====>Removing the log files"
	rm -f "$dest_dir/log/"*

	echo " ====>Removing the module components used for compilation"
	rm -rf "$dest_dir/front/bower_components"
	rm -rf "$dest_dir/front/node_modules"

	echo ""
done

########################################################################################################
cd $CURRENT_DIR
