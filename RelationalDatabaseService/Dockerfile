#FROM maven:3.8.4-openjdk-17 AS build
#WORKDIR /workspace/app
#
#COPY . .
#RUN mvn install -DskipTests
#
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#
#FROM openjdk:17-jdk-slim
#VOLUME /tmp
#ARG DEPENDENCY=/workspace/app/target/dependency
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#
#ENTRYPOINT ["java", "-cp", "app:app/lib/*", "rs.ac.uns.acs.nais.RelationalDatabaseService.RelationalDatabaseServiceApplication"]


# Base image with Maven and JDK installed
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /workspace/app

# Copy the Maven settings file (if you have one)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies as a separate layer to leverage Docker cache
# This layer will be rebuilt only if pom.xml changes
# Resolve plugins explicitly
RUN mvn dependency:resolve-plugins

# Then go offline
RUN mvn dependency:go-offline


# Copy your source files
COPY src src

# Package without running tests to speed up the build
RUN mvn install -DskipTests

# Final stage to reduce the size of the image
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY --from=build /workspace/app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]