/**
 * This represents the inventory of the player in the game
 * 
 * <p>
 * The inventory stores all the collected items which includes
 * the collected keys and the different type of boots. This also
 * manages the usage and availability depending on how the game is played.
 * </p>
 * 
 * @author Melangelo Guanzon
 * @version 1.0
 * 
 */
public class Inventory {
    private int blueKeys;
    private int redKeys;
    private boolean flippers;
    private boolean fireBoots;

    /**
     * Constructs an empty inventory.
     * 
     * post-condition: initialized with zero keys and no boots of any type
     */
    public Inventory(){
        this.blueKeys = 0;
        this.redKeys = 0;
        this.flippers = false;
        this.fireBoots = false;
    }

    /**
     * Adds the key that's collected to the inventory.
     * 
     * pre-condition: key object must be initialized
     * post-condition: key count is increased then marked collected
     * 
     * <p>
     * Increments the specific key based on the color
     * then marks it as collected.
     * </p>
     * 
     * @param key the key being collected
     */
    public void addKey(Key key){
        if (key.getColor().equals("blue")){
            this.blueKeys++;
        }
        else{
            this.redKeys++;
        }
        key.setIsCollected(true);
    }

    /**
     * Uses a key of the specified color that's from the inventory.
     * 
     * pre-condition: key color should be blue or red
     * post-condition: decrease the key count then returns true, false if otherwise
     * 
     * <p>
     * The method checks if the key is available and if so,
     * it decreases the count and returns the true/false
     * 
     * @param color the specific color of the key
     * @return true if key was successfully used, false if otherwise
     */
    public boolean useKey(String color){

        if (hasKey(color)){
            if (color.equals("blue")){
                blueKeys--;
                return true;
            }
            else if (color.equals("red")){
                redKeys--;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the inventory if there's a key of the specific color.
     * 
     * pre-condition: key color should be blue or red
     * post-condition: returns true or false
     * 
     * @param color specific color of the key
     * @return true if the key of that specific color is available, false if otherwise.
     */
    public boolean hasKey(String color){
        if (color.equals("blue")){
            return blueKeys > 0;
        }
        else if (color.equals("red")){
            return redKeys > 0;
        }
        else{
            return false;
        }
    }

    /**
     * Adds the boots that's collected to the inventory.
     * 
     * Sets to true on the flag of fireBoots or flippers and marks it as collected.
     * 
     * pre-condition: boots object must be initialized
     * post-condition: is set to true, then marked as collected
     * 
     * @param boots the boots being collected
     */
    public void addBoots(Boots boots){
        if (boots.getType().equals("fireboots")){
            fireBoots = true;
        }
        else if (boots.getType().equals("flippers")){
            flippers = true;
        }
        boots.setIsCollected(true);
    }

    
    /**
     * Checks the inventory if there's a Boots of the specific type.
     * 
     * pre-condition: type must be either "fireboots" or "flippers"
     * post-condition: returns true or false
     * 
     * @param type specified type of boots
     * @return true if the boots of that specific type is available, false if otherwise
     */
    public boolean hasBoots(String type){
        if (type.equals("flippers")){
            return flippers;
        }
        else if (type.equals("fireboots")){
            return fireBoots;
        }
        else{
            return false;
        }
    }

    /**
     * displays the items in the inventory and their statuses.
     * 
     * <p>
     * Prints and shows the amount of keys of specific type
     * and the availability of the two types of boots
     * </p>
     */
    public void displayInventory(){
        System.out.println("Items in Inventory:");
        System.out.println("Blue Keys = " + blueKeys);
        System.out.println("Red Keys = " + redKeys);
        System.out.println("Fireboots = " + (fireBoots ? "Available" : "Not available"));
        System.out.println("Flippers = " + (flippers ? "Available" : "Not available"));
    }

    /**
     * Resets the inventory to its beginning state
     * 
     * <p>
     * removes all keys and sets both type of boots to unavailable by setting
     * to false
     * </p>
     */
    public void resetInventory(){
        blueKeys = 0;
        redKeys = 0;
        fireBoots = false;
        flippers = false;
    }

}
