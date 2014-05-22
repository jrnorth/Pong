package north.joseph.pong.screens;

import north.joseph.pong.game.GameWorld;
import north.joseph.pong.helpers.AssetManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class MainMenuScreen implements Screen {
    private final int BUTTON_WIDTH = 100;
    private final int BUTTON_HEIGHT = 30;
    private final int BUTTON_GAP = 10;

    private Game game;
    private Stage stage;
    private Label titleLabel;
    private LabelStyle titleLabelStyle;
    private TextButton playButton;
    private TextButton quitButton;
    private SpriteBatch batch;
    private ScalingViewport viewport;

    public MainMenuScreen(Game game) {
            this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {
        viewport = new ScalingViewport(Scaling.fill, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport);
        titleLabelStyle = new LabelStyle(AssetManager.font, Color.WHITE);
        titleLabel = new Label("Pong", titleLabelStyle);
        float x = (GameWorld.GAME_WIDTH - titleLabel.getWidth()) / 2;
        float y = GameWorld.TOTAL_HEIGHT - (GameWorld.TOTAL_HEIGHT / 4) - titleLabel.getHeight();
        titleLabel.setPosition(x, y);
        stage.addActor(titleLabel);
        batch = new SpriteBatch();
        playButton = new TextButton("Play", AssetManager.uiSkin);
        playButton.setWidth(BUTTON_WIDTH);
        playButton.setHeight(BUTTON_HEIGHT);
        playButton.setPosition((GameWorld.GAME_WIDTH - BUTTON_WIDTH) / 2, (GameWorld.TOTAL_HEIGHT - BUTTON_HEIGHT) / 2);
        playButton.addListener(new ClickListener() {
            @Override 
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new GameScreen());
            }
        });
        stage.addActor(playButton);
        quitButton = new TextButton("Quit", AssetManager.uiSkin);
        quitButton.setWidth(BUTTON_WIDTH);
        quitButton.setHeight(BUTTON_HEIGHT);
        quitButton.setPosition((GameWorld.GAME_WIDTH - BUTTON_WIDTH) / 2, playButton.getY() - BUTTON_HEIGHT - BUTTON_GAP);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        stage.addActor(quitButton);
        Gdx.input.setInputProcessor(stage);
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
            stage.dispose();
            batch.dispose();
    }

}
