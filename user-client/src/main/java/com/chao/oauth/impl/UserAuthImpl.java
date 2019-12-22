package com.chao.oauth.impl;

import com.chao.domain.ResponseMessage;
import com.chao.domain.UserBean;
import com.google.gson.Gson;
import com.chao.oauth.UserAuthService;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class UserAuthImpl implements UserAuthService {

    private final static Logger logger = Logger.getLogger("userService");

    @Override
    public void login(final String account, String password, final LoginBack loginBack) {
        logger.info("用户登录：{},{}");

    }

    @Override
    public void getUserByToken(String token, final LoginBack loginBack) {
        logger.info("用户身份验证：{}");

    }

    @Override
    public int addGrade(int grade) {
        return 99;
    }


    public UserBean getByMeseage(ResponseMessage responseMessage) throws IOException {
        if (responseMessage != null && responseMessage.getCode() == 200) {
            Map<String, Object> map = responseMessage.getExtend();
            Object object = map.get("user");
            return new Gson().fromJson(new Gson().toJson(object), UserBean.class);
        }
        return null;
    }


}
