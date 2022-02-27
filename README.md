# pokemon-rest
Small application that retrieve a list 0f 500 Pokémons from the PokéAPI and store them in a database. <br/>
The list can be retrieved with HTTP endpoints including the pagination.

#### NOTE:
The Pokémon list is retrieved and stored into the DB when the application starts through a runner process, and given that there are 500 element to fetch,
it could take a bit while.

The DB table is also cleared before every restart of the application.



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

* Go to root project
```
cd pokemon-rest
```

* Change folder mode
```
chmod +x mvnw
```

* Creating an executable JAR
Execute this command from the root project:
```
./mvnw package
```

### Run the application
Execute this command from the root project:
```
java -jar target/*.jar
```

### Endpoints implemented
1. Get all Pokémons
```
GET - http://localhost:8080/api/pokemons
```
- CURL:
`
curl -X GET "http://localhost:8080/api/pokemons" -H "accept: */*"
`

<br/><br/>
2. Get all Pokémons with Pagination. (This is an example of the first page with 10 Pokémons)
```
GET - http://localhost:8080/api/pokemons?page=0&size=10
```
- CURL:
`
curl -X GET "http://localhost:8080/api/pokemons?page=0&size=10" -H "accept: */*"
`

<br/><br/>
3. Find a specific Pokémon by the Id. (Implemented for testing)
```
GET - http://localhost:8080/api/pokemons/{id}
```
- CURL:
`
curl -X GET "http://localhost:8080/api/pokemons/1" -H "accept: */*"
`
