# Spring Boot JPA

This project demonstrates a simple microservice that uses Spring Boot and JPA with the PostgreSQL database.

### PostgreSQL

Run PostgreSQL on Docker (default username and database: ```postgres```):

```
docker run --name local-postgres -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -d postgres
```

### Run project

Run ``` ./gradlew bootRun ``` and access: ``` http://localhost:8080/v1/products ``` 


### Swagger

Access: ``` http://localhost:8080/swagger-ui.html ```

![Alt text](doc/swagger_print.png?raw=true "Swagger")