package com.chao.views.pannel;

import com.chao.views.game.Algorithm;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Chatroom extends JPanel {
    private static final long serialVersionUID = 1L;
    private final static Chatroom chatroom = new Chatroom();

    private static JTextArea jArea;
    private static JTextField jText;
    private static JButton btLine,btClear, btSend, btSendToUSer;

    static {
        chatroom.setLayout(new FlowLayout(FlowLayout.CENTER));
        jArea = new JTextArea(12, 32);
        jText = new JTextField(20);
        btLine = new JButton("连接");
        btClear = new JButton("清空");
        btSend = new JButton("发送");
        btSendToUSer = new JButton("向指定用户发送消息");
        jArea.setEnabled(false);
        // 自动换行
        jArea.setLineWrap(true);
        jArea.setText("聊天室内容：");
        jArea.setFont(new Font("宋体", Font.BOLD, 14));
        JScrollPane jsp = new JScrollPane(jArea);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        chatroom.add(jsp);
        chatroom.add(jText);
        chatroom.add(btLine);
        chatroom.add(btClear);
        chatroom.add(btSend);
        chatroom.add(btSendToUSer);
        chatroom.addListener();
    }

    private Chatroom() {
    }

    public static Chatroom getInstance() {
        return chatroom;
    }

    public void addListener() {
        btClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                jArea.setText("聊天室内容：");
            }
        });
        btSend.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                MyJDialog.sendToUser();
            }
        });
        btLine.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        btSendToUSer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                MyJDialog.sendToUser();
            }
        });
    }

    public static void addText(String text, String username) {
        String msg_text = username + ";" + text;
        String str = jArea.getText();

        jArea.setText(str + "\n" + msg_text);
    }
}
