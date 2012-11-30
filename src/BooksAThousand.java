import java.io.*;

public class BooksAThousand
{
      //get String or simply enter from shell
      public static String getStringFromShell(String prompt)
      {
            try
            {
                  System.out.print(prompt);
                  return new BufferedReader(new InputStreamReader(System.in)).readLine();		
            }
            catch (IOException e)
            {
                  e.printStackTrace();
            }
            return null ;
      }

      // get an int. Keep asking until not
      public static int getIntFromShell(String prompt)
      {
            String line = "" ;
            int num = 0 ;
            while(line.equals(""))
            {
                  line = getStringFromShell(prompt);
                  try
                  {
                        num = Integer.parseInt(line);
                  }
                  catch(NumberFormatException e)
                  {
                        System.out.println("Error: Invalid number");
                        line = "" ;
                  }
            }
            return num ;
      }

      // get a float. Keep asking until not
      public static float getFloatFromShell(String prompt)
      {
            String line = "" ;
            float num = 0 ;
            while(line.equals(""))
            {
                  line = getStringFromShell(prompt);
                  try
                  {
                        num = Float.parseFloat(line);
                  }
                  catch(NumberFormatException e)
                  {
                        System.out.println("Error: Invalid number");
                        line = "" ;
                  }
            }
            return num ;
      }
      
      // Similar methods can be made for getting char , double etc

      public static void main(String args[])
      {
    	  
    	  String menu_string = "Enter 1 to act as sales person.\n"
    	  					 + "Enter 2 to act as billing staff.\n"
    	  					 + "Enter 3 to act as warehoue stocker.\n"
    	  					 + "Enter 4 to act as branch manager.\n"
    	  					 + "Enter 5 to act as chain manager.\n"
    	  					 + "Enter 6 to exit from the program.\n";
    	  int menu_choice = 0;
    	  
    	  do
    	  {
              menu_choice  = BooksAThousand.getIntFromShell(menu_string);
              switch(menu_choice)
              {
              	case 1: SalesPerson.main(args);
              			break;
              	case 2: BillingStaff.main(args);
  						break;
              	case 3: WareHouseStocker.main(args);
      					break;
              	case 4: BranchManager.main(args);
              			break;
              	case 5: ChainManager.main(args);
              			break;
              	case 6: System.out.println("Now exiting.");
              			break;
      			default:	System.out.println("Please enter a valid value");
              }
    	  }	while(menu_choice != 6);
      }

}
