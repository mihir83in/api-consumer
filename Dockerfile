FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/service.jar service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/service.jar"]