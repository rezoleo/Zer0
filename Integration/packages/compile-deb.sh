#!/bin/bash

########################################################################################################
# File      : ./compile-deb.sh                                                                         #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to compile the differents sources in DEBIAN packages                              #
# Version   : 1.0.0                                                                                    #
#             The script was tested on Ubuntu 14.04                                                    #
########################################################################################################


# Root Id
ROOT_UID=0

#Check if user is root
if [ $UID != $ROOT_UID ]; then
	echo -e "[-]Error : User must be root to compile packages."
	exit 0
fi

SCRIPT_DIR=$(dirname "$0")
source $SCRIPT_DIR"/../../Utils/scripts/sources/sources.sh"
CURRENT_DIR=`pwd`

cd $INT_DIR/packages/


echo "-------------------------------"
echo "[+] Compilation of the debian packages"
echo "-------------------------------"

# Compilations for the applications
for rep in `ls -d Applications/*/`
do
	dpkg-deb --build "$rep"
done

# Compilations for the modules
for rep in `ls -d Modules/*/`
do
	dpkg-deb --build "$rep"
done

# Compilations for the services
for rep in `ls -d Services/*/`
do
	dpkg-deb --build "$rep"
done

# Compilations for the tools
for rep in `ls -d Tools/*/`
do
	dpkg-deb --build "$rep"
done

echo "-------------------------------"
echo "[+] Copy of the DEBIAN packages"
cp -f Applications/*.deb All/DEB/
cp -f Modules/*.deb      All/DEB/
cp -f Services/*.deb     All/DEB/
cp -f Tools/*.deb        All/DEB/

echo "-------------------------------"
cd $CURRENT_DIR
