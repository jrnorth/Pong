package north.joseph.pong.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import north.joseph.pong.Pong;

public class DesktopLauncher {
	private final static int SCREEN_WIDTH = 480;
	private final static int SCREEN_HEIGHT = 270;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	    cfg.title = "Pong";
	    cfg.width = SCREEN_WIDTH;
	    cfg.height = SCREEN_HEIGHT;
	    new LwjglApplication(new Pong(), cfg);
	}
}
