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
	public static boolean howToPlay = false;
	private FlyDot flydot;
	private AngleBow anglebow;
	private Color color;
	private Coins coins; 
	private Color greyblack = new Color(20,20,20);
	public static int x = 0;
	public static float G = (float) -0.2;
	public static int score = 0;
	private int jumpLimit = 6;
	private int bounce = 2;
	
	
	public FlyAwayGame(String title) {
		super(title);
	}

	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		gameMenu(g);
		gameMenuFunction(container, g);
		howToPlay(g);
		showWhenDead(g);
	}


	public void howToPlay(Graphics g) throws SlickException {
		if (howToPlay && startGame == false) {
			g.drawString("How To Play", 450, 100);
			g.drawString("1. When you select start game you will a bow.", 100, 160);
			Image bow = new Image("C:///Users/Chayenjr/Desktop/junior/KU �� 2/OOP/Fly Away/AngleBow.png");
			bow.setRotation(-45);
			bow.draw(550, 80, 80, 80);
			g.drawString("2. You should press up/down key to change angle. ", 100, 180);
			g.drawString("3. Press enter key to start.", 100, 200);
			g.drawString("Key config", 450, 250);
			g.drawString("Spacebar : jump", 100, 300);
			g.drawString("Press key_2 back to menu game or key_1 to start game", 100, 500);
		}
	}


	public void showWhenDead(Graphics g) throws SlickException {
		if (isDead == true) {
			Rectangle box = new Rectangle(200, 30, 600, 500);
			ShapeFill fillBlack = new GradientFill(200, 30, greyblack, 800, 530, greyblack);
			g.fill(box, fillBlack);
			g.drawString("You are dead!!!!!", 430, 50);
			Image rabbit555 = new Image("C:///Users/Chayenjr/Desktop/junior/KU �� 2/OOP/Fly Away/rabbit555.png");
			rabbit555.draw(320,100);
			g.drawString("5555555555555", 550, 250);
			g.drawString("Your score is " + score, 420, 450);
		}
	}


	public void gameMenuFunction(GameContainer container, Graphics g) throws SlickException {
		if (startGame) { // Press 1 start, 2 menu, 3 exit 4 how to play
			bg();
			flydot.render();
			anglebow.render();
			if (isStarted) {
				anglebow.remove();
			}
			jumpPic();
			stringFunction(g);
			coins.render();
		}
		if (container.getInput().isKeyPressed(Input.KEY_2)) {
			startGame = false;
			isDead = false;
			howToPlay = false;
		}
		if (container.getInput().isKeyPressed(Input.KEY_3) && howToPlay == false) {
			container.exit();
		}
		if (container.getInput().isKeyPressed(Input.KEY_4)) {
			howToPlay = true;
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
		if (isStarted == false && startGame == false && isDead == false && howToPlay == false) {
			g.drawString("Please Select Game Menu", 400, 250);
			g.drawString("1 : Start Game", 400, 300);
			g.drawString("2 : Back to Menu Game", 400, 320);
			g.drawString("3 : Exit Game", 400, 340);
			g.drawString("4 : How To Play", 400, 360);
		}
	}

	public void jumpPic() throws SlickException { //Picture of jump gauge
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
		coins = new Coins(900, 0);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(isStarted) {
			angleDistanceBeforeStarted();
			x += vx;
			scoreSummary();
			bounceWhenCollision();
			coins.update();
		}
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) { //Press Esc to restart
			container.reinit();
			score = 0;
			x = 0;
			jumpLimit = 6;
			isDead = false;
			bounce = 2;
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


	public void scoreSummary() throws SlickException {
		score += -vx;
		if (score % 1500 == 0) { //Check jumplimit will get increase by 1 if score = 1200*k
			jumpLimit++;
			if (jumpLimit > 6) {
				jumpLimit = 6;
			}
		}
		if (coins.isCollide()) {
			score += 500;
			//coins.destroyCoins();
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
	    	if (isBounce == true && jumpLimit > 0) { //Check bounce when jump bounce will reset to initial value
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
