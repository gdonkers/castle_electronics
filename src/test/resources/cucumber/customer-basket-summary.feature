Feature: A customer can add and remove products from his basket

  Background:
    Given an empty database
    When Id Generator returns iPhone
    Then POST json to /products:
    """
      {
        "name": "iPhone",
        "unitPrice": 1000.0
      }
    """
    And call POST on /products/iPhone/discount-deals/Buy_1_get_50_pct_off_second

    Given Id Generator returns 789
    When POST json to /customers/bob/basket/products:
    """
      {
        "customerId": "bob",
        "productId": "iPhone",
        "quantity": 3
      }
    """
    Then the response status should be 200

  Scenario: View summary of basket
    When call GET on /customers/bob/basket
    Then the response json should be:
    """
      {
        "totalPrice": 2500.0,
        "customerId": "bob",
        "products": [
          {
            "name": "iPhone",
            "quantity": 3,
            "originalUnitPrice": 1000.0,
            "totalPriceAfterDiscounts": 2500.0,
            "discounts": ["Buy_1_get_50_pct_off_second"]
          }
        ]
      }
    """

  Scenario: View summary of basket after discount deal was retracted
    Given call DELETE on /products/iPhone/discount-deals/Buy_1_get_50_pct_off_second
    When call GET on /customers/bob/basket
    Then the response json should be:
    """
      {
        "totalPrice": 3000.0,
        "customerId": "bob",
        "products": [
          {
            "name": "iPhone",
            "quantity": 3,
            "originalUnitPrice": 1000.0,
            "totalPriceAfterDiscounts": 3000.0,
            "discounts": []
          }
        ]
      }
    """

  Scenario: View summary of basket after a product was discontinued
    Given call DELETE on /products/iPhone
    When call GET on /customers/bob/basket
    Then the response status should be 500
