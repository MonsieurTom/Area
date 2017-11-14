#!/bin/sh

echo "do you want to launch the mariadb installation script ? [y/N]"
read yn
if [[ $yn == "y" ]] || [[ $yn == "Y" ]]
then
    which apt >/dev/null 2>&1
    if [ $? -eq 0 ]
    then
	packetmg="apt"
	sudo apt update
	sudo apt -y install mariadb-server
    fi

    which pacman > /dev/null 2>&1
    if [ $? -eq 0 ]
    then
	packetmg="pacman"
	sudo pacman -Syy
	sudo pacman -Syu mariadb --noconfirm
    fi

    which yum > /dev/null 2>&1
    if [ $? -eq 0 ]
    then
	packetmg="yum"
	sudo yum update
	sudo yum -y install mariadb-server
    fi

    which zypper > /dev/null 2>&1
    if [ $? -eq 0 ]
    then
	packetmg="zypper"
	sudo zypper update
	sudo zypper -n install mariadb
    fi

    sudo mysql_install_db --user=mysql --basedir=/usr --datadir=/var/lib/mysql
    sudo systemctl enable mariadb.service
    sudo systemctl restart mariadb.service
fi

echo "please set root password as 'root' for our program to work, or change the password in the WebMvcConfig.java file, the getDataSource function. Don't change the ip address though."

echo "do you want to launch the sql db installation script ?[y/N]"
read yn
[[ $yn != "y" ]] && [[ $yn != "Y" ]] && echo "Exiting..." && sudo -k && exit 0

sudo mysql_secure_installation
sudo mysql -h localhost -u root -proot -e "source script.sql"
sudo -k
