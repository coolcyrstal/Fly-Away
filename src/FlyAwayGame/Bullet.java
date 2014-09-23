package FlyAwayGame;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bullet {

	public static float x;
	public static float x2;
	public static float x3;
	private float y;
	private Image bullet;
	
	public Bullet(float x, float y) throws SlickException {
		this.x = x;
		this.x2 = x + 300;
		this.x3 = x + 600;
		this.y = y;
		randomBulletPosition();
	    bullet = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/firebullet.png");
	}
	
	public void render() {
		bullet.draw(x,y+FlyDot.y-120);
		if (FlyAwayGame.score >= 4000) {
			bullet.draw(x2,y+FlyDot.y);
		}
		if (FlyAwayGame.score >= 8000) {
			bullet.draw(x3,y+FlyDot.y-240);
		}
	}
	
	public float randomBulletPosition() {
		Random random = new Random();
		this.y = random.nextInt(400);
		return this.y;
	}
	
	public void update() {
		x += -15;
		if (x < -50) {
			randomBulletPosition();
			x = 2000;
		}
		if (FlyAwayGame.score >= 4000) {
			x2 += -15;
		}
		if (x2 < -50) {
			randomBulletPosition();
			x2 = 2300;
		}
		if (FlyAwayGame.score >= 8000) {
			x3 += -15;
		}
		if (x3 < -50) {
			randomBulletPosition();
			x3 = 2600;
		}
	}
	
	public boolean isCollide() {
		return Colision.isCollideBullet(x, y+FlyDot.y-120);
	}
	
	public boolean isCollide2() {
		return Colision.isCollideBullet(x2, y+FlyDot.y);
	}
	
	public boolean isCollide3() {
		return Colision.isCollideBullet(x3, y+FlyDot.y-240);
	}
}
