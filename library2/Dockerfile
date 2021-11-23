FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/library-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} sbgpapp.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/sbgpapp.jar"]