**Quiz App (Spring Boot) — Backend**

**Status**: IN PROGRESS Core quiz and question REST APIs implemented; polishing, tests, and deployment pending.

**Overview**
- **Purpose**: Lightweight backend for creating, serving, and grading quizzes. Designed as a RESTful Spring Boot service to support a frontend or mobile client.

**Features**
- **Create quiz**: Generate a quiz from a question pool by category and number of questions.
- **Fetch quiz questions**: Retrieve questions for a specific quiz instance.
- **Submit answers**: Submit user responses and receive a score/summary.
- **Manage questions**: Add questions and fetch questions by category or all questions.

**Tech Stack**
- **Language**: Java 17
- **Framework**: Spring Boot (Web + Data JPA)
- **Database**: PostgreSQL (runtime dependency present; can use H2 for local testing)
- **Build**: Maven

**API Endpoints**
- **Questions**
	- **GET** `/questions/allquestions` : Returns all questions.
	- **GET** `/questions/Category/{category}` : Returns questions filtered by `{category}`.
	- **POST** `/questions/add` : Add a new question — accepts a `Question` JSON in the request body.

- **Quiz**
	- **POST** `/quiz/create` : Create a new quiz. Query parameters: `category` (String), `noOfQuestions` (int), `title` (String). Returns a quiz id or status message.
		- Example:
			```bash
			curl -X POST "http://localhost:8080/quiz/create?category=math&noOfQuestions=5&title=QuickMath"
			```
	- **GET** `/quiz/get/{id}` : Get questions for quiz with id `{id}`. Returns a list of `QuestionWrapper` objects.
	- **POST** `/quiz/submit/{id}` : Submit answers for quiz `{id}`. Body: JSON array of `Response` objects. Returns score (integer).
		- Example submit:
			```bash
			curl -X POST "http://localhost:8080/quiz/submit/1" -H "Content-Type: application/json" -d '[{"questionId":10,"answer":"B"}]'
			```

Notes: Endpoint signatures are implemented in the controllers; see `QuestionController` and `QuizController` for exact request/response shapes.

**How to run (local)**
- Prerequisites: Java 17+, Maven, and a database (Postgres recommended). For quick local testing you can configure an in-memory DB in `application.properties`.
- Build:
	```bash
	mvn clean package
	```
- Run:
	```bash
	mvn spring-boot:run
	```
- The app listens on port `8080` by default; adjust `application.properties` to change DB connection or port.
