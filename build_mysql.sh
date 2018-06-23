#!/usr/bin/env sh

cd docker-database-execution/

docker container stop dockerdatabaseexecution_database_1
docker container rm dockerdatabaseexecution_database_1

docker network rm default_network
docker network create default_network --subnet=172.20.1.0/16 --gateway=172.20.1.1

docker-compose down
docker-compose up
