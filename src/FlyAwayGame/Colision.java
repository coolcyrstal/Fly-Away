package FlyAwayGame;

public class Colision {
	
	static boolean isCollide(float x, float y) {
		if (y <= 120) {
			return true;
		}
		else return false;
	}
	
	static boolean isCollideCoins(float x, float y) {
		System.out.println(FlyDot.x + " : " + x + "|||" + FlyDot.y + " : " + y);
		if ((FlyDot.x < x+30 && FlyDot.x > x-30) && (FlyDot.y < y+30 && FlyDot.y > y-30 )) {
			return true;
		}
		else return false;
	}
}
