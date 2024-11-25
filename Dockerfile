# Usa una imagen base de Gradle con JDK 11 o la que necesites
FROM gradle:7.6-jdk11 AS builder

# Define el directorio de trabajo
WORKDIR /app

# Copia los archivos del proyecto dentro del contenedor
COPY . /app/

# Asigna permisos de ejecución al script gradlew
RUN chmod +x /app/gradlew

# Configura el SDK de Android
ENV ANDROID_HOME=/opt/android-sdk
ENV PATH=$ANDROID_HOME/tools:$ANDROID_HOME/tools/bin:$ANDROID_HOME/platform-tools:$PATH

# Instala dependencias de Android SDK (solo las necesarias para compilar)
RUN mkdir -p $ANDROID_HOME && \
    curl -s https://dl.google.com/android/repository/commandlinetools-linux-8512546_latest.zip -o android-tools.zip && \
    unzip android-tools.zip -d $ANDROID_HOME && \
    rm android-tools.zip && \
    yes | $ANDROID_HOME/tools/bin/sdkmanager --licenses && \
    $ANDROID_HOME/tools/bin/sdkmanager "platforms;android-30" "build-tools;30.0.3" "platform-tools"

# Ejecuta el comando gradle para compilar el proyecto
RUN ./gradlew clean build -x check -x test --info

# Define el contenedor de la aplicación final (si lo necesitas)
FROM openjdk:11-jre-slim

WORKDIR /app

# Copia los artefactos de la construcción al contenedor final
COPY --from=builder /app/build/libs /app/

# Define el comando para ejecutar la aplicación
CMD ["java", "-jar", "your-application.jar"]
