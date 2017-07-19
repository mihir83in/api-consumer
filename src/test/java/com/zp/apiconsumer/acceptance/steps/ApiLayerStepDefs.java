package com.zp.apiconsumer.acceptance.steps;

import com.zp.apiconsumer.acceptance.Application;
import com.zp.apiconsumer.acceptance.Model.ApiDataModel;
import com.zp.apiconsumer.client.CurrencyClient;
import com.zp.apiconsumer.client.currencylayer.CurrencyLayerClient;
import com.zp.apiconsumer.client.oxr.OxrClient;
import com.zp.apiconsumer.commons.Currency;
import com.zp.apiconsumer.commons.model.api.CurrencyList;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections.CollectionUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class ApiLayerStepDefs {

    private static final int DELAY = 10000;

    @Autowired
    private CurrencyLayerClient currencyLayerClient;

    @Autowired
    private OxrClient oxrClient;

    @Autowired
    private ApiDataModel apiDataModel;


    @Given("^that I call (.+) to get rate for (.+) currency for date (.+)$")
    public void givenThatICallClientToGetHistoricalDataForACurrency(String clientName, String currency, String date) {

        CurrencyClient currencyClient = getCurrencyClient(clientName);
        CurrencyRates currencyRates = "today".equalsIgnoreCase(date) ? currencyClient.getLatestRates(currency) :
                currencyClient.getHistoricalRates(date, currency);

        assertNotNull(currencyRates);
        apiDataModel.setCurrencyRates(currencyRates);
    }


    private CurrencyClient getCurrencyClient(String clientName) {

        return currencyLayerClient.getClientName().equals(clientName) ? currencyLayerClient : oxrClient;
    }


    @When("^I wait for the data to be retrieved$")
    public void iWaitForTheDataToBeRetrieved$() {

        try {
            Thread.sleep(DELAY);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Then("^I should be able to retrieve rate for currency (.+)$")
    public void iShouldBeAbleToRetrieveRateForGivenCurrency(String currency) {

        Map<Currency, BigDecimal> data = apiDataModel.getCurrencyRates().getData();

        assertNotNull(data);
        assertNotNull(data.get(Currency.fromName(currency)));
    }


    @Given("^that I call (.+) to get list of currencies$")
    public void iCallClientToGetListOfCurrencies(String clientName) {

        CurrencyList supportedCurrencies = getCurrencyClient(clientName).getSupportedCurrencies();
        assertNotNull(supportedCurrencies);

        apiDataModel.setCurrencyList(supportedCurrencies);
    }


    @Then("^I should be able to retrieve list of currencies$")
    public void iShouldBeAbleToRetrieveListOfCurrencies() {

        assertTrue(CollectionUtils.isNotEmpty(apiDataModel.getCurrencyList().getCurrencies()));

        long count = apiDataModel.getCurrencyList().getCurrencies().stream()
                .filter(currencyItem -> Optional.ofNullable(Currency.fromName(currencyItem.getName())).isPresent()).count();

        assertEquals(count, Currency.values().length);
    }
}
