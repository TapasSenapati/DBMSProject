import java.io.*;
import java.sql.*;
import java.text.*;

public class BillingStaff {
    
    public static void showMenu(Connection conn) {
	    //For this demo we won't have people login as a billing person because 
	    //they all have the same privileges (not store-specific). 
	    
	    int swValue;
	    
	    do {
		
		System.out.println("You are logged in as a billing staff member");
		// Display menu graphics
		System.out.println("===========================================================");
		System.out.println("|   AVAILABLE MENU FOR BILLING STAFF                       |");
		System.out.println("===========================================================");
		System.out.println("|   Choose from one of the Options below:                 |");
		System.out.println("|        1. Generate a customer bill                      |");
		System.out.println("|        2. Generate a vendor bill                        |");
		System.out.println("|        3. Process customer payment                      |");
		System.out.println("|        4. Process vendor payment                        |");
		System.out.println("|        5. Exit                                          |");
		System.out.println("===========================================================");
		swValue = BooksAThousand.getIntFromShell("Please Select an option: ");
		
		// Switch construct
		switch (swValue) {	    
		case 1:
		    GenerateCustomerBill(conn);
		    break;
		case 2:
		    GenerateVendorBill(conn);
		    break;
		case 3:
		    ProcessCustomerPayment(conn);
		    break;
		case 4:
		    ProcessVendorPayment(conn);
		    break;
		case 5:
		    break;
		default:
		    System.out.println("Invalid selection");
		}
	    } while (swValue != 5);
    }

    public static void ProcessCustomerPayment(Connection conn){
	// This function updates is_paid so billing staff can keep track of the payments customers have made   

	int c_id = BooksAThousand.getIntFromShell("Enter customer ID: ");

	try { 
	    Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    ResultSet rs = statement.executeQuery("select customer_id, isbn, quantity, price, customer_order_date, is_paid"+
						  " from customer_order where customer_id="+ c_id +" and is_paid='N'");
	    if (rs.next()) {
		System.out.println("Outstanding payments:");
		System.out.printf("Order  %-10s %-5s %-5s %-10s %-10s\n", "ISBN", "Quant", "Price", "Total", "Date");
		Date date;
		int i =0;
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm");
		do {
		    i++;
		    date = rs.getDate(5);
		    System.out.printf("%-5s: %-10s %-5d %-5d %-10d %-10s \n",
				      i, rs.getInt(2),  rs.getInt(3),rs.getInt(4), 
				      rs.getInt(3)*rs.getInt(4), sdf.format(date));
		} while (rs.next());
		int order = BooksAThousand.getIntFromShell("Which order to mark as paid: ");		
		rs.absolute(i);
		rs.updateString(6,"Y");
		rs.updateRow();
		System.out.println("Update successful.");

	    }
	    else {
		System.out.println("This user has no unpaid orders");
	    }
	}	
	catch (Throwable e) {
	    e.printStackTrace();
	}	
    }

    public static void ProcessVendorPayment(Connection conn){
	// This function updates is_paid so billing staff can keep track of the payments they've made to vendors
	
	
	int v_id = BooksAThousand.getIntFromShell("Enter vendor ID: ");
	try { 
	    Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    ResultSet rs = statement.executeQuery("select vendor_id, isbn, quantity, price, warehouse_order_date, is_paid"+
						  " from warehouse_order where vendor_id="+ v_id +" and is_paid='N'");
	    if (rs.next()) {
		System.out.println("Outstanding payments:");
		System.out.printf("Order  %-10s %-5s %-5s %-10s %-10s\n", "ISBN", "Quant", "Price", "Total", "Date");
		Date date;
		int i =0;
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm");
		do {
		    i++;
		    date = rs.getDate(5);
		    System.out.printf("%-5s: %-10s %-5d %-5d %-10d %-10s \n",
				      i, rs.getInt(2),  rs.getInt(3),rs.getInt(4), 
				      rs.getInt(3)*rs.getInt(4), sdf.format(date));
		} while (rs.next());
		int order = BooksAThousand.getIntFromShell("Which order to mark as paid: ");		
		rs.absolute(i);
		rs.updateString(6,"Y");
		rs.updateRow();
		System.out.println("Update successful.");
	    }
	    else 
		System.out.println("We owe nothing to this vendor");
	    
	}
	catch (Throwable e) {
	    e.printStackTrace();	    
	}
    }	


    public static void GenerateCustomerBill(Connection conn){
	// We intended to produce the bills over certain time periods but
	// we will offer an option to check just the last 3 months. 
	try { 
	    int c_id = BooksAThousand.getIntFromShell("Enter customer ID: ");
	    Statement statement = conn.createStatement();
	    ResultSet rs = statement.executeQuery("select address, name from customer where customer_id = " + c_id);
	    rs.next();
	    String address = rs.getString(1);
	    String name = rs.getString(2);
	    rs = statement.executeQuery("select customer_id, isbn, quantity, price, customer_order_date"+
					" from customer_order where customer_id="+ c_id +" and is_paid='N'");
	    float total = 0;
	    if (rs.next()) {
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm");
		System.out.println("\n"+address);
		System.out.println("\nDear " + name + ", \nBelow are your unpaid orders. Pay up.\n");
		System.out.printf("%-10s %-20s %-5s %-8s %-11s\n", "ISBN", "Date", "Quant", "Price", "Total");

		do {
		    total += rs.getInt(3)*rs.getFloat(4);
		    date = rs.getDate(5);
		    System.out.printf("%-10s %-20s %-5d $%-7.2f $%-10.2f \n", rs.getInt(2), sdf.format(date),  
				      rs.getInt(3),rs.getFloat(4), rs.getInt(3)*rs.getFloat(4) );
		} while (rs.next());
		System.out.printf("Total: $%.2f\n", total);	       
	    }
	    else {
		System.out.println("This user has no unpaid orders");
		
	    }
	}	
	catch (Throwable e) {
	    e.printStackTrace();
	}	
    }

    public static void GenerateVendorBill(Connection conn){
	// We intended to produce the bills over certain time periods but
	// we will offer an option to check just the last 3 months. 
	try { 
	    int v_id = BooksAThousand.getIntFromShell("Enter vendor ID: ");
	    Statement statement = conn.createStatement();
	    ResultSet rs = statement.executeQuery("select name from vendor where vendor_id = " + v_id);
	    rs.next();
	    String name = rs.getString(1);
	    rs = statement.executeQuery("select vendor_id, isbn, quantity, price, warehouse_order_date" +
					" from warehouse_order where vendor_id="+ v_id +" and is_paid='N'");
	    float total = 0;
	    if (rs.next()) {
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm");
		System.out.printf("%-10s %-20s %-5s %-8s %-11s\n", "ISBN", "Date", "Quant", "Price", "Total");
		do {
		    total += rs.getInt(3)*rs.getFloat(4);
		    date = rs.getDate(5);
		    System.out.printf("%-10s %-20s %-5d $%-7.2f $%-10.2f \n", rs.getInt(2), sdf.format(date),  
				      rs.getInt(3),rs.getFloat(4), rs.getInt(3)*rs.getFloat(4) );
		} while (rs.next());
		System.out.printf("We owe %s a total of $%.2f\n", name, total);	       
	    }
	    else {
		System.out.println("This user has no unpaid orders");
		
	    }
	}	
	catch (Throwable e) {
	    e.printStackTrace();
	}	
    }

    }