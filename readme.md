# Spring Todo Application

School project focused on practicing of technologies such as Spring framework and Hibernate. 
Project also includes a integration tests.

## Front-end
As a front-end, javascript demo from todomvc.com is used. 
Store.js was little bit modified for communication over REST API.
Rest Api have to located on localhost:8080 (it is possible to change it)

todomvc.com front-end source code:
 https://github.com/tastejs/todomvc/tree/gh-pages/examples/vanillajs

## Back-end
Project demonstrate simple Spring application with persistence configuration.

Back end is structured into few directories such as:
- controller -- contains controller for REST API communication
- repository -- dummy repository
- service -- dummy service

Controller, repository and service are implemented in abstract way for reusable purposes.

### How to run it?
- maven way: mvn clean package and deploy it to a Java application server such as Apache Tomcat.
