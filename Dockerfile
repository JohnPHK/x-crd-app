FROM openjdk:8-alpine
COPY target/x*.jar /usr/local/app/twitter/lib/x_crd.jar
ENTRYPOINT ["java", "-jar", "/usr/local/app/twitter/lib/x_crd.jar"]
