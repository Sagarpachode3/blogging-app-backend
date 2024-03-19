# Blogging Web App Backend

This is the backend component of the Blogging web app, built using Spring Boot. It provides essential functionalities such as user authentication, post management, and integration with a MySQL database. The backend exposes RESTful APIs that are consumed by the frontend to create, retrieve, update, and delete blog posts. Security features, including JWT-based authentication, are implemented to ensure secure access to the application. 

## Dependencies

- **Spring Boot Starter Data JPA:** Simplifies the implementation of data access layer for JPA-compliant data stores.
- **Spring Boot Starter Web:** Provides essential dependencies and auto-configuration for building web applications using Spring MVC.
- **Spring Boot Starter Security:** Provides security features for Spring Boot applications, including authentication and authorization.
- **MySQL Connector/J:** Official JDBC driver for MySQL databases, allowing Spring Boot applications to connect and interact with MySQL databases.
- **jjwt-api, jjwt-impl, jjwt-jackson:** Dependencies for JSON Web Token (JWT) support in Spring Boot applications, providing utilities for generating, parsing, and validating JWTs.

## Getting Started

Ensure that you have Java and Maven installed on your machine. Configure the `application.properties` file with your MySQL database connection details before running the application.

## Usage

The backend provides RESTful APIs for managing blog posts, user authentication, and other functionalities. These APIs are consumed by the frontend of the Blogging web app. Detailed API documentation, generated using Swagger, is available for reference.

## Contributing

Contributions to the backend project are welcome! Please fork this repository and submit a pull request with your changes. For major modifications, please open an issue first to discuss the changes.
