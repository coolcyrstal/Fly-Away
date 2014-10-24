package FlyAwayGame;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ItemHeart {

	private float x;
	private float x2;
	private float y;
	private float y2;
	private Image itemheart;

	public ItemHeart(float x, float y) throws SlickException {
		this.x = x;
		this.x2 = x + 200;
		this.y = y;
		this.y2 = y - 100;
		randomItemPosition();
	    itemheart = new Image("res/itemHeart.png");
	}
	
	public float randomItemPosition() {
		Random random = new Random();
		this.y = random.nextInt(400) - 120;
		return this.y;
	}
	
	public void render() {
		itemheart.draw(x,y+FlyDot.y-120);
		if (FlyAwayGame.score >= 4000) {
			itemheart.draw(x2,y2+FlyDot.y-240);
		}
	}
	
	public void update() {
		x += FlyAwayGame.vx;
		if (x < -100) {
			randomItemPosition();
			x = 1500;
		}
		if (FlyAwayGame.score >= 4000) {
			x2 += FlyAwayGame.vx;
		}
		if (x2 < -100) {
			randomItemPosition();
			x2 = 1700;
		}
	}
	
	public boolean isCollide() {
		return Colision.isCollideItemHeart(x, y+FlyDot.y-120);
	}
	
	public boolean isCollide2() {
		return Colision.isCollideItemHeart(x2, y2+FlyDot.y-240);
	}
}
