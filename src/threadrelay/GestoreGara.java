/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

/**
 *
 * @author caibugatti.ruben
 */
public class GestoreGara {
    FormGara gara=new FormGara(this);

    private int currentThread;
    
    public void show(){
        gara.setVisible(true);
    }
    
    public void avvio(){

    }
    
    public void startThread(){

    }
    
    public void setMtThread(int m){
        gara.aggNum(currentThread,m);
    }
}
