FROM openjdk:8-jre-alpine
WORKDIR /app
ADD nao-saia.jar /app/nao-saia.jar
ENTRYPOINT java -jar nao-saia.jar --spring.profiles.active=prod
EXPOSE 8088