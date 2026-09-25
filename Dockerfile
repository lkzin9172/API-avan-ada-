# Usando uma imagem leve do Java (OpenJDK)
FROM eclipse-temurin:17-jdk

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do seu projeto para dentro do container
COPY src ./src

# Compila o código fonte
RUN javac src/main/java/com/freefire/api/ApiServer.java -d bin

# Define o comando para rodar o servidor
# O comando abaixo roda a classe principal e o classpath para o código compilado
CMD ["java", "-cp", "bin", "com.freefire.api.ApiServer"]

# Expõe a porta que o servidor vai usar
EXPOSE 8080
