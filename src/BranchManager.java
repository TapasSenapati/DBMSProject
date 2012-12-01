import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BranchManager{
  public static int showMenu(Connection conn){
    int swValue;
    
    do{
      // Display menu graphics
      System.out.println("===========================================================");
      System.out.println("|   AVAILABLE MENU FOR BRANCH MANAGER                     |");
      System.out.println("===========================================================");
      System.out.println("|   Choose from one of the Options below:                 |");
      System.out.println("|        1. Request a stock                               |");
      System.out.println("|        2. Add a staff                                   |");
      System.out.println("|        3. Edit a Staff                                  |");
      System.out.println("|        4. Purchase History of Customer                  |");
      System.out.println("|        5. Check Customers Assisted by a staff           |");
      System.out.println("|        6. Edit a store                                  |");
      System.out.println("|        7. Exit                                          |");
      System.out.println("===========================================================");
      swValue=BooksAThousand.getIntFromShell("Please Select an option: ");
      // Switch construct
      switch (swValue) {
        case 1:
          requestStock(conn);
          break;
        case 2:
          addStaff(conn);
          break;
        case 3:
          editStaff(conn);
          break;
        case 4:
          purchaseHistory(conn);
          break;
        case 5:
          customersAssisted(conn);
          break;
        case 6:
          editStore(conn);
          break;
        case 7:
          System.out.println("Exiting Branch Manager menu....");
          break;
        default:
          System.out.println("Invalid selection");
      }
    }while(swValue!=7);
    return 0;
  }
  private static void requestStock(Connection conn)
  {
    System.out.println("");
    
    int store_id = BooksAThousand.getIntFromShell("Please enter the store id: ");
    String isbn = BooksAThousand.getStringFromShell("Please input ISBN no: ");
    int qty = BooksAThousand.getIntFromShell("Please input the quantity: ");
    float price = BooksAThousand.getFloatFromShell("Please input the price of Stock: ");
    String store_order_date = BooksAThousand.getStringFromShell("Please input store order date: ");
    String is_fulfilled = BooksAThousand.getStringFromShell("Please input if order fulfilled or not (Y/N): ");
    
    try{
      Statement statement = conn.createStatement();
      //insert into store_order values(3, '978-1570762604', 4, 200, to_date('2012-08-03','yyyy-mm-dd'),  'Y');
      statement.executeUpdate("insert into store_order values(" + store_id + "," + "'" + isbn + "'" + "," + qty + "," + price + "," + "'" + store_order_date + "'" + "," + "'" + is_fulfilled + "'");
    }
    catch (Throwable e) { 
      e.printStackTrace();
    }
  }
  
  private static void addStaff(Connection conn)
  {
    System.out.println("");
    
    int ID = BooksAThousand.getIntFromShell("Please enter staff id: ");
    String name = BooksAThousand.getStringFromShell("Please enter staff name: ");
    int age = BooksAThousand.getIntFromShell("Please enter staff age: ");
    String gender = BooksAThousand.getStringFromShell("Please enter staff gender(M or F): ");
    float salary = BooksAThousand.getFloatFromShell("Please enter satff salary: ");
    String job_title = BooksAThousand.getStringFromShell("Please enter staff title: ");
    int store_id = BooksAThousand.getIntFromShell("Please enter store id: ");
    String phone = BooksAThousand.getStringFromShell("Please enter staff phone number: ");
    String address = BooksAThousand.getStringFromShell("Please enter  staff address: ");
    String is_active = BooksAThousand.getStringFromShell("Please enter whether active(Y/N): ");
    try{
      Statement statement = conn.createStatement();
      statement.executeUpdate("insert into staff values(" + ID + "," + "'" + name + "'" + "," + age + "," + "," + "'" + gender + "'"+ salary + "," + "'" + job_title + "'" + store_id + "'" + "," + "'" + phone + "'" + "," + "'" + address + "'" + "," + "'" + is_active + "'");
    }
    catch (Throwable e) { 
      e.printStackTrace();
    }
  }
  
  private static void editStaff(Connection conn)
  {
    System.out.println("");
    int s_id = BooksAThousand.getIntFromShell("Please enter staff id: ");
    int field_num = BooksAThousand.getIntFromShell("Enter the field number to be edited: e.g 1 for staff name, 2 for staff age, 3 for gender, 4 for salary, 5 for job title, 6 for store id, 7 for phone,8 for address,9 for is active");
    String s_field = null;
    switch (field_num) {
      case 1: s_field = "name";
      break;
      case 2: s_field = "age";
      break;
      case 3: s_field = "gender";
      break;
      case 4: s_field = "salary";
      break;
      case 5: s_field = "job_title";
      break;
      case 6: s_field = "store_id";
      break;
      case 7: s_field = "phone";
      break;
      case 8: s_field = "address";
      break;
      case 9: s_field = "is_active";
      break;
      default: System.out.println("Illegal value");
      break;
    }
    
    if((field_num == 1) || (field_num == 3) || (field_num == 5) || (field_num == 7) || (field_num == 8) || (field_num == 9)){
      String s_edit = BooksAThousand.getStringFromShell("Please enter the new value: ");
      try {
        Statement statement = conn.createStatement();
        statement.executeUpdate("update staff set " + s_field + " = " +  "'" + s_edit + "'" + "where staff_id = " + s_id);
      }
      catch (Throwable e) { 
        e.printStackTrace();
      }
    }
    else if( field_num == 2 || field_num == 6)
    {
      int s_edit = BooksAThousand.getIntFromShell("Please enter the new value: ");
      try {
        Statement statement = conn.createStatement();
        statement.executeUpdate("update staff set " + s_field + " = " +  "'" + s_edit + "'" + "where staff_id = " + s_id);
      }
      catch (Throwable e) { 
        e.printStackTrace();
      }
    }
    else 
    {
      float s_edit = BooksAThousand.getFloatFromShell("Please enter the new value: ");
      try {
        Statement statement = conn.createStatement();
        statement.executeUpdate("update staff set " + s_field + " = " +  "'" + s_edit + "'" + "where staff_id = " + s_id);
      }
      catch (Throwable e) { 
        e.printStackTrace();
      }
    }    
  }
  private static void purchaseHistory(Connection conn)
  {
    System.out.println("");
    
    int customer_id = BooksAThousand.getIntFromShell("Please enter the customer id: ");
    String date1= BooksAThousand.getStringFromShell("Please input start date: ");
    String date2= BooksAThousand.getStringFromShell("Please input end date: ");
    
    try {
      Statement statement = conn.createStatement();
      String query1 = "(select ISBN, quantity from instore_purchase where customer_id = " + customer_id + " and purchase_date > '" + date1 + "'" + " and purchase_date < '" + date2 + "')";
      String query2 = " UNION (select ISBN, quantity from customer_order where customer_id = " + customer_id + " and customer_order_date > '" + date1 + "'" + " and customer_order_date < '" + date2 + "')";
      String query = query1 + query2;
      System.out.println(query);
      ResultSet rs = statement.executeQuery(query);
      System.out.println( "ISBN" + '\t' + "Quantity");
      if (rs.next()) {
        do {
          System.out.println(rs.getString(1) + '\t' + rs.getString(2));
        } while (rs.next());
        
      } else
        System.out.println("Sorry.No purchase history found.");
      String user_continue = BooksAThousand.getStringFromShell("Please press enter to continue: ");
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }
   private static void customersAssisted(Connection conn)
  {
    System.out.println("");
    
    int staff_id = BooksAThousand.getIntFromShell("Please enter the staff id: ");
    String date1= BooksAThousand.getStringFromShell("Please input start date: ");
    String date2= BooksAThousand.getStringFromShell("Please input end date: ");
    
    try {
      Statement statement = conn.createStatement();
      String query1 = "(select c.customer_id, name, purchase_date from instore_purchase ip, customer c where staff_id = " + staff_id + " and purchase_date > '" + date1 + "'" + " and purchase_date < '" + date2 + "' and c.customer_id = ip.customer_id)";
      String query2 = " UNION (select c.customer_id, name, customer_order_date from customer_order co, customer c where staff_id = " + staff_id + " and customer_order_date > '" + date1 + "'" + " and customer_order_date < '" + date2 + "' and c.customer_id = co.customer_id)";
      String query = query1 + query2;
      System.out.println(query);
      ResultSet rs = statement.executeQuery(query);
      System.out.println( "Customer_ID" + '\t' + "Name");
      if (rs.next()) {
        do {
          System.out.println(rs.getString(1) + '\t' + rs.getString(2));
        } while (rs.next());
        
      } else
        System.out.println("Sorry.No customers assisted in the period.");
      String user_continue = BooksAThousand.getStringFromShell("Please press enter to continue: ");
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }
  private static void editStore(Connection conn)
  {
    System.out.println("");
    int s_id = BooksAThousand.getIntFromShell("Please enter store id: ");
    int field_num = BooksAThousand.getIntFromShell("Enter the field number to be edited: e.g 1 for store name, 2 for store address or 3 for store phone no.");
    String s_field = null;
    switch (field_num) {
      case 1: s_field = "name";
      break;
      case 2: s_field = "address";
      break;
      case 3: s_field = "phone";
      break;
      default: System.out.println("Illegal value");
      break;
    }
    
    String s_edit = BooksAThousand.getStringFromShell("Please enter the new value: ");
    try {
      Statement statement = conn.createStatement();
      statement.executeUpdate("update store set " + s_field + " = " +  "'" + s_edit + "'" + "where store_id = " + s_id);
    }
    catch (Throwable e) { 
      e.printStackTrace();
    }
  }
  
}    
