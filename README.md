# Book Recommendation Management
A lightweight Book Recommendation web application built using Spring Boot, Thymeleaf-like static HTML, Spring Data JPA, and H2/MySQL.
Users can add books, view available books, and get simple recommendations based on book genres.

This project follows a clean MVC structure and includes custom exception handling, DAO/JPA repository, and a minimal frontend UI.

---

#🌟 Features
**✅ Book Management**

Add new books

View all books

Search books (by genre/title)

Delete or update (via backend APIs)

**🎯 Recommendation Engine (Rule-Based)**

Recommends books based on:

Genre matching

Basic filtering logic (from BookService)

User-selected genre on the UI page

**🖥 Simple UI**

Static HTML pages served from /static:

index.html – Home page

view-books.html – View all books

recommend.html – Select genre → recommended books

style.css – Basic styling

---

#Tech Stack
| Layer              | Technology                        |
| ------------------ | --------------------------------- |
| Backend            | Spring Boot                       |
| ORM                | Spring Data JPA                   |
| Database           | H2 / MySQL                        |
| Build Tool         | Maven                             |
| UI                 | HTML, CSS (served from `/static`) |
| Exception Handling | Custom GlobalExceptionHandler     |

---

#⚙️ Setup & Installation

**1️⃣ Clone the repository**
git clone https://github.com/your-username/BookRecommendation_With_UI.git
cd BookRecommendation_With_UI/BookRecommendation

**2️⃣ Configure Database**

H2 (default)
spring.datasource.url=jdbc:h2:mem:bookdb
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true

**3️⃣ Run the project**
mvn spring-boot:run

---

#Testing
Use Postman or browser:
GET http://localhost:8080/books


