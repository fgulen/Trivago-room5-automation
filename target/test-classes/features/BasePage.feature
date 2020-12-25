@regression
Feature: Basepage/Mainmenu functions

  Background:
    Given user opens homepage

  Scenario Outline: Destination links go to page title
    When user clicks mainMenuIcon and clicks "destination" and "<destinationOptions>"
    Then verify that page title is "<pageTitle>"
    Examples:
      | destinationOptions | pageTitle |
      | Northwest          | Northwest |
      | Southwest          | Southwest |
      | Midwest            | Midwest   |
      | Southeast          | Southeast |
      | Northeast          | Northeast |


  Scenario:Destination menu has following datas
    When user clicks mainMenuIcon and clicks "destination"
    Then verify that menu exist following options
      | NORTHWEST |
      | SOUTHWEST |
      | MIDWEST   |
      | NORTHEAST |
      | SOUTHEAST |


  Scenario Outline:Search function
    When user clicks search button and writes "<destinations>" to search
    Then verify that message contains following "<destinations>"
    Examples:
      | destinations | texts          |
      | USA          | Search Results |
      | Ankara       | No results     |
      | Arizona      | Search Results |
      | Leverkusen   | No results     |
      | Istanbul     | Search Results |


