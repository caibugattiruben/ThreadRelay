/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

/**
 *
 * @author caibugatti.ruben
 */
public class Atleta extends Thread{
    private int mt=0;
    private GestoreGara g;
    
    public Atleta(GestoreGara ge){
        this.g=ge;
    }
    
    @Override
    public void run(){
        for(int i=0;i<101;i++){
            mt = mt + 1;
            g.setMtThread(mt);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
        
    }
}
