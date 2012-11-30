import java.util.*;
public class BranchManager{
  public static void main(String[] args){
    int swValue;
    
    do{
      // Display menu graphics
      System.out.println("===========================================================");
      System.out.println("|   AVAILABLE MENU FOR BRANCH MANAGER                     |");
      System.out.println("===========================================================");
      System.out.println("|   Choose from one of the Options below:                 |");
      System.out.println("|        1. Request a stock                               |");
      System.out.println("|        2. Add a staff                                   |");
      System.out.println("|        3. Edit a Staff                                  |");
      System.out.println("|        4. Purchase History of Customer                  |");
      System.out.println("|        5. Check Customers Assisted by a staff           |");
      System.out.println("|        6. Edit a store                                  |");
      System.out.println("|        7. Exit                                          |");
      System.out.println("===========================================================");
      System.out.println(" Please Select an option:                                  ");
      swValue=StdIn.readInt();    
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
        default:
          System.out.println("Invalid selection");
      }
    }while(swValue!=7);
  }
}