#FROM openjdk:8-jdk-alpine
#RUN  apk update && apk upgrade && apk add netcat-openbsd
#RUN mkdir -p /usr/local/statisticservice
#ADD @project.build.finalName@.jar /usr/local/statisticservice/
#ADD run.sh run.sh
#RUN chmod +x run.sh
#CMD ./run.sh


FROM openjdk:8-jdk-alpine
#COPY /target/authorization-service-1.0.0.jar /usr/local/authorizationservice/
RUN mkdir -p /usr/local/authorizationservice
ADD @project.build.finalName@.jar /usr/local/authorizationservice/
WORKDIR /usr/local/authorizationservice
EXPOSE 8085
CMD ["java", "-jar", "authorization-service-1.0.0.jar"]
