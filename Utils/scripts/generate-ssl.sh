#!/bin/bash

########################################################################################################
# File      : ./scripts/generate-ssl.sh                                                                #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to generate SSL certificate and keystore in Development/Utils/ssl/ directory      #
# Version   : 1.1.0                                                                                    #
########################################################################################################


# Root Id
ROOT_UID=0

#Check if user is not root
if [ $UID == $ROOT_UID ]; then
	echo -e "[-]Error : User must NOT be root to generate SSL certificates."
	exit 0
fi

CURRENT_DIR=`pwd`
SSL_DIR=$(dirname "$0")"/../ssl"

KEY_FILE="server-key.pem"
PUBLIC_CERT="server-cert.pem"
PRIVATE_CERT="server-csr.pem"
KEYSTORE="keystore.jks"
LIFESPAN=365

#Password of the keystore
PASSWORD="password"


# Go to SSL directory
cd $SSL_DIR;


# Generate the key
echo "[+] Generation of the key file"
openssl genrsa -out $KEY_FILE 1024

# Generate the private certificate
echo "[+] Generation of the private certificate"
openssl req -new -key $KEY_FILE -out $PRIVATE_CERT -subj "/C=SW/ST=Somewhere/L=Somewhere/O=SW/CN=localhost"

# Generate the public certificate
echo "[+] Generation of the public certificate"
openssl x509 -req -days $LIFESPAN -in $PRIVATE_CERT -signkey $KEY_FILE -out $PUBLIC_CERT

# Generate the keystore
if [ -f $KEYSTORE ]
then
	echo "[+] Remove the previous Java keystore"
	rm $KEYSTORE;
fi

echo "[+] Create the Java keystore"
keytool -import -trustcacerts -alias selfsigned -file $PUBLIC_CERT -keystore $KEYSTORE -noprompt -validity $LIFESPAN -storepass $PASSWORD


# Go to previous directory
cd $CURRENT_DIR;

