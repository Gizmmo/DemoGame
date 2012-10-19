package javagame;

import org.newdawn.slick.*;

public class NPCPerson {

	//Contains the many elements of a Character
	private Animation[] characterAnimations;
	private SpriteSheet currentSheet;
	private String characterName;
	private String dialog;
	private float NPCPostionX;
	private float NPCPostionY;
	
	//The constructor for NPCPerson - Manual input of parameters
	public NPCPerson(Animation[] currentCharacter, SpriteSheet currentSheet, String characterName, String dialog){
		this.setCharacterAnimations(currentCharacter);
		this.setCurrentSheet(currentSheet);
		this.setCharacterName(characterName);
		this.setDialog(dialog);
	}
	
	//Another constructor for the NPCPerson class, this one will fill a Person from a database with a unique idNumber
	public NPCPerson(int idNumber){
		//Fill Code
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	/* These are the NPCPerson classes Getters and Setters */
	
	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getDialog() {
		return dialog;
	}

	public void setDialog(String dialog) {
		this.dialog = dialog;
	}

	public SpriteSheet getCurrentSheet() {
		return currentSheet;
	}

	public void setCurrentSheet(SpriteSheet currentSheet) {
		this.currentSheet = currentSheet;
	}

	public float getNPCPostionX() {
		return NPCPostionX;
	}

	public void setNPCPostionX(float nPCPostionX) {
		NPCPostionX = nPCPostionX;
	}

	public float getNPCPostionY() {
		return NPCPostionY;
	}

	public void setNPCPostionY(float nPCPostionY) {
		NPCPostionY = nPCPostionY;
	}

	public Animation[] getCharacterAnimations() {
		return characterAnimations;
	}

	public void setCharacterAnimations(Animation[] characterAnimations) {
		this.characterAnimations = characterAnimations;
	}
}
