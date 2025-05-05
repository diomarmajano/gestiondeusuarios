FROM openjdk:21-ea-24-oracle 
WORKDIR /app
COPY target/gestiondeusuarios-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_BD_Prueba /app/Wallet_BD_Prueba
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]