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
	private int bounce = 2;
	public static boolean startGame = false;
	
	public FlyAwayGame(String title) {
		super(title);
	}

	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		gameMenu(g);
		gameMenuFunction(container, g);
	}


	public void gameMenuFunction(GameContainer container, Graphics g) throws SlickException {
		if (startGame) {
			bg();
			flydot.render();
			g.drawString("Score : " + score, 850, 10);
			g.drawString("2 : Back to Menu", 120, 10);
			g.drawString("3 : Exit", 300, 10);
			g.drawString("Esc : Restart Game", 400, 10);
			anglebow.render();
			if (isStarted) {
				anglebow.remove();
			}
			jumpPic();
			g.drawString("Jump Limit x " + jumpLimit, 40, 40);
		}
		if (container.getInput().isKeyPressed(Input.KEY_2)) {
			startGame = false;
		}
		if (container.getInput().isKeyPressed(Input.KEY_3)) {
			container.exit();
		}
	}


	public void gameMenu(Graphics g) {
		if (isStarted == false && startGame == false) {
			g.drawString("Please Select Game Menu", 400, 250);
			g.drawString("1 : Start Game", 400, 300);
			g.drawString("2 : Back to Menu Game", 400, 320);
			g.drawString("3 : Exit Game", 400, 340);
		}
	}

	public void jumpPic() throws SlickException {
		Image jumppic = new Image("C:///Users/Chayenjr/Desktop/junior/KU �� 2/OOP/Fly Away/jump"+jumpLimit+".png");
		jumppic.draw(20,40);
	}

	private void bg() throws SlickException {
		for(int i = 0; i < 20; i++) {
			Image background = new Image("C:///Users/Chayenjr/Desktop/junior/KU �� 2/OOP/Fly Away/bg.png");
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
			bounceWhenCollision();
		}
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			container.reinit();
			score = 0;
			x = 0;
			jumpLimit = 6;
		}
	}


	public void bounceWhenCollision() {
		if(flydot.isCollide()) {
			if (bounce > 0) {
				flydot.jump();
				bounce--;
			}
			else {
				isStarted = false;
			}
		}
	}


	public void scoreSummary() {
		score += -vx;
		if (score % 1200 == 0) {
			jumpLimit++;
			if (jumpLimit > 6) {
				jumpLimit = 6;
			}
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
	    if (key == Input.KEY_SPACE && isStarted == true) {
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
	    if (key == Input.KEY_1) {
	    	startGame = true;
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
		if (jumpLimit < 0) {
			jumpLimit = 0;
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