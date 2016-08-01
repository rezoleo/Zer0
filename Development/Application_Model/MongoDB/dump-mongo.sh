##########################################################################################################
## Script to save a Mongo database                                                                      ##
## Auteur      : Zidmann (zidmann@gmail.com)                                                            ##
## Date        : 12/10/2014                                                                             ##
## Version     : 0.0.1                                                                                  ##
##########################################################################################################

DATE=`date +%y-%m-%d-%Hh%Mm%Ss`
DATABASE='accessSystem'

echo "Starting backup of the mongo database $DATABASE the $DATE"

# Dumping the database into ./backup/$DATABASE/
mongodump -d $DATABASE -o backup > /dev/null

# Compress the files
tar -czPf backup/$DATABASE\_$DATE.tar.gz backup/$DATABASE/
echo "Backup finished"
