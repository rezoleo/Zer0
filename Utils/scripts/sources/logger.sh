#!/bin/bash

# Basic logger based on https://dev.to/thiht/shell-scripts-matter

print_date() {
  date '+%d-%m-%Y %H:%M:%S'
}

readonly LOG_FILE="/tmp/$(basename "$0").log"
info()    { echo "$(print_date) [INFO]    $*" | tee -a "$LOG_FILE" >&2 ; }
warning() { echo "$(print_date) [WARNING] $*" | tee -a "$LOG_FILE" >&2 ; }
error()   { echo "$(print_date) [ERROR]   $*" | tee -a "$LOG_FILE" >&2 ; }
fatal()   { echo "$(print_date) [FATAL]   $*" | tee -a "$LOG_FILE" >&2 ; exit 1 ; }
