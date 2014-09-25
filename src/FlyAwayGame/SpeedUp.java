package FlyAwayGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpeedUp {

	private Image speedUp;
	private int vx;
	private int time = 0;

	public SpeedUp(int vx) throws SlickException {
		this.vx = vx;
	    speedUp = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/speedup.png");
	}
	
	public void render() {
		speedUp.draw(20,180);
	}
	
	public void update() {
		FlyAwayGame.x += increaseSpeed();
	}
	
	public int increaseSpeed() {
		time -= this.vx-15;
		return this.vx-15;
	}
	public int checktime() {
		return time;
	}
}
