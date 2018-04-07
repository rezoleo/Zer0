#!/bin/bash

########################################################################################################
# File      : ./scripts/get-version-infos.sh                                                           #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to get the versions requested in npm and bower configurations and what was used   #
# Version   : 1.1.0                                                                                    #
########################################################################################################


# Root Id
ROOT_UID=0

#Check if user is not root
if [ $UID == $ROOT_UID ]; then
	echo -e "[-]Error : User must NOT be root to get version details."
	exit 0
fi

SCRIPT_DIR=$(dirname "$0")
source $SCRIPT_DIR"/sources/sources.sh"

CONF_FILE=$TRC_DIR/"configured.$(date '+%Y%m%d_%H%M%S').csv"
USED_FILE=$TRC_DIR/"used.$(date '+%Y%m%d_%H%M%S').csv"
TMP_FILE="/tmp/centrale.datacore.$$"

echo "Creation du répertoire des traces ($TRC_DIR)"
mkdir -p "$TRC_DIR"

echo "Creation du fichier temporaire ($TMP_FILE)"
echo "" > "$TMP_FILE"
chmod a+x "$TMP_FILE"

echo "Creation des fichiers"
echo "TYPE;CONF_PATH;MODULE;VERSION" > "$CONF_FILE"
echo "TYPE;CONF_PATH;VERSION"        > "$USED_FILE"

echo "Extraction des dépendances dans les fichiers package.json du projet"
find /opt/centrale-datacore/ -name "package.json" 2>/dev/null | xargs grep "\"~" | sed "s/\"//g" | sed "s/,//g" | sed "s/:/;/g" | awk '{print "NPM;"$1""$2""$3""$4}'   | grep -v 'node_modules\|bower_components' >> "$CONF_FILE"

echo "Extraction des dépendances dans les fichiers bower.json du projet"
find /opt/centrale-datacore/ -name "bower.json"   2>/dev/null | xargs grep "\"~" | sed "s/\"//g" | sed "s/,//g" | sed "s/:/;/g" | awk '{print "BOWER;"$1""$2""$3""$4}' | grep -v 'node_modules\|bower_components' >> "$CONF_FILE"

echo "Extraction des version dans les modules npm"
cat "$CONF_FILE" | grep "^NPM"   | awk -F';' '{print "ls "$2}' | sed "s/package.json/node_modules\/*\/package.json/g" | sort -u >  "$TMP_FILE"
"$TMP_FILE" 2>/dev/null | xargs grep "\"version\"" | sed "s/\"//g" | sed "s/,//g" | sed "s/:/;/g" | awk '{print "NPM;"$1""$3}'              >> "$USED_FILE"

echo "Extraction des version dans les modules bower"
cat "$CONF_FILE" | grep "^BOWER"   | awk -F';' '{print "ls "$2}' | sed "s/bower.json/bower_components\/*\/bower.json/g" | sort -u >  "$TMP_FILE"
"$TMP_FILE" 2>/dev/null | xargs grep "\"version\"" | sed "s/\"//g" | sed "s/,//g" | sed "s/:/;/g" | awk '{print "BOWER;"$1""$3}'              >> "$USED_FILE"

echo ""
echo "Résultats extraction dans les fichiers :"
echo "$CONF_FILE"
echo "$USED_FILE"
echo ""
