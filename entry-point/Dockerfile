FROM java:8-jre
MAINTAINER Serhii Solohub <sergei.sologub@gmail.com>

ADD ./target/entry-point.jar /app/
CMD ["java", "-Xmx500m", "-Dcamunda.url=http://camunda:8080", "-Ddb-worker.url=http://db-worker:9002", "-jar", "/app/entry-point.jar" ]

EXPOSE 9001