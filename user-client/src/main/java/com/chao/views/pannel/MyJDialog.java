package com.chao.views.pannel;

import com.chao.domain.MyMessage;
import com.chao.views.game.Algorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 自定义对话框集合
 */
public class MyJDialog {
    public static void sendToUser() {
        final JDialog jd = new JDialog();
        jd.setBounds(620, 380, 400, 300);
        jd.setTitle("向指定用户发送消息");
        jd.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        jd.add(new JLabel("接收用户"));
        final JTextField userField = new JTextField(32);
        jd.add(userField);
        jd.add(new JLabel("消息内容"));
        final JTextArea msgArea = new JTextArea(7, 32);
        jd.add(msgArea);

        final JButton jButton = new JButton("发送消息");
        jd.add(jButton);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                    jd.setVisible(false);
            }
        });
        jd.setModal(true);//确保弹出的窗口在其他窗口前面
        jd.setVisible(true);
    }

    public static void creatChat() {
        String name = JOptionPane.showInputDialog("请输入对方用户名，以建立连接", "");
        if (name == null) {
            return;
        }
        int i = JOptionPane.showConfirmDialog(null, "确定要退出系统吗？", "正在退出五子棋...",
                JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        JOptionPane.showMessageDialog(MainFrame.getInstance(),"正在等待对方回应，请稍后...", "建立连接", JOptionPane.INFORMATION_MESSAGE);

        Object[] options = {"", "中级", "高级"};
        int m = JOptionPane
                .showOptionDialog(MainFrame.getInstance(), "", "",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);
        if (m == 0) {
            Algorithm.LEVEL = Algorithm.LEVEL_Low;
            return;
        }

    }
}
