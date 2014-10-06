package FlyAwayGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpeedUp {

	private Image speedUp;
	private int i = 0;

	public SpeedUp(int vx) throws SlickException {
	    speedUp = new Image("res/speedup0.png");
	}
	
	public void render() throws SlickException {
		if (FlyAwayGame.delaySkillSpeed <= 550 && FlyAwayGame.delaySkillSpeed >= 400) {
			i = 3;
		}
		else if (FlyAwayGame.delaySkillSpeed < 400 && FlyAwayGame.delaySkillSpeed >= 200) {
			i = 2;
		}
		else if (FlyAwayGame.delaySkillSpeed < 200 && FlyAwayGame.delaySkillSpeed > 0) {
			i = 1;
		}
		else i = 0;
		speedUp = new Image("res/speedup" + i + ".png");
		speedUp.draw(20,180);
	}
}
