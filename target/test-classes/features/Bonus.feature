@regression
Feature: DataLayer Test

  Background: Events and Data
    Given user opens homepage
    Then user click on read more button in the hero image

  Scenario Outline:Fired Events and containing data
    When following "<events>" fired
    Then verify "<events>" contains following "<data>"
    Examples:
      | events                | data                    |
      | OneTrustLoaded        | C0001,C0003,C0002,C0004 |
      | OptanonLoaded         | C0001,C0003,C0002,C0004 |
      | OneTrustGroupsUpdated | C0001,C0003,C0002,C0004 |


  Scenario: Content Load event and HotelIds
    When  Check "contentLoaded" is fired
    Then verify when the "contentLoaded" is fired check hotelIds are not null
    And verify when the "contentLoaded" is fired "target-propertiesd" has the same path in the URL "https://magazine.trivago.com/top-vacation-spots-us"
