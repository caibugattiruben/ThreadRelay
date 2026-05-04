/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package threadrelay;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author caibugatti.ruben
 */
public class FormGara extends javax.swing.JFrame{
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormGara.class.getName());
    
    GestoreGara g;
    JProgressBar[] lCorridori=new JProgressBar[4];
    JButton avvia,interrompi,riprendi,azzera;
    JComboBox combo;
    /**
     * Creates new form FormGara
     */
    public FormGara(GestoreGara g) {
        initComponents();
        
        this.g=g;
        
        JPanel panel=new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        
        gbc.gridx=0;
        gbc.weightx=1;
        gbc.fill = GridBagConstraints.BOTH;
        
        //---------------------------------------PANEL GARA---------------------------------------
        JPanel panelGara = new JPanel() {
            Image immagine = new ImageIcon("immagini/sfondoGara.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(immagine, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelGara.setOpaque(false);

        JPanel corridori = new JPanel();
        corridori.setOpaque(false);
        corridori.setLayout(new GridLayout(4, 1, 10, 10));

        for (int i = 0; i < 4; i++) {
            lCorridori[i] = new JProgressBar(0, 100);
            lCorridori[i].setStringPainted(true);
            lCorridori[i].setValue(0);
            corridori.add(lCorridori[i]);
        }

        panelGara.setLayout(new BorderLayout());
        panelGara.add(corridori);

        gbc.gridy = 0;
        gbc.weighty = 0.8;

        panel.add(panelGara, gbc);
        
        //---------------------------------------PANEL BOTTONI---------------------------------------
        JPanel panelBottoni=new JPanel(){
            Image immagine=new ImageIcon("immagini/erba.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(immagine, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelBottoni.setOpaque(false);
        
        panelBottoni.setLayout(new GridLayout(1,5,10,10));
        
        combo=new JComboBox();
        combo.addItem("Slow");
        combo.addItem("Medium");
        combo.addItem("Fast");
        
        
        avvia=new JButton("AVVIA");
        avvia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vel=String.valueOf(combo.getSelectedItem());
                
                if(vel.equals("Slow")){
                    avvio(100);
                }
                else if(vel.equals("Medium")){
                    avvio(60);
                }
                else{
                    avvio(20);
                }
                
                avvia.setEnabled(false);
                combo.setEnabled(false);
            }
        });
        
        
        interrompi=new JButton("INTERROMPI");
        interrompi.setEnabled(false);
        interrompi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interrompi.setEnabled(false);
                riprendi.setEnabled(true);
                azzera.setEnabled(true);
                g.sospensione(true);
            }
        });
        
        riprendi=new JButton("RIPRENDI");
        riprendi.setEnabled(false);
        riprendi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interrompi.setEnabled(true);
                riprendi.setEnabled(false);
                azzera.setEnabled(false);
                g.sospensione(false);
            }
        });
        
        azzera=new JButton("AZZERA");
        azzera.setEnabled(false);
        
        panelBottoni.add(combo);
        panelBottoni.add(avvia);
        panelBottoni.add(interrompi);
        panelBottoni.add(riprendi);
        panelBottoni.add(azzera);
        
        
        //AGGIUNGO PANEL PULSANTI AL PANEL PRINCIPALE
        gbc.gridy=1;
        gbc.weighty=0.2;
        
        panel.add(panelBottoni,gbc);
        
        //---------------------------------------AGGIUNGO TUTTO AL FORM---------------------------------------
        this.setLayout(new BorderLayout());
        this.add(panel);
        
    }

    public void aggNum(int m,int pos){
        lCorridori[pos].setValue(m);
    }
    
    public void avvio(int v){
        reset();
        
        interrompi.setEnabled(true);
        
        g.avvio(v);
    }
    
    public void reset(){
        for(int i=0;i<4;i++){
            lCorridori[i].setValue(0);
        }
    }
    
    public void pronto(){
        interrompi.setEnabled(false);
        riprendi.setEnabled(false);
        azzera.setEnabled(false);
        
        avvia.setEnabled(true);
        combo.setEnabled(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
