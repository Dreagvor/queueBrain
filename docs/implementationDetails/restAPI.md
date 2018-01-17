### Users API

| #    | Method | Path          | Description             |
| ---- | ------ | ------------- | ----------------------- |
| 1    | GET    | /users        | Common users statistics |
| 2    | GET    | /users?id=123 | Info about one user     |
| 3    | POST   | /users        | Creation of new user    |
| 4    | DELETE | /users?id=123 | User deletion           |
| 5    | PUT    | /users        | User updating           |

1. GET /users

   * Response

     ```json
     {
       "usersCount": 2342
     }
     ```

2. GET /users?id={id}

   * Response

     ```json
     {
       "name": "Mike",
       "contacts": "Minsk"
     }
     ```

3. POST /users

   * Request

     ```json
     {
       "name": "Mike",
       "email": "mike@email.com"
     }
     ```

   * Response: empty 200 or appropriate error with status

4. DELETE /users?id={id}

   * Response: empty 200 or appropriate error with status

5. PUT /users

   * Request

     ```json
     {
       "id": "342",
       "contacts": "+123121234567"
     }
     ```

   * Response: empty 200 or appropriate error with status

### Queues API

| #    | Method | Path                | Description              |
| ---- | ------ | ------------------- | ------------------------ |
| 1    | GET    | /queues             | Common queues statistics |
| 2    | GET    | /queues?id=123      | Info about one queue     |
| 3    | POST   | /queues             | Creation of new queue    |
| 4    | DELETE | /queues?id=123      | Queue deletion           |
| 5    | PUT    | /queues             | Update queue state       |
| 6    | PUT    | /queues/push?id=123 | Push the queue           |

1. GET /queues

   - Response

     ```json
     {
       "queuesCount": 2342
     }
     ```

2. GET /queues?id={id}

   - Response

     ```json
     {
       "name": "queueOne",
       "admin": 23,
       "users": [1, 5, 39],
       "currentUser": [2]
     }
     ```

3. POST /queues

   - Request

     ```json
     {
       "name": "Mike",
       "admin": 5
     }
     ```

   - Response: empty 200 or appropriate error with status

4. DELETE /queues?id={id}

   - Response: empty 200 or appropriate error with status

5. PUT /queues

   - Request

     ```json
     {
       "id": 342,
       "state": "ACTIVE"
     }
     ```

   - Response: empty 200 or appropriate error with status

6. PUT /queues/push?id=123

   - Response: id of next processing user of empty 200

     ```json
     {
      "nextUserId": 433 
     }
     ```