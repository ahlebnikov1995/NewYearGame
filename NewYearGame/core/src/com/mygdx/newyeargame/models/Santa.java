package com.mygdx.newyeargame.models;

import com.mygdx.newyeargame.screens.GameScreen;

public class Santa extends GameObject{
    long lastPresent;
    public Santa() {
        super(  GameScreen.SCR_WIDTH/2f - GameScreen.SCR_WIDTH/16f,
                GameScreen.SCR_HEIGHT - GameScreen.SCR_WIDTH/10f,
                GameScreen.SCR_WIDTH / 6f,
                GameScreen.SCR_WIDTH / 12f,
                true,
                0,
                0);
    }

    public long getLastPresent() {
        return lastPresent;
    }

    public void setLastPresent(long lastPresent) {
        this.lastPresent = lastPresent;
    }
}
