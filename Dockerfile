FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/*.jar
ADD ./target/forexrateconverter-0.0.1-SNAPSHOT.jar forex-rate-converter.jar

ENTRYPOINT ["java", "-jar", "/forex-rate-converter.jar"]