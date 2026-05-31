# Project Management System - Backend API

## Overview

Project Management System is a RESTful backend application built using Spring Boot and PostgreSQL. The system allows organizations to manage Projects, Tasks, Team Members, and Users through secure APIs.

The application follows a layered architecture and implements JWT Authentication with Role-Based Access Control (RBAC).

---

## Features

### Project Management

* Add Project
* Update Project
* Delete Project
* Get All Projects
* Get Project By ID

### Task Management

* Add Task
* Update Task
* Delete Task
* Get All Tasks
* Get Task By ID

### Team Member Management

* Add Team Member
* Update Team Member
* Delete Team Member
* Get All Team Members
* Get Team Member By ID

### Security Features

* User Registration
* User Login
* JWT Authentication
* BCrypt Password Encryption
* Role-Based Access Control (ADMIN, MANAGER, MEMBER)

### Additional Features

* Input Validation
* Global Exception Handling
* Logging
* Swagger Documentation
* DTO Pattern
* PostgreSQL Integration

---

## Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate

### Database

* PostgreSQL

### Authentication

* JWT (JSON Web Token)
* BCrypt Password Encoder

### Documentation & Testing

* Swagger/OpenAPI
* Postman

### Build Tool

* Maven

---

## Project Architecture

Controller Layer

↓

Service Layer

↓

Repository Layer

↓

PostgreSQL Database

The project follows a layered architecture to maintain clean separation of concerns and improve maintainability.

---

## Entity Relationships

### Project ↔ Task

One Project can contain multiple Tasks.

### Team Member ↔ Task

One Team Member can be assigned multiple Tasks.

---

## Security Implementation

### Authentication Flow

User Login

↓

Credentials Validation

↓

JWT Token Generation

↓

Bearer Token Sent With Requests

↓

JWT Authentication Filter

↓

Access Granted

### Roles

#### ADMIN

* Full access to all APIs
* Manage Projects
* Manage Tasks
* Manage Team Members

#### MANAGER

* View Projects
* View Team Members
* Create and Update Tasks

#### MEMBER

* View Projects
* View Tasks

---

## API Endpoints

### Authentication APIs

#### Register User

POST `/api/auth/register`

#### Login User

POST `/api/auth/login`

---

### Project APIs

#### Add Project

POST `/api/projects/add`

#### Get All Projects

GET `/api/projects/getAll`

#### Get Project By ID

GET `/api/projects/get/{id}`

#### Update Project

PUT `/api/projects/update/{id}`

#### Delete Project

DELETE `/api/projects/delete/{id}`

---

### Team Member APIs

#### Add Team Member

POST `/api/members/add`

#### Get All Team Members

GET `/api/members/getAll`

#### Get Team Member By ID

GET `/api/members/get/{id}`

#### Update Team Member

PUT `/api/members/update/{id}`

#### Delete Team Member

DELETE `/api/members/delete/{id}`

---

### Task APIs

#### Add Task

POST `/api/tasks/add`

#### Get All Tasks

GET `/api/tasks/getAll`

#### Get Task By ID

GET `/api/tasks/get/{id}`

#### Update Task

PUT `/api/tasks/update/{id}`

#### Delete Task

DELETE `/api/tasks/delete/{id}`

---

## Swagger Documentation

After running the application:

http://localhost:8080/swagger-ui/index.html

---

## How To Run

### Clone Repository

git clone YOUR_GITHUB_REPOSITORY_URL

### Configure PostgreSQL

Update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/project_management_system

spring.datasource.username=postgres

spring.datasource.password=your_password

### Run Application

mvn spring-boot:run

or run ProjectManagementSystemApplication.java from IDE.

---

## Sample Roles

### ADMIN

* Full Access

### MANAGER

* Project Monitoring
* Task Management

### MEMBER

* View Assigned Resources

---

## Future Enhancements

* Pagination & Sorting
* Docker Support
* Unit Testing
* Email Notifications
* Cloud Deployment

---

## Author

Nikhil Prajapati

Backend Developer | Java | Spring Boot | PostgreSQL | Spring Security
