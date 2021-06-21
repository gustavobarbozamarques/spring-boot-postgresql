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

![Alt text](doc/swagger.png?raw=true "Swagger")

### Sonar

Run Sonar on Docker

```docker run -d --name sonarqube -p 9000:9000 sonarqube```

Access: ``` http://localhost:9000 ```

Username: ``` admin ```

Password: ``` admin ``` 

Create a new project, get and run gradle snippet.

Ex.:

```
./gradlew sonarqube \
  -Dsonar.projectKey=<PROJECT_KEY> \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=<PROJECT_TOKEN>
```

![Alt text](doc/sonar.png?raw=true "Sonar")
