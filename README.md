# AutoPlan-DB

AutoPlan-DB is the backend database and REST API system for managing users, user settings, and lessons in the AutoPlan platform. Built with Java and Spring Boot, it connects to a MySQL database and exposes data through a RESTful interface.

## 📦 Project Structure

```arduino
AutoPlan-DB/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/
│ │ │ ├── users/ # UserController, UserDAO, User model
│ │ │ ├── settings/ # UserSettingsController, DAO, model
│ │ │ ├── lessons/ # LessonController, DAO, model
│ │ │ └── AutoPlanDbApplication.java # Main application entry
│ │ └── resources/
│ │ └── application.properties # DB config
├── pom.xml
└── README.md
```


---

## 🔧 Technologies

- Java 17+
- Spring Boot
- MySQL
- JDBC
- Maven
- RESTful API

---

## 🗄️ Database Schema

**Tables:**
- `users` – Contains `user_id (CHAR(36))`, `username`, etc.
- `user_settings` – Contains preferences/settings per user
- `lessons` – Contains lesson content, each linked to a user via `user_id`

**Relationships:**
- One `user` → Many `lessons`
- One `user` → One `user_settings`

---

## 🚀 Setup & Run

### 1. Clone the repository
```bash
git clone https://github.com/your-username/AutoPlan-DB.git
cd AutoPlan-DB
```

### 2. Configure MySQL connection
Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/autoplan
spring.datasource.username=username
spring.datasource.password=1234567890
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
server.port=8080
```

### 3. Run the Server
With Maven:
```bash
./mvnw spring-boot:run
```
Or from your IDE (IntelliJ, Eclipse): run `AutoPlanDbApplication.java`.

## 🔗API Endpoints

### Users
- `GET /users` – List all users

- `GET /users/{id}` – Get a user by ID

- `POST /users` – Create a new user

- `PUT /users/{id}` – Update user

- `DELETE /users/{id}` – Delete user

### Lessons
- `GET /lessons` – List all lessons

- `GET /lessons/{id}` – Get lesson by ID

- `POST /lessons` – Add a lesson

- `DELETE /lessons/{id}` – Delete lesson

### User Settings
- `GET /settings/{userId}` – Get settings for a user

- `PUT /settings/{userId}` – Update settings

## 🧪 Testing
You can test the API with:
- [Postman](https://www.postman.com)

- `curl`

- Or connect with a front-end (e.g., React, HTML/JS)

## ✅ TODO
- Add authentication

- Integrate Swagger for API docs

- Add unit tests

- Improve exception handling

## 📄 License
MIT License © [Jack Rose (RA4)]
