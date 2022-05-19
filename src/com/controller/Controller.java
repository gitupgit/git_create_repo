
package com.controller;


import com.model.InvoiceHeader;
import com.model.InvoiceLine;
import com.model.Invoicetable;
import com.model.LineTable;
import com.view.InvoiceDialog;
import com.view.LineDialog;
import com.view.viewJFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author sohah
 */
public class Controller implements ActionListener,ListSelectionListener {
    private viewJFrame frame;
    private InvoiceDialog invodialog;
    private LineDialog lindialog;
    
    public  Controller (viewJFrame frame){
    this.frame=frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actioncmd=e.getActionCommand();
       
        System.out.println(actioncmd);
        
        switch(actioncmd){
            
            case"Load":
                LoadInvoice();
                break;
            case"Save invoice":
                SaveInvioce();
                                        
                break;
            case"create new invoice":
                CreateNewInvoice();
                break;
            case"delete invoice":
                DeleteInvoice();
                break;
            case"save new line":
                savenewline();
                break;
            case"cancel":
                Cancel();
                break;
            
            case "createInvoiceCancel":
                createInvoiceCancel();
                break;
            case "createInvoiceOK":
                createInvoiceOK();
                break;
            case "createLineOK":
                createLineOK();
                break;
            case "createLineCancel":
                createLineCancel();
                break;
                   
        }
    }
     @Override
    public void valueChanged(ListSelectionEvent e) {
       int selected=frame.getInvoiceTable().getSelectedRow();
       if(selected!= -1){
       InvoiceHeader invoice1=frame.getInvoices().get(selected);
       frame.getInvoicenum().setText(""+invoice1.getInvoiceNum());
       frame.getInvoicedate().setText(""+invoice1.getInvoiceDate());
       frame.getInvoicetotal().setText(""+invoice1.Totalinvoice());
       frame.getCustomername().setText(invoice1.getCustomerName());
       
       LineTable linetable=new LineTable(invoice1.getLines());
       frame.getLinetable().setModel(linetable);
       linetable.fireTableDataChanged();
    }
    }
    private void LoadInvoice() {
        
          JFileChooser fch=new JFileChooser();
             
         try{
             int result =fch.showOpenDialog(frame);
               
             if(result==JFileChooser.APPROVE_OPTION)  //check open click
             {
               File headerfile=fch.getSelectedFile();  //headerfile for the selected file.
               Path headerpath= Paths.get(headerfile.getAbsolutePath());
               List<String>headerinvoices = Files.readAllLines(headerpath);//create array for each line of file of invoiceheader
               ArrayList<InvoiceHeader> Invoicearray=new ArrayList<>();
               
                System.out.println("hello");              
                for(String header : headerinvoices)
               {
                   
                  String[] splitheaderline= header.split(",");//split each line to separated data
                  int    invoiceno=Integer.parseInt(splitheaderline[0]);//and put each separeted data in an array
                  String invoicedate=splitheaderline[1];
                  String customerName=splitheaderline[2];
               
                  InvoiceHeader invoiceHeader =new InvoiceHeader( invoiceno,invoicedate,customerName); 
                  Invoicearray.add(invoiceHeader);
               }
                    
              result =fch.showOpenDialog(frame);
              if(result==JFileChooser.APPROVE_OPTION)
              {
                  File linefile=fch.getSelectedFile();
                  Path linepath= Paths.get(linefile.getAbsolutePath());
                  List<String>lineinvoices = Files.readAllLines(linepath);
                   
                  for(String s :lineinvoices)
                  {
                                    
                   String splititemsline[]= s.split(",");
                   int    itemnum=Integer.parseInt(splititemsline[0]);    //and put each separeted data in an array
                   String itemname=splititemsline[1];
                   Double itemprice=Double.parseDouble(splititemsline[2]);
                   int    count= Integer.parseInt(splititemsline[3]);
                   
                   InvoiceHeader invo = null;
                   for(InvoiceHeader invoice: Invoicearray)
                   {
                       if(invoice.getInvoiceNum() == itemnum){
                         invo=invoice;
                        break;
                       }
                   
                   }
                   
               
               InvoiceLine invoiceline=new InvoiceLine(itemname,itemprice,count,invo);
               invo.getLines().add(invoiceline);
              
               }
               
              }
              
            frame.setInvoices(Invoicearray);
            Invoicetable invoicetable=new Invoicetable(Invoicearray);
            frame.setInvoicetable(invoicetable);
            frame.getInvoiceTable().setModel(invoicetable);
            frame.getInvoicetable().fireTableDataChanged();
             }
             
        }
         catch(IOException e){
             e.printStackTrace();
        }
        
    }

    private void SaveInvioce() {
        ArrayList<InvoiceHeader> invoices = frame.getInvoices();
        String headers = "";
        String lines = "";
        for (InvoiceHeader invoice : invoices) {
            String invCSV = invoice.getAsCSV();
            headers += invCSV;
            headers += "\n";

            for (InvoiceLine line : invoice.getLines()) {
                String lineCSV = line.getAsCSV();
                lines += lineCSV;
                lines += "\n";
            }
        }
       
        try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                FileWriter fw = new FileWriter(file);
                fw.write(headers);
                fw.flush();
                fw.close();
                result = fc.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    FileWriter lfw = new FileWriter(lineFile);
                    lfw.write(lines);
                    lfw.flush();
                    lfw.close();
                }
            }
        } catch (Exception ex) {

        }
    }

    private void CreateNewInvoice() {
       invodialog = new InvoiceDialog(frame);
        invodialog.setVisible(true); 
    }

    private void DeleteInvoice() {
        int selected = frame.getInvoiceTable().getSelectedRow();
        if (selected != -1) {
            frame.getInvoices().remove(selected);
            frame.getInvoicetable().fireTableDataChanged();
        }
    }

    private void savenewline() {
        lindialog = new LineDialog(frame);
        lindialog.setVisible(true);
       
    }

    private void Cancel() {
        
        int select=frame.getLinetable().getSelectedRow();
        if(select != -1 ){
           
            LineTable linetbl =(LineTable)  frame.getLinetable().getModel();
            linetbl.getLines().remove(select);
            linetbl.fireTableDataChanged();
            frame.getInvoicetable().fireTableDataChanged();;
        }
    }

   
  private void createInvoiceCancel() {
        invodialog.setVisible(false);
        invodialog.dispose();
        invodialog = null;
    }

    private void createInvoiceOK() {
        String date = invodialog.getInvDateField().getText();
        String customer = invodialog.getCustNameField().getText();
        int num = frame.getNextInvoiceNum();

        InvoiceHeader invoice = new InvoiceHeader(num, date, customer);
        frame.getInvoices().add(invoice);
        frame.getInvoicetable().fireTableDataChanged();
        invodialog.setVisible(false);
        invodialog.dispose();
        invodialog = null;
    }

    private void createLineOK() {
        String item = lindialog.getItemNameField().getText();
        String countStr = lindialog.getItemCountField().getText();
        String priceStr = lindialog.getItemPriceField().getText();
        int count = Integer.parseInt(countStr);
        double price = Double.parseDouble(priceStr);
        int selectedInvoice = frame.getInvoiceTable().getSelectedRow();
        if (selectedInvoice != -1) {
            InvoiceHeader invoice = frame.getInvoices().get(selectedInvoice);
            InvoiceLine line = new InvoiceLine(item, price, count, invoice);
            invoice.getLines().add(line);
            LineTable linemodel = (LineTable) frame.getLinetable().getModel();
            linemodel.fireTableDataChanged();
            frame.getInvoicetable().fireTableDataChanged();
        }
        lindialog.setVisible(false);
        lindialog.dispose();
        lindialog = null;
    }

    private void createLineCancel() {
        lindialog.setVisible(false);
        lindialog.dispose();
        lindialog = null;
    }

    
      
    
    }
  
  
    

