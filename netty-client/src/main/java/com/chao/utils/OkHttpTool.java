package com.chao.utils;

import okhttp3.*;

public class OkHttpTool {
    public static void getUser(String token, Callback callback) {
        final Request.Builder builder = new Request.Builder().url(Constants.OAUTH_PATH + "/token/check");
        builder.addHeader(Constants.AUTHORIZATION, token);  //将请求头以键值对形式添加，可添加多个请求头
        final Request request = builder.build();
        final OkHttpClient client = new OkHttpClient.Builder().build();

        client.newCall(request).enqueue(callback);
    }

    public static void login(String username, String password, Callback callback) {
        final Request.Builder builder = new Request.Builder().url(Constants.OAUTH_PATH + "/user/login");
        //Form表单格式的参数传递
        FormBody formBody = new FormBody
                .Builder()
                .add("username", username)
                .add("password", password)
                .build();

        final Request request = builder.post(formBody).build();
        final OkHttpClient client = new OkHttpClient.Builder().build();
        client.newCall(request).enqueue(callback);
    }
}
