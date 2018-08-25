package com.chao.views.pannel;

import com.chao.utils.RecordTool;
import com.chao.views.game.GameCenter;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

/**
 * 主界面UI 版本0.3 2017.06.23
 *
 * @author chaos
 */
public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final MainFrame mainFrame = new MainFrame();

    public static final String VER = "v2.0.0  正式版";
    public static final String TIME = "2018.08.26.23(正式版)";
    public static final String COPR = "自主设计开发  拥有本软件所有版权";
    public static final String UPDA = "在线对战可使用智能AI";
    // 窗体大小
    private static int FRAME_WIDTH = 860;
    private static int FRAME_HIGH = 800;

    private MainFrame() {
    }

    public static MainFrame getInstance() {
        return mainFrame;
    }

    static {

        UIManager.put("Label.font", new Font("宋体", Font.BOLD, 15));
        UIManager.put("Button.font", new Font("宋体", 0, 20));

        mainFrame.setTitle(" 在线五子棋  " + TIME);
        mainFrame.setSize(FRAME_WIDTH, FRAME_HIGH);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);

        int sWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int sHight = Toolkit.getDefaultToolkit().getScreenSize().height;
        mainFrame.setLocation((sWidth - FRAME_WIDTH) / 2, (sHight - FRAME_HIGH) / 2);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            java.net.URL imgURL = mainFrame.getClass().getResource("/raw/goBang.png");
            ImageIcon imgIcon = new ImageIcon(imgURL);
            mainFrame.setIconImage(imgIcon.getImage());
        } catch (Exception e) {
            System.out.println("图标设置失败！");
        }

        mainFrame.addWidget();
    }

    /**
     * 添加控件和菜单栏
     */
    private void addWidget() {

        //xy坐标偏移量
        int offset_x = 5;
        int offset_y = 3;

        //棋盘面板大小
        int broadSize = 19 * 30;

        mainFrame.setJMenuBar(MyJMenuBar.getInstance());
        int jHeight = 80;//菜单栏有高度,需要减去此高度

        getContentPane().add(UserPanel.getInstance());
        UserPanel.getInstance().setBounds(offset_x, offset_y, broadSize, FRAME_HIGH - broadSize - jHeight);

        getContentPane().add(ChessBroad.getInstance());
        ChessBroad.getInstance().setBounds(offset_x, FRAME_HIGH - broadSize - jHeight + offset_y * 2, broadSize, broadSize);

        getContentPane().add(StatePanel.getInstance());
        StatePanel.getInstance().setBounds(offset_x + broadSize, offset_y, FRAME_WIDTH - broadSize, FRAME_HIGH - broadSize - jHeight);

        getContentPane().add(Chatroom.getInstance());
        Chatroom.getInstance().setBounds(offset_x + broadSize, FRAME_HIGH - broadSize - jHeight + offset_y, FRAME_WIDTH - broadSize + offset_x * 2, 320);

        getContentPane().add(ControlPanel.getInstance());
        ControlPanel.getInstance().setBounds(offset_x + broadSize, FRAME_HIGH - broadSize + 240, FRAME_WIDTH - broadSize + offset_x, 500);

    }

    // 界面显示，控件加载完毕后执行(向控件加载数据等)
    public static void init() {
        GameCenter.reStart();

        ChessBroad.init();
        UserPanel.init();
        ControlPanel.init();
        mainFrame.repaint();
        new RecordTool();
    }

    // 窗口关闭事件
    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            close();
            return; // 直接返回，阻止默认动作，阻止窗口关闭
        }
        super.processWindowEvent(e); // 该语句会执行窗口事件的默认动作(如：隐藏)
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ControlPanel.init();
    }

    public static void close() {
        int i = JOptionPane.showConfirmDialog(null, "确定要退出系统吗？", "正在退出五子棋...",
                JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}