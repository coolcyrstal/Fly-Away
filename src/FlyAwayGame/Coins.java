package FlyAwayGame;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Coins {

	static float x;
	static float x2;
	private float y;
	private float y2;
	private Image coins;

	public Coins(float x, float y) throws SlickException {
		this.x = x;
		this.x2 = x + 200;
		this.y = y;
		this.y2 = y;
		randomCoinPosition(y);
	    coins = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/RainbowCoin.png");
	}
	
	public float randomCoinPosition(float y) {
		Random random = new Random();
		y = random.nextInt(400) - random.nextInt(300);
		return y;
	}
	
	public void render() {
		coins.draw(x,y+FlyDot.y-120);
		if (FlyAwayGame.score >= 4000) {
			coins.draw(x2,y2+FlyDot.y);
		}
	}
	
	public void update() {
		x += FlyAwayGame.vx;
		if (x < -100) {
			randomCoinPosition(y);
			x = 1100;
		}
		if (FlyAwayGame.score >= 4000) {
			x2 += FlyAwayGame.vx;
		}
		if (x2 < -100) {
			randomCoinPosition(y2);
			x2 = 1200;
		}
	}
	
	
	
	public boolean isCollide() {
		return Colision.isCollideCoins(x, y+FlyDot.y-120);
	}
	
	public boolean isCollide2() {
		return Colision.isCollideCoins(x2, y2+FlyDot.y);
	}
}
