# user-service/Dockerfile
#Build stage
FROM maven:3.8.2-openjdk-17 as snapshot
WORKDIR /services/build
COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

#Runtime stage
FROM amazoncorretto:17
ARG PROFILE=native
ARG APP_VERSION=0.0.1

WORKDIR /services/app
COPY --from=snapshot /services/build/target/user-service-*.jar /services/app

EXPOSE 8090

ENV DB_URL=jdbc:mysql://localhost:3307/users_db
ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

CMD java -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${DB_URL} -jar user-service-${JAR_VERSION}-SNAPSHOT.jar