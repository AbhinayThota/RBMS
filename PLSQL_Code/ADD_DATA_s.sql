/* package to add products to products table and purchase to purchases table */
/* implemented both functions and procedures*/

CREATE OR REPLACE PACKAGE Add_Data
as
/* for purchases*/
function Fun_add_purchase(e_id in varchar2,p_id in varchar2,c_id in varchar2,pur_qty in number)
return varchar2;

procedure Proc_add_purchase(e_id in varchar2,p_id in varchar2,c_id in varchar2,pur_qty in number);

/* for products */
function Fun_add_product(p_id in varchar2,p_name in varchar2,q_On_Hand in number,q_thres in number,org_price in number,dis in number)
return varchar2;

procedure Proc_add_product(p_id in varchar2,p_name in varchar2,q_On_Hand in number,q_thres in number,org_price in number,dis in number);
end;
/
