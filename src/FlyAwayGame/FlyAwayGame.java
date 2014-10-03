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
	public static int x = 0, vx = -5;
	public static final float FLYDOT_JUMP_VY = 5;
	public static float G = (float) -0.2;
	public static boolean isStarted;
	public static boolean startGame = false, howToPlay = false;
	public static boolean isBounce = false , isDead = false;
	private boolean speedSkill = false;
	private FlyDot flydot;
	private AngleBow anglebow;
	private Color color, greyblack = new Color(20,20,20);
	private Coins coins; 
	private Bullet bullet;
	private SpeedUp speedup;
	public static int score = 0, countScore = 1;
	public static int jumpLimit = 6;
	public static int BULLET_COUNT = 1;
	public static int heart = 2;
	public static int delaySkillSpeed = 0;
	private int bounce = 2;
	private int speedDistance = 0;
	
	
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

	public void gameMenu(Graphics g) {
		if (isStarted == false && startGame == false && isDead == false && howToPlay == false) {
			g.drawString("Please Select Game Menu", 400, 250);
			g.drawString("1 : Start Game", 400, 300);
			g.drawString("2 : Back to Menu Game", 400, 320);
			g.drawString("3 : Exit Game", 400, 340);
			g.drawString("4 : How To Play", 400, 360);
		}
	}


	public void gameMenuFunction(GameContainer container, Graphics g) throws SlickException {
		
		// Press 1 start, 2 menu, 3 exit 4 how to play
		if (startGame) { 
			bg();
			flydot.render();
			anglebow.render();
			speedup.render();
			if (isStarted) {
				anglebow.remove();
			}
			jumpAndHeartPic();
			stringFunction(g);
			coins.render();
			bullet.render();
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

	
	public void howToPlay(Graphics g) throws SlickException {
		if (howToPlay && startGame == false) {
			g.drawString("How To Play", 450, 80);
			g.drawString("--------------------------------------" + 
						"---------------------------------------" + "--------------", 80, 100);
			g.drawString("Before Start Game", 100, 150);
			Image bow = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/AngleBow.png");
			bow.setRotation(-45);
			bow.draw(600, 100, 80, 80);
			g.drawString("1. You should press 'up/down' key to change angle of bow ", 100, 180);
			g.drawString("2. Press 'Enter' to start.", 100, 200);
			g.drawString("Key && Skill in Game", 100, 270);
			g.drawString("Spacebar : jump", 100, 300);
			g.drawString("Skill Speedup : 'A'", 100, 320);
			g.drawString("Please press '2' to get back menu game or '1' to start game", 100, 500);
		}
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

	//text that show when play game
	public void stringFunction(Graphics g) throws SlickException { 
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


	

	//Picture of jump and heart gauge
	public void jumpAndHeartPic() throws SlickException { 
		Image jumppic = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/jump" + jumpLimit + ".png");
		jumppic.draw(20,40);
		Image heartPic = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/heart" + heart + ".png");
		heartPic.draw(20,80);
	}

	//Create background
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
		coins = new Coins(900, 0);
		bullet = new Bullet(2000, 0);
		speedup = new SpeedUp(vx);
		
	}
		
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(isStarted) {
			angleDistanceBeforeStarted();
			x += vx;
			scoreSummary();
			bounceWhenCollision();
			coins.update();
			bullet.update();
			checkSkillSpeedUp();
		}
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) { //Press Esc to restart
			container.reinit();
			resetInitialValue();
		}
	}
	
	//if game restart >> reset variable of game to initial value
	public void resetInitialValue() {
		score = 0;
		x = 0;
		jumpLimit = 6;
		isDead = false;
		bounce = 2;
		heart = 2;
		countScore = 1;
		delaySkillSpeed = 0;
	}
	
	//Projectile of angle that select before start
	public void angleDistanceBeforeStarted() { 
		if (score <= -anglebow.angle*5 || score <= 600 + anglebow.angle*5) {
			if (score < -anglebow.angle*5) {
				flydot.y += FLYDOT_JUMP_VY;	
			}
			else if (score > -anglebow.angle*5) {
				flydot.y += 1;	
			}
		}
		if (score <= 600 + anglebow.angle*5 || score <= -anglebow.angle*5) {
			if (score < 600 + anglebow.angle*5) {
				flydot.x += -vx;	
			}
			else if (score > 600 + anglebow.angle*5) {
				flydot.x += 1;	
			}
		}
		else {
			flydot.update();
		}
	}

	public void bounceWhenCollision() {
		if(flydot.isCollide()) {
			//Bouncing of character that contain 2 bounce to die
			if (bounce > 0) { 
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
	
	// SUM of score
	public void scoreSummary() throws SlickException {
		score += -vx;
		increaseJumpLimitWhenScoreUp();
		checkGetCoins();
		checkBulletAttack();
	}
	
	// Skill speedup
	public void checkSkillSpeedUp() {
		if (speedSkill) {
			speedDistance -= vx;
			delaySkillSpeed--;
			if (speedDistance > 500) {
				speedSkill = false;
				vx = -5;
			}
		}
		// check delay of skill
		else if (delaySkillSpeed > 0) {
			delaySkillSpeed--;
		}
	}
	
	//Check jump limit will get increase by 1 if score = 1200*k
	public void increaseJumpLimitWhenScoreUp() {
		if (score - 1200*countScore >= 0) { 
			countScore++;
			jumpLimit++;
			if (jumpLimit > 6) {
				jumpLimit = 6;
			}
		}
	}

	// check when rabbit collision to coin
	public void checkGetCoins() {
		if (coins.isCollide()) {
			coins.x += 1000;
			increaseJumpLimitWhenScoreUp();
		}
		if (coins.isCollide2() && score >= 4000) {
			coins.x2 += 1200;
			increaseJumpLimitWhenScoreUp();
		}
	}

	// check when rabbit attack by bullet
	public void checkBulletAttack() {
		if (bullet.isCollide()) {
			bullet.x += 2000;
			heartLifeWhenAttack();
		}
		if (bullet.isCollide2() && score >= 4000) {
			bullet.x2 += 2300;
			heartLifeWhenAttack();
		}
		if (bullet.isCollide3() && score >= 8000) {
			bullet.x3 += 2600;
			heartLifeWhenAttack();
		}
	}

	//decrease heart life by 1, check heart life when life == 0 >> DIED!!
	public void heartLifeWhenAttack() {
		heart--;
		if (heart == 0) { 
			isStarted = false;
			isDead = true;
		}
	}

	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ENTER && isDead == false) {
			isStarted = true;
		}
	    if (key == Input.KEY_SPACE && isStarted == true) {
	    	jump();
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
	    if (key == Input.KEY_A && delaySkillSpeed <= 0) {
	    	speedSkill = true;
	    	speedDistance = 0;
	    	delaySkillSpeed = 550;
	    	vx = -10;
	    }
	}


	public void jump() {
		if (jumpLimit > 0) {
			jumpLimit();
			
			//Check bounce when jump >> bounce will reset to initial value
			if (isBounce == true) { 
				isBounce = false;
				bounce = 2;
			}
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
