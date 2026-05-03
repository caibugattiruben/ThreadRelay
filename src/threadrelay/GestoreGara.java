/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

import java.util.ArrayList;

/**
 *
 * @author caibugatti.ruben
 */
public class GestoreGara  implements Observer{
    private ArrayList<Atleta> atletiAttivi = new ArrayList<>();
    FormGara gara=new FormGara(this);
    bastoncino stecco=new bastoncino();
    
    private int currentThread=0;
    
    public void show(){
        gara.setVisible(true);
    }
    
    public void start(){
        
    }
    public void avvio(int vel){
        for (Atleta a : atletiAttivi) {
            a.interrupt();
        }
        atletiAttivi.clear();
        
        stecco.lascio();
        this.currentThread = 0;

        for(int i = 0; i < 4; i++){
            Atleta a = new Atleta(this, i, stecco, vel);
            a.addObserverGrafica(this);
            atletiAttivi.add(a);
            a.start();
        }
    }
    
    public int getCurrentThread(){
        return currentThread;
    }
    
    public void nextGara(){
        this.currentThread=currentThread+1;
    }
    
    public void setMtThread(int m,int id){
        gara.aggNum(m,id);
    }
    
    @Override
    public void update(int valore,int pos) {
        setMtThread(valore,pos);
    }
    
    @Override
    public void next(){
        nextGara();
    }
    
    @Override
    public void ready(){
        gara.pronto();
    }
    
    @Override
    public void sospenzione(boolean tipo){
        if(tipo==true){
            atletiAttivi.get(currentThread).sospeso();
        }
        else{
            atletiAttivi.get(currentThread).sospesoFine();
        }
    }
    
}
