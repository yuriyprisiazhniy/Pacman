/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

/**
 *
 * @author Yura
 */
public class MainPanel extends JPanel{
    private int x;
    private int y;
    private int[][] matrix;
    public  int[][] getMatrix() {
        return matrix;
    }
    private BufferedImage defaultImage;
    private BufferedImage image, loose;
    private int height,width;
    public static int score;
    public final int step;
    private Monster[] monsters ;

    public  Monster[] getMonsters() {
        return monsters;
    }
    boolean isLoose;

    public  void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void setIsLoose(boolean isLoose) {
        this.isLoose = isLoose;
    }
    private static int sign(int a){
        if(a>=0) return 0;
        else return -1;
    }
    private static int multiplier(int a){
        if(Math.abs(a)>0) return 2;
        else return 1;
    }
    private void generateMatrix(String path){
        File f =new File(path);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)))){
            for(int i=0; i<matrix.length; i++)
            {
                String s = br.readLine();
                for(int j=0; j<matrix[0].length; j++)
                    matrix[i][j] = Integer.parseInt(s.split(" ")[j]);
            }
        }catch(IOException e){e.printStackTrace();}
        matrix[x/step][y/step] = -1;
    }
    public final void beginPosition(){
        for(int i=0; i<matrix.length; i++)
            for(int j=0; j<matrix[0].length; j++)
                if(matrix[i][j] == 0 || matrix[i][j] == -1){
                    x = j*step;
                    y = i*step;
                    return;
                }
                    
    }
    public void moveMonster(int x, int y){
        int oldValue;
        int oldX,oldY;
        for(int i=0; i<monsters.length;i++)
        {
            
            oldX = monsters[i].getX();
            oldY = monsters[i].getY();
            oldValue = monsters[i].changeCoordinates(x, y, matrix);
           
            matrix[monsters[i].getY()/step][monsters[i].getX()/step] = 2;
            matrix[oldY/step][oldX/step] = oldValue;
            //matrix[oldY/step][oldX/step] = -1;
            if(monsters[i].getX() == x && monsters[i].getY() == y)
                isLoose = true;
            this.repaint();
        }
    }
    public void runMonsters(){
        while(!isLoose)
        {
            moveMonster(x,y);
            try{
        Thread.sleep(300);
        }catch(InterruptedException e){e.printStackTrace();}
        
        }
        
    }
    public MainPanel(int height,int width, int step){
        score = 0;
        isLoose = false;
        monsters = new Monster[2];
        this.height = height;
        this.width = width;
        this.step = step;
        for(int i=0; i< 2; i++)
            monsters[i] = new Monster(step,width-step,(height-step)*i);
        matrix = new int[height/step][width/step];
        generateMatrix("C:\\Users\\Yura\\Documents\\NetBeansProjects\\Pacman\\icon\\matrix.txt");
        beginPosition();
        try{
            image = ImageIO.read(new File("C:\\Users\\Yura\\Documents\\NetBeansProjects\\Pacman\\icon\\5.jpg"));
            defaultImage = ImageIO.read(new File("C:\\Users\\Yura\\Documents\\NetBeansProjects\\Pacman\\icon\\5.jpg"));
            loose = ImageIO.read(new File("C:\\Users\\Yura\\Documents\\NetBeansProjects\\Pacman\\icon\\loose.jpg"));
        }catch(IOException e){e.printStackTrace();}
        
            //moveMonster(x,y);//!!!!!!!!!!!!!!
    }
    public void movePacman(int x, int y, BufferedImage im){
        image = im;
        Rectangle rect = new Rectangle(this.x+step*sign(x),this.y+step*sign(y),this.x+step*sign(x)+multiplier(x)*step,this.x+step*sign(x)+multiplier(y)*step);
        if(this.x+x>=0 && this.x+x<=width-step && this.y+y>=0 && this.y+y<=height-step && (matrix[(this.y+y)/step][(this.x+x)/step] != 1))
        {
            
            if(matrix[(this.y+y)/step][(this.x+x)/step] == 0)
            {
                score++;
                matrix[(this.y+y)/step][(this.x+x)/step] = -1;
            }
            this.x += x;
            this.y += y;
            this.repaint(rect);
        }
        //moveMonster(this.x,this.y);
        
    }

    @Override
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      if(isLoose){
          g.drawImage(loose, 0, 0, this);
          return;
      }
      for(int i=0; i<matrix.length; i++)
          for(int j=0; j<matrix[0].length; j++)
          {
              if(matrix[i][j] == 1)
              {
                g.setColor(Color.blue);
                g.fillRect(j*step, i*step, step, step);
              }
              else if (matrix[i][j] == 0){
                 g.setColor(Color.white);
                 g.fillOval(j*step+step/3, i*step+step/3, step/4, step/4);
              }
              
              
          }
      g.drawImage(image, x, y, this);
      for(int i=0; i<monsters.length; i++)
        g.drawImage(monsters[i].getIcon(), monsters[i].getX(), monsters[i].getY(), this);
    }
}
