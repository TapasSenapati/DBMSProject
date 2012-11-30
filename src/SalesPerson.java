public class SalesPerson {
	public static int main(String[] args) {
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
					.println("|        6. Exit                                          |");
			System.out
					.println("===========================================================");
			System.out
					.println(" Please Select an option:                                  ");
			swValue = StdIn.readInt();

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
		} while (swValue != 6);
		return 0;
	}
}