package north.joseph.pong.game;

import north.joseph.pong.gameobjects.Ball;
import north.joseph.pong.gameobjects.Paddle;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class GameWorld {
	// Constants
	public static final int TOTAL_HEIGHT = 270;
	public static final int GAME_WIDTH = 480;
	public static final int GAME_HEIGHT = 240;
	public static final int LEFT_PADDLE_OFFSET = 30;
	public static final int RIGHT_PADDLE_OFFSET = 40;
	
	// Objects
	private static Paddle leftPaddle;
	private static Paddle rightPaddle;
	private Ball ball;
	private Circle ballBoundingCircle;
	private Rectangle leftBoundingRectangle;
	private Rectangle rightBoundingRectangle;
	
	// Fields
	private int playerScore;
	private int computerScore;
	private boolean didPlayerWin;
	
	// Flags
	private GameState currentState;
	
	public enum GameState {
		READY,
		RUNNING,
		BALL_OUT_OF_BOUNDS,
		FINISHED
	}
	
	public GameWorld() {
		initGameObjects();
		playerScore = computerScore = 0;
		currentState = GameState.READY;
		didPlayerWin = false;
	}
	
	private void initGameObjects() {
		int midpointPaddleY = (int) ((GAME_HEIGHT / 2) - (Paddle.PADDLE_HEIGHT / 2));
		leftPaddle = new Paddle(LEFT_PADDLE_OFFSET, midpointPaddleY);
		rightPaddle = new Paddle(GAME_WIDTH - RIGHT_PADDLE_OFFSET, midpointPaddleY);
		ball = new Ball(this);
		leftBoundingRectangle = leftPaddle.getBoundingRectangle();
		rightBoundingRectangle = rightPaddle.getBoundingRectangle();
		ballBoundingCircle = ball.getBoundingCircle();
	}
	
	public void update(float delta) {
		switch (currentState) {
		case RUNNING:
			leftPaddle.update(delta);
			rightPaddle.setPosition(ball.getY());
			rightPaddle.update(delta);
			checkPaddleCollisions();
			ball.update(delta);
			break;
		case READY:
			break;
		case BALL_OUT_OF_BOUNDS:
			break;
		}
	}
	
	private void checkPaddleCollisions() {
		if (Intersector.overlaps(ballBoundingCircle, leftBoundingRectangle) && ball.getXVelocity() < 0) {
			ball.reverseXVelocity();
		} else if (Intersector.overlaps(ballBoundingCircle, rightBoundingRectangle) && ball.getXVelocity() > 0) {
			ball.reverseXVelocity();
		}
	}
	
	public void start() {
		currentState = GameState.RUNNING;
	}
	
	public void ballOutOfBounds() {
		currentState = GameState.BALL_OUT_OF_BOUNDS;
		if (ball.getX() < GAME_WIDTH / 2) {
			// The player lost.
			++computerScore;
			if (computerScore == 10) {
				currentState = GameState.FINISHED;
			}
			didPlayerWin = false;
		} else {
			// The computer lost.
			++playerScore;
			if (playerScore == 10) {
				currentState = GameState.FINISHED;
				didPlayerWin = true;
			}
			didPlayerWin = true;
		}
	}
	
	public void reset() {
		ball.reset();
		leftPaddle.reset();
		rightPaddle.reset();
	}
	
	public Paddle getLeftPaddle() {
		return leftPaddle;
	}
	
	public Paddle getRightPaddle() {
		return rightPaddle;
	}
	
	public Ball getBall() {
		return ball;
	}
	
	public boolean isReady() {
		return currentState == GameState.READY;
	}
	
	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}
	
	public boolean isBallOutOfBounds() {
		return currentState == GameState.BALL_OUT_OF_BOUNDS;
	}
	
	public boolean isFinished() {
		return currentState == GameState.FINISHED;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
	
	public int getComputerScore() {
		return computerScore;
	}
	
	public boolean didPlayerWin() {
		return didPlayerWin;
	}
}


