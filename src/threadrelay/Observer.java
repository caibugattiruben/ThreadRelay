package threadrelay;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author caibugatti.ruben
 */
public interface Observer {
    
    void update(int valore,int pos);
    void next();
    void ready();
}
