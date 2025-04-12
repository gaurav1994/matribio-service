FROM bellsoft/liberica-openjdk-alpine
CMD [ "java", "-jar", "matribio-service.jar" ]
WORKDIR /work/
COPY ./target/*.jar /work/