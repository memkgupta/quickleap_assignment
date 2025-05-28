
# 📦 Quickleap Assignment – Microservices Architecture

This repository contains a simple microservices-based application designed for managing users and their subscriptions. It demonstrates the implementation of service discovery, API gateway, and communication between multiple Spring Boot services.

---

## 📁 Folder Structure

```plaintext
quickleap_assignment/
├── api_gateway/           # Handles routing all external requests to internal services
├── service_discovery/     # Eureka-based service registry
├── subscription_service/  # CRUD operations for subscriptions
├── user_service/          # User registration, login, and authentication
```

---

## 🔧 Tech Stack Used

* **Java** (JDK 17)
* **Spring Boot**
* **Spring Cloud Eureka** – for service discovery
* **Spring Cloud Gateway** – for routing/API gateway
* **Spring Security** – for securing endpoints
* **JWT** – for authentication
* **Maven** – as the build tool
* **PostgreSQL** – as database

---

## 🛠️ Setup Instructions

### 1. Clone the repository:

```bash
git clone https://github.com/memkgupta/quickleap_assignment.git
cd quickleap_assignment
```

### 2. Start the Eureka Server:

```bash
cd service_discovery
mvn spring-boot:run
```

### 3. Start the API Gateway:

```bash
cd api_gateway
mvn spring-boot:run
```

### 4. Start the User Service:

```bash
cd user_service
mvn spring-boot:run
```

### 5. Start the Subscription Service:

```bash
cd subscription_service
mvn spring-boot:run
```

> ⚠️ Make sure all services register correctly with Eureka before testing any endpoint.

---
---
## API DOCUMENTATION

You can find swagger api docs after running Subscription service and User Service at these endpoints
http://localhost:USER_SERVICE_PORT/swagger-ui.html
http://localhost:SUBSCRIPTION_SERVICE_PORT/swagger-ui.html
---
## 🧪API Endpoints

### ✅ User Service

* `POST /users/register` – User registration
* `POST /users/login` – Returns JWT token
* `GET /users/{id}` – Get user info (secured)

### 📬 Subscription Service

* `POST /subscriptions` – Create a subscription
* `GET /subscriptions/{id}` – Get details
* `PUT /subscriptions/{id}` – Update
* `DELETE /subscriptions/{id}` – Cancel

> All requests should go through the gateway for proper routing.

---

## 🔐 Security

* JWT tokens are issued after login
* Tokens are required for accessing protected routes (like `/subscriptions/**`)
* Send token in the `Authorization` header:

```
Authorization: Bearer <token>
```

---

## 📌 Notes

* Eureka acts as the central registry.
* Gateway routes all incoming requests to appropriate services based on the path.
* Internal services are decoupled and communicate independently.

---

