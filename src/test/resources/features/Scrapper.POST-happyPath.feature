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
      | borderPrice | url                                                                                                                | productName                                                  | productPrice | shopType    |
      | 4449.00     | https://www.x-kom.pl/p/648711-smartfon-telefon-apple-iphone-12-128gb-purple-5g.html                                | "Apple iPhone 12 128GB Purple 5G"                            | 3849.00      | XKOM        |
      | null        | https://www.x-kom.pl/p/541186-smartfon-telefon-samsung-galaxy-s20-g980f-dual-sim-cloud-blue.html                   | "Samsung Galaxy S20 G980F Dual SIM Cloud Blue"               | 3349.00      | XKOM        |
      | null        | https://mediamarkt.pl/telefony-i-smartfony/smartfon-apple-iphone-12-pro-128gb-grafitowy-mgmk3pm-a                  | "Smartfon APPLE iPhone 12 Pro 128GB Grafitowy MGMK3PM/A"     | 4799.00      | MEDIA_MARKT |
      | null        | https://www.jula.pl/catalog/wypoczynek/trening/przyrzady-treningowe/hantle-i-gryfy-do-sztang/zestaw-hantli-951147/ | "Zestaw hantli 18 kg"                                        | 229.00       | JULA        |
      | 1000        | https://www.jula.pl/catalog/ogrod/wyposazenie-ogrodu/ogrodzenia-i-ploty/slupki/slupek-ogrodzeniowy-738217/         | "Słupek ogrodzeniowy 150 cm"                                 | 16.99        | JULA        |
      | 1200        | https://www.bikester.pl/serious-gravix-black-mattmango-1281870.html                                                | "Serious Gravix, czarny (2021)"                              | 4499.00      | BIKESTER    |
      | null        | https://www.bikester.pl/cube-reaction-hybrid-performance-625-allroad-metallicgreynwhite-1484786.html               | "Cube Reaction Hybrid Performance 625 Allroad, szary (2022)" | 13249.00     | BIKESTER    |