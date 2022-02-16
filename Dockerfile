FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/*.jar
ADD ./target/forex-rate-converter.jar forex-rate-converter.jar

ENTRYPOINT ["java", "-jar", "/forex-rate-converter.jar"]