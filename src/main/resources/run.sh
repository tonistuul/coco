#!/bin/sh
pkill java
nohup java -jar -Dfile.encoding=UTF8 /opt/coco/coco-0.0.1-SNAPSHOT.jar &
