/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package threadrelay;

import java.awt.BorderLayout;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;

/**
 *
 * @author caibugatti.ruben
 */
public class FormGara extends javax.swing.JFrame{
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormGara.class.getName());
    
    GestoreGara g;
    JProgressBar[] lCorridori=new JProgressBar[4];
    JButton avvia,interrompi,riprendi,azzera;
    JComboBox<String> combo;
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
        corridori.setLayout(new GridLayout(4, 1, 14, 14));
        corridori.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        for (int i = 0; i < 4; i++) {
            lCorridori[i] = new RunnerProgressBar();
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
        panelBottoni.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        combo=new JComboBox<>();
        combo.addItem("Slow");
        combo.addItem("Medium");
        combo.addItem("Fast");
        styleCombo(combo);
        
        
        avvia=createRoundedButton("AVVIA");
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
        
        
        interrompi=createRoundedButton("INTERROMPI");
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
        
        riprendi=createRoundedButton("RIPRENDI");
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
        
        azzera=createRoundedButton("AZZERA");
        azzera.setEnabled(false);
        azzera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                interrompi.setEnabled(false);
                riprendi.setEnabled(false);
                azzera.setEnabled(false);
                
                avvia.setEnabled(true);
                combo.setEnabled(true);
                
                g.fine();
                reset();
            }
        });
        
        panelBottoni.add(wrapControl(combo));
        panelBottoni.add(wrapControl(avvia));
        panelBottoni.add(wrapControl(interrompi));
        panelBottoni.add(wrapControl(riprendi));
        panelBottoni.add(wrapControl(azzera));
        
        
        //AGGIUNGO PANEL PULSANTI AL PANEL PRINCIPALE
        gbc.gridy=1;
        gbc.weighty=0.2;
        
        panel.add(panelBottoni,gbc);
        
        //---------------------------------------AGGIUNGO TUTTO AL FORM---------------------------------------
        this.setLayout(new BorderLayout());
        this.add(panel);
        
    }
    
    private JPanel wrapControl(JComponent control) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 8));
        wrapper.setOpaque(false);
        wrapper.add(control);
        return wrapper;
    }

    private JButton createRoundedButton(String testo) {
        RoundedButton bottone = new RoundedButton(testo);
        bottone.setPreferredSize(new Dimension(128, 38));
        bottone.setFont(new Font("Segoe UI", Font.BOLD, 12));
        bottone.setForeground(Color.WHITE);
        bottone.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return bottone;
    }
    
    private void styleCombo(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Segoe UI", Font.BOLD, 12));
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(new LineBorder(new Color(138, 153, 166), 1, true));
        comboBox.setPreferredSize(new Dimension(110, 34));
        comboBox.setFocusable(false);
    }

    private static class RoundedButton extends JButton {
        private static final Color BASE = new Color(94, 114, 131);
        private static final Color HOVER = new Color(104, 124, 141);
        private static final Color DISABLED = new Color(140, 151, 160);
        private boolean hover;

        RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setMargin(new java.awt.Insets(0, 14, 0, 14));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hover = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            Color fill;
            if (!isEnabled()) {
                fill = DISABLED;
            } else if (hover) {
                fill = HOVER;
            } else {
                fill = BASE;
            }
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
            g2.dispose();

            super.paintComponent(g);
        }
    }

    private static class RunnerProgressBar extends JProgressBar {
        RunnerProgressBar() {
            super(0, 100);
            setOpaque(false);
            setStringPainted(false);
            setBorder(BorderFactory.createEmptyBorder());
            setFont(new Font("Segoe UI", Font.BOLD, 14));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            int textArea = 62;
            int laneStart = 8;
            int laneEnd = Math.max(laneStart + 1, width - textArea - 8);
            int laneWidth = laneEnd - laneStart;

            double ratio = (getValue() - getMinimum()) / (double) (getMaximum() - getMinimum());
            ratio = Math.max(0.0, Math.min(1.0, ratio));

            int baseSize = Math.max(14, height - 14);
            int sidePadding = Math.max(10, baseSize / 2 + 10);
            int safeStart = laneStart + sidePadding;
            int safeEnd = Math.max(safeStart, laneEnd - sidePadding);
            int safeWidth = safeEnd - safeStart;

            int runnerX = safeStart + (int) Math.round(ratio * safeWidth);
            int baseline = height / 2 + 3;
            drawRunner(g2, runnerX, baseline, baseSize);

            String testo = getValue() + "%";
            g2.setColor(Color.WHITE);
            g2.setFont(getFont().deriveFont(Font.BOLD, 14f));
            java.awt.FontMetrics fm = g2.getFontMetrics();
            int textX = width - fm.stringWidth(testo) - 10;
            int textY = (height - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(testo, textX, textY);

            g2.dispose();
        }

        private void drawRunner(Graphics2D g2, int x, int baseline, int size) {
            float stroke = Math.max(2.0f, size / 10.0f);
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            int headRadius = Math.max(3, size / 6);
            int headCenterY = baseline - size / 2;
            g2.drawOval(x - headRadius, headCenterY - headRadius, headRadius * 2, headRadius * 2);

            int bodyTop = headCenterY + headRadius;
            int bodyBottom = baseline - 2;
            g2.drawLine(x, bodyTop, x, bodyBottom);
            g2.drawLine(x, bodyTop + 2, x + headRadius + 4, bodyTop + 9);
            g2.drawLine(x, bodyTop + 4, x - headRadius - 4, bodyTop + 10);
            g2.drawLine(x, bodyBottom, x + headRadius + 6, baseline + 5);
            g2.drawLine(x, bodyBottom, x - headRadius - 5, baseline + 4);
        }
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
