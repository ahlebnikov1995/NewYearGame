package com.mygdx.newyeargame.models;

import com.mygdx.newyeargame.screens.GameScreen;

public class GameObject {
    private float x,y;
    private float width, height;
    private boolean isAlive;
    private float vx,vy;

    public GameObject(float x, float y, float width, float height, boolean isAlive, float vx, float vy) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isAlive = isAlive;
        this.vx = vx;
        this.vy = vy;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWeight(float weight) {
        this.width = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public void move(){
        x += vx;
        y += vy;

        if(x < 0 - width || x > GameScreen.SCR_WIDTH || y < 0 - height || y > GameScreen.SCR_HEIGHT){
            isAlive = false;
        }
    }
    public boolean overlaps(GameObject o){
        return ((x > o.x && x < o.x + o.width) || (o.x > x && o.x < x + width)) &&
                ((y > o.y && y < o.y + o.height) || (o.y > y && o.y < y + height));
    }

}
