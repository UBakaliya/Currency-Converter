package com.example.Currency_Exchanger;

import java.util.Set;

// This class the interface for the function that will be useful for
// retrieving the information from the api.
public interface APIData {
    // This will give use the most recent date for the person
    // using the app, the api date is bit off, thus we will
    // get date base on the persons' location.
    public String getCurrentDate();

    // It will return all the currencies names are in the api
    public Set<String> getAllCurrenciesNames();

    // This will return the rate of the given country currency
    // NOTE: This will only the rate on the conversions. i.e. 3.3%
    public double getRateForGivenCurrency(String currency);

    // This will return 'all' the currencies with their rates
    public String getCurrenciesNamesWithRates();
}
