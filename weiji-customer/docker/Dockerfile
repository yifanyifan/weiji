FROM centos-jdk
VOLUME /tmp
ADD weiji-customer-0.0.1.jar app.jar
ADD entrypoint.sh ./
RUN chmod +x ./entrypoint.sh
RUN sh -c 'touch /app.jar'
ENTRYPOINT  ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 7001