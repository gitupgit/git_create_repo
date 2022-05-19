/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sohah
 */
public class LineTable extends AbstractTableModel {
    private ArrayList<InvoiceLine>lines;
    private String[]column={"Item num","Item Name","Price","count","Total"};

    public LineTable(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }

    public ArrayList<InvoiceLine> getLines() {
        return lines;
    }

    @Override
    public int getRowCount() {
        return lines.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }
     @Override
    public String getColumnName(int col) {
        return column[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         InvoiceLine invoiceline=lines.get(rowIndex);
        switch(columnIndex){
            case 0:return invoiceline.getInvoiceHeader().getInvoiceNum();
            case 1:return invoiceline.getItemName();
            case 2:return invoiceline.getItemPrice();
            case 3:return invoiceline.getCount();
             case 4:return invoiceline.CalculateTotal();
            default: return"";
    }
    
}
}