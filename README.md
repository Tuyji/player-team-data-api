# Player Team Data API

Simple football player team data application.


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* [H2 Database](https://www.h2database.com/) - Java SQL database -  in-memory database
* [git](https://git-scm.com/) - Free and Open-Source distributed version control system
* [Lombok](https://projectlombok.org/) - Java library that automatically plugs into your editor and build tools, spicing up your java.
* [Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.
* [Postman](https://www.getpostman.com/) - API Development Environment (Testing Docmentation)
* [JUnit5](https://junit.org/junit5/) - JUnit 5 is the next generation of JUnit. The goal is to create an up-to-date foundation for developer-side testing on the JVM
* [Mockito](https://site.mockito.org/) - Tasty mocking framework for unit tests in Java

## To-Do

- [ ] Central Logging



## Modules

- ### player-data-api
- ### contract-price-service




## Running the application locally


- Download the zip or clone the Git repository.
- Import Maven projects
- Running Order : 
	- player-data-api
	- contract-price-service

### URLs

|  URL | Remark |
|----------|--------------|
|`http://localhost:8080/api/teams`                      | Teams API |
|`http://localhost:8080/api/players`                    | Players API |
|`http://localhost:8080/swagger-ui.html`                | Swagger Team - Player API |
|`http://localhost:8090/api/contractprice`              | Contract Price Service |
|`http://localhost:8090/swagger-ui.html`                | Swagger Contract Price Service |
|`http://localhost:8080/h2`                    			| H2 Database |


## Sample Images & Documentation


### H2 Database


#### DB Interface

[![INSERT YOUR GRAPHIC HERE](https://github.com/Tuyji/player-team-data-api/blob/master/images/H2.PNG)]()


### Swagger UI


#### Player Team Data API Swagger Documentation

[![INSERT YOUR GRAPHIC HERE](https://github.com/Tuyji/player-team-data-api/blob/master/images/PlayerDataSwagger.PNG)]()


#### Contract Price API Swagger Documentation

[![INSERT YOUR GRAPHIC HERE](https://github.com/Tuyji/player-team-data-api/blob/master/images/ContractPriceSwagger.PNG)]()


#### Postman Collection


* [player-team-data-api.postman_collection.json](https://github.com/Tuyji/player-team-data-api/blob/master/player-team-data-api.postman_collection.json)

* [contract-price-api.postman_collection.json](https://github.com/Tuyji/player-team-data-api/blob/master/contract-price-api.postman_collection.json)


[![INSERT YOUR GRAPHIC HERE](https://github.com/Tuyji/player-team-data-api/blob/master/images/PlayerTeamDataAPI.PNG)]()



## packages

- `model` — service request- response holders;
- `entity` — entity holder;
- `repository` — database communication;
- `service` — business logic holder;
- `controller` — rest api implementations;
- `errorhandling` — custom error handling and exceptions;

- `resources/` - Contains all the static resources, templates and property files.
- `resources/application.properties` - It contains application-wide properties (server, proxy, url, database configrations) .

- `test/` - contains unit and integration tests

- `pom.xml` - contains all the project dependencies
 
