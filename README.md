# Football Club Management API

REST API backend for football club management.

This project is developed as a personal learning project to practice backend development with Java, Spring Boot, REST APIs, relational databases and JPA/Hibernate.

## 🛠️ Technologies

- **Language:** Java 21
- **Framework:** Spring Boot
- **Persistence:** Spring Data JPA / Hibernate
- **Database:** MySQL
- **Build & Dependencies:** Maven
- **API Testing:** Postman
- **Security:** Spring Security
- **Authentication:** JWT

## 🏗️ Architecture

The application follows a layered architecture that separates HTTP handling, business logic, persistence and database access.

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
MySQL
```

### Main layers

- **Controller:** Handles HTTP requests and responses.
- **Service:** Contains the application and business logic.
- **Repository:** Manages data persistence through Spring Data JPA.
- **MySQL:** Stores the application data.

## ⚽ Domain Model

The current domain model includes the following main entities:

- **Club:** Root entity representing a football club.
- **Team:** Team associated with a club.
- **Player:** Player associated with a team.
- **TrainingSession:** Training session associated with a team and players.
- **Match:** Represents football matches.
- **Callup:** Manages player call-ups.
- **Standings:** Manages competition standings.

The relationships between the entities are modelled using JPA/Hibernate.

## 🔗 API Endpoints

### Players

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/players/team/{teamId}` | Create a player associated with a team |
| POST | `/api/players/{dni}/trainings/{trainingId}` | Assign a training session to a player |
| GET | `/api/players` | Get all players |
| GET | `/api/players/{dni}` | Get a player by DNI |
| GET | `/api/players/team/{teamId}` | Get players by team |
| PUT | `/api/players/{id}` | Update a player |
| DELETE | `/api/players/{id}` | Delete a player |

Additional controllers are available for authentication, clubs, teams, matches, standings, call-ups and training sessions.

## 📌 Current Status

The project is currently under active development as part of my backend learning process.

The current MVP includes:

- Core domain entities
- Relational entity relationships
- Business logic
- REST endpoints
- JPA/Hibernate persistence
- MySQL database integration
- Basic automated testing
- Security and authentication components

Further improvements will be added progressively.

## 🚀 Local Setup

### 1. Clone the repository

```bash
git clone https://github.com/raulgisbertroig-ops/football-club-management-api.git
cd football-club-management-api
```

### 2. Configure MySQL

Create the required MySQL database locally.

### 3. Configure database credentials

Set the following environment variables in your local development environment:

```text
DB_USERNAME
DB_PASSWORD
```

### 4. Run the application

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The application will start locally and expose the REST API.

## 🧪 Testing

The project includes automated tests for the application context and repository functionality.

Tests are executed using Maven:

```bash
mvnw.cmd test
```

## 🔐 Security

The project includes Spring Security and JWT-based authentication components.

Sensitive credentials should never be committed to the repository.

## 🗺️ Roadmap

- Complete API endpoint documentation
- Expand automated test coverage
- Improve validation and exception handling
- Improve DTO usage
- Improve authentication and authorization
- Improve database configuration
- Add API documentation
- Containerize the application with Docker
- Explore AI integration in future versions

## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Author

**Raül Gisbert Roig**

DAM Student | Backend & Software Development

[LinkedIn](https://www.linkedin.com/in/raulgisbertroig/)
