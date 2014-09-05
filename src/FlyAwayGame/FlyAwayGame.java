package FlyAwayGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class FlyAwayGame extends BasicGame{

	public FlyAwayGame(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
	}

	public static void main(String[] args) {
		
	    try {
	      FlyAwayGame game = new FlyAwayGame("Super Ship Game");
	      AppGameContainer appgc = new AppGameContainer(game);
	      appgc.setDisplayMode(1000, 600, false);
	      appgc.setMinimumLogicUpdateInterval(1000 / 60);
	      appgc.start();
	    } 
	    catch (SlickException e) {
	      e.printStackTrace();
	    }
	}
}
