package com.sliceclient.console.api;

import com.sliceclient.console.SliceConsole;
import com.sliceclient.console.util.hardware.HardwareUtil;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * API for Slice auth
 * */
public class API {

    /**
     * Checks if a user is authenticated with the server
     * **/
    public static boolean sendAuthRequest() {
        try {
            URL url = new URL("https://api.sliceclient.com/checkAuth/" + HardwareUtil.getHardwareID());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.connect();
            String response = readResponse(connection);

            JSONObject json = null;
            if(response != null) {
                json = new JSONObject(response);
            }

            if(json != null) {
                boolean success = json.getBoolean("status");
                boolean admin = json.getBoolean("admin");

                return admin && success;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong while checking authentication");
            System.exit(-1);
        }
        return false;
    }

    /**
     * Reads the response from the server
     *
     * @param connection the connection to the server
     * **/
    public static String readResponse(HttpURLConnection connection) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception ignored) {}
        return null;
    }
}
