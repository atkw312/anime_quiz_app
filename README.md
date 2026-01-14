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
- Player submits a guess for the anime title
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


## Agile Development Process

This project is developed using a Scrum-style Agile methodology adapted for a single developer.

### Sprint Structure
- Sprint length: 1 week
- Each sprint has a defined goal
- Each sprint delivers a working, deployable increment

### Work Tracking
- All work is tracked using GitHub Issues
- Issues are categorized as user stories or technical tasks
- A GitHub Project board is used to track progress

### Sprint Rituals
- Sprint Planning: Select issues aligned with sprint goal
- Daily Check-in: Brief progress notes recorded in issue comments
- Sprint Review: Demo working features locally
- Sprint Retrospective: Document improvements for next sprint

### Definition of Done
A task is considered complete when:
- Code is implemented
- Tests pass
- API behavior is verified
- Changes are merged via pull request
- Service runs locally via Docker

### Sprint 1 Goal
Establish secure user authentication using JWT-based stateless authentication.

This Agile process ensures incremental delivery, continuous feedback, and production-ready code quality.
