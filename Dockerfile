FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/service.jar service.jar
CMD ["java", "-Dserver.port=$PORT", "-jar", "service.jar"]
