# Mobile Software Store

The application allows a user to view programs available for sale by the following categories:
- Games;
- Education;
- Health.

Also, the app allows the downloading of programs.

### Following technologies being used:

- Maven 3
- JDK 1.8
- Tomcat 9.0.17
- H2database
- Spring Boot 2.1.4
- Flyway 5.2.4
- MySQL 8.0.15

### Login information:

- user/user
- developer/developer

### Configuration:

There are three spring profiles: 
 - test
 - main
 - prod
 
 Test profile works with embedded in-memory H2 database. Main and prod profiles work with MySQL database.
 Prod profile uses container-managed DataSource provided via JNDI in case of deployment to external Servlet Container.
 Name of resource is jdbc/AppstoreDatabase.
 
 Next command line arguments are available: 
 - --port - server HTTP port;
 - --profile - comma-separated list of active profiles;
 - --ds.url - JDBC URL of the database;
 - --ds.username - Login username of the database;
 - --ds.password - Login password of the database.
 
 Example: java -jar appstore-0.0.1-SNAPSHOT.war --profile=main