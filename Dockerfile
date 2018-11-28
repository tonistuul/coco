FROM openjdk:8-jre-alpine

RUN apk --no-cache add curl

RUN mkdir -p /var/app
WORKDIR /var/app

ADD build/libs/coco-0.0.1-SNAPSHOT.jar ./

HEALTHCHECK CMD curl http://localhost:9701

ENTRYPOINT ["java", "-jar", "coco-0.0.1-SNAPSHOT.jar"]
