# MiniProject-Selenium - Automated E-Commerce Test Suite

An enterprise-grade test automation framework built to perform end-to-end (E2E) functional, regression, validation, and responsive UI testing on an e-commerce platform hosted at [QA Automation Labs Shopping Portal](https://shop.qaautomationlabs.com/).

This project leverages modern Java capabilities (JDK 25) alongside **Selenium WebDriver 4** and **TestNG 7** to achieve high speed, robust validation, and maximum maintainability.

---

## 🚀 Key Framework Features

- **Page Object Model (POM) Design Pattern:** Decouples web element selectors and page interaction logic from the actual assertions, ensuring modularity and easy maintenance.
- **Dynamic `DriverManager`:** Simplifies local execution by dynamically instantiating WebDriver sessions for multiple browsers (Chrome, Firefox, Edge) using switch expressions.
- **Optimized `SessionManager` Flow:** Leverages local storage injection to mock or retain the authenticated state (`isLoggedIn: true`) across tests, eliminating repetitive login steps and decreasing execution time by up to 70% for post-authentication tests.
- **Hierarchical Base Classes:**
  - `BaseTest`: Manages driver instantiation, browser maximization, and standard page timeouts.
  - `UnauthenticatedBaseTest`: Deletes cookies and navigates to the registration or login landing pages.
  - `AuthenticatedBaseTest`: Injects authentication tokens/state before test execution.
- **Responsiveness Layout Testing:** Programmatically resizes the browser to verify elements across custom viewports (Desktop, Tablet, Mobile) and detects any horizontal overflow issues.
- **Ordered Test Suites:** Preserves order in `testng.xml` to execute realistic user journeys (Registration ➔ Login ➔ Shop Navigation ➔ Search ➔ Cart Operations ➔ Checkout).

---

## 📂 Project Structure

```text
MiniProject-Selenium/
│
├── .gitignore                    # Git file exclusion rules
├── pom.xml                       # Maven build configuration & dependencies
├── testng.xml                    # TestNG test suite and execution order definition
│
└── src/
    ├── main/
    │   └── java/
    │       ├── Driver/
    │       │   └── DriverManager.java     # Manages and instantiates cross-browser drivers
    │       │
    │       ├── pages/                     # Page Object Classes
    │       │   ├── BasePage.java          # Wrapper for locator find, click, sendKeys, and explicit waits
    │       │   ├── CategoryPage.java      # Shop Categories page logic
    │       │   ├── CheckoutPage.java      # Checkout form inputs and order placement
    │       │   ├── LoginPage.java         # Login form interactions and validations
    │       │   ├── MensWearPage.java      # Specific products list and filter controls
    │       │   ├── ProductSearchPage.java # Search execution and result verification
    │       │   ├── RegisterPage.java      # Account registration fields and validation errors
    │       │   ├── ShopPage.java          # Main navigation page
    │       │   ├── ShoppingCartPage.java  # Item count, adjust quantities, cart totals
    │       │   └── ShopWear.java          # Shop Wear-related interactions
    │       │
    │       └── utils/
    │           └── SessionManager.java    # Bypasses login screen via localStorage injection
    │
    └── test/
        └── java/
            ├── BaseTest/
            │   ├── BaseTest.java              # Standard WebDriver setup and teardown
            │   ├── AuthenticatedBaseTest.java # Setup for tests requiring active session
            │   └── UnauthenticatedBaseTest.java # Setup for public-facing guest pages
            │
            ├── CartTests/
            │   └── CartTest.java              # Cart add, view, remove, and update tests
            │
            ├── CheckoutTests/
            │   └── CheckoutTest.java          # Full end-to-end checkout and form validation tests
            │
            ├── LoginTests/
            │   └── LoginTest.java             # Credentials and error validations tests
            │
            ├── ProductTests/
            │   ├── MensWearTest.java          # Men's Wear category product validation tests
            │   └── ProductSearchTest.java     # Search bar functionality and search result tests
            │
            ├── RegistrationTests/
            │   └── RegistrationTest.java      # Success/failure registration forms tests
            │
            ├── ShopTests/
            │   └── ShopCategoryTest.java      # Shop navigation and category selection tests
            │
            └── UIandResponsiveTests/
                └── UIandResponsiveTest.java   # Responsiveness (Desktop/Tablet/Mobile) and overflow checks
```

---

## 🛠️ Prerequisites

Before you run this project, ensure you have the following installed on your machine:

1. **Java Development Kit (JDK) 25 or higher:**
   Ensure `JAVA_HOME` is set up correctly. (Verify with `java -version`)
2. **Apache Maven 3.9+:**
   Ensure Maven is configured in your system path. (Verify with `mvn -version`)
3. **Web Browsers:**
   - **Google Chrome** (Recommended)
   - **Mozilla Firefox**
   - **Microsoft Edge**

---

## ⚙️ Configuration & Architecture

### 1. `DriverManager`
WebDrivers are loaded automatically. No physical binary files are needed, as Selenium 4 uses **Selenium Manager** out of the box to resolve drivers for:
* `"chrome"` (Default)
* `"firefox"` or `"ff"`
* `"edge"`

To run tests on a different browser, modify the driver selection in [`BaseTest.java`](src/test/java/BaseTest/BaseTest.java:17):
```java
driver = DriverManager.selectDriver("firefox");
```

### 2. `SessionManager` & Bypass Login
Tests that require authentication inherit from `AuthenticatedBaseTest`. Instead of executing an expensive UI login for each test, the `SessionManager` directly sets the `isLoggedIn` flag inside the browser's `localStorage` on the target domain:
```java
js.executeScript("window.localStorage.setItem('isLoggedIn', 'true');");
```
This is a standard test-speed optimization that saves significant execution time during CI/CD test runs.

### 3. Smart/Explicit Waits
To eliminate flaky tests, [`BasePage.java`](src/main/java/pages/BasePage.java) wraps standard Selenium actions using a `WebDriverWait` of **10 seconds** with `ExpectedConditions`:
* `find()`: Waits for visibility of the element.
* `click()`: Waits until the element is clickable.
* `sendData()`: Clears the element before writing.
* `waitForUrlContains()`: Safely monitors transitions between pages.

---

## 💻 Running the Tests

### Command Line Execution (Maven)

To run the entire suite defined in `testng.xml`:
```bash
mvn clean test
```

### Running inside an IDE (IntelliJ IDEA / Eclipse)

1. Open the project root folder in your favorite Java IDE (e.g., **IntelliJ IDEA**).
2. Wait for Maven dependencies to import completely.
3. Right-click the `testng.xml` file at the root.
4. Select **Run '.../testng.xml'**.
5. Alternatively, run individual test classes or test methods by clicking the green play arrow next to their declarations.

---

## 🧪 Detailed Test Scenarios Covered

### 1. Account Registration (`RegistrationTest.java`)
* **`successfulUserRegistrationWithValidData`**: Submits the form with complete, valid details and asserts successful redirection to the login screen.
* **`registrationWithPasswordMismatch`**: Asserts validation errors when passwords do not match.
* **`registrationWithInvalidEmail`**: Verifies email formatting error message for inputs lacking `@` or domains.
* **`registrationWithEmptyFirstName`**: Asserts validation error when mandatory fields like First Name are blank.
* **`registrationWithInvalidPhone`**: Ensures phone numbers shorter than 10 digits are rejected with a clear message.

### 2. Authentication (`LoginTest.java`)
* **`successfulLoginWithValidCredentials`**: Logs in using valid test credentials and confirms redirection to the Dashboard.
* **`loginWithNonExistentEmail`**: Asserts error message when attempting login with unregistered emails.
* **`loginWithIncorrectPassword`**: Confirms that incorrect passwords trigger security errors.
* **`loginWithEmptyCredentials`**: Asserts inline validation triggers for blank username/password inputs.

### 3. Shopping Cart (`CartTest.java`)
* **`addToCart`**: Navigates to the shopping page, adds a product, and asserts that the cart badge incremented.
* **`viewInCart`**: Adds a random quantity of items to the cart and validates that the exact number reflects inside the cart overview page.
* **`removeFromCart`**: Confirms that removing a product from the cart decreases the item count accurately.
* **`adjustQuantity`**: Increments quantities on the cart page and validates that the subtotal is calculated correctly.

### 4. Checkout & Order Placement (`CheckoutTest.java`)
* **`proceedToCheckout`**: Verifies transition from shopping cart to the secure checkout page.
* **`checkoutMissingPinCode`**: Validates form error handling when mandatory fields (like Pincode) are left blank.
* **`checkoutValidFlow`**: Fills in full delivery details, verifies billing overview, places an order, and asserts the successful order confirmation message: `🎉 Thank You for Your Order!`.

### 5. Responsiveness & UI (`UIandResponsiveTest.java`)
* **`responsiveLayoutVerification`**: Programs the browser to match three major viewports:
  - **Desktop:** `1920 x 1080`
  - **Tablet:** `768 x 1024`
  - **Mobile:** `375 x 667`
  * Validates that the product list and search input are fully visible on all platforms, and asserts that no horizontal scrollbars (page overflow) are present in any responsive viewport.

---

## 🔒 Security & Code Quality Standards

* **No Hardcoded Production Secrets:** Test credentials (`demo@demo.com`) are standard, public sandbox user profiles.
* **Secure Session Handling:** Standard test cleanups clear localStorage, cookies, and sessionStorage between runs to guarantee test isolation.
* **Encapsulated Locators:** Selectors (`By` variables) are strictly declared as `private` or `protected` within their respective Page Object classes to prevent leaking selector details to tests.

---
