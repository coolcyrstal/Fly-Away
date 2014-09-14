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
	public static final float FLYDOT_JUMP_VY = 5;
	public static float G = (float) -0.2;
	private FlyDot flydot;
	public static boolean isStarted;
	private int score = 0;
	private AngleBow anglebow;
	private int jumpLimit = 6;
	
	public FlyAwayGame(String title) {
		super(title);
	}

	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		bg();
		flydot.render();
		g.drawString("Score : " + score, 850, 10);
		anglebow.render();
		if (isStarted) {
			anglebow.remove();
		}
		jumpPic();
	}


	public void jumpPic() throws SlickException {
		Image jumppic = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/jump"+jumpLimit+".png");
		jumppic.draw(20,40);
	}


	private void bg() throws SlickException {
		for(int i = 0; i < 20; i++) {
			Image background = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/bg.png");
			background.draw(x + 2000*i, -600+flydot.y-120);
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		flydot = new FlyDot(100, 120,FLYDOT_JUMP_VY);
		isStarted = false;
		anglebow = new AngleBow(100,120);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(isStarted) {
			angleDistanceBeforeStarted();
			x += vx;
			scoreSummary();
		}
		if(flydot.isCollide()) {
			isStarted = false;
		}
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			container.reinit();
			score = 0;
			x = 0;
		}
	}


	public void scoreSummary() {
		score += -vx;
		if (score % 1500 == 0) {
			jumpLimit++;
		}
	}


	public void angleDistanceBeforeStarted() {
		if (score <= -anglebow.angle*5) {
			
			flydot.y += FLYDOT_JUMP_VY;
		}
		if (score <= 600 + anglebow.angle*5) {
			
			flydot.x += -vx;
		}
		else {
			flydot.update();
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ENTER) {
			isStarted = true;
		}
	    if (key == Input.KEY_SPACE) {
	    	jumpLimit();
	    }
	    if (isStarted == false && key == Input.KEY_UP) {
	    	anglebow.increaseAngle();
	    	anglebow.update();
	    }
	    if (isStarted == false && key == Input.KEY_DOWN) {
	    	anglebow.decreaseAngle();
	    	anglebow.update();
	    }
	}
	
	public void jumpLimit() {
		if (jumpLimit > 0) {
			flydot.jump();
			jumpLimit --;
		}
		if (jumpLimit > 6) {
			jumpLimit = 6;
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
