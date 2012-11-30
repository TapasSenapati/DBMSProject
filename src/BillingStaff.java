public class BillingStaff {
	
	public static void main(String[] args) {
		int swValue;

		do {
			// Display menu graphics
			System.out
					.println("===========================================================");
			System.out
					.println("|   AVAILABLE MENU FOR BILLING STAFF                       |");
			System.out
					.println("===========================================================");
			System.out
					.println("|   Choose from one of the Options below:                 |");
			System.out
					.println("|        1. Generate a custoemr bill                      |");
			System.out
					.println("|        2. Generate a vendor bill                        |");
			System.out
					.println("|        3. Exit                                          |");
			System.out
					.println("===========================================================");
			swValue = BooksAThousand.getIntFromShell("Please Select an option: ");

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
			default:
				System.out.println("Invalid selection");
			}
		} while (swValue != 3);
	}
}