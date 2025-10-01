# Web-Agentic Assistant

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A web-based agentic assistant that leverages Retrieval-Augmented Generation (RAG) to understand and interact with your local files, enabling contextual Q&A and file manipulation.

## Description

This project allows a user to grant a sophisticated AI agent access to local files and directories. The agent ingests this data, building a contextual understanding using vector embeddings. Users can then converse with the agent to ask complex questions about their files, ask for modifications, or even request the creation of new files and directories, all through a simple chat interface.

## Key Features

-   **Local File Access**: Securely select local files or entire directories to use as a context.
-   **Retrieval-Augmented Generation (RAG)**: All ingested text data is chunked, vectorized, and stored in a Milvus vector database for powerful semantic search.
-   **Contextual Q&A**: Ask questions about your codebase, documents, or any text-based files and get answers based on their content.
-   **File Manipulation**: The agent is equipped with tools to read, write, and create files on the local system based on user prompts.
-   **Conversation History**: All conversations are saved, allowing users to track their interactions with the agent.

## Architecture Overview

The application is built on a modern, three-layer architecture to separate concerns and ensure scalability.

[![High Level Architecture](assets/high%20level%20overview.png)](https://www.figma.com/board/tTr3LCinzwgRy9cXZwNiKZ/Untitled?node-id=1-2)
*(Click image to view the interactive Figma board)*

1.  **Frontend**: A React-based single-page application that provides the user-facing chat interface.
2.  **Backend & Agent**: A Spring Boot application that exposes a RESTful API and contains the core agentic logic built with Langchain4j.
3.  **Database**: A dual-database system using MongoDB for storing user and conversation data, and Milvus DB for vector embeddings.

## Technology Stack

| Layer              | Technology                           |
| ------------------ |--------------------------------------|
| **Frontend** | React, Vite, TypeScript              |
| **Backend & Agent**| Java 21+, Spring Boot 3, Langchain4j |
| **Database** | MongoDB, Milvus DB                   |
| **DevOps** | Docker, Docker Compose               |

---

## Getting Started

Follow these instructions to get a local copy up and running for development and testing.

### Prerequisites

-   Git
-   JDK 17 or later
-   Maven 3.8+
-   Node.js 18+
-   Docker and Docker Compose

### Installation & Setup

1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/aarzoooo/agentic-project-assistant.git](https://github.com/aarzoooo/agentic-project-assistant.git)
    cd agentic-project-assistant
    ```

2.  **Configure Environment Variables:**
    Create an `application.properties` file inside `backend/src/main/resources/` and add the following configuration.
    *Note: The LLM API Key is required for the agent's core functionality.*
    ```properties
    # MongoDB Configuration
    spring.data.mongodb.uri=mongodb://localhost:27017/agent_db

    # Milvus Configuration
    milvus.host=localhost
    milvus.port=19530

    # LLM Provider Configuration
    langchain4j.chat-model.provider=openai
    langchain4j.chat-model.openai.api-key=<YOUR_OPENAI_API_KEY>
    langchain4j.chat-model.openai.model-name=gpt-4
    
    # Embedding Model Configuration
    langchain4j.embedding-model.provider=huggingface
    langchain4j.embedding-model.huggingface.model-name=all-minilm-l6-v2
    ```

3.  **Launch Databases:**
    Start the required MongoDB and Milvus DB instances using Docker Compose.
    ```sh
    docker-compose up -d
    ```

4.  **Install Backend Dependencies:**
    ```sh
    cd backend
    mvn clean install
    ```

5.  **Install Frontend Dependencies:**
    ```sh
    cd ../frontend
    npm install
    ```

### Running the Application

1.  **Run the Backend Server:**
    ```sh
    cd backend
    mvn spring-boot:run
    ```
    The backend will be available at `http://localhost:8080`.

2.  **Run the Frontend Development Server:**
    ```sh
    cd frontend
    npm run dev
    ```
    The frontend will be available at `http://localhost:5173`.

---

## Workflows

### New User Creation

The system supports user creation via local credentials or OAuth2 providers.

[![User Creation Flow](assets/new%20user%20flow.png)](https://www.figma.com/board/tTr3LCinzwgRy9cXZwNiKZ/Web-based-Agentic-Assistant?node-id=10-921)

### New Session Creation (Data Ingestion)

A new session begins when a user provides a file or directory for context. The content is then processed and ingested into the vector database.

[![Session Creation Flow](assets/new%20session%20flow.png)](https://www.figma.com/board/tTr3LCinzwgRy9cXZwNiKZ/Web-based-Agentic-Assistant?node-id=13-1277)

### Conversation Flow (RAG in Action)

Each user message triggers a RAG pipeline to fetch relevant context and generate an informed response.

[![Conversation Flow](assets/conversation%20flow.png)](https://www.figma.com/board/tTr3LCinzwgRy9cXZwNiKZ/Web-based-Agentic-Assistant?node-id=13-1678)

## API Reference

The backend exposes a RESTful API for managing users and conversations.

| Method | Endpoint                        | Description                               |
| ------ | ------------------------------- | ----------------------------------------- |
| `GET`  | `/api/v1/users/{username}`      | Retrieves user information.               |
| `POST` | `/api/v1/users`                 | Creates a new user.                       |
| `PUT`  | `/api/v1/users/{username}`      | Updates user details.                     |
| `GET`  | `/api/v1/conversations`         | Retrieves a list of conversations for a user. |
| `POST` | `/api/v1/conversations`         | Creates a new conversation.               |
| `GET`  | `/api/v1/conversations/{convId}`| Retrieves messages for a conversation.    |
| `PUT`  | `/api/v1/conversations/{convId}`| Adds a new message to a conversation.     |
