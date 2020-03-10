import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class test extends Actor
{
    /**
     * Act - do whatever the test wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
                                            
    private int delay = 3;
    private int width;
    private int height;
    private int index;
    public test()
    {
        width = 280;
        height = 280;
        GreenfootImage img = glowFrames[0];
        img.scale(width, height);
        setImage(img);
        index = 0;
    }
    public void act() 
    {
        index += delay / 3;
        if (index > 89) {index = 0;}
        GreenfootImage img = glowFrames[index];
        img.scale(width, height);
        img.setTransparency(200);
        setImage(img);
        if (delay == 3) {
            delay = 0;
        } else {
            delay++;
        }
    }    
}
