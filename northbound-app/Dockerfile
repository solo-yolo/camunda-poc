FROM java:8-jre
MAINTAINER Serhii Solohub <sergei.sologub@gmail.com>

ADD ./target/northbound-app.jar /app/
CMD ["java", "-Xmx500m", "-jar", "/app/northbound-app.jar" ]

EXPOSE 9003