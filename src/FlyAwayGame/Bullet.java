package FlyAwayGame;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bullet {

	public static float x;
	public static float x2;
	public static float x3;
	private float y;
	private float y2;
	private float y3;
	private Image bullet;
	
	public Bullet(float x, float y) throws SlickException {
		this.x = x;
		this.x2 = x + 300;
		this.x3 = x + 600;
		this.y = y;
		this.y2 = y;
		this.y3 = y;
		randomBulletPosition();
	    bullet = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/firebullet.png");
	}
	
	public void render() {
		bullet.draw(x,y+FlyDot.y-120);
		if (FlyAwayGame.score >= 4000) {
			bullet.draw(x2,y2+FlyDot.y-240);
		}
		if (FlyAwayGame.score >= 8000) {
			bullet.draw(x3,y3+FlyDot.y-180);
		}
	}
	
	public float randomBulletPosition() {
		Random random = new Random();
		this.y = random.nextInt(400) - 120;
		return this.y;
	}
	
	public void update() {
		x += -15;
		if (x < -50) {
			randomBulletPosition();
			x = 2000;
		}
		if (FlyAwayGame.score >= 4000) {
			if (FlyAwayGame.score >= 6000) {
				x2 += -20;
			}
			else x2 += -15;
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
		return Colision.isCollideBullet(x2, y2+FlyDot.y-240);
	}
	
	public boolean isCollide3() {
		return Colision.isCollideBullet(x3, y3+FlyDot.y-180);
	}
}
