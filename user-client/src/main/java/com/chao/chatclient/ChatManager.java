package com.chao.chatclient;

import com.chao.domain.UserToken;
import com.chao.oauth.UserManager;
import com.chao.utils.Constants;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.URI;

public class ChatManager {
    private final static Logger logger = LoggerFactory.getLogger(ChatManager.class);

    public static Channel channel;

    public static void startChat() {
        new Thread() {
            @Override
            public void run() {
                try {
                    URI uri = new java.net.URI(Constants.NETTY_PATH);
                    new ChatClient(uri.getHost(),  uri.getPort()).run();

                    logger.info("握手请求已建立！");
                    //验证用户身份
                    sendToken();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    //发送token口令，验证用户身份
    public static void sendToken() {
        logger.info("开始验证身份，准备发送口令信息,{}", UserManager.getToken());

        if (UserManager.getToken() == null) {
            logger.info("终止验证口令，用户口令为空！");
            return;
        }

        UserToken userToken = new UserToken();
        String token = UserManager.getToken();
        userToken.setToken(token);

        sendMessage(userToken);
    }

    public static void setChannel(Channel channel) {
        ChatManager.channel = channel;
    }

    public static void sendMessage(Object object) {
        if (channel == null) {
            logger.warn("无法发送消息，通道被关闭，{},{}", channel, channel.isOpen());
            return;
        }
        channel.writeAndFlush(object);
    }

    public static void main(String[] args) {
        startChat();
    }
}
