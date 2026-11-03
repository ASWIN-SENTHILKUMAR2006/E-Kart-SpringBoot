🛒 Ekart – Backend E-Commerce System (Spring Boot)  : https://urbancanopy.triospark.in/


📌 Overview

Ekart is a backend-focused e-commerce system built using Java and Spring Boot, designed to simulate real-world cart and product management workflows.

This project is not built as a UI-first application. Instead, it focuses on:

Designing proper domain relationships

Implementing business rules

Structuring scalable backend architecture

Thinking in terms of system behavior rather than CRUD operations

The goal of this project is to strengthen my understanding of backend engineering principles while preparing for full-stack Java developer roles.

🎯 Project Objective

The primary goal of Ekart is to:

Move beyond basic CRUD APIs

Implement real-world cart logic

Enforce business constraints (like single active cart per user)

Model entity relationships properly using JPA

Develop clean service-layer architecture

This project is part of my journey toward becoming a strong Java Backend / Full Stack Developer.

🏗️ Architecture & Design Approach

The project follows a layered architecture:

Controller → Service → Repository → Database
Design Principles Applied:

Separation of concerns

Business logic inside Service layer

Repository abstraction using Spring Data JPA

Custom API response structure

Domain-driven thinking (User → Cart → CartItem → Product)

🧠 Core Business Logic Implemented
👤 User Management

Create and retrieve users

One user can have multiple carts (historical)

🛒 Cart Management

A user can have only one ACTIVE cart at a time

Cart is created only when needed

Active cart retrieval logic implemented

📦 CartItem & Product Flow

Products are added to cart via CartItem

If product already exists in cart → quantity updates

Product price is captured at time of addition

Cart-User relationship enforced

CartItem acts as transactional entity between Cart and Product

This ensures the system behaves like a real e-commerce backend instead of isolated APIs.

🛠️ Tech Stack

Java

Spring Boot

Spring Data JPA

Hibernate

MySQL

REST APIs

Maven

🚀 Current Status

✔ User → Cart flow implemented
✔ Cart → CartItem → Product flow implemented
🔄 Improving validation & refining business rules
🔜 Future enhancements planned

🔮 Future Enhancements

Order & Checkout module

Inventory management

DTO pattern implementation

Exception handling refinement

Authentication & Authorization (JWT)

Unit & Integration Testing

Pagination & filtering

Performance optimization

📈 What This Project Demonstrates

This project reflects my:

Understanding of backend architecture

Ability to design relational entity flows

Thinking in terms of business rules

Structured API design approach

Growth from basic CRUD to system-level thinking

👨‍💻 About Me

I am a Computer Science student focused on becoming a strong Java Full Stack Developer.
I am actively building backend systems to improve my understanding of:

Spring ecosystem

System design fundamentals

Database modeling

Scalable API development

This repository is part of my professional learning journey.
