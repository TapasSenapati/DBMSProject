import java.sql.*;
import java.io.*;
import java.util.*;


public class SalesPerson {
    private static int staff_id;

    public static int showMenu(Connection conn) {

	
	int swValue;
	getUser(conn);
	do {
	    
	    System.out.printf("You are logged in as a salesperson, ID=%s\n",staff_id);
	    // Display menu graphics
	    System.out.println("===========================================================");
	    System.out.println("|   AVAILABLE MENU FOR SALESPERSON                        |");
	    System.out.println("===========================================================");
	    System.out.println("|   Choose from one of the Options below:                 |");
	    System.out.println("|        1. Check  Out                                    |");
	    System.out.println("|        2. Place Customer Order                          |");
	    System.out.println("|        3. Add Customer                                  |");
	    System.out.println("|        4. Edit Customer                                 |");
	    System.out.println("|        5. Check Customer Existance                      |");
	    System.out.println("|        6. Change User                                   |");
	    System.out.println("===========================================================");
	    
	    swValue=BooksAThousand.getIntFromShell("Please Select an option: ");
	    
	    // Switch construct
	   
	    switch (swValue) {
	    case 1:
		checkOut(conn);
		break;
	    case 2:
		placeCustomerOrder(conn);
		break;
	    case 3:
		addCustomer(conn);
		break;
	    case 4:
		editCustomer(conn);
		break;
	    case 5:
		checkCustomerExistence(conn);
		break;
	    case 6:
		break;
	    default:
		System.out.println("Invalid selection");
	    }
	} while (swValue != 6);
	return 0;
    }
    
    private static void getUser(Connection conn)
    {
	try{
	    Statement statement = conn.createStatement();
	    ResultSet rs = statement.executeQuery("select * from staff where job_title='salesman'");	
	    
	    if (rs.next()){	      
		System.out.printf("%5s%20s%5s\n", "ID", "name", "age");
		do {
		    System.out.printf("%5s%20s%5s\n", rs.getString(1),rs.getString(2),rs.getString(3));
		} while(rs.next());	
	    }
	    System.out.println("Which salesperson are you ? (enter ID)");
	    Scanner input = new Scanner(System.in);
	    staff_id = input.nextInt();
	}
	catch(Throwable e){
	    e.printStackTrace();
	}
    }
    private static void checkOut(Connection conn){
	try{	
	    int c_id = BooksAThousand.getIntFromShell("Customer's id: ");
	    String isbn = BooksAThousand.getStringFromShell("Book's isbn: ");
	    int quantity = BooksAThousand.getIntFromShell("Quantity:");

	    Statement statement = conn.createStatement();
	    ResultSet rs = statement.executeQuery("select retail_price from merchandise where isbn='"+isbn+"'");
	    rs.next();
	    float price = rs.getFloat(1);	    
	    PreparedStatement ps = conn.prepareStatement("insert into instore_purchase values(?,?,?,?,?,?)");

	    ps.setInt(1, c_id);
	    ps.setString(2, isbn);
	    ps.setInt(3, staff_id);
	    ps.setFloat(5, price);
	    java.util.Date today = new java.util.Date();
	    ps.setDate(6, (new java.sql.Date(today.getTime())));
	    ps.setInt(4, quantity);
	    System.out.printf("That'll be $%d. Thanks \n",(int)price * (int)quantity);
	    ps.executeUpdate();
	} catch (Throwable e) {	
	    e.printStackTrace();
	}
	
	
	
    }

    private static void  placeCustomerOrder(Connection conn)
    {
	try{	
	    int c_id = BooksAThousand.getIntFromShell("Customer's id: ");
	    String isbn = BooksAThousand.getStringFromShell("Book's isbn: ");
	    int quantity = BooksAThousand.getIntFromShell("Quantity:");


	    Statement statement = conn.createStatement();
	    ResultSet rs = statement.executeQuery("select retail_price from merchandise where isbn='"+isbn+"'");
	    rs.next();
	    float price = rs.getFloat(1);	    
	    PreparedStatement ps = conn.prepareStatement("insert into Customer_order values(?,?,?,?,?,?,'N','N')");
	    ps.setInt(1, c_id);
	    ps.setString(2, isbn);
	    ps.setInt(3, quantity);
	    ps.setFloat(4, price); 
	    java.util.Date today = new java.util.Date();
	    ps.setDate(5, (new java.sql.Date(today.getTime())));
	    ps.setInt(6, staff_id);
	    System.out.println("Your order has been placed.");
	    ps.executeUpdate();
	} catch (Throwable e) {	
	    e.printStackTrace();            


	}
	
    }

    private  static void addCustomer(Connection conn)
    {
	
	int id = BooksAThousand.nextID("customer",conn);

	String name = BooksAThousand.getStringFromShell("New customer's name: ");
	String address = BooksAThousand.getStringFromShell("New customer' address: ");
	String phone = BooksAThousand.getStringFromShell("New customer's phone: ");
        String ssn = BooksAThousand.getStringFromShell("New customer's SSN: ");
        String dob = BooksAThousand.getStringFromShell("New customer's DOB (mm-dd-yyyy): ");
        String gender = BooksAThousand.getStringFromShell("Gender (M/F): ");
	
	try{   
	    Statement statement = conn.createStatement();
	    String query = String.format( "insert into Customer "
					  + "values (%s, '%s', '%s', '%s', '%s', to_date('%s','mm-dd-yyyy'), '%s')",
					  id, name, address, phone, ssn, dob, gender);

	    statement.executeUpdate(query);
	    System.out.printf("%s added successfully.\n", name);

	} catch (Throwable e) {	
	System.out.println(id);
	    System.out.println("Failure");
	    e.printStackTrace();
	}
    }

    private static  void editCustomer(Connection conn)
    {

	int id = BooksAThousand.getIntFromShell("Select the customer (id)?");
	String field = BooksAThousand.getStringFromShell("Edit which field?");
	try{
	    Statement statement = conn.createStatement();
	    String query = String.format("select %s from customer where customer_id=%d", field, id);
	    ResultSet rs = statement.executeQuery(query);
	    String current_value;
	    if (rs.next())
		current_value = rs.getString(1);
	    else{
		System.out.println("User not found");
		return;
	    }
	    
	    String new_value = BooksAThousand.getStringFromShell(String.format("New value (currently %s):", current_value));
	    if (field.equals("DOB"))
		query = String.format("update customer set DOB=to_date('%s','mm-dd-yyyy') where customer_id=%d", new_value, id);
	    else
		query = String.format("update customer set %s='%s' where customer_id=%d", field, new_value, id);

	    statement.executeUpdate(query);



	    System.out.println("Customer information updated.");
								 
	    } catch(Throwable e) {
	    e.printStackTrace();
	}
    }
    
    
    
    private static  void checkCustomerExistence(Connection conn)
    {
	//Check if the Customer exists. If there are name matches,
	//show the cashier id/name/phone/address to see if it's the same person

	String name = BooksAThousand.getStringFromShell("Enter the customer's name:");
	try{
		

	    Statement statement = conn.createStatement();
	    ResultSet rs = statement.executeQuery("select * from customer where name='"+ name + "'");
	    if (rs.next()){
		System.out.printf("%5s%20s%15s%20s\n", "ID", "name", "phone", "address");
		do {
		    System.out.printf("%5s%20s%15s%20s\n", rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(3));
		}while(rs.next() );
		
	    }
	    else 
		System.out.println("This customer is not in our database");
	} catch (Throwable e) {	
	    e.printStackTrace();
	}
    } 

}