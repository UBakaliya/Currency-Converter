/**
 * @author Uvaish Bakaliya
 * @apiNote This class the online getting hte data from the api.
 * @implNote If the data is not retrieve successfully then it will give error.
 * API Used: "https://cdn.moneyconvert.net/api/latest.json"
 */

package com.example.currency_exchanger;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class FetchCurrencyRatesAPI {
    private int respCode;

    // Given the api it will return the format the api is in:
    public String fetchAPI(String API_URL) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(API_URL).openConnection();
            conn.setRequestMethod("GET"); // since we are getting the data

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            // get all the data from the api
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        // If something goes wrong getting the api data then:
        return "Unsuccessful. !200.";
    }
}
