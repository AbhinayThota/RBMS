/* trigger for case 5 to make logs table entry when insert a tuple into the supply table */

CREATE OR REPLACE TRIGGER supply_logEntry
after insert ON SUPPLY
for each row

/* execution section */
begin 
    insert into logs values(log_sequence.nextval,user,sysdate,'supply','insert',:new.sup#);
    dbms_output.put_line('A new supply is done. Log entry is made.');
    
end;
/
