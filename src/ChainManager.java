import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ChainManager{
  public static int showMenu(Connection conn) {
		
    int swValue;
    
    do{
      // Display menu graphics
      System.out.println("===========================================================");
      System.out.println("|   AVAILABLE MENU FOR CHAIN MANAGER                      |");
      System.out.println("===========================================================");
      System.out.println("|   Choose from one of the Options below:                 |");
      System.out.println("|        1. Add a store                                   |");
      System.out.println("|        2. Remove a store                                |");
      System.out.println("|        3. Add new Merhandise                            |");
      System.out.println("|        4. Remove a Merchandise                          |");
      System.out.println("|        5. Change Retail Price                           |");
      System.out.println("|        6. Exit                                          |");
      System.out.println("===========================================================");
      swValue=BooksAThousand.getIntFromShell("Please Select an option: ");    
      // Switch construct
      switch (swValue) {
        case 1:
          addStore(conn);	
          break;
        case 2:
          removeStore(conn);
          break;
        case 3:
          addMerchandise(conn);	
          break;
        case 4:
          removeMerchandise(conn);
          break;
        case 5:
          changeRetailPrice(conn);
          break;
        case 6:
          break;
        default:
          System.out.println("Invalid selection");
      }
    }while(swValue!=6);
    return 0;
  }
  
  private static void addStore(Connection conn)  
  {
	  int store_id = BooksAThousand.getIntFromShell("Please enter the store id: ");
	  String store_address = BooksAThousand.getStringFromShell("Please enter the store address : ");
	  String store_phone = BooksAThousand.getStringFromShell("Please enter the store phone number : ");
	  try{
	    Statement statement = conn.createStatement();
		statement.executeUpdate("insert into Store values (" + store_id + "," +  "'" + store_address + "'" + "," +  "'" + store_phone + "'" + ")");
	  }
	  catch (Throwable e) {	
	    e.printStackTrace();
	  }
  }
  
  private static void removeStore(Connection conn)
  {
	  int store_id = BooksAThousand.getIntFromShell("Please enter the store id to be removed : ");
	  
	  try{
	    Statement statement = conn.createStatement();
	    statement.executeUpdate("delete from Store where store_id = " + store_id);
	  }
	  catch (Throwable e) {	
	    e.printStackTrace();
	  }
  }
  private static void addMerchandise(Connection conn)
  {	  
	  String isbn = BooksAThousand.getStringFromShell("Please enter the isbn : ");
	  int vendor_id = BooksAThousand.getIntFromShell("Please enter the vendor id : ");
	  float vendor_price = BooksAThousand.getFloatFromShell("Please enter the vendor price : ");
	  float retail_price = BooksAThousand.getFloatFromShell("Please enter the retail price : ");
	  
	  try{
	    Statement statement = conn.createStatement();
		statement.executeUpdate("insert into Merchandise values ('" + isbn + "'" + "," + vendor_id + "," + vendor_price + "," + retail_price + ")");
	  }
	  catch (Throwable e) {	
	    e.printStackTrace();
	  }
  }
  private static void removeMerchandise(Connection conn)
  {	  
	  String isbn = BooksAThousand.getStringFromShell("Please enter the isbn to be removed : ");
	  try{
	    Statement statement = conn.createStatement();
		statement.executeUpdate("delete from Merchandise where ISBN = '" + isbn + "'");
	  }
	  catch (Throwable e) {	
	    e.printStackTrace();
	  }
  }
  private static void changeRetailPrice(Connection conn)
  {
	  String isbn = BooksAThousand.getStringFromShell("Please enter the isbn : ");	  
	  float retail_price = BooksAThousand.getFloatFromShell("Please enter the new retail price : ");
	  try{
	    Statement statement = conn.createStatement();
		statement.executeUpdate("update Merchandise set retail_price = " + retail_price + " where ISBN = '" + isbn + "'");
	  }
	  catch (Throwable e) {	
	    e.printStackTrace();
	  }
  }
}public class ChainManager{
  public static void main(String[] args){
    int swValue;
    
    do{
      // Display menu graphics
      System.out.println("===========================================================");
      System.out.println("|   AVAILABLE MENU FOR CHAIN MANAGER                      |");
      System.out.println("===========================================================");
      System.out.println("|   Choose from one of the Options below:                 |");
      System.out.println("|        1. Add a store                                   |");
      System.out.println("|        2. Remove a store                                |");
      System.out.println("|        3. Add new Merhandise                            |");
      System.out.println("|        4. Remove a Merchandise                          |");
      System.out.println("|        5. Change Retail Price                           |");
      System.out.println("|        6. Exit                                          |");
      System.out.println("===========================================================");
      swValue=BooksAThousand.getIntFromShell("Please Select an option: ");    
      // Switch construct
      switch (swValue) {
        case 1:
          System.out.println("Option 1 selected");
          break;
        case 2:
          System.out.println("Option 2 selected");
          break;
        case 3:
          System.out.println("Option 3");
          break;
        case 4:
          System.out.println("Option 4");
          break;
        case 5:
          System.out.println("Option 5");
          break;
        case 6:
          System.out.println("Option 6");
          break;
        default:
          System.out.println("Invalid selection");
      }
    }while(swValue!=6);
  }
}