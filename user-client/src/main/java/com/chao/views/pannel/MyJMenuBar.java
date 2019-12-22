package com.chao.views.pannel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import com.chao.control.Countown;
import com.chao.oauth.UserManager;
import com.chao.oauth.UserAuthService;
import com.chao.oauth.impl.UserAuthImpl;
import com.chao.utils.Constants;
import com.chao.utils.MyIPTool;
import com.chao.views.game.Algorithm;
import com.chao.views.player.PlayerMe;

/**
 * 界面显示 之 菜单栏
 */
public class MyJMenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;
    private static final MyJMenuBar myJMenuBar = new MyJMenuBar();

    private static JMenuItem chechIP, userCenter, exit, about, version, setTime, setLevel, login,
            register, admin, word, userAlter, down;

    static {
        UIManager.put("Menu.font", new Font("宋体", Font.BOLD, 22));
        UIManager.put("MenuItem.font", new Font("宋体", Font.BOLD, 22));

        JMenu menu = new JMenu("菜单(F)");
        menu.setMnemonic('f'); // 助记符
        chechIP = new JMenuItem("查看本机IP");
        exit = new JMenuItem("退出");

        JMenu my = new JMenu("登录(M)");
        my.setMnemonic('m'); // 助记符
        login = new JMenuItem("登录账户");
        register = new JMenuItem("注册账户");
        admin = new JMenuItem("管理员后台");

        JMenu community = new JMenu("社区(W)");
        community.setMnemonic('m'); // 助记符
        userCenter = new JMenuItem("用户中心");
        userAlter = new JMenuItem("修改信息");
        down = new JMenuItem("下载中心");

        JMenu setting = new JMenu("设置(S)");
        setting.setMnemonic('s'); // 助记符
        setLevel = new JMenuItem("难度设置");
        setTime = new JMenuItem("倒计时设置");

        JMenu help = new JMenu("帮助(H)");
        help.setMnemonic('h'); // 助记符
        about = new JMenuItem("开发说明");
        version = new JMenuItem("软件版本");
        word = new JMenuItem("游戏说明");

        menu.add(chechIP);
        menu.add(exit);
        my.add(login);
        my.add(register);
        my.add(admin);
        community.add(userCenter);
        community.add(userAlter);
        community.add(down);

        setting.add(setLevel);
        setting.add(setTime);
        help.add(about);
        help.add(version);
        help.add(word);

        myJMenuBar.add(menu);
        myJMenuBar.add(my);
        myJMenuBar.add(community);
        myJMenuBar.add(setting);
        myJMenuBar.add(help);
        myJMenuBar.addListener();
    }

    private MyJMenuBar() {
    }

    public static MyJMenuBar getInstance() {
        return myJMenuBar;
    }

    public void addListener() {
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                MainFrame.close();
            }
        });
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String function = "在线五子棋";
                String person = "\n软件编码：陈光超 1501511668\n智能算法：袁志强 1501511668\n软件测试：芶 钰 1501511668\n";
                String info = function + person + "\n" + MainFrame.TIME + "\n"
                        + MainFrame.COPR;
                JOptionPane.showMessageDialog(null, info, "软件开发说明",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        version.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String info = " 软件版本" + MainFrame.VER + "\n 开发日期 " + MainFrame.TIME
                        + "\n" + MainFrame.COPR + "\n" + MainFrame.UPDA;
                JOptionPane.showMessageDialog(null, info, "软件开发说明",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        chechIP.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String localIP = "本机所有IP地址:";
                ArrayList<String> res = new ArrayList<String>();
                res = MyIPTool.getAllLocalHostIP();
                for (String ip : res) {
                    localIP += "\n" + ip;
                }
                JOptionPane.showMessageDialog(MainFrame.getInstance(), localIP, "查看本机IP",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        setTime.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                String input = JOptionPane.showInputDialog("请输入超时时间(秒)", "30");
                try {
                    int time = Integer.valueOf(input);
                    Countown.startTiming(time);
                } catch (Exception e) {
                }
            }
        });
        setLevel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                Object[] options = {"初级", "中级", "高级"};
                int m = JOptionPane
                        .showOptionDialog(MainFrame.getInstance(), "请选择AI智能程度", "请选择",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (m == 0) {
                    Algorithm.LEVEL = Algorithm.LEVEL_Low;
                    return;
                }
                if (m == 1) {
                    JOptionPane.showMessageDialog(MainFrame.getInstance(),
                            "算法设计,AI智能：袁志强", "重要提示..",
                            JOptionPane.INFORMATION_MESSAGE);
                    Algorithm.LEVEL = Algorithm.LEVEL_Middle;
                    return;
                }
                if (m == 2) {
                    JOptionPane.showMessageDialog(MainFrame.getInstance(),
                            "算法设计,AI智能：袁志强", "重要提示..",
                            JOptionPane.INFORMATION_MESSAGE);
                    Algorithm.LEVEL = Algorithm.LEVEL_Hight;
                    return;
                }
            }
        });

        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String name = JOptionPane.showInputDialog("正在登陆，请输入用户名", "");
                if (name == null) {
                    return;
                }
                String pass = JOptionPane.showInputDialog("正在登陆，请输入密码", "");
                if (pass == null) {
                    return;
                }
                new UserAuthImpl().login(name, pass, new UserAuthService.LoginBack() {
                    @Override
                    public void success() {
                        String username = UserManager.getUserBean().getUsername();
                        PlayerMe.setUsername(username);
                        PlayerMe.getInstance().setGrade(1201);

                        UserPanel.setUserInfo(PlayerMe.getInstance(), UserPanel.LEFT);
                        JOptionPane.showMessageDialog(MainFrame.getInstance(), username
                                        + "欢迎回来！当前分数：" + 1201 + "\n在线一分钟可获得1积分！", "登录成功",
                                JOptionPane.INFORMATION_MESSAGE);

                    }

                    @Override
                    public void error() {
                        JOptionPane.showMessageDialog(MainFrame.getInstance(), "登录失败！",
                                "登录失败", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                });

            }
        });
        register.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    java.net.URI uri = new java.net.URI(Constants.WEB_URL_register);
                    java.awt.Desktop.getDesktop().browse(uri);
                } catch (Exception e) {
                }
            }
        });
        admin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    java.net.URI uri = new java.net.URI(Constants.WEB_URL_admin);
                    java.awt.Desktop.getDesktop().browse(uri);
                } catch (Exception e) {
                }
            }
        });
        word.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane
                        .showMessageDialog(
                                MainFrame.getInstance(),
                                "游戏名称：在线五子棋\n主要功能：双人游戏,人机对战,在线对战\n提供登陆账号服务，可储存你的积分\n下次登陆则加载你的积分\n在线一分钟可获得一积分",
                                "游戏说明", JOptionPane.INFORMATION_MESSAGE);
            }

        });
        down.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    java.net.URI uri = new java.net.URI(Constants.WEB_URL_DOWNLOAD);
                    java.awt.Desktop.getDesktop().browse(uri);
                } catch (Exception e) {
                }
            }
        });
        userAlter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    java.net.URI uri = new java.net.URI(Constants.WEB_URL_userAlter);
                    java.awt.Desktop.getDesktop().browse(uri);
                } catch (Exception e) {
                }
            }
        });
        userCenter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    java.net.URI uri = new java.net.URI(Constants.WEB_URL_userCenter);
                    java.awt.Desktop.getDesktop().browse(uri);
                } catch (Exception e) {
                }
            }
        });
    }
}
