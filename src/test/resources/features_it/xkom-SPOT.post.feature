Feature: Requesting scrapping from x-kom (POST)

  Scenario Template: User calls for product's price subscription

    When User prepares and executes request as below:
      | url   | borderPrice   |
      | <url> | <borderPrice> |

    Then HTTP Status is successful
    And Product name is <productName>

    Examples:

      | borderPrice | url                                                                                              | productName                                    |
      | 4449.00     | https://www.x-kom.pl/p/648711-smartfon-telefon-apple-iphone-12-128gb-purple-5g.html              | "Apple iPhone 12 128GB Purple 5G"              |
      | null        | https://www.x-kom.pl/p/541186-smartfon-telefon-samsung-galaxy-s20-g980f-dual-sim-cloud-blue.html | "Samsung Galaxy S20 G980F Dual SIM Cloud Blue" |