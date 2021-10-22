FROM openjdk:11
VOLUME /tmp
EXPOSE 8099
ADD ./target/Diccionario-server-0.0.1-SNAPSHOT.jar server.jar
ENTRYPOINT ["java","-jar","/server.jar"]
