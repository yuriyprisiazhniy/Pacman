
package pacman;
import java.util.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
public class Pacman{
    public List<BufferedImage> pictures;
    private int x,y;
    private MainPanel p;
    private int step;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }
    
    public int getStep() {
        return step;
    }
    
    public int getY() {
        return y;
    }
    
    public void movePacman(KeyEvent e){
        int shiftX = 0;
        int shiftY = 0;
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP: shiftX = 0; shiftY = -step; p.movePacman(shiftX, shiftY,pictures.get(2));break;
            case KeyEvent.VK_LEFT: shiftX = -step; shiftY = 0; p.movePacman(shiftX, shiftY,pictures.get(0));break;
            case KeyEvent.VK_RIGHT: shiftX = step; shiftY = 0; p.movePacman(shiftX, shiftY,pictures.get(1));break;
            case KeyEvent.VK_DOWN: shiftX = 0; shiftY = step; p.movePacman(shiftX, shiftY,pictures.get(3));break;
        }
    }
    public Pacman(MainPanel p){
        step = p.step;
        this.p = p;
        pictures = new ArrayList<>();
        for(int i=1; i<=5; i++)
            try{
                pictures.add(ImageIO.read(new File("C:\\Users\\Yura\\Documents\\NetBeansProjects\\Pacman\\icon\\"+i+".jpg")));
            }catch (IOException e){System.err.println("Не вдалось завантажити зображення"); e.printStackTrace();}
        
    }
    
    public static void main(String[] args) {
        MainFrame f = new MainFrame();
        f.launchFrame();
    }
}
