package FlyAwayGame;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bullet {

	public static float x;
	private float y;
	private Image bullet;
	private float fBullet = 0;
	
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
		x += -20;
		if (x <= -50) {
			randomBulletPosition();
			x += 2050 - fBullet*50; 
			if (x <= 1000) {
				x = 1000;
			}
			fBullet++;
		}
	}
	
	public boolean isCollide() {
		return Colision.isCollideBullet(x, y+FlyDot.y-120);
	}
}
