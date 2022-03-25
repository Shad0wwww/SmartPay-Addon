package com.shadow.smartpay.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class getVersionControl {
    public static JsonObject getVestionControlJson() throws IOException {
        String sURL = "https://addonsmartpay.netlify.app/VersionControl.json";
        URL url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();
        StringWriter writer = new StringWriter();
        IOUtils.copy((InputStream)request.getContent(), writer, StandardCharsets.UTF_8.name());
        String json = writer.toString();
        JsonObject jsonObject = (JsonObject)(new Gson()).fromJson(json, JsonObject.class);
        return jsonObject;
    }
}
