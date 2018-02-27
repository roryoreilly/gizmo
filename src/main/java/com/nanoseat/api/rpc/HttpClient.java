package com.nanoseat.api.rpc;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

class HttpClient {

    private final OkHttpClient client = new OkHttpClient();
    private final String host;
    private String apiKey;

    HttpClient(String host) {
        this.host = host;
    }

    HttpClient(String host, String apiKey) {
        this.host = host;
        this.apiKey = apiKey;
    }

    String post(String body) throws IOException {
        okhttp3.Request request;
        if (apiKey == null) {
            request = new okhttp3.Request.Builder()
                    .post(RequestBody.create(MediaType.parse("application/json"), body))
                    .url(host)
                    .build();
        } else {
            request = new okhttp3.Request.Builder()
                    .post(RequestBody.create(MediaType.parse("application/json"), body))
                    .url(host)
                    .header("Authorization", apiKey)
                    .build();
        }

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
