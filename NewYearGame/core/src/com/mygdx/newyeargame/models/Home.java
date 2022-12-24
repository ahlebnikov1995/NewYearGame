package com.mygdx.newyeargame.models;

import com.mygdx.newyeargame.screens.GameScreen;

public class Home extends GameObject {
    private boolean haveFlue;

    public Home() {
        super(GameScreen.SCR_WIDTH,
                0,
                GameScreen.SCR_WIDTH/3f,
                GameScreen.SCR_HEIGHT/4f,
                true,
                -GameScreen.SCR_WIDTH/255f,
                0);
        haveFlue = false;
    }

    public boolean isHaveFlue() {
        return haveFlue;
    }

    public void setHaveFlue(boolean haveFlue) {
        this.haveFlue = haveFlue;
    }
}
