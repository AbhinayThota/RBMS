/* package specifications for case 4 monthly sale report */
/* we have implemented both functions and procedures for case 4*/

CREATE OR REPLACE PACKAGE Monthly_prod_report
as
type ref_cursor is ref cursor;

function Fun_report_monthly_sale(prod_id in varchar2)
return ref_cursor;

procedure Proc_report_monthly_sale(prod_id in varchar2);
end;
/
