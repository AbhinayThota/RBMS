
import java.io.IOException;
import java.sql.SQLException;

/**
 * based on user choices that choice related method is executed
 * @author vthota1 svempat2
 */
public class RetailDriver 
{
	private String username;
	private String password;
	
	public RetailDriver(String usernameIN,String passwordIN)
	{
		username=usernameIN;
		password=passwordIN;
	}
	
	void drive() 
	{
		try
		{
		RetailController rc = new RetailController(username,password);
		boolean flag=true;
		
		/*int choice;*/
		int subChoice;
		
		ShowMenu sm = new ShowMenu();
		
		while(flag)
		{
			int choice=sm.displayMainMenu();
			
			switch(choice)
			{
			case 1:
					subChoice=sm.displaySubMenu();
					actOnSubMenu(subChoice);
					break;
			case 2:rc.getProductSaleReport();
					break;
			case 3:rc.addPurchase();
					break;
			case 4: rc.addProduct();
					break;
			case 5:	rc.getLogs();
					break;
			case 6: flag=false;
					break;
			default:System.out.println("Wrong option. Please enter a right Choice.");
					break;
					
			}
			
		}
		System.out.println("Closing the Retail Business Management System. \n Thank You. Visit Again.");
		}
		catch(SQLException se){
			System.out.println("unable to connect to database"+se.getMessage());
			System.exit(1);
		}
		catch (IOException ie){
			System.out.println("\n**** IO Exception Caught ****\n"+ie.getMessage());
			System.exit(1);
		}
		catch(Exception e){
			System.out.println("unable to add to Purchases");
			System.exit(1);
			}
		}
		
	
	
	
	public void actOnSubMenu(int subChoice)
	{
		RetailController rc = new RetailController(username,password);
		//System.out.println("in the act on sub menu");
		switch(subChoice)
		{
		case 1:rc.getEmployees();
			break;
		case 2:rc.getCustomers();
			break;
		case 3:rc.getProducts();
				break;
		case 4: rc.getPurchases();
				break;
		case 5:	rc.getSuppliers();
				break;
		case 6: rc.getSupply();
				break;
		default:System.out.println("You have entered a wrong choice.");
				break;
			
		}
	}
	


}
