Feature: Validate AddPlace API

Scenario Outline: Verify the user should be able to add place using AddPlace API
      Given Place payload "<Accuracy>" "<name>" "<phone no>" "<address>"
      When user calls "AddPlaceAPI" with "POST" http request
      Then API call is successfull with status code 200
      And "status" in response body should be "OK"
      And "scope" in response body should be "APP"

Examples:

|Accuracy|name         |phone no        |address|
|50      |Sujith House1|(+91) 7259310103|29, side layout, cohen 09|
|70      |Sujith House2|(+91) 9738847136|79, denin layout, cohen 88|