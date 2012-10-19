package javagame;

/*
 * Used to trigger an event when the players sprite overlaps on the event.
 */
public class OnTouchEvent extends Event {

	/*
	 * Constructor for OnTouchEvent
	 */
	public OnTouchEvent(int eventID, float eventPositionX, float eventPositionY){
		super(eventID, eventPositionX, eventPositionY);
	}
}
