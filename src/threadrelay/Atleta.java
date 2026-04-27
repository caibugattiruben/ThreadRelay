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
    private GestoreGara g;
    private bastoncino stecco;
    private int id;
    
    public Atleta(GestoreGara ge,int i,bastoncino b){
        this.g=ge;
        this.id=i;
        this.stecco=b;
    }
    
    @Override
    public void run(){
        while(true){
            synchronized(stecco){
                if(g.getCurrentThread()==id && stecco.stato()==false){
                    stecco.prendo();
                    for(int i=0;i<101;i++){
                        g.setMtThread(i,id);
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException ex) {
                        }
                    }
                    g.next();
                    stecco.lascio();
                    stecco.notifyAll();
                }
                else{
                    try {
                        stecco.wait();
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }
        
        
    }
}
