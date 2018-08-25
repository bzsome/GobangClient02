package com.chao.control;

import com.chao.views.pannel.StatePanel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 倒计时
 */
public class Countown {
    public static int time = 30;

    public static void startTiming(int mTime) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                StatePanel.setTime(time--);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }
}
