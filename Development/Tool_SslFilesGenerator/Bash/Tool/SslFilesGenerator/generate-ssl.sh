#!/bin/bash

########################################################################################################
# File      : ./generate-ssl.sh                                                                        #
# Author(s) : Denof, Zidmann (tarikmegzari@gmail.com, emmanuel.zidel@gmail.com)                        #
# Function  : Script to generate SSL certificates for the projects which belong to RezoLution program  #
# Version   : 1.0.0                                                                                    #
########################################################################################################

# Check if the user is connected in root
if [ "$(id -u)" != "0" ]
then
	echo "[-] The script must be run as root."
	exit 1
fi


# Put the right owner, group and permissions to the created certificates
user_list=($(awk -F':' '{ print $1}' /etc/passwd | grep 'serv_\|app_'|sort))
group_list=("applications" "services")
rslt=0

echo "============================================"
echo "[+] Selection of the destinated service user"
echo "== List of existing user =="

for ((i=0; i<${#user_list[@]}; i++))
do
	echo "$((i+1))) -> ${user_list[$i]}"
done
echo "[?] Choose a number from the list below or enter a custom USER : "
read USR

for ((i=0; i<${#user_list[@]}; i++))
do
	if [[ "$((i+1))" == "$USR" ]]
	then
		user=${user_list[$i]}
		rslt=1
		break
	fi
done

if [ $rslt -eq 0 ]
then
	user=$USR
fi

echo ""
echo "Selected user=$user"
if [ `getent passwd $user 2>/dev/null | wc -l` -eq 0 ]
then
	echo "(UNKNOWN USER)"
fi
echo ""

echo "============================================"
echo "[+] Selection of the group"
echo "== List of existing user =="

for ((i=0; i<${#group_list[@]}; i++))
do
	echo "$((i+1))) -> ${group_list[$i]}"
done
echo "[?] Choose a number from the list below or enter a custom GROUP : "
read GRP

rslt=0
for ((i=0; i<${#group_list[@]}; i++))
do
	if [[ "$((i+1))" == "$GRP" ]]
	then
		group=${group_list[$i]}
		rslt=1
		break
	fi
done

if [ $rslt -eq 0 ]
then
	group=$GRP
fi

echo ""
echo "Selected group=$group"
if [ `getent group $group 2>/dev/null | wc -l` -eq 0 ]
then
	echo "(UNKNOWN GROUP)"
fi
echo ""

echo "============================================"
echo "[+] Creation of the files"

KEY_FILE="server-key.pem"
PUBLIC_CERT="server-cert.pem"
PRIVATE_CERT="server-csr.pem"
PERIOD="365"

openssl genrsa -out $KEY_FILE 1024 -days $PERIOD
openssl req -new -batch -key $KEY_FILE -out $PRIVATE_CERT -days $PERIOD
openssl x509 -req -in $PRIVATE_CERT -signkey $KEY_FILE -out $PUBLIC_CERT -days $PERIOD

#Setting the right permissions for the created certificates
echo ""
echo "[+] Setting the user and the group of the files"
chown $user:$group $PUBLIC_CERT
chown $user:$group $PRIVATE_CERT
chown $user:$group $KEY_FILE

echo ""
echo "[+] Setting the permission of the files"
chmod 600 $PUBLIC_CERT
chmod 600 $PRIVATE_CERT
chmod 600 $KEY_FILE

