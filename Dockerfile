FROM bellsoft/liberica-openjdk-alpine:latest
CMD [ "java", "-jar", "matribio-service.jar" ]
WORKDIR /work/
COPY ./target/*.jar /work/