package FlyAwayGame;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Coins {

	private float x;
	private float y;
	private Image coins;

	public Coins(float x, float y) throws SlickException {
		this.x = x;
		this.y = y;
		randomCoinPosition();
	    coins = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/RainbowCoin.png");
	}
	
	public void render() {
		coins.draw(x,y+FlyDot.y-120);
	}
	
	public float randomCoinPosition() {
		Random random = new Random();
		this.y = random.nextInt(600);
		if (this.y >= 480) {
			this.y = 200;
		}
		if (this.y >= 250 && this.y <= 400) {
			this.y -= 50;
		}
		return this.y;
	}
	
	public void update() {
		x += FlyAwayGame.vx;
		if (x < -100) {
			randomCoinPosition();
			x = 1100;
		}
	}
	
	public boolean isCollide() {
		return Colision.isCollideCoins(x, y);
	}
	
	public void destroyCoins() throws SlickException {
		coins.destroy();
	}
}
