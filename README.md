# forexrateconverter
fx rate converter api


docker run -p 8080:8080 aemrezorlu/forexrateconverter 
@TODO
-Exchange Rate

-Conversion


![](/docs/fxRateDesign.jpg?raw=true "Title")

**DESIGN shown above**

**TECH STACK**
- **Java 11**
- **Spring Boot 2.6.3**
    - + Spring Cloud Dependencies v2021 
- **JUnit 5**
- **Lombok+Mapper**
- **MongoDB**
- **Springdoc** 1.6.6 due to dockletApi error of Swagger on spring 2.6.3 :arrow_right: http://localhost:8080/swagger-ui/index.html#/
- **Docker** :arrow_right: https://hub.docker.com/repository/docker/aemrezorlu/readingisgood/general

	docker images
	docker run -p 8080:8080 aemrezorlu/forexrateconverter
- **Postman** 2.1 :arrow_right: /docs/Forex.postman_collection.json
