package FlyAwayGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class FlyDot {

	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	private Image flyimage;
	static float x;
	static float y;
	private float vy;
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
	
	public void render() throws SlickException {
		if (FlyAwayGame.heart < 2) {
			flyimage = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/flydothurt.png");
		}
		else {
			flyimage = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/flydot.png");
		}
		flyimage.draw(x - WIDTH/2, FlyAwayGame.GAME_HEIGHT - (y + HEIGHT/2));
	}
	
	public void update() {
		y += vy;
		vy += FlyAwayGame.G; 
	    limitOfXAndY();
	    change_G_WhenHigh();
	}

	public void limitOfXAndY() {
		if (y > 600) {
	    	y = 600;
	    }
	    if (y < 120) {
	    	y = 120;
	    }
	    if (x > 1000) {
	    	x = 1000;
	    }
	}

	public void change_G_WhenHigh() {
		if (y > 400) {
	    	FlyAwayGame.G = (float) -0.1;
	    }
	    else {
	    	FlyAwayGame.G = (float) -0.2;
	    }
	}
	
	public boolean isCollide() {
		return Colision.isCollide(x,y);
	}
}
