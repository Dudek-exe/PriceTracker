Feature: Requesting scrapping from shops -POST

  Scenario Template: User calls for product's price subscription

    When User prepares and executes POST request as below:
      | url   | borderPrice   |
      | <url> | <borderPrice> |

    Then HTTP Status is created
    And Field productName name is <productName>
    And Field productPrice is <productPrice>
    And Field shopType is <shopType>

    Examples:
      | borderPrice | url                                                                                               | productName                                              | productPrice | shopType    |
      | 4449.00     | https://www.x-kom.pl/p/648711-smartfon-telefon-apple-iphone-12-128gb-purple-5g.html               | "Apple iPhone 12 128GB Purple 5G"                        | 3999.00      | XKOM        |
      | null        | https://www.x-kom.pl/p/541186-smartfon-telefon-samsung-galaxy-s20-g980f-dual-sim-cloud-blue.html  | "Samsung Galaxy S20 G980F Dual SIM Cloud Blue"           | 3349.00      | XKOM        |
      | null        | https://mediamarkt.pl/telefony-i-smartfony/smartfon-apple-iphone-12-pro-128gb-grafitowy-mgmk3pm-a | "Smartfon APPLE iPhone 12 Pro 128GB Grafitowy MGMK3PM/A" | 4899.00      | MEDIA_MARKT |

