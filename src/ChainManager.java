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
      // Ask for an option from user
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
    }while(swValue!=6); // If user enters 6 then exit
    return 0;
  }
  
  private static void addStore(Connection conn)  
  {
	  // Input from user all the Store table fields
	  int store_id = BooksAThousand.getIntFromShell("Please enter the store id: ");
	  String store_name = BooksAThousand.getStringFromShell("Please enter the store name : ");
	  String store_address = BooksAThousand.getStringFromShell("Please enter the store address : ");
	  String store_phone = BooksAThousand.getStringFromShell("Please enter the store phone number : ");
	  
	  // Insert a new store in the table
	  try{
	    Statement statement = conn.createStatement();
		statement.executeUpdate("insert into Store values (" + store_id + "," +  "'" + store_name + "'" + ","
			+  "'" + store_address + "'" + "," +  "'" + store_phone + "'" + ")");
		System.out.println("Store added successfully");
	  }
	  catch (Throwable e) {	
	    e.printStackTrace();
	  }
  }
  
  private static void removeStore(Connection conn)
  {
	  // Input from user the Store ID to be removed
	  int store_id = BooksAThousand.getIntFromShell("Please enter the store id to be removed : ");
	  
	  // Delete the row from the Store table
	  try{
	    Statement statement = conn.createStatement();
	    statement.executeUpdate("delete from Store where store_id = " + store_id);
	    System.out.println("Store deleted successfully");
	  }
	  catch (Throwable e) {	
	    e.printStackTrace();
	  }
  }
  private static void addMerchandise(Connection conn)
  {	  
	  // Input from user all the Merchandise table fields
	  String isbn = BooksAThousand.getStringFromShell("Please enter the isbn : ");
	  int vendor_id = BooksAThousand.getIntFromShell("Please enter the vendor id : ");
	  float vendor_price = BooksAThousand.getFloatFromShell("Please enter the vendor price : ");
	  float retail_price = BooksAThousand.getFloatFromShell("Please enter the retail price : ");
	  String title = BooksAThousand.getStringFromShell("Please enter the title : ");
	  String author = BooksAThousand.getStringFromShell("Please enter the author name : ");
	  // Insert new merchandise in the table
	  try{
	    Statement statement = conn.createStatement();
		statement.executeUpdate("insert into Merchandise values ('" + isbn + "'" + "," + vendor_id + "," + vendor_price
				+ "," + retail_price + "," +  "'" + title + "'" + "," +  "'" + author + "'" + ")");
		System.out.println("Merchandise added successfully");
	  }
	  catch (Throwable e) {	
	    e.printStackTrace();
	  }
  }
  private static void removeMerchandise(Connection conn)
  {	  
	  // Input from user the Merchandise's ISBN to be removed
	  String isbn = BooksAThousand.getStringFromShell("Please enter the isbn to be removed : ");
	  
	  // Delete the row from the Merchandise table
	  try{
	    Statement statement = conn.createStatement();
		statement.executeUpdate("delete from Merchandise where ISBN = '" + isbn + "'");
		System.out.println("Merchandise deleted successfully");
	  }
	  catch (Throwable e) {	
	    e.printStackTrace();
	  }
  }
  private static void changeRetailPrice(Connection conn)
  {
	  // Input from user the isbn and new retail price
	  String isbn = BooksAThousand.getStringFromShell("Please enter the isbn : ");	  
	  float retail_price = BooksAThousand.getFloatFromShell("Please enter the new retail price : ");
	  
	  // Update the retail price in the table
	  try{
	    Statement statement = conn.createStatement();
		statement.executeUpdate("update Merchandise set retail_price = " + retail_price + " where ISBN = '" + isbn + "'");
		System.out.println("Retail price updated successfully");
	  }
	  catch (Throwable e) {	
	    e.printStackTrace();
	  }
  }
}