Feature: Feature to provide a timer which will count down from the given time

  Scenario Outline: Validate that the countdown timer works with the correct time duration
    Given I am on the EggTimer Homepage
    When I provide <time> seconds to the timer
    And I click on start button
    Then I should see that timer has started
    And the seconds count should go down by one second each time

    Examples:
    | time     |
    | 10       |
    | 25       |
    | 10000000 |

    Scenario Outline: Providing incorrect time in seconds and expecting errors
      Given I am on the EggTimer Homepage
      When I enter invalid <time> in seconds to the timer
      And I click on start button
      Then I should see timer should not start

      Examples:
      | time |
      | ""   |
      | -1   |
      | $1   |
      | ABCD |