/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.ArrayList;


public class InvoiceHeader {
   private int     invoiceNum;
   private String  invoiceDate;
   private String  customerName;
   private ArrayList<InvoiceLine> Lines;
   
   public Double Totalinvoice(){
       Double total=0.0;
       for(InvoiceLine invoiceline:getLines()){
           total +=invoiceline.CalculateTotal();
       }
       return total;
   }

    public ArrayList<InvoiceLine> getLines() {
        if(Lines==null){
            Lines=new ArrayList<>();
        }
        return Lines;
    }

    
    public InvoiceHeader() {
    }

   

    
    public InvoiceHeader(int invoiceNum, String invoiceDate, String customerName) {
        this.invoiceNum = invoiceNum;
       this.invoiceDate = invoiceDate;
       this.customerName = customerName;
        
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public String toString() {
        return "InvoiceHeader{" + "invoiceNum=" + invoiceNum + ", invoiceDate=" + invoiceDate + ", customerName=" + customerName + '}';
    }

   
      public String getAsCSV() {
        return invoiceNum + "," + invoiceDate + "," + customerName;
    }
    
}
