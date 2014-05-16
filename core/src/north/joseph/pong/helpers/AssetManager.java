package north.joseph.pong.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetManager {
	public static BitmapFont font;
	public static BitmapFont shadow;
	public static BitmapFont scoreFont;
	public static BitmapFont scoreShadow;
	
	public static void load() {
		font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.setScale(.5f, .5f);
        shadow = new BitmapFont(Gdx.files.internal("shadow.fnt"));
        shadow.setScale(.5f, .5f);
        scoreFont = new BitmapFont(Gdx.files.internal("text.fnt"));
        scoreFont.setScale(.36f, .36f);
        scoreShadow = new BitmapFont(Gdx.files.internal("shadow.fnt"));
        scoreShadow.setScale(.36f, .36f);
	}
	
	public static void dispose() {
		font.dispose();
		shadow.dispose();
		scoreFont.dispose();
		scoreShadow.dispose();
	}
}
