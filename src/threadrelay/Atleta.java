/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;


/**
 *
 * @author caibugatti.ruben
 */
public class Atleta extends Thread implements Subject{
    private GestoreGara g;
    private bastoncino stecco;
    private int id;
    private Observer obsGrafica;
    private int vel;
    private boolean sospeso=false;
    
    public Atleta(GestoreGara ge,int i,bastoncino b,int vel){
        this.g=ge;
        this.id=i;
        this.stecco=b;
        this.vel=vel;
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
                        synchronized(this){
                            while(sospeso){
                                try {
                                    this.wait();
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                    return;
                                }
                            }
                        }
                        notifyObservers(i,id);
                        try {
                            Thread.sleep(vel);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    notifyNext();          
                    stecco.lascio();   
                    stecco.notifyAll(); 
                }
                else{
                    try {
                        stecco.wait();
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            }

            if(presoStecco==true){
                synchronized(this){
                   for(int i=90;i<101;i++){
                        while(sospeso){
                            try {
                                this.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                                return;
                            }
                        }
                        notifyObservers(i,id);
                        try {
                            Thread.sleep(vel);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }     
                }
                

                if(id==3){
                    ready();  
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
        if (copia != null) {
            copia.update(valore,pos);
        }
    };
    
    @Override
    public void notifyNext(){
        Observer copia = obsGrafica;
        if (copia != null) {
            copia.next();
        }
    };
    
    @Override
    public void ready(){
        Observer copia = obsGrafica;
        if (copia != null) {
            copia.ready();
        }
    };
    
    @Override
    public void sospeso(){
        this.sospeso=true;
    };
    
    @Override
    public void sospesoFine(){
        synchronized (this) { 
            this.sospeso = false;
            this.notifyAll(); 
        }
    };

    @Override
    public void fine(){
        this.interrupt();
    };
   
}
