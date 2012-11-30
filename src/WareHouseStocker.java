public class WareHouseStocker{
  public static void main(String[] args){
    int swValue;
    
    do{
      // Display menu graphics
      System.out.println("===========================================================");
      System.out.println("|   AVAILABLE MENU FOR WARE HOUSE STOCKER                 |");
      System.out.println("===========================================================");
      System.out.println("|   Choose from one of the Options below:                 |");
      System.out.println("|        1. Add a vendor                                  |");
      System.out.println("|        2. Edit a vendor                                 |");
      System.out.println("|        3. Edit merchandise vendor                       |");
      System.out.println("|        4. Make a vendor order                           |");
      System.out.println("|        5. Check Outstanding orders                      |");
      System.out.println("|        6. Fulfill a customer order                      |");
      System.out.println("|        7. Fulfill a store order                         |");
      System.out.println("|        8. Exit                                          |");
      System.out.println("===========================================================");
      swValue=BooksAThousand.getIntFromShell("Please Select an option: ");
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
        case 7:
          System.out.println("Option 7");
          break;
        case 8:
          System.out.println("Option 8");
          break;
        default:
          System.out.println("Invalid selection");
      }
    }while(swValue!=8);
  }
}