FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/service.jar service.jar
EXPOSE ${PORT:-8080}
ENV JAVA_OPTS="-XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -XX:+UseConcMarkSweepGC"
CMD ["java", "-jar", "service.jar"]