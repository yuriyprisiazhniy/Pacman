/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Yura
 */
public class MainFrame extends KeyAdapter{
    private JFrame mainFrame;
    private MainPanel panel;
    private Pacman pacman;
    public final int width;
    public final int height;
    private MoveMonster mm;
    public MainFrame(){
        width = 400;
        height = 400;
        mainFrame = new JFrame("Pacman");
        panel = new MainPanel(height,width,20);//20 = step = розмір іконки
        panel.setPreferredSize(new Dimension(width,height));
        pacman = new Pacman(panel);
        mm = new MoveMonster(panel);
    }
    public void launchFrame(){
        mainFrame.addKeyListener(this);
        mainFrame.setResizable(false);
        
        panel.setBackground(Color.black);
        mainFrame.setContentPane(panel);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mm.start();
    }
    @Override
    public void keyPressed(KeyEvent e){
            if(e.isActionKey())
                pacman.movePacman(e);
        }
    }

