/*
 * LocationGenerator.java
 *
 * Created on October 31, 2002, 7:00 PM
 *
 * @author E. Rajasekar
 */

package app.astrosoft.ui.util;

import java.awt.Point;

public class LocationGenerator {

    private Point origin;
    
    private int row = 0;
    private int col = 0;
    
    private int xinc;
    
    private int yinc;
    
    public LocationGenerator(int x, int y, int hgap, int vgap) {
        
        origin = new Point(x,y);
        this.xinc = hgap;
        this.yinc = vgap;
    }

    public LocationGenerator(Point origin, int hgap, int vgap){
     
        this(origin.x, origin.y, hgap, vgap);
    }
    
    public void setOrigin(int x, int y){
        origin = new Point(x,y);
        row = 0;
        col = 0;
    }
    
    public void setOrigin(Point origin){
        setOrigin(origin.x, origin.y);
    }
    
    public void setHGap(int hgap){
        this.xinc = hgap;
    }
    
    public void setVGap(int vgap){
        this.yinc = vgap;
    }
    
    public Point getNextRow() {
        col = 0;
        return new Point(origin.x + (xinc * col),origin.y + (yinc * row++));
    }
    
    public Point getNextColumn() {
        return new Point(origin.x + (xinc * ++col),origin.y + (yinc * (row - 1)));
    }
    
    public Point getLocation(int row, int col){
        return new Point(origin.x + (xinc * (col - 1)),origin.y + (yinc * (row - 1)));
    }
    
    public String toString(){
        return "[ " + origin + " , ( " + row + " , " + col + " ) ,"+ xinc + " ," + yinc + " ]";
    }
}
