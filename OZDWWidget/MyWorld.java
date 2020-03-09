import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        StatBar stat = new StatBar(true, 320, 160, 5, 500, 30);
        StatBar stat2 = new StatBar(false, 400, 100, 5, 500, 250);
        addObject(stat, getWidth()/2, getHeight()/2);
        addObject(stat2, getWidth()/4 * 3, getHeight()/2);
    }
}
