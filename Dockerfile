FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/service.jar service.jar
EXPOSE ${PORT:-8080}
CMD ["java", "-XX:+PrintGCDetails", "-XX:+PrintGCDateStamps", "-XX:+PrintTenuringDistribution", "-XX:+UseConcMarkSweepGC",
"-jar", "service.jar"]