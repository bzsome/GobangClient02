package com.chao.oauth.impl;

import com.chao.chatclient.ChatClientHandler;
import com.chao.domain.ResponseMessage;
import com.chao.domain.UserBean;
import com.chao.oauth.UserManager;
import com.chao.utils.OkHttpTool;
import com.google.gson.Gson;
import com.chao.oauth.UserAuthService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class UserAuthImpl implements UserAuthService {

    private final static Logger logger = LoggerFactory.getLogger(UserAuthImpl.class);

    @Override
    public void login(final String account, String password, final LoginBack loginBack) {
        logger.info("用户登录：{},{}", account, password);
        OkHttpTool.login(account, password, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                loginBack.error();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    handlerUser(response, loginBack);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getUserByToken(String token, final LoginBack loginBack) {
        logger.info("用户身份验证：{}", token);
        OkHttpTool.getUser(token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                loginBack.error();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    handlerUser(response, loginBack);
            }
        });
    }

    @Override
    public int addGrade(int grade) {
        return 99;
    }

    public ResponseMessage getByResponse(Response response) throws IOException {
        String json = response.body().string();
        logger.info("返回json信息：{}", json);
        return new Gson().fromJson(json, ResponseMessage.class);
    }

    public UserBean getByMeseage(ResponseMessage responseMessage) throws IOException {
        if (responseMessage != null && responseMessage.getCode() == 200) {
            Map<String, Object> map = responseMessage.getExtend();
            Object object = map.get("user");
            return new Gson().fromJson(new Gson().toJson(object), UserBean.class);
        }
        return null;
    }

    public void handlerUser(Response response, LoginBack loginBack) throws IOException {
        ResponseMessage responseMessage = getByResponse(response);
        UserBean userBean = getByMeseage(responseMessage);
        //服务器未返回用户信息，则表示登录失败
        if (userBean == null) {
            loginBack.error();
            return;
        }
        //需要判断userBean是否为空才获得token
        Object objToken = responseMessage.getExtend().get("token");
        UserManager.setToken(String.valueOf(objToken));
        UserManager.setUserBean(userBean);
        loginBack.success();
    }
}
