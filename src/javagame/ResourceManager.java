package javagame;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
 
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.loading.LoadingList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
/**
 * Collects an XML, and instantiates the resources stored in said xml
 * as their respective objects. These objects are stored in a map to
 * be called and collected.
 * 
 * @author Denny Scott
 * @version 11/10/2012
 *
 */
public class ResourceManager {
 
	private static String SPRITE_SHEET_REF = "__SPRITE_SHEET_";
 
	//Singleton
	private static ResourceManager _instance = new ResourceManager();
 
	//Maps of Instantiated Objects
	private Map<String, Sound> soundMap;
	private Map<String, Image> imageMap;
	private Map<String, ResourceAnimationData> animationMap;
	private Map<String, String> textMap;
 
	/**
	 * Constructor, Instantiates Maps
	 */
	private ResourceManager(){
		soundMap 	 = new HashMap<String, Sound>();
		imageMap 	 = new HashMap<String, Image>();
		animationMap = new HashMap<String, ResourceAnimationData>();
		textMap 	 = new HashMap<String, String>();
	}
 
	/**
	 * Collects the ResourceManager instance. This is always the 
	 * same instance.
	 * 
	 * @return ResourceManager Singleton object of _instance
	 */
	public final static ResourceManager getInstance(){
		return _instance;
	}
 
	/**
	 * Load Desired XML through inputstream. This will take the contents
	 * of the xml, and load them to their respective Maps, instantiated. This is
	 * the overloaded method, which will call the other loadResources method with
	 * a false passed through deferred.
	 * 
	 * @param is Stream of desired XML file. Often used in conjunction with FileInputStream
	 * @throws SlickException
	 */
	public void loadResources(InputStream is) throws SlickException {
		loadResources(is, false);
	}
 
	/**
	 * Load Desired XML through inputstream. This will take the contents
	 * of the xml, and load them to their respective Maps, instantiated. The user
	 * has the option to only selectivly load data.
	 * 
	 * @param is Stream of desired XML file. Often used in conjunction with FileInputStream
	 * @param deferred Boolean, selectivly allow data to be loaded, rather then entire xml.
	 * @throws SlickException
	 */
	public void loadResources(InputStream is, boolean deferred) throws SlickException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
		
        //Construct docBuilder, which will be used to parse our XML
        try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new SlickException("Could not load resources", e);
		}
        
        //Document to contain parsed info
		Document doc = null;
      
		
		//Attempt to Parse passed XML
		try {
			doc = docBuilder.parse (is);
		} catch (SAXException e) {
			throw new SlickException("Could not load resources", e);
		} catch (IOException e) {
			throw new SlickException("Could not load resources", e);
		}
 
		// normalize text representation
        doc.getDocumentElement ().normalize ();
 
        //Collect Resource Information
        NodeList listResources = doc.getElementsByTagName("resource");
 
        int totalResources = listResources.getLength();
 
        //Detect Deferred
        if(deferred){
        	LoadingList.setDeferredLoading(true);
        }
 
        //Iterate through Nodes of Resources, detect which type the object
        //is, and perform their respective method call.
        for(int resourceIdx = 0; resourceIdx < totalResources; resourceIdx++){
 
        	Node resourceNode = listResources.item(resourceIdx);
 
        	if(resourceNode.getNodeType() == Node.ELEMENT_NODE){
        		Element resourceElement = (Element)resourceNode;
 
        		String type = resourceElement.getAttribute("type");
 
        		if(type.equals("image")){
        			addElementAsImage(resourceElement);
        		}else if(type.equals("sound")){
        			addElementAsSound(resourceElement);
        		}else if(type.equals("text")){
        			addElementAsText(resourceElement);
        		}else if(type.equals("font")){
 
        		}else if(type.equals("animation")){
        			addElementAsAnimation(resourceElement);
        		}
        	}
        }
 
	}
 
	/**
	 * Element detected was an Animation, gather the animation information from the
	 * XML and assign the data to the needed information to loadAnimation, to instantiate
	 * an object.
	 * 
	 * @param resourceElement An element of animation recovered from the XML
	 * @throws SlickException
	 */
	private void addElementAsAnimation(Element resourceElement) throws SlickException{
		loadAnimation(resourceElement.getAttribute("id"), resourceElement.getTextContent(), 
				Integer.valueOf(resourceElement.getAttribute("tw")),
				Integer.valueOf(resourceElement.getAttribute("th")),
				Integer.valueOf(resourceElement.getAttribute("duration")));
	}
 
	/**
	 * Contents recieved will load the SpriteSheet information, and create
	 * an animation based on the other stored information in the XML, such as 
	 * duration.
	 * 
	 * @param id Stored ID of Animation, used to call
	 * @param spriteSheetPath Saved location of spriteSheetPath
	 * @param tw Location of Sprite on sheet width
	 * @param th Location of Sprite on sheet Height
	 * @param duration The duration between each animation frame
	 * @throws SlickException
	 */
	private void loadAnimation(String id, String spriteSheetPath,
			int tw, int th, int duration) throws SlickException{
		if(spriteSheetPath == null || spriteSheetPath.length() == 0)
			throw new SlickException("Image resource [" + id + "] has invalid path");
 
		loadImage( SPRITE_SHEET_REF + id, spriteSheetPath);
 
		animationMap.put(id, new ResourceAnimationData(SPRITE_SHEET_REF+id, tw, th, duration));
	}
 
	/**
	 * Used by other Objects to search for an Animation based on an ID.
	 * The content must be already loaded previously.
	 * 
	 * @param ID The ID to which the Map will be searched with.
	 * @return Animation The desired animation called for.
	 */
	public final Animation getAnimation(String ID){
		ResourceAnimationData rad = animationMap.get(ID);
 
		SpriteSheet spr = new SpriteSheet(getImage(rad.getImageId()), rad.tw, rad.th);
 
		Animation animation = new Animation(spr, rad.duration);
 
		return animation;
	}
 
	/**
	 * Add the element recovered in the XML, detected as a String, a a String object.
	 * @param resourceElement Recovered element from the XML
	 * @throws SlickException
	 */
	private void addElementAsText(Element resourceElement) throws SlickException{
		loadText(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}
 
	/**
	 * Take the attributes recovered from the XML of type "Text" and create a string
	 * object with these Variables. This object will be stored in the Text Map.
	 * 
	 * @param id Used to search for Text in Map      
	 * @param value The String itself, which will be stored with the ID
	 * @return Returns the Value of the String.
	 * @throws SlickException
	 */
	public String loadText(String id, String value) throws SlickException{
		if(value == null)
			throw new SlickException("Text resource [" + id + "] has invalid value");
 
		textMap.put(id, value);
 
		return value;
	}
 
	/**
	 * Returns String in the text map, based upon the ID passed.
	 * @param ID Used to search the Map
	 * @return The String value stored in the map
	 */
	public String getText(String ID) {
		return textMap.get(ID);
	}
 
	/**
	 * Add the element recovered in the XML, detected as a Sound,to be instantiated
	 * as a Sound object.
	 * @param resourceElement The element recovered from the XML.
	 * @throws SlickException
	 */
	private void addElementAsSound(Element resourceElement) throws SlickException {
		loadSound(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}
 
	/**
	 * Take the attributes recovered from the XML of type "Sound" and create an object
	 *  with these Variables. This object will be stored in the Sound Map.
	 * 
	 * @param id Used to search for Sound in Map      
	 * @param path The Path to a sound file, to be used to store the sound in the map
	 * @return The Sound File
	 * 
	 * @throws SlickException
	 */
	public Sound loadSound(String id, String path) throws SlickException{
		if(path == null || path.length() == 0)
			throw new SlickException("Sound resource [" + id + "] has invalid path");
 
		Sound sound = null;
 
		try {
			sound = new Sound(path);
		} catch (SlickException e) {
			throw new SlickException("Could not load sound", e);
		}
 
		this.soundMap.put(id, sound);
 
		return sound;
	}
 
	/**
	 * Return the sound file associated with the stored ID from the Map.
	 * @param ID The ID used to traverse the Sound map
	 * @return The Sound File
	 */
	public final Sound getSound(String ID){
		return soundMap.get(ID);
	}
 
	/**
	  Add the element recovered in the XML, detected as a Image,to be instantiated
	 * as a Image object.
	 * 
	 * @param resourceElement The element recovered from the XML.
	 * @return The Image found in the XML
	 */
	private final void addElementAsImage(Element resourceElement) throws SlickException {
		loadImage(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}
 
	/**
	 *  Take the attributes recovered from the XML of type "Image" and create an object
	 *  with these Variables. This object will be stored in the Image Map.
	 * 
	 * @param id Used to search for Image in Map      
	 * @param path The Path to a image file, to be used to store the image in the map
	 * @return The Image File
	 * @throws SlickException
	 */
	public Image loadImage(String id, String path) throws SlickException{
		if(path == null || path.length() == 0)
			throw new SlickException("Image resource [" + id + "] has invalid path");
 
		Image image = null;
		try{
			image = new Image(path);
		} catch (SlickException e) {
			throw new SlickException("Could not load image", e);
		}
 
		this.imageMap.put(id, image);
 
		return image;
	}
 
	/**
	 * Returns the Image stored in the image map at the specified ID.
	 * @param ID The String value used to traverse the image map
	 * @return The Desired Image File.
	 */
	public final Image getImage(String ID){
		return imageMap.get(ID);
	}
 
 /**
  * This is a private class that will help create the necessary Animation
  * when it is called upon.
  * 
  * @author Denny Scott
  * @Version 11/10/2012
  *
  */
	private class ResourceAnimationData{
		int duration;
		int tw;
		int th;
		String imageId;
 
		public ResourceAnimationData(String id, int tw, int th, int duration){
			this.imageId = id;
			this.tw = tw;
			this.th = th;
			this.duration = duration;
		}
 
		public final int getDuration() {
			return duration;
		}
		public final void setDuration(int duration) {
			this.duration = duration;
		}
		public final int getTw() {
			return tw;
		}
		public final void setTw(int tw) {
			this.tw = tw;
		}
		public final int getTh() {
			return th;
		}
		public final void setTh(int th) {
			this.th = th;
		}
		public final String getImageId() {
			return imageId;
		}
		public final void setImageId(String imageId) {
			this.imageId = imageId;
		}
 
	}
}