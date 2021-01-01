@regression
Feature: Bonus DataLayer

  Background:
    Given user opens homepage
    When user click on Read more button in the Hero Image

  Scenario Outline: DataLayer verification
    When user opens the page verify following "<events>" are fired and contains following "<data>"
    And verify "contentLoaded" is fired and hotelId is not null
    Then "target-properties" has the same path in the URL "https://magazine.trivago.com/top-vacation-spots-us"
    Examples:
      | events                | data                    |
      | OneTrustLoaded        | C0001,C0003,C0002,C0004 |
      | OptanonLoaded         | C0001,C0003,C0002,C0004 |
      | OneTrustGroupsUpdated | C0001,C0003,C0002,C0004 |
