Narrative:
As a user
I want to search a flight with details through KAYAK.COM
So that flight search page dispayed all the details

Scenario: Search for the flight by providing all the details

Meta:
@kayak

Given I visit to Kayak site to search the flight
And I enter <Origin> and <Destination> Cities
And I select Departure date as <dept> days and Return date as <rtn> days from today
And I select Near by airports as <near> for both origin and destination
When I click Search
And I select the search result <N> row
Then I found Origin <Origin> and Destination <Destination> Details are same as the one displaying in the main screen
And I report flight details - Total Fare, Time of Travel


Examples:
|Origin|Destination|dept|rtn|near|N|
|MDW|BUF|12|15|false|2|