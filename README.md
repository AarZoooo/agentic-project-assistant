# Agentic Project Assistant

An AI-powered coding assistant that provides conversational Q&A and performs refactoring on a local codebase through a secure web interface.

---

## Overview

This project aims to build an intelligent agent that acts as a pair programmer. It addresses the common challenges developers face, such as understanding legacy codebases, debugging complex logic, or performing repetitive refactoring tasks. By securely indexing a local project directory, the agent can provide context-aware answers and eventually modify code with user approval, improving developer velocity and code quality.

## Key Features

-   **Code Comprehension:** Ask questions in plain English about your codebase and get synthesized answers.
-   **Secure & Local:** Indexes and reads your files locally using the browser's File System Access API. Your code never leaves your machine.
-   **Agentic Code Modification:** (Phase 3) Instruct the agent to perform refactoring, review the changes, and approve them before they are written to your files.
-   **Reproducible Environment:** (Phase 2) The entire application stack can be launched with a single `docker-compose up` command.

## Tech Stack

-   **Frontend:** React (Vite), Tailwind CSS
-   **Backend:** Java 21, Spring Boot 3
-   **Agent Logic:** LangChain4j
-   **Databases:** Milvus (Vector Store), MongoDB (Conversation History)
-   **Containerization:** Docker & docker-compose

---

## 🎯 Project Roadmap & Checklist

This is the step-by-step plan to follow to build the project. Update this checklist as you complete each task.

### Phase 1: Build the Core Application (Read-Only MVP)

* [ ] **Setup: Backend**
    * [ ] Initialize a Spring Boot 3 project with required dependencies (`web`, `langchain4j`, `milvus`, `mongodb`).
    * [ ] Create the project directory structure (`controller`, `service`, `dto`, `tools`).
* [ ] **Setup: Frontend**
    * [ ] Initialize a React project using Vite.
    * [ ] Create the project directory structure (`components`, `services`).
* [ ] **Backend: API & Database Layer**
    * [ ] Define the API contract (`/api/index`, `/api/chat`) in `AgentController`.
    * [ ] Create the DTOs for the API contract.
    * [ ] Implement the MongoDB `ConversationRepository`.
    * [ ] Implement the Milvus connection and schema for `code_chunks`.
* [ ] **Backend: Agent Logic**
    * [ ] Implement the `IndexingWorkflow` in `AgentService` (chunking, embedding, storing).
    * [ ] Implement the `RAGWorkflow` in `AgentService` (searching, prompting, generating).
* [ ] **Frontend: Core Functionality**
    * [ ] Implement the `DirectoryPicker` component using the File System Access API.
    * [ ] Create the `api.js` service to communicate with the backend.
    * [ ] Ensure file indexing is working end-to-end.
* [ ] **Frontend: UI**
    * [ ] Build the `ChatWindow`, `MessageBubble`, and `MessageInput` components.
    * [ ] Integrate the chat UI with the `/api/chat` endpoint.
    * [ ] Implement the `CodeBlock` component for syntax highlighting.

### Phase 2: Containerize the Application

* [ ] **Dockerfiles**
    * [ ] Create a multi-stage `Dockerfile` for the Spring Boot backend.
    * [ ] Create a multi-stage `Dockerfile` for the React frontend (using Nginx).
* [ ] **Orchestration**
    * [ ] Create a `docker-compose.yml` file defining the `frontend`, `backend`, `milvus`, and `mongodb` services.
    * [ ] Ensure the entire application can be launched successfully with `docker-compose up`.

### Phase 3: Evolve to the Read-Write Agent

* [ ] **Backend: New Tools**
    * [ ] Implement the `WriteFileTool`, `CreateFileTool`, and other file modification tools.
    * [ ] Update the agent logic in `AgentService` to be able to use these new tools.
* [ ] **Frontend: Approval UI**
    * [ ] Design and build a UI component to display code "diffs" (changes).
    * [ ] Implement the approval workflow (e.g., "Accept" / "Reject" buttons) that triggers the agent's file-writing tools.

---

## Getting Started (Local Development)

Instructions for running the project before containerization.

**Prerequisites:**
-   Java 21+
-   Node.js 20+
-   Local instances of Milvus and MongoDB running.

**1. Clone the repository:**
```bash
git clone [https://github.com/aarzoooo/agentic-project-assistant.git](https://github.com/aarzoooo/agentic-project-assistant.git)
cd agentic-project-assistant
```

**2. Run the Backend:**
```bash
cd backend
# Configure application.yml with your local settings
mvn spring-boot:run
```

**3. Run the Frontend:**
```bash
cd frontend
npm install
npm run dev
```