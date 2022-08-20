
# Education Portal ![workflow](https://github.com/mharikmert/education-portal/actions/workflows/CI.yml/badge.svg) [![live-demo](https://img.shields.io/badge/live-demo-brightgreen.svg)](https://portal-demo.mharikmert.dev)

[![Java 17](https://img.shields.io/badge/Java-17-brightgreen.svg)](https://www.oracle.com/java/technologies/downloads/#java17)
[![Spring Boot 2.5.4](https://img.shields.io/badge/SpringBoot-2.5.4-brightgreen.svg)](https://github.com/spring-projects/spring-boot/releases/tag/v2.5.4)
[![Gradle 7.3.3](https://img.shields.io/badge/Gradle-7.3.3-brightgreen.svg)](https://gradle.org/releases)
[![Angular 12.0.3](https://img.shields.io/badge/Angular-12.0.3-brightgreen.svg)](https://www.npmjs.com/package/@angular/cli/v/12.0.3)
[![TypeScript 4.2.3](https://img.shields.io/badge/TypeScript-4.2.3-brightgreen.svg)](https://www.npmjs.com/package/typescript/v/4.2.3)
[![MySQL 8.0.13](https://img.shields.io/badge/MySQL-8.0.13-brightgreen.svg)](https://www.npackd.org/p/com.mysql.dev.MySQLWorkbench/8.0.13)

## Description
This is a student information system for high school students in Association for the Support of Contemporary Living (NGO in Turkey). It is a web application that is used to manage the students, teachers, courses, schedules, exams. It is developed using [Java](https://www.oracle.com/java/technologies/downloads), [Spring Boot](https://spring.io/projects/spring-boot), [Spring Security](https://spring.io/projects/spring-security), [Angular](https://angular.io), [MySQL](https://mysql.com)  


## Installation

``` bash
git clone https://github.com/mharikmert/education-portal
```

## Configuration

Set your environment variables in `/backend/spring/src/main/resources/sample-application.yml` and rename as `application.yml`.

``` yaml

# default configuration
spring:
  # Java mail sender configuration 
  mail:
    host: smtp.gmail.com
    port: 587
    username: # sender email address
    password: # app password for the email address

server:
  port: 8080

jwt_secret: "secret key here"
jwt_expiration: "1h"

# dev environment configuration
---
spring:
  config:
    activate:
      on-profile: dev
  datasource:
    url: jdbc:mysql://localhost:3306/education_portal_dev
    username: username 
    password: password
server:
  port: 8080

# test environment configuration
---
spring:
  config:
    activate:
      on-profile: test
  datasource:
    url: jdbc:mysql://localhost:3306/education_portal_test
    username: username
    password: password
server:
  port: 8081

# prod environment configuration
---
spring:
  config:
    activate:
      on-profile: prod
  datasource:
    url: prod-db-url
    username: prod-db-username
    password: prod-db-password

server:
  port: 9000

```

You can specify your active profile as follows:

`application.properties`

``` properties
spring.profiles.active = dev
```
or

`application.yml`

``` yaml
spring:
  profiles:
    active: dev
```
## Running the application

``` bash

cd education-portal

docker compose up -d
```
You can see running containers with
``` docker ps -a ```


![Screenshot](https://user-images.githubusercontent.com/42295478/185742946-ae57de6c-3a96-41ce-805f-c434043edd45.png)

## Deployment Diagram 
![deployment-schema](https://user-images.githubusercontent.com/42295478/185751362-b1e69052-97ce-41f8-9cc5-7b4108ae4feb.png)

## Contributing
Pull requests are welcome. For bug reports, please open an issue. 

