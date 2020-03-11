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
public class test extends Actor
{
    // Constants (used for ratios)
    private static final int baseChargeValue = 50;
    private static final int baseHealthValue = 100;
    private static final int offset = 20;

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
    private int hpVal;
    private int maxHpVal;
    private int chargeVal;
    private int maxChargeVal;
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
    private int diameter;
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
    public test()
    {
        diameter = 120;
        width = 400;
        height = 60;
        hpVal = 100;
        borderWidth = 10;
        maxHpVal = 100;
        chargeVal = 0;
        maxChargeVal = 100;
        
        img = new GreenfootImage(width + diameter + offset * (int) ((double) (width / 200) + 0.5), diameter);
        playedUltSound = false;

        filledColor = Color.GREEN;
        emptyColor = Color.BLACK;
        borderColor = Color.BLACK;
        updateDownColor = Color.RED;
        updateUpColor = new Color(200, 255, 200);
        
        img.setColor(Color.BLACK);
        img.fillRect(0, diameter / 4, width, height);
        
        img.setColor(filledColor);
        img.fillRect(borderWidth, diameter / 4 + borderWidth, width - 2 * borderWidth, height - 2 * borderWidth);
        
        chargeBarFrames[0].scale(diameter, diameter);
        img.drawImage(chargeBarFrames[0], width + offset * (int) ((double) (width / 200) + 0.5), 0);
        
        setImage(img);
    }
    
    /**
     * Creates a StatBar, which is either a rectangular health bar or a circular ability charge bar, 
     * of the specified width and height, and with a specified maximum and current value.
     * 
     * @param isChargeBar True for ability charge bar, false otherwise
     * @param width Width of the StatBar
     * @param height Height of the StatBar
     * @param borderWidth Width of the border of the StatBar. Only applies to rectangular health bars and is ignored by circular ability charge bars.
     * @param maxHpVal Maximum possible value of the StatBar.
     * @param hpVal Current value of the StatBar.
     */
    public test(int width, int height, int borderWidth, int hpVal, int maxHpVal, int chargeVal, int maxChargeVal)
    {
        this.maxHpVal = maxHpVal;
        this.hpVal = hpVal;
        this.chargeVal = chargeVal;
        this.maxChargeVal = maxChargeVal;
        this.width = width;
        this.borderWidth = borderWidth;
        this.height = height;
        this.diameter = height * 2;
        img = new GreenfootImage(width + diameter + offset * (int) ((double) (width / 200) + 0.5), diameter);

        playedUltSound = false;
        
        double increment = (double) maxChargeVal / 37;
        
        index = (int) (chargeVal / increment);
        if (index == 37) {index = 36;}
        chargeBarFrames[index].scale(diameter, diameter);
        img.drawImage(chargeBarFrames[index], width + offset * (int) ((double) (width / 200) + 0.5), 0);

        playingLowHPSound = false;
        filledColor = Color.GREEN;
        emptyColor = Color.BLACK;
        borderColor = Color.BLACK;
        updateDownColor = Color.RED;
        updateUpColor = new Color(200, 255, 200);
        
        img.setColor(borderColor);
        img.fillRect(0, diameter / 4, width, height);
        
        int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal;
        int emptyWidth = (width - 2 * borderWidth) - filledWidth;
        
        img.setColor(filledColor);
        img.fillRect(borderWidth, borderWidth + diameter / 4, filledWidth, height - 2 * borderWidth);
        
        img.setColor(emptyColor);
        img.fillRect(borderWidth + filledWidth, borderWidth + diameter / 4, emptyWidth, height - 2 * borderWidth);

        setImage(img);
    }
    
    /**
     * Creates a rectangular health bar (one of the two types of StatBars).
     * 
     * @param width Width of the StatBar
     * @param height Height of the StatBar
     * @param borderWidth Width of the border of the StatBar. 
     * @param maxHpVal Maximum possible value of the StatBar.
     * @param hpVal Current value of the StatBar.
     * @param filledColor Colour of the filled up portion of a rectangular health bar.
     * @param emptyColor Colour of the empty portion of a rectangular health bar.
     * @param borderColor Colour of the border of a rectangular health bar.
     * @param updateDownColor Colour of the animation for health loss.
     * @param udateUpColor Colour of the animation for health gain.
     */
    public test(int width, int height, int borderWidth, int hpVal, int maxHpVal, int chargeVal, int maxChargeVal, Color filledColor, Color emptyColor, Color borderColor, Color updateDownColor, Color updateUpColor)
    {
        this.maxHpVal = maxHpVal;
        this.hpVal = hpVal;
        this.chargeVal = chargeVal;
        this.maxChargeVal = maxChargeVal;
        this.width = width;
        this.borderWidth = borderWidth;
        this.height = height;
        this.diameter = height * 2;
        img = new GreenfootImage(width + diameter + offset * (int) ((double) (width / 200) + 0.5), diameter);

        playedUltSound = false;
        
        double increment = (double) maxChargeVal / 37;
        
        index = (int) (chargeVal / increment);
        if (index > 36) {index = 36;}
        chargeBarFrames[index].scale(diameter, diameter);
        img.drawImage(chargeBarFrames[index], width + offset * (int) ((double) (width / 200) + 0.5), 0);

        playingLowHPSound = false;
        this.filledColor = filledColor;
        this.emptyColor = emptyColor;
        this.borderColor = borderColor;
        this.updateDownColor = updateDownColor;
        this.updateUpColor = updateUpColor;
        
        img.setColor(borderColor);
        img.fillRect(0, diameter / 4, width, height);
        
        int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal;
        int emptyWidth = (width - 2 * borderWidth) - filledWidth;
        
        img.setColor(filledColor);
        img.fillRect(borderWidth, borderWidth + diameter / 4, filledWidth, height - 2 * borderWidth);
        
        img.setColor(emptyColor);
        img.fillRect(borderWidth + filledWidth, borderWidth + diameter / 4, emptyWidth, height - 2 * borderWidth);

        setImage(img);

    }
    
    /**
     * Act - do whatever the StatBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        update(true, hpVal);
        if (Greenfoot.isKeyDown("Q")) {useAbility();}
        if (Greenfoot.isKeyDown("E")) {update(true, maxHpVal / 2);}
        if (Greenfoot.isKeyDown("F")) {update(false, maxChargeVal / 2);}

        if (Greenfoot.isKeyDown("A") && hpVal > 0)
        {
            hpVal--;
        }
        else if (Greenfoot.isKeyDown("D") && hpVal < maxHpVal)
        {
            hpVal++;
        }
            
        if (Greenfoot.isKeyDown("A") && chargeVal > 0)
        {
            chargeVal--;
        }
        else if (Greenfoot.isKeyDown("D") && chargeVal < maxChargeVal)
        {
            chargeVal++;
        }

        //update(true, hpVal);
        //update(false, chargeVal);
        // keep
        soundCheck();
        
        // Animate changes in the health bar's values
        if (hpVal < updtVal)
        {
            updtVal -= (int) ((double) maxHpVal / baseHealthValue + 0.5);
        }
        else if (hpVal > updtVal)
        {
            updtVal += (int) ((double) maxHpVal / baseHealthValue + 0.5);
        }
        
        // Edge case handling (if the difference between the update value and current value is very small)
        if (Math.abs(updtVal - hpVal) < (int) ((double) maxHpVal / baseHealthValue + 0.5))
        {
            hpVal = updtVal;
        }
    }
    
    /**
     * Updates the StatBar's current value with a new value. Also updates the appearance of the StatBar accordingly.
     * 
     * @param newVal New value to update the StatBar with.
     */
    public void update(boolean updatingHp, int newVal)
    {
        if (updatingHp)
        {
            if (newVal > maxHpVal) {newVal = maxHpVal;} // protect values (cannot exceed max)
            if (newVal < 0) {return;} // protect values (cannot have negative values)
            hpVal = newVal; // update value
        }
        else
        {
            if (newVal > maxChargeVal) {newVal = maxChargeVal;} // protect values (cannot exceed max)
            if (newVal < 0) {return;} // protect values (cannot have negative values)
            chargeVal = newVal; // update value
        }
        
        img.clear();
        img.setColor(borderColor);
        img.fillRect(0, diameter / 4, width, height);
        
        double increment = (double) maxChargeVal / 36; // 36 ticks in the bar to fill, each tick is worth "increment" amount of charge
        index = (int) (chargeVal / increment); // Get the corresponding index for the bar's image
        if (index == 37) {index = 0;} // Prevent index out of bounds exception
        chargeBarFrames[index].scale(diameter, diameter); 
        img.drawImage(chargeBarFrames[index], width + offset * (int) ((double) (width / 200) + 0.5), 0); // draw the image
        
        // width of the "empty" section of the bar
        
        // If statements for updating down or updating up values
        if(hpVal < updtVal)
        {
             int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal; // width of the "filled in" section of the bar
             int updatingWidth = (width - 2 * borderWidth) * updtVal / maxHpVal; // width of the section to be updated
             int emptyWidth = (width - 2 * borderWidth) - updatingWidth;
            img.setColor(updateDownColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, updatingWidth, height - 2 * borderWidth);
            
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, filledWidth, height - 2 * borderWidth);
            
            img.setColor(emptyColor);
            img.fillRect(borderWidth + updatingWidth, borderWidth + diameter / 4, emptyWidth, height - 2 * borderWidth);
        }
        else
        {
            int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal; // width of the "filled in" section of the bar
            int updatingWidth = (width - 2 * borderWidth) * updtVal / maxHpVal; // width of the section to be updated
            int emptyWidth = (width - 2 * borderWidth) - filledWidth; // width of the "empty" section of the bar
            img.setColor(updateUpColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, filledWidth, height - 2 * borderWidth);
            
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, updatingWidth, height - 2 * borderWidth);
            
            img.setColor(emptyColor);
            img.fillRect(borderWidth + filledWidth, borderWidth + diameter / 4, emptyWidth, height - 2 * borderWidth);
        }
        setImage(img);
    }
    
    /**
     * Updates the StatBar's current value and current maximum value with new values. Also updates the appearance of the StatBar accordingly.
     * 
     * @param newVal New value to update the StatBar with.
     * @param newMax New maximum value to update the StatBar with.
     */
    public void update(boolean updatingHp, int newVal, int newMax)
    {
        if (updatingHp) 
        {
           if (newVal < 0 || newMax < 0) {return;} // Both parameters must not be negative
            maxHpVal = newMax; // Set max to the specified max to be updated to
            if (newVal > maxHpVal) {newVal = maxHpVal;} // Cannot exceed the max value
            hpVal = newVal; // Update current value 
        }
        else
        {
            if (newVal < 0 || newMax < 0) {return;}
            maxChargeVal = newMax;
            if (newVal > maxChargeVal) {newVal = maxChargeVal;}
            chargeVal = newVal;
        }
        
        img.clear();
        img.setColor(borderColor);
        img.fillRect(0, diameter / 4, width, height);
        
        double increment = (double) maxChargeVal / 36; // 36 ticks in the bar to fill, each tick is worth "increment" amount of charge
        index = (int) (chargeVal / increment); // Get the corresponding index for the bar's image
        if (index == 37) {index = 0;} // Prevent index out of bounds exception
        chargeBarFrames[index].scale(diameter, diameter); 
        img.drawImage(chargeBarFrames[index], width + offset * (int) ((double) (width / 200) + 0.5), 0); // draw the image
        
        int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal; // width of the "filled in" section of the bar
        int updatingWidth = (width - 2 * borderWidth) * updtVal / maxHpVal; // width of the section to be updated
        int emptyWidth = (width - 2 * borderWidth) - updatingWidth; // width of the "empty" section of the bar
        
        // If statements for updating down or updating up values
        if(newVal < updtVal)
        {
            img.setColor(updateDownColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, updatingWidth, height - 2 * borderWidth);
            
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, filledWidth, height - 2 * borderWidth);
            
            img.setColor(emptyColor);
            img.fillRect(borderWidth + updatingWidth, borderWidth + diameter / 4, emptyWidth, height - 2 * borderWidth);
        }
        else
        {
            img.setColor(updateUpColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, filledWidth, height - 2 * borderWidth);
            
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, updatingWidth, height - 2 * borderWidth);
            
            img.setColor(emptyColor);
            img.fillRect(borderWidth + filledWidth, borderWidth + diameter / 4, emptyWidth, height - 2 * borderWidth);
        }
        setImage(img);
    }
    
    /**
     * Checks to see if any sounds currently are playing
     * or need to be playing/played.
     */
    private void soundCheck()
    {

            // Attempt to play the fully charged sound if the current value is at the maximum (full charge)
            if (hpVal == maxHpVal)
            {
                if (playedUltSound) {return;} // Don't play the sound again if it has played already
                ultCharged.play();
                playedUltSound = true;
            }
            else if (hpVal < maxHpVal) // Reset boolean once the current value drops below the maximum
            {
                playedUltSound = false;
            }

            if (hpVal * 10 <= maxHpVal * 3) // for healthbars, low HP warning noise plays when the current value is <=30% of the maximum value
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
    
    /**
     * Tells circular ability charge bars that the ability has been used
     * (essentially flags an ability as used and resets its charge).
     * Only allows the ability to be used when the current charge is equal
     * to the bar's maximum charge value. 
     */
    public void useAbility()
    {
        if (usingAbility) {return;} // Can't use an ability while already using said ability
        if (hpVal < maxHpVal) {return;} // Can't use an ability if it is not fully charged
        usingAbility = true; // Flag the bar as "using ability" to trigger animation
    }
    
}