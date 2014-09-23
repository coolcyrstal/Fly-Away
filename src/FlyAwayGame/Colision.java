package FlyAwayGame;

public class Colision {
	
	static boolean isCollide(float x, float y) {
		if (y <= 120) {
			return true;
		}
		else return false;
	}
	
	static boolean isCollideCoins(float x, float y) {
		if ((FlyDot.x+30 <= x+60 && FlyDot.x+30 >= x) && (FlyDot.y+30 <= 600-y+60 && FlyDot.y+30 >= 600-y)) {
			FlyAwayGame.score += 500;
			return true;
		}
		else return false;
	}

	static boolean isCollideBullet(float x, float y) {
		if ((FlyDot.x <= x+100 && FlyDot.x+30 >= x) && (FlyDot.y <= 600-y+30 && FlyDot.y >= 600-y)) {
			return true;
		}
		else return false;
	}
}
