FROM openjdk:11.0.8-slim
WORKDIR /home/app
COPY target /home/app/target
ENV JAR_FILE=/home/app/target/mongo.abelha-0.0.1-SNAPSHOT.jar
COPY --from=build ${JAR_FILE} /home/app/application.jar
ENTRYPOINT ["java","-jar","/home/app/application.jar"]