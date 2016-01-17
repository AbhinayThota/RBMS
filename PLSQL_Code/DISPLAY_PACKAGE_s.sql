/* pacakge specification for functions and procedures to display tables products, customers, logs, suppliers, supply, purchases, employees*/

CREATE OR REPLACE PACKAGE display_package
as
type ref_cursor is ref cursor;

function showFun_employees
return ref_cursor;

procedure showProc_employees;


function showFun_customers
return ref_cursor;

procedure showProc_customers;

function showFun_products
return ref_cursor;

procedure showProc_products;


function showFun_purchases
return ref_cursor;

procedure showProc_purchases;

function showFun_suppliers
return ref_cursor;

procedure showProc_suppliers;


function showFun_supply
return ref_cursor;

procedure showProc_supply;


function showFUN_logs
return ref_cursor;

procedure showProc_logs;

function showFUN_productQoh(pidI in varchar2)
return ref_cursor;

end;
/
