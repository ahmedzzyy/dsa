# Data Structures and Algorithms in Java

This repository is a practice space for implementing and understanding various **data structures** and **algorithms** using Java.
The project structure adheres to Maven's standard layout for easy organization and testing.

## Project Structure

The project is organized into three primary packages under the `src/main/java` directory:

- **`edu.practice`**: Contains the main entry point of the application (`Main.java`).
- **`edu.practice.datastructures`**: Houses implementations of various data structures like `Queue.java`, `Stack.java`, etc.
- **`edu.practice.algorithms`**: Designed for implementing algorithms such as sorting, searching, graph algorithms, etc.

### Testing

The tests are organized in a mirrored structure under `src/test/java`. For example:
- Tests for classes in `edu.practice.datastructures` are located in `edu.practice.datastructures` under the `test` directory.
- Tests for classes in `edu.practice.algorithms` are located in `edu.practice.algorithms` under the `test` directory.

## Getting Started

To work with the project, you need the following tools installed:

- **Java**: Version 17 or above.
- **Maven**: To manage dependencies and build the project.

### Clone the Repository

```bash
git clone https://github.com/ahmedzzyy/dsa.git
cd dsa
```

### Build the Project

Use Maven to compile the code and ensure that dependencies are set up:

```bash
mvn clean install
```

### Run Tests

Run all tests using Maven:

```bash
mvn test
```

You can find detailed test results in the output of this command or in the generated reports (if applicable).

### Running the Main Class

To run the `Main` class:

```bash
mvn exec:java -Dexec.mainClass="edu.practice.Main"
```

## Contribution Guide

This repository is primarily for practice, but contributions are welcome if they align with its educational goals.
Feel free to comment and suggest what can be fixed or improved.

### Suggestions for Contributions:
- Implement additional data structures or algorithms.
- Add or improve test cases.
- Provide documentation or examples.

### Pull Requests

If you want to contribute, fork the repository, create a new branch for your feature or fix, and open a pull request. Ensure the code follows the established directory structure and conventions.

## Learning Goals

This project serves as a foundation to:
- Understand and implement core data structures like arrays, stacks, queues, trees, graphs, etc.
- Learn and implement algorithms like sorting, searching, and dynamic programming.
- Write clean and efficient Java code adhering to industry standards.

---

Feel free to explore the code, run the examples, and expand your knowledge!