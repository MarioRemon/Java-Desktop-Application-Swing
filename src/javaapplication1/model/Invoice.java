/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1.model;

import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author DELL
 */
public class Invoice {
    private int no = 0;
    private Date date;
    private String customerName;
    private double invoiceTotal;
    private ArrayList<Line> lines ;
    public Invoice(int no, Date date, String customerName)
    {
        this.no = no;
        this.date = date;
        this.customerName = customerName;
       lines  = new ArrayList<>();
    }
    public Invoice(int no, String date, String customerName)
    {
        this.no = no;
        //this.date = date;
        this.customerName = customerName;
       lines  = new ArrayList<>();
    }

    public  int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public double getInvoiceTotal() {
        double total = 0.0;
        for(Line l : lines)
        {
          total += (l.getLineTotal());
        }
        this.invoiceTotal = total;
        return invoiceTotal;
    }
    public ArrayList<Line> getLines()
    {
        if(lines == null)
        {
            lines = new ArrayList<>();
        }
        return lines;
    }
    public void setLines(ArrayList<Line> lines)
    {
        this.lines = lines;
    }
       @Override
    public String toString() {
        return  (no + "," + date + "," + customerName + "," + invoiceTotal);
    }

}
