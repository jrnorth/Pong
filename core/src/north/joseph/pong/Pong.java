package north.joseph.pong;

import north.joseph.pong.helpers.AssetManager;
import north.joseph.pong.screens.MainMenuScreen;

import com.badlogic.gdx.Game;

public class Pong extends Game {

    @Override
    public void create() {
        AssetManager.load();
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void dispose() {
        AssetManager.dispose();
    }

    @Override
    public void render() {
        super.render();
    }
}
