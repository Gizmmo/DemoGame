package javagame;

public class ActionEvent extends Event{

	int i = 0;
	/*
	 * Constructor for the ActionEvent class
	 */
	public ActionEvent(int eventID, float eventPositionX, float eventPositionY){
			super(eventID, eventPositionX, eventPositionY);
	}
	
	/*
	 * Called to update ActionEvent
	 */
	public void update(int delta){
		i++;
		System.out.println(i);
	}
}
