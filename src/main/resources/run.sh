#!/bin/sh
cd /opt/docker
docker-compose down
nohup docker-compose up &

cd ../coco-react
pkill npm
git fetch --all
git reset --hard origin/master
npm install
nohup npm start &

cd ../coco-api/coco
git fetch --all
git reset --hard origin/master
pkill java
sh gradlew bootJar
nohup java -jar -Dfile.encoding=UTF8 build/libs/coco-0.0.1-SNAPSHOT.jar &