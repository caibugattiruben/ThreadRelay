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
    bastoncino stecco=new bastoncino();
    
    private int currentThread=0;
    
    public void show(){
        gara.setVisible(true);
    }
    
    public void avvio(){
        for(int i=0;i<4;i++){
            Atleta a=new Atleta(this,i,stecco);
            a.start();
        }
    }
    
    public int getCurrentThread(){
        return currentThread;
    }
    
    public void next(){
        this.currentThread=currentThread+1;
    }
    
    public void setMtThread(int m,int id){
        gara.aggNum(id,m);
    }
}
