# IT Academy
## Java & Spring Framework (formació online)
### Sprint 5

## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

1. Run this scripts to your local MongoDb instance with mongosh:
  ```sh
  use DiceGameDb
  db.createCollection("Player")
  db.sequence.insertOne({_id:"player",seq:0})
  db.sequence.insertOne({_id:"game",seq:0})
  ```
2. When running the application, you can get to the Swagger documentation at /swagger-ui
3. You can get an authorization bearer token using the first open endpoint /auth/login with any username.
4. Enjoy :)
