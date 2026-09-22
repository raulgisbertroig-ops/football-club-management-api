## 🛠️ Stack Tecnológico
- **Lenguaje:**Java
- **Framework:**Spring Boot
- **Capa de Persistencia:** Spring Data JPA / Hibernate
- **Base de Datos:**MySQL
- **Construcción y Dependencias:**Maven
- **Testing de Red:**Postman

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

Main layers
Controller: Handles HTTP requests and responses.
Service: Contains the application and business logic.
Repository: Manages data persistence through Spring Data JPA.
MySQL: Stores the application data.

Main layers
Controller: Handles HTTP requests and responses.
Service: Contains the application and business logic.
Repository: Manages data persistence through Spring Data JPA.
MySQL: Stores the application data.

## 🚀 Despliegue Local (Setup)
1. **Clonar el repositorio:**
   '''bash
  git clone https://github.com/raulgisbertroig-ops/football-club-management-api.git
