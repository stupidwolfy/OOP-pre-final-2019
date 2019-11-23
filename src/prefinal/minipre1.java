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
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author aon_c
 */
public class minipre1 {

    public void init() {
        JFrame f1 = new JFrame();
        JLabel l1 = new JLabel(new ImageIcon("/src/pre1/icon.bmp"));
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource().equals(l1)) {
                    System.out.println("Poke");
                }
            }
        };

        l1.setSize(l1.getPreferredSize());

        //Timer timer = new Timer();
        //Thread t = new Thread(timer);
        //l1.addActionListener(al);
        f1.setLayout(new FlowLayout());
        //f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLocation((int) (Math.random() * Toolkit.getDefaultToolkit().getScreenSize().getWidth()), (int) (Math.random() * Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
        f1.add(l1);
        f1.pack();
        f1.setVisible(true);
    }
}
