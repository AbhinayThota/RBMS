/* trigger for case 5 to make logs table entry when update the qoh attribute of the products table */

CREATE OR REPLACE TRIGGER product_logEntry
after update of qoh ON PRODUCTS
for each row

/* execution section */
begin
    insert into logs values(log_sequence.nextval,user,sysdate,'products','update',:old.pid);
    dbms_output.put_line('A product is updated. Log entry is made.');
end;
/
