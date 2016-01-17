/* trigger for case 5 to make logs table entry when insert a tuple into the purchases table */

CREATE OR REPLACE TRIGGER purchase_logEntry
after insert ON PURCHASES
for each row

/* execution section */
begin 
    insert into logs values(log_sequence.nextval,user,sysdate,'purchases','insert',:new.pur#);
    dbms_output.put_line('A new purchase is made. Log entry is made.');
    
end;
/
