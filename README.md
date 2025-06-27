# Quiz Application - Microservices Architecture

This project is a **Quiz Application** built using **Spring Boot** and follows a **microservices architecture**. It allows users to create quizzes based on categories, attempt them, and receive scores. The system is split into separate services for better scalability and maintainability.

## 🛠️ Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Feign Client (Inter-service Communication)
- MySQL
- RESTful APIs
- Maven

## 📂 Microservices Overview

### 1. **Question-Service**
- Manages quiz questions.
- Exposes endpoints to fetch questions by category and number.
- Stores and retrieves question data from the database.

### 2. **Quiz-Service**
- Creates quizzes using questions fetched from Question-Service.
- Stores quiz metadata (category, questions selected).
- Evaluates answers submitted and calculates scores.

## 🔗 Service Communication

- Uses **OpenFeign** to make HTTP requests between services.
- `Quiz-Service` calls `Question-Service` to fetch relevant questions when creating a quiz.

## 📌 Features

- Create quizzes based on category and number of questions.
- Retrieve and display questions to the user.
- Submit answers and receive an auto-evaluated score.
- Microservices communicate seamlessly using Feign clients.
- Scalable architecture suitable for cloud deployment.

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL
- Postman (for testing APIs)

 🔍 API Endpoints
Question Service
GET /questions/category/{category} – Get questions by category

GET /questions/category/{category}/count/{count} – Get N questions from a category

Quiz Service
POST /quiz/create – Create a quiz

GET /quiz/{id} – Get quiz questions

POST /quiz/submit – Submit answers and get score  
