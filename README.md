# Booking Management System

Booking Management System is a clinic/hospital booking management application built using **Spring Boot** and **Java**, managed with **Maven**, and deployable via **Docker**.

---

## Technologies Used

* **Java 21 & Spring Boot**
* **Spring Data JPA** with **PostgreSQL / H2**
* **Maven** for dependency and build management
* **Docker** for containerization
* **Spring Cache** for performance optimization
* **Spring Security (optional)** for user management

---

## Features

1. Patient management (CRUD) linked with accounts.
2. Pagination and sorting for patients (`getPagedPatients`).
3. Caching using Spring Cache for faster data access.
4. Unit and Integration Tests to ensure business logic and integration correctness.
5. Docker-ready with CI/CD via GitHub Actions.

---

## Running the Project Locally

### 1. Database

Use **PostgreSQL** or **H2 in-memory** for quick testing.

### 2. Build Project

```bash
mvn clean package -DskipTests
```

### 3. Run Project

```bash
java -jar target/booking-management-system.jar
```

### 4. Run with Docker

```bash
docker build -t ineutrono/booking-management-system:latest .
docker run -p 2006:2006 ineutrono/booking-management-system:latest
```

* Access the app at: `http://localhost:2006`

---

## Tests

* **Unit Tests:** Test service logic with Mockito.
* **Integration Tests:** Test interaction of Controller + Service + Repository + Database.

```bash
mvn test
```

---

## CI/CD

* Configured with **GitHub Actions**
* Automatically builds and pushes Docker image to Docker Hub on push to `main` branch.

---

## Docker Hub

Official image available at: [https://hub.docker.com/repository/docker/ineutrono/booking-management-system/general](https://hub.docker.com/repository/docker/ineutrono/booking-management-system/general)

---

## Contribution

* Developers can add features or improve code.
* Please open Issues or Pull Requests for contributions.

---

## License

* Project is under **MIT License**.
