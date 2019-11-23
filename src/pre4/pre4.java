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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author aon_c
 */
public class pre4 {

    private JFrame f1;
    private JPanel p1, minip1, minip2, superminip2;
    private JLabel idl, fnamel, lnamel, gpal;
    private JTextField id, fname, lname, gpa;
    private JButton prev, next, add, exit;
    private ArrayList<Student> student_table = new ArrayList<>(); 
    private int pointer = 0;

    public void init() {
        f1 = new JFrame("(Student Application");
        p1 = new JPanel(new BorderLayout());
        minip1 = new JPanel(new GridLayout(4, 2));
        minip2 = new JPanel(new BorderLayout());
        superminip2 = new JPanel(new FlowLayout());
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource().equals(prev)) {
                    System.out.println("Prev");
                    prevfunc();
                } else if (ae.getSource().equals(next)) {
                    System.out.println("Next");
                    nextfunc();
                } else if (ae.getSource().equals(add)) {
                    System.out.println("Add");
                    addfunc();
                } else if (ae.getSource().equals(exit)) {
                    System.out.println("Exit");
                    save();
                    f1.dispose();
                }
            }
        };

        load();

        idl = new JLabel("Student ID:");
        fnamel = new JLabel("First Name:");
        lnamel = new JLabel("Last Name:");
        gpal = new JLabel("GPA:");

        id = new JTextField();
        fname = new JTextField();
        lname = new JTextField();
        gpa = new JTextField();

        id.setEditable(false);
        fname.setEditable(false);
        lname.setEditable(false);
        gpa.setEditable(false);

        prev = new JButton("Prev");
        next = new JButton("Next");
        add = new JButton("Add");
        exit = new JButton("Exit");

        prev.addActionListener(al);
        next.addActionListener(al);
        add.addActionListener(al);
        exit.addActionListener(al);

        minip1.add(idl);
        minip1.add(id);
        minip1.add(fnamel);
        minip1.add(fname);
        minip1.add(lnamel);
        minip1.add(lname);
        minip1.add(gpal);
        minip1.add(gpa);

        superminip2.add(prev);
        superminip2.add(next);
        superminip2.add(add);
        superminip2.add(exit);

        minip2.add(superminip2);

        p1.add(minip1, BorderLayout.NORTH);
        p1.add(minip2);

        f1.add(p1);

        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
        f1.setSize(400, 160);
        f1.setLocationRelativeTo(null);

        view();
    }

    public void addfunc() {
        new addStudent(this).init();
    }

    public void prevfunc() {
        if (pointer > 0) {
            pointer--;
            view();
        }
    }

    public void nextfunc() {
        if (pointer < student_table.size() - 1) {
            pointer++;
            view();
        }
    }

    public void save() {
        File f = new File("students.dat");
        try {
            FileOutputStream fout = new FileOutputStream(f);
            ObjectOutputStream Oout = new ObjectOutputStream(fout);
            Oout.writeObject(student_table);
            Oout.close();
            fout.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }

    public void load() {
        File f = new File("students.dat");
        try {
            FileInputStream fin = new FileInputStream(f);
            ObjectInputStream Oin = new ObjectInputStream(fin);
            Object readed = Oin.readObject();
            student_table = (ArrayList) readed;
            Oin.close();
            fin.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }

    }

    public void addStu(Student stu) {
        student_table.add(stu);
        pointer = student_table.size() - 1;
        view();
    }

    public void view() {
        Student std = student_table.get(pointer);
        id.setText(std.getStd_id());
        fname.setText(std.getFirstname());
        lname.setText(std.getLastname());
        gpa.setText(std.getGpa());
    }

    public static void main(String[] args) {
        new pre4().init();
    }
}
