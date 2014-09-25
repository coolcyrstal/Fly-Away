package FlyAwayGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpeedUp {

	private Image speedUp;

	public SpeedUp(int vx) throws SlickException {
	    speedUp = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/speedup.png");
	}
	
	public void render() {
		speedUp.draw(20,180);
	}
}
