FROM openjdk:latest
EXPOSE 9090
ADD target/walkover-project-app.jar walkover-project-app.jar
ENTRYPOINT ["java","-jar","/walkover-project-app.jar"]