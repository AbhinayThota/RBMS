
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;
/** 
 * @author vthota1 and svempat2
 * will get database access here using JDBC and retrive PLSQL outputs to the console
 */


public class RetailController{
	
	private String username;
	private String password;
	
	public RetailController(String s1, String s2){
		username=s1;
		password=s2;
	}
	
	Scanner scanner = new Scanner(System.in);
	
	/**
	 * this method takes input parameters for Fun_add_products
	 * returns: success or problem while adding this input product
	 * @throws SQLException
	 * @throws IOException
	 */
	public void addProduct() throws SQLException, IOException {
		try{
		System.out.println("Please enter following details:\nPID (in the format p[0-9]{3}):");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pid = br.readLine(); 
		System.out.println("pname:");
		br = new BufferedReader(new InputStreamReader(System.in));
		String pname = br.readLine();
		System.out.println("qoh:");
		br = new BufferedReader(new InputStreamReader(System.in));
		String qoh = br.readLine();
		System.out.println("qoh_threshold:");
		br = new BufferedReader(new InputStreamReader(System.in));
		String qoh_threshold = br.readLine(); 
		System.out.println("original_price:");
		br = new BufferedReader(new InputStreamReader(System.in));
		String original_price = br.readLine(); 
		System.out.println("discnt_rate");
		br = new BufferedReader(new InputStreamReader(System.in));
		String discnt_rate = br.readLine(); 
		
		Connection conn = new RetailConnect(username,password).getDBConnection();
		String query = "begin ? := Add_Data.Fun_add_product(?, ?, ?, ?, ?, ?); end;";
		CallableStatement cs = conn.prepareCall(query);
		
		cs.setString(2, pid);
		cs.setString(3, pname);
		cs.setString(4, qoh);
		cs.setString(5, qoh_threshold);
		cs.setString(6, original_price);
		cs.setString(7, discnt_rate);
		cs.registerOutParameter(1, OracleTypes.VARCHAR);
		cs.executeQuery();
		String outp=cs.getString(1);
		System.out.println(outp);
		//br.close();
		cs.close();
		conn.close();
		}
		catch(SQLException se){
			System.out.println("unable to add a product"+se.getMessage());
			System.exit(1);
		}
		catch (IOException ie){
			System.out.println("\n**** IO Exception Caught ****\n"+ie.getMessage());
			System.exit(1);
		}
		catch(Exception e){
			System.out.println("unable to add a product with the given PID"+e.getMessage());
			System.exit(1);
			}
	}
	
	
	/**
	 * this method returns the cursor output from DB to console from PL/SQL function showFUN_employees()
	 * output is employees table
	 */
	public void getEmployees(){
		try{
			
		Connection conn = new RetailConnect(username,password).getDBConnection();
		//String query = "begin display_package.showFUN_employees(?); end;";
		CallableStatement cs = conn.prepareCall("begin ? := display_package.showFUN_employees(); end;");
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		if(!(rs.equals(null))){
		System.out.println("eid\tename\t\ttelephone#");
		System.out.println("----------------------------------------------------------------------------------------");
		    while(rs.next())
		    {
			System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t\t"+
							   rs.getString(3));
		    }
		}
		else {
			System.out.println("No data found");
		}
		rs.close();
		//st.close();
		cs.close();
		conn.close();
		}
		catch(SQLException e){
			System.err.println("unable to show employees table"+e.getMessage());			
		}
	}

	/**
	 * this method returns the cursor output from DB to console from PL/SQL function showFUN_customers()
	 * output is customers table
	 */
public void getCustomers(){
	try
	{
	Connection conn = new RetailConnect(username,password).getDBConnection();
	String query = "begin  ? := display_package.showFUN_customers(); end;";
	CallableStatement cs = conn.prepareCall(query);
	cs.registerOutParameter(1, OracleTypes.CURSOR);
	cs.execute();
	ResultSet rs = (ResultSet) cs.getObject(1);
	if(!(rs.equals(null))){
	System.out.println("cid\t\tcname\t\ttelephone#\tvisits_made\t\tlast_visit_date");
	System.out.println("------------------------------------------------------------------------------------------------");
	while(rs.next()){
		System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+
						   rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5));
	}
	}
	else {
		System.out.println("No data found");
	}
	rs.close();
	cs.close();
	conn.close();
	}
	catch(SQLException e){
		System.err.println("unable to show customers table");			
	}
}

	/**
	 * this method returns the cursor output from DB to console from PL/SQL function showFUN_products()
	 * output is products table
	 */
public void getProducts(){
	try
	{
	Connection conn = new RetailConnect(username,password).getDBConnection();
	//String query = "begin  ? := display_package.showFUN_products(); end;";
	CallableStatement cs = conn.prepareCall("begin ? := display_package.showFUN_products(); end;");
	cs.registerOutParameter(1, OracleTypes.CURSOR);
	cs.execute();
	ResultSet rs = (ResultSet) cs.getObject(1);
	if(!(rs.equals(null))){
	System.out.println("pid\t\tpname\t\tqoh\tqoh_threshold\toriginal_price\tdiscnt_rate");
	System.out.println("--------------------------------------------------------------------------------------------------------");
	while(rs.next()){
		System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+
						   rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t"+rs.getString(6));
	}
	}
	else { 
		System.out.println("No data found");
	}
	rs.close();
	cs.close();
	conn.close();
	}
	catch(SQLException e){
		System.err.println("unable to show products table"+e.getMessage());			
	}
}


	/**
	 * this method returns the cursor output from DB to console from PL/SQL function showFUN_suppliers()
	 * output is suppliers table
	 */
public void getSuppliers(){
	try
	{
	Connection conn = new RetailConnect(username,password).getDBConnection();
	//String query = "begin display_package.showFUN_suppliers(?); end;";
	CallableStatement cs = conn.prepareCall("begin  ? := display_package.showFUN_suppliers(); end;");
	cs.registerOutParameter(1, OracleTypes.CURSOR);
	cs.execute();
	ResultSet rs = (ResultSet) cs.getObject(1);
	if(!(rs.equals(null))){
	System.out.println("sid\t\tsname\t\t\tcity\t\ttelephone#");
	System.out.println("--------------------------------------------------------------------------------");
	while(rs.next()){
		System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+
						   rs.getString(3)+"\t\t"+rs.getString(4));
	}
	}
	else { 
		System.out.println("No data found");
	}
	rs.close();
	cs.close();
	conn.close();
	}
	catch(SQLException e){
		System.err.println("unable to show suppliers table");			
	}
}


	/**
	 * this method returns the cursor output from DB to console from PL/SQL function showFUN_supply()
	 * output is supply table
	 */
public void getSupply(){
	try
	{
	Connection conn = new RetailConnect(username,password).getDBConnection();
	//String query = "begin display_package.showFUN_supply(?); end;";
	CallableStatement cs = conn.prepareCall("begin  ? := display_package.showFUN_supply(); end;");
	cs.registerOutParameter(1, OracleTypes.CURSOR);
	cs.execute();
	ResultSet rs = (ResultSet) cs.getObject(1);
	if(!(rs.equals(null))){
	System.out.println("sup#\t\tpid\t\tsid\t\tquantity");
	System.out.println("--------------------------------------------------------------------------------");
	while(rs.next()){
		System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+
						   rs.getString(3)+"\t\t"+rs.getString(4));
	}
	}
	else { 
		System.out.println("No data found");
	}
	rs.close();
	cs.close();
	conn.close();
	}
	catch(SQLException e){
		System.err.println("unable to show supply table");			
	}
}


	/**
	 * this method returns the cursor output from DB to console from PL/SQL function showFUN_purchases()
	 * output is purchases table
	 */
public void getPurchases(){
	try
	{
	Connection conn = new RetailConnect(username, password).getDBConnection();
	//String query = "begin display_package.showFUN_purchases(?); end;";
	CallableStatement cs = conn.prepareCall("begin  ? := display_package.showFUN_purchases(); end;");
	cs.registerOutParameter(1, OracleTypes.CURSOR);
	cs.execute();
	ResultSet rs = (ResultSet) cs.getObject(1);
	if(!(rs.equals(null))){
	System.out.println("pur#\t\teid\t\tpid\t\tcid\t\tqty\t\tptime\t\t\t\ttotal_price");
	
System.out.println("------------------------------------------------------------------------------------------------------------------------");
	while(rs.next()){
		System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+
						   rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t"+
								   rs.getString(6)+"\t\t"+rs.getString(7));
	}
	}
	else 
		{
		System.out.println("No data found");
		}
	rs.close();
	cs.close();
	conn.close();
	}
	catch(SQLException e){
		System.err.println("unable to show purchases table");			
	}
}



public void getProductSaleReport() throws SQLException , IOException
{
	try
	{
		getProducts();
		System.out.println("Please enter the PRODUCT ID to retrieve the product details from the above products:\nPID (in the format p[0-9]{3}):");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pid = br.readLine(); 
//		br.close();
		Connection conn = new RetailConnect(username, password).getDBConnection();
		CallableStatement cs = conn.prepareCall("begin ? :=Monthly_prod_report.Fun_report_monthly_sale(?); end;");
		
		cs.setString(2, pid);
		cs.registerOutParameter(1,OracleTypes.CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet)cs.getObject(1);
		System.out.println("ProductName \t Month \t Year \t Total_QtySold \t Total_Amount \t\t Avg_Price");
		System.out.println("----------------------------------------------------------------------------------------------");
		if(!(rs.equals(null))){
		while (rs.next()) {
            	System.out.println(rs.getString(1) + "\t\t" +
                rs.getString(2) +"\t"+ rs.getString(3)+"\t\t" + 
                rs.getString(4) + 
                "\t\t" + rs.getDouble(5) + "\t\t" +
                rs.getString(6));
        	}
		}
		else{
			System.out.println("There are no records for the given pid");}

		cs.close();
		conn.close();
		
	}
	catch(SQLException e)
	{
		System.err.println("no purchase record found with this PID"+e.getMessage());			
	}
	
	
}


/**
 * this method returns the cursor output from DB to console from pl/sql function showFUN_logs()
 * output is logs table
 */
public void getLogs(){
	try
	{
	Connection conn = new RetailConnect(username,password).getDBConnection();
	//String query = "begin display_package.showFUN_supply(?); end;";
	CallableStatement cs = conn.prepareCall("begin  ? := display_package.showFUN_logs(); end;");
	cs.registerOutParameter(1, OracleTypes.CURSOR);
	cs.execute();
	ResultSet rs = (ResultSet) cs.getObject(1);
	if(!(rs.equals(null))){
	System.out.println("log#\twho\totime\t\t\ttable_name\topeartion\tkey_value");
	System.out.println("-------------------------------------------------------------------------------------");
	while(rs.next()){
		System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+
						   rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t\t"+rs.getString(6));
	}
	}
	else { 
		System.out.println("No data found");
	}
	rs.close();
	cs.close();
	conn.close();
	}
	catch(SQLException e){
		System.err.println("unable to show Logs table"+e.getMessage());			
	}
}


/**
 * adds a tuple in the purchases table using plsql function Fun_add_purchases
 * @throws SQLException
 * @throws IOException
 */
public void addPurchase()  /*throws SQLException, IOException*/ {
	try
	{
	System.out.println("Please enter following details:\neid (in the format e[0-9]{3}):");
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String eid = br.readLine(); 
	System.out.println("pid (in the format p[0-9]{3}):");
	br = new BufferedReader(new InputStreamReader(System.in));
	String pid = br.readLine();
	System.out.println("cid (in the format c[0-9]{3}):");
	br = new BufferedReader(new InputStreamReader(System.in));
	String cid = br.readLine();
	System.out.println("pur_qty");
	br = new BufferedReader(new InputStreamReader(System.in));
	String pur_qty = br.readLine();  
	
	Connection conn = new RetailConnect(username,password).getDBConnection();
	String query = "begin  ? := Add_Data.Fun_add_purchase(?, ?, ?, ?); end;";
	CallableStatement cs = conn.prepareCall(query);
	
	cs.setString(2, eid);
	cs.setString(3, pid);
	cs.setString(4, cid);
	cs.setString(5, pur_qty);
	cs.registerOutParameter(1, OracleTypes.VARCHAR);
	cs.executeQuery();
	String msg=cs.getString(1);
	System.out.println(msg);
//	br.close();
	cs.close();
	conn.close();
	if((!msg.equals("Insufficient quantity in stock")) && (!msg.equals("Purchase Successfully Made ! Thank You"))){
	getProductUpdate(pid);
	}
}
catch(SQLException se){
	System.out.println("unable to connect to database"+se.getMessage());
	System.exit(1);
}
catch(Exception e){
	System.out.println("unable to add to Purchases");
	System.exit(1);
	}

}



/**
 * method to show products table qoh
 * used when update of qoh is done
 */
public void getProductUpdate(String pidI){
	try
	{
	Connection conn = new RetailConnect(username,password).getDBConnection();
	String query = "begin  ? := display_package.showFUN_productQoh(?); end;";
	CallableStatement cs = conn.prepareCall(query);
	cs.setString(2, pidI);
	cs.registerOutParameter(1, OracleTypes.CURSOR);
	cs.execute();
	ResultSet rs = (ResultSet) cs.getObject(1);
	if(!(rs.equals(null))){
	//System.out.println("cid\t\tcname\t\ttelephone#\tvisits_made\t\tlast_visit_date");
	while(rs.next()){
		System.out.println("\nThe product Id "+rs.getString(1)+" with product Name "+rs.getString(2)+" has qoh of "+
						   rs.getString(3)+" after purchase(Supply will be done if qoh is less than qoh_threshold.)");
		System.out.println("\n\n\n");
	}
	}
	else {
		System.out.println("No data found");
	}
	rs.close();
	cs.close();
	conn.close();
	}
	catch(SQLException e){
		System.err.println("Error retrieving results from products table, after purchase has made."+e.getMessage());			
	}
}



}

