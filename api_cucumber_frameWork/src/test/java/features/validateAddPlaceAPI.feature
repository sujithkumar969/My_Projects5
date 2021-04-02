Feature: Validate AddPlace API

@addPlaceAPI @RegressionTest
Scenario Outline: Verify the user should be able to add place using AddPlace API

      Given Place payload "<Accuracy>" "<name>" "<phone no>" "<address>"
      When user calls "addPlaceAPI" with "put" http request
      Then API call is successfull with status code 200
      And "status" in response body should be "OK"
      And "scope" in response body should be "APP"
      And verify that place Id maps to "<name>" by calling "getPlaceAPI" with "get" http request

Examples:

        |Accuracy|name         |phone no        |address|
        |50      |Sujith House1|(+91) 7259310103|29, side layout, cohen 09|
#       |70      |Sujith House2|(+91) 9738847136|79, denin layout, cohen 88|


@deletePlaceAPI @RegressionTest
Scenario: Verify the user should be able to delete place using deletePlaceAPI

      Given delete place payload
      When user calls "deletePlaceAPI" with "delete" http request
      Then API call is successfull with status code 200
      And "status" in response body should be "OK"