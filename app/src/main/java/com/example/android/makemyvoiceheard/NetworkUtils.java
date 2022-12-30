package com.example.android.makemyvoiceheard;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    final static String CIVICS_URL = "https://civicinfo.googleapis.com/civicinfo/v2/representatives?address=6703%20Canterbury%20Dr&includeOffices=true&levels=country&roles=legislatorUpperBody&roles=legislatorLowerBody&key=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    final static String CIVICS_URL_PART2 = "--header 'Accept: application/json' --compressed";
    final static String HTTP_STRING  = "https://civicinfo.googleapis.com/civicinfo/v2/representatives?address=6703%20Canterbury%20Dr%2C%20Frisco%2C%20TX%2075035&includeOffices=true&levels=country&roles=legislatorUpperBody&roles=legislatorLowerBody&key=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    final static String API_BASE_STRING  = "https://civicinfo.googleapis.com/civicinfo/v2/representatives?address=";
    final static String API_PARAMETERS_STRING  = "&includeOffices=true&levels=country&roles=legislatorUpperBody&roles=legislatorLowerBody&key=XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    final static String ACCEPT = " \n\n  Accept: application/json";
    private static boolean networkConnected;
    private static Boolean downloadDataSuccess;

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(String address) throws IOException {
        downloadDataSuccess = false;
        //String urlString = CIVICS_URL + CIVICS_URL_PART2;
        String urlString = API_BASE_STRING + address + API_PARAMETERS_STRING;
        Uri builtUri = Uri.parse(urlString).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        try {
            InputStream in = urlConnection.getInputStream();
            if (in == null) {
                networkConnected = false;
            }
            else {
                networkConnected = true;
            }

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } finally {
            urlConnection.disconnect();
        }
    }

    public static boolean getNetworkConnected() {
        return networkConnected;
    }
}
