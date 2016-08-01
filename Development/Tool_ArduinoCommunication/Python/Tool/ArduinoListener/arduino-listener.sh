#!/bin/bash

################################################################################################################
# File      : ./arduino-listener.sh                                                                            #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                               #
# Function  : Script to start the python program which catches the serial JSON file sent by an Arduino card    #
# Version   : 1.0.0                                                                                            #
################################################################################################################


# Directory of the script
SCRIPT_DIR=`dirname "$0"`

# Information about the date for the log writting
NOW=$(date +"%Y-%m-%d")

# Paths for the log writting
LOG_DIR=$SCRIPT_DIR/log/$NOW
LOG_FILE=$LOG_DIR/$NOW.log
ERR_FILE=$LOG_DIR/$NOW.err
TMP_FILE="/tmp/arduino-listener.tmp"

# Minimum delay to wait before restarting the Python script
WAIT_TIME="1s"

# Path to the directory where the JSON files are stored
JSON_DIR=$SCRIPT_DIR/signal/

# Creation of the log directory if does not exist
mkdir -p $LOG_DIR;

# Starting the Arduino listener and writting inside the log file
echo "--------------------------------" >> $LOG_FILE

NOW_DETAIL=$(date +"%Y-%m-%d %Hh%Mm%Ss")
echo "["$NOW_DETAIL"][+] Starting the Arduino Listener" | tee -a $LOG_FILE

while true; do
	rm $JSON_DIR/*.json 2>/dev/null;

	# Check if the Arduino card is ready
	$SCRIPT_DIR/arduino-listener.py "--check" 1>/dev/null 2>$TMP_FILE
	NB_LINES=`wc -l $TMP_FILE | awk -F " " '{ print $1 }'`

	if [ $NB_LINES -eq "0" ]; then
		echo "--------------------------------" >> $ERR_FILE
		NOW_DETAIL=$(date +"%Y-%m-%d %Hh%Mm%Ss")
		echo "["$NOW_DETAIL"][+] Arduino Listener ready" | tee -a $LOG_FILE
		$SCRIPT_DIR/arduino-listener.py "$JSON_DIR" 1>>$LOG_FILE 2>>$ERR_FILE
		NOW_DETAIL=$(date +"%Y-%m-%d %Hh%Mm%Ss")
		echo "["$NOW_DETAIL"][+] Rebooting the Arduino Listener" | tee -a $LOG_FILE
	fi
	rm $JSON_DIR/*.json 2>/dev/null;

	sleep $WAIT_TIME
done
