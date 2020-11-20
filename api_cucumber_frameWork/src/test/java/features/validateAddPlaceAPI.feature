Feature: Validate AddPlace API

Scenario: Verify the user should be able to add place using AddPlace API
      Given Place payload
      When user calls AddPlaceAPI with POST http request
      Then API call is successfull with status code "200"
      And "status" in response body should be "OK"
      And "scope" in response body should be "APP"
