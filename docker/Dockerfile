FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/service.jar service.jar
ENTRYPOINT ["java", "-jar", "/service.jar"]