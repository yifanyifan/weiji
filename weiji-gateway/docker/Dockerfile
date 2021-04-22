FROM centos-jdk:1.0.0
VOLUME /tmp
ADD weiji-gateway-0.0.1.jar app.jar
ADD entrypoint.sh ./
RUN chmod +x ./entrypoint.sh
RUN sh -c 'touch /app.jar'
ENTRYPOINT  ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 9999