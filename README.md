# Project Zer0

By [Zidmann](mailto:emmanuel.zidel@gmail.com) :bow:

## Description

Project Zer0 is a project created by engineering students of l'Ecole Centrale de Lille.

It consists in developing a repository of information shared by several applications or websites used by the students of the engineering school.
The repository contains information about students (firstname, lastname, age, photo identification, ...), about the different associations, clubs or commissions (members, managers, ...) and about the student cards (ID, ...).

This project also provides an identification system.

Project Zer0 is the touchstone of the projects belonged to RezoLution : they rely on the information provided by this repository and use the identification system.

### Cloning the project

When you clone the project, **use a user different from 'root'**.

The NodeJS servers are restricted and **can't run in 'root'** user.

### After cloning the project

* In the case you are behind a proxy, your proxy settings must be updated.

Note : You have to type 'HTTP_PROXY' (in capital letter), not 'http_proxy' (in small letter), otherwise the bower installation will fail.

Moreover, the proxy settings must be typed in the same console window that the installation scripts; if you close the console window you will have to retype the proxy settings commands.

```bash
export HTTP_PROXY="http://<proxy_adress>:<proxy_port>"
export HTTPS_PROXY="http://<proxy_adress>:<proxy_port>"
npm config set proxy http://<proxy_adress>:<proxy_port>
npm config set https-proxy http://<proxy_adress>:<proxy_port>
```

* Update your Linux version and install the different program necessary.

Note : This script **must be launched with 'root'** user.

```bash
/opt/centrale-datacore/Development/Utils/scripts/dependancy-installation.sh
```

* Create a symbolic link to the project
```bash
ln -s <path_of_the_git_project> /opt/centrale-datacore;
```

* Create the log directory and install the NodeJS dependancies

Note :
Some error messages can appear during the execution of install-modules.sh
Moreover, sometimes the install-module freezes with a **'root' user, then do not use it**.

```bash
/opt/centrale-datacore/Development/Utils/scripts/create-jar-dir.sh
/opt/centrale-datacore/Development/Utils/scripts/create-log-dir.sh
/opt/centrale-datacore/Development/Utils/scripts/create-module-links.sh
/opt/centrale-datacore/Development/Utils/scripts/install-modules.sh
/opt/centrale-datacore/Development/Utils/scripts/create-front.sh
```

Moreover, during the module installation choose :

- for 'angular.js' choose "1) angular#1.3.x which resolved to 1.3.17 and is required by angular-material-icons#0.4.0"

- for 'moment.js' choose "3) moment#>=2.8.0 <2.11.0 which resolved to 2.9.0 and is required by angular-moment#0.10.3"


### Before a test

* Prepare the data in Mongo databases for unit test suites
! WARNING ! The bases used by the services and the applications are cleared by this script
```bash
/opt/centrale-datacore/Development/Utils/scripts/prepare-mongo.sh
```

### To manage the NodeJS servers

* Start all the NodeJS servers
```bash
/opt/centrale-datacore/Development/Utils/scripts/start-servers.sh
```

* Restart all the NodeJS servers
```bash
/opt/centrale-datacore/Development/Utils/scripts/restart-servers.sh
```

* Stop all the NodeJS servers
```bash
/opt/centrale-datacore/Development/Utils/scripts/stop-servers.sh
```

### To work on the Java sources and the JUnit test cases
Select all the Java project directories in one time :
  * Open Eclipse IDE 
  * Click on 'File->Import'
  * Choose 'General>Existing Projects into Workspace'
  * Define '/opt/centrale-datacore/Development' as root directory 
  * Click on 'Finish' button

### To generate the final files (JAR and DEB packages)

The final DEB packages will be in the directory Integration/packages/All/DEB.

The final JAR librairies will be in the directory Integration/packages/All/JAR.

* Copy the NodeJS codes from Development to Integration branch (before compiling)

Note : This script doesn't copy the tools yet.

```bash
/opt/centrale-datacore/Integration/packages/copy-nodejs.sh
```

* Compile the DEB packages (after copying the NodeJS codes to Integration branch)

Note : This script **must be launched with root** user.

```bash
/opt/centrale-datacore/Integration/packages/compile-deb.sh
```

* Copy the JAR packages from the Development branch to Integration branch
```bash
/opt/centrale-datacore/Integration/packages/copy-jar.sh
```


### Something else useful

* To create new (self-signed) SSL certificate
```bash
/opt/centrale-datacore/Development/Utils/scripts/generate-ssl.sh
```

* To purge log directories
```bash
/opt/centrale-datacore/Development/Utils/scripts/purge-log.sh
```

* To purge the database
```bash
/opt/centrale-datacore/Development/Utils/scripts/prepare-mongo.sh
```

* To remove the dependancies in node_modules/ and bower_components/
```bash
/opt/centrale-datacore/Development/Utils/scripts/purge-modules.sh
```

* To extract the versions configured for npm and bower modules, to get the version currently used
```bash
/opt/centrale-datacore/Development/Utils/scripts/get-version-infos.sh
```

* To check if all the dependancies are installed and enabled
```bash
/opt/centrale-datacore/Development/Utils/scripts/check-dependancies.sh
```

## Components

Each application relies on several services and modules.
Each service relies on modules and also services.

The communication between an application and a service or between the two services is based on HTTP.

## Dependancies

* To install the different DEBIAN packages of the project, you need :

| Component | Category | Use in the project |
| ---- | ---- | ---- |
| bash | Environment | To execute scripts |
| nano | Text editor | To open the configuration files during installation steps |
| openssl | Security | To generate the SSL certificate files |
| nodejs | Server | To launch the servers |
| pm2 | Process manager | To manage the NodeJs servers |
| mongodb | Database | To store information |
| python | | Used for Arduino card listener (prototype) |
| arduino | | Used for Arduino card listener (prototype) |
| nginx | | Used for Arduino card listener (prototype) |


* To develop on the project, you need those above and those beside :

| Component | Category | Use in the project |
| ---- | ---- | ---- |
| bower | Package manager | To manage dependencies and generate web pages on front side |
| npm | Process manager | To manage dependencies of back side |


## Security

The security of the system relies on several points.

| Category | Description |
| ---- | ---- |
| Authentication | SSL certificates confirm client and server identities |
| Encryption | SSL public and private keys are used to make the information unreadable for external users|
| Flow control | Bruteforce protection is implemented by the FloodProtection module |
| Network | Clients are filtered by their IP address |
| Password hashing | A password broadcasted between servers is always hashed (with SHA512) and is also hashed when it is stored in the database (with bcrypt)|
| Information filter | HTTP Headers are filtered by Helmet module and passwords (or their hash values) are never shown in the responses|
| Permission management | JWT tokens are used to define permissions of one client|
| Temporary files | Temporary files of the Picture service are purged after each request|

* Helmet module - headers
  * X-Frame-Options : set to "deny" to prevent from clickjacking attacks mitigating when the server responses are included in a frame like <frame/> <iframe/> or <object/>
  * X-Powered-By : removed to make it slightly harder for attackers see what potentially-vulnerable technology powers your site
  * X-Download-Options : set to prevent Internet Explorer from executing downloads in your site’s context
  * X-Content-Type-Options : set to "nosniff" to prevent browsers from trying to guess (“sniff”) the MIME type (it can have security implications)
  * X-XSS-Protection : prevent reflected XSS attacks and some security problems for old IE versions
  * Strict-Transport-Security : set to keep the client on HTTPS connection when it is opened to the server

* Default password :
  * Value = Password1 
  * hash512->bcrypt = $2a$14$kgXx7D0MFRu.CWU26EWyJujtk7vW9XnctJW8I23IQF9nXjDENEV5W

## Contributors

* [Emmanuel ZIDEL-CAUFFET](mailto:emmanuel.zidel@gmail.com) :bow:
* [Hugo LEHMANN](mailto:shogi31@gmail.com) :bow:
* [Thomas GAUDIN](mailto:t.goudine@gmail.com) :bow:
* [Tarik MEGZARI](mailto:tarikmegzari@gmail.com) :bow: 
