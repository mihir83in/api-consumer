FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/service.jar service.jar
EXPOSE ${PORT:-8080}
CMD ["java", "-jar", "service.jar", "-Xms510k", "-Xmx:256m"]