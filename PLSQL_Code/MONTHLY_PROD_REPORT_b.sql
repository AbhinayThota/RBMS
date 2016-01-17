/*purpose:  function and procedure to get monthly sale report based on PID */
/* input parameters: PID */
/* output: refcursor for function and dbms_output for procedure */

/* package body */
CREATE OR REPLACE PACKAGE BODY Monthly_prod_report
as

/* function */
function Fun_report_monthly_sale(prod_id in varchar2)
return ref_cursor is

/* refcursor declaration and pref is refcursor variable */
    pref ref_cursor;
    pcount number;

/* execution section */
begin

    open pref for 
    select pname,mont,yea,tot_qty,tot_price,tot_price/tot_qty as avg_price
    from
    (select p.pid,PR.PNAME,to_char(p.ptime,'Mon') mont,to_char(p.ptime,'yyyy') yea,sum(p.qty) tot_qty,sum(P.TOTAL_PRICE) tot_price
     from purchases p,products pr
     where P.PID=PR.PID
     group by p.pid,to_char(p.ptime,'Mon'),to_char(p.ptime,'yyyy'),pr.pname
     order by 1) temp_tab
     where pid=prod_id;
    
    return pref;
end;


/* procedure */
procedure Proc_report_monthly_sale(prod_id in varchar2)
is
pcount number;
type precord_type is record 
(pname products.pname%type,
 mont varchar2(3),
 yea varchar2(4),
 tot_qty number,
 tot_price number
 );
/*cursor declaration and pcur is cursor variable */    
cursor pcur is 
select pname,mont,yea,tot_qty,tot_price,tot_price/tot_qty as avg_price
from
    (select p.pid,PR.PNAME,to_char(p.ptime,'Mon') mont,to_char(p.ptime,'yyyy') yea,sum(p.qty) tot_qty,sum(P.TOTAL_PRICE) tot_price
     from purchases p,products pr
     where P.PID=PR.PID
     group by p.pid,to_char(p.ptime,'Mon'),to_char(p.ptime,'yyyy'),pr.pname
     order by 1) temp_tab
     where pid=prod_id;
 
precord precord_type;

/* execution section */
begin

select count(*) into pcount
    from
    (select p.pid,PR.PNAME,to_char(p.ptime,'Mon') mont,to_char(p.ptime,'yyyy') yea,sum(p.qty) tot_qty,sum(P.TOTAL_PRICE) tot_price
     from purchases p,products pr
     where P.PID=PR.PID
     group by p.pid,to_char(p.ptime,'Mon'),to_char(p.ptime,'yyyy'),pr.pname
     order by 1) temp_tab
     where pid=prod_id;
     
     if pcount>0
     then
         for precord in pcur
         loop
            dbms_output.put_line(precord.pname||','||precord.mont||','||precord.yea||','||precord.tot_qty||','||precord.tot_price);
         end  loop;
     else
        dbms_output.put_line('There are no records available for the given product id');
     end if;
     
        
end;

end;
/
