/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prefinal;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author aon_c
 */
public class pre1 {

    public static void main(String[] args) {
        JFrame f1 = new JFrame();
        JButton b1 = new JButton("ADD");
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource().equals(b1)) {
                    System.out.println("BUTT");
                    minipre1 mp = new minipre1();
                    mp.init();
                }
            }
        };

        //Timer timer = new Timer();
        //Thread t = new Thread(timer);
        b1.addActionListener(al);

        f1.setLayout(new FlowLayout());
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLocationRelativeTo(null);
        f1.add(b1);
        f1.pack();
        f1.setVisible(true);

    }

}

