/* trigger to modify visits_information in customers table */
/* update to visits is done only once for a day even though there were multiple visits in the same day*/ 

CREATE OR REPLACE TRIGGER modify_visitsInformation
after insert ON PURCHASES
for each row

/*declration section*/
declare
   err_code number;
   err_msg varchar2(100);
   Tdate varchar2(30);
   Tpurdate varchar2(30);

/*execution section for error handling and messages*/
begin
    
    select to_char(last_visit_date,'Mon dd yyyy')
    into Tdate
    from customers
    where cid=:new.cid;
    
    
    select to_char(:new.ptime,'Mon dd yyyy')
    into Tpurdate
    from dual;
    
    IF (UPPER(RTRIM(LTRIM(Tdate))) <> UPPER(RTRIM(LTRIM(Tpurdate)))) 
    THEN
        
        update customers
        set customers.visits_made=customers.visits_made+1
        where customers.cid=:new.cid;
        
        update customers
        set customers.last_visit_date=:new.ptime
        where customers.cid=:new.cid;
    end if;

/* exception section to show errors*/    
exception
    WHEN NO_DATA_FOUND 
    THEN
        dbms_output.put_line('There is no customer with the customer id '||:new.cid);
    When others 
    then
        err_code := SQLCODE;
        err_msg := SUBSTR(SQLERRM, 1, 200);
      
        dbms_output.put_line(err_code||' '||err_msg||' '||' visists information for the customer is not updated. Sorry');
end;
/
