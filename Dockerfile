FROM openjdk:8-jdk-alpine as base

LABEL maintainer="fabien.sanglier@softwareaggov.com"

ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk/
ENV JAVA_OPTS=
ENV RETRY_INTERVAL=10
ENV JDBC_JAR_PATH=
ENV JDBC_CONNECT_USER=
ENV JDBC_CONNECT_PASSWORD=
ENV JDBC_CONNECTION_STRING=

RUN apk add --update \
    tini \
    && rm -rf /var/cache/apk/*

# copy code
COPY ./src/OracleJdbcTester.java /src/
COPY ./entrypoint.sh /src/

RUN chmod a+x /src/entrypoint.sh

# This is the current working directory
WORKDIR /src/

RUN javac OracleJdbcTester.java

ENTRYPOINT ["/sbin/tini", "--", "/src/entrypoint.sh"]