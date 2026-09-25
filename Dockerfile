FROM eclipse-temurin:17-jdk-slim
WORKDIR /app
COPY . .
RUN javac ApiServer.java
EXPOSE 8080
CMD ["java", "ApiServer"]
