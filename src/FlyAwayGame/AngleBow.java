package FlyAwayGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AngleBow{
	
	private float x, y;
	private Image anglebow;
	private int WIDTH = 200, HEIGHT = 120;
	int angle = -45;
	private int add;

	public AngleBow(float x, float y) throws SlickException {
	    this.x = x;
	    this.y = y;
	    anglebow = new Image("res/AngleBow.png");
	}
	
	public void render() {
		anglebow.draw(x - WIDTH/2 + 20, FlyAwayGame.GAME_HEIGHT - (y + HEIGHT /2));
		anglebow.setRotation(angle);
	}
	
	public void increaseAngle() {
		add = -5;
	}
	
	public void decreaseAngle() {
		add = 5;
	}
	
	public void update() {
		angle += add;
		limitAngle();
	}

	public void limitAngle() {
		if (angle < -80) {
			angle = -80;
		}
		if (angle > -10) {
			angle = -10;
		}
	}

	public void remove() throws SlickException {
		x -= 200;
		y += 200;
	}
}
