/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threadrelay;

/**
 *
 * @author ruben
 */
public class bastoncino {
    
    private boolean preso=false;
    
    public void lascio(){
        preso=false;
    }
    public void prendo(){
        preso=true;
    }
    
    public boolean stato(){
        return preso;
    }
    
}
