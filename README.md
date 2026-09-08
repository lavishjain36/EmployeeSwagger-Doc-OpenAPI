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
Examples: `@Schema` on `model/Employee`.

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
