package com.chao.utils.etc;

import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpTools {
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public static String doPost(String url, Map<String, String> params) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = buildPostRequst(url, params);
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private static Request buildPostRequst(String url, Map<String, String> params) {
        Request request = null;
        if (params == null) {
            params = new HashMap<>();
        }

        if (params != null) {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : entries) {
                builder.add(entry.getKey(), entry.getValue());
            }
            request = new Request.Builder().url(url).post(builder.build()).build();
        }
        return request;
    }
}
