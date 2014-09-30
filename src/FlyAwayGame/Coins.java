package FlyAwayGame;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Coins {

	static float x, x2;
	private float y, y2;
	private Image coins;

	public Coins(float x, float y) throws SlickException {
		this.x = x;
		this.x2 = x + 200;
		this.y = y;
		this.y2 = y - 100;
		randomCoinPosition();
	    coins = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/RainbowCoin.png");
	}
	
	public float randomCoinPosition() {
		Random random = new Random();
		this.y = random.nextInt(400) - 120;
		return this.y;
	}
	
	public void render() {
		coins.draw(x,y+FlyDot.y-120);
		if (FlyAwayGame.score >= 4000) {
			coins.draw(x2,y2+FlyDot.y-240);
		}
	}
	
	public void update() {
		x += FlyAwayGame.vx;
		if (x < -100) {
			randomCoinPosition();
			x = 1100;
		}
		if (FlyAwayGame.score >= 4000) {
			x2 += FlyAwayGame.vx;
		}
		if (x2 < -100) {
			randomCoinPosition();
			x2 = 1200;
		}
	}
	
	
	
	public boolean isCollide() {
		return Colision.isCollideCoins(x, y+FlyDot.y-120);
	}
	
	public boolean isCollide2() {
		return Colision.isCollideCoins(x2, y2+FlyDot.y-240);
	}
}
