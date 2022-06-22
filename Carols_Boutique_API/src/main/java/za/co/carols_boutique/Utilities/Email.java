 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.Utilities;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import za.co.carols_boutique.models.CardPayment;
import za.co.carols_boutique.models.CashPayment;
import za.co.carols_boutique.models.LineItem;
import za.co.carols_boutique.models.Sale;
import za.co.carols_boutique.models.Stock;


/**
 *
 * @author Jomar
 */

// Keep aside initialized
// Low stock

public class Email extends Thread{
    Date date;
    String code;
    Session newSession = null;
    MimeMessage mimeMessage = null;
    String recipient;
    String action;
    ArrayList<String>recipients;
    Sale sale;
    String productname;
    LineItem preLineItem;
    LineItem postLineItem;
    ArrayList<Stock> products;
    String promocode;

    public Email(String action, String recipient){
        this.action = action;
        this.recipient = recipient;
        this.start();
    }
    public Email(String action, String recipient,String Promocode,Date date){
        this.action = action;
        this.recipient = recipient;
        this.promocode=Promocode;
        this.date = date; 
        this.start();
    }
    
    
    
    public Email(String action, ArrayList<String> recipients,String promocode){
        this.action = action;
        this.recipients = recipients;
        this.promocode=promocode;
        this.start();
    }
    
    public Email(String action, String recipient, Sale sale){
        this.action = action;
        this.recipient = recipient;
        this.sale = sale;
        this.start();
    }
    
    public Email(String action, String recipient, LineItem preLineItem){
        this.action = action;
        this.recipient = recipient;
        this.preLineItem = preLineItem;
        this.start();
    }
    public Email(String action, String recipient, LineItem preLineItem,String code){
        this.action = action;
        this.recipient = recipient;
        this.preLineItem = preLineItem;
        this.code= code; 
        this.start();
    }
    
    public Email(String action, String recipient, Sale sale, LineItem preLineItem){
        this.action = action;
        this.recipient = recipient;
        this.sale = sale;
        this.preLineItem = preLineItem;
        this.start();
    }
    
    public Email(String action, String recipient, Sale sale, LineItem preLineItem, LineItem postLineItem){
        this.action = action;
        this.recipient = recipient;
        this.sale = sale;
        this.preLineItem = preLineItem;
        this.postLineItem = postLineItem;
        this.start();
    }
    
    public Email(String action, String recipient, ArrayList<Stock> products){
        this.action = action;
        this.recipient = recipient;
        this.products = products;
        this.start();
    }
    
    
    @Override
    public void run(){
        
        switch(action){
            
            case "sendPromotions":
                try {
                    setupServerProperties();
                    sendPromotions(recipients,promocode);
                    sendEmail();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }   
                break;
                
            case "sendReceipt":
                try {
                    setupServerProperties();
                    sendReceipt(recipient, sale);
                    sendEmail();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }   
                break;
                
            case "sendAmendedReceipt":
                try {
                    setupServerProperties();
                    sendAmendedReceipt(recipient, sale, preLineItem, postLineItem);
                    sendEmail();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }   
                break;
                
            case "sendRefund":
                try {
                    setupServerProperties();
                    sendRefund(recipient,sale);
                    sendEmail();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }   
                break;
                
            case "send24hReminder":
                try {
                    setupServerProperties();
                    send24hReminder(recipient, preLineItem);
                    sendEmail();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }   
                break;
                
            case "send36hReminder":
                try {
                    setupServerProperties();
                    send36hReminder(recipient, preLineItem);
                    sendEmail();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }   
                break;
                
            case "send48hReminder":
                try {
                    setupServerProperties();
                    send48hReminder(recipient);
                    sendEmail();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }   
                break;
                
            case "keepAsideCreated":
                try {
                    setupServerProperties();
                    keepAsideCreated(recipient, preLineItem,code);
                    sendEmail();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }   
                break;
                
            case "lowStockReminder":
                try {
                    setupServerProperties();
                    lowStockReminder(recipient, products);
                    sendEmail();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }   
                break;
            case "newsLetterPromotion":
                try{
                    setupServerProperties();
                    newsLetterPromotion(recipient,date,promocode);
                    sendEmail();
                }catch(MessagingException e){
                    e.printStackTrace();
                }
                break;
            case "test":
                try {
                    setupServerProperties();
                    test(recipient);
                    sendEmail();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }   
                break;
        }
        
    }
    

    public void setupServerProperties(){
        String host = "smtp.gmail.com";
        final String username = "carols.boutique.domain@gmail.com";
        //Enter your Gmail password
        final String password = "qfdqfmqeznacwbzl";

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.ssl.trust", host);

        newSession = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

    }
    
    public void sendEmail() throws MessagingException {
        String fromUser = "jomarvn@gmail.com";
        String userPassword = "J0hannes";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost,fromUser,userPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully");
    }
    
    

    public MimeMessage sendPromotions(ArrayList<String> emailRecipients,String promoCode) throws MessagingException {
        
        mimeMessage = new MimeMessage(newSession);
        for (int i = 0; i < emailRecipients.size(); i++) {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipients.get(i)));
        }
        mimeMessage.setSubject("Carol's Boutique promotion");
        String body = promoString(promoCode);
        mimeMessage.setContent(body,"text/html");
        return mimeMessage;
    }
    
    public MimeMessage sendReceipt(String recipient, Sale sale) throws AddressException, MessagingException{

        mimeMessage = new MimeMessage(newSession);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        String body = receiptString(sale);
        mimeMessage.setSubject("Carol's Boutique receipt");
        mimeMessage.setContent(body,"text/html");
        return mimeMessage;
    }
    
    public MimeMessage sendAmendedReceipt(String recipient, Sale sale, LineItem pre, LineItem post) throws AddressException, MessagingException{
        String emailSubject = "Test Email";
        String emailBody = "This is a test email from Carol's Boutique. Please let me know when you get this\n-@ jomarvn@gmail.com";
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        mimeMessage.setSubject(emailSubject);

        String body = amendedReceiptString(sale,pre,post);
        mimeMessage.setSubject("Carol's Boutique receipt");
        mimeMessage.setContent(body,"text/html");
        return mimeMessage;
    }
    
    public MimeMessage sendRefund(String recipient, Sale sale) throws AddressException, MessagingException{
        String emailSubject = "Test Email";
        String emailBody = "This is a test email from Carol's Boutique. Please let me know when you get this\n-@ jomarvn@gmail.com";
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        mimeMessage.setSubject(emailSubject);

        String body = refundString(sale);
        mimeMessage.setSubject("Carol's Boutique receipt");
        mimeMessage.setContent(body,"text/html");
        return mimeMessage;
    }
    
    public MimeMessage send24hReminder(String recipient, LineItem lineItem) throws MessagingException {
        
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        String body = reminder24hString(lineItem);
        mimeMessage.setSubject("Carol's Boutique receipt");
        mimeMessage.setContent(body,"text/html");
        return mimeMessage;
    }
    
    public MimeMessage send36hReminder(String recipient, LineItem lineItem) throws MessagingException {
        
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        String body = reminder36hString(lineItem);
        mimeMessage.setSubject("Carol's Boutique receipt");
        mimeMessage.setContent(body,"text/html");
        return mimeMessage;
    }
    
    public MimeMessage send48hReminder(String recipient) throws MessagingException {
   
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        
        String body = reminder48hString();
        mimeMessage.setSubject("Carol's Boutique receipt");
        mimeMessage.setContent(body,"text/html");
        return mimeMessage;
    }
    

    
    public MimeMessage keepAsideCreated(String recipient, LineItem lineItem,String code) throws MessagingException {
   
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        String body = keepAside(lineItem,code);
        mimeMessage.setSubject("Carol's Boutique receipt");
        mimeMessage.setContent(body,"text/html");
        return mimeMessage;
    }
    
    public MimeMessage lowStockReminder(String recipient, ArrayList<Stock> products) throws MessagingException {
   
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        String body = lowStockString(products);
        mimeMessage.setSubject("Carol's Boutique receipt");
        mimeMessage.setContent(body,"text/html");
        return mimeMessage;
    }
    
    public MimeMessage test(String recipient) throws MessagingException {
   
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        String body = "This is a test";
        mimeMessage.setSubject("Carol's Boutique receipt");
        mimeMessage.setContent(body,"text/html");
        return mimeMessage;
    }
    public MimeMessage newsLetterPromotion(String recipient,Date date,String promocode) throws MessagingException{
        mimeMessage = new MimeMessage(newSession);
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        String body = newsLetterPromotionString(date, promocode);
        mimeMessage.setSubject("Carol's Boutique receipt");
        mimeMessage.setContent(body,"text/html");
        return mimeMessage;
    }
    
    private String receiptString(Sale sale){
        Float total = 0f;
        String table="";
        int count=0;
        List<LineItem> lineitems =sale.getLineItems();
        for(LineItem lineitem: lineitems){
            count +=1; 
            table=table+((count%2!=0)?"<tr style=\"background-color:lightgrey\"d>":"<tr style =\"background-color:rgb(166, 166, 166)\">")
            +"<td>"+lineitem.getProduct().getId()+"</td>"
            +"<td>"+lineitem.getProduct().getName()+"</td>"
            +"<td>"+lineitem.getAmounnt()+"</td>"+
            "<td>"+lineitem.getTotal()+"</td>"
            +"</tr>";
            total = total +lineitem.getTotal();
        }
        CardPayment cp = null;
        CashPayment cp2 = null;
        if(sale.getPayment() instanceof CardPayment){
            cp = (CardPayment)(sale.getPayment());
            
        }
        if(sale.getPayment() instanceof CashPayment){
            cp2 = (CashPayment)(sale.getPayment());
        }
        String s = "<!DOCTYPE html>\n" +
"<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"<title>receipt</title>\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"<h1><br><br>Carol's Boutique</h1>\n" +
"<h3>Receipt of purchase on: ??Date??</h3>\n" +
"\n" +
"<table style=\"width:400px\">\n" +
"  <tr>\n" +
"    <th style = \"background-color:blue;\">NO:</th>\n" +
"    <th style =\"width:50%;background-color:lightblue\">Item</th>\n" +
"    <th style =\"background-color:lightblue;\">Qty</th>\n" +
"    <th style =\"background-color:blue;\">Amount</th> \n" +
"    \n" +
"  </tr>\n" +
"\n" +
"\n" +
table+
"</table>\n" +
"<br>\n" +
"<table style=\"width:400px\">\n" +
"  <tr style=\"text-align:left\">\n" +
"    <th >Payment Info</th>\n" +
"    <th ></th>\n" +
"    <th></th>\n" +
"    <th ></th> \n" +
"    \n" +
"  </tr>\n" +
"\n" +
"\n" +
"  <tr>\n" +
"    <td>Cash/Card</td>\n" +
"    <td>?cash/card?</td>\n" +
"    <td>SubTotal:</td>\n" +
"    <td>?subTotal?</td>\n" +
"    \n" +
"    \n" +
"  </tr>\n" +
"  <tr >\n" +
"    <td>Number:</td>\n" +
"    <td>?Number?</td>\n" +
"    <td>Tax</td>\n" +
"    <td>?tax?</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"  <td>Account type:</td>\n" +
"  <td>?Account type?</td>\n" +
"  <td>Total</td>\n" +
"  <td>?total?</td>\n" +
"  </tr>\n" +
"</table>\n" +
"<h4><u><b>Return policy</b></u></h4>\n" +
"<h5>You can return any product within 10 days of purchase.</h5>\n" +
"<h6><u>Please rate our service:?Link?</u></h6>\n" +
"</body>\n" +
"</html>";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(cp!=null){
        s=s.replace("?Account type?", cp.getCardType());
        s=s.replace("?cash/card?", "Card");
        s=s.replace("?Number?", cp.getCardNum());
        }
        s=s.replace("??Date??",sdf.format(sale.getDate()));
        
        if(cp2!=null){
        s=s.replace("?Account type?","None" );
        s=s.replace("?cash/card?","Cash" );
        s=s.replace("?Number?", "None");    
        }
        
        Float vat = total*0.15f; 
        Float subtotal = total*0.85f;
        
        s=s.replace("?tax?","R"+String.format("%,.2f", vat));
        s=s.replace("?subTotal?","R"+String.format("%,.2f", subtotal));
        s=s.replace("?total?","R"+String.format("%,.2f", total));
        return s;
    }
    
    
    private String amendedReceiptString(Sale sale,LineItem prelineitem,LineItem postlineitem){
        double TaxPercentage = 0.15d;
        String table ="";
        float total =0f;
        int count =0;
        List<LineItem> lineitems =sale.getLineItems();
        for(LineItem lineitem: lineitems){
            count +=1; 
            table=table+((count%2!=0)?"<tr style=\"background-color:lightgrey\"d>":"<tr style =\"background-color:rgb(166, 166, 166)\">")
            +"<td>"+lineitem.getProduct().getId()+"</td>"
            +"<td>"+lineitem.getProduct().getName()+"</td>"
            +"<td>"+lineitem.getAmounnt()+"</td>"+
            "<td>"+lineitem.getTotal()+"</td>"
            +"</tr>";
            total = total +lineitem.getTotal();
        }
        CardPayment cp = null;
        CashPayment cp2 = null;
        if(sale.getPayment() instanceof CardPayment){
            cp = (CardPayment)(sale.getPayment());
            
        }
        if(sale.getPayment() instanceof CashPayment){
            cp2 = (CashPayment)(sale.getPayment());
        }
        table =table+
        "<tr style =\"background-color:rgb(255, 77, 77)\">"+
        "<td>"+prelineitem.getProduct().getId()+"</td>"+
        "<td>"+prelineitem.getProduct().getName()+"</td>"+
        "<td>"+prelineitem.getAmounnt()+"</td>"+
        "<td>"+"-"+prelineitem.getTotal()+"</td>"+
        "</tr>"+
        "<tr style =\"background-color:lightgreen\">"+
        "<td>"+postlineitem.getProduct().getId()+"</td>"+
        "<td>"+postlineitem.getProduct().getName()+"</td>"+
        "<td>"+postlineitem.getAmounnt()+"</td>"+
        "<td>"+"+"+postlineitem.getTotal()+"</td>"+
        "</tr>";
        total=total+postlineitem.getTotal()-prelineitem.getTotal();
        String s = "<h1>Carols Boutique</h1>"+
                "<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"<title>Page Title</title>\n" +
"</head>\n" +
"<body>\n" +
"<h1><br><br>Carol's Boutique</h1>\n" +
"<h3>Receipt of purchase on: ??Date??</h3>\n" +
"\n" +
"<table style=\"width:400px\">\n" +
"  <tr>\n" +
"    <th style = \"background-color:blue;\">NO:</th>\n" +
"    \n" +
"    <th style =\"width:50%;background-color:lightblue\">Item</th>\n" +
"    <th style =\"background-color:lightblue;\">Qty</th>\n" +
"    <th style =\"background-color:blue;\">Amount</th> \n" +
"    \n" +
"    \n" +
"    \n" +
"  </tr>\n" +
"\n" +
"\n" +table+
"  <tr style = \"background-color:lightgrey; height: 18px;\">\n" +
"  <td></td>\n" +
"  <td></td>\n" +
"  <td></td>\n" +
"  <td></td>\n" +
"  </tr>\n" +
"</table>\n" +
"<br>\n" +
"<table style=\"width:400px\">\n" +
"  <tr style=\"text-align:left\">\n" +
"    <th >Payment Info</th>\n" +
"    <th ></th>\n" +
"    <th></th>\n" +
"    <th ></th> \n" +
"    \n" +
"  </tr>\n" +
"\n" +
"\n" +"<tr>\n" +
"    <td>Cash/Card</td>\n" +
"    <td>?cash/card?</td>\n" +
"    <td>SubTotal:</td>\n" +
"    <td>?subTotal?</td>\n" +
"    \n" +
"    \n" +
"  </tr>\n" +
"  <tr >\n" +
"    <td>Number:</td>\n" +
"    <td>?Number?</td>\n" +
"    <td>Tax</td>\n" +
"    <td>?tax?</td>\n" +
"  </tr>\n" +
"  <tr>\n" +
"  <td>Account type:</td>\n" +
"  <td>?Account type?</td>\n" +
"  <td>Total</td>\n" +
"  <td>?total?</td>\n" +
"  </tr>\n" +
"</table>\n" +
"<h4><u><b>Return policy</b></u></h4>\n" +
"<h5>You can return any product within 10 days of purchase.</h5>\n" +
"<h6 Style = \"font-size:15px\"><u>Please rate our service:??link??</u></h6>\n" +
"</body>\n" +
"</html>"
                ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(cp!=null){
        s=s.replace("?Account type?", cp.getCardType());
        s=s.replace("?cash/card?", "Card");
        s=s.replace("?Number?", cp.getCardNum());
        }
        s=s.replace("??Date??",sdf.format(sale.getDate()));
        
        if(cp2!=null){
        s=s.replace("?Account type?","None" );
        s=s.replace("?cash/card?","Cash" );
        s=s.replace("?Number?", "None");    
        }
        
        Float vat = total*0.15f; 
        Float subtotal = total*0.85f;
        
        s=s.replace("?tax?","R"+String.format("%,.2f", vat));
        s=s.replace("?subTotal?","R"+String.format("%,.2f", subtotal));
        s=s.replace("?total?","R"+String.format("%,.2f", total));
        return s;
    }
    
    private String refundString(Sale sale){
        CardPayment cp = null;
        if(sale.getPayment() instanceof CardPayment){
            cp = (CardPayment)(sale.getPayment());
        }
        String s ="<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"<title>Page Title</title>\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"<h1 style =\"font-size:40px;\">Carols Boutique</h1>\n" +
"<h2>Your refund has been processed.<br><br>Account ??account?? has been refunded on ??date??.</h3>\n" +
"\n" +
"\n" +
"</body>\n" +
"</html>";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        s=s.replace("??date??",sdf.format(sale.getDate()));
        s=s.replace("??account??",cp.getCardNum());
        return s;
    }
    
    private String promoString(String Promocode){
        String s ="<html>\n" +
"<head>\n" +
"<title>Page Title</title>\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"<h1><u>Carol's Boutique</u></h1>\n" +
"<p1>At carol's Boutique we have great deals</p1><br>\n" +
"\n" +
"<p2>Save 20% off on your next purchase with this Promocode<br><p2><br>\n" +
"<table style=\"font-size:20px\">\n" +
"<tr>\n" +
"<th>PromoCode:</th>\n" +
"<th Style =\"border-style:solid;border-width:6px;border-color:black;\">??PromoCode??</th>\n" +
"</tr>\n" +
"</table>\n" +
"</body>\n" +
"</html>";
        s=s.replace("??PromoCode??", Promocode);
        return s; 
    }
    
    private String reminder24hString(LineItem lineItem){
        String s ="<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"<title>remind me</title>\n" +
"</head>\n" +
"<body>\n" +
"<h1>Carol's Boutique</h1>\n" +
"<p1>Dear valued customer</p1><br><br>\n" +
"<p2>You have ??amount?? ??product??(s) waiting for you at our store.<br>Please pick it up within 24 hours before it is placed back on the rack.<br></p2>\n" +
"\n" +
"</body>\n" +
"</html>";
        s= s.replace("??amount??",""+lineItem.getAmounnt() );
        s=s.replace("??product??", lineItem.getProduct().getName());
        return s;
    }
    
    private String reminder36hString(LineItem lineItem){
        String s= "<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"<title>remind me</title>\n" +
"</head>\n" +
"<body>\n" +
"<h1>Carol's Boutique</h1>\n" +
"<p1>Dear valued customer</p1><br><br>\n" +
"<p2>You have ??amount?? ??product??(s) waiting for you at our store.<br>Please pick it up within 12 hours before it is placed back on the rack.<br></p2>\n" +
"\n" +
"</body>\n" +
"</html>";
        s= s.replace("??amount??",""+lineItem.getAmounnt() );
        s=s.replace("??product??", lineItem.getProduct().getName());
        return s;
    }
    
    private String reminder48hString(){
        return "<html>\n" +
"<head>\n" +
"<title>latereminder</title>\n" +
"</head>\n" +
"<body>\n" +
"<h1>Carol's Boutique</h1>\n" +
"<p1>Dear valued customer<br><br></p1>\n" +
"<p2>24 hours has passed, your product has been placed back on the rack. If you are still in interested in this product, please visit any of our stores.<p2>\n" +
"</body>\n" +
"</html>";
    }
    
    private String keepAside(LineItem lineItem,String code){
        String s ="<html>\n" +
"<head>\n" +
"<title>Page Title</title>\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"<h1>Carol's Boutique</h1>\n" +
"\n" +
"<p>Dear valued customer<br><br>??Number?? ??Product??(s) has been kept-aside for your retrieval.<br>Please fetch it within 48 hours at our store before the product will be placed back on the rack.</p>\n" +
"\n" +
"<h2>This is your id.</h2>\n" +
"<h3 style=\"border-width:1px;border-style:solid;width: 200px;border-width:3px\">??ID??</h3>\n" +
"<p>Please do not lose it as it is needed for retreival of the product.</p>\n" +
"\n" +
"</body>\n" +
"</html>";
        s=s.replace("??ID??", code);
        s=s.replace("??Number??",""+ lineItem.getAmounnt());
        s=s.replace("??Product??", lineItem.getProduct().getName());
        return s;
    }
    
    private String lowStockString(ArrayList<Stock> stocks){
        String table= "";
        for(Stock stock:stocks){
            table=table+"<tr style =\"text-Align:left;\">"+
            "<td>"+stock.getProductID()+"</td>"+
            "<td>"+stock.getProductName()+"</td>"+
            "<td>"+stock.getAmount()+"</td>"+"</tr>";
        }
        String s ="<html>\n" +
"<head>\n" +
"<title>remind me</title>\n" +"<style>\n" +
"table,tr,th,td{border: 2px solid black;\n" +
"  border-collapse: collapse;\n" +
"  border-color:white;\n" +
"}\n" +
"</style>"+
"</head>\n" +
"<body>\n" +
"<h1>Carol's Boutique<h1>\n" +
"<p style=\"font-size:20px;\">Dear store manager:</p>\n" +
"<p style=\"font-size:20px;\">The following products are running low.</p>\n" +
"<table style =\"width:300px;text-align:center;font-size:20px;background-color:lightblue;\">\n" +
"<tr>\n" +
"<th>ID</th>\n" +
"<th><u>product:<u></th>\n" +
"<th><u>amount:<u></th>\n" +
"</tr>\n"+table+
"</table>\n" +
"</body>\n" +
"</html>";
        
        return s;
    }
    private String newsLetterPromotionString(Date date,String PromoCode){
        String s = "<html>\n" +
"<head>\n" +
"<title>Page Title</title>\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"<h1>Carol's Boutique</h1>\n" +
"<p1>Thank you for Subscribing to our newsletter</p1>\n" +
"<h2>Here is a promocode to get 20% off on your next purchase</h2>\n" +
"<h3>Your promo-code is available till ??date?? <h4>\n" +
"<h4 style=\"border-width:1px;border-style:solid;width: 150px;border-width:3px\">??ID??</h4>\n" +
"<label>\n" +
"\n" +
"</body>\n" +
"</html>";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        s=s.replace("??date??",sdf.format(date) );
        s=s.replace("??ID??", PromoCode);
        return s;
    }

}
