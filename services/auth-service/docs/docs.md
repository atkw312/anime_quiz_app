## Auth Service

This service handles user registration and authentication.
It issues JWT tokens upon successful login.

## Tech Stack
- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- PostgreSQL
- JWT (jjwt)
- Thymeleaf (for demo UI)


## Authentication Flow

1. User submits login credentials
2. Credentials are validated against stored user
3. Password is verified using BCrypt
4. JWT is generated with:
    - subject = userId
    - issuedAt
    - expiration
5. JWT is returned:
    - via JSON for API login
    - via flash attribute for MVC demo

## Endpoints

GET  /auth/register     → signup page  
POST /auth/register     → create user  
GET  /auth/login        → login page  
POST /auth/login        → login (HTML form)  
POST /auth/login/api    → login (JSON, returns JWT)  
GET  /auth/success      → login success page  
