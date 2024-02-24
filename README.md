# Project Title

Order System

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 8 or higher
- Gradle
- Kafka

### Installing and Running Kafka

1. Download and extract the latest stable version of Kafka from https://kafka.apache.org/downloads

2. Navigate to the directory where you extracted Kafka.

3. Start the ZooKeeper service:

    ```bash
    bin/zookeeper-server-start.sh config/zookeeper.properties
   ```

4. Open another terminal window and start the Kafka broker:

   ```bash
   bin/kafka-server-start.sh config/server.properties
   ```

### Compiling the React App
1. Navigate to the front-end/order-fe directory

2. Install the npm packages:

   ```bash
   npm install
   ```

3. Build the React app:

   ```bash 
   npm run build
   ```

4. Copy the app to be included in the Gradle build

   ```bash
   cp front-end/order-fe/build/* src/main/resources/static
   ```

### Running the Application

1. Navigate to the project directory.

2. Use Gradle to run the application:

   ```bash
   ./gradlew bootRun
   ```

The application will start and connect to the local Kafka broker.

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Apache Kafka](https://kafka.apache.org/)
- [Gradle](https://gradle.org/)
- [React](https://create-react-app.dev/)

## Authors

- [Jason Sanchez](https://github.com/j4ysanchez)