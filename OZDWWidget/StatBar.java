import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
/**
 * Write a description of class StatBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StatBar extends Actor
{
    // Instance variables
    
    // Bar type
    private boolean isHealthBar;
    
    // Track if the ult fully charged sound has been played
    private boolean playedUltSound;
    
    // Track if the low HP warning noie is currently playing
    private boolean playingLowHPSound;
    
    // Track values
    private int currVal;
    private int maxVal;
    
    // Bar colors
    private Color filledColor;
    private Color emptyColor;
    private Color borderColor;
    
    // Bar dimensions
    private int width;
    private int height;
    private int borderWidth;
    
    // Sounds
    private GreenfootSound ultCharged = new GreenfootSound("UltimateCharged.mp3");
    
    // Source: https://www.youtube.com/watch?v=j0sJ4RoFTug
    private GreenfootSound lowHPSound = new GreenfootSound("LowHealthNoise.mp3");
    
    // Canvas
    private GreenfootImage img;
    
    public StatBar(boolean barType)
    {
        // Default width and height
        width = 300;
        height = 50;
        borderWidth = 5;
        
        // Set the maximum value for the bar
        maxVal = 100;
        
        playedUltSound = false;
        playingLowHPSound = false;
        
        // Image to draw on
        img = new GreenfootImage(width, height);
        
        // Either a health/shield/hitpoint bar or a "charge" bar (tracks ability charge/progression)
        isHealthBar = barType;
        
        // Creating the border
        borderColor = Color.DARK_GRAY;
        img.setColor(borderColor);
        img.fillRect(0, 0, width, height);
        
        // Depending on the bar type, create the bar using a set color
        if (isHealthBar)
        {
            filledColor = Color.GREEN;
            emptyColor = Color.RED;
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth, width - borderWidth * 2, height - borderWidth  * 2);
        }
        else
        {
            filledColor = Color.ORANGE;
            emptyColor = Color.BLACK;
            img.setColor(emptyColor);
            img.fillRect(borderWidth, borderWidth, width - borderWidth * 2, height - borderWidth  * 2);
        }
        
        setImage(img);
    }
    
    public StatBar(boolean barType, int width, int height, int borderWidth)
    {
        // Setting dimensions & bar type
        this.width = width;
        this.height = height;
        isHealthBar = barType;
        this.borderWidth = borderWidth;
        
        playedUltSound = false;
        playingLowHPSound = false;
        
        // Set the maximum value for the bar
        maxVal = 100;
        
        // Image to draw on
        img = new GreenfootImage(width, height);
        
        // Creating the border
        borderColor = Color.DARK_GRAY;
        img.setColor(borderColor);
        img.fillRect(0, 0, width, height);
        
        // Depending on the bar type, create the bar using a set color
        if (isHealthBar)
        {
            filledColor = Color.GREEN;
            emptyColor = Color.RED;
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth, width - borderWidth * 2, height - borderWidth  * 2);
        }
        else
        {
            filledColor = Color.ORANGE;
            emptyColor = Color.BLACK;
            img.setColor(emptyColor);
            img.fillRect(borderWidth, borderWidth, width - borderWidth * 2, height - borderWidth  * 2);
        }
        
        setImage(img);
    }

    /**
     * Act - do whatever the StatBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int test = 30;
    public void act() 
    {
        // Just some testing code
        if (Greenfoot.getKey() == "space")
        {
            ultCharged.play();
        }
        update(test);
        if (Greenfoot.isKeyDown("A"))
        {
            test--;
        }
        else if (Greenfoot.isKeyDown("D"))
        {
            test++;
        }
    }
    
    public void update(int newValue)
    {
        // Can't have a negative amount of charge/health
        if (newValue < 0) {return;}
        
        
        if (isHealthBar) // Sound playing if statement for healthbars
        {
            if (!playingLowHPSound && newValue * 10 <= maxVal * 3) // If the updated value is less than 30% of the maximum, and the sound was not previously playing, play the sound
            {
                lowHPSound.playLoop();
                playingLowHPSound = true;
            }
            else
            {
                if (newValue * 10 > maxVal * 3) // Once the updated value exceeds 30% of the maximum, no longer critical health, stop the sound
                {
                    playingLowHPSound = false;
                    lowHPSound.stop();
                }
            }
        }
        else // Sound playing if statement for charge bars
        {
            if (newValue == maxVal && !playedUltSound) 
            {
                ultCharged.play();
                playedUltSound = true;
            }
            else
            {
                // Reset the sound tracking boolean
                if (newValue < maxVal)
                {
                    playedUltSound = false;
                }
            }
            
            // Can't exceed the max value
            if (newValue > maxVal) 
            {
                newValue = maxVal;
            }
        }
        
        
        int filledWidth = (width - borderWidth * 2 ) * newValue / maxVal;
        int emptyWidth = (width - borderWidth * 2) - filledWidth;

        img.setColor(filledColor);
        img.fillRect(borderWidth, borderWidth, filledWidth, height - borderWidth * 2);
        img.setColor(emptyColor);
        img.fillRect(borderWidth + filledWidth, borderWidth, emptyWidth, height - borderWidth * 2);
        setImage(img);
    }
}
