# Sauce Demo Capstone Project

## About the project
This project is a capstone project required for finishing IT Bootcamp's Software testing (manual + automation) course. Its purpose is showcasing the knowledge and competence of a student. I was assigned automatizing [Sauce Demo](https://www.saucedemo.com/), a well-known UI automation learning tool. In order to do so, I used the following tech stack:
* **Java** programming language.
* **Selenium** testing framework (**Page Object Model** using **Page Factory library**).
The project is done following all principles of object oriented programming and POM testing.
### Manual foundation
This automatization is based on 41 Test Cases which I deemed most important or interesting. Test Cases are accessible in the root of the project. 

## Structure
Everything I did is located in **src/test/java**. As per Page Object Model infrastructure, it contains:
### Base
* **BaseTest**, the fundamental class.
### Pages
The **Pages** package contains Page Object Model representations of all key application screens. Each page encapsulates its locators and behavior, improving readability, reusability, and test maintainability.
* **LoginPage** — Handles login interactions and credential validation.
* **HomePage** — Default product listing page displayed after successful login.
* **HomePageWithFilter** — Represents the home page state with applied sorting/filter options.
* **InventoryItemPage** — Product details page for individual inventory items.
* **CartPage** — Shopping cart page before items are added.
* **CartWithItemsPage** — Cart page in a populated state (used to validate item persistence and totals).
* **CheckoutStepOnePage** — First step of checkout (user info form).
* **CheckoutStepOneWithErrorMessagePage** — Same page in an error-state scenario (used for negative test cases).
* **CheckoutStepTwoPage** — Order summary and price breakdown screen.
* **CheckoutCompletePage** — Final confirmation page after successful checkout.
* **Menu** — Side navigation / burger menu page object.
* **Footer** — Footer component used across multiple pages.
### Tests
The **Tests** package contains end-to-end UI test scenarios organized by application functionality. Each test class focuses on a specific feature group and relies on page objects rather than direct Selenium calls.
* **LoginTest** — Valid and invalid login scenarios.
* **LogoutTest** — Logout behavior validation.
* **InventoryTest** — Product listing and item navigation tests.
* **FilterTest** — Sorting and filtering behavior tests.
* **CartTest** — Addin and removing cart items.
* **CheckoutTest** — Positive and negative checkout flows across both steps.
* **MenuTest** — Menu navigation and link validation.
* **FooterLinksTest** — Visibility and navigation of footer links.

