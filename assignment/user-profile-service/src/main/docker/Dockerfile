#FROM openjdk:8-jdk-alpine
#RUN  apk update && apk upgrade && apk add netcat-openbsd
#RUN mkdir -p /usr/local/statisticservice
#ADD @project.build.finalName@.jar /usr/local/statisticservice/
#ADD run.sh run.sh
#RUN chmod +x run.sh
#CMD ./run.sh


FROM openjdk:8-jdk-alpine
#COPY /target/user-profile-service-1.0.0.jar /usr/local/userprofileservice/
RUN mkdir -p /usr/local/userprofileservice
ADD @project.build.finalName@.jar /usr/local/userprofileservice/
WORKDIR /usr/local/userprofileservice
EXPOSE 8080
CMD ["java", "-jar", "user-profile-service-1.0.0.jar"]
