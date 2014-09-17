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
	    coins = new Image("C:///Users/Chayenjr/Desktop/junior/KU �� 2/OOP/Fly Away/RainbowCoin.png");
	}
	
	public void render() {
		coins.draw(x,y);
	}
	
	public float randomCoinPosition() {
		Random random = new Random();
		return this.y = random.nextInt(400);
	}
	
	public void update() {
		x += FlyAwayGame.vx;
		y += FlyDot.y;
		if (x == -100) {
			x = 1100;
		}
	}
}
