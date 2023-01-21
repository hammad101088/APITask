[![Desktop Browsers Tests](https://github.com/hammad101088/Terkwaz-Automation-Task/actions/workflows/continuous-integration.yml/badge.svg)](https://github.com/hammad101088/Terkwaz-Automation-Task/actions/workflows/continuous-integration.yml)
# VOIS Test Automation Assignment (FIVE IN A ROW GAME)

* Implement a test automation framework using **SHAFT_Engine** for testing **Five in a Row game API**.
* Implement modular design by Applying the Page Object Model design pattern (POM).
* Externalize test data using JSON files And Applying data driven testing (DDT).


# Technologies

* JDK-17
* [Maven](https://maven.apache.org/) as a build tool.
* [TestNG](https://testng.org/) as a unit test framework.
* [SHAFT_ENGINE](https://github.com/MohabMohie/SHAFT_ENGINE) which is a Test Automation Engine that provides a unified high-level interface to any of the underlying test automation frameworks.
* [REST-Assured](https://rest-assured.io/) for API testing.
* [Allure Report Framework](https://docs.qameta.io/allure/) for generating test execution report.
* [GitHub Actions](https://docs.github.com/en/actions) for continous integration.


# Run the Test
## 1. LOCALLY
Prerequisites: jdk-17 and maven should be installed

### 1.1 Run the Test locally using Eclipse IDE

* Import this project to your eclipse.
* Run the test cases/classes
* The report will be generated automatically after running the test.

### 1.2 Run the Test locally using IntelliJ IDEA

* Import this project to your intellij.
* Kindly, refer to this [link](https://github.com/shafthq/SHAFT_ENGINE) and follow the steps to add the listeners in intellij.
* Run the test cases/classes
* The report will be generated automatically after running the test.

### 1.3 Run the Test locally using the Terminal or CMD

* Clone or download the project.
* At the root directory of the project (e.g. downloads/projectName/); run the following command ```mvn test```
* The report will be generated automatically after running the test.

------------------

#### Important Note (for local execution):
- By default, allure report will be automatically generated after the execution, but you can check the result of the the test by running "generate_allure_report.bat" or "generate_allure_report.sh" files.



