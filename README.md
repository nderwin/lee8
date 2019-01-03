# lee8

[![Build Status](https://travis-ci.com/nderwin/lee8.svg?branch=develop)](https://travis-ci.com/nderwin/lee8)

Learning Java EE / Jakarta EE 8 / Microprofile

This project runs in Thorntail.  The project configuration file is specified on the command line similar to the following:

```
java -jar target/lee8-thorntail.jar -s target/project-defaults.yml -s target/datasource-defaults.yml
```

To run in a Docker container, use Docker Compose (which will also start up a PostGreSQL container):

```
docker-compose up
```

The REST endpoints are deployed under the `/resources` URL.

## Configuration

Since the project is also set up to run in Docker, the database credentials are specified in files located in the `dockersecrets`
directory.  These are not included in the repo, so these will need to be created after cloning:

`dockersecrets/postgres_username`

```
lee8
```

`dockersecrets/postgres_password`

```
lee8
```

`dockersecrets/datasource-defaults.yml`

```yaml
swarm:
  datasources:
    data-sources:
      lee8:
        driver-name: org.postgresql
        connection-url: jdbc:postgresql://database:5432/lee8db
        user-name: lee8
        password: lee8
```

