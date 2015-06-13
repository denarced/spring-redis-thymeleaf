# spring-redis-thymeleaf
Basic Redis-Thymeleaf template created with Spring Boot.

The following will build and run this small webapp on localhost:8080:

    mvn install && java -jar target/redis-tryout-0.0.1-SNAPSHOT.jar

The running webapp requires that redis is running on localhost. On Ubuntu 15.04, the following is sufficient:

    sudo apt-get install redis-server
