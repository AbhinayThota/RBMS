/* trigger for case 6 to check whether the quantity ordered is <=qoh else prints error message*/

CREATE OR REPLACE TRIGGER check_qoh
before insert ON PURCHASES
for each row
declare
    less_thanRequired exception;
    Tqoh number;
begin
    select qoh into Tqoh
    from products
    where pid=:new.pid;
    
    if Tqoh< :new.qty
    then
        raise less_thanRequired;
    end if;
/* error message if order qty exceeds qoh */
exception
    when less_thanRequired
    then
        dbms_output.put_line('Insufficient quantity in stock');
        raise_application_error(-20001,'Insufficient quatity in stock');
    end;
/
