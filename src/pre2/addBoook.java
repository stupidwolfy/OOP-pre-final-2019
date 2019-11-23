/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pre2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pre4.Student;
import pre4.pre4;

/**
 *
 * @author aon_c
 */
public class addBoook {

    private JFrame f1;
    JPanel minip1, minip2;
    private JLabel namel, pricel, typel;
    private JTextField name, price, pointer_field;
    private JComboBox type_box;
    private String[] type_item;
    private JButton insert;
    private pre2 pre;

    public addBoook(pre2 pre) {
        this.pre = pre;
    }

    public void init() {
        type_item = pre.getTypeItem();

        f1 = new JFrame("");
        minip1 = new JPanel(new GridLayout(3, 2));
        minip2 = new JPanel(new FlowLayout());
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource().equals(insert)) {
                    System.out.println("insrt");
                    insertfunc();
                }
            }
        };

        namel = new JLabel("Name");
        pricel = new JLabel("Price");
        typel = new JLabel("Type");

        name = new JTextField();
        price = new JTextField();
        type_box = new JComboBox(type_item);

        insert = new JButton("Insert");

        insert.addActionListener(al);

        minip1.add(namel);
        minip1.add(name);
        minip1.add(pricel);
        minip1.add(price);
        minip1.add(typel);
        minip1.add(type_box);

        minip2.add(insert);

        f1.add(minip1, BorderLayout.CENTER);
        f1.add(minip2, BorderLayout.SOUTH);
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        f1.setVisible(true);
        //f1.setSize(400, 160);\
        f1.pack();
        f1.setLocationRelativeTo(null);
    }

    public void insertfunc() {
        try {
            Book std = new Book();
            std.setName(name.getText());
            std.setPrice(Integer.parseInt(price.getText()));
            std.setType(type_box.getSelectedItem().toString());
            pre.addBook(std);
            f1.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(f1, "Please insert correct price.", "Worng price!!", JOptionPane.WARNING_MESSAGE);
            System.out.println("Worng price!!");
        }

    }
}
