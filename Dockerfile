FROM openjdk:8-slim
VOLUME /tmp
ADD target/service.jar service.jar
EXPOSE ${PORT:-8080}
CMD ["java", "-jar", "service.jar", "-Xms512k", "-Xmx:192m"]
