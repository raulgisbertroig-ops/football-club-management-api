## 🛠️ Stack Tecnológico
- **Lenguaje:**Java
- **Framework:**Spring Boot
- **Capa de Persistencia:** Spring Data JPA / Hibernate
- **Base de Datos:**MySQL
- **Construcción y Dependencias:**Maven
- **Testing de Red:**Postman

## ⚙️ Arquitectura y Estado Actual (MVP - Fase 1)
Actualmente, el sisttema implementa la capa de Dominio y Servicio para un CRUD relacional asegurando la integridad referencial (Foreign Keys). La topologia de entidades se estructura de la siguiente manera:
* "Club" (entidad Raíz)
* "Team" (Equipos vinculados a un club mediante relacionship 1:N)
*  "Player" y "TrainingSession" (Vinculados a su respectivo equipo)

El diseño prioriza la encapsulación, delegando la lógica de negocio a la capa "@Service" e inyectando dependencias mediante el contenedor del IoC de Spring Boot.

## 🚀 Despliegue Local (Setup)
1. **Clonar el repositorio:**
   '''bash
  git clone https://github.com/raulgisbertroig-ops/football-club-management-api.git
