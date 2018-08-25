package com.chao.views.pannel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.chao.control.AutoChess;
import com.chao.oauth.UserManager;
import com.chao.views.game.GameCenter;
import com.chao.views.game.Spot;

/**
 * 主界面的控制按钮面板
 */
public class ControlPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final static ControlPanel controlPanel = new ControlPanel();
    private static JButton btShowChess, btAgain, btChessAI, btTest, btOnline, btnNetty;
    int i = 0;

    static {
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        controlPanel.setBackground(new Color(220, 220, 220, 220));
        btAgain = new JButton("开始/重新游戏");
        btnNetty = new JButton("网络对战(服务器)");
        btOnline = new JButton("在线对战(直连IP)");

        btChessAI = new JButton("智能AI下棋");
        btShowChess = new JButton("显示所有棋子");
        btTest = new JButton("备用测试按钮");

        controlPanel.add(btAgain);
        controlPanel.add(btOnline);
        controlPanel.add(btnNetty);
        controlPanel.add(btChessAI);
        controlPanel.add(btShowChess);
        controlPanel.add(btTest);
        controlPanel.addListener();
    }

    private ControlPanel() {
    }

    public static ControlPanel getInstance() {
        return controlPanel;
    }

    public static void init() {
        controlPanel.repaint();
    }

    /**
     * 添加监听事件
     */
    private void addListener() {

        btShowChess.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                GameCenter.showChess();
            }
        });

        btAgain.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String name = JOptionPane.showInputDialog("请输入用户名，以开始游戏", "");
                if (name == null) {
                    return;
                }

            }
        });

        btChessAI.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (GameCenter.isEnd()) {
                    JOptionPane.showMessageDialog(null, "游戏未开始，无法使用AI下棋",
                            "提示信息", JOptionPane.CANCEL_OPTION);
                    return;
                }
                new AutoChess();
            }
        });

        btTest.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                controlPanel.repaint();
                System.out.println("----------黑棋权值-------");
                GameCenter.showWeight(Spot.blackChess);

                System.out.println("----------白棋权值-------");
                GameCenter.showWeight(Spot.whiteChess);
            }
        });


        btOnline.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, "此版本仅支持网络对战，如需使用此功能请前往下载中心。",
                        "提示信息", JOptionPane.CANCEL_OPTION);
                return;
            }
        });

        btnNetty.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (UserManager.getToken() == null) {
                    JOptionPane.showMessageDialog(null, "你未登陆，请先登录后再操作！",
                            "提示信息", JOptionPane.CANCEL_OPTION);
                    return;
                }


            }
        });
    }
}
