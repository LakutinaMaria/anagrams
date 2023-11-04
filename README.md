# Anagram service 

The Anagram Service is a Java-based application that provides functionalities to check whether two words are anagrams and retrieve anagrams for a given word.


## Requirements

For building and running the application you need:
- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org)


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.beyonnex.anagram.AnagramApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

`
mvn spring-boot:run
`
After running locally application is available at the link http://localhost:8080

### DB console
After running the application locally, the DB console available at the link http://localhost:8080/h2-console
username=admin password=password
## Usage
### Endpoints

/areAnagrams: Endpoint to check if two words are anagrams. Example: http://localhost:8080/areAnagrams?word1=listen&word2=silent

/getAllAnagrams: Endpoint to retrieve anagrams for a word. Example: http://localhost:8080/getAllAnagrams?word=test
### Logging

The application logs activities and results for areAnagrams and getAllAnagrams methods.

### Testing

The application includes unit tests for the AnagramService class using JUnit and Mockito. You can run tests using:

```shell
mvn test
```


#
#

Created by [Maria Lakutina](https://www.linkedin.com/in/maria-lakutina) 
