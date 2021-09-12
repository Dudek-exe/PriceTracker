Feature: Requesting for prices of selected product from x-kom (GET)

  Scenario Template: User calls for subscribed product's prices

    When User prepares and executes GET request for <productName>

    Then HTTP Status is successful
    And Every record or the response has the same <productName>
    And ShopType is <shopType>


    Examples:
      | productName                                          | shopType |
      | "Apple iPhone 12 128GB Purple 5G"                    | XKOM     |
      | "Samsung Galaxy S20 FE Fan Edition Snapdragon White" | XKOM     |






