package com.chao.chatclient.message;

import com.chao.domain.MyMessage;
import com.chao.views.pannel.Chatroom;

import java.util.logging.Logger;


public class MessageManager {
    private final static Logger logger = Logger.getLogger(MessageManager.class.getName());

    /**
     * 显示Message信息
     *
     * @param myMessage
     */
    public static void handlerMessage(MyMessage myMessage) {
        String username = myMessage.getSend_user();
        String msg_text = myMessage.getMsg_text();
        Chatroom.addText(msg_text, username);
    }

}
