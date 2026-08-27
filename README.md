# Phoenix Test Automation Framework

<p align="center">
  <strong>Java-based REST API automation framework built with REST Assured and TestNG</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-16+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/REST%20Assured-5.5.6-4CAF50?style=for-the-badge" />
  <img src="https://img.shields.io/badge/TestNG-7.12.0-EF2D5E?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" />
</p>

## 📌 Overview

**Phoenix Test Automation Framework** is a Java-based API automation framework designed to validate the Phoenix in-warranty workflow through automated REST API tests.

The framework focuses on **reusable request/response specifications, schema validation, readable TestNG tests, and suite-based execution**.

## 🎯 What It Automates

The current TestNG suite covers key API flows including:

- 🔐 Login API
- 👤 User details API
- 🔢 Count API
- 📋 Master API
- 🛠️ Create Job API

The suite is configured as **Phoenix API Test Suite → Inwarranty Flow** and groups the tests around API/regression/smoke execution where applicable.

## 🧰 Technology Stack

| Technology | Purpose |
| --- | --- |
| **Java** | Programming language |
| **REST Assured** | REST API automation |
| **TestNG** | Test execution and test grouping |
| **Maven** | Build and dependency management |
| **Jackson** | JSON serialization/deserialization |
| **JSON Schema Validator** | Response contract validation |

## 🏗️ Framework Highlights

### Reusable Request & Response Specifications

Common API configuration is abstracted into reusable specifications, allowing individual tests to focus on business assertions rather than repetitive setup.

### Schema Validation

API responses can be validated against JSON schemas in the test resources, helping detect contract-level changes in addition to functional assertion failures.

### TestNG Groups

Tests can be organised into groups such as:

```text
api
smoke
regression
```

This makes it easier to build targeted suites as the automation suite grows.

### Suite-Based Execution

The project uses Maven Surefire with a configurable TestNG suite file, making suite selection straightforward for local and CI execution.

## 📂 Project Structure

```text
PhoenixTestAutomationFramework/
├── pom.xml
├── testng.xml
└── src/
    ├── main/
    │   └── java/
    │       └── com/api/
    │           ├── request/model/
    │           └── utils/
    └── test/
        ├── java/
        │   └── com/api/tests/
        │       ├── LoginAPITest.java
        │       ├── UserDetailsAPITest.java
        │       ├── CountAPITest.java
        │       ├── MasterAPITest.java
        │       └── CreateJobAPITest.java
        └── resources/
            └── response-schema/
```

## ▶️ Getting Started

### Prerequisites

- Java 16 or later
- Maven 3.8+
- Git

### Clone the repository

```bash
git clone https://github.com/CodeCheckSweta/PhoenixTestAutomationFramework.git
cd PhoenixTestAutomationFramework
```

### Run the complete TestNG suite

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run with a custom suite

```bash
mvn clean test -DsuiteXmlFile=<your-suite.xml>
```

## 🧪 Example Test Pattern

A typical API test follows a simple **Arrange → Execute → Validate** flow:

```java
given()
    .spec(requestSpec(userCredentials))
.when()
    .post("/login")
.then()
    .spec(responseSpec_OK())
    .body("message", equalTo("Success"))
    .and()
    .body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
```

This keeps the test readable while combining **HTTP response validation, business assertions, and JSON schema validation**.

## 📊 Quality Engineering Practices Demonstrated

- ✅ API-first automation
- ✅ Reusable framework components
- ✅ TestNG grouping and suite execution
- ✅ Response specification reuse
- ✅ JSON schema validation
- ✅ Data/model separation
- ✅ Maven-based execution
- ✅ CI/CD-ready test structure

## 🚀 Future Enhancements

Potential improvements for expanding the framework include:

- [ ] Environment-specific configuration
- [ ] Externalised test data and secrets
- [ ] Parallel execution
- [ ] Allure/Extent reporting
- [ ] GitHub Actions/Jenkins pipeline integration
- [ ] Request/response logging for failed tests
- [ ] Retry handling for transient failures
- [ ] Dockerised execution

## 👩‍💻 Author

**Sweta Singh** — Senior QA Engineer / SDET

Focused on **test automation, API testing, Playwright, Selenium, CI/CD, and quality engineering**.

[GitHub Profile](https://github.com/CodeCheckSweta) · [LinkedIn](https://www.linkedin.com/in/swetasingh22/)

---

⭐ If you find this framework useful, consider starring the repository.
