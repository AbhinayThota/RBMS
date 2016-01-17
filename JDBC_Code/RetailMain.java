
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * class that prnts welcome message
 * it takes username and password for connection
 * @author vthota1 svempat2
 */
public class RetailMain {
	
	
	public static void main(String[] args) throws IOException
	{
		System.out.println("\t\t--------------------------------------------");		
		System.out.println("\t\tWelcome to Retail Business Management System ");
		System.out.println("\t\t--------------------------------------------");
//		System.out.println('\n');
		System.out.println("\t\tDeveloped By: 'Sandeep Vempati' & 'Abhinay Thota'");
		System.out.println('\n');
		try
		{
			System.out.println("Please enter your credentials to connect to Database:");
			BufferedReader readUsername = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("UserName:");
			String username=readUsername.readLine();
			BufferedReader readPassword = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Password:");
			String password=readPassword.readLine();
			
			RetailDriver rd = new RetailDriver(username,password);
			rd.drive();
			
			
		}

		catch (IOException ie){
			System.out.println("\n**** IO Exception Caught ****\n"+ie.getMessage());
			System.exit(1);
		} catch (Exception e){
			System.out.println("\n**** Other Exception caught ****\n"+e.getMessage());
			System.exit(1);
		}
		
		
	
		
	}
	

}
