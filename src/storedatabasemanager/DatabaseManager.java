package storedatabasemanager;

import java.sql.*;
import java.util.Scanner;

public class DatabaseManager{

      public static void main(String [] args){
      
      DatabaseManager test = new DatabaseManager();
      
      test.initiate();

   }


   public void initiate(){
      boolean control = true;
      while(control){
         //menu options
         System.out.println("1. Add item to database");
         System.out.println("2. Display item information");
         System.out.println("3. Display low quanity items");
         System.out.println("4. Display current store services");
         System.out.println("5. Display current customer subscriptions");
         System.out.println("6. LogOut");
         System.out.println();
         
         
         
         
         //user chooses option
         
         Scanner input = new Scanner(System.in);
         System.out.print("Enter menu option: ");
         
         int selection = input.nextInt();
         
         
         
         switch(selection){
            case 1:
               addItem();
               break;
               
            case 2:
               displayItem();
               break;
            
            case 3:
               displayOuts();
               break;
               
            case 4:
               displayServices();
               break;
            
            case 5:
               displaySubs();
               break;
               
            case 6:
               control=false;
               break;
               
            
            
            default:
               System.out.println("Invalid selection");
         
         }
      }
   }
  
   
   private void addItem(){
   
      Connection conn = null;
   	
   	try {
   
       	conn =
          DriverManager.getConnection("jdbc:mysql://localhost:3306/store?useSSL=false","root","root");
   
       Statement stmt = conn.createStatement();
       
       Scanner kbd = new Scanner(System.in);
       System.out.println("Enter item number:");
       int number =kbd.nextInt();
       System.out.println("Enter item:");
       String item =kbd.nextLine();
       System.out.println("Enter price:");
       Double price =kbd.nextDouble();
       System.out.println("Enter quantity:");
       int quantity =kbd.nextInt();
       System.out.println("Enter description:");
       String description =kbd.nextLine();
       System.out.println("Enter location:");
       String location =kbd.next();
       System.out.println("Enter attachments:");
       String attachments =kbd.next();
       System.out.println("Enter store:");
       int store =kbd.nextInt();
       
       String sql = "INSERT INTO Item VALUES ("+number+", '"+item+"',"+price+","+quantity+",'"+description+"','"+location+"','"+attachments+"',"+store+")";
       stmt.executeUpdate(sql);

      }
      
      
       catch (SQLException ex) {
       // handle any errors
       System.out.println("SQLException: " + ex.getMessage());
       System.out.println("SQLState: " + ex.getSQLState());
       System.out.println("VendorError: " + ex.getErrorCode());
   	}
   }
   
   
   
   
   private void displayItem(){
      
      Connection conn = null;
	
	   try {

    	conn =
       DriverManager.getConnection("jdbc:mysql://localhost:3306/store?useSSL=false","root","root");
      Statement stmt = conn.createStatement(); 
      Scanner kbd = new Scanner(System.in);                         
                                   
      System.out.println("Insert item number: ");
      int number =kbd.nextInt();
     
      String sql = "SELECT * FROM Item Where itemID = "+ number +"";
      ResultSet rs = stmt.executeQuery(sql);
      
      while(rs.next()){
      int itemID= rs.getInt("ItemID");
      String name = rs.getString("name");
      String price = rs.getString("price");
      int quantity = rs.getInt("quantity");
      String description = rs.getString("description");
      String location = rs.getString("location");
      String attachments = rs.getString("attachments");
      int store = rs.getInt("storeID");
      
      System.out.println("Item number: " + itemID);
      System.out.println("Item: " + name);
      System.out.println("Price: " + price);
      System.out.println("Qty: " + quantity);
      System.out.println("Description: " + description);
      System.out.println("Loc: " + location);
      System.out.println("store: " + store);
      }
      rs.close(); 
      
      } 
      
      catch (SQLException ex) {
          // handle any errors
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
	   }
   }
   


 
   private void displaySubs(){
      
         Connection conn = null;
   	
   	   try {
   
       	conn =
          DriverManager.getConnection("jdbc:mysql://localhost:3306/store?useSSL=false","root","root");
         Statement stmt = conn.createStatement(); 
         
         Scanner input = new Scanner(System.in);
         System.out.println("Insert customer 7 digit phone number: ");
         String insert = input.next();
         
         
         
         String sql = "SELECT firstName, lastName, name, description FROM Customer c, Service s WHERE c.serviceID = s.serviceID AND phone = '"+insert+"'";
         ResultSet rs = stmt.executeQuery(sql);
         
         while(rs.next()){
         String first = rs.getString("firstName");
         String last = rs.getString("lastName");
         String service = rs.getString("name");
         String description = rs.getString("description");
         
         System.out.print("Customer " + first + " " + last + " currently has a/an " + service + " which consists of " + description + "." );
         System.out.println();
         }
         rs.close();     
         }    
         catch (SQLException ex) {
             // handle any errors
             System.out.println("SQLException: " + ex.getMessage());
             System.out.println("SQLState: " + ex.getSQLState());
             System.out.println("VendorError: " + ex.getErrorCode());
   	   }
      }


   
   
   private void displayOuts(){
   
      Connection conn = null;
	
	   try {

    	conn =
       DriverManager.getConnection("jdbc:mysql://localhost:3306/store?useSSL=false","root","root");
      Statement stmt = conn.createStatement(); 
      
      String sql = "SELECT * FROM LowItems";
      ResultSet rs = stmt.executeQuery(sql);
      
      while(rs.next()){
      int itemID= rs.getInt("ItemID");
      String name = rs.getString("name");
      int quantity = rs.getInt("quantity");
      
      
      System.out.println("Item number: " + itemID + "   Item: " + name + "   Qty: " + quantity );
      }
      rs.close(); 
      
      
      }
      
          catch (SQLException ex) {
          // handle any errors
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
	   }

   }
   
   
   
   
   private void displayServices(){
   
      Connection conn = null;
   	
   	try {
   
       	conn =
          DriverManager.getConnection("jdbc:mysql://localhost:3306/store?useSSL=false","root","root");
   
       Statement stmt = conn.createStatement();
       
       String sql = "SELECT * FROM Service";
       ResultSet rs = stmt.executeQuery(sql);
       
       
         while(rs.next()){
         int serviceID= rs.getInt("serviceID");
         String name = rs.getString("name");
         String price = rs.getString("price");
         String description = rs.getString("description");
         
         System.out.println("Item number: " + serviceID);
         System.out.println("Item: " + name);
         System.out.println("Price: " + price);
         System.out.println("Description: " + description);
         System.out.println();
         }
         rs.close(); 
   
      }
      
          catch (SQLException ex) {
          // handle any errors
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
	   }

   
   }
   
   
}
