# QuizGPT: AI-Powered Quiz Generator

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

An intelligent web application that dynamically generates quiz questions based on a user's prompt. Built with a modern tech stack including Spring Boot, Spring AI, MongoDB, and React.js, and secured with JWT.

<div align="center">
  <img src="./QuizUI/quizapp/public/QuizGPT.png" width="600" height="400" alt="QuizGPT Demo">
</div>

---

## 🚀 About The Project

QuizGPT is a full-stack application designed to streamline the creation of quizzes. The core idea is to leverage the power of Generative AI to create relevant and challenging questions on any topic provided by the user. The backend is built with the robust and scalable Spring Boot framework, using **Spring AI** to interface with language models. The frontend is a dynamic and responsive single-page application built with **React.js**.

User authentication and route protection are handled securely using **JSON Web Tokens (JWT)**, ensuring that user data and quiz sessions are kept private.

### Key Features

* **Dynamic Quiz Generation:** Provide any topic, and the AI will generate a set of multiple-choice questions.
* **Secure Authentication:** JWT-based login system to protect user accounts and data.
* **Interactive Quiz Interface:** A clean and intuitive UI built with React for taking quizzes.
* **Instant Scoring:** Once the quiz is submitted, users get immediate feedback on their score, the number of questions attempted, and the total number of questions.
* **Decoupled Architecture:** A clear separation between the Spring Boot backend (`Quiz`) and the React.js frontend (`QuizUI`).

---

## 🛠️ Tech Stack

| Backend                                                              | Frontend                                                | Database      |
| -------------------------------------------------------------------- | ------------------------------------------------------- | ------------- |
| ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) | ![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB) | ![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white) |
| ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white) | ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black) |               |
| ![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot) | ![Axios](https://img.shields.io/badge/Axios-5A29E4?style=for-the-badge&logo=axios&logoColor=white) |               |
| ![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white) | ![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white) |               |


---

## 📂 Project Structure

The repository is organized into two main directories:

```
QuizGPT/
├── Quiz/      # Backend: Spring Boot Application
└── QuizUI/    # Frontend: React.js Application
```

---

## 🏁 Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

Make sure you have the following installed on your machine:
* Java (JDK 17 or later)
* Maven
* Node.js and npm
* MongoDB (running locally or a cloud instance URI)

### Installation & Setup

1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/narerndra369/QuizGPT.git](https://github.com/narerndra369/QuizGPT.git)
    cd QuizGPT
    ```

2.  **Backend Setup (`Quiz`):**
    * Navigate to the backend directory:
        ```sh
        cd Quiz
        ```
    * Configure your database and AI settings in `src/main/resources/application.properties`. You will need to add your MongoDB connection string, JWT secret key, and Spring AI API key.
        ```properties
        # Example configuration
        spring.data.mongodb.uri=mongodb://localhost:27017/quizdb
        spring.ai.openai.api-key=YOUR_OPENAI_API_KEY
        jwt.secret.key=YOUR_SUPER_SECRET_JWT_KEY
        ```
    * Install dependencies using Maven:
        ```sh
        mvn clean install
        ```
    * Run the Spring Boot server:
        ```sh
        mvn spring-boot:run
        ```
    * The backend server will start on `http://localhost:8000`.

3.  **Frontend Setup (`QuizUI`):**
    * Open a new terminal and navigate to the frontend directory:
        ```sh
        cd quizapp
        ```
    * Install all required npm packages (this will install `axios` and other dependencies listed in `package.json`):
        ```sh
        npm install
        ```
    * Start the React development server:
        ```sh
        npm start
        ```
    * The application will open automatically in your browser at `http://localhost:3000`.

---

## 🗺️ Future Scope

We have exciting plans to enhance QuizGPT further:

* **Microservices Architecture:** Decompose the monolith backend into smaller, independently deployable microservices for better scalability and maintenance.
* **RAG Implementation:** Integrate **Retrieval-Augmented Generation (RAG)** with Spring AI. This will allow users to upload their own documents (PDFs, text files), from which the AI will generate highly relevant and context-aware quiz questions.

---

## 🤝 Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

---

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

---
