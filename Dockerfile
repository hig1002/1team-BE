FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} kwthon.jar
ENTRYPOINT ["java","-jar","/kwthon.jar"]