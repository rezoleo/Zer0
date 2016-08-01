/*
 * Copyright 2015 Emmanuel ZIDEL-CAUFFET
 *
 * This class is used in a project designed by some Ecole Centrale de Lille students.
 * This program is distributed in the hope that it will be useful.
 * 
 * It is a free code: you can redistribute it and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either Version 3 of the License.
 *
 * However the source code is distributed without any warranty
 * See the GNU General Public License for more details.
 *
 */

/**************************************************************************/
/*! 
    @file     : readMifare.pde
    @author   : Zidmann (emmanuel.zidel@gmail.com)
    @license  : GNU GENERAL PUBLIC LICENSE v3 (see LICENSE)
    @function : This programs makes a card with a NFC shield send a JSON to a RX/TX listener
    @version  : 1.0.0
*/
/**************************************************************************/

#include <Adafruit_NFCShield_I2C.h>
#include <Wire.h>

#define IRQ       (2)
#define RESET     (3)      // Not connected by default on the NFC Shield
#define DATA_RATE (9600)   // Rate 

#define VERSION   "1.0.0"  // Version of the program

#define DELAY     900      // Minimum time (in ms) to recognize the same card

Adafruit_NFCShield_I2C nfc(IRQ, RESET);


uint8_t uid[]     = { 0, 0, 0, 0, 0, 0, 0 };  // Buffer to store the returned UID
uint8_t uidLength;                            // Length of the UID (4 or 7 bytes depending on ISO14443A card type)

uint8_t saveUid[] = { 0, 0, 0, 0, 0, 0, 0 };  // Buffer to store the last detected UID
uint8_t saveLength;                           // Length of the last detected UID

unsigned long time;

void setup(void) {
    Serial.begin(DATA_RATE);

    nfc.begin();
    uint32_t versiondata = nfc.getFirmwareVersion();
    // Send that the card is in error
    if (!versiondata) {
        Serial.println("");
        Serial.print("{\"event\":\"starting\", \"status\":\"errored\", \"message\":\"Didn't find PN53x board\", \"version\":\"");
        Serial.print(VERSION);
        Serial.println("\"}");
        while (1); // halt
    }

    // configure board to read RFID tags
    nfc.SAMConfig();

    // Send that the card is ready
    Serial.println("");
    Serial.print("{\"event\":\"starting\", \"status\":\"running\", \"message\":\"");
    Serial.print("Chip PN5 ("); Serial.print((versiondata>>24) & 0xFF, HEX); 
    Serial.print(") & Firmware version ("); Serial.print((versiondata>>16) & 0xFF, DEC); 
    Serial.print("."); Serial.print((versiondata>>8) & 0xFF, DEC);
    Serial.print(")\", \"version\":\""); Serial.print(VERSION); Serial.println("\"}");
}

void loop(void) {
    time = millis();
    uint8_t success    = nfc.readPassiveTargetID(PN532_MIFARE_ISO14443A, uid, &uidLength);
    unsigned long diff = millis()-time;

    if(success && (!compareUid(uid, uidLength, saveUid, saveLength) || diff>=DELAY)){
        // Send the UID information
        Serial.println("");
        Serial.print("{\"event\":\"reading\", \"status\":\"running\", \"message\":\"");
        printDecodedUid(uid, uidLength);
        Serial.print("\", \"version\":\""); Serial.print(VERSION); Serial.println("\"}");

        // Save the last detected UID
        saveLength=uidLength;
        for(int i=0; i<uidLength; i++){
            saveUid[i]=uid[i];
        }
        
        delay(DELAY);
    }
}

// Function to print the UID code in hexadecimal form
void printDecodedUid(uint8_t uid[], uint8_t uidLength){
    char str[]={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};  // Buffer to store the different letter of an hexadecimal code

    for(int i=0; i<uidLength; i++){
         int val = uid[i];
         int div = val/16;
         int rst = val%16;

         // Print the hexa code
         Serial.print(str[div]);
         Serial.print(str[rst]);
    }    
}

// Function to compare two UID
boolean compareUid(uint8_t uid1[], uint8_t length1, uint8_t uid2[], uint8_t length2){
    if(length1!=length2){
        return false;
    }
    for(int i=0; i<=length1; i++){
        if(uid1[i]!=uid2[i]){
            return false;
        }
    }
    return true;
}
