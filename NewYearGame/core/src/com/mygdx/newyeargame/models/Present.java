package com.mygdx.newyeargame.models;

import com.mygdx.newyeargame.screens.GameScreen;

public class Present extends GameObject{
    public Present(Santa santa) {
        super(  santa.getX() + santa.getWidth()/2f,
                santa.getY() - GameScreen.SCR_WIDTH/48f,
                GameScreen.SCR_WIDTH/24f,
                GameScreen.SCR_WIDTH/24f,
                true,
                -GameScreen.SCR_WIDTH/255f,
                -GameScreen.SCR_WIDTH/90f);
    }
}
