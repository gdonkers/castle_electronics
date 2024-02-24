Feature: An administrator can manage products

  Background:
    Given an empty database
    When Id Generator returns 123
    Then POST json to /products:
    """
      {
        "name": "iPhone",
        "unitPrice": 1000.0
      }
    """
    And the response status should be 200
    And Id Generator returns 999

  Scenario: Create a new product
    Then the response json should be:
    """
      {
        "id": "123",
        "name": "iPhone",
        "unitPrice": 1000.0
      }
    """

  Scenario: Get existing products
    When call GET on /products
    Then the response status should be 200
    Then the response json should be:
    """
     [
        {
          "id": "123",
          "name": "iPhone",
          "unitPrice": 1000.0
        }
     ]
    """

  Scenario: Update a product
    When POST json to /products:
    """
      {
        "id":"123",
        "name": "iPhone15",
        "unitPrice": 2000.0
      }
    """
    Then the response json should be:
    """
      {
        "id": "123",
        "name": "iPhone15",
        "unitPrice": 2000.0
      }
    """
    When call GET on /products
    Then the response json should be:
    """
     [
        {
          "id": "123",
          "name": "iPhone15",
          "unitPrice": 2000.0
        }
     ]
    """
  Scenario: Remove an existing product
    When call DELETE on /products/123
    Then the response status should be 200
    When call GET on /products
    Then the response json should be:
    """
     [ ]
    """

