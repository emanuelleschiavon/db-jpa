### Building

```
./gradlew clean build
```


### Running

First, run the dependency database.
```
docker-compose up -d mysql
```
```
./gradlew bootRun
```
