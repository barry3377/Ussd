package com.ussd.app.Ussd.OrangeSMS;


import com.squareup.okhttp.*;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class OrangeSMS {

    public  OkHttpClient client;

    public OrangeSMS() {
        client = new OkHttpClient();
    }

    public String getToken() {

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials");
        Request request = new Request.Builder()
                .url("https://api.orange.com/oauth/v3/token")
                .method("POST", body)
                .addHeader("Authorization", "Basic OGFmc2RuME9PNjVKY2FFZXZxcW1jMFE4a0taSDhpQ3c6dW5aZ1U0VVNqYmNlMmk0Mg==")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();

            String jsonData = response.body().string();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonData);
            JSONObject myjson = new JSONObject(jsonObject);

            return myjson.get("access_token").toString();
        } catch (Exception e) {
            return "";
        }

    }

    public boolean sendMessage(String telephone, String message) {

        String token = getToken();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"outboundSMSMessageRequest\":{\r\n    " +
                "    \"address\": \"tel:"+telephone+"\",\r\n        \"senderAddress\":\"tel:+224627044179\",\r\n     " +
                "   \"outboundSMSTextMessage\":{\r\n            \"message\":\""+message+"\"\r\n        }\r\n    }\r\n}");
        System.out.println(telephone);
        Request request = new Request.Builder()
                .url("https://api.orange.com/smsmessaging/v1/outbound/tel:+224627044179/requests")
                .method("POST", body)
                .addHeader("Authorization", "Bearer "+token)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
