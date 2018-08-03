#!/bin/bash
echo begin init taroco...

COMPOSE_FILE=./taroco-docs/docker/docker-compose.yml
JAR_DIR=./taroco-docs/docker/jar/

echo stop&remove old docker-compose containers
if docker-compose -f ${COMPOSE_FILE} ps
    then
        docker-compose -f ${COMPOSE_FILE} stop
        docker-compose -f ${COMPOSE_FILE} rm
fi

echo build jar
mvn clean package -Dmaven.test.skip=true

echo move jar to ${JAR_DIR}
if [ ! -d ${JAR_DIR}]
    then
        mkdir ${JAR_DIR}
fi
copy ./taroco-cloud/cloud-registry/target/cloud-registry*.jar ${JAR_DIR}
copy ./taroco-cloud/cloud-config/target/cloud-config*.jar ${JAR_DIR}
copy ./cloud-oauth2/authentication-server/target/authentication-server*.jar ${JAR_DIR}
copy ./taroco-upms-service/target/taroco-upms-service*.jar ${JAR_DIR}
copy ./taroco-gateway/target/taroco-gateway*.jar ${JAR_DIR}

echo run docker-compose up

