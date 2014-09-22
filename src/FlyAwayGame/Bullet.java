package FlyAwayGame;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bullet {

	private float x;
	private float y;
	private Image bullet;
	
	public Bullet(float x, float y) throws SlickException {
		this.x = x;
		this.y = y;
		randomBulletPosition();
	    bullet = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/firebullet.png");
	}
	
	public void render() {
		bullet.draw(x,y+FlyDot.y-120);
	}
	
	public float randomBulletPosition() {
		Random random = new Random();
		this.y = random.nextInt(400);
		return this.y;
	}
	
	public void update() {
		x += FlyAwayGame.vx;
		if (FlyAwayGame.score >= 2000) {
			randomBulletPosition();
		}
	}
}
