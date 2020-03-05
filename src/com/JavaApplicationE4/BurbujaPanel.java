/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.JavaApplicationE4;

import com.JavaApplicationE4.logica.BoxNumber;
import com.JavaApplicationE4.logica.Numero;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author jbermudezb
 */
public class BurbujaPanel extends JPanel {

    private Dimension dimension = new Dimension(320, 128);
    private BoxNumber[] bNumber;

    private long inicio = 0;
    private long fin = 0;
    static double tiempo = 0;

    /**
     * Constructor de clase
     */
    public BurbujaPanel() {
        setSize(dimension);
        setVisible(true);
    }

    static double getTiempo() {
        return tiempo;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255));
        g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        //pinta numeros y casillas
        if (bNumber != null) {
            for (BoxNumber b : bNumber) {
                b.draw(g2);
            }
        }

    }

    /**
     * Genera 5 numeros al azar y asigna a casillas posicion las casillas en el
     * panel
     */
    public void generar(ArrayList lista) {
        bNumber = new BoxNumber[lista.size()];
        //Random rn = new Random();
        for (int i = 0; i < lista.size(); i++) {
            bNumber[i] = new BoxNumber();
            bNumber[i].x = 10 + bNumber[i].WIDTH * i;
            bNumber[i].y = getHeight() / 2 - bNumber[i].HEIGHT / 2;
            //int num = rn.nextInt(max - min + 1) + min;
            Numero n = (Numero) lista.get(i);
            bNumber[i].setNumber(String.valueOf(n.getNumero()));
            //System.out.println("Num "+n.getNumero()+" bNumber "+bNumber[0].getValue());
        }
        repaint();
    }

    /**
     * Comando para ordenar el array de numeros con el metodo de la burbuja
     */
    public boolean ordenar() {
        if (bNumber != null) {
            new MetodoBurbuja().execute();//inicia worker
            return true;
        } else {
            return false;
        }
    }

    /**
     * Clase
     */
    public class MetodoBurbuja extends SwingWorker<Void, Void> {

        private int SPEED = 11; //velocidad de animacion (milisegundos) 

        @Override
        protected Void doInBackground() throws Exception {

            int i, j;
            BoxNumber aux;
            inicio = System.currentTimeMillis();
            Thread.sleep(2000);
            
            Principal.alertaAma_act();
            Principal.txtNum.setEnabled(false);
            Principal.btnIn.setEnabled(false);
            Principal.btnGu.setEnabled(false);
            //System.out.println("Iniciamos MetodoBurbuja ...");
            
            for (i = 0; i < bNumber.length - 1; i++) {
                for (j = 0; j < bNumber.length - i - 1; j++) {
                    if (bNumber[j + 1].getValue() < bNumber[j].getValue()) {
                        aux = bNumber[j + 1];
                        //animar movimiento
                        girar(j, j + 1);
                        bNumber[j + 1] = bNumber[j];
                        bNumber[j] = aux;
                    }
                }
            }
            Principal.alertaAma_inact();
            //System.out.println("Finalizamos MetodoBurbuja ...");
            
            Principal.alertaVer_act();
            fin = System.currentTimeMillis();
            
            Principal.lbl_dur.setVisible(true);
            Principal.lbl_dur.setText( String.valueOf( (double) ((fin - inicio) / 1000)) );
            
            Principal.btnOr.setEnabled(false);
            Principal.txtNum.setEnabled(true);
            Principal.btnIn.setEnabled(true);
            Principal.btnGu.setEnabled(true);
            
            return null;
        }

        /**
         *
         */
        private void girar(int a, int b) {
            //movmiento vertical
            for (int i = 0; i < bNumber[0].HEIGHT; i++) {
                bNumber[a].y -= 1;
                bNumber[b].y += 1;
                try {
                    Thread.sleep(SPEED);
                } catch (InterruptedException e) {
                }
                repaint();
            }

            //vomiento horizontal
            for (int i = 0; i < bNumber[0].WIDTH; i++) {
                bNumber[a].x += 1;
                bNumber[b].x -= 1;
                try {
                    Thread.sleep(SPEED);
                } catch (InterruptedException e) {
                }
                repaint();
            }

            //movimiento vertical
            for (int i = 0; i < bNumber[0].HEIGHT; i++) {
                bNumber[a].y += 1;
                bNumber[b].y -= 1;
                try {
                    Thread.sleep(SPEED);
                } catch (InterruptedException e) {
                }
                repaint();
            }
        }

    }// fin clace MetodoBurbuja

}
