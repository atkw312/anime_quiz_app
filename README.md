# anime_quiz_app

## Gameplay Flow

### 1. Lobby Creation
- User creates a lobby
- Lobby Service generates and assigns a unique lobby ID
- Lobby metadata is persisted in PostgreSQL
- Active lobby state is cached in Redis for fast access

### 2. Match Start
- Lobby host initiates the match
- Game Engine selects a character image for the round
- Match start timestamp is recorded server-side
- `MATCH_STARTED` event is published to Kafka

### 3. Guess Submission
- Player submits a guess
- Game Engine validates the guess
- Submission timestamp is recorded
- `GUESS_SUBMITTED` event is published to Kafka

### 4. Placement Calculation
- First correct guess is assigned **1st place**
- Subsequent correct guesses are ranked based on submission time
- Incorrect guesses are penalized or limited according to game rules
- Redis atomic operations are used to enforce ordering and prevent race conditions

### 5. Match End
- Match ends when all players are placed or the time limit expires
- `MATCH_COMPLETED` event is published to Kafka
- Lobby leaderboard is generated and displayed
- Player statistics and leaderboards are updated asynchronously

## Technologies Used

## Technologies Used

### Backend
- **Java, Spring Boot** — Core backend framework for REST APIs, service orchestration, and game logic
- **Spring Web (REST)** — Lobby creation, match lifecycle, and guess submission endpoints
- **Spring Data JPA / Hibernate** — ORM layer for persisting users, lobbies, matches, and statistics

### Datastores
- **PostgreSQL** — Persistent storage for user accounts, lobby metadata, match history, and leaderboards
- **Redis** — In-memory cache for active lobby state, atomic ordering, and race-condition-safe placement logic

### DevOps & Infrastructure
- **Docker** — Containerization of backend services and databases
- **Docker Compose** — Local orchestration of services (auth, lobby, game engine, PostgreSQL, Redis, Kafka)
- **Kubernetes** — Service orchestration, replica scaling, and deployment readiness

### Build & CI/CD
- **Maven** — Dependency management and reproducible Java builds
- **GitHub Actions** — Automated builds, tests, and CI workflows on pull requests
