# continuous-jdbc-checker
A simple project that will check DB "liveness" via jdbc connectivity

Find image on [dockerhub](https://hub.docker.com/repository/docker/lanimall/continuous-jdbc-checker)

NOTE: Currently java code "hard-coded" for Oracle backends...Obviously not ideal...will improve and make more generic asap.

# Usage

Provide the following ENV vars:
 - RETRY_INTERVAL - The interval in seconds between jdbc requests (type: number, default: 10)
 - JDBC_JAR_PATH - The path to the JDBC implementation jar
 - JDBC_CONNECT_USER - The user to use to connect to the database
 - JDBC_CONNECT_PASSWORD - The password to use to connect to the database
 - JDBC_CONNECTION_STRING - The JDBC connection string (ie. for oracle that'd be something like: jdbc:oracle:thin:@//host:port/SID)

# Build the image

```
docker-compose build continuous-jdbc-checker
```

# Run using Docker-compose

```
docker-compose up -d
```