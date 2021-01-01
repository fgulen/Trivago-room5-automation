@regression
Feature:Contact page sending message

  Background:
    Given user opens homepage

  Scenario Outline: With valid data
    Given user clicks "contacts" links to go contact page
    When user writes message in "<textArea>", name in "<fullName>" and your mail in "<yourEmail>" inputs
    Then Verify that user can see the "Message Sent Successfully!" message on the page

    Examples:
      | textArea | fullName | yourEmail |
      | message  | name     | email     |
      | message  | name     | email     |