FROM java:8
VOLUME /tmp
ADD target/hello-docker.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8082
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]