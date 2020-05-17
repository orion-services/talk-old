# Orion Talk Service

Orion Talk Service is a simple text message microservice that implements both an Web Service and Web Socket APIs.

## Run with docker compose

The easer way to install and execute the Orion Talk Service is to use docker-composer. Once docker compose is installed, you can run the bellow command in the:

    docker-compose up -d

Note: Default database root and password is: orion-talk-service

## Build a docker image

To create a Docker image for Orion Talk Service:

    mvn clear package

    docker build -t orion-talk-image .

    docker run -d --name orion-talk-service -p 9081:9081 -p 9444:9444 orion-talk-image

### Development mode

During development, you can use Liberty's development mode (dev mode) to code while observing and testing your changes on the fly.

    mvn clear package
    mvn liberty:dev
<!--
### JWT Auth

Have a look at the **TestSecureController** class (main application) which calls the protected endpoint on the secondary application.
The **ProtectedController** contains the protected endpoint since it contains the _@RolesAllowed_ annotation on the JAX-RS endpoint method.

The _TestSecureController_ code creates a JWT based on the private key found within the resource directory.
However, any method to send a REST request with an appropriate header will work of course. Please feel free to change this code to your needs.
-->
