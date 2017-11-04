#!/bin/bash

########################################################################################################
# File      : ./scripts/sources/sources.sh                                                             #
# Author(s) : Zidmann (emmanuel.zidel@gmail.com)                                                       #
# Function  : Script to define the NodeJS directories                                                  #
# Version   : 1.0.0                                                                                    #
########################################################################################################


# Information about the different NodeJS server used for testing the different components
dir_tab=(
     "Application_Administrator/NodeJS/Application/administrator/"
     "Application_Checker/NodeJS/Application/checker/"
     "Service_Alert/NodeJS/Service/alert/"
     "Service_Authentification/NodeJS/Service/authentification/"
     "Service_Card/NodeJS/Service/card/"
     "Service_Contributor/NodeJS/Service/contributor/"
     "Service_Group/NodeJS/Service/group/"
     "Service_Mail/NodeJS/Service/mail/"
     "Service_PasswordReset/NodeJS/Service/passwordreset/"
     "Service_People/NodeJS/Service/people/"
     "Service_Picture/NodeJS/Service/picture/"
     "Service_Provider/NodeJS/Service/provider/"
     "Module_ApplicationCore/UnitTests/NodeJS"
     "Module_WebServiceCore/UnitTests/NodeJS"
)

########################################################################################################
# Information about the different NodeJS modules
########################################################################################################

## Name of the modules
dir_module_name=(
     "applicationcore"
     "authorization"
     "cache"
     "core"
     "floodprotection"
     "token_generator"
     "toolbox"
     "webservicecore"
)

## Path of the modules
dir_module_path=(
     "Module_ApplicationCore/NodeJS/Module/applicationcore/"
     "Module_Authorization/NodeJS/Module/authorization/"
     "Module_Cache/NodeJS/Module/cache/"
     "Module_Core/NodeJS/Module/core/"
     "Module_FloodProtection/NodeJS/Module/floodprotection/"
     "Module_TokenGenerator/NodeJS/Module/token_generator/"
     "Module_ToolBox/NodeJS/Module/toolbox/"
     "Module_WebServiceCore/NodeJS/Module/webservicecore/"
)

########################################################################################################

## Path of the services
dir_service_path=(
     "Service_Alert/NodeJS/Service/alert/"
     "Service_Authentification/NodeJS/Service/authentification/"
     "Service_Card/NodeJS/Service/card/"
     "Service_Contributor/NodeJS/Service/contributor/"
     "Service_Group/NodeJS/Service/group/"
     "Service_Mail/NodeJS/Service/mail/"
     "Service_PasswordReset/NodeJS/Service/passwordreset/"
     "Service_People/NodeJS/Service/people/"
     "Service_Picture/NodeJS/Service/picture/"
     "Service_Provider/NodeJS/Service/provider/"
)

########################################################################################################

## Directory name of the applications
dir_application_path=(
     "Application_Administrator/NodeJS/Application/administrator/"
     "Application_Checker/NodeJS/Application/checker/"
)

########################################################################################################
## Path of the front pages which need to be compiled
dir_front_path=(
     "Application_Administrator/NodeJS/Application/administrator/front/"
)

########################################################################################################
## Project source directory paths
PROJECT_DIR="/opt/centrale-datacore"
DOC_DIR="$PROJECT_DIR/Documentation/"
DEV_DIR="$PROJECT_DIR/Development/"
INT_DIR="$PROJECT_DIR/Integration/"
TRC_DIR="$PROJECT_DIR/Trace/"
