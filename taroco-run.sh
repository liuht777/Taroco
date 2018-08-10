#!/bin/bash
echo -- -- begin init taroco... -- --

COMPOSE_FILE=./taroco-docs/docker/docker-compose.yml
JAR_DIR=./taroco-docs/docker/jar

echo -- -- stop and remove old docker-compose containers -- --
if docker-compose -f ${COMPOSE_FILE} ps
    then
        docker-compose -f ${COMPOSE_FILE} stop
        docker-compose -f ${COMPOSE_FILE} rm --force
fi

echo -- -- building jar -- --
mvn clean package -Dmaven.test.skip=true

echo -- -- move jar to ${JAR_DIR} -- --
if [ ! -d ${JAR_DIR} ];then
   mkdir -p ${JAR_DIR}
fi

rm -rf ${JAR_DIR}/authentication-server*.jar
rm -rf ${JAR_DIR}/taroco-upms*.jar
rm -rf ${JAR_DIR}/cloud-admin*.jar
rm -rf ${JAR_DIR}/taroco-gateway*.jar

cp ./taroco-oauth2/authentication-server/target/authentication-server*.jar ${JAR_DIR}
cp ./taroco-upms/target/taroco-upms*.jar ${JAR_DIR}
cp ./taroco-cloud/cloud-admin/target/cloud-admin*.jar ${JAR_DIR}
cp ./taroco-gateway/target/taroco-gateway*.jar ${JAR_DIR}

echo -- -- run docker-compose up -- --
docker-compose -f ${COMPOSE_FILE} up -d --build

docker images|grep none|awk '{print $3 }'|xargs docker rmi
