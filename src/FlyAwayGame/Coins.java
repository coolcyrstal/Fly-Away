package FlyAwayGame;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Coins {

	static float x;
	static float x2;
	private float y;
	private Image coins;

	public Coins(float x, float y) throws SlickException {
		this.x = x;
		this.x2 = x + 200;
		this.y = y;
		randomCoinPosition();
	    coins = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/RainbowCoin.png");
	}
	
	public float randomCoinPosition() {
		Random random = new Random();
		this.y = random.nextInt(400);
		return this.y;
	}
	
	public void render() {
		coins.draw(x,y+FlyDot.y-120);
		if (FlyAwayGame.score >= 4000) {
			coins.draw(x2,y+FlyDot.y);
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
			x = 1200;
		}
	}
	
	
	
	public boolean isCollide() {
		return Colision.isCollideCoins(x, y+FlyDot.y-120);
	}
	
	public boolean isCollide2() {
		return Colision.isCollideCoins(x2, y+FlyDot.y);
	}
}
