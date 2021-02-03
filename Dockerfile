FROM openjdk:11 
EXPOSE 7770
ARG JAR_FILE=build/libs/present_system.jar
ADD ${JAR_FILE} present_system.jar
ENTRYPOINT ["java","-jar","/present_system.jar"]