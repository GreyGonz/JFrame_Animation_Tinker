/*
Source: https://zetcode.com/javagames/animation/
Author: GreyGonz
Date: 13/04/2022
*/

package org.greygonz.jframe_tinkering.thread;

import javax.swing.*;
import java.awt.*;

/**
 * Genera el JPanel que mostrara els frames renderitzats
 */
public class ThreadBoard extends JPanel implements Runnable {

    private final int B_WIDTH = 200;
    private final int B_HEIGHT = 200;
    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 25;

    private Image image_frame;
    private Thread animator;
    private int x, y;

    public ThreadBoard() {
        init();
    }

    /**
     * Carrega d'una imatge inicial
     */
    private void loadImage() {

        ImageIcon ii = new ImageIcon();
        image_frame = ii.getImage();

    }

    /**
     * Inicialitza el JPanel
     */
    private void init() {

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        loadImage();

        x = INITIAL_X;
        y = INITIAL_Y;

    }

    /**
     * Aquesta funció es crida un cop creat el JPanel, es sol utilitzar per a la configuració del mateix
     */
    @Override
    public void addNotify() {

        super.addNotify();

        // Crea el Thread encarregat d'actualitzar els frames
        animator = new Thread(this);
        animator.start();

    }

    /**
     * Dibuixa el nou frame al JPanel
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        drawImageFrame(g);

    }

    /**
     * https://stackoverflow.com/questions/20804198/what-does-toolkit-getdefaulttoolkit-sync-mean
     * Dibuixa el frame i sincronitza els gràfics
     * @param g
     */
    private void drawImageFrame(Graphics g) {

        g.drawImage(image_frame, x, y, this);
        Toolkit.getDefaultToolkit().sync();

    }

    /**
     * Es crida per cada iteració, aquí haurem d'afegir el codi que actualitza l'estat dels frames
     */
    private void cycle() {

        System.out.println("Frame updated!");

    }

    /**
     * Es crida una sola vegada dins el Thread, el while loop demana els nous frames i s'encarrega
     * de sincronitzar els updates fent ús del System.currentTimeMillis(); i el DELAY
     */
    @Override
    public void run() {

        long beforeTime;
        long timeDiff;
        long sleep;

        beforeTime = System.currentTimeMillis();

        while(true) {

            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {

                String msg = String.format("Thread interrupted: %s", e.getMessage());

                JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);

            }

            beforeTime = System.currentTimeMillis();

        }

    }

}
