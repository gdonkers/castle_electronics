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
    And Id Generator returns 456

  Scenario: Add a deal discount
    Then call POST on /products/123/discount-deals/Buy_1_get_50_pct_off_second
    Then the response status should be 200
    Then the response json should be:
    """
      {
        "id": "456",
        "productId": "123",
        "dealType": "Buy_1_get_50_pct_off_second"
      }
    """
    When call GET on /products/123/discount-deals
    Then the response status should be 200
    Then the response json should be:
    """
     [
        {
          "id": "456",
          "productId": "123",
          "dealType": "Buy_1_get_50_pct_off_second"
        }
     ]
    """

  Scenario: Remove an existing deal
    Then call POST on /products/123/discount-deals/Buy_1_get_50_pct_off_second
    When call DELETE on /products/discount-deals/456
    Then the response status should be 200
    When call GET on /products/123/discount-deals
    Then the response json should be:
    """
     [ ]
    """

