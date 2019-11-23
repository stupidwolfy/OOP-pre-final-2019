/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pre3;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author aon_c
 */
public class pre3 {

    private JFrame f1;
    private JPanel superp1, p1, p2, p3;
    private JLabel namel, pricel, typel;
    private JTextField name, price, pointer_field;
    private JComboBox type_box;
    private JButton prev, next, add, update, delete;
    private String[] type_item;
    private ArrayList<Book> book_table = new ArrayList<>();
    private int pointer = 0;
    private ActionListener al;
    private WindowAdapter wl;

    public void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            System.out.println(ex.toString());
        }

        type_item = new String[]{"General", "Computer", "Math&Sci", "Photo"};
        f1 = new JFrame();
        superp1 = new JPanel(new BorderLayout());
        p1 = new JPanel(new GridLayout(3, 2));
        p2 = new JPanel(new FlowLayout());
        p3 = new JPanel(new FlowLayout());

        al = new ActionListener() {
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
                } else if (ae.getSource().equals(update)) {
                    System.out.println("update");
                    updatefunc();
                } else if (ae.getSource().equals(delete)) {
                    System.out.println("delete");
                    deletefunc();
                }
            }
        };

        wl = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.out.println("Saving.....");
                save();
                System.out.println("Closing....");
                we.getWindow().dispose();
            }
        };

        namel = new JLabel("  Name");
        pricel = new JLabel("  Price");
        typel = new JLabel("  Type");

        name = new JTextField();
        price = new JTextField();
        pointer_field = new JTextField(5);
        pointer_field.setHorizontalAlignment(JTextField.CENTER);
        type_box = new JComboBox(type_item);

        prev = new JButton("<<<");
        next = new JButton(">>>");
        add = new JButton("Add");
        update = new JButton("Update");
        delete = new JButton("Delete");

        prev.addActionListener(al);
        next.addActionListener(al);
        add.addActionListener(al);
        update.addActionListener(al);
        delete.addActionListener(al);

        f1.addWindowListener(wl);

        p1.add(namel);
        p1.add(name);
        p1.add(pricel);
        p1.add(price);
        p1.add(typel);
        p1.add(type_box);

        p2.add(prev);
        p2.add(pointer_field);
        p2.add(next);

        p3.add(add);
        p3.add(update);
        p3.add(delete);

        superp1.add(p1, BorderLayout.NORTH);
        superp1.add(p2);
        superp1.add(p3, BorderLayout.SOUTH);

        f1.add(superp1);

        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
        //f1.setSize(400, 360);
        f1.pack();
        f1.setLocationRelativeTo(null);

        load();
        view();
    }

    public String[] getTypeItem() {
        return type_item;
    }

    public void addfunc() {
        new addBoook(this).init();
    }

    public void prevfunc() {
        if (pointer > 0) {
            pointer--;
            view();
        }
    }

    public void nextfunc() {
        if (pointer < book_table.size() - 1) {
            pointer++;
            view();
        }
    }

    public void updatefunc() {
        pointer = Integer.parseInt(pointer_field.getText());

        if (pointer <= book_table.size() - 1 || pointer >= 0) {
            book_table.get(pointer).setName(name.getText());
            book_table.get(pointer).setPrice(Double.parseDouble(price.getText()));
            book_table.get(pointer).setType(type_box.getSelectedItem().toString());
            JOptionPane.showMessageDialog(f1, "Done it.", "Update Command", JOptionPane.PLAIN_MESSAGE);
            view();
        } else {
            JOptionPane.showMessageDialog(f1, "Fail.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void deletefunc() {
        pointer = Integer.parseInt(pointer_field.getText());

        if (pointer <= book_table.size() - 1 && book_table.size() > 0) {
            book_table.remove(pointer);
            JOptionPane.showMessageDialog(f1, "Done it.", "Delete Command", JOptionPane.PLAIN_MESSAGE);
            pointer -= pointer == 0 ? 0 : 1;
            view();
        } else {
            JOptionPane.showMessageDialog(f1, "It already empty.", "Nope", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void save() {
        File f = new File("book2.dat");
        try {
            FileOutputStream fout = new FileOutputStream(f);
            ObjectOutputStream Oout = new ObjectOutputStream(fout);
            Oout.writeObject(book_table);
            Oout.close();
            fout.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        System.out.println("Update completed");
    }

    public void load() {
        File f = new File("book2.dat");
        try {
            FileInputStream fin = new FileInputStream(f);
            ObjectInputStream Oin = new ObjectInputStream(fin);
            Object readed = Oin.readObject();
            book_table = (ArrayList) readed;
            Oin.close();
            fin.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }

    }

    public void addBook(Book stu) {
        book_table.add(stu);
        pointer = book_table.size() - 1;
        view();
    }

    public void view() {
        if (pointer > book_table.size()) {
            pointer = book_table.size();
        }
        if (book_table.size() > 0) {
            Book std = book_table.get(pointer);
            name.setText(std.getName());
            price.setText(Double.toString(std.getPrice()));
            type_box.setSelectedItem(std.getType());
        } else {
            name.setText("");
            price.setText("");
            type_box.setSelectedIndex(0);

        }
        pointer_field.setText(Integer.toString(pointer));
    }

    public static void main(String[] args) {
        new pre3().init();
    }
}
