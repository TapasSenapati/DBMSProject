public class ChainManager{
  public static void main(String[] args){
    int swValue;
    
    do{
      // Display menu graphics
      System.out.println("===========================================================");
      System.out.println("|   AVAILABLE MENU FOR CHAIN MANAGER                      |");
      System.out.println("===========================================================");
      System.out.println("|   Choose from one of the Options below:                 |");
      System.out.println("|        1. Add a store                                   |");
      System.out.println("|        2. Remove a store                                |");
      System.out.println("|        3. Add new Merhandise                            |");
      System.out.println("|        4. Remove a Merchandise                          |");
      System.out.println("|        5. Change Retail Price                           |");
      System.out.println("|        6. Exit                                          |");
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
        default:
          System.out.println("Invalid selection");
      }
    }while(swValue!=6);
  }
}