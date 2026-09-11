Feature: Basic practice form submission

  Scenario Outline: Submit the basic form with multiple valid data sets
    Given user opens the basic form page
    When user enters "<name>", "<password>", "<email>", "<phone>", and "<bio>"
    And submits the form
    Then the form submission message should show "Form submitted successfully"

    Examples:
      | name          | password   | email                    | phone        | bio                                              |
      | Alice Smith  | Pass@1234 | alice.smith@example.com  | 9876543210   | I am a QA automation engineer                    |
     # | Bob Johnson  | Welcome1! | bob.johnson@example.com  | 9123456780   | I enjoy testing web applications and APIs       |
     # | Carol White  | Secure#99 | carol.white@example.com  | 9988776655   | I love building reliable and maintainable tests |
     # | David Brown  | Abcd@4321 | david.brown@example.com  | 9765432109   | I work with Selenium and Cucumber for UI checks |
