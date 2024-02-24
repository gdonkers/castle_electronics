Feature: An administrator can manage discount deals on products

  Background:
    Given an empty database
    When Id Generator returns 123
    And POST json to /products:
    """
      {
        "name": "iPhone",
        "unitPrice": 1000.0
      }
    """
    Then the response status should be 200
    When call POST on /products/123/discount-deals/Buy_1_get_50_pct_off_second
    Then the response status should be 200
    And the response json should be:
    """
      {
        "productId": "123",
        "dealType": "Buy_1_get_50_pct_off_second"
      }
    """
    Then the response status should be 200

  Scenario: Add and get a deal discount
    When call GET on /products/123/discount-deals
    Then the response status should be 200
    Then the response json should be:
    """
     [
        {
          "productId": "123",
          "dealType": "Buy_1_get_50_pct_off_second"
        }
     ]
    """

  Scenario: Try adding a deal discount to a non-existing product should fail
    When call POST on /products/456/discount-deals/Buy_1_get_50_pct_off_second
    Then the response status should be 500

  Scenario: Remove an existing deal
    When call DELETE on /products/123/discount-deals/Buy_1_get_50_pct_off_second
    Then the response status should be 200
    When call GET on /products/123/discount-deals
    Then the response json should be:
    """
     [ ]
    """

