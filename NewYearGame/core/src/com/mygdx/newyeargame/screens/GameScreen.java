package com.mygdx.newyeargame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.newyeargame.Main;
import com.mygdx.newyeargame.models.Flue;
import com.mygdx.newyeargame.models.Home;
import com.mygdx.newyeargame.models.Present;
import com.mygdx.newyeargame.models.Santa;

public class GameScreen implements Screen {
   public static final int SCR_WIDTH = Gdx.graphics.getWidth(), SCR_HEIGHT = Gdx.graphics.getHeight();
   Main game;
   long presentInterval = 1000;
   long timeToDefeat;

   SpriteBatch batch;
   BitmapFont font;
   OrthographicCamera camera;

   Texture imgSanta;
   Texture imgHome;
   Texture imgPresent;
   Texture imgFlue;
   Texture background;

   Array<Home> homes = new Array<>();
   Santa santa;
   Array<Flue> flues = new Array<>();
   Array<Present> presents = new Array<>();
   int score;

   private void loadResources(){
      imgSanta = new Texture("santa.png");
      imgHome = new Texture("house.png");
      imgPresent = new Texture("present.png");
      imgFlue = new Texture("flue.png");
      background = new Texture("background.png");

   }

   public GameScreen(Main game) {
      this.game = game;
      this.batch = new SpriteBatch();
      this.font = new BitmapFont();
      font.setColor(1,0,0,1);
      font.getData().scale(GameScreen.SCR_WIDTH/1500f);
      this.camera = new OrthographicCamera();
      camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
      this.santa = new Santa();
      this.score = 0;
      loadResources();
   }

   private void actions(){
      if(homes.size == 0){
         homes.add(new Home());
      }

      if(Gdx.input.isTouched() && TimeUtils.millis() - santa.getLastPresent() > presentInterval){
         presents.add(new Present(santa));
         santa.setLastPresent(TimeUtils.millis());
      }

      for (int i = 0; i < homes.size; i++) {
         homes.get(i).move();
         if(!homes.get(i).isHaveFlue()){
            flues.add(new Flue(homes.get(i)));
            homes.get(i).setHaveFlue(true);
         }
         if(!homes.get(i).isAlive()){
            homes.removeIndex(i);
         }
      }

      for (int i = 0; i < flues.size; i++) {
         for (int j = 0; j < presents.size; j++) {
            if(presents.get(j).overlaps(flues.get(i))){
               presents.get(i).setAlive(false);
               flues.get(i).setGetPresent(true);
               score++;
            }
         }
      }

      for (int i = 0; i < flues.size; i++) {
         flues.get(i).move();
         if(!flues.get(i).isAlive()){
            if(!flues.get(i).isGetPresent()){
               score = 0;
               timeToDefeat = TimeUtils.millis();
            }
            flues.removeIndex(i);
         }
      }

      for (int i = 0; i < presents.size; i++) {
         presents.get(i).move();
         if(!presents.get(i).isAlive()){
            presents.removeIndex(i);
         }
      }
   }

   @Override
   public void show() {

   }

   @Override
   public void render(float delta) {
      actions();

      camera.update();
      batch.setProjectionMatrix(camera.combined);
      batch.begin();

      batch.draw(background, 0,0,SCR_WIDTH,SCR_HEIGHT);

      if(TimeUtils.millis() - timeToDefeat < 1500 && TimeUtils.millis() > 1500){
         font.draw(batch, "HOME SKIPPED, SCORE RESET", SCR_WIDTH/2.5f, SCR_HEIGHT/1.5f);
      }

      batch.draw(imgSanta, santa.getX(),santa.getY(),santa.getWidth(),santa.getHeight());

      for (int i = 0; i < homes.size; i++) {
         batch.draw(imgHome, homes.get(i).getX(), homes.get(i).getY(),homes.get(i).getWidth(),homes.get(i).getHeight());
      }

      for (int i = 0; i < flues.size; i++) {
         batch.draw(imgFlue, flues.get(i).getX(), flues.get(i).getY(),flues.get(i).getWidth(),flues.get(i).getHeight());
      }

      for (int i = 0; i < presents.size; i++) {
         batch.draw(imgPresent, presents.get(i).getX(), presents.get(i).getY(),presents.get(i).getWidth(),presents.get(i).getHeight());
      }

      font.draw(batch, "SCORE: " + score, SCR_WIDTH - SCR_WIDTH/5f, SCR_HEIGHT - SCR_HEIGHT/50f);


      batch.end();
   }

   @Override
   public void resize(int width, int height) {

   }

   @Override
   public void pause() {

   }

   @Override
   public void resume() {

   }

   @Override
   public void hide() {

   }

   @Override
   public void dispose() {
      batch.dispose();
      font.dispose();
      imgFlue.dispose();
      imgHome.dispose();
      imgPresent.dispose();
      imgSanta.dispose();
      background.dispose();

   }
}
