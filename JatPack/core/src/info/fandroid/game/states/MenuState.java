package info.fandroid.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import info.fandroid.game.JetPack;

public class MenuState  extends State{

    private Texture background;
    private Texture playBtn;

    @Override
    protected void handleinput() {
    if(Gdx.input.justTouched()) {
        gsm.set(new PlayState(gsm));
    }
    }

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, JetPack.WIDTH /2, JetPack.HEIGHT/2);
        background = new Texture("bk.png");
        playBtn = new Texture("button.png");
    }

    @Override

    public void update(float dt) {
       handleinput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
    sb.begin();
    sb.draw(background, 10,10);
    sb.draw(playBtn, camera.position.x - playBtn.getWidth() /2 , JetPack.HEIGHT / 2, camera.position.y);
    sb.end();
    }

    @Override
    public void dispose() {
    background.dispose();
    playBtn.dispose();
        System.out.println("MenuState Disposed");
    }
}
