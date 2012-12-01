import java.sql.*;
import java.io.*;
import java.util.*;

public class SalesPerson {
	public static int showMenu(Connection conn) {
		int swValue;

		do {
			// Display menu graphics
			System.out
					.println("===========================================================");
			System.out
					.println("|   AVAILABLE MENU FOR SALESPERSON                        |");
			System.out
					.println("===========================================================");
			System.out
					.println("|   Choose from one of the Options below:                 |");
			System.out
					.println("|        1. Check  Out                                    |");
			System.out
					.println("|        2. Place Customer Order                          |");
			System.out
					.println("|        3. Add Customer                                  |");
			System.out
					.println("|        4. Edit Customer                                 |");
			System.out
					.println("|        5. Check Customer Existance                      |");
			System.out
					.println("|        6. Change User                                   |");
			System.out
					.println("===========================================================");
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
			    CheckCustomerExistence(conn);
				break;
			case 6:
				break;
			default:
				System.out.println("Invalid selection");
			}
		} while (swValue != 6);
		return 0;
	}
    
    private static void checkOut(Connection conn)
    {
	System.out.println("");
	Scanner input = new Scanner(System.in);

	try{
	    input = new Scanner(System.in);
	    String name = input.nextLine();
	    Statement statement = conn.createStatement();
	    ResultSet rs = statement.executeQuery("select * from customer where name='" + name + "'");
	    if (rs.next()){	      
		do {
		}while(rs.next() );
		
	    }
	    else 
		System.out.println("This customer is not in our database");
	} catch (Throwable e) {	
	    e.printStackTrace();
	}
    }

    private static void placeCustomerOrder(Connection conn)
    {
	System.out.println("");
	Scanner input = new Scanner(System.in);

	try{
	    input = new Scanner(System.in);
	    String name = input.nextLine();
	    Statement statement = conn.createStatement();
	    ResultSet rs = statement.executeQuery("");
	    if (rs.next()){	      
		do {
		}while(rs.next() );
		
	    }
	    else 
		System.out.println("This customer is not in our database");
	} catch (Throwable e) {	
	    e.printStackTrace();
	}
    }

    private static void addCustomer(Connection conn)
    {
	System.out.println("");
	Scanner input = new Scanner(System.in);

	try{
	    input = new Scanner(System.in);
	    String name = input.nextLine();
	    Statement statement = conn.createStatement();
	    ResultSet rs = statement.executeQuery("");
	    if (rs.next()){	      
		do {
		}while(rs.next() );
	    
	    }
	    else 
		System.out.println("This customer is not in our database");
	} catch (Throwable e) {	
	    e.printStackTrace();
	}
    }

    private static void editCustomer(Connection conn)
    {
	System.out.println("");
	Scanner input = new Scanner(System.in);

	try{
	    input = new Scanner(System.in);
	    String name = input.nextLine();
	    Statement statement = conn.createStatement();
	    ResultSet rs = statement.executeQuery("select * from customer where name='" + name + "'");
	    if (rs.next()){	      
		do {
		}while(rs.next() );
		
	    }
	    else 
		System.out.println("This customer is not in our database");
	} catch (Throwable e) {	
	    e.printStackTrace();
	}
    }

    private static void CheckCustomerExistence(Connection conn)
    {
	//Check if the Customer exists. If there are name matches,
	//    show the cashier id/name/phone/address
	System.out.println("Name?");
	Scanner input = new Scanner(System.in);

	try{
	    input = new Scanner(System.in);
	    String name = input.nextLine();
	    Statement statement = conn.createStatement();
	    ResultSet rs = statement.executeQuery("");
	    if (rs.next()){
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