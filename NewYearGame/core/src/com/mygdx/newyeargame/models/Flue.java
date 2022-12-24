package com.mygdx.newyeargame.models;

import com.mygdx.newyeargame.screens.GameScreen;

public class Flue extends GameObject{
    boolean isGetPresent;
    public Flue(Home home) {
        super(home.getX() + home.getWidth()/2f,
                home.getY() + home.getHeight(),
                GameScreen.SCR_WIDTH / 15f,
                GameScreen.SCR_HEIGHT / 15f,
                true,
                home.getVx(),
                home.getVy());
    }

    @Override
    public void move(){
        this.setX(this.getX() + this.getVx());
        this.setY(this.getY() + this.getVy());

        if(this.getX() < 0 - this.getWidth()) this.setAlive(false);
    }

    public boolean isGetPresent() {
        return isGetPresent;
    }

    public void setGetPresent(boolean getPresent) {
        isGetPresent = getPresent;
    }
}
