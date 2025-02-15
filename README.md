# Data Structures and Algorithms in Kotlin (JVM)

This repository is a practice space for implementing and understanding various **data structures** and **algorithms** using Kotlin on *JVM*.
The project has been rewritten to use Kotlin and is structured as a **Gradle project** for better flexibility and modern dependency management.

For a detailed roadmap of planned features, improvements, and pending tasks, check out the [`TODO.md`](./TODO.md) file.

## Project Structure

No standalone `main` functions are included for testing the implemented data structures and algorithms.  
Instead, all implementations are thoroughly tested using unit tests.

- **`src/main/kotlin`**: Contains the main entry point of the application (`Main.kt`).
- **`edu.practice.datastructures`**: Houses implementations of various data structures like `Queue.kt`, `Stack.kt`, etc.
- **`edu.practice.algorithms`**: Designed for implementing algorithms such as sorting, searching, graph algorithms, etc.

### Testing

The tests are organized in a mirrored structure under `src/test/kotlin`. For example:
- Tests for classes in `edu.practice.datastructures` are located in `edu.practice.datastructures` under the `test` directory.
- Tests for classes in `edu.practice.algorithms` are located in `edu.practice.algorithms` under the `test` directory.

## Getting Started
To work with the project, you need the following tools installed:

- **Java**: Version 17 or above (required for Kotlin runtime).
- **Gradle**: To manage dependencies and build the project.

### Clone the Repository
```bash
git clone https://github.com/ahmedzzyy/dsa.git
cd dsa
```

### Build the Project

Use Gradle to compile the code and ensure dependencies are set up:
```bash
./gradlew build
```

### Run Tests

Run all tests using Gradle:
```bash
./gradlew test
```
You can find detailed test results in the output of this command or in the generated reports in the `build/reports/tests/test/index.html`.

### Running the Main class

To run the `Main` class:
```bash
./gradlew run
```

Alternatively, you can directly specify the main class if needed:
```bash
./gradlew run --args="edu.practice.Main"
```

## Contribution Guide
This repository is primarily for practice, but I would be more than happy to have your help.
Whether you're improving documentation, optimizing existing implementations, or adding new features, open an issue or drop a comment, I’d love to hear from you.

Before contributing, here are a few things to check out:
- Read the Guidelines: Take a look at the [`CONTRIBUTING.md`](./CONTRIBUTING.md) file for details on how to get started, set up the project, and submit a pull request.
- Check the Roadmap: The [`TODO.md`](./TODO.md) file lists planned features, improvements, and pending tasks. If you see something that interests you, feel free to tackle it!

### Suggestions for Contributions:
- Implement additional data structures or algorithms.
- Add or improve test cases.
- Provide documentation or examples.
- Address tasks or enhancements listed in [`TODO.md`](./TODO.md).

## Learning Goals

This project serves as a foundation to:
- Understand and implement core data structures like arrays, stacks, queues, trees, graphs, etc.
- Learn and implement algorithms like sorting, searching, and dynamic programming.
- Write clean and efficient Kotlin code adhering to industry standards.
- For ideas on expanding the project, see the [`TODO.md`](./TODO.md) file.

---

Feel free to explore the code, run the examples, and expand your knowledge!