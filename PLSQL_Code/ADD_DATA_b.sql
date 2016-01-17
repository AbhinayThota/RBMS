/* package body for adding products to products table and adding purchases to purchases table*/
CREATE OR REPLACE PACKAGE BODY Add_Data
as

/*functiion to add a purchase*/
/*return type varchar*/
/*input parameters: eid,pid,cid,pur_qty */

function Fun_add_purchase(e_id in varchar2,p_id in varchar2,c_id in varchar2,pur_qty in number)
return varchar2
is
Tqoh number;
pPrice number;
outP varchar2(500);
tot_price number;
err_code number;
err_msg varchar2(100);
Tqoh_thresh number;
updated_qoh number;
/* execution section */
begin
    select qoh
    into Tqoh
    from products
    where pid=p_id;
    
    select (original_price)*(1-discnt_rate) 
    into pPrice
    from products
    where pid=p_id;
    
    tot_price := pPrice*pur_qty;
    
    if Tqoh<pur_qty
    then
        --outP:=1;
       outP := 'Insufficient quantity in stock';
        return outP;
    else
        
        
        insert into purchases values(pur_sequence.nextval,e_id,p_id,c_id,pur_qty,sysdate,tot_price);
        
        outP := 'Purchase Successfully Made ! Thank You';
        
        
        select qoh_threshold
        into Tqoh_thresh
        from products
        where pid=p_id;
        
        updated_qoh:=Tqoh-pur_qty;
        
        if updated_qoh < Tqoh_thresh
        then
            outP := outP||'\n Current qoh of the product is below the required threshold '||Tqoh_thresh||'. Order will be done automatically.';
        end if;
 
        return outP;
        
    end if;
    commit;
    Exception 
    WHEN NO_DATA_FOUND 
    THEN 
            outP :=  'Please enter valid eid and pid and cid.	 Try Again!';
            return outP;
    When others 
    then
        err_code := SQLCODE;
        err_msg := SUBSTR(SQLERRM, 1, 200);
      
        outP := err_code||' '||err_msg||' '||' Purchase Declined. Sorry';
        return outP;
        
end;

/*procedure to add a purchase*/
/*input parameters: eid,pid,cid,pur_qty */
procedure Proc_add_purchase(e_id in varchar2,p_id in varchar2,c_id in varchar2,pur_qty in number)
as
pPrice number;
tot_price number;
err_code number;
err_msg varchar2(100);

/* execution section */
begin

    select (original_price)*(1-discnt_rate) 
    into pPrice
    from products
    where pid=p_id;

    tot_price := pPrice*pur_qty;
    
    insert into purchases values(pur_sequence.nextval,e_id,p_id,c_id,pur_qty,sysdate,tot_price);
    
    dbms_output.put_line('Purchase successfully made. Tnak You.');
    commit;

/* exceptions section for error messages and handling */
    Exception 
    WHEN NO_DATA_FOUND 
    THEN 
            dbms_output.put_line('A SELECT...INTO did not return any row.');
    When others 
    then
        err_code := SQLCODE;
        err_msg := SUBSTR(SQLERRM, 1, 200);
      
        dbms_output.put_line(err_code||' '||err_msg||' '||' Purchase Declined. Sorry');
end;

/*functiion to add a product*/
/*return type varchar*/
/*input parameters: pid,pname,qoh,qoh_threshold,original_price,Discnt_rate */

function Fun_add_product(p_id in varchar2,p_name in varchar2,q_On_Hand in number,q_thres in number,org_price in number,dis in number)
return varchar2
as
outP varchar2(100);
err_code number;
err_msg varchar2(100);

/* execution section */
begin

if dis >0 and dis <0.8
then 
    insert into products values(p_id,p_name,q_On_Hand,q_thres,org_price,dis);
    outP := 'Product Successfully included.';
    return outP;
else
    outP := 'Discount should be between 0 and 0.8';
    return outP;
end if;

/* exceptions section for error messages and handling */
 Exception 
    WHEN NO_DATA_FOUND 
    THEN 
            outP := 'A SELECT...INTO did not return any row.';
            return outP;
    When others 
    then
        err_code := SQLCODE;
        err_msg := SUBSTR(SQLERRM, 1, 200);
      
        outP := err_code||' '||err_msg||' '||' unable to add a product. Sorry';
        return outP;
end;

/*procedure to add a product*/
/*input parameters: pid,pname,qoh,qoh_threshold,original_price,Discnt_rate */
procedure Proc_add_product(p_id in varchar2,p_name in varchar2,q_On_Hand in number,q_thres in number,org_price in number,dis in number)
as
err_code number;
err_msg varchar2(100);

/* execution section */
begin

if dis >0 and dis <0.8
then 
    insert into products values(p_id,p_name,q_On_Hand,q_thres,org_price,dis);
    dbms_output.put_line('Product Successfully included.');
else
    dbms_output.put_line('Discount should be between 0 and 0.8');
end if;

/* exceptions section for error messages and handling */
 Exception 
    WHEN NO_DATA_FOUND 
    THEN 
            dbms_output.put_line('A SELECT...INTO did not return any row.');
    When others 
    then
        err_code := SQLCODE;
        err_msg := SUBSTR(SQLERRM, 1, 200);
      
        dbms_output.put_line(err_code||' '||err_msg||' '||' Purchase Declined. Sorry');
end;

end;
/
