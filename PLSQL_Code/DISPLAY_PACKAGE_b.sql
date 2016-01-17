/* purpose: to display tables */
/* we have implemented both functions and procedures */
/* input parameters: null */
/* output refcursor variable for functions and cursor variable for procedures*/

/*package body to display tables*/
CREATE OR REPLACE PACKAGE BODY display_package
as

/*function to show employee tables*/
function showFun_employees
return ref_cursor is
    eref ref_cursor;
begin
    open eref for select * from employees;
    return eref;
end;

/*procedure to show employee tables*/
procedure showProc_employees 
is
    cursor emp is select * from employees;
    emp_rec employees%rowtype;
/* execution section */
begin
    
    for emp_rec in emp 
    loop
        dbms_output.put_line(emp_rec.eid||','||emp_rec.ename||','||emp_rec.telephone#);
    end loop;
/* exceptions section for error messages and handling */
exception
    when no_data_found then
        dbms_output.put_line('No Data Found in Employees Table');
end;


/*function to show customers tables*/
function showFun_customers
return ref_cursor is
    cref ref_cursor;
begin
    open cref for select * from customers;
    return cref;
end;

/*procedure to show customers tables*/
procedure showProc_customers 
is
    cursor cus is select * from customers;
    cus_rec customers%rowtype;
begin
    
    for cus_rec in cus 
    loop
        dbms_output.put_line(cus_rec.cid||','||cus_rec.cname||','||cus_rec.telephone#||','||cus_rec.visits_made||','||cus_rec.last_visit_date);
    end loop;
exception
    when no_data_found then
        dbms_output.put_line('No Data Found in Customers Table');
end;


/* Products*/
/*function to show products tables*/
function showFun_products
return ref_cursor is
    pref ref_cursor;
begin
    open pref for select * from products;
    return pref;
end;

/*procedure to show productss tables*/
procedure showProc_products 
is
    cursor prod is select * from products;
    prod_rec products%rowtype;
begin
    
    for prod_rec in prod 
    loop
        dbms_output.put_line(prod_rec.pid||','||prod_rec.pname||','||prod_rec.qoh||','||prod_rec.qoh_threshold||','||prod_rec.original_price||','||prod_rec.discnt_rate);
    end loop;
exception
    when no_data_found then
        dbms_output.put_line('No Data Found in Products Table');
end;


/*purchases*/

/*function to show purchases tables*/
function showFun_purchases
return ref_cursor is
    purref ref_cursor;
begin
    open purref for select * from purchases;
    return purref;
end;

/*procedure to show purchasess tables*/
procedure showProc_purchases 
is
    cursor pur is select * from purchases;
    pur_rec purchases%rowtype;
begin
    
    for pur_rec in pur 
    loop
        dbms_output.put_line(pur_rec.pur#||','||pur_rec.eid||','||pur_rec.pid||','||pur_rec.cid||','||pur_rec.qty||','||pur_rec.ptime||','||pur_rec.total_price);
    end loop;
exception
    when no_data_found then
        dbms_output.put_line('No Data Found in Purchases Table');
end;


/*suppliers*/
/*function to show suppliers tables*/
function showFun_suppliers
return ref_cursor is
    sref ref_cursor;
begin
    open sref for select * from suppliers;
    return sref;
end;

/*procedure to show suppliers tables*/
procedure showProc_suppliers 
is
    cursor sup is select * from suppliers;
    sup_rec suppliers%rowtype;
begin
    
    for sup_rec in sup 
    loop
        dbms_output.put_line(sup_rec.sid||','||sup_rec.sname||','||sup_rec.city||','||sup_rec.telephone#);
    end loop;
exception
    when no_data_found then
        dbms_output.put_line('No Data Found in Suppliers Table');
end;


/*supply*/
/*function to show supply tables*/
function showFun_supply
return ref_cursor is
    spref ref_cursor;
begin
    open spref for select * from supply;
    return spref;
end;

/*procedure to show supply tables*/
procedure showProc_supply 
is
    cursor suppl is select * from supply;
    supp_rec employees%rowtype;
begin
    
    for supp_rec in suppl 
    loop
        dbms_output.put_line(supp_rec.sup#||','||supp_rec.pid||','||supp_rec.sid||','||supp_rec.sdate||','||supp_rec.quantity);
    end loop;
exception
    when no_data_found then
        dbms_output.put_line('No Data Found in Supply Table');
end;

/*function to show logs tables*/
function showFUN_logs
return ref_cursor is
    spref ref_cursor;
begin
    open spref for select * from logs;
    return spref;
end;

/*procedure to show logs tables*/
procedure showProc_logs 
is
    cursor logg is select * from logs;
    log logs%rowtype;
begin
    
    for log in logg 
    loop
        dbms_output.put_line(log.log#||','||log.who||','||log.otime||','||log.table_name||','||log.operation||','||log.key_value);
    end loop;
exception
    when no_data_found then
        dbms_output.put_line('No Data Found in Logs Table');
end;

/*function to show product qoh based on pid as input parameter*/
function showFUN_productQoh(pidI in varchar2)
return ref_cursor
is
spref ref_cursor;
begin
    open spref for select pid,pname,qoh from products where pid=pidI;
    return spref;
end;

end;
/
