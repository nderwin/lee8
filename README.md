# lee8

[![Build Status](https://travis-ci.com/nderwin/lee8.svg?branch=develop)](https://travis-ci.com/nderwin/lee8)

Learning Java EE / Jakarta EE 8 / Microprofile

This project runs in Thorntail, using a hollow jar.  This means that the JDBC driver (PostgreSQL) and
project configuration files need to be specified on the command line similar to the following:

```
java -Dthorntail.classpath=<path to JDBC jar>/postgresql-42.2.5.jar -jar target/lee8-hollow-thorntail.jar target/lee8.war -s ./src/main/resources/project-defaults.yml
```
