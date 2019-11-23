/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pre4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author aon_c
 */
public class addStudent {

    private JFrame f1;
    JPanel p1, minip1, minip2, superminip2;
    private JLabel idl, fnamel, lnamel, gpal;
    private JTextField id, fname, lname, gpa;
    private JButton ok;
    private pre4 pre;

    public addStudent(pre4 pre) {
        this.pre = pre;
    }

    public void init() {
        f1 = new JFrame("(Student Application");
        p1 = new JPanel(new BorderLayout());
        minip1 = new JPanel(new GridLayout(4, 2));
        minip2 = new JPanel(new BorderLayout());
        superminip2 = new JPanel(new FlowLayout());
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource().equals(ok)) {
                    System.out.println("ok");
                    okfunc();
                    f1.dispose();
                }
            }
        };

        idl = new JLabel("Student ID:");
        fnamel = new JLabel("First Name:");
        lnamel = new JLabel("Last Name:");
        gpal = new JLabel("GPA:");

        id = new JTextField();
        fname = new JTextField();
        lname = new JTextField();
        gpa = new JTextField();

        ok = new JButton("OK");

        ok.addActionListener(al);

        minip1.add(idl);
        minip1.add(id);
        minip1.add(fnamel);
        minip1.add(fname);
        minip1.add(lnamel);
        minip1.add(lname);
        minip1.add(gpal);
        minip1.add(gpa);

        superminip2.add(ok);
        minip2.add(superminip2);

        p1.add(minip1, BorderLayout.NORTH);
        p1.add(minip2);
        f1.add(p1);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f1.setVisible(true);
        f1.setSize(400, 160);
        f1.setLocationRelativeTo(null);
    }

    public void okfunc() {
        Student std = new Student();
        std.setStd_id(id.getText());
        std.setFirstname(fname.getText());
        std.setLastname(lname.getText());
        std.setGpa(gpa.getText());
        
        pre.addStu(std);
    }
}
