package com.chao;

import com.chao.oauth.UserManager;
import com.chao.views.pannel.MainFrame;
import com.chao.views.pannel.UserPanel;
import com.chao.views.player.PlayerMe;

import javax.swing.*;

/**
 * 主类，主函数类
 *
 * @author chaos
 */
public class MainActivity {

    /**
     * @param args
     */
    public static void main(String[] args) {
        MainFrame mainUI = MainFrame.getInstance();
        mainUI.setVisible(true);
        // 界面加载完后，加载数据
        MainFrame.init();

        UserManager.load(new UserManager.BackAction() {
            @Override
            public void success() {
                String username = UserManager.getUserBean().getUsername();
                PlayerMe.setUsername(username);
                PlayerMe.getInstance().setGrade(1201);


                UserPanel.setUserInfo(PlayerMe.getInstance(), UserPanel.LEFT);
                JOptionPane.showMessageDialog(MainFrame.getInstance(), username + "欢迎回来！当前分数：" + 1201 + "\n在线一分钟可获得1积分！", "自动登录成功",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void error() {
                UserPanel.setUserInfo(PlayerMe.getInstance(), UserPanel.LEFT);
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "自动登录失败！请检查网络后重试。", "自动登录",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
