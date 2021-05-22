Feature: This feature will verify home loan calculator is working as expected

@smoke
Scenario Outline: verify calculator is processing customer data and calculates home loan amount one can borrow
Given I am on the homeloan calculator page
When I provide all the required input from data file <dataSet>
Then I see the amount that can be borrowed
Examples:
| dataSet |
| validData |

@smoke
Scenario Outline: verify calculator is processing user data and displays a message
Given I am on the homeloan calculator page
When I provide all the required input from data file <dataSet>
Then I see the message <message>
Examples:
| dataSet | message |
| invalidData | Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500. |
  
@smoke
Scenario Outline: verify customer can start over by resetting the data on loan page
Given I am on the homeloan calculator page
When I provide all the required input from data file <dataSet>
#Then I see the amount that can be borrowed
Then I select start over
Then I see that data is reset for all the fields
Examples:
| dataSet |
| invalidData |