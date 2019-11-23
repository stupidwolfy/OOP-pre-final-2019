/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pre1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author aon_c
 */
public class minipre1 implements Runnable {

    private JFrame f1;
    private JLabel l1, l2;
    private int num;
    private boolean is_tapped;

    public void init(int num) {
        this.num = num;
        f1 = new JFrame();
        l1 = new JLabel(new ImageIcon("src/pre1/icon2.png"));
        l2 = new JLabel(Integer.toString(num));
        Thread t1 = new Thread(this);
        MouseListener al = new MouseListener() {
            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (me.getSource().equals(l1)) {
                    System.out.println("Beep!!");
                    f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    f1.dispose();
                }
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {

            }

            @Override
            public void mouseClicked(MouseEvent me) {

            }
        };

        l1.setSize(l1.getPreferredSize());

        l1.addMouseListener(al);

        f1.setResizable(false);
        f1.setLayout(new FlowLayout());
        f1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f1.setLocation((int) (Math.random() * Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.9), (int) (Math.random() * Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.8));
        f1.add(l1);
        f1.add(l2);
        f1.pack();
        f1.setVisible(true);
        t1.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (f1.getLocation().x < -50 || f1.getLocation().x > Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 50
                        || f1.getLocation().y < -50 || f1.getLocation().y > Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50) {
                    f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    f1.dispose();
                }
                f1.setLocation(f1.getLocation().x + (int) Math.round(((Math.random()) * 10) - 5),
                        f1.getLocation().y + (int) Math.round(((Math.random()) * 10) - 5));
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }

    }
}
