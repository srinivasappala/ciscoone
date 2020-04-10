#!/bin/bash

wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie"  http://download.oracle.com/otn-pub/java/jdk/8u151-b12/e758a0de34e24606bca991d704f6dcbf/jdk-8u151-linux-x64.tar.gz
mkdir -p /usr/local/java

echo "I am installing into : "
echo $(pwd)

mv jdk-8u151-linux-x64.tar.gz /usr/local/java
cd /usr/local/java
tar -xf jdk-8u151-linux-x64.tar.gz
echo "JAVA_HOME=/usr/local/java/jdk1.8.0_151" >> /etc/profile
echo "JRE_HOME=/usr/local/java/jdk1.8.0_151/jre" >> /etc/profile
echo "PATH=\$PATH:\$JAVA_HOME/bin:\$JRE_HOME/bin" >> /etc/profile
echo "export JAVA_HOME" >> /etc/profile
echo "export JAVA_HOME" >> /etc/profile
echo "export PATH" >> /etc/profile
sh /etc/profile
rm -rf jdk-8u151-linux-x64.tar.gz