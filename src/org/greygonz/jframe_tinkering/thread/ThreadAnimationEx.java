/*
Source: https://zetcode.com/javagames/animation/
Author: GreyGonz
Date: 13/04/2022
*/

package org.greygonz.jframe_tinkering.thread;

import javax.swing.*;
import java.awt.*;

public class ThreadAnimationEx extends JFrame {

    public ThreadAnimationEx() {
        init();
    }

    private void init() {

        // Afegeix el component (JPanel) a dins el JFrame
        add(new ThreadBoard());

        setResizable(false);
        // Escala el tamany del JFrame al contingut
        pack();

        setTitle("JFrame Thread");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * https://stackoverflow.com/questions/22534356/java-awt-eventqueue-invokelater-explained
     * @param args
     */
    public static void main(String[] args) {

        // Genera un nou Thread per a que es pugui actualitzar el UI del JFrame sense que es causin
        // colisions amb altres tasques del Thread del JFrame.
        EventQueue.invokeLater(() -> {
            JFrame ex = new ThreadAnimationEx();
            ex.setVisible(true);
        });

    }

}
