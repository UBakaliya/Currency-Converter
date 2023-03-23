package com.example.currency_converter;

import java.util.*;

/**
 * @implNote This class hele use manumitted the api data and all the usefully
 * functions for converting the currencies.
 */
public class ManipulateAPIData implements ManipulateAPIDataInterface {
    private String currenciesWithRatesResp, countriesWithCurrencyCodesResp;
    private HashMap<String, Double> apiData;

    /**
     * @implNote Get all the data and assign it to response
     */
    public ManipulateAPIData() {
        // * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * * *  * * * *
        // * @Currencies code with rates (API): "https://cdn.moneyconvert.net/api/latest.json"         *
        // * @Countries with currency codes (API): "https://openexchangerates.org/api/currencies.json" *
        // * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * * *  * * * *

        final String API_URL_CURRENCY_WITH_RATES = "https://cdn.moneyconvert.net/api/latest.json"; // IN USED
        final String API_URL_COUNTRIES_WITH_CURRENCY_CODES = "https://openexchangerates.org/api/currencies.json"; // NOT USED
        FetchCurrencyRatesAPI fetchAPI = new FetchCurrencyRatesAPI();
        this.currenciesWithRatesResp = fetchAPI.fetchAPI(API_URL_CURRENCY_WITH_RATES); // Get the api data

        // Build the map cleaning the response
        this.buildAPIDataMap();
    }

    /**
     * @implNote Build the map that will have the currency as the
     * key of the map and the value as the rate i.e. USD --> 1
     */
    private void buildAPIDataMap() {
        // Trim the api response and only get the useful information
        this.currenciesWithRatesResp = this.currenciesWithRatesResp.substring(
                30, this.currenciesWithRatesResp.length() - 52);

        ArrayList<String> splitDataCurrenciesWithRates = new ArrayList<>(Arrays.asList(
                this.currenciesWithRatesResp.split(",")));
        this.apiData = new HashMap<>();

        // Trim the unnecessary information and build the map
        for (final String i : splitDataCurrenciesWithRates) {
            String currency = i.substring(0, i.indexOf(":")).replaceAll("\\s", "").replaceAll("\"", "");
            double rate = Double.parseDouble(i.substring(i.indexOf(":") + 1));
            this.apiData.put(currency, rate);
        }

    }

    /**
     * @return apiData.size -> int
     * @implNote Useful for testing
     */
    public int apiDataMapSize() {
        return this.apiData.size();
    }

    /**
     * @return current date
     */
    @Override
    public String getCurrentDate() {
        return String.valueOf(java.time.LocalDate.now());
    }

    /**
     * @return get all the currencies name useful of the listing to choose
     * user can use from
     */
    @Override
    public SortedSet<Object> getAllCurrenciesNames() {
        return new TreeSet<>(this.apiData.keySet());
    }

    /**
     * @param currency -> String
     * @return currencies -> String
     */
    @Override
    public double getRateForGivenCurrency(String currency) {
        if (!this.apiData.containsKey(currency.toUpperCase()))
            return -1;
        return this.apiData.get(currency);
    }

    /**
     * @return get all the information in the api, but without the non
     * need it information. It will return the string version of the api
     */
    @Override
    public String getCurrenciesNamesWithRates() {
        StringBuilder currenciesWithRates = new StringBuilder();
        Set<String> keys = this.apiData.keySet();
        for (final String i : keys) {
            currenciesWithRates.append(i).append(" ").append(this.apiData.get(i)).append("\n");
        }
        return currenciesWithRates.toString();
    }


    /**
     * @return this function will convert amount of the give currency into one and
     * another and return the calculation of it
     */
    @Override
    public double calculateRates(String amount, String currency1, String currency2) {

        double currency1Rate = this.getRateForGivenCurrency(currency1),
                currency2Rate = this.getRateForGivenCurrency(currency2);

        return currency2Rate * (Double.parseDouble(amount) / currency1Rate);
    }

}