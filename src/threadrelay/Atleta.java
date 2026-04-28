/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author caibugatti.ruben
 */
public class Atleta extends Thread implements Subject{
    private GestoreGara g;
    private bastoncino stecco;
    private int id;
    private Observer obsGrafica;
    
    public Atleta(GestoreGara ge,int i,bastoncino b){
        this.g=ge;
        this.id=i;
        this.stecco=b;
    }
    
    @Override
    public void run(){
        boolean corro=true,presoStecco=false;
        while(corro){
            synchronized(stecco){
                if(g.getCurrentThread()==id && stecco.stato()==false){
                    stecco.prendo();
                    presoStecco=true;
                    for(int i=0;i<90;i++){
                        notifyObservers(i,id);
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
            
            if(presoStecco==true){
                for(int i=90;i<101;i++){
                    notifyObservers(i,id);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                    }
                }
                removeObserverGrafica(g);
                corro=false;
            }
            
        }
        
    }
    
    @Override
    public void addObserverGrafica(Observer o){
        obsGrafica=o;
    };
    
    @Override
    public void removeObserverGrafica(Observer o){
        obsGrafica=null;
    };
    
    @Override
    public void notifyObservers(int valore,int pos){
        Observer copia = obsGrafica;
        
        copia.update(valore,pos);
    };
    
}
