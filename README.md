# 📝 Task Management System (Spring Boot + JWT)

## 🚀 Project Overview

This is a secure Task Management backend application built using Spring Boot.  
It supports user authentication, role-based authorization, and task management with JWT-based security.

---

## 🔧 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA (Hibernate)
- MySQL / PostgreSQL
- Lombok

---

## 🔐 Features

### 👤 Authentication
- User Registration
- User Login
- Password Encryption using BCrypt
- JWT Access Token & Refresh Token

---

### 🔑 Authorization
- Role-based access control:
  - USER → Can manage only their own tasks
  - ADMIN → Can access all tasks

---

### 📋 Task Management
- Create Task
- Get Tasks (role-based)
- Update Task
- Delete Task

---

### ⚙️ Security
- JWT-based authentication
- Stateless session management
- Protected APIs
- Token validation using filter

---

### ⚠️ Exception Handling
- Global Exception Handling using @RestControllerAdvice
- Custom Exceptions:
  - ResourceNotFoundException
  - UnauthorizedException
  - TokenExpiredException

---

### ✅ Validation
- Input validation using annotations (@NotBlank, @Email, etc.)
- Clean error responses

---

## 📁 Project Structure
# 📝 Task Management System (Spring Boot + JWT)

## 🚀 Project Overview

This is a secure Task Management backend application built using Spring Boot.  
It supports user authentication, role-based authorization, and task management with JWT-based security.

---

## 🔧 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA (Hibernate)
- MySQL / PostgreSQL
- Lombok

---

## 🔐 Features

### 👤 Authentication
- User Registration
- User Login
- Password Encryption using BCrypt
- JWT Access Token & Refresh Token

---

### 🔑 Authorization
- Role-based access control:
  - USER → Can manage only their own tasks
  - ADMIN → Can access all tasks

---

### 📋 Task Management
- Create Task
- Get Tasks (role-based)
- Update Task
- Delete Task

---

### ⚙️ Security
- JWT-based authentication
- Stateless session management
- Protected APIs
- Token validation using filter

---

### ⚠️ Exception Handling
- Global Exception Handling using @RestControllerAdvice
- Custom Exceptions:
  - ResourceNotFoundException
  - UnauthorizedException
  - TokenExpiredException

---

### ✅ Validation
- Input validation using annotations (@NotBlank, @Email, etc.)
- Clean error responses

---

## 📁 Project Structure
controller/
service/
repository/
entity/
dto/
config/
exception/

---

## 🔄 API Endpoints

### 🔐 Auth APIs

| Method | Endpoint | Description |
|--------|----------|------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login user |
| POST | `/api/auth/refresh` | Refresh access token |

---

### 📋 Task APIs (Secured)

| Method | Endpoint | Description |
|--------|----------|------------|
| POST | `/api/tasks` | Create task |
| GET | `/api/tasks` | Get tasks |
| PUT | `/api/tasks/{id}` | Update task |
| DELETE | `/api/tasks/{id}` | Delete task |

---

## 🔐 Authentication Usage

For secured APIs, add header:
Authorization: Bearer <access_token>
---

## 🧠 Business Logic

- Users can access only their own tasks
- Admin users can access all tasks
- JWT is used for authentication
- Refresh token is used to generate new access tokens

---

## 🧪 Testing

Tested using Postman:
- Register → Login → Get Token
- Use token to access secured APIs
- Tested scenarios:
  - Unauthorized access
  - Invalid token
  - Validation errors

---

## ⚡ How to Run

1. Clone the repository
2. Configure database in `application.properties`
3. Run the application
4. Use Postman to test APIs

---

## 👨‍💻 Author

Kalaivanan

---

## ⭐ Highlights

- Clean architecture (Controller → Service → Repository)
- JWT Authentication
- Role-based Authorization
- Global Exception Handling
- Production-level design
