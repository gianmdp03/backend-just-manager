# 🌿 Just-Manager Backend API

A comprehensive "Full-Stack" inventory, sales, and customer management system, developed as a RESTful API in Spring Boot. This backend is specifically designed to manage physical stock, strictly control product expiration dates (ideal for cosmetics, essential oils, and wellness products), manage storage locations, and process sales orders.

## 🚀 Technologies Used

The project is built with a modern and robust stack based on the Spring ecosystem:

* **Java 25**: Main programming language.
* **Spring Boot 4.0.1**: Core framework for the application.
* **Spring Data JPA / Hibernate**: ORM for data persistence and database abstraction.
* **Spring Security & JJWT (0.12.6)**: Authentication and authorization based on JSON Web Tokens (JWT).
* **MySQL**: Relational database (via `mysql-connector-j`).
* **MapStruct (1.6.3)**: Automatic mapping between Entities and DTOs.
* **Lombok**: Boilerplate code reduction (getters, setters, constructors).
* **SpringDoc OpenAPI**: Automated API documentation (Swagger UI).
* **JUnit 5 & AssertJ**: Unit and integration testing.

## ⚙️ Architecture and Patterns

* **DTO Pattern (Data Transfer Object)**: Strict separation between database entities and the objects returned to the client using `MapStruct` to prevent sensitive data exposure and cyclic serialization issues.
* **Soft Delete (Logical Deletion)**: Products are not physically deleted from the database to preserve the integrity of the order history. An `enabled` flag is used instead.
* **Global Exception Handling**: Implementation of `@RestControllerAdvice` to catch exceptions (`NotFoundException`, `ConflictException`, `BadRequestException`) and return standardized HTTP responses.
* **CORS Configured**: Natively prepared to be consumed from an Angular frontend (`http://localhost:4200`).

---

## 📡 API Endpoints (For Postman)

The API has built-in pagination for almost all `GET` methods (default `page=0`, `size=18`). 

### 👥 1. Customers (`/api/customers`)
Management of the customer list and their contact information.

* `POST /api/customers` - Create a new customer.
    * **Body:** `{"fullname": "Juan Perez", "phoneNumber": "2231112222"}`
* `GET /api/customers` - List all customers (Paginated).
* `GET /api/customers/{fullname}` - Search customers by full name.
* `GET /api/customers/id/{id}` - Get the details of a specific customer by ID.
* `PATCH /api/customers/{id}` - Partially update a customer's data.
* `DELETE /api/customers/{id}` - Delete a customer.

### 📦 2. Products (`/api/products`)
Product catalog (e.g., "Eucasol", "Oleo 31"). Features *soft deletion*.

* `POST /api/products` - Create a new product.
    * **Body:** `{"name": "Thyme Cream", "imageUrl": "image_url.jpg"}`
* `GET /api/products` - List active products.
* `GET /api/products/{name}` - Search product by name.
* `GET /api/products/id/{id}` - Get product details.
* `PATCH /api/products/{id}` - Update product data.
* `DELETE /api/products/{id}` - Soft delete a product (`enabled = false`).

### 📍 3. Locations (`/api/locations`)
Management of physical spaces where stock is stored (e.g., "Shelf A", "Safe Box").

* `POST /api/locations` - Register a new location.
    * **Body:** `{"name": "Main Cabinet"}`
* `GET /api/locations` - List all locations.
* `GET /api/locations/{name}` - Search location by name.
* `GET /api/locations/id/{id}` - View location and its associated items.
* `PATCH /api/locations/{id}` - Update location name.
* `DELETE /api/locations/{id}` - Delete location (Throws `ConflictException 409` if it contains stock).

### 🗃️ 4. Inventory (`/api/inventory-items`)
The core of stock control. Links Products, Locations, Quantities, and **Expiration Dates**.
*(Note: `{localDate}` must be in `YYYY-MM-DD` format)*.

* `POST /api/inventory-items` - Enter a new batch into stock.
    * **Body:** `{"productId": 1, "locationId": 2, "stock": 50, "expireDate": "2026-12-31"}`
* `GET /api/inventory-items/{localDate}` - List all valid stock from a given date onwards.
* `GET /api/inventory-items/{localDate}/product/{productId}` - View stock for a specific product.
* `GET /api/inventory-items/{localDate}/location/{locationId}` - View all stock in a specific location.
* `GET /api/inventory-items/{localDate}/expired` - 🚨 **EXPIRED stock report** prior to the given date.
* `GET /api/inventory-items/{localDate}/almost/{days}` - ⚠️ **Expiring soon report** for stock expiring in the next `X` days.
* `PATCH /api/inventory-items/{id}` - Update quantity or move to another location.
    * **Body:** `{"stock": 45, "locationId": 3}`
* `DELETE /api/inventory-items/{id}` - Delete an inventory record.

### 🛒 5. Orders / Sales (`/api/orders`)
Registration of sales to customers with multiple items.

* `POST /api/orders/{localDateTime}/{customerId}` - Create a new sale with its items. *(e.g., `2026-03-07T10:15:30`)*.
    * **Body:** `[ {"productId": 1, "amount": 2}, {"productId": 3, "amount": 1} ]`
* `GET /api/orders` - General sales history.
* `GET /api/orders/{startDate}/{endDate}` - Filter sales by date range (`YYYY-MM-DD`/`YYYY-MM-DD`).
* `GET /api/orders/id/{id}` - Get the full detail of an order and its items.
* `GET /api/orders/{orderId}` - Secondary interface to list only the items of a specific order.
* `DELETE /api/orders/{id}` - Cancel/Delete a sale (Cascades to delete items).
* `DELETE /api/orders/item/{id}` - Delete a specific item from a sale without deleting the entire order.

---

## 🔒 Security (JWT Authentication)
The application includes the domain infrastructure for `User` and `Role`. JWT token validation restricts access to the API (internal system management). For registration or login, it will be validated through the `AuthenticationRequest` DTOs, and the response will provide a `token` in JWT format.

## 🛠️ How to run the project locally

1. Clone the repository.
2. Configure the MySQL credentials in `src/main/resources/application.properties` (Make sure you have a schema created, for example, `just_manager_db`).
3. Define your JWT secret key in the properties: `application.security.jwt.secret-key`.
4. Run using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
