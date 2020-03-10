import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
/**
 * DESC
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StatBar extends Actor
{
    private static final int baseChargeValue = 50;
    private static final int baseHealthValue = 100;
    // Instance variables
    private boolean isChargeBar;
    
    // Array to keep track of the glowing animation frames
    private GreenfootImage[] glowFrames = {new GreenfootImage("glow1.gif"), new GreenfootImage("glow2.gif"), new GreenfootImage("glow3.gif"),
                                            new GreenfootImage("glow4.gif"), new GreenfootImage("glow5.gif"), new GreenfootImage("glow6.gif"), new GreenfootImage("glow7.gif"),
                                            new GreenfootImage("glow8.gif"), new GreenfootImage("glow9.gif"), new GreenfootImage("glow10.gif"), new GreenfootImage("glow11.gif"),
                                            new GreenfootImage("glow12.gif"), new GreenfootImage("glow13.gif"), new GreenfootImage("glow14.gif"), new GreenfootImage("glow15.gif"), 
                                            new GreenfootImage("glow16.gif"), new GreenfootImage("glow17.gif"), new GreenfootImage("glow18.gif"), new GreenfootImage("glow19.gif"),
                                            new GreenfootImage("glow20.gif"), new GreenfootImage("glow21.gif"), new GreenfootImage("glow22.gif"), new GreenfootImage("glow23.gif"),
                                            new GreenfootImage("glow24.gif"), new GreenfootImage("glow25.gif"), new GreenfootImage("glow26.gif"), new GreenfootImage("glow27.gif"),
                                            new GreenfootImage("glow28.gif"), new GreenfootImage("glow29.gif"), new GreenfootImage("glow30.gif"), new GreenfootImage("glow31.gif"),
                                            new GreenfootImage("glow32.gif"), new GreenfootImage("glow33.gif"), new GreenfootImage("glow34.gif"), new GreenfootImage("glow35.gif"),
                                            new GreenfootImage("glow36.gif"), new GreenfootImage("glow37.gif"), new GreenfootImage("glow38.gif"), new GreenfootImage("glow39.gif"),
                                            new GreenfootImage("glow40.gif"), new GreenfootImage("glow41.gif"), new GreenfootImage("glow42.gif"), new GreenfootImage("glow43.gif"), 
                                            new GreenfootImage("glow44.gif"), new GreenfootImage("glow45.gif"), new GreenfootImage("glow46.gif"), new GreenfootImage("glow47.gif"), 
                                            new GreenfootImage("glow48.gif"), new GreenfootImage("glow49.gif"), new GreenfootImage("glow50.gif"), new GreenfootImage("glow51.gif"), 
                                            new GreenfootImage("glow52.gif"), new GreenfootImage("glow53.gif"), new GreenfootImage("glow54.gif"), new GreenfootImage("glow55.gif"), 
                                            new GreenfootImage("glow56.gif"), new GreenfootImage("glow57.gif"), new GreenfootImage("glow58.gif"), new GreenfootImage("glow59.gif"), 
                                            new GreenfootImage("glow60.gif"), new GreenfootImage("glow61.gif"),new GreenfootImage("glow62.gif"), new GreenfootImage("glow63.gif"), 
                                            new GreenfootImage("glow64.gif"), new GreenfootImage("glow65.gif"),new GreenfootImage("glow66.gif"), new GreenfootImage("glow67.gif"),
                                            new GreenfootImage("glow68.gif"), new GreenfootImage("glow69.gif"),new GreenfootImage("glow70.gif"), new GreenfootImage("glow71.gif"), 
                                            new GreenfootImage("glow72.gif"), new GreenfootImage("glow73.gif"),new GreenfootImage("glow74.gif"), new GreenfootImage("glow75.gif"), 
                                            new GreenfootImage("glow76.gif"), new GreenfootImage("glow77.gif"),new GreenfootImage("glow78.gif"), new GreenfootImage("glow79.gif"), 
                                            new GreenfootImage("glow80.gif"), new GreenfootImage("glow81.gif"),new GreenfootImage("glow82.gif"), new GreenfootImage("glow83.gif"), 
                                            new GreenfootImage("glow84.gif"), new GreenfootImage("glow85.gif"),new GreenfootImage("glow86.gif"), new GreenfootImage("glow87.gif"),
                                            new GreenfootImage("glow88.gif"), new GreenfootImage("glow89.gif"),new GreenfootImage("glow90.gif")};

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
        if (Greenfoot.isKeyDown("E")) {update(maxVal / 2);}
        if (Greenfoot.isKeyDown("R")) {update(400, 500);}
        
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
            currVal -= 3 * maxVal / baseChargeValue;
            if (currVal <= 0) {usingAbility = false; currVal = 0;}
        }
        
        // keep
        soundCheck();
        if (currVal < updtVal)
        {
            updtVal -= (int) ((double) maxVal / baseHealthValue + 0.5);
        }
        else if (currVal > updtVal)
        {
            updtVal += (int) ((double) maxVal / baseHealthValue + 0.5);
        }
        
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
     * Updates the StatBar's current value and current maximum value with new values. Also updates the appearance of the StatBar accordingly.
     * 
     * @param newVal New value to update the StatBar with.
     * @param newMax New maximum value to update the StatBar with.
     */
    public void update(int newVal, int newMax)
    {
        if (newVal < 0 || newMax < 0) {return;}
        maxVal = newMax;
        if (newVal > maxVal) {newVal = maxVal;}
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
    
    /**
     * Tells circular ability charge bars that the ability has been used
     * (essentially flags an ability as used and resets its charge).
     * Only allows the ability to be used when the current charge is equal
     * to the bar's maximum charge value. 
     */
    public void useAbility()
    {
        if (usingAbility) {return;}
        if (currVal < maxVal) {return;}
        if (!isChargeBar) {return;}
        usingAbility = true;
    }
}
