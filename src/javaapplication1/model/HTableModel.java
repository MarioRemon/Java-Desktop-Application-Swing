/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author DELL
 */
public class HTableModel extends AbstractTableModel {
    private String[] colsName = {"Num", "Date", "Customer Name","Total"};
    private List<Invoice> invo;
    public HTableModel(List<Invoice> invo)
    {
        this.invo = invo;
    }

    @Override
    public int getRowCount() {
        return invo.size();
    }

    @Override
    public int getColumnCount() {
        return colsName.length;
    }
    @Override
    public String getColumnName(int columnIndex)
    {
        return colsName[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Invoice inv = invo.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return inv.getNo();
            case 1:
                return inv.getDate();
            case 2:
                return inv.getCustomerName();
            case 3:
                return inv.getInvoiceTotal();
        }
        return "";
    }
    
}
