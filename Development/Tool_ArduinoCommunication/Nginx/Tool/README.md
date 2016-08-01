# Nginx configuration for ArduinoCommunication

By [Zidmann](mailto:emmanuel.zidel@gmail.com) :bow: 

## Description

Nginx configuration file for reading the RFID student card.

## How does it work ?

An arduino card with an NFC module reads a student card, broadcasts a message by the serial port of the computer which is listened by the Python program.
This program catches the arduino messages and writes data in two files (signal.json and system.json) that can be accessed locally on the computer by an HTTP or HTTPS request thanks to nginx.
The javascript functions launched on the client browser can access to this data and know when a student card was read and what was its uid.

## Requirement

To use the redirection with the nginx HTTP/HTTPS server, you need to install it.

## Notes

It exists other solutions to read the JSON file produced by the ArduinoCommunication plugin.
You can also use Apache server for instance.

