FROM java:8-jre
MAINTAINER Serhii Solohub <sergei.sologub@gmail.com>

ADD ./target/db-worker.jar /app/
CMD ["java", "-Xmx500m", "-Dcamunda.url=http://camunda:8080/engine-rest", "-jar", "/app/db-worker.jar"]

EXPOSE 9001