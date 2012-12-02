import java.sql.*;

public class WareHouseStocker {
	public static int showMenu(Connection conn) {
		int swValue;

		do {
			// Display menu graphics
			System.out
					.println("===========================================================");
			System.out
					.println("|   AVAILABLE MENU FOR WARE HOUSE STOCKER                 |");
			System.out
					.println("===========================================================");
			System.out
					.println("|   Choose from one of the Options below:                 |");
			System.out
					.println("|        1. Add a vendor                                  |");
			System.out
					.println("|        2. Edit a vendor                                 |");
			System.out
					.println("|        3. Edit a merchandise                            |");
			System.out
					.println("|        4. Make a vendor order                           |");
			System.out
					.println("|        5. Check outstanding orders 			            |");
			System.out
					.println("|        6. Check outstanding orders 			            |");
			System.out
					.println("|        7. Fulfill a customer order                      |");
			System.out
					.println("|        8. Fulfill a store order                         |");
			System.out
					.println("|        9. Exit                                          |");
			System.out
					.println("===========================================================");
			swValue = BooksAThousand
					.getIntFromShell("Please Select an option: ");
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
				fulfillCustOrder(conn);
				break;
			case 8:
				fulfillStoreOrder(conn);
				break;
			case 9:
				// System.out.println("Now leaving");
				break;
			default:
				System.out.println("Invalid selection");
			}
		} while (swValue != 8);
		return 0;
	}

	private static void fulfillStoreOrder(Connection conn) {

		int c_id = BooksAThousand
				.getIntFromShell("Please enter the store id :");
		int m_id = BooksAThousand.getIntFromShell("Please enter the isbn :");
		int c_quantity = BooksAThousand
				.getIntFromShell("Please enter the quantity :");
		String c_date = BooksAThousand
				.getStringFromShell("Please enter the order date : ");

		try {
			conn.setAutoCommit(false);

			Statement statement1 = conn.createStatement();
			Statement statement2 = conn.createStatement();
			Statement statement3 = conn.createStatement();
			System.out.println("Store order table has not been updated yet");
			statement1.executeUpdate("update store_order set is_fulfilled = 'Y' where store_id = " + c_id + " and store_order_date = " + "'" + c_date + "'");
			System.out.println("Store order table has been updated");
			ResultSet rs = statement2.executeQuery("select * from stock where store_id = "
							+ c_id + " and ISBN = " + m_id);
			if (rs.next() == false) {
				statement2.executeUpdate("insert into stock values (" + m_id
						+ "," + c_id + "," + "0" + ")");
			}
			statement2.executeUpdate("update stock set quantity = quantity - "
					+ c_quantity + " where store_id = 2 and ISBN = " + m_id);
			System.out.println("Stock table has been updated for warehouse");
			statement3.executeUpdate("update stock set quantity = quantity + "
					+ c_quantity + " where store_id = " + c_id + " and ISBN = "
					+ m_id);
			System.out.println("Stock table has been updated for warehouse");
			conn.commit();
			String user_continue = BooksAThousand
					.getStringFromShell("Transaction successful and commit was performed. Press enter to continue: ");
		} catch (Throwable e) {
			try {
				e.printStackTrace();
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			String user_continue = BooksAThousand
					.getStringFromShell("Transaction failed. Rollback was performed. Press enter to continue: ");
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void fulfillCustOrder(Connection conn) {

		int c_id = BooksAThousand
				.getIntFromShell("Please enter the customer id :");
		int m_id = BooksAThousand.getIntFromShell("Please enter the isbn :");
		int c_quantity = BooksAThousand
				.getIntFromShell("Please enter the quantity :");
		String c_date = BooksAThousand
				.getStringFromShell("Please enter the order date : ");

		try {
			conn.setAutoCommit(false);

			Statement statement1 = conn.createStatement();
			Statement statement2 = conn.createStatement();

			statement1
					.executeUpdate("update customer_order set is_fulfilled = 'Y' where customer_id = "
							+ c_id
							+ " and customer_order_date = "
							+ "'"
							+ c_date + "'" + " and ISBN = " + m_id);
			System.out.println("Customer order table has been updated");
			statement2.executeUpdate("update stock set quantity = quantity - "
					+ c_quantity + " where store_id = 2 and ISBN = " + m_id);
			System.out.println("Stock table has been updated");
			conn.commit();
			String user_continue = BooksAThousand
					.getStringFromShell("Transaction successful and commit was performed. Press enter to continue: ");
		} catch (Throwable e) {
			try {
				e.printStackTrace();
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			String user_continue = BooksAThousand
					.getStringFromShell("Transaction failed. Rollback was performed. Press enter to continue: ");
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void checkOutstandingCustomerOrders(Connection conn) {

		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement
					.executeQuery("select * from customer_order where is_fulfilled = 'N'");
			System.out
					.println("Customer ID - ISBN	- Quantity - Price - Date - Staff ID - Fulfilled - Paid");
			if (rs.next()) {
				do {
					System.out.println(rs.getString(1) + '\t' + rs.getString(2)
							+ '\t' + rs.getString(3) + '\t' + rs.getString(4)
							+ '\t' + rs.getString(5).substring(0, 10) + '\t'
							+ rs.getString(6) + '\t' + rs.getString(7) + '\t'
							+ rs.getString(8));
				} while (rs.next());

			} else
				System.out.println("There are no oustanding orders");
			String user_continue = BooksAThousand
					.getStringFromShell("Please press enter to continue: ");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void checkOutstandingStoreOrders(Connection conn) {

		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement
					.executeQuery("select * from store_order where is_fulfilled = 'N'");
			System.out
					.println("Store ID - ISBN	- Quantity - Price - Date - Fulfilled");
			if (rs.next()) {
				do {
					System.out.println(rs.getString(1) + '\t' + rs.getString(2)
							+ '\t' + rs.getString(3) + '\t' + rs.getString(4)
							+ '\t' + rs.getString(5).substring(0, 10) + '\t'
							+ rs.getString(6));
				} while (rs.next());

			} else
				System.out.println("There are no oustanding orders");
			String user_continue = BooksAThousand
					.getStringFromShell("Please press enter to continue: ");
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	private static void makeVendorOrder(Connection conn) {

		int m_id = BooksAThousand.getIntFromShell("Please enter the isbn :");
		int v_id = BooksAThousand
				.getIntFromShell("Please enter the vendor id : ");
		int v_quantity = BooksAThousand
				.getIntFromShell("Please enter the quantity : ");
		int v_price = BooksAThousand
				.getIntFromShell("Please enter the price : ");
		String v_isPaid = BooksAThousand
				.getStringFromShell("Please enter the isPaid field: ");
		String v_isFulfilled = BooksAThousand
				.getStringFromShell("Please enter the isFulfilled field: ");
		String v_date = BooksAThousand
				.getStringFromShell("Please enter the order date: ");

		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("insert into Warehouse_order values ("
					+ m_id + "," + v_id + "," + v_quantity + "," + v_price
					+ "," + "'" + v_isPaid + "'" + "," + "'" + v_isFulfilled
					+ "'" + "," + "'" + v_date + "'" + ")");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void editMerchandiseVendor(Connection conn) {

		int m_id = BooksAThousand
				.getIntFromShell("Enter the isbn of the merchandise be modified: ");

		int m_field_num = BooksAThousand
				.getIntFromShell("Enter the field to be edited: 1 for vendor id, 2 for vendor price, 3 for retail price, 4 for title, 5 for author: ");
		String m_field = null;
		switch (m_field_num) {
		case 1:
			m_field = "vendor_id";
			break;
		case 2:
			m_field = "vendor_price";
			break;
		case 3:
			m_field = "retail_price";
			break;
		case 4:
			m_field = "title";
			break;
		case 5:
			m_field = "author";
			break;
		default:
			System.out.println("Illegal value");
			break;
		}

		String m_edit = BooksAThousand
				.getStringFromShell("Please enter the new value: ");

		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("update Merchandise set " + m_field + " = "
					+ "'" + m_edit + "'" + "where isbn = " + m_id);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void editVendor(Connection conn) {

		int v_id = BooksAThousand
				.getIntFromShell("Enter the vendor id to be modified: ");
		int v_field_num = BooksAThousand
				.getIntFromShell("Enter the field to be edited: 1 for vendor name, 2 for vendor address, 3 for vendor phone number: ");
		String v_field = null;
		switch (v_field_num) {
		case 1:
			v_field = "name";
			break;
		case 2:
			v_field = "address";
			break;
		case 3:
			v_field = "phone";
			break;
		default:
			System.out.println("Illegal value");
			break;
		}

		String v_edit = BooksAThousand
				.getStringFromShell("Please enter the new value: ");

		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("update Vendor set " + v_field + " = "
					+ "'" + v_edit + "'" + "where vendor_id = " + v_id);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void addVendor(Connection conn) {
		int v_id = BooksAThousand
				.getIntFromShell("Please enter the vendor id :");
		String v_name = BooksAThousand
				.getStringFromShell("Please enter the vendor name : ");
		String v_address = BooksAThousand
				.getStringFromShell("Please enter the vendor address : ");
		String v_phone = BooksAThousand
				.getStringFromShell("Please enter the vendor phone number : ");
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("insert into Vendor values (" + v_id + ","
					+ "'" + v_name + "'" + "," + "'" + v_address + "'" + ","
					+ "'" + v_phone + "'" + ")");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}