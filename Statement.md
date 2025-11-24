# Project Statement

## Overview
This repository contains a Java-based ID Generator application that provides multiple methods for generating unique identifiers. It is designed to help developers generate IDs suitable for various use cases such as logging, tracking, database references, and distributed systems.

## Purpose
The purpose of this project is to implement different strategies of ID generation in one unified program. This allows students, developers, and learners to understand the strengths and weaknesses of each method and use them appropriately in their applications.

## Key Features
- **UUID Generation:** Creates universally unique identifiers.
- **Timestamp-Based ID:** Uses system time to generate sortable and time-dependent IDs.
- **Sequential Counter:** Generates IDs in an incremental manner.
- **Hybrid ID:** Combines timestamp with random components for higher uniqueness and readability.
- **Alphanumeric Codes:** User-defined length codes useful for coupons, product keys, etc.
- **Snowflake-Style ID:** Inspired by Twitter's Snowflake algorithm, generates distributed, time-sortable 64-bit IDs.

## Project Structure
The project contains the following components:
- `Main.java` — Entry point of the application.
- `IDGenerator` — Class containing various ID generation methods.
- `IDGeneratorUI` — User interface that interacts with the generator.

## Requirements
- **Java Development Kit (JDK):** Version 8 or above
- **Terminal/Command Prompt:** To compile and run the Java file

## How to Run
1. Clone or download the repository.
2. Navigate to the project directory.
3. Compile the program:
   ```bash
   javac Main.java
   ```
4. Run the application:
   ```bash
   java Main
   ```
5. Select the ID generation option from the menu.

## Future Improvements
- Add unit tests (JUnit)
- Split classes into separate files for better modularity
- Add database integration for storing generated IDs
- Add GUI-based interface
- Provide a packaged JAR file for easier use

## Author
Aniket Palandi (Aniket-03-AI)

