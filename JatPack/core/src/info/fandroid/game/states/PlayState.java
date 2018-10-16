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
    public static final int GROUND_Y_OFFSET  = -30;

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
 //   private Tube tube;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        camera.setToOrtho(false, JetPack.WIDTH /2, JetPack.HEIGHT/2);
        bg = new Texture("gamefon.png");
        ground = new Texture("ground.png");
       // tube = new Tube(100);
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos1 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
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
        updateGround();
        bird.update(dt);
        camera.position.x =bird.getPosition().x;

        for (int i =0; i< tubes.size; i++) {
            
            Tube tube = tubes.get(i);

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

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube :tubes)
            tube.dispose();
        System.out.println("PlayState Disposed");
    }

    private void updateGround()
    {
        if(camera.position.x - (camera.viewportWidth /2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if(camera.position.x - (camera.viewportWidth /2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);

    }
}
