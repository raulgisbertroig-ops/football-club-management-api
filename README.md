# ⚽ Football Club Management API (DTFutbol)

Backend RESTful API designed for the comprehensive management of football clubs, youth categories, coaching staffs, training sessions, match callups, and real-time league standings.

Built with **Java 21** and **Spring Boot 3**, following clean layered architecture principles, stateless **JWT security**, and **OpenAPI 3** documentation standards.

---

## 🚀 Key Features & Business Logic

* **Club & Category Rules Management:** Supports custom category rules (e.g., Grassroots/Benjamín F7, flying substitutions, match periods, and duration).
* **Team & Roster Administration:** Full CRUD operations for teams and players, including DNI lookups and player statistics.
* **Training & Attendance Tracking:** Schedule training sessions and record player attendance rates.
* **Match Day & Callups:** Manage player callups with category limits, record match scores, track match events (goals, cards), and automatically compute league standings.
* **Stateless Security (RBAC):** Secured endpoints using **Spring Security 6**, **JWT (JSON Web Tokens)**, and **BCrypt** password encryption for coach authentication (`ROLE_COACH`).
* **Observability & Live Documentation:** Health monitoring via **Spring Boot Actuator** and interactive API testing via **Swagger UI**.

---

## 🛠️ Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 3.2.5 (Web, Data JPA, Validation, Security, Actuator)
* **Database:** MySQL 8.x (Production/Dev) & H2 (Testing)
* **Security:** JJWT (`0.11.5`) & BCrypt
* **Documentation:** Springdoc OpenAPI 3 / Swagger UI (`2.2.0`)
* **Tools & DevOps:** Maven Wrapper (`mvnw`), Lombok, SLF4J, Docker & Docker Compose, GitHub Actions, Postman, Render, Aiven.

---

## 📂 Project Architecture

The project follows a strict separation of concerns:

text
src/main/java/com/systemicr2/footballclubmanagementapi
├── config/       # SecurityConfig, JwtRequestFilter, DataInitializer
├── controller/   # REST Controllers
├── dto/          # Data Transfer Objects
├── exception/    # Global Exception Handling
├── model/        # JPA Entities
├── repository/   # Spring Data JPA Repositories
├── service/      # Business Logic
└── util/         # JwtUtil

---

## 👨‍💻 Author

**Raül Gisbert Roig**  
*Multiplatform Application Development (DAM) Student & Software Developer*

