FROM java:8-jre
VOLUME /tmp
ADD zuul-0.0.1-SNAPSHOT.jar zuul-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/zuul-server.jar"]