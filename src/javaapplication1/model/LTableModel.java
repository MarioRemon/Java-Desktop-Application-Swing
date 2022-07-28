/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class LTableModel  extends AbstractTableModel{
    private final String[] colsName ={"No", "Invoice Number","Item Name", "Item Price", "Count", "total" };
    List<Line> line;
    public LTableModel(List<Line> line)
    {
        this.line = line;
    }
    
    @Override
    public int getRowCount() {
        return line.size();
    }

    @Override
    public int getColumnCount() {
        return colsName.length;
    }
    
    @Override
    public String getColumnName(int colIndex)
    {
        return colsName[colIndex];
    }

    public List<Line> getLine() {
        return line;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Line l = line.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return l.getNum();
            case 1:
                return l.getInvoiceNo().getNo();
            case 2:
                return l.getItemName();
            case 3:
                return l.getItemPrice();
            case 4:
                return l.getCount();
            case 5:
                return l.getLineTotal();
        }
        return "";
    }
    
}
