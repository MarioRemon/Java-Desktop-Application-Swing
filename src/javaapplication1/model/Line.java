/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1.model;

/**
 *
 * @author DELL
 */
public class Line {
    private int num;

    private Invoice invoiceNo;
    private String itemName;
    private double itemPrice;
    private int count;
    public double lineTotal;

    public Line(int num, Invoice invoiceNo, String itemName, double itemPrice, int count) {
        this.num = num;
        this.invoiceNo = invoiceNo;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.lineTotal = this.count * this.itemPrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public Invoice getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Invoice invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setLineTotal() {
        this.lineTotal = (this.getCount() * this.getItemPrice());
    }
     public double getLineTotal() {
        return this.lineTotal;
     }

    @Override
    public String toString() {
        return invoiceNo + "," + itemName + "," + itemPrice + "," + count + "," + lineTotal;
    }
     
}
