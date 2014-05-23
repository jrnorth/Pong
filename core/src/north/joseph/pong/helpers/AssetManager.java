package north.joseph.pong.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {

    public static BitmapFont font;
    public static BitmapFont scoreFont;
    public static Skin uiSkin;

    public static void load() {
        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.setScale(.5f, .5f);
        scoreFont = new BitmapFont(Gdx.files.internal("text.fnt"));
        scoreFont.setScale(.36f, .36f);
        uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
    }

    public static void dispose() {
        font.dispose();
        scoreFont.dispose();
    }
}
