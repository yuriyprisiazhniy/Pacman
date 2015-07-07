/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

/**
 *
 * @author Yura
 */
public class MoveMonster extends Thread{
    MainPanel p;
    boolean loose;
    public MoveMonster(MainPanel p){
        this.p = p;
        loose = false;
    }
    @Override
    public void run(){
        p.runMonsters();
        
    }
}
