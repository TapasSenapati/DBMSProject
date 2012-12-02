import java.io.*;
import java.sql.*;

public class BooksAThousand {

 public static int nextID(String table, Connection conn){
	 
	 // This function takes a table name and connection as parameters
	 // it performs a select statement to obtain the maximum id value, currently in use
	 // then it returns the next value of it
	 // this function is used when we are inserting new rows into tables such as vendor, staff etc
	 // this function ensures that the allocation of the id is auto generated and hence, saves the users from
	 // primary key violation issues, when trying to insert with the same unique id
	 
	try{
	    Statement statement = conn.createStatement();
	    String query = String.format("select max(%s_id) from %s", table, table);
	    ResultSet rs = statement.executeQuery(query);			  
	    if(rs.next()) 
		return rs.getInt(1)+1;
	    else
		return 1;
	}
	catch (Throwable e){
	    e.printStackTrace();
	}
	return 1;
    }

  public static String getStringFromShell(String prompt) {
	  // This function gets a string from the terminal
	  
    try {
      System.out.print(prompt);
      return new BufferedReader(new InputStreamReader(System.in))
        .readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static int getIntFromShell(String prompt) {
	  // This function gets an integer from the terminal

    String line = "";
    int num = 0;
    while (line.equals("")) {
      line = getStringFromShell(prompt);
      try {
        num = Integer.parseInt(line);
      } catch (NumberFormatException e) {
        System.out.println("Error: Invalid number");
        line = "";
      }
    }
    return num;
  }
  
  public static float getFloatFromShell(String prompt) {
	  // This function gets a float from the terminal

    String line = "";
    float num = 0;
    while (line.equals("")) {
      line = getStringFromShell(prompt);
      try {
        num = Float.parseFloat(line);
      } catch (NumberFormatException e) {
        System.out.println("Error: Invalid number");
        line = "";
      }
    }
    return num;
  }
  
  static void close(Connection connection) {
	  // This function closes the connection to the database
	  
    if (connection != null) {
      try {
        connection.close();
      } catch (Throwable whatever) {
      }
    }
  }
  
  public static void main(String args[]) {
    // The main function
	  
    String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
    String user = "orsevin"; // user id
    String password = "000751169"; // password
    
    // here, we are establishing the connection to the database
    
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    
    Connection connection = null;
    
    try {
      connection = DriverManager.getConnection(jdbcURL, user, password);
      
      // if connection is established, proceed with the program and display the user menu
            
      String menu_string = "Please Select an option: ";
      int menu_choice = 0;
      
      do {
    	  
  		System.out.println("===========================================================");
  		System.out.println("|   WELCOME TO BOOKS-A-THOUSAND DATABASE SYSTEM           |");
  		System.out.println("===========================================================");
  		System.out.println("|   Choose from one of the Options below:                 |");
  		System.out.println("|        1. SALES PERSON                                  |");
  		System.out.println("|        2. BILLING STAFF                                 |");
  		System.out.println("|        3. WAREHOUSE STOCKER                             |");
  		System.out.println("|        4. BRANCH MANAGER                                |");
  		System.out.println("|        5. CHAIN MANAGER                                 |");
  		System.out.println("|        6. EXIT                                          |");
  		System.out.println("===========================================================");
    	  
        menu_choice = BooksAThousand.getIntFromShell(menu_string);
        switch (menu_choice) {
          case 1:
            SalesPerson.showMenu(connection);
            break;
          case 2:
            BillingStaff.showMenu(connection);
            break;
          case 3:
            WareHouseStocker.showMenu(connection);
            break;
          case 4:
            BranchManager.showMenu(connection);
            break;
          case 5:
            ChainManager.showMenu(connection);
            break;
          case 6:
            System.out.println("Now exiting...");
            break;
          default:
            System.out.println("Please enter a valid value");
        }
      } while (menu_choice != 6);
    } catch (Throwable oops) {
      oops.printStackTrace();
    } finally {
      close(connection); // close the connection when ever the program ends
    }
  }
}
