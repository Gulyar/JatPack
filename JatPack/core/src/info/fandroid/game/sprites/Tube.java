package info.fandroid.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {

    public static final int TUBE_WIDTH = 52;
    public static final int FLUCTUATION = 130;//130
    public static final int TUBE_GAP = 150;
    public static final int LOWEST_OPENING = 120;

    private Texture truba, truba1;
    private Vector2 posTopTube, posBotTube, vidstan;
    private Random rand;
    private Rectangle boundsTop, boundsBot;



    public Tube(Texture truba, Texture truba1, Vector2 posTopTube, Vector2 postBotTube) {
        this.truba = truba;
        this.truba1 = truba1;
        this.posTopTube = posTopTube;
        this.posBotTube = postBotTube;
    }

    public Texture getTruba() {
        return truba;
    }

    public Texture getTruba1() {
        return truba1;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }


    public Tube(float x){
        truba = new Texture("truba.png");
        truba1 = new Texture("truba1.png");
        rand = new Random();
        vidstan = new Vector2(x+220,220);
        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - truba1.getHeight());

      boundsTop = new Rectangle(posTopTube.x,posTopTube.y,truba.getWidth(), truba.getHeight());
        boundsBot = new Rectangle(posBotTube.x,posBotTube.y,truba1.getWidth(), truba1.getHeight());


    }

     public void reposition(float x)
     {
         posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
         posBotTube.set(x, posTopTube.y - TUBE_GAP - truba1.getHeight());
         boundsTop.setPosition(posTopTube.x,posTopTube.y);
         boundsBot.setPosition(posBotTube.x, posBotTube.y);
     }

     public boolean collides (Rectangle player)
     {
         return player.overlaps(boundsTop) || player.overlaps(boundsBot);
     }

     public void dispose()
     {
         truba.dispose();
         truba1.dispose();
     }

}
