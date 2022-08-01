/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class NewInvoice extends JDialog{
    private JTextField customerName;
    private JTextField invoiceDate;
    private JLabel custName;
    private JLabel invDate;
    private JButton okBtn;
    private JButton cancelBtn;
    public NewInvoice(SalesInvoiceFrame frame)
    {
        customerName = new JTextField(20);
        invoiceDate  = new JTextField(20);
        
        custName = new JLabel("Customer Name");
        invDate = new JLabel("Date");
        
        okBtn = new JButton("OK");
        okBtn.setActionCommand("Okay Invoice");
        okBtn.addActionListener(frame.getsA());
        
        cancelBtn = new JButton("Cancel");
        cancelBtn.setActionCommand("Cancel Invoice");
        cancelBtn.addActionListener(frame.getsA());
        setLayout(new GridLayout(3,2));
        add(custName);
        add(customerName);
        add(invDate);
        add(invoiceDate);
        add(okBtn);
        add(cancelBtn);
        setModal(true);
        setTitle("New Invoice");
        pack();
    }

    public JTextField getCustomerName() {
        return customerName;
    }

    public JTextField getInvoiceDate() {
        return invoiceDate;
    }
    
    
}
