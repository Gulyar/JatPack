package info.fandroid.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {

    public static final int FLUCTUATION = 130;
    public static final int TUBE_GAP = 100;
    public static final int LOWEST_OPENING = 120;

    private Texture truba, truba1;
    private Vector2 posTopTube, posBotTube;
    private Random rand;




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

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - truba1.getHeight());




    }
}
