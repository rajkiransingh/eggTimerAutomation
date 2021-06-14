# Egg Timer Automation Framework
> Selenium and cucumber based project using Java as a programming language.

## General info
- 'src/test/java/pages/' - Place to keep all our pages together
- 'src/test/java/setup/' - Place to keep initial setup
- 'src/test/java/StepDefinitions/' - Place to keep all step definitions
- 'src/test/java/utils/' - Place to keep utilities and constants

- 'src/test/resources/drivers/' - all the webdriver executables are stored here
- 'src/test/resources/Features/' - Place to keep all the feature files

## Technologies
- Language: Java
- Framework: Selenium, Cucumber
- Build Tool: Gradle
- Test Framework: JUNIT4
- Logging: log4j

## Pre-requisite
- Java 8 or above
- Gradle 6.3 or above

## Execution
### For running the code in Chrome
- Open CMD
- Navigate to the project location
- Run command 'gradlew test -Dbrowser=ch'

Note: For running in Firefox, just use the browser property -Dbrowser=ff
    
## Highlights
- Used POM to separate the elements and methods from the code
- Separate Browser and driver initialization
- BDD for easy read
- Ran positive and negative tests

## Improvements
- I could have improved browse and environment selection from command prompt
- Timer validation code can be improved, currently its brittle and depends on page load time
- Logging and Reporting can be improved

## Bugs
- There are few bugs I have noticed and 2 of them are validated in the cucumber tests as well.
    - If I provide a large number in the field, timer will not start
    - for negative number, timer starts with some random time
    - Font of the timer changes in middle of the run
    - There are no error message, I have to use other assertions to validate that timer is not displayed
    


