package FlyAwayGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class FlyDot {

	public static final int WIDTH = 40, HEIGHT = 40;
	private Image flyimage;
	static float x, y;
	private float vy, vjump;
	
	public FlyDot(float x, float y, float vjump) throws SlickException {
	    this.x = x;
	    this.y = y;
	    flyimage = new Image("res/flydot.png");
	    this.vy = vjump;
	    this.vjump = vjump;
	}
	
	public void jump() {
	    vy = vjump;
	}
	
	public void render() throws SlickException {
		if (FlyAwayGame.heart < 2) {
			flyimage = new Image("res/flydothurt.png");
		}
		else {
			flyimage = new Image("res/flydot.png");
		}
		flyimage.draw(x - WIDTH/2, FlyAwayGame.GAME_HEIGHT - (y + HEIGHT/2));
	}
	
	public void update() {
		y += vy;
		change_G_WhenHigh();
		vy += FlyAwayGame.G; 
	    limitOfXAndY();
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
