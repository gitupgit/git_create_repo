/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

public class InvoiceLine {
   private String  itemName ;
   private Double  itemPrice;
   private int     count;
   private InvoiceHeader InvoiceHeader;
    
   
    public Double CalculateTotal(){
      return count* itemPrice; 
  }  
   

//    public InvoiceLine(String itemName, Double itemPrice, int count, int no) {
//        this.itemName = itemName;
//        this.itemPrice = itemPrice;
//        this.count = count;
//        
//    }

    public InvoiceLine(String itemName, Double itemPrice, int count, InvoiceHeader InvoiceHeader) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.InvoiceHeader = InvoiceHeader;
    }
    
    public InvoiceLine() {
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InvoiceHeader getInvoiceHeader() {
        return InvoiceHeader;
    }
   
      public String getAsCSV() {
        return InvoiceHeader.getInvoiceNum()+ "," + itemName + "," + itemPrice + "," + count;
    }
    
}
