<h1>Online Book store Demo 👋</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-1.o-blue.svg?cacheSeconds=2592000" />
</p>


> This project is a simulation of an online bookstore.


### Running the application
1. clone the repository
2. run your application using the command below (using command)
```sh
mvn spring-boot:run
```
OR  (using Java command)
```sh
java -jar target/BookStore-0.0.1-SNAPSHOT.jar
```
3. Note that you must have the Java JRE library on your machine.

### Create user
```sh
curl --location 'http://localhost:8082/api/v1/users/create' \
--header 'Content-Type: application/json' \
--data-raw '{
"username": "ayobami",
"password": "@y05@m1",
"email": "sample@email.com",
"firstName": "Ayobami",
"lastName": "Akinyinka",
"phoneNumber": "1234567890",
}'
```


-----------------------------------------------------------------------------


-----------------------------------------------------------------------------
<br><br>

## Author
👤 **Ayobami Akinyinka**
* Github: [@ayoh9](https://github.com/ayoh9)

