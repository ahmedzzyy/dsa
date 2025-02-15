# Contributing to Data Structures and Algorithms in Kotlin (JVM)

I am genuinely grateful for your interest in contributing to this project!

## How to Contribute

### 1. Fork the Repository
- Click the **Fork** button (top-right corner of the repository page) to create your own copy.

### 2. Clone Your Fork
Clone the repository to your local machine:
```bash
git clone https://github.com/<your-username>/dsa.git
cd dsa
```

### 3. Create a New Branch
Always create a new branch for your feature or bug fix:
```bash
git checkout -b feature/your-feature-name
```

### 4. Make Your Changes
- Implement New Features or Fix Issues
- Add or Improve Tests: Ensure that your changes are accompanied by unit tests in the mirrored structure under `src/test/kotlin`.

### 5. Run Tests
Before submitting any changes, run the tests to ensure everything is working perfectly:
```bash
./gradlew test
```

### 6. Commit Your Changes
Commit using clear, descriptive messages like:
```
[feature]: Add implementation for binary tree traversal

- Added binary tree traversal methods (in-order, pre-order, post-order).
- Included unit tests for the new functionality.
```

### 7. Push Your Branch and Open a Pull Request
Push your branch to your fork:
```bash
git push origin feature/your-feature-name
```
Then, open a pull request against the `master` branch of the original repository. In your pull request description, reference any related issues or tasks from the [`TODO.md`](./TODO.md) file.

## Reporting Issues and Feature Requests

- **Open an Issue:**
  If you find a bug or have a feature request, please open an issue. Provide details to help us understand and reproduce the issue.
- **Check TODO.md:**
  Before opening a new issue, please review [`TODO.md`](./TODO.md) to see if the task has already been noted.

## Code Style Guidelines

- **Language:** Kotlin : Follow Kotlin coding conventions and best practices.
- **Formatting:** Use consistent code formatting.
- **Documentation:** Comment your code where necessary and update documentation if your changes impact usage.

## Additional Resources

- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Gradle User Guide](https://docs.gradle.org/current/userguide/userguide.html)
- [GitHub Flow Guide](https://guides.github.com/introduction/flow/)

## Questions or Feedback

If you have any questions or need guidance on your contribution, please feel free to open an issue or contact us.

---

Thank you for helping make this project better. Happy coding!