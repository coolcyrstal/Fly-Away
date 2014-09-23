package FlyAwayGame;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bullet {

	public static float x;
	//public static float x2;
	private float y;
	private Image bullet;
	private float fBullet = 0;
	
	public Bullet(float x, float y) throws SlickException {
		this.x = x;
		//this.x2 = x;
		this.y = y;
		randomBulletPosition();
	    bullet = new Image("C:///Users/Chayenjr/Desktop/junior/KU Ле 2/OOP/Fly Away/firebullet.png");
	}
	
	public void render() {
		bullet.draw(x,y+FlyDot.y-120);
		if (FlyAwayGame.score >= 4000) {
			bullet.draw(x + 300,y+FlyDot.y);
		}
		if (FlyAwayGame.score >= 8000) {
			bullet.draw(x + 600,y+FlyDot.y-240);
		}
	}
	
	public float randomBulletPosition() {
		Random random = new Random();
		this.y = random.nextInt(400);
		return this.y;
	}
	
	public void update() {
		x += -15;
		warpBulletWhenGetOutOfScreen();
	}

	public void warpBulletWhenGetOutOfScreen() {
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
