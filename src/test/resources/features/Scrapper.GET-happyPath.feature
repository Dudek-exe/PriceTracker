Feature: Requesting for prices of selected product from shop -GET

  Scenario Template: User calls for subscribed product's prices

    When User prepares and executes GET request for <productName>

    Then HTTP Status is successful
    And Every record of the response has the same productName: <productName>
    And Every record of the response has the <shopType>

    Examples:
      | productName                                                              | shopType    |
      | "Apple iPhone 12 128GB Purple 5G"                                        | XKOM        |
      | "Samsung Galaxy S20 FE Fan Edition Snapdragon White"                     | XKOM        |
      | "Smartfon APPLE iPhone 12 Pro 128GB Grafitowy MGMK3PM/A"                 | MEDIA_MARKT |
      | "Schowek na narzędzia Darwin126x205x185 cm"                              | JULA        |
      | "Zestaw kluczy nasadowych 40 elementów1/4\""                             | JULA        |
      | "Gonso Sitivo Thermo Bib Tights with Firm Seat Pad Men, czerwony (2021)" | BIKESTER    |






