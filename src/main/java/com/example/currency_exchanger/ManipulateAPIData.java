package com.example.currency_exchanger;

import java.util.*;

public class ManipulateAPIData implements ManipulateAPIDataInterface {
    private String resp;
    private HashMap<String, Double> apiData;

    /**
     * Get all the data and assign it to response
     */
    ManipulateAPIData(final String API_URL) {

        /*
         * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * * *
         * @api: "https://cdn.moneyconvert.net/api/latest.json" ( API USED FOR THE PROGRAM) *
         * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * *  * * * *
         */

        FetchCurrencyRatesAPI fetchAPI = new FetchCurrencyRatesAPI();
        this.resp = fetchAPI.fetchAPI(API_URL); // Get the api data
        // Build the map cleaning the response
        this.buildAPIDataMap();
    }

    /**
     * Build the map that will have the currency as the key of the map and the value as the rate
     * i.e. USD --> 1
     */
    private void buildAPIDataMap() {
        // Trim the api response and only get the useful information
        this.resp = this.resp.substring(30, this.resp.length() - 52);

        ArrayList<String> splitData = new ArrayList<>(Arrays.asList(this.resp.split(",")));
        this.apiData = new HashMap<>();

        // Trim the unnecessary information and build the map
        for (final String i : splitData) {
            String currency = i.substring(0, i.indexOf(":")).replaceAll("\\s", "").replaceAll("\"", "");
            double rate = Double.parseDouble(i.substring(i.indexOf(":") + 1));
            this.apiData.put(currency, rate);
        }
    }

    // Useful for testing
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
     * @param currency passing the name will
     * @return give the rate of the given currency name
     */
    @Override
    public double getRateForGivenCurrency(String currency) {
        if (!this.apiData.containsKey(currency))
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
}
