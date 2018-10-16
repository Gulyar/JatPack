package info.fandroid.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import info.fandroid.game.JetPack;
import info.fandroid.game.sprites.Bird;
import info.fandroid.game.sprites.Tube;

public class PlayState extends State {
    public  static  final int TUBE_SPACING = 125;
    public  static  final int TUBE_COUNT = 4;


    private Bird bird;
    private Texture bg;
 //   private Tube tube;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        camera.setToOrtho(false, JetPack.WIDTH /2, JetPack.HEIGHT/2);
        bg = new Texture("gamefon.png");
       // tube = new Tube(100);

        tubes = new Array<Tube>();

        for(int i=0; i< TUBE_COUNT;i++)
        {

            tubes.add(new Tube(i*(TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

        System.out.println("ddd");
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
        camera.position.x =bird.getPosition().x;

        for (Tube tube: tubes) {
            if(camera.position.x - (camera.viewportWidth /2) > tube.getPosTopTube().x+tube.getTruba().getWidth())
            {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH +  TUBE_SPACING) * TUBE_COUNT));
            }
            if(tube.collides(bird.getBounds()))
                 gsm.set(new PlayState(gsm));
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTruba(), tube.getPosBotTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getTruba1(), tube.getPosBotTube().x, tube.getPosBotTube().y);

        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
