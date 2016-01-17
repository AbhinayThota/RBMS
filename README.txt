STEPS TO COMPILE AND EXECUTE 

1. open svempat2_vthota1_project2DB
2. there will be readme and 3 folder
	1. PLSQL_Code
	2. JDBC_Code
	3. Reports
3. In Plsql folder the following .sql files are packages
		 ADD_DATA_s.sql;
		 ADD_DATA_b.sql
		 DISPLAY_PACKAGE_s.sql
		 DISPLAY_PACKAGE_b.sql
		 MONTHLY_PROD_REPORT_s.sql
		 MONTHLY_PROD_REPORT_b.sql
4. triggers .sql files in PL/SQL folder are for further info
CHECK_QOH.sql
CUSTOMER_LOGENTRY.sql
MODIFY_VISITSINFORMATION.sql
PRODUCT_LOGENTRY.sql
PURCHASE_LOGENTRY.sql
SUPPLY_LOGENTRY.sql
Update_Qoh.sql

5. to create tables for testing use createTables.sql
	1.in sqlplus environment type cmd start createTables.sql;
	this command will drop tables and sequences and recreates them
	2. to initiate tables with tuples start proj2data.sql;
6. for compiling packages type command 
		start create_packages.sql;

7.to create triggers type command start create_triggers.sql; this will compile all triggers
	
8.to drop triggers type command start drop_triggers.sql
9. open JDBc_Code folder there you will find .java files and makefile then 
	1. Type command make
	2. type command java RetailMain
10. choose options and test results
11. Open reports folder to find team and individual reports
