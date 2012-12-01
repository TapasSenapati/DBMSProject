import java.sql.*;

public class WareHouseStocker{
  public static int showMenu(Connection conn){
    int swValue;
    
    do{
      // Display menu graphics
      System.out.println("===========================================================");
      System.out.println("|   AVAILABLE MENU FOR WARE HOUSE STOCKER                 |");
      System.out.println("===========================================================");
      System.out.println("|   Choose from one of the Options below:                 |");
      System.out.println("|        1. Add a vendor                                  |");
      System.out.println("|        2. Edit a vendor                                 |");
      System.out.println("|        3. Edit a merchandise                            |");
      System.out.println("|        4. Make a vendor order                           |");
      System.out.println("|        5. Check outstanding orders 			            |");
      System.out.println("|        6. Check outstanding orders 			            |");
      System.out.println("|        7. Fulfill a customer order                      |");
      System.out.println("|        8. Fulfill a store order                         |");
      System.out.println("|        9. Exit                                          |");
      System.out.println("===========================================================");
      swValue=BooksAThousand.getIntFromShell("Please Select an option: ");
      // Switch construct
      switch (swValue) {
        case 1:
          addVendor(conn);
          break;
        case 2:
          editVendor(conn);
          break;
        case 3:
          editMerchandiseVendor(conn);
          break;
        case 4:
          makeVendorOrder(conn);
          break;
        case 5:
          checkOutstandingCustomerOrders(conn);
          break;
        case 6:
            checkOutstandingStoreOrders(conn);
            break;
        case 7:
          //fulfillCustOrder(conn);
          break;
        case 8:
//          fulfillStoreOrder(conn);
          break;
        case 9:
//          System.out.println("Option 8");
          break;
        default:
          System.out.println("Invalid selection");
      }
    }while(swValue!=8);
	return 0;
  }

	private static void checkOutstandingCustomerOrders(Connection conn) {

		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from customer_order where is_fulfilled = 'N'");
			System.out.println("Customer ID - ISBN	- Quantity - Price - Date - Staff ID - Fulfilled - Paid");
			if (rs.next()) {
				do {
					System.out.println(rs.getString(1) + '\t' + rs.getString(2) + '\t' + rs.getString(3) + '\t' + rs.getString(4) + '\t' + rs.getString(5).substring(0, 10) + '\t' + rs.getString(6) + '\t' + rs.getString(7) + '\t' + rs.getString(8));
				} while (rs.next());

			} else
				System.out.println("There are no oustanding orders");
			String user_continue = BooksAThousand.getStringFromShell("Please press enter to continue: ");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
}

	private static void checkOutstandingStoreOrders(Connection conn) {

		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from store_order where is_fulfilled = 'N'");
			System.out.println("Store ID - ISBN	- Quantity - Price - Date - Fulfilled");
			if (rs.next()) {
				do {
					System.out.println(rs.getString(1) + '\t' + rs.getString(2) + '\t' + rs.getString(3) + '\t' + rs.getString(4) + '\t' + rs.getString(5).substring(0, 10) + '\t' + rs.getString(6));
				} while (rs.next());

			} else
				System.out.println("There are no oustanding orders");
			String user_continue = BooksAThousand.getStringFromShell("Please press enter to continue: ");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
}

	
	private static void makeVendorOrder(Connection conn) {

		int m_id = BooksAThousand.getIntFromShell("Please enter the isbn :");
		int v_id = BooksAThousand.getIntFromShell("Please enter the vendor id : ");
		int v_quantity = BooksAThousand.getIntFromShell("Please enter the quantity : ");
		int v_price = BooksAThousand.getIntFromShell("Please enter the price : ");
		String v_isPaid = BooksAThousand.getStringFromShell("Please enter the isPaid field: ");
		String v_isFulfilled = BooksAThousand.getStringFromShell("Please enter the isFulfilled field: ");
		String v_date = BooksAThousand.getStringFromShell("Please enter the order date: ");

		try{
		    Statement statement = conn.createStatement();
		    statement.executeUpdate("insert into Warehouse_order values (" + m_id + "," + v_id + "," + v_quantity + "," + v_price + "," + "'" + v_isPaid + "'" + "," +  "'" + v_isFulfilled + "'" + "," +  "'" + v_date + "'" + ")");
		    }
		catch (Throwable e) {	
		    e.printStackTrace();
		}
	}

	private static void editMerchandiseVendor(Connection conn) {

		int m_id = BooksAThousand.getIntFromShell("Enter the isbn of the merchandise be modified: ");
		
		int m_field_num = BooksAThousand.getIntFromShell("Enter the field to be edited: 1 for vendor id, 2 for vendor price, 3 for retail price, 4 for title, 5 for author: ");
		String m_field = null;
		switch (m_field_num) {
		case 1: m_field = "vendor_id";
				break;
		case 2: m_field = "vendor_price";
				break;
		case 3: m_field = "retail_price";
				break;
		case 4: m_field = "title";
				break;
		case 5: m_field = "author";
				break;
		default:	System.out.println("Illegal value");
					break;
		}
		
		String m_edit = BooksAThousand.getStringFromShell("Please enter the new value: ");
		
		try {
			Statement statement = conn.createStatement();
		    statement.executeUpdate("update Merchandise set " + m_field + " = " +  "'" + m_edit + "'" + "where isbn = " + m_id);
		}
		catch (Throwable e) {	
		    e.printStackTrace();
		}	
}

	private static void editVendor(Connection conn) {
		
		int v_id = BooksAThousand.getIntFromShell("Enter the vendor id to be modified: ");
		int v_field_num = BooksAThousand.getIntFromShell("Enter the field to be edited: 1 for vendor name, 2 for vendor address, 3 for vendor phone number: ");
		String v_field = null;
		switch (v_field_num) {
		case 1: v_field = "name";
				break;
		case 2: v_field = "address";
				break;
		case 3: v_field = "phone";
				break;
		default:	System.out.println("Illegal value");
					break;
		}
		
		String v_edit = BooksAThousand.getStringFromShell("Please enter the new value: ");
		
		try {
			Statement statement = conn.createStatement();
		    statement.executeUpdate("update Vendor set " + v_field + " = " +  "'" + v_edit + "'" + "where vendor_id = " + v_id);
		}
		catch (Throwable e) {	
		    e.printStackTrace();
		}
}

	private static void addVendor(Connection conn)
	{
		int v_id = BooksAThousand.getIntFromShell("Please enter the vendor id :");
		String v_name = BooksAThousand.getStringFromShell("Please enter the vendor name : ");
		String v_address = BooksAThousand.getStringFromShell("Please enter the vendor address : ");
		String v_phone = BooksAThousand.getStringFromShell("Please enter the vendor phone number : ");
		try{
		    Statement statement = conn.createStatement();
		    statement.executeUpdate("insert into Vendor values (" + v_id + "," +  "'" + v_name + "'" + "," +  "'" + v_address + "'" + "," +  "'" + v_phone + "'" + ")");
		    }
		catch (Throwable e) {	
		    e.printStackTrace();
		}
	}
}