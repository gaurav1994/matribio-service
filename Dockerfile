FROM bellsoft/liberica-openjdk-alpine
CMD [ "java", "-jar", "matribio-service-0.0.1-SNAPSHOT.jar" ]
WORKDIR /work/
COPY ./target/*.jar /work/