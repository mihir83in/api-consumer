# Currency Converter

On Heroku: https://currencyconv.herokuapp.com 
On Docker hub: https://hub.docker.com/r/mihir83in/currencyconverter/ 
On Travis: https://travis-ci.org/mihir83in/api-consumer 

Currency Converter is a hobby project that uses spring stack including spring-boot, spring-cloud-netflix, spring-retry etc.

The goal is to showcase heterogeneous third party apis that offer currency conversion whilst applying best practices and
production ready features.

Here is the list of technology used in the project.

### UI - Views and Web
* Spring MVC -  Controllers for returnin Thymeleaf views and global exception managers
* Thymeleaf - Facilitate spring bean backed forms with html templating.
* Spring validation - Hibernate validator is used to validate user creation form and currency conversion form. This is
implemented quite transparently using validator annotations.

### Backend
* Spring-Data-Jpa: In conjunction with h2 in-memory database, jpa annotations have been used to map database schema to Entity
Pojos. JpaRepository is used for CRUD operations. There are mainly two domain objects, 1. User 2. Conversion Queries.

### Api Layer
* Netflix Feign: Calls to open exchange rates and currency layer apis are written with declarative rest client feign. Further,
This calls are wrapped with a custom random load balancing strategy (Fully customizable to support any algorithm in future like
round robin, least heavily used, fast performing etc.). The load balancer component returns a randomly chosen client from a
list of clients. This calls are wrapped in spring-retry to be retried upto 3 times (configurable) if there's a failure.
Each retry will be with a different client as loadbalancer would return a different client each time.
* Spring-Cache: Caffeine, which is a Java-8 groovy based cache, is used to cache the api results with a configurable TTL
(through application.yml)

### Robustness
* Spring-Retry: Calls to apis are additionally wrapped with spring-retry so that they are retried three times in case of failures.
* Spring Security: Bcrypt encoded password is stored in the database to protect it. A robust login model with CSRF enabled
pages is implemented.
* Monitoring Management Interface: DropWizard metrics are used to capture timings of each website separately. An error count
metric is also introduced. Spring-Boot-Actuator is used to enable production grade monitoring and is exposed over /management
endpoint.
* Management Endpoint Security: Management endpoint also has been authenticated
* Jolokia: Jolokia enables exporting JMX over http, this has been used to expose all metrics that are JMX only.

### CI / CD
* Travis: On every commit, travis-ci will run a build (defined in .travis.yml) that compiles source-code, runs tests and
prepares a docker image.
* Docker Hub: This image is then deployed after build is successful to docker hub with docker registry.
* Heroku: The same image is then deployed after build is successful to heroku with heroku docker registry. This triggers a new
/ re deployment of container over heroku.

### Running / Setup:
* Intellij: In Intellij, it can be run by just running Application.java
* FatJar: Running, mvn clean install will generate a fat jar in target folder called service.jar, CMD to target and
invoke `java -jar service.jar` command to run
* Docker Image: Running, mvn clean package docker:build will generate a docker image, which can be run on a machine with docker
 installed by entering `docker run -p 8080:8080 <generatedImageName>`
