# Money Tracking Application

## Project Overview

The **Money Tracking Application** is a Java-based application designed to help users manage and track their financial transactions.

The application uses Java 17 and Maven. It includes classes for managing accounting records, displaying information, handling files, and maintaining a ledger. Transaction data is stored in a CSV file.

## Technologies Used

- Java 17
- Apache Maven
- CSV (Comma-Separated Values)
- IntelliJ IDEA
- Git and GitHub

## Project Structure

```text
capstones/
│
├── .idea/
├── .mvn/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.pluralsight/
│   │   │       ├── AccountingLedger.java
│   │   │       ├── DisplayScreen.java
│   │   │       ├── FileManager.java
│   │   │       ├── Ledger.java
│   │   │       ├── Main.java
│   │   │       └── MoneyTrackApp.java
│   │   │
│   │   └── resources/
│   │       └── transactions.csv
│   │
│   └── test/
│
├── target/
├── .gitignore
└── pom.xml
```

## Class Descriptions

| Class | Purpose |
|---|---|
| `Main.java` | Entry point of the application. |
| `MoneyTrackApp.java` | Main application functionality. |
| `AccountingLedger.java` | Handles accounting-related operations. |
| `DisplayScreen.java` | Displays information to the user. |
| `FileManager.java` | Manages file operations and transaction data. |
| `Ledger.java` | Manages the transaction ledger. |

## Data Storage

The application uses a CSV file named `transactions.csv`, located in:

`src/main/resources/transactions.csv`

This file stores transaction records and provides a way to organize financial data.

## Getting Started

### Prerequisites

Before running the application, make sure you have:

- Java JDK 17 or later
- IntelliJ IDEA or another Java IDE
- Apache Maven

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/Shashwot-yearup/capstones.git
   ```

2. Open the project in IntelliJ IDEA.

3. Allow Maven to load and download the required dependencies.

4. Navigate to `Main.java`.

5. Run the application.

## Building the Project

To build the project using Maven, run:

```bash
mvn clean install
```

## Learning Objectives

This project demonstrates the use of:

- Object-Oriented Programming (OOP)
- Java classes and methods
- File handling
- CSV data management
- Maven project management
- Application organization
- Version control with Git and GitHub

## Author

**Shashwot**

Year Up Capstone Project

## Repository

[GitHub Repository](https://github.com/Shashwot-yearup/capstones)

## License

This project was created for educational purposes.
