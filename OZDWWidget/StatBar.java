import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StatBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StatBar extends Actor
{
    // Instance variables
    
    // Track values
    private int currHp;
    private int maxHp;
    private int currShield;
    private int maxShield;
    private int currCharge;
    private int maxCharge;
    
    // Bar colors
    private Color hpFilled;
    private Color hpEmpty;
    private Color shieldFilled;
    private Color shieldEmpty;
    private Color chargeFilled;
    private Color chargeEmpty;
    
    // Bar dimensions
    private int hpWidth;
    private int hpHeight;
    private int shieldWidth;
    private int shieldHeight;
    private int chargeRadius;

    public StatBar()
    {
        GreenfootImage myImage = new GreenfootImage(200, 50);
        maxHp = 100;
        maxShield = 100;
        maxCharge = 100;
        
        myImage.setColor(Color.GREEN);
        myImage.fillRect(0, 0, 150, 25);
        setImage(myImage);
        
        myImage.setColor(Color.RED);
        myImage.fillRect(100, 0, 50, 25);
        setImage(myImage);
    }
    
    /**
     * Act - do whatever the StatBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

    }    
}
