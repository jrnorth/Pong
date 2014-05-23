package north.joseph.pong.screens;

import north.joseph.pong.game.GameRenderer;
import north.joseph.pong.game.GameWorld;
import north.joseph.pong.helpers.InputHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen() {
        world = new GameWorld();
        renderer = new GameRenderer(world);
        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
		// TODO Auto-generated method stub

    }

    @Override
    public void show() {
		// TODO Auto-generated method stub

    }

    @Override
    public void hide() {
		// TODO Auto-generated method stub

    }

    @Override
    public void pause() {
		// TODO Auto-generated method stub

    }

    @Override
    public void resume() {
		// TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
		// TODO Auto-generated method stub

    }
}
