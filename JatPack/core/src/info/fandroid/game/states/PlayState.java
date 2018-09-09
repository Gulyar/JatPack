package info.fandroid.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import info.fandroid.game.JetPack;
import info.fandroid.game.sprites.Bird;

public class PlayState extends State {

    private Bird bird;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        camera.setToOrtho(false, JetPack.WIDTH /2, JetPack.HEIGHT/2);
    }

    @Override
    protected void handleinput() {
    if(Gdx.input.justTouched())
        bird.jump();
    }

    @Override
    public void update(float dt) {
        handleinput();
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
    sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
    sb.end();
    }

    @Override
    public void dispose() {

    }
}
