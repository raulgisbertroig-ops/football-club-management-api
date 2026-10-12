# 1. IMAGEN BASE: Un mini-Linux (Alpine) que ya trae Java 21 preinstalado
FROM eclipse-temurin:21-jre-alpine

# 2. DIRECTORIO DE TRABAJO: Creamos una carpeta llamada /app dentro del contenedor
WORKDIR /app

# 3. COPIA: Copiamos el artefacto .jar desde tu carpeta target de Windows el contenedor Linux
# (El asterisco asegura que pille tu  archivo sin importar la version exacta)
COPY target/*.jar app.jar

# 4. PUERTO: Le decimos a Docker que esta aplicación expone el puerto 8080
EXPOSE 8080

# 5. ARRANQUE: El camino exacto que ejecutará el contenedor al encenderse
ENTRYPOINT ["java", "-jar", "app.jar"]