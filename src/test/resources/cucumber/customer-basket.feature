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

  Scenario: Add a product to a customer basket
    Then the response json should be:
    """
      {
        "id": "789",
        "customerId": "bob",
        "productId": "iPhone",
        "quantity": 3
      }
    """

  Scenario: Remove product from basket
    When call GET on /customers/bob/basket/products
    Then the response json should be:
    """
     [
      {
        "id": "789",
        "customerId": "bob",
        "productId": "iPhone",
        "quantity": 3
      }
     ]
    """
    When call DELETE on /customers/bob/basket/products/iPhone
    Then the response status should be 200
    When call GET on /customers/bob/basket/products
    Then the response json should be:
    """
     [ ]
    """
