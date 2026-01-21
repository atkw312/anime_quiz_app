# Sprint 1 — Authentication & JWT

Sprint Duration: 1 week  
Sprint Goal: Implement user authentication with issued JWT tokens  
Status: Completed

## Sprint Goal

The goal of Sprint 1 was to design and implement a secure authentication
system that supports user registration and login, and issues JWT tokens
for authentication.

## Completed Work

The following features were completed during Sprint 1:

- User registration endpoint (`POST /auth/register`)
- User login endpoint (`POST /auth/login`)
- Password hashing using BCrypt
- JWT generation with user ID and expiration
- PostgreSQL persistence using JPA/Hibernate

## Authentication Design

The system uses a JWT-based authentication model.

### Registration Flow
1. Client sends JSON payload with username, email, and password
2. Password is hashed using BCrypt
3. User is stored in the database
4. JWT token is generated and returned

### Login Flow
1. Client submits username and password
2. Credentials are validated
3. JWT token is issued on success

## Known Limitations

- No refresh token support
- No role-based authorization
- No email verification

## Sprint Retrospective

What went well:
- Core authentication features completed on time
- Clean separation between controller, service, and repository layers

What can improve:
- Earlier setup of Spring Security configuration
- Better planning for frontend vs JSON API integration


