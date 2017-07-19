Feature: Currency Layer Test
    Scenario: Get Historical Data
        Given that I call CurrencyLayerClient to get rate for GBP currency for date 2016-07-19
        When I wait for the data to be retrieved
        Then I should be able to retrieve rate for currency GBP

    Scenario: Get Latest Data
        Given that I call CurrencyLayerClient to get rate for GBP currency for date today
        When I wait for the data to be retrieved
        Then I should be able to retrieve rate for currency GBP

    Scenario: Get list of currencies
        Given that I call CurrencyLayerClient to get list of currencies
        When I wait for the data to be retrieved
        Then I should be able to retrieve list of currencies