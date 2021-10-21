# springboot-redis-master-slave
Example to show redis master slave using spring boot

- Conf folder contains redis.conf to be used to build slave redis.
- Bring up redis-master & slave using ```docker-compose up```
- Run ```mvn clean install```
- Bring up spring application using ```java -jar <jarName>```
- Bring master down to get reads from slave using ```docker-compose stop master```
