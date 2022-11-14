
# Education Portal ![workflow](https://github.com/mharikmert/education-portal/actions/workflows/CI.yml/badge.svg) [![live-demo](https://img.shields.io/badge/live-demo-brightgreen.svg)](https://portal-demo.mharikmert.dev)

[![Java 17](https://img.shields.io/badge/Java-17-brightgreen.svg)](https://www.oracle.com/java/technologies/downloads/#java17)
[![Spring Boot 2.7.5](https://img.shields.io/badge/SpringBoot-2.7.5-brightgreen.svg)](https://github.com/spring-projects/spring-boot/releases/tag/v2.7.5)
[![Gradle 7.3.3](https://img.shields.io/badge/Gradle-7.3.3-brightgreen.svg)](https://gradle.org/releases)
[![Angular 12.0.3](https://img.shields.io/badge/Angular-12.0.3-brightgreen.svg)](https://www.npmjs.com/package/@angular/cli/v/12.0.3)
[![TypeScript 4.2.3](https://img.shields.io/badge/TypeScript-4.2.3-brightgreen.svg)](https://www.npmjs.com/package/typescript/v/4.2.3)
[![MySQL 8.0.13](https://img.shields.io/badge/MySQL-8.0.13-brightgreen.svg)](https://www.npackd.org/p/com.mysql.dev.MySQLWorkbench/8.0.13)

## Description
This is a student information system for high school students in Association for the Support of Contemporary Living (NGO in Turkey). It is a web application that is used to manage the students, teachers, courses, schedules, exams. It is developed using [Java](https://www.oracle.com/java/technologies/downloads), [Spring Boot](https://spring.io/projects/spring-boot), [Spring Security](https://spring.io/projects/spring-security), [Angular](https://angular.io), [MySQL](https://mysql.com)  


## Installation
You can use the source code to build Java and Angular applications separately or you can pull the docker images of the repository to run the application. 
``` bash
git clone https://github.com/mharikmert/education-portal
```
```bash
docker pull ghcr.io/mharikmert/education-portal/api:latest
docker pull ghcr.io/mharikmert/education-portal/client:latest
```

## Configuration

Environment variables are set in the application properties file as follows
```properties
spring.profiles.active = ${ACTIVE.PROFILE:dev}
spring.datasource.url = ${DB_HOST}
spring.datasource.username = ${DB_USERNAME}
spring.datasource.password = ${DB_PASSWORD}
spring.mail.host = ${MAIL_HOST}
spring.mail.username = ${MAIL_USER}
spring.mail.password = ${MAIL_PASSWORD}
spring.mail.port = ${MAIL_PORT}
jwt_secret = ${JWT_SECRET}
```
## Run
Backend application requires an environment variable file as you see above, it can be done by uncommenting the `env_file` line in the docker-compose.yaml file and specifying the file path or simply running the backend container with the .env file.
![Ss](https://user-images.githubusercontent.com/42295478/201759646-e0088021-b2fa-4082-a936-401e219c0a58.png)

### Build and Run with compose
``` bash
docker compose up -d
```

### Pull the images and Run
```bash 
docker run -p 8080:8080 --env-file /path/to/env/.env ghcr.io/mharikmert/education-portal/api:latest
docker run -p 4200:80 ghcr.io/mharikmert/education-portal/client:latest
```


You can see running containers with
``` docker ps```

## Contributing
Pull requests are welcome. For bug reports, please open an issue. 

