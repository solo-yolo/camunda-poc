FROM java:8-jre
MAINTAINER Serhii Solohub <sergei.sologub@gmail.com>

ADD ./target/inventory-worker.jar /app/
CMD ["java", "-Xmx500m", "-Dcamunda.url=http://camunda:8080/engine-rest", "-jar", "/app/inventory-worker.jar"]

EXPOSE 9003