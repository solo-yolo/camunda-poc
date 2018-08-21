FROM java:8-jre
MAINTAINER Serhii Solohub <sergei.sologub@gmail.com>

ADD ./target/northbound-worker.jar /app/
CMD ["java", "-Xmx500m", "-Dcamunda.url=http://camunda:8080/engine-rest", "-Dnorthbound.url=http://northbound-app:9005", "-jar", "/app/northbound-worker.jar"]

EXPOSE 9004