
package com.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sohah
 */
public class Invoicetable extends AbstractTableModel {
  private ArrayList<InvoiceHeader> invoices;
 private  String[]invoicecolumns={"Invoice num","Date","customer Name","Total"};

    public Invoicetable(ArrayList<InvoiceHeader> Invoicearray) {
      this.invoices=Invoicearray;
    }

    @Override
    public int getRowCount() {
      return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return invoicecolumns.length;
    }
   
    
    @Override
    public String getColumnName(int colum) {
        return invoicecolumns[colum];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      InvoiceHeader invoiceheader;
      invoiceheader = invoices.get(rowIndex);
        switch(columnIndex){
            case 0:return invoiceheader.getInvoiceNum();
            case 1:return invoiceheader.getInvoiceDate();
            case 2:return invoiceheader.getCustomerName();
            case 3:return invoiceheader.Totalinvoice();
            default: return"";
        }
    }

    public Object getLines() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
