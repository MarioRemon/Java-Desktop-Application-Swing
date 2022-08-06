/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javaapplication1.model.HTableModel;
import javaapplication1.model.Invoice;
import javaapplication1.model.LTableModel;
import javaapplication1.model.Line;
import javaapplication1.view.NewInvoice;
import javaapplication1.view.NewLine;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javaapplication1.view.SalesInvoiceFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class SalesInvoiceActions implements ActionListener, ListSelectionListener{

    private NewLine newLine;
    private NewInvoice newInvoice;
    private SalesInvoiceFrame frame;
    public SalesInvoiceActions(SalesInvoiceFrame frame) {
        this.frame = frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("aaaaa");
       switch(e.getActionCommand())
       {
           case "Load File":
               loadFile(null,null);
               break;
           case "Save File":
               savingFile();
               break;
           case "Create New Invoice":
               createNewInvoice();
               break;
           case "Delete Invoice":
               DeleteInvoice();
               break;
           case "Create New Line":
               createNewLine();
               break;
           case "Delete Line":
               deleteLine();
               break;
           case "Okay Invoice":
            {
                try {
                    okayInvoice();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(frame,"Error in date format","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
               break;

           case "Cancel Invoice":
               cancelInvoice();
               break;
           case "Cancel Line":
               cancelLine();
               break;
           case "Okay Line":
               okayLine();
               break;
       }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = frame.getInvoiceTable().getSelectedRow();
        if(row > -1 && row < frame.getInvoices().size())
        {
            Invoice invo = frame.getInvoices().get(row);
            List<Line> line = invo.getLines();
            frame.getLinesTable().setModel(new LTableModel(line));

            frame.getInvoiceNumber().setText(String.valueOf(invo.getNo()));
            frame.getInvoiceDate().setText(frame.sdf.format(invo.getDate()));
            frame.getCustomerName().setText(invo.getCustomerName());
            frame.getInvoiceTotal().setText(String.valueOf(invo.getInvoiceTotal()));
        }
        else
        {
            frame.getLinesTable().setModel(new LTableModel(new ArrayList<Line>()));
            frame.getInvoiceNumber().setText("");
            frame.getInvoiceDate().setText("");
            frame.getCustomerName().setText("");
            frame.getInvoiceTotal().setText("");
        }
    }
    public void loadFile(String invoiceFile, String lineFile)
    {
        File finvoiceFile = null;
        File flineFile = null;
        if(invoiceFile == null && lineFile == null)
        {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) 
            {
                finvoiceFile = fc.getSelectedFile();
                if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) 
                {
                    flineFile = fc.getSelectedFile();
                }
            }
        }
        else
        {
            finvoiceFile = new File(invoiceFile);
            flineFile = new File(lineFile);
        }
        if(finvoiceFile != null && flineFile != null)
        {
            try
            {
                frame.getInvoices().clear();
                List<String> hlines = Files.lines(Paths.get(finvoiceFile.getAbsolutePath())).collect(Collectors.toList());
                List<String> llines = Files.lines(Paths.get(flineFile.getAbsolutePath())).collect(Collectors.toList());
                for(String detail: hlines)
                {
                    String [] partsOfInvoice  = detail.split(",");
                    int num = Integer.parseInt(partsOfInvoice[0]);
                    String dateS = partsOfInvoice[1];
                    String name = partsOfInvoice[2];
                    Date date = SalesInvoiceFrame.sdf.parse(dateS);
                    Invoice invo = new Invoice(num,date,name);
                    
                    frame.getInvoices().add(invo);
                    System.out.println("Invoice aho");
                    
                }
                for(String detail: llines)
                {
                    String [] partsOfLine = detail.split(",");
                    int num = Integer.parseInt(partsOfLine[0]);
                    String ItemName = partsOfLine[2];
                    double price = Double.parseDouble(partsOfLine[3]);
                    int count = Integer.parseInt(partsOfLine[4]);
                    Invoice invo = getInvoiceByNum(Integer.parseInt(partsOfLine[1]));
                    Line line = new Line(num, invo,ItemName,price,count);
                    invo.getLines().add(line);
                    System.out.println("line aho");
                }
                frame.getInvoiceTable().setModel(new HTableModel(frame.getInvoices()));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
    }
    private Invoice getInvoiceByNum(int num)
    {
        for(Invoice invo: frame.getInvoices())
        {
            if(invo.getNo() == num)
            {
                return invo;
            }
        }
        return null;
    }

    private void DeleteInvoice() 
    {
        if(frame.getInvoiceTable().getSelectedRow() > -1 )
        {    
            frame.getInvoices().remove(frame.getInvoiceTable().getSelectedRow());
            ((HTableModel)frame.getInvoiceTable().getModel()).fireTableDataChanged();
        }
    }

    private void deleteLine() 
    {
        System.out.println("noo");
        if(frame.getLinesTable().getSelectedRow()> -1)
        {
            System.out.println("hey");
            int hRow = frame.getInvoiceTable().getSelectedRow();
            LTableModel l = (LTableModel)frame.getLinesTable().getModel();
            l.getLine().remove(frame.getLinesTable().getSelectedRow());
            System.out.println(l.getLine());
           //l.getLine().remove(0);
            l.fireTableDataChanged();
            ((HTableModel)frame.getInvoiceTable().getModel()).fireTableDataChanged();
            frame.getInvoiceTable().setRowSelectionInterval(hRow,hRow);
        }
    }

    private void okayInvoice() throws ParseException 
    {        
        Invoice invo = new Invoice(getNextInvoiceNum(),frame.sdf.parse(newInvoice.getInvoiceDate().getText()),newInvoice.getCustomerName().getText());
        frame.getInvoices().add(invo);
        ((HTableModel)frame.getInvoiceTable().getModel()).fireTableDataChanged();
        newInvoice.setVisible(false);
        newInvoice.dispose();
    }

    private void createNewInvoice() {
        newInvoice = new NewInvoice(frame);
        newInvoice.setVisible(true);
    }

    private void createNewLine() 
    {
        newLine = new NewLine(frame);
        newLine.setVisible(true);
    }

    private int getNextInvoiceNum() {
        int num = -1;
        for(Invoice l : frame.getInvoices())
        {
            if(l.getNo() > num)
            {
                num = l.getNo();
            }
        }
        return num + 1;
    }
    private int getNextLineNum() {
        Invoice invo = getInvoiceByNum(frame.getInvoiceTable().getSelectedRow());
        int num = -1;
        for(Line l : invo.getLines())
        {
            if(l.getNum() > num)
            {
                num = l.getNum();
            }
        }
        return num + 1;
    }

    private void cancelInvoice() {
        newInvoice.setVisible(false);
        newInvoice.dispose();
    }

    private void okayLine() 
    {
        if(getInvoiceByNum(frame.getInvoiceTable().getSelectedRow()) != null){
            System.out.println(getNextLineNum());
            Line l = new Line(getNextLineNum(),getInvoiceByNum(frame.getInvoiceTable().getSelectedRow()),newLine.getItemName().getText(),Double.parseDouble(newLine.getItemPrice().getText()),Integer.parseInt(newLine.getItemCount().getText()));
            getInvoiceByNum(frame.getInvoiceTable().getSelectedRow()).getLines().add(l);
            l.toString();
            newLine.setVisible(false);
            newLine.dispose();
            ((HTableModel)frame.getInvoiceTable().getModel()).fireTableDataChanged();
            LTableModel u = (LTableModel)frame.getLinesTable().getModel();
            u.fireTableDataChanged();
        }
        else
        {
            JOptionPane.showMessageDialog(frame,"No Invoice Selected","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelLine() 
    {
        newLine.setVisible(false);
        newLine.dispose();
    }
        private void savingFile ()
        {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                String path = fc.getSelectedFile().getPath();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(path);
                   // int length = getLength();
                    int c = 0;
                    byte[] b = null;
                    while(c < frame.getInvoices().size())
                    {
                        b = frame.getInvoices().get(c).toString().getBytes(StandardCharsets.UTF_8);
                        fos.write(b);
                        fos.write("\n".getBytes(StandardCharsets.UTF_8));
                        c++;
                    }
                    savingLineFile ();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
       private void savingLineFile ()
        {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                String path = fc.getSelectedFile().getPath();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(path);
                    int c = 0;
                    byte[] b = null;
                    while(c < frame.getInvoices().size())
                    {
                        b = frame.getInvoices().get(c).getLines().toString().getBytes(StandardCharsets.UTF_8);
                        fos.write(b);
                        fos.write("\n".getBytes(StandardCharsets.UTF_8));
                        c++;
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
}
