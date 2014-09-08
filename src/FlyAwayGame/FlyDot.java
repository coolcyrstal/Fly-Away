package FlyAwayGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FlyDot {

	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	private Image flyimage;
	private float x;
	float y;
	private float vy;
	private float vx = 5;
	private float vjump;
	
	public FlyDot(float x, float y, float vjump) throws SlickException {
	    this.x = x;
	    this.y = y;
	    flyimage = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/flydot.png");
	    this.vy = vjump;
	    this.vjump = vjump;
	}
	
	public void jump() {
	    vy = vjump;
	}
	
	public void render() {
		flyimage.draw(x - WIDTH/2, FlyAwayGame.GAME_HEIGHT - (y + HEIGHT/2));
	}
	
	public void update() {
		y += vy;
	    vy += FlyAwayGame.G;
	}
	
}
