## ✨ Features

The application offers a menu to generate IDs using the following methods:

1.  **UUID** (Universally Unique Identifier)
2.  **Timestamp-based** (Time-based, prone to collisions)
3.  **Sequential** (Atomic Counter, strictly ordered)
4.  **Hybrid** (Timestamp + Random number)
5.  **Alphanumeric** (Custom length, human-readable codes)
6.  **Snowflake-like ID** (High-throughput, distributed, time-sortable 64-bit ID)

## 🚀 Getting Started

The entire application is contained in a single file (`Main.java`).

### Prerequisites

  * Java Development Kit (JDK 8 or newer).

### Execution

1.  **Save:** Save the combined Java code into a file named `Main.java`.
2.  **Compile:**
    ```bash
    javac Main.java
    ```
3.  **Run:**
    ```bash
    java Main
    ```
4.  Follow the on-screen menu prompts to select the generation method and quantity.

## 📄 Code Structure

The project is split into three nested classes in `Main.java`:

  * **`IDGenerator`**: Contains all static methods for generating the different types of IDs.
  * **`IDGeneratorUI`**: Handles the command-line interface, menu display, and user input.
  * **`Main`**: The entry point, which manages the application loop and calls the UI/Generator methods.
