
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author vthota1 svempat2
 * this class prints the menu so that the user can use Retail Business Management System with his preferred choices
 */
public class ShowMenu {

	public ShowMenu()
	{
		
	}
	
	public int displayMainMenu()
	{
		int input=0;
		//Scanner inp = new Scanner(System.in);
		try
		{
		
		System.out.println("**********************MENU*********************");
		System.out.println("1. Display Table Data");
		System.out.println("2. Get Monthly Sale Report for a Product");
		System.out.println("3. Purchase a product");
		System.out.println("4. Add a product");
		System.out.println("5. Display Logs");
		System.out.println("6. Exit");
		System.out.println("Enter your option:");
		
		/*while(!inp.hasNextInt())
		{
			System.out.println("Please enter a correct option");
			inp.next();
		}
		input=inp.nextInt();
		inp.close();*/
		
		BufferedReader readChoice = new BufferedReader(new InputStreamReader(System.in));
		String choice=readChoice.readLine();
		
		//String choice= inp.nextLine();
//		inp.close();
		
		input=Integer.parseInt(choice);
		//readChoice.close();
		}
		catch (IOException ie){
			System.out.println("\n**** IO Exception Caught ****\n"+ie.getMessage());
			System.exit(1);
		} catch (Exception e){
			System.out.println("\n**** Other Exception caught ****\n"+e.getMessage());
			System.exit(1);
		}
		
		return input;
	}

	/**
	 * sub menu when user hits option 1 it give this menu so that he can choose what table he want to see
	 */
	
	public int displaySubMenu()
	{
		int input=0;
		
		try
		{
		
		System.out.println("Select Table :");
		System.out.println("1. Employees");
		System.out.println("2. Customers");
		System.out.println("3. Products");
		System.out.println("4. Purchases");
		System.out.println("5. Suppliers");
		System.out.println("6. Supply");
		System.out.println("Enter your option:");
		/*Scanner inp = new Scanner(System.in);*/
		
		
		/*while(!inp.hasNextInt())
		{
			System.out.println("Please enter a correct option");
			inp.next();
		}*/
		
		/*System.out.println("Please enter your credentials to connect to Database:");*/
		BufferedReader readChoice = new BufferedReader(new InputStreamReader(System.in));
		String choice=readChoice.readLine();
		
		input=Integer.parseInt(choice);
		
		
		/*input=inp.nextInt();
		System.out.println("I reached exit of sub menu");
		inp.close();*/
		
		}
		catch (IOException ie){
			System.out.println("\n**** IO Exception Caught ****\n"+ie.getMessage());
		} catch (Exception e){
			System.out.println("\n**** Other Exception caught ****\n"+e.getMessage());
		}
		return input;
	}
	
}
