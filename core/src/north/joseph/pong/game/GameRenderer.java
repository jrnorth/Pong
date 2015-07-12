package north.joseph.pong.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import north.joseph.pong.gameobjects.Ball;
import north.joseph.pong.gameobjects.Paddle;
import north.joseph.pong.helpers.AssetManager;

public class GameRenderer {

    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private GlyphLayout glyphLayout;

    private GameWorld world;
    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;

    private String touchToBegin = "Touch to begin";

    public GameRenderer(GameWorld world) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameWorld.GAME_WIDTH, GameWorld.TOTAL_HEIGHT);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        this.world = world;
        ball = world.getBall();
        leftPaddle = world.getLeftPaddle();
        rightPaddle = world.getRightPaddle();

        glyphLayout = new GlyphLayout();
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        // Draw ball. Need to add BALL_RADIUS to the ball's x and y coordinates
        // because I track the ball's coordinates by its lower left-hand corner.
        // This ensures it gets drawn correctly.
        shapeRenderer.circle(ball.getX() + Ball.BALL_RADIUS, ball.getY() + Ball.BALL_RADIUS, Ball.BALL_RADIUS);
        // Draw left paddle.
        shapeRenderer.rect(leftPaddle.getX(), leftPaddle.getY(), Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT);
        // Draw right paddle.
        shapeRenderer.rect(rightPaddle.getX(), rightPaddle.getY(), Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.rect(0, 0, GameWorld.GAME_WIDTH, GameWorld.GAME_HEIGHT);
        shapeRenderer.end();

        batch.begin();
        // Draw scores
        String playerScore = "" + world.getPlayerScore();
        glyphLayout.setText(AssetManager.scoreFont, playerScore);
        float scoreWidth = glyphLayout.width;
        AssetManager.scoreFont.draw(batch, playerScore, GameWorld.GAME_WIDTH / 4 - scoreWidth / 2, 265);
        String computerScore = "" + world.getComputerScore();
        glyphLayout.setText(AssetManager.scoreFont, computerScore);
        scoreWidth = glyphLayout.width;
        AssetManager.scoreFont.draw(batch, computerScore, 3 * GameWorld.GAME_WIDTH / 4 - scoreWidth / 2, 265);

        if (world.isReady()) {
            glyphLayout.setText(AssetManager.font, touchToBegin);
            float messageWidth = glyphLayout.width;
            float messageHeight = glyphLayout.height;
            // Draw text.
            AssetManager.font.draw(batch, touchToBegin, (GameWorld.GAME_WIDTH - messageWidth) / 2, (GameWorld.GAME_HEIGHT + messageHeight) / 2);
        } else if (world.isBallOutOfBounds()) {
            String message = world.didPlayerWin() ? "Player wins the point" : "Computer wins the point";
            glyphLayout.setText(AssetManager.font, message);
            float messageWidth = glyphLayout.width;
            float messageHeight = glyphLayout.height;
            AssetManager.font.draw(batch, message, (GameWorld.GAME_WIDTH - messageWidth) / 2, (GameWorld.GAME_HEIGHT + messageHeight) / 2);
        } else if (world.isFinished()) {
            String message = world.didPlayerWin() ? "Player wins" : "Computer wins";
            glyphLayout.setText(AssetManager.font, message);
            float messageWidth = glyphLayout.width;
            float messageHeight = glyphLayout.height;
            AssetManager.font.draw(batch, message, (GameWorld.GAME_WIDTH - messageWidth) / 2, (GameWorld.GAME_HEIGHT + messageHeight) / 2);
        }
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }
}
