import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
/**
 * StarBar is a Greenfoot Actor that displays values, such as health of a character of charge of an ability.
 * 
 * There are two types of StatBar. One is a circular bar that displays a percentage value in the middle as well as
 * some ticks to represent the percentage. The other is a rectangular bar that can is intended to be used as an HP/shield
 * bar, but can be used for other purposes if desired. 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StatBar extends Actor
{
    // Constants (used for ratios)
    private static final int baseChargeValue = 50;
    private static final int baseHealthValue = 100;
    
    // Instance variables
    private boolean isChargeBar;
    
    // Array to keep track of the chargeBar states
    private GreenfootImage[] chargeBarFrames = {new GreenfootImage("ultBar0.png"), new GreenfootImage("ultBar1.png"), new GreenfootImage("ultBar2.png"), new GreenfootImage("ultBar3.png"), 
                                             new GreenfootImage("ultBar4.png"), new GreenfootImage("ultBar5.png"), new GreenfootImage("ultBar6.png"), new GreenfootImage("ultBar7.png"),
                                             new GreenfootImage("ultBar8.png"), new GreenfootImage("ultBar9.png"), new GreenfootImage("ultBar10.png"), new GreenfootImage("ultBar11.png"), 
                                             new GreenfootImage("ultBar12.png"), new GreenfootImage("ultBar13.png"), new GreenfootImage("ultBar14.png"), new GreenfootImage("ultBar15.png"),  
                                             new GreenfootImage("ultBar16.png"), new GreenfootImage("ultBar17.png"), new GreenfootImage("ultBar18.png"), new GreenfootImage("ultBar19.png"), 
                                             new GreenfootImage("ultBar20.png"), new GreenfootImage("ultBar21.png"), new GreenfootImage("ultBar22.png"), new GreenfootImage("ultBar23.png"),
                                             new GreenfootImage("ultBar24.png"), new GreenfootImage("ultBar25.png"), new GreenfootImage("ultBar26.png"), new GreenfootImage("ultBar27.png"), 
                                             new GreenfootImage("ultBar28.png"), new GreenfootImage("ultBar29.png"), new GreenfootImage("ultBar30.png"), new GreenfootImage("ultBar31.png"),
                                             new GreenfootImage("ultBar32.png"), new GreenfootImage("ultBar33.png"), new GreenfootImage("ultBar34.png"), new GreenfootImage("ultBar35.png"), 
                                             new GreenfootImage("ultBar36.png")};

    // Track if the ult fully charged sound has been played
    private boolean playedUltSound;
    
    // Track if the low HP warning noise is currently playing
    private boolean playingLowHPSound;
    
    // Boolean to track if the ability is currently being used
    private boolean usingAbility;
    
    // Check if the ability bar is glowing
    private boolean isGlowing;
    
    // Track values
    private int currVal;
    private int maxVal;
    private int updtVal;
    
    // Bar colors
    private Color filledColor;
    private Color emptyColor;
    private Color borderColor;
    private Color updateUpColor;
    private Color updateDownColor;
    
    // Bar dimensions
    private int width;
    private int height;
    private int borderWidth;
    
    // Index for chargeBar array images
    private int index = 0;
    private int glowIndex = 0;
    private int animationDelay = 0;
    
    // Sounds
    private GreenfootSound ultCharged = new GreenfootSound("UltimateCharged.mp3");
    
    // Source: https://www.youtube.com/watch?v=j0sJ4RoFTug
    private GreenfootSound lowHPSound = new GreenfootSound("LowHealthNoise.mp3");
    
    // Canvas
    private GreenfootImage img;
    
    /**
     * Creates a generic StatBar, which can either be a rectangular health bar or a circular ability charge bar.
     * It has a set maximum and set current value.
     *
     * @param isChargeBar True if an ability charge bar is wanted, false otherwise.
     */
    public StatBar(boolean isChargeBar)
    {
        this.isChargeBar = isChargeBar; 
        if (isChargeBar)
        {
            width = 160;
            height = 160;
            currVal = 0;
            maxVal = 500;
            img = new GreenfootImage(160, 160);
            playedUltSound = false;
            
            chargeBarFrames[0].scale(160, 160);
            img.drawImage(chargeBarFrames[0], 0, 0);
            
            setImage(img);
        }
        else 
        {
            width = 400;
            height = 50;
            borderWidth = 10;
            currVal = 50;
            maxVal = 100;
            playingLowHPSound = false;
            
            img = new GreenfootImage(width, height);
            filledColor = Color.GREEN;
            emptyColor = Color.BLACK;
            borderColor = Color.BLACK;
            updateDownColor = Color.RED;
            updateUpColor = new Color(200, 255, 200);
            
            img.setColor(Color.BLACK);
            img.fillRect(0, 0, width, height);
            
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth, width - 2 * borderWidth, height - 2 * borderWidth);
            setImage(img);
           
        }
    }
    
    /**
     * Creates a StatBar, which is either a rectangular health bar or a circular ability charge bar, 
     * of the specified width and height, and with a specified maximum and current value.
     * 
     * @param isChargeBar True for ability charge bar, false otherwise
     * @param width Width of the StatBar
     * @param height Height of the StatBar
     * @param borderWidth Width of the border of the StatBar. Only applies to rectangular health bars and is ignored by circular ability charge bars.
     * @param maxVal Maximum possible value of the StatBar.
     * @param currVal Current value of the StatBar.
     */
    public StatBar(boolean isChargeBar, int width, int height, int borderWidth, int maxVal, int currVal)
    {
        this.isChargeBar = isChargeBar;
        this.maxVal = maxVal;
        this.currVal = currVal;
        this.width = width;
        this.borderWidth = borderWidth;
        this.height = height;
        img = new GreenfootImage(width, height);
        if (currVal > maxVal) {this.currVal = maxVal;}
        if (isChargeBar)
        {
            
            playedUltSound = false;
            double increment = (double) maxVal / 37;
            index = (int) (currVal / increment);
            if (index == 37) {index = 36;}
            chargeBarFrames[index].scale(width, height);
            img.drawImage(chargeBarFrames[index], 0, 0);
            setImage(img);
        }
        else
        {
            playingLowHPSound = false;
            filledColor = Color.GREEN;
            emptyColor = Color.BLACK;
            borderColor = Color.BLACK;
            updateDownColor = Color.RED;
            updateUpColor = new Color(200, 255, 200);
            
            img.setColor(borderColor);
            img.fillRect(0, 0, width, height);
            
            int filledWidth = (width - 2 * borderWidth) * currVal / maxVal;
            int emptyWidth = (width - 2 * borderWidth) - filledWidth;
            
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth, filledWidth, height - 2 * borderWidth);
            
            img.setColor(emptyColor);
            img.fillRect(borderWidth + filledWidth, borderWidth, emptyWidth, height - 2 * borderWidth);

            setImage(img);
        }
    }
    
    /**
     * Creates a rectangular health bar (one of the two types of StatBars).
     * 
     * @param width Width of the StatBar
     * @param height Height of the StatBar
     * @param borderWidth Width of the border of the StatBar. 
     * @param maxVal Maximum possible value of the StatBar.
     * @param currVal Current value of the StatBar.
     * @param filledColor Colour of the filled up portion of a rectangular health bar.
     * @param emptyColor Colour of the empty portion of a rectangular health bar.
     * @param borderColor Colour of the border of a rectangular health bar.
     * @param updateDownColor Colour of the animation for health loss.
     * @param udateUpColor Colour of the animation for health gain.
     */
    public StatBar(int width, int height, int borderWidth, int maxVal, int currVal, Color filledColor, Color emptyColor, Color borderColor, Color updateDownColor, Color updateUpColor)
    {
        isChargeBar = false;
        this.maxVal = maxVal;
        this.currVal = currVal;
        this.width = width;
        this.borderWidth = borderWidth;
        this.height = height;
        this.filledColor = filledColor;
        this.emptyColor = emptyColor;
        this.borderColor = borderColor;
        this.updateDownColor = updateDownColor;
        this.updateUpColor = updateUpColor;
        
        img = new GreenfootImage(width, height);
        if (currVal > maxVal) {currVal = maxVal;}
        
        playingLowHPSound = false;
        filledColor = Color.GREEN;
        emptyColor = Color.RED;
        borderColor = Color.BLACK;
        
        img.setColor(borderColor);
        img.fillRect(0, 0, width, height);
        
        int filledWidth = (width - 2 * borderWidth) * currVal / maxVal;
        int emptyWidth = (width - 2 * borderWidth) - filledWidth;
        
        img.setColor(filledColor);
        img.fillRect(borderWidth, borderWidth, filledWidth, height - 2 * borderWidth);
        
        img.setColor(emptyColor);
        img.fillRect(borderWidth + filledWidth, borderWidth, emptyWidth, height - 2 * borderWidth);

        setImage(img);

    }
    
    /**
     * Act - do whatever the StatBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        update(currVal);
        if (Greenfoot.isKeyDown("Q")) {useAbility();}
        if (Greenfoot.isKeyDown("E")) {update(maxVal / 2);}
        if (Greenfoot.isKeyDown("R")) {update(maxVal);}
        
        if (!usingAbility) 
        {
            if (Greenfoot.isKeyDown("A") && currVal > 0)
            {
                currVal--;
            }
            else if (Greenfoot.isKeyDown("D") && currVal < maxVal)
            {
                currVal++;
            }
            
        }
        else
        {
            currVal -= 2 * maxVal / baseChargeValue;
            if (currVal <= 0) {usingAbility = false; currVal = 0;}
        }
        
        // keep
        soundCheck();
        
        // Animate changes in the health bar's values
        if (currVal < updtVal)
        {
            updtVal -= (int) ((double) maxVal / baseHealthValue + 0.5);
        }
        else if (currVal > updtVal)
        {
            updtVal += (int) ((double) maxVal / baseHealthValue + 0.5);
        }
        
        // Edge case handling (if the difference between the update value and current value is very small)
        if (Math.abs(updtVal - currVal) < (int) ((double) maxVal / baseHealthValue + 0.5))
        {
            currVal = updtVal;
        }
    }
    
    /**
     * Updates the StatBar's current value with a new value. Also updates the appearance of the StatBar accordingly.
     * 
     * @param newVal New value to update the StatBar with.
     */
    public void update(int newVal)
    {
        if (newVal > maxVal) {newVal = maxVal;} // protect values (cannot exceed max)
        if (newVal < 0) {return;} // protect values (cannot have negative values)
        currVal = newVal; // update value
        if (isChargeBar) // ability charge bar update handler
        {
            img.clear();
            double increment = (double) maxVal / 36; // 36 ticks in the bar to fill, each tick is worth "increment" amount of charge
            index = (int) (currVal / increment); // Get the corresponding index for the bar's image
            if (index > 36) {index = 0;} // Prevent index out of bounds exception
            chargeBarFrames[index].scale(width, height); // scale the image to be the width and height of the bar
            img.drawImage(chargeBarFrames[index], 0, 0); // draw the image
            setImage(img);
        }
        else // health bar update handler
        {
            int filledWidth = (width - 2 * borderWidth) * newVal / maxVal; // width of the "filled in" section of the bar
            int updatingWidth = (width - 2 * borderWidth) * updtVal / maxVal; // width of the section to be updated
            int emptyWidth = (width - 2 * borderWidth) - updatingWidth; // width of the "empty" section of the bar
            
            // If statements for updating down or updating up values
            if(newVal < updtVal)
            {
                img.setColor(updateDownColor);
                img.fillRect(borderWidth, borderWidth, updatingWidth, height - 2 * borderWidth);
                
                img.setColor(filledColor);
                img.fillRect(borderWidth, borderWidth, filledWidth, height - 2 * borderWidth);
                
                img.setColor(emptyColor);
                img.fillRect(borderWidth + updatingWidth, borderWidth, emptyWidth, height - 2 * borderWidth);
            }
            else
            {
                img.setColor(updateUpColor);
                img.fillRect(borderWidth, borderWidth, filledWidth, height - 2 * borderWidth);
                
                img.setColor(filledColor);
                img.fillRect(borderWidth, borderWidth, updatingWidth, height - 2 * borderWidth);
                
                img.setColor(emptyColor);
                img.fillRect(borderWidth + filledWidth, borderWidth, emptyWidth, height - 2 * borderWidth);
            }
            setImage(img);
        }
    }
    
    /**
     * Updates the StatBar's current value and current maximum value with new values. Also updates the appearance of the StatBar accordingly.
     * 
     * @param newVal New value to update the StatBar with.
     * @param newMax New maximum value to update the StatBar with.
     */
    public void update(int newVal, int newMax)
    {
        if (newVal < 0 || newMax < 0) {return;} // Both parameters must not be negative
        maxVal = newMax; // Set max to the specified max to be updated to
        if (newVal > maxVal) {newVal = maxVal;} // Cannot exceed the max value
        currVal = newVal; // Update current value
        if (isChargeBar) // ability charge bar update handler
        {
            img.clear();
            double increment = (double) maxVal / 36;
            index = (int) (currVal / increment);
            if (index > 36) {index = 0;}
            chargeBarFrames[index].scale(width, height);
            img.drawImage(chargeBarFrames[index], 0, 0);
            setImage(img);
        }
        else // health bar update handler
        {
            int filledWidth = (width - 2 * borderWidth) * newVal / maxVal;
            int updatingWidth = (width - 2 * borderWidth) * updtVal / maxVal;
            int emptyWidth = (width - 2 * borderWidth) - updatingWidth;
            if(newVal < updtVal)
            {
                img.setColor(updateDownColor);
                img.fillRect(borderWidth, borderWidth, updatingWidth, height - 2 * borderWidth);
                
                img.setColor(filledColor);
                img.fillRect(borderWidth, borderWidth, filledWidth, height - 2 * borderWidth);
                
                img.setColor(emptyColor);
                img.fillRect(borderWidth + updatingWidth, borderWidth, emptyWidth, height - 2 * borderWidth);
            }
            else
            {
                img.setColor(updateUpColor);
                img.fillRect(borderWidth, borderWidth, filledWidth, height - 2 * borderWidth);
                
                img.setColor(filledColor);
                img.fillRect(borderWidth, borderWidth, updatingWidth, height - 2 * borderWidth);
                
                img.setColor(emptyColor);
                img.fillRect(borderWidth + filledWidth, borderWidth, emptyWidth, height - 2 * borderWidth);
            }
            
            setImage(img);

        }
    }
    
    /**
     * Checks to see if any sounds currently are playing
     * or need to be playing/played.
     */
    private void soundCheck()
    {
        if (isChargeBar)
        {
            // Attempt to play the fully charged sound if the current value is at the maximum (full charge)
            if (currVal == maxVal)
            {
                if (playedUltSound) {return;} // Don't play the sound again if it has played already
                ultCharged.play();
                playedUltSound = true;
            }
            else if (currVal < maxVal) // Reset boolean once the current value drops below the maximum
            {
                playedUltSound = false;
            }
        }
        else
        {
            if (currVal * 10 <= maxVal * 3) // for healthbars, low HP warning noise plays when the current value is <=30% of the maximum value
            {
                if (!playingLowHPSound) // Begin the loop if the sound was not already playing
                {
                    lowHPSound.playLoop();
                    playingLowHPSound = true;
                }
            }
            else // stop the sound once the current value is >30% of the maximum value
            {
                lowHPSound.stop();
                playingLowHPSound = false;
            }
        }
    }
    
    /**
     * Tells circular ability charge bars that the ability has been used
     * (essentially flags an ability as used and resets its charge).
     * Only allows the ability to be used when the current charge is equal
     * to the bar's maximum charge value. 
     */
    public void useAbility()
    {
        if (usingAbility) {return;} // Can't use an ability while already using said ability
        if (currVal < maxVal) {return;} // Can't use an ability if it is not fully charged
        if (!isChargeBar) {return;} // Healthbar's do not correspond to abilities
        usingAbility = true; // Flag the bar as "using ability" to trigger animation
    }
    
}
