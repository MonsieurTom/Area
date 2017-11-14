> (realisation time : 1week | Team of 5 people)

# area
========

## Introduction
area is a webapp written in java which enables numerous common actions on the web by interconnecting web services

## Installation
### Requirements
- Java JDK 8 and Java JRE 8
- maven
- An internet connection for the dependencies
- tomcat or another web server

### Installation
First install mariadb (if you don't have it) and setup the database :
- Go to the SQL folder in the project directory
- Follow the instructions of the README situated there

Then you need to compile the available modules, either by launching the following script (recommended) 
- ./compile_modules.sh
Or if you want to do it yourself (not recommended)
- Starting from the root or the project folder go to /src/main/Modules
- Compile the modules : `mvn package`
- Move the modules from the /src/main/Modules/target folder to the /Modules from where the modules are loaded

If the server was already launched, you need to restart it for the modules to be loaded.
You can manually add other modules to the /Modules folder

## Usage :
You need to launch the server at the root of the project folder (example with tomcat) :
- `mvn tomcat7:run`
or you can compile the project and launch it with your own server(will compile but have problems with url root, url root should be configured manually on tomcat, etc...) :
- `mvn package`
- move the .war file you made from the target folder to the root of you webserver and launch your webserver as you would otherwise

## Creating other modules:
You can create other modules that will work with existing modules and with the webapp in general, they just need to implement the IModuleAction interface for the action modules and the IModuleReaction interface for the reaction modules. One module can implement both interfaces and will be loaded as such. You can find these ressources in the /CreateModules folder. When you have compiled your(s) module(s), place them in the /Modules folder, restart your server and voila, they should be working !

## Usage through the web browser
### For the admin :
The default admin account is admin@admin.admin and it's password is, you guessed it, admin.
The admin can administrate the users accounts, delete theme if need be, and send a newletter through the panel
### For the user :
The user can create an account with an email and a password, and he can creat panels to link actions and reactions together through an ergonomic and easy to use interface

## Credits
Victor KERN, Erwan GUIOMAR, Tom LENORMAND, Morgan CARON and Benjamin GUARIGLIA

## Troubleshooting
If you have an error after connecting with a user, showing a lot of errors, try restarting your mariadb service and checking the database is properly initialize (cf SQL/README)

## License
    Copyright © 2000 Benjamin Guariglia <benjamin.guariglia@epitech.eu>
    This work is free. You can redistribute it and/or modify it under the
    terms of the Do What The Fuck You Want To Public License, Version 2,
    as published by Sam Hocevar. See the COPYING file for more details.
