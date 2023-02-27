# FLATROCK DEMO

## Summary
This project you can: 

1. Create a user.
2. Search for a user. 
3. Delete a user.    

You are able to do so using a Rest client (ie postman) or any rest client of your choice.

## Prerequisite

IDE (eclipse/intelij)

Java 11

Maven

Postman (any REST client)

## Frameworks and Technologies

Spring Boot 2.7.5

H2 database

```
RUN application

When using intelij select DemoApplication class and click run()

The application will start on port 8080.

Open you browser to connect to the H2 in memory db.

In the application.properties set the below to your preference:
spring.datasource.username=???
spring.datasource.password=???

Enter the following url:http://localhost:8080/h2-console/

Login with the credential you specified in the application.properties

Once you logged in you will be able to manage the data in the H2 db.

```

## Resources

```
In the project there is a docs folder that contains:

1. Postman collection 

2. Api spec

```
