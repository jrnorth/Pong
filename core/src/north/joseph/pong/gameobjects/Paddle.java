package north.joseph.pong.gameobjects;

import north.joseph.pong.game.GameWorld;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Paddle {
	// Constants
	public static final int PADDLE_WIDTH = 10;
	public static final int PADDLE_HEIGHT = 30;
	public static final int PADDLE_MOVE_SPEED = 120;
	
	// Objects
	private Rectangle boundingRectangle;
	
	private Vector2 position;
	private Vector2 velocity;
	
	public Paddle(int xPos, int yPos) {
		position = new Vector2(xPos, yPos);
		velocity = new Vector2(0, 0);
		
		boundingRectangle = new Rectangle(xPos, yPos, PADDLE_WIDTH, PADDLE_HEIGHT);
	}
	
	public void update(float delta) {
		position.add(velocity.cpy().scl(delta));
		
		if (position.y + PADDLE_HEIGHT > GameWorld.GAME_HEIGHT) {
			// Check for collision at the top of the play area.
			position.y = GameWorld.GAME_HEIGHT - PADDLE_HEIGHT;
		} else if (position.y < 0) {
			// Check for collision at the bottom of the play area.
			position.y = 0;
		}
		
		boundingRectangle.setPosition(position);
	}
	
	// Testing method for having the right paddle follow the ball.
	// Used for testing bouncing conditions.
	public void setPosition(float yPos) {
		position.y = yPos;
	}
	
	public void reset() {
		
	}
	
	public void moveUp() {
		velocity.y = PADDLE_MOVE_SPEED;
	}
	
	public void moveDown() {
		velocity.y = -PADDLE_MOVE_SPEED;
	}
	
	public void stop() {
		velocity.y = 0;
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}
}
