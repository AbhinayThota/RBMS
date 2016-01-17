/* trigger for case 7 updating qoh, ordering supply, updating qoh*/
/* purpose: decrements QOH on purchase in products table and checks id qoh<qoh_threshold id so orders supply and updates qoh after supply in 
products*/

CREATE OR REPLACE TRIGGER Update_Qoh
after insert ON PURCHASES
for each row


/* declaration section */
declare
    Tsid char(2);
    quantity number;
    err_code number;
    err_msg varchar2(100);
    Tqoh number;
	Tqoh_threshold number;


/* decrementing qoh in products table while a purchase is done*/

/* execution section */
begin
    
	
    update products
    set qoh=qoh-:new.qty
    where pid= :new.pid;
    
    select qoh_threshold
	into Tqoh_threshold
	from products
	where pid = :new.pid;
	
	select qoh
	into Tqoh
	from products
	where pid=:new.pid;
	
/* checking if qoh<qoh_threshold if so will order supply*/
	if Tqoh < Tqoh_threshold
	then
		dbms_output.put_line('The qoh of the product '||:new.pid||' is below the required threshold and new supply is required');
		
		select sid 
		into Tsid
		from supply
		where supply.pid=:new.pid and rownum=1
		order by supply.sid;
		
		quantity := 11+Tqoh_threshold+Tqoh;

/* ordering supply */		
		insert into supply values(sup_sequence.nextval,:new.pid,Tsid,sysdate,quantity);
    
		dbms_output.put_line('A supply for the product with pid '||:new.pid||' has made.');
	
/* updating qoh in products after supply is done*/		
		update products
		set qoh=quantity
		where products.pid=:new.pid;
    
		select qoh into Tqoh
		from products
		where products.pid=:new.pid;
    
    
		dbms_output.put_line('The qoh of the product '||:new.pid||' is incresed to '||Tqoh);
	end if;

/* error messages for exceptions*/
exception
    WHEN NO_DATA_FOUND 
    THEN
        dbms_output.put_line('There is no supplier who supplies the required product in the supply Table.');
    When others 
    then
        err_code := SQLCODE;
        err_msg := SUBSTR(SQLERRM, 1, 200);
      
        dbms_output.put_line(err_code||' '||err_msg||' '||' Update of quantity in hand of products failed. Sorry');
    
    
end;
/
show errors
