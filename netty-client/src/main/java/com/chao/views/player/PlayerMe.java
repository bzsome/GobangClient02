package com.chao.views.player;

public class PlayerMe extends Player {

    private static final Player player = new PlayerMe();

    private PlayerMe() {
    }

    public static Player getInstance() {
        return player;
    }
    public static void setUsername(String username){
        player.setName(username);
    }

}
