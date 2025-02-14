# X CRD Application

---

## Introduction

This is a Java application that interacts with the RESTful X API to create, read or delete (CRD) a tweet. The application performs each operation by making a corresponding HTTP request and sending it to the X API. Then, it parses and processes the HTTP response received from X API.

`Maven` is used to manage all the dependencies and build the project's directory structure. `Jackson` was used to map a JSON String to a Java Object. `JUnit` and `Mockito` were utilized for thorough unit and integration testing. Finally, the project was deployed using `Docker`.

The main purpose of this project was to educate myself with Spring Framework. This project taught me the mechanisms behind Inversion of Control (IoC) and Dependency Injection (DI) and how the Spring framework implements IoC/DI to architect application components. Below shows different ways of spring implementation and how each manage application components.

## Quick Start
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

Another way to run this would be to pull the docker image `johnphk/x-crd-app` from dockerhub. Then, run the following command:

```bash
docker run --rm \            
    -e consumerKey=<Consumer Key from X API> \
    -e consumerSecret=<Consumer Secret from X API> \
    -e accessToken=<Access Token from X API> \
    -e tokenSecret=<Token Secret from X API> \
    johnphk/x-crd-app post|show|delete <Status Message>
```

## Design

### UML diagram

![UML Diagram](./assets/uml_diagram.png)

#### App/Main

This class serves as the initial entry point of the application to perform post, show or delete a status message. It parses the command-line arguments and invokes a controller to execute the post, show, or delete operations on X.

#### Controller

This class represents the controller component of MVC architecture. Based on the command-line argument received from the App/Main class, it invokes the corresponding method of service class to perform CRD operation.

#### Service

This class represents the service layer, which acts as an intermediary between the controller and the data layer. It handles business logic and calls the DAO to retrieve or update data on X. Additionally, it performs sanity checks to ensure no unsupported data is passed to the X API.

#### DAO

The DAO represents the data layer in the application architecture. It directly interacts with the X API to retrieve or update data on X. It achieves this by constructing a URI that maps to the Create, Read, or Delete operations for the data on X.

#### Model

The model is designed to represent the JSON data received as a response from X API.

For example,

```json
{
    "data": {
        "edit_history_tweet_ids": [
            "1890255049797841060"
        ],
        "id": "1890255049797841060",
        "text": "Hello Hello MY FRIENDS 2025"
    }
}
```

To represent JSON data as shown above, two model classes are implemented: TwitterData.java and Data.java. TwitterData.java serves as a wrapper class representing an entire response from the X API, while Data.java represents the values mapped from the data key.

## Spring

## Test

## Deployment
