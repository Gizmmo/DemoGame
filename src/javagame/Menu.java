package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Menu extends BasicGameState {
	
	private int state;
	Image playNow;
	Image exitGame;
//	public String mouse = "No Input Detected. Motha";
//	Image logo;0
//	int logoX = 200;
//	int logoY = 200;
//	int speed = 2;
	
	public Menu(int state){
		this.state = state;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
//		logo = new Image("res/Logo.png");
		playNow = new Image("res/playNow.png");
		exitGame = new Image("res/exitGame.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
//		g.drawString(mouse, 50, 50);
//		g.drawRect(50, 100, 60, 120);
		
//		g.drawImage(logo, logoX, logoY);
//		g.fillOval(75, 100, 100, 100);
//		g.drawString("play now?", 80, 70);
		
		g.drawString("Welcome to Massit Land", 100, 50);
		g.drawImage(playNow, 100, 100);
		g.drawImage(exitGame, 100, 150);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		Input input = gc.getInput();
		int xPos = Mouse.getX();
		int yPos = Mouse.getY();
//		mouse = "Mouse Position X:" +xPos + " Y:" + yPos;
		

		//Play Now Button
		if((xPos>100 && xPos<311) && (yPos>209 && yPos<260)){
			if(input.isMouseButtonDown(0)){
				sbg.enterState(1, new FadeOutTransition(Color.white, 1000), new FadeInTransition(Color.white, 1000) );;
			}
		}
		
		//Exit Button
		if((xPos>100 && xPos<311) && (yPos>159 && yPos<210)){
			if(input.isMouseButtonDown(0)){
				System.exit(0);
			}
		}
		
//		if(input.isKeyDown(Input.KEY_UP)){
//			logoY -= speed; 
//		}
//		if(input.isKeyDown(Input.KEY_DOWN)){
//			logoY += speed; 
//		}
//		if(input.isKeyDown(Input.KEY_LEFT)){
//			logoX -= speed; 
//		}
//		if(input.isKeyDown(Input.KEY_RIGHT)){
//			logoX += speed; 
//		}
			
	}
	
	public int getID(){
		return state;
	}

	
}
