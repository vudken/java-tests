Feature: Weather

  Scenario: Check correct weather data
    Given city "London"
    And country "UK"

    When we are requesting weather data

    Then coord are:
      | lon | -0.13 |
      | lat | 51.51 |

    And weather is:
      | id          | 300                     |
      | main        | Drizzle                 |
      | description | light intensity drizzle |
      | icon        | 09d                     |

    And base is: "stations"

    And main is:
      | temp     | 280.32 |
      | pressure | 1012   |
      | humidity | 81     |
      | temp_min | 279.15 |
      | temp_max | 281.15 |

    And visibility is: 10000

    And wind is:
      | speed | 4.1 |
      | deg   | 80  |

    And clouds are: 90

    And dt is: 1485789600

    And sys is:
      | type    | 1          |
      | id      | 5091       |
      | message | 0.0103     |
      | country | GB         |
      | sunrise | 1485762037 |
      | sunset  | 1485794875 |

    And id is: 2643743

    And name is: "London"

    And cod is: 200
