#!/usr/bin/env python

import datetime
import platform
import serial
import serial.tools.list_ports
import sys
from time import sleep
from threading import Thread

#Definition of the global variables
global serial_port, serial_baud, json_dir, start_date, signal, seq

# Global variables
serial_port = ""
serial_baud = 9600

json_dir = ""

seq = 0
signal = ""
start_date = datetime.datetime.now().isoformat()
version = "1.0.0"


# Function defined to say on which system the Python program is started
def which_system():
    return platform.uname()[0]

# Function defined to say which port the Python program must listen
def which_serial_port():
    system = which_system()
    if system=="Linux":		# For Linux
        return "/dev/ttyACM0"
    elif system=="Windows":	# For Windows
        return "COM3"
    elif system=="Darwin":	# For MacOS
        return "/dev/tty.usbserial-A9007UX1"
    else :			# For anything else
        return "/dev/ttyUSB0"

# Function designed to return the signal delivered by the Arduino card and an autoincrement number
def json_signal():
    global seq, signal
    output = "{\"seq\": %d, \"signal\": %s}" % (seq, signal)
    return output

# Function designed to return the system information
def json_system_info():
    global version, start_date, serial_port
    output = "{\"version\": \"%s\", \"start_date\": \"%s\", \"serial_port\": \"%s\", \"system\": \"%s\"}" % (version, start_date, serial_port, which_system())
    return output

# Function designed to write a file
def write_file(path, content):
    file = open(path, "w")
    file.write(content)
    file.close()

# Main code source started
if __name__ == '__main__':
    print "[+] Starting RX/TX listener"
    serial_port= which_serial_port()
    if len(sys.argv)>=2:
        if sys.argv[1]!="--check":
            json_dir = sys.argv[1]
    else:
        sys.stderr.write("[-] Error : No argument for JSON directory\n")
        print "[+] Stopping RX/TX listener"
        sys.exit()
    ser = serial.Serial(serial_port, serial_baud)
    if json_dir != "":
        write_file(json_dir+'/system.json', json_system_info())
        while True:
            auxi = ser.readline()
            index=auxi.find("\n")-1
            signal = ser.readline()[index:-2]
            if (len(signal)>0):
                print 'Signal '+str(seq)+' catched'
                write_file(json_dir+'/signal.json', json_signal())
                seq=seq+1
