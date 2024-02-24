Feature: System APIs exist for diagnostics and server maintenance

  Scenario: Call actuator for health
    When call GET on /actuator/health
    Then the response json should be:
    """
    {
      "status": "UP"
    }
    """

  Scenario: Call Swagger for live docs
    When call GET on /v3/api-docs
    Then the response json should contain '3.0.1' at path 'openapi'

