/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class NewLine extends JDialog {
    private JTextField itemName ;
    private JTextField itemPrice;
    private JTextField itemCount;
    private JLabel itName;
    private JLabel itPrice;
    private JLabel itCount;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public NewLine(SalesInvoiceFrame frame)
    {
        itName = new JLabel("Item Name");
        itemName = new JTextField(20);
        
        itPrice = new JLabel("Item Price");
        itemPrice = new JTextField(20);
        
        itCount = new JLabel("Item Count");
        itemCount = new JTextField(20);

        okBtn = new JButton("OK");
        okBtn.setActionCommand("Okay Line");
        okBtn.addActionListener(frame.getsA());

        cancelBtn = new JButton("Cancel");
        cancelBtn.setActionCommand("Cancel Line");
        cancelBtn.addActionListener(frame.getsA());
        setLayout(new GridLayout(4,2));
        add(itName);
        add(itemName);
        add(itCount);
        add(itemCount);
        add(itPrice);
        add(itemPrice);
        add(okBtn);
        add(cancelBtn);
        setModal(true);
        setTitle("New Line");
        pack();
    }

    public JTextField getItemName() {
        return itemName;
    }

    public JTextField getItemPrice() {
        return itemPrice;
    }

    public JTextField getItemCount() {
        return itemCount;
    }
    
}
