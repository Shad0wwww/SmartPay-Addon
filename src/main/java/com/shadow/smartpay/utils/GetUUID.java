package com.shadow.smartpay.utils;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetUUID {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static String GET_URL;

    public GetUUID() {
    }

    public static String getUUID(String player) {
        GET_URL = "https://api.mojang.com/users/profiles/minecraft/" + player;
        String uuid = null;

        try {
            uuid = sendGET();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return uuid;
    }

    private static String sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            return null;
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();

            String inputLine;
            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            JsonObject jsonObject = (new JsonParser()).parse(response.toString()).getAsJsonObject();
            String uuid = jsonObject.get("id").toString();
            uuid = uuid.substring(1);
            uuid = uuid.substring(0, uuid.length() - 1);
            return uuid;
        }
    }
}
