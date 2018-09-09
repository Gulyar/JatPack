package info.fandroid.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import info.fandroid.game.JetPack;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = JetPack.WIDTH;
		config.height = JetPack.HEIGHT;
		config.title = JetPack.title;
		new LwjglApplication(new JetPack(), config);
	}
}
