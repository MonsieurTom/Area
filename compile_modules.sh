#!/bin/sh

tmppath=$PWD
mkdir Modules
cd src/main/Modules
mvn package
mv target/Module*jar $tmppath/Modules
cd $tmppath
