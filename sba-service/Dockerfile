FROM java:8

ENV SERVER_PORT 8081

ARG JAR_FILE

ADD target/${JAR_FILE} /sba-service.jar

ENTRYPOINT ["java", "-jar","/sba-service.jar"]