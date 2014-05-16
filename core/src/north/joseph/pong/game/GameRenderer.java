package north.joseph.pong.game;

import north.joseph.pong.gameobjects.Ball;
import north.joseph.pong.gameobjects.Paddle;
import north.joseph.pong.helpers.AssetManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameRenderer {
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batch;
	
	private GameWorld world;
	private Ball ball;
	private Paddle leftPaddle;
	private Paddle rightPaddle;
	
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
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        // Draw ball. Need to add BALL_RADIUS to the ball's x and y coordinates
        // because I track the ball's coordinates by its lower left-hand corner.
        // This ensures it gets drawn correctly.
        shapeRenderer.circle(ball.getX() + Ball.BALL_RADIUS, ball.getY() + Ball.BALL_RADIUS, Ball.BALL_RADIUS);
        // Draw left paddle.
        shapeRenderer.rect(leftPaddle.getX(), leftPaddle.getY(), Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT);
        // Draw right paddle.
        shapeRenderer.rect(rightPaddle.getX(), rightPaddle.getY(), Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT);
        shapeRenderer.rect(0, 0, GameWorld.GAME_WIDTH, GameWorld.GAME_HEIGHT);
        shapeRenderer.end();
        
        batch.begin();
        // Draw scores
        String playerScore = "" + world.getPlayerScore();
        AssetManager.scoreShadow.draw(batch, playerScore, 105, 266);
        AssetManager.scoreFont.draw(batch, playerScore, 106, 265);
        String computerScore = "" + world.getComputerScore();
        AssetManager.scoreShadow.draw(batch, computerScore, GameWorld.GAME_WIDTH - 106, 266);
        AssetManager.scoreFont.draw(batch, computerScore, GameWorld.GAME_WIDTH - 105, 265);
        
        if (world.isReady()) {
        	// Draw shadow first.
            AssetManager.shadow.draw(batch, "Touch to begin", 105, 149);
            // Draw text.
            AssetManager.font.draw(batch, "Touch to begin", 106, 150);
        } else if (world.isBallOutOfBounds()) {
        	
        } else if (world.isFinished()) {
        	
        }
        batch.end();
	}
}
