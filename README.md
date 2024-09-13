## Spring Boot Account Management API
### Overview
This Spring Boot application provides a RESTful API for managing user accounts. The API includes endpoints for creating, retrieving, updating, and deleting user accounts, as well as user authentication.

### Features
•	User account management (CRUD operations)
•	Secure password storage with BCrypt
•	User authentication with email and password
•	In-memory H2 database for development and testing
•	Swagger API documentation

### Prerequisites
•	Java 17 or higher
•	Maven
•	IDE (e.g., IntelliJ IDEA, VS Code)

## Setup and Installation
### Clone the Repository
git clone[ https://github.com/yourusername/account-management-api.git](https://github.com/iam-karthick/account-management/]
cd account-management-api

## Build the Application
### Use Maven to build the application:
mvn clean install

### Run the Application
You can run the application using Maven:
#### mvn spring-boot:run

### Alternatively, you can run the JAR file:
java -jar target/account-management-api-0.0.1-SNAPSHOT.jar
The application will start on http://localhost:8080.

## API Endpoints
### Authentication
•	POST /api/accounts/login
Request Body:
json

{
  "email": "user@example.com",
  "password": "password123"
}
Response:
json

{
  "id": 1,
  "name": "John Doe",
  "email": "user@example.com"
}
User Management
•	POST /api/accounts
Request Body:
json

{
  "name": "John Doe",
  "email": "user@example.com",
  "password": "password123"
}
Response:
json

{
  "id": 1,
  "name": "John Doe",
  "email": "user@example.com"
}
•	GET /api/accounts
Response:
json

[
  {
    "id": 1,
    "name": "John Doe",
    "email": "user@example.com"
  }
]
•	GET /api/accounts/{id}
Response:
json

{
  "id": 1,
  "name": "John Doe",
  "email": "user@example.com"
}
•	PUT /api/accounts/{id}
Request Body:
json

{
  "name": "Jane Doe",
  "email": "user@example.com",
  "password": "newpassword123"
}
Response:
json

{
  "id": 1,
  "name": "Jane Doe",
  "email": "user@example.com"
}
•	DELETE /api/accounts/{id}
Response: 204 No Content

## API Documentation
API documentation is available via Swagger UI. Access it at http://localhost:8080/swagger-ui.html once the application is running.

## Testing
The application includes unit tests. To run the tests, use:
mvn test

## Postman Collection
A Postman collection for testing the API is available. Download Postman Collection.

## Contact
For any questions or issues, please contact:
•	Email: karthickd94@outlook.com
•	GitHub: [iam-karthick](https://github.com/iam-karthick/)


