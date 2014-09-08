package FlyAwayGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class FlyAwayGame extends BasicGame{

	public static final int GAME_WIDTH = 1000;
	public static final int GAME_HEIGHT = 600;
	public static final int vx = -5;
	public static int x = 0;
	public static final float FLYDOT_JUMP_VY = 10;
	public static final float G = (float) -0.2;
	private FlyDot flydot;
	private boolean isStarted;
	private int score = 0;
	
	public FlyAwayGame(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		Image background = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/background.png");
		background.draw(x, -600);
		flydot.render();
		g.drawString("" + score, 950, 10);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		flydot = new FlyDot(GAME_WIDTH/2, GAME_HEIGHT/2,FLYDOT_JUMP_VY);
		isStarted = false;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(isStarted) {
			flydot.update();
			x += vx;
			score += -vx;
			if (x <= -500) {
				x = 0;
			}
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ENTER) {
			isStarted = true;
		}
	    if (key == Input.KEY_SPACE) {
	    	flydot.jump();
	    }
	}

	public static void main(String[] args) {
		
	    try {
	      FlyAwayGame game = new FlyAwayGame("Fly Away Game");
	      AppGameContainer appgc = new AppGameContainer(game);
	      appgc.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, false);
	      appgc.setMinimumLogicUpdateInterval(1000 / 60);
	      appgc.start();
	    } 
	    catch (SlickException e) {
	      e.printStackTrace();
	    }
	}
}
