package north.joseph.pong.helpers;

import north.joseph.pong.game.GameWorld;
import north.joseph.pong.gameobjects.Paddle;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {
	private GameWorld world;
	private Paddle leftPaddle;
	
	public InputHandler(GameWorld world) {
		this.world = world;
		this.leftPaddle = world.getLeftPaddle();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.UP:
			leftPaddle.moveUp();
			break;
		case Input.Keys.DOWN:
			leftPaddle.moveDown();
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.UP || keycode == Input.Keys.DOWN)
			leftPaddle.stop();
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (world.isReady())
			world.start();
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
