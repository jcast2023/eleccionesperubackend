FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 10000

ENTRYPOINT ["sh","-c","java -Dserver.port=$PORT -jar target/elecciones-peru-backend-0.0.1-SNAPSHOT.jar"]



