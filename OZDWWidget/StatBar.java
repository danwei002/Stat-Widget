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
    private static final int baseChargeValue = 50;
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
                                             new GreenfootImage("ultBar36.png"), new GreenfootImage("ultBar37.png"), new GreenfootImage("ultBar38.png"), new GreenfootImage("ultBar39.png"), 
                                             new GreenfootImage("ultBar40.png"), new GreenfootImage("ultBar41.png"), new GreenfootImage("ultBar42.png")};

    // Track if the ult fully charged sound has been played
    private boolean playedUltSound;
    
    // Track if the low HP warning noise is currently playing
    private boolean playingLowHPSound;
    
    // Boolean to track if the ability is currently being used
    private boolean usingAbility;
    
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
    
    // Index for chargeBar array images
    private int index = 0;
    private int animationDelay = 0;
    
    // Sounds
    private GreenfootSound ultCharged = new GreenfootSound("UltimateCharged.mp3");
    
    // Source: https://www.youtube.com/watch?v=j0sJ4RoFTug
    private GreenfootSound lowHPSound = new GreenfootSound("LowHealthNoise.mp3");
    
    // Canvas
    private GreenfootImage img;
    
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
            emptyColor = Color.RED;
            borderColor = Color.BLACK;
            
            img.setColor(Color.BLACK);
            img.fillRect(0, 0, width, height);
            
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth, width - 2 * borderWidth, height - 2 * borderWidth);
            setImage(img);
           
        }
    }
    
    public StatBar(boolean isChargeBar, int width, int height, int borderWidth, int maxVal, int currVal)
    {
        this.isChargeBar = isChargeBar;
        this.maxVal = maxVal;
        this.currVal = currVal;
        this.width = width;
        this.borderWidth = borderWidth;
        this.height = height;
        img = new GreenfootImage(width, height);
        if (currVal > maxVal) {currVal = maxVal;}
        if (isChargeBar)
        {
            playedUltSound = false;
            double increment = (double) maxVal / 43;
            index = (int) (currVal / increment);
            if (index == 43) {index = 42;}
            chargeBarFrames[index].scale(width, height);
            img.drawImage(chargeBarFrames[index], 0, 0);
            setImage(img);
        }
        else
        {
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
    }
    
    public StatBar(boolean isChargeBar, int width, int height, int borderWidth, int maxVal, int currVal, Color filledColor, Color emptyColor, Color borderColor)
    {
        this.isChargeBar = isChargeBar;
        this.maxVal = maxVal;
        this.currVal = currVal;
        this.width = width;
        this.borderWidth = borderWidth;
        this.height = height;
        this.filledColor = filledColor;
        this.emptyColor = emptyColor;
        this.borderColor = borderColor;
        
        img = new GreenfootImage(width, height);
        if (currVal > maxVal) {currVal = maxVal;}
        
        if (isChargeBar)
        {
            playedUltSound = false;
            double increment = (double) maxVal / 43;
            index = (int) (currVal / increment);
            if (index == 43) {index = 42;}
            chargeBarFrames[index].scale(width, height);
            img.drawImage(chargeBarFrames[index], 0, 0);
            setImage(img);
        }
        else
        {
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
    }
    
    /**
     * Act - do whatever the StatBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        update(currVal);
        if (Greenfoot.isKeyDown("Q")) {useAbility();}
        
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
            soundCheck();
        }
        else
        {
            currVal -= 3 * maxVal / baseChargeValue;
            if (currVal <= 0) {usingAbility = false; currVal = 0;}
        }
        
    }
    
    public void update(int newVal)
    {
        if (newVal > maxVal) {newVal = maxVal;}
        if (newVal < 0) {return;}
        currVal = newVal;
        if (isChargeBar)
        {
            img.clear();
            double increment = (double) maxVal / 43;
            index = (int) (currVal / increment);
            if (index > 42) {index = 42;}
            chargeBarFrames[index].scale(width, height);
            img.drawImage(chargeBarFrames[index], 0, 0);
            setImage(img);
        }
        else 
        {
            int filledWidth = (width - 2 * borderWidth) * currVal / maxVal;
            int emptyWidth = (width - 2 * borderWidth) - filledWidth;
            
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth, filledWidth, height - 2 * borderWidth);
            
            img.setColor(emptyColor);
            img.fillRect(borderWidth + filledWidth, borderWidth, emptyWidth, height - 2 * borderWidth);

            setImage(img);
        }
    }
    
    private void soundCheck()
    {
        if (isChargeBar)
        {
            if (currVal == maxVal)
            {
                if (playedUltSound) {return;}
                ultCharged.play();
                playedUltSound = true;
            }
            else if (currVal < maxVal)
            {
                playedUltSound = false;
            }
        }
        else
        {
            if (currVal * 10 <= maxVal * 3)
            {
                if (!playingLowHPSound)
                {
                    lowHPSound.playLoop();
                    playingLowHPSound = true;
                }
            }
            else 
            {
                lowHPSound.stop();
                playingLowHPSound = false;
            }
        }
    }
    
    public void useAbility()
    {
        if (usingAbility) {return;}
        if (currVal < maxVal) {return;}
        if (!isChargeBar) {return;}
        usingAbility = true;
    }
}
