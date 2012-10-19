package javagame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * This is a Demo for level One. It is currently called play. In this class, we choose a map,
 * and Direct the Controls.
 * 
 * @author Denny Scott
 * @version 09/10/2012
 *
 */
public class Play extends BasicGameState {
	
	//Controller for Level
	private Controls controller;
	private int state;
	private Sound backgroundMusic;

	
	//Map
	private BlockMap map;
	
	
	/**
	 * Constructor
	 * @param state The current state of the game. For our demo, this will be 1.
	 */
	public Play(int state){
		this.state = state;
	}
	
	/**
	 * Set which map you would like to use (ending with the .tmx attribute), and then decide
	 * where on the map the user will appear. This is done when creating a new Controller. Both 
	 * the map and the controller must be initialized directly after being created.
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		gc.setVSync(true);
		
		map = new BlockMap("res/leveltwo.tmx");
		
		try{
		InputStream test = new FileInputStream("res/sounds.xml");
	    ResourceManager.getInstance().loadResources(test);
		backgroundMusic = ResourceManager.getInstance().getSound("ZELDA_BACKGROUND");
		}catch(FileNotFoundException e){
			throw new SlickException("Could not find xml resources", e);
		}

		//rm.loadSound("ZELDA_BACKGROUND", "res/sounds.xml");
		
		
		float playerX =gc.getWidth()/2;
		float playerY = gc.getHeight()/2;
		
		controller = new Controls(playerX, playerY, 3, map, gc);
		
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) {
	      backgroundMusic.play();   
	      backgroundMusic.loop();
	   }
	
	public void leave(GameContainer gc, StateBasedGame sbg) {
		  backgroundMusic.stop();
	   }
	
	/**
	 * Render the Map and the controller on screen. (Controller being the user's character)/
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		map.render(controller.getCameraX(), controller.getCameraY());
		controller.render(g);
		
	}
	
	/**
	 * Update the Controller. This allows users to move across screen.
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		controller.update();
	}
	
	/**
	 * Get the current State (ID) of this level.
	 * 
	 * @return state The current Id of the level.
	 */
	public int getID(){
		return state;
	}

	/**
	 * Get the map associated with this level.
	 * @return map The current map of the level.
	 */
	public BlockMap getMap() {
		return map;
	}

	/**
	 * Set the map associated with this level
	 * @param map The map that will be set to this level.
	 */
	public void setMap(BlockMap map) {
		this.map = map;
	}

	
}
