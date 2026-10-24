# ⚽ Football Club Management API (DTFutbol)

A RESTful backend API for football club management, developed with Java 21 and Spring Boot 3. The project is designed to manage clubs, teams, players, training sessions, and other football-related operations.

The application follows a layered architecture and uses Spring Data JPA for persistence, Spring Security and JWT for authentication, and MySQL for database storage.

## 🚀 Features

- **Club and team management:** Organize football clubs, teams, and categories.
- **Player management:** Manage player information and team associations.
- **Training management:** Create, retrieve, update, and delete training sessions.
- **Authentication and security:** Spring Security, JWT-based authentication, and BCrypt password hashing.
- **Data persistence:** Spring Data JPA and Hibernate with MySQL.
- **API documentation:** OpenAPI 3 and Swagger UI.
- **Health monitoring:** Spring Boot Actuator.
- **Automated build validation:** Maven and GitHub Actions.
- **Deployment:** Hosted on Render with a MySQL database hosted on Aiven.

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Spring Boot 3.2.5 | Backend framework |
| Spring Web | REST endpoints |
| Spring Data JPA | Data access |
| Hibernate | ORM |
| MySQL | Relational database |
| Spring Security | Application security |
| JWT (JJWT 0.11.5) | Token-based authentication |
| BCrypt | Password hashing |
| Springdoc OpenAPI | API documentation |
| Maven Wrapper | Build and dependency management |
| Docker / Docker Compose | Containerization |
| GitHub Actions | Continuous integration |
| Render | API deployment |
| Aiven | Managed database hosting |

## 🌐 Deployment

**Production API:**  
https://footballclubmanagement-api-latest.onrender.com

**Interactive API documentation:**  
https://footballclubmanagement-api-latest.onrender.com/swagger-ui/index.html

The availability of the API and its documentation may depend on the deployment status of the hosting service.

## 💻 Run the Project Locally

### Prerequisites

- Java 21
- Git
- Access to a configured MySQL database for local execution

### 1. Clone the repository

```bash
git clone https://github.com/raulgisbertroig-ops/football-club-management-api.git
cd football-club-management-api
```

### 2. Configure the environment

Configure the database connection and JWT settings using the environment variables or local configuration expected by the application.

Keep credentials and signing secrets private. Do not commit `.env` files, database passwords, or JWT secrets to Git.

### 3. Run the application

On Windows, use the Maven Wrapper:

```powershell
.\mvnw.cmd spring-boot:run
```

The application will start on the port configured for the project, typically `8080`.

### 4. Build and verify

To clean the previous build, compile the application, run the configured verification checks, and package the application:

```powershell
.\mvnw.cmd clean verify
```

A successful build ends with:

```text
BUILD SUCCESS
```

## 🧪 Verification

The project has been checked using the Maven verification lifecycle and GitHub Actions.

The training endpoints were also manually verified against the deployed API:

- **Create:** `POST /api/trainings` — HTTP `201 Created`.
- **List:** `GET /api/trainings?page=0&size=10` — HTTP `200 OK`.
- **Update:** `PUT /api/trainings/{id}` — HTTP `200 OK`.
- **Delete:** `DELETE /api/trainings/{id}` — HTTP `204 No Content`.

The temporary training created during verification was deleted, and the subsequent list request confirmed its removal.

These checks document the training workflow verified during this project; they do not imply that every endpoint has undergone exhaustive testing.

## 📂 Project Structure

```text
src/main/java/com/systemicr2/footballclubmanagementapi/
├── config/       # Security configuration, JWT filter and initialization
├── controller/   # REST controllers
├── dto/          # Data Transfer Objects
├── exception/    # Exception handling
├── model/        # JPA entities
├── repository/   # Spring Data JPA repositories
├── service/      # Business logic
└── util/         # Utility classes, including JWT utilities
```

## 🔐 Security Notes

- Authentication uses JWT-based security.
- Passwords are protected using BCrypt hashing.
- Database credentials and JWT signing secrets must be supplied through private configuration.
- The `.env` file is intended for local configuration and must not be tracked by Git.
- If a secret has been exposed, replace it with a new secret and update the deployment configuration.

## 👨‍💻 Author

**Raül Gisbert Roig**  
Multiplatform Application Development (DAM) Student | Aspiring Software Developer

## 📄 Project Status

This project demonstrates the development, configuration, deployment, and verification of a Spring Boot REST API for football club management. Features and verification coverage may evolve as development continues.