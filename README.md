# Castle Electronics
### Product Service

## Run- and build-time Requirements
- JDK17
- Maven

## Build
`mvn clean package`

## Run service
`./run-service.sh`

## Run tests
`./run-tests.sh`

## Access Service
To check the server is up, hit: http://localhost:8080/actuator/health

To see the API docs with Swagger: http://localhost:8080/swagger-ui/index.html


## Tech Stack
- SpringBoot 3
- Lombok
- SLF4J logging
- Liquibase for database version control
- JPA for database access
- H2 as default database implementation
- Swagger for live API documentation
- Actuator support for service diagnostics

## Release Notes
- There is no authentication or authorisation support. So admin and customer APIs are open to all.
- There is no persistent storage. The server runs on in-memory H2 so all state is lost on restart
- Duplicate deals can be added still
- No guard against existing products in baskets or with deals when deleting products 
- Customer id will need come from somewhere else - the system does not generate one
