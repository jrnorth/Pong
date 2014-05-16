package north.joseph.pong.gameobjects;

import north.joseph.pong.game.GameWorld;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Ball {
	// Constants
	private static final int X_SPEED = 200;
	private static final int MAX_Y_SPEED = 200;
	public static final float BALL_RADIUS = 7.5f;
	public static final float BALL_DIAMETER = 2 * BALL_RADIUS;
	
	// Objects	
	private Circle boundingCircle;
	
	private Vector2 position;
	private Vector2 velocity;
	
	private GameWorld world;
	
	// Flags
	boolean isAlive;
	
	public Ball(GameWorld world) {
		position = new Vector2(GameWorld.GAME_WIDTH / 2 - BALL_RADIUS, GameWorld.GAME_HEIGHT / 2 - BALL_RADIUS);
		
		boundingCircle = new Circle(position.x + BALL_RADIUS, position.y + BALL_RADIUS, BALL_RADIUS);
		
		boolean xStartsRight = MathUtils.randomBoolean();
		int xVelocity = xStartsRight ? 1 : -1;
		
		int yVelocity = MathUtils.random(MAX_Y_SPEED);
		
		velocity = new Vector2(xVelocity * X_SPEED, yVelocity);
		
		isAlive = true;
		
		this.world = world;
	}
	
	public void update(float delta) {
		position.add(velocity.cpy().scl(delta));
		
		if (position.x <= 0) {
			isAlive = false;
			world.ballOutOfBounds();
			return;
		} else if (position.x + BALL_DIAMETER >= GameWorld.GAME_WIDTH) {
			isAlive = false;
			world.ballOutOfBounds();
			return;
		}
		
		if (position.y < 0) {
			position.y = 0;
			reverseYVelocity();
		} else if (position.y + BALL_DIAMETER > GameWorld.GAME_HEIGHT) {
			position.y = GameWorld.GAME_HEIGHT - BALL_DIAMETER;
			reverseYVelocity();
		}
		
		boundingCircle.setPosition(position.x + BALL_RADIUS, position.y + BALL_RADIUS);
	}
	
	public void reset() {
	
	}
	
	public void reverseXVelocity() {
		velocity.x = -velocity.x;
	}
	
	public void reverseYVelocity() {
		velocity.y = -velocity.y;
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public float getXVelocity() {
		return velocity.x;
	}
	
	public Circle getBoundingCircle() {
		return boundingCircle;
	}
}
