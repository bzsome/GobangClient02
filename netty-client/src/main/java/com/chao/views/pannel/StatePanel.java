package com.chao.views.pannel;

import com.chao.views.game.Algorithm;
import com.chao.views.game.GameCenter;
import com.chao.views.game.Spot;
import com.chao.views.game.TableData;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 * 界面显示 之 游戏状态面板,显示游戏模式和出棋情况
 */
public class StatePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final StatePanel statePanel = new StatePanel();
    private static JLabel colorLabel, modeLabel, timeJLabel, levelLabel;

    static {
        modeLabel = new JLabel("显示游戏模式");
        levelLabel = new JLabel("难度级别:默认中级");
        timeJLabel = new JLabel("倒计时:暂未设置");
        colorLabel = new JLabel("玩家:黑棋先下");
        statePanel.setBackground(new Color(200, 200, 198));
        statePanel.setLayout(new GridLayout(0, 1));
        statePanel.add(modeLabel);
        statePanel.add(levelLabel);
        statePanel.add(timeJLabel);
        statePanel.add(colorLabel);
        // 重视，注释此代码，导致UserPanel内容无法显示，原因未知(原因是UserPanel 显示数据后未重新绘制）
        putInfo();
    }

    private StatePanel() {
    }

    public static StatePanel getInstance() {
        return statePanel;
    }

    /**
     * 使用线程动态更新状态面板
     */
    private static void putInfo() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                int color = TableData.getTheColor();
                if (color == Spot.blackChess) {
                    colorLabel.setText("上手白棋，请下黑棋");
                } else if (color == Spot.whiteChess) {
                    colorLabel.setText("上手黑棋，请下白棋");
                } else {
                    colorLabel.setText("玩家:黑棋先下");
                }

                switch (GameCenter.getMode()) {
                    case GameCenter.MODE_END:
                        modeLabel.setText("游戏模式:未开始游戏");
                        break;
                    case GameCenter.MODE_COUPE:
                        modeLabel.setText("游戏模式:双人对战");
                        break;
                    case GameCenter.MODE_ROBOT:
                        modeLabel.setText("游戏模式:人机对战");
                        break;
                    case GameCenter.MODE_ONLINE:
                        modeLabel.setText("游戏模式:在线对战");
                        break;
                    default:
                        break;
                }
                switch (Algorithm.LEVEL) {
                    case Algorithm.LEVEL_Low:
                        levelLabel.setText("游戏难度:初级难度");
                        break;
                    case Algorithm.LEVEL_Middle:
                        levelLabel.setText("游戏难度:中级难度");
                        break;
                    case Algorithm.LEVEL_Hight:
                        levelLabel.setText("游戏难度:高级难度");
                        break;
                    default:
                        break;
                }
            }
        };
        java.util.Timer timer = new Timer();
        timer.schedule(timerTask, 0, 200);
    }

    public static void setTime(int mTime) {
        timeJLabel.setText("剩余时间:" + mTime + "秒");
    }
}
