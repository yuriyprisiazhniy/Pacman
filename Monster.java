/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;
import java.util.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
/**
 *
 * @author Yura
 */
public class Monster {
    private BufferedImage icon,icon1;

    public BufferedImage getIcon() {
        return icon;
    }

    public BufferedImage getIcon1() {
        return icon1;
    }
    private int x,y,step;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Monster( int step, int x, int y){
        this.x = x;
        this.y = y;
        this.step = step;
        try{
            icon = ImageIO.read(new File("C:\\Users\\Yura\\Documents\\NetBeansProjects\\Pacman\\icon\\monster.jpg"));
            icon1 = ImageIO.read(new File("C:\\Users\\Yura\\Documents\\NetBeansProjects\\Pacman\\icon\\monster.jpg"));
        }catch(IOException e){e.printStackTrace();}
        
    }
    private double distance(int x1, int y1, int x2, int y2){
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
    public  int changeCoordinates(int xP, int yP, int[][] m){
        if((x<xP) && (x+step<m.length*step) && (m[y/step][(x+step)/step]!=1)) x+=step;
        else if(x>xP && x-step>0 && m[y/step][(x-step)/step]!=1) x-=step;
        else if(y<yP && y+step<m.length*step && m[(y+step)/step][x/step]!=1) y+=step;
        else if(y>yP && y-step>0 && m[(y-step)/step][x/step]!=1) y-=step;
        return m[y/step][x/step];
    }
}
