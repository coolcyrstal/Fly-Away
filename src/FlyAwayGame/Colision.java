package FlyAwayGame;

public class Colision {
	
	static boolean isCollide(float x, float y) {
		if (y <= 120) {
			return true;
		}
		else return false;
	}
	
	static boolean isCollideCoins(float x, float y) {
		if ((FlyDot.x +30 <= x+60 && FlyDot.x+30 >= x) && (FlyDot.y+30 <= 600-y+60 && FlyDot.y+30 >= 600-y)) {
			System.out.println(FlyDot.x + " : " + x + "|||" + FlyDot.y + " : " + y);
			FlyAwayGame.score += 500;
			return true;
		}
		else return false;
	}
}
