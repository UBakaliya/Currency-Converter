package com.example.currency_exchanger;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTests {
    final ManipulateAPIData API_DATA_TEST = new ManipulateAPIData();

    @Test
    void apiDataMapSizeTest() {
        assertEquals(191, API_DATA_TEST.apiDataMapSize());
    }

    @Test
    void getCurrentDateTest() {
        assertEquals(String.valueOf(java.time.LocalDate.now()), API_DATA_TEST.getCurrentDate());
    }

    /**
     * NOTE: If you are running the test and if it falls then the data is used for
     * the time is build then it will function as that then.
     * -----------------------------------------
     *
     * @@@ Data USED @@@
     * {"table": "latest", "rates":
     * {"AED": 3.67305, "AFN": 88.847952, "ALL": 108.733622, "AMD": 392.470897, "ANG": 1.801238,
     * "AOA": 506.5, "ARS": 195.197485, "AUD": 1.486989, "AWG": 1.8, "AZN": 1.7, "BAM": 1.848537,
     * "BBD": 2, "BDT": 107.008358, "BGN": 1.848189, "BHD": 0.375703, "BIF": 2064, "BMD": 1,
     * "BND": 1.345331, "BOB": 6.906051, "BRL": 5.2104, "BSD": 1, "BTC": 4.3200846e-05, "BTN": 82.705765,
     * "BTS": 132.38018913, "BWP": 13.314087, "BYN": 2.522601, "BZD": 2.014548, "CAD": 1.36375, "CDF": 2078.816578,
     * "CHF": 0.940359, "CLF": 0.029827, "CLP": 823.03, "CNH": 6.9797, "CNY": 6.9561, "COP": 4855.32095,
     * "CRC": 561.187484, "CUC": 1, "CUP": 25.75, "CVE": 104.1661, "CZK": 22.4197, "DASH": 0.01444774,
     * "DJF": 177.95096, "DKK": 7.0589, "DOGE": 12.43781095, "DOP": 55.752686, "DZD": 136.789, "EGP": 30.63, "ERN": 15,
     * "ETB": 53.75297, "ETH": 0.0006280263, "EUR": 0.94585, "FJD": 2.2193, "FKP": 0.837065, "GBP": 0.837065, "GEL": 2.635,
     * "GGP": 0.837065, "GHS": 12.867602, "GIP": 0.837065, "GMD": 61.1, "GNF": 8602.151131, "GTQ": 7.805489, "GYD": 210.882825,
     * "HKD": 7.84955, "HNL": 24.64031, "HRK": 7.14462, "HTG": 149.80343, "HUF": 360.435, "IDR": 15265.15, "ILS": 3.66777,
     * "IMP": 0.837065, "INR": 82.93255, "IQD": 1458.652145, "IRR": 42325, "ISK": 144.78, "JEP": 0.837065, "JMD": 154.175973,
     * "JOD": 0.7094, "JPY": 136.485, "KES": 126.527045, "KGS": 87.42, "KHR": 4043.617407, "KMF": 466.900353, "KPW": 900,
     * "KRW": 1314.8, "KWD": 0.30704, "KYD": 0.832837, "KZT": 448.840628, "LAK": 16854.118783, "LBP": 15001.483609,
     * "LD": 320, "LKR": 364.789778, "LRD": 153.775887, "LSL": 18.404421, "LTC": 0.0107290381, "LYD": 4.819611, "MAD": 10.407633,
     * "MDL": 18.811217, "MGA": 4299.634949, "MKD": 58.206975, "MMK": 2098.79391, "MNT": 3406.965265, "MOP": 8.079165,
     * "MRO": 356.999828, "MRU": 36.328941, "MUR": 46.332182, "MVR": 15.35, "MWK": 1025.844084, "MXN": 18.3874, "MYR": 4.4355,
     * "MZN": 63.899991, "NAD": 18.41, "NGN": 459.73264, "NIO": 36.518823, "NOK": 10.439805, "NPR": 132.329158, "NXT": 369.059921212,
     * "NZD": 1.625488, "OMR": 0.383657, "PAB": 1, "PEN": 3.802088, "PGK": 3.523027, "PHP": 55.232998, "PKR": 260.175, "PLN": 4.475718,
     * "PYG": 7308.880698, "QAR": 3.647726, "RON": 4.6642, "RSD": 111.235, "RUB": 76.016735, "RWF": 1088.47097, "SAR": 3.749361,
     * "SBD": 8.245284, "SCR": 13.048, "SDG": 588, "SEK": 10.50518, "SGD": 1.3499, "SHP": 0.837065, "SLL": 17665, "SOS": 568.136542,
     * "SRD": 33.661, "SSP": 130.26, "STD": 22823.990504, "STN": 23.144868, "STR": 11.3166487642, "SVC": 8.744982, "SYP": 2512.53,
     * "SZL": 18.413428, "THB": 34.796656, "TJS": 10.013467, "TMT": 3.51, "TND": 3.13, "TOP": 2.35285, "TRY": 18.874401, "TTD": 6.783714,
     * "TWD": 30.6718, "TZS": 2337.649375, "UAH": 36.730245, "UGX": 3717.821421, "USD": 1, "UYU": 38.874622, "UZS": 11343.605689,
     * "VEF_BLKMKT": 10.4, "VEF_DICOM": 8.82, "VEF_DIPRO": 529.62, "VES": 24.38578, "VND": 23787.5, "VUV": 118.044, "WST": 2.69755,
     * "XAF": 620.436995, "XAG": 0.0481661, "XAU": 0.00055216, "XCD": 2.70255, "XDR": 0.751122, "XMR": 0.0080009049,
     * "XOF": 620.436995, "XPD": 0.00071967, "XPF": 112.869941, "XPT": 0.00109385, "XRP": 2.7679494091, "YER": 250.350023,
     * "ZAR": 18.4233, "ZMW": 19.713665, "ZWL": 322, "NMC": 0.6583052534, "PPC": 1.2681920972, "NVC": 4.1334737071,
     * "XPM": 4.8789734625, "EAC": 1463.0791964769, "VTC": 2.1090018835, "EMC": 15.3254221404, "FCT": 0.741747612},
     * "lastupdate": "2023-02-25T23:45:24.820000+00:00"}
     * -----------------------------------------
     */
    @Test
    void getAllCurrenciesNamesTest() {
        SortedSet<Object> validCurrencies = new TreeSet<>();
        assertEquals(validCurrencies, API_DATA_TEST.getAllCurrenciesNames());
    }

    @Test
    void getRateForGivenCurrencyTest() {

    }

    @Test
    void getCurrenciesNamesWithRatesTest() {

    }

}