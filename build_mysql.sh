#!/usr/bin/env sh

cd docker-database-execution/

sudo docker network rm default_network
sudo docker network create default_network --subnet=172.19.0.0/16 --gateway=172.19.0.1
sudo docker-compose down
sudo docker-compose up