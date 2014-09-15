package FlyAwayGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

public class FlyAwayGame extends BasicGame{

	public static final int GAME_WIDTH = 1000;
	public static final int GAME_HEIGHT = 600;
	public static final int vx = -5;
	public static final float FLYDOT_JUMP_VY = 5;
	public static boolean isStarted;
	public static boolean startGame = false;
	public static boolean isBounce = false;
	public static boolean isDead = false;
	private FlyDot flydot;
	private AngleBow anglebow;
	private Coins coins;
	private Color color;
	private Color greyblack = new Color(20,20,20);
	public static int x = 0;
	public static float G = (float) -0.2;
	private int score = 0;
	private int jumpLimit = 6;
	private int bounce = 2;
	
	
	public FlyAwayGame(String title) {
		super(title);
	}

	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		gameMenu(g);
		gameMenuFunction(container, g);
		showWhenDead(g);
	}


	public void showWhenDead(Graphics g) throws SlickException {
		if (isDead == true) {
			Rectangle box = new Rectangle(200, 30, 600, 500);
			ShapeFill fillBlack = new GradientFill(200, 30, greyblack, 800, 530, greyblack);
			g.fill(box, fillBlack);
			g.drawString("You are dead!!!!!", 430, 50);
			Image rabbit555 = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/rabbit555.png");
			rabbit555.draw(320,100);
			g.drawString("5555555555555", 550, 250);
			g.drawString("Your score is " + score, 420, 450);
		}
	}


	public void gameMenuFunction(GameContainer container, Graphics g) throws SlickException {
		if (startGame) { // Press 1 start, 2 menu, 3 exit
			bg();
			flydot.render();
			anglebow.render();
			if (isStarted) {
				anglebow.remove();
			}
			jumpPic();
			stringFunction(g);
		}
		if (container.getInput().isKeyPressed(Input.KEY_2)) {
			startGame = false;
			isDead = false;
		}
		if (container.getInput().isKeyPressed(Input.KEY_3)) {
			container.exit();
		}
	}


	public void stringFunction(Graphics g) throws SlickException { //Set color of string
		color = new Color(255,0,0);
		g.setColor(color);
		g.drawString("2 : Back to Menu", 120, 10);
		g.drawString("3 : Exit", 300, 10);
		g.drawString("Esc : Restart Game", 400, 10);
		color = new Color(255, 255, 255);
		g.setColor(color);
		g.drawString("Score : " + score, 850, 10);
		g.drawString("Jump Limit x " + jumpLimit, 55, 40);
	}


	public void gameMenu(Graphics g) {
		if (isStarted == false && startGame == false && isDead == false) {
			g.drawString("Please Select Game Menu", 400, 250);
			g.drawString("1 : Start Game", 400, 300);
			g.drawString("2 : Back to Menu Game", 400, 320);
			g.drawString("3 : Exit Game", 400, 340);
		}
	}

	public void jumpPic() throws SlickException { //Picture of jump gauge
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
			bounceWhenCollision();
		}
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) { //Press Esc to restart
			container.reinit();
			score = 0;
			x = 0;
			jumpLimit = 6;
			isDead = false;
		}
	}


	public void bounceWhenCollision() {
		if(flydot.isCollide()) {
			if (bounce > 0) { //Bouncing of character that contain 2 bounce to die
				flydot.jump();
				bounce--;
				isBounce = true;
			}
			else {
				isStarted = false;
				isDead = true;
			}
		}
	}


	public void scoreSummary() {
		score += -vx;
		if (score % 1200 == 0) { //Check jumplimit will get increase by 1 if score = 1200*k
			jumpLimit++;
			if (jumpLimit > 6) {
				jumpLimit = 6;
			}
		}
	}


	public void angleDistanceBeforeStarted() { //Projectile (*-*)^(T-T)
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
	    	if (isBounce == true) { //Check bounce when jump bounce will reset to initial value
	    		isBounce = false;
	    		bounce = 2;
	    	}
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
