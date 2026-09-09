# Employee API

Spring Boot CRUD for employees, documented with **Swagger / OpenAPI**.

```bash
./mvnw spring-boot:run
```

Windows: `mvnw.cmd spring-boot:run`

## Swagger

| What | URL |
|------|-----|
| Swagger UI | http://localhost:8080/swagger-ui.html |
| OpenAPI JSON (generated) | http://localhost:8080/v3/api-docs |

Config: `config/OpenApiConfig` (title, version, server).  
Operations: `@Tag` / `@Operation` / `@ApiResponse` on `controller/EmployeeController`.

Open Swagger UI → **Employees** → try **GET /api/v1/employees**.

## CRUD

| Method | Path | Status |
|--------|------|--------|
| GET | `/api/v1/employees` | 200 |
| GET | `/api/v1/employees/{id}` | 200, 404 |
| POST | `/api/v1/employees` | 201, 400, 409 |
| PUT | `/api/v1/employees/{id}` | 200, 400, 404, 409 |
| DELETE | `/api/v1/employees/{id}` | 204, 404 |

Seven sample employees load on startup.

## Packages

`controller` → `service` → `repository` (`JpaRepository`) → `model` (`@Entity`)  
Swagger setup is in `config`.

## MySQL

Create a local MySQL user/database (or let JDBC create `employee_db`):

1. Start MySQL.
2. Set `spring.datasource.username` and `spring.datasource.password` in `application.properties`.
3. Run the app. Hibernate creates the `employees` table (`ddl-auto=update`).

Default URL: `jdbc:mysql://localhost:3306/employee_db`

## Automated tests (5 minutes)

```bash
./mvnw test
```

**Story to tell**

1. **JUnit 5** — a method with `@Test` is one check.
2. **Mockito** (`EmployeeServiceTest`) — we do **not** use MySQL. We say: “Pretend person 1 is Ajay.” Then we ask `findById(1)` and check the name. Then we pretend person 99 is missing and check it fails.
3. **@SpringBootTest** (`EmployeeControllerTest`) — start the app and call the real URLs: add Ajay (created), ask for 99999 (not found).

`when(repository.findById(1L)).thenReturn(...)` means: *if someone asks for id 1, give them Ajay.*
