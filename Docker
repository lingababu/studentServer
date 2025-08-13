FROM openjdk:17
COPY target/student-server-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app/
EXPOSE 2222
ENTRYPOINT ["java","-jar","student-server-0.0.1-SNAPSHOT.jar"] 
