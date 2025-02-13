# Introduction

This is a Java application that interacts with the RESTful X API to create, read or delete (CRD) a tweet. The application performs each operation by making an HTTP request and sending it to the X API. Then, it parses and processes the HTTP response received from X API.

`Maven` is used to manage all dependencies and build the project's directory structures. `Jackson` was used to map a JSON String to a Java Object. `JUnit` and `Mockito` were utilized for thorough unit and integration testing. Finally, the project was deployed using `Docker`.

The main purpose of this project was to educate myself with Spring Framework. This project taught me the mechanisms behind Inversion of Control (IoC) and Dependency Injection (DI) and how the Spring framework implements IoC/DI to architect application components. Below shows different ways of spring implementation and how each manage application components.

# Quick Start
<!--- how to package your app using mvn?-->
First, clone the app to your local machine. Then, set the environment variables for OAuth 1.0a

- `consumerKey`: The Consumer Key or called API key
- `consumerSecret`: Consumer Secret or called API secret key
- `accessToken`: The Access Token for read and write access
- `tokenSecret`: The Secret Key for access token

Then, package your application through mvn:

```bash
mvn clean package
```

Above input to the terminal at the directory of the app will generate Uber JAR. Then, run the application through following command:

```bash
java -jar x_crd-1.0-SNAPSHOT.jar post|show|delete [options]
```

Another way to run this would be to pull the docker image johnphk/x-crd-app from dockerhub. Then, run the following command:

```bash
docker run --rm \            
    -e consumerKey=<Consumer Key from X API> \
    -e consumerSecret=<Consumer Secret from X API> \
    -e accessToken=<Access Token from X API> \
    -e tokenSecret=<Token Secret from X API> \
    johnphk/x-crd-app post|show|delete <Status Message>
```

# Design

## UML diagram

## explain each component(app/main, controller, service, DAO) (30-50 words each)

## Models

## Spring

# Test

## Deployment

