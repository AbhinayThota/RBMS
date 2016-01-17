/* trigger for case 5 to make logs table entry when update the visits_made attribute of the customers table */
CREATE OR REPLACE TRIGGER customer_logEntry
after update of visits_made ON CUSTOMERS
for each row
begin
    insert into logs values(log_sequence.nextval,user,sysdate,'customers','update',:old.cid);
    dbms_output.put_line('values pertaining to a customer  is updated. Log entry is made.');
end;
/
