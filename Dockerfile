FROM eclipse-temurin:11.0.18_10-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:11

VOLUME /Desktop/Universidad/Semestre 9/Arquitectura de Software/Proyecto Final/logsProy

ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENV URL_DISCOVERY "http://localhost:8701"
ENV SERVER_URI "http://localhost:8888"
ENV PROFILE "local"


ENTRYPOINT ["java","-Dspring.profiles.active=prod","-cp","app:app/lib/*","com.example.msinventory.MsInventoryApplicationKt"]