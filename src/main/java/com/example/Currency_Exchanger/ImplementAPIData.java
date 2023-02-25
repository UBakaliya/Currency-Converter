package com.example.Currency_Exchanger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ImplementAPIData implements APIData {
    // Get all the data from the api:
    private String resp;
    private HashMap<String, Double> apiData;

    // Get all the data and assign it to response
    ImplementAPIData() {
        FetchCurrencyRatesAPI fetchAPI = new FetchCurrencyRatesAPI();
        String API_URL = "https://cdn.moneyconvert.net/api/latest.json";

        this.resp = fetchAPI.fetchAPI(API_URL);
        // Build the map cleaning the response
        this.buildAPIDataMap();
    }

    // Build the map that will have the currency as the key of the map and the value as the rate
    // i.e. USD --> 1
    private void buildAPIDataMap() {
        // Trim the api response and only get the useful information
        this.resp = this.resp.substring(30, this.resp.length() - 52);

        ArrayList<String> splitData = new ArrayList<>(Arrays.asList(this.resp.split(",")));
        this.apiData = new HashMap<>();

        // Trim the unnecessary information and build the map
        for (String i : splitData) {
            String currency = i.substring(0, i.indexOf(":")).replaceAll("\\s", "").replaceAll("\"", "");
            double rate = Double.parseDouble(i.substring(i.indexOf(":") + 1));
            this.apiData.put(currency, rate);
        }
    }


    /**
     * @return current date
     */
    @Override
    public String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    /**
     * @return get all the currencies name useful of the listing to choose
     * user can use from
     */
    @Override
    public Set<String> getAllCurrenciesNames() {
        return this.apiData.keySet();
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
        for (String i : keys) {
            currenciesWithRates.append(i).append(" ").append(this.apiData.get(i)).append("\n");
        }
        return currenciesWithRates.toString();
    }
}
