# pokemon-rest
Small application that retrieve a list 0f 500 Pokémons from the PokéAPI and store them in a database.
The list can be retrieve then with HTTP endpoints with paginataion as well.

#### NOTE:
The Pokémon list is retrieved and stored into the DB on once the application starts through a runner process, and given that there are 500 element to fetch,
it could take a bit while.

The DB table is also cleared before that.



## Getting Started


### Main technologies used
 - JDK 11
 - Maven
 - Spring Boot
 - SQLite


### Installing
* Clone the repository
```
git clone git@github.com:Hbuz/pokemon-rest.git
```

* Creating an executable JAR
Execute this command from the root of the project:
```
mvn install
```

### Run the application
Execute this command from the root of the project:
```
mvn spring-boot:run
```
or as a packaged jar application
```
java -jar target/api-0.0.1-SNAPSHOT.jar
```

### Endpoints implemented
* Get all Pokémons
```
GET - http://localhost:8080/api/pokemons
```

* Get all Pokémons with Pagination. (This is an example of the first page with 10 Pokémons)
```
GET - http://localhost:8080/api/pokemons?page=0&size=10
```

* Find a specific Pokémon by the Id. (Implemented for testing)
```
GET - http://localhost:8080/api/pokemons/{id}
```
