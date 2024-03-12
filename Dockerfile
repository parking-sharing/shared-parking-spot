FROM eclipse-temurin:17-jdk-alpine
COPY ./shared-parking-spot-app/target/shared-parking-spot-app-0.0.1-SNAPSHOT.jar shared-parking-spot.jar
ENTRYPOINT ["java","-jar","/shared-parking-spot.jar"]