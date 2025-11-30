# 🧪 Swag Labs Testing & Automation Project

## 📋 Overview

This project is developed as part of the **Digital Egypt Pioneers
Initiative (DEPI)** and focuses on applying comprehensive **manual and
automated testing** techniques on **Swag Labs**, an educational
e-commerce demo application.
The testing scope covers login, inventory, cart, checkout, and user flow
validations using both manual QA artifacts and automation with Selenium.

## 🎯 Project Goals

-   Validate core functionality of the Swag Labs web application
-   Design and execute detailed manual test cases
-   Identify, document, and track software defects
-   Build a complete requirements traceability matrix
-   Automate critical scenarios using Selenium WebDriver (Java)
-   Implement the Page Object Model (POM) design pattern

## 🛠️ Technology Stack

-   **Programming Language:** Java
-   **Automation Tool:** Selenium WebDriver
-   **Testing Framework:** TestNG
-   **Build Tool:** Maven
-   **Browser:** Google Chrome
-   **Test Management:** Excel (requirements, test cases, bug reports)

## 📁 Project Structure

    swaglabs-automation/
    ├── src/main/java/com/swaglabs/
    │   ├── drivers/        # WebDriver setup & configuration
    │   ├── pages/          # POM classes
    │   ├── tests/          # TestNG test classes
    │   └── utils/          # Utility and helper classes
    ├── test-results/        # Test execution reports
    ├── documentation/       # Requirements, test cases, bugs, RTM
    └── pom.xml              # Maven dependencies & config

## 🗂️ Key Components

### 📄 Page Objects

-   LoginPage.java → Login actions & validations
-   ProductsPage.java → Product listing, sorting, navigation
-   CartPage.java → Cart operations
-   CheckoutInfoPage.java → Checkout details submission

### 🧪 Test Classes

-   LoginTest.java → Data-driven login scenarios
-   ProductsTest.java → Sorting, product view tests
-   CartTest.java → Add/remove items, cart consistency
-   CheckoutInfoTest.java → Checkout fields & flow validation

### 🔧 Utilities

-   DriverSetup.java → WebDriver initialization
-   ElementActions.java → Click, type & interaction helpers
-   WaitClass.java → Explicit wait wrappers
-   BrowserActions.java → Browser-level operations

## 🚀 Test Scenarios Covered

### 🔐 Login Functionality

-   Valid login
-   Invalid login error validation
-   Data-driven testing (multiple accounts)
-   Locked-user validation

### 🛍️ Product Management

-   Sorting options
-   Add to cart
-   Product details navigation
-   Cart badge updates

### 🛒 Cart Operations

-   Add/remove items
-   Cart content validation
-   Continue shopping
-   Cart badge persistence

### 💳 Checkout Process

-   Submit checkout information
-   Required field validation
-   Cancel checkout
-   Navigation flow validation

## 📊 Testing Documentation

-   Requirements Specification
-   User Stories
-   90+ Manual Test Cases
-   25+ Bug Reports
-   Traceability Matrix

## 🔍 Key Features

-   Data-Driven Testing
-   Page Object Model
-   Explicit Waits
-   Cross-browser-ready
-   Strong assertion coverage

## 🐛 Identified Issues

-   Sorting inconsistencies
-   Cart synchronization issues
-   UI responsiveness bugs
-   Weak checkout validation

## 👥 Contributors

- **Mahmoud Abdo** 
- **Reneh Raafat** 
- **Meran Ramadan** 
- **Sarah Ashraf** 
- **Mazen Azhary** 

## 📄 License

This project was developed as part of the **DEPI** training program.
