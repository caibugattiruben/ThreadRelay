package threadrelay;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author caibugatti.ruben
 */
public interface Subject {
    void addObserverGrafica(Observer o);
    void removeObserverGrafica(Observer o);
    void notifyObservers(int valore,int pos);
    void notifyNext();
    void ready();
    void sospeso();
    void sospesoFine();
}
