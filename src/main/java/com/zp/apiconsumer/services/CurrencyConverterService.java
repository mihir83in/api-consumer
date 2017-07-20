package com.zp.apiconsumer.services;

import com.zp.apiconsumer.client.CurrencyClient;
import com.zp.apiconsumer.commons.Currency;
import com.zp.apiconsumer.commons.model.api.CurrencyList;
import com.zp.apiconsumer.commons.model.api.CurrencyRates;
import com.zp.apiconsumer.exception.ApiConsumerException;
import com.zp.apiconsumer.loadbalancer.CurrencyClientLoadBalancer;
import com.zp.apiconsumer.loadbalancer.LoadBalancerStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.metrics.dropwizard.DropwizardMetricServices;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;


/**
 * Currency Conversion service
 */
@Service
@Slf4j
public class CurrencyConverterService {

    private final LoadBalancerStats loadBalancerStats;
    private CurrencyClientLoadBalancer currencyClientLoadBalancer;
    private DropwizardMetricServices metricServices;
    private final String symbols = Currency.stringify();


    public CurrencyConverterService(CurrencyClientLoadBalancer currencyClientLoadBalancer,
            DropwizardMetricServices metricServices) {

        this.currencyClientLoadBalancer = currencyClientLoadBalancer;
        this.metricServices = metricServices;
        this.loadBalancerStats = new LoadBalancerStats();
    }


    /**
     * Returns supported currencies
     *
     * @return List of supported currencies
     */
    @Retryable
    public CurrencyList getSupportedCurrencies() {

        StopWatch stopWatch = startStopWatch();

        CurrencyClient client = getClient();
        CurrencyList supportedCurrencies = client.getSupportedCurrencies();
        stopWatch.stop();
        log.debug("got suppportedCurrencies:{} with {}", supportedCurrencies, client.getClientName());

        metricServices.submit(getTimedMetricPrefix(client) + ".supportedCurrencies", stopWatch.getTotalTimeMillis());
        return supportedCurrencies;
    }


    /**
     * Returns historical currency rates
     *
     * @param date date of history
     * @return List of currency rates
     */
    @Retryable
    public CurrencyRates getHistoricalRates(String date) {

        return getHistoricalRates(date, symbols);
    }


    /**
     * Returns historical currency rates
     *
     * @param date date of history
     * @param from from currency
     * @param to   to currency
     * @return List of currency rates
     */
    @Retryable
    @Cacheable(cacheNames = "currencies", unless = "#result == null")
    public CurrencyRates getHistoricalRates(Currency from, Currency to, String date) {

        return getHistoricalRates(date, from.toString() + "," + to.toString());
    }


    /**
     * Returns latest rates
     *
     * @return rates of currency
     */
    @Retryable
    @Cacheable(cacheNames = "currencies", unless = "#result == null")
    public CurrencyRates getLatestRates() {

        return getLatestRates(symbols);
    }


    /**
     * Returns latest rates of given currencies.
     *
     * @param from from currency
     * @param to   to currency
     * @return rates of currencies
     */
    @Retryable
    @Cacheable(cacheNames = "currencies", unless = "#result == null")
    public CurrencyRates getLatestRates(Currency from, Currency to) {

        return getLatestRates(from.toString() + "," + to.toString());
    }


    private CurrencyRates getHistoricalRates(String date, String symbols) {

        StopWatch stopWatch = startStopWatch();

        CurrencyClient client = getClient();
        CurrencyRates historicalRates = client.getHistoricalRates(date, symbols);
        stopWatch.stop();
        log.debug("got getHistoricalRates:{} with {}", historicalRates, client.getClientName());
        throwUp(historicalRates);

        metricServices.submit(getTimedMetricPrefix(client) + ".historical", stopWatch.getTotalTimeMillis());
        return historicalRates;
    }


    private CurrencyRates getLatestRates(String symbols) {

        StopWatch stopWatch = startStopWatch();

        CurrencyClient client = getClient();
        CurrencyRates latestRates = client.getLatestRates(symbols);
        stopWatch.stop();
        log.debug("got getLatestRates:{} with {}", latestRates, client.getClientName());
        throwUp(latestRates);

        metricServices.submit(getTimedMetricPrefix(client) + ".latest", stopWatch.getTotalTimeMillis());
        return latestRates;
    }


    private void throwUp(CurrencyRates currencyRates) {

        if (currencyRates.getErrorResponse() != null) {
            throw new ApiConsumerException(currencyRates.getErrorResponse().toString());
        }
    }


    private StopWatch startStopWatch() {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        return stopWatch;
    }


    private String getTimedMetricPrefix(CurrencyClient currencyClient) {

        return "timer." + currencyClient.getClientName();
    }


    private CurrencyClient getClient() {

        return currencyClientLoadBalancer.getClient(loadBalancerStats);
    }
}
