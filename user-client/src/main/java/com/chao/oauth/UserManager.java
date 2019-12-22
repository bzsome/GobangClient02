package com.chao.oauth;

import com.chao.domain.UserBean;
import com.chao.oauth.impl.UserAuthImpl;
import com.chao.views.player.PlayerMe;

import java.util.logging.Logger;

import java.io.*;
import java.util.Properties;

public class UserManager {

    private final static Logger logger = Logger.getLogger(UserManager.class.getName());
    //储存当前登录用户
    private static UserBean userBean;

    //储存当前用户token口令
    private static String token;

    public static Properties properties = new Properties();

    /**
     * 程序启动时，加载用户数据
     */
    public static void load(final BackAction backAction) {
        token = getTokenByFile();
        if (token == null || "null".equals(token)) {
            logger.info("用户口令为空，终止校验：{}");
            return;
        }
        new UserAuthImpl().getUserByToken(token, new UserAuthService.LoginBack() {
            @Override
            public void success() {
                logger.info("通过token口令获得用户信息：{}");
                backAction.success();
            }

            @Override
            public void error() {

            }
        });
    }

    public static UserBean getUserBean() {
        return userBean;
    }

    public static void setUserBean(UserBean userBean) {
        UserManager.userBean = userBean;
    }

    public static String getToken() {
        if (token == null) {
            token = getTokenByFile();
        }
        return token;
    }

    public static void setToken(String token) {
        if (token == null || "null".equals(token)) {
            logger.info("用户口令为空！禁止保存！");
            return;
        }
        saveTokenByFile(token);
        UserManager.token = token;

    }

    /**
     * 保存token至文件中
     *
     * @param value
     */
    public static void saveTokenByFile(String value) {
        if (value == null || "null".equals(value)) {
            logger.info("用户口令为空！禁止保存！");
            return;
        }
        try {
            OutputStream output = new FileOutputStream(new File("user.dat"));
            properties.setProperty("token", value);
            properties.store(output, "token");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 从文件中读取token
     *
     * @return
     */
    public static String getTokenByFile() {
        try {
            InputStream input = new FileInputStream(new File("user.dat"));
            properties.load(input);
            String token = properties.getProperty("token");
            if (token == null || "null".equals(token)) {
                return null;
            }
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface BackAction {
        void success();

        void error();
    }

}

