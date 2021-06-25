
CREATE TABLE BUYINFO (
    
    BUYCODE NUMBER(4) CONSTRAINT BINFO_BCODE_PK PRIMARY KEY,
    BUYQTY INTEGER CONSTRAINT BINFO_BQTY_NN NOT NULL,
    TOTALPRICE INTEGER CONSTRAINT BINFO_TPRICE_NN NOT NULL,
    ITEMCODE NUMBER CONSTRAINT BINFO_ICODE_FK REFERENCES PRODUCTINFO(ITEMCODE) --참조코드
    
);

CREATE SEQUENCE BINFO_BCODE_SEQ
START WITH 1
INCREMENT BY 1;

SELECT * FROM BUYINFO;
DROP TABLE BUYINFO;

update BUYINFO set itemQty=itemQty+? where itemcode=?;

INSERT INTO BUYINFO VALUES (BINFO_BCODE_SEQ.NEXTVAL,20,14000,1);
INSERT INTO BUYINFO VALUES (BINFO_BCODE_SEQ.NEXTVAL,5,3000,2);
INSERT INTO BUYINFO VALUES (BINFO_BCODE_SEQ.NEXTVAL,2,12000,4);

select p.itemcode,p.name, b.buyqty, b.totalprice from PRODUCTINFO p, BUYINFO b where p.itemcode=b.itemcode;

SELECT ITEMCODE, P.NAME, B.BUYQTY, sum(B.TOTALPRICE) FROM PRODUCTINFO P INNER JOIN BUYINFO B ITEMCODE = ITEMCODE 
group by itemcode ;

select totalprice, buyqty, totalprice*buyqty from buyinfo  group by totalprice, buyqty, totalprice;

select b.itemcode, p.name, p.price, b.buyqty,b.totalprice, b.buyqty*b.totalprice 총가격
from BUYINFO B , PRODUCTINFO P
where b.itemcode=p.itemcode 
group by b.itemcode,p.name,p.price, b.buyqty, b.totalprice, b.buyqty*b.totalprice
order by p.name desc;

from PRODUCTINFO p, BUYINFO b 
where p.itemcode=b.itemcode and p.itemcode = '1';

order by b.totalprice desc;

drop SEQUENCE BINFO_BCODE_SEQ;

drop TABLE BUYINFO;

commit;

select b.itemcode, p.name, p.price, b.buyqty,b.totalprice, b.buyqty*b.totalprice 총가격
from BUYINFO B , PRODUCTINFO P
where b.itemcode=p.itemcode 
group by b.itemcode,p.name,p.price, b.buyqty, b.totalprice, b.buyqty*b.totalprice
order by p.name desc;

select *
from BUYINFO inner join PRODUCTINFO 
on itemcode = itemcode;


select b.itemcode, p.name, buyqty, 
from ((select itemcode, name, price
       from PRODUCTINFO P
      ) union all
      (select itemcode, buyqty, totalprice
       from BUYINFO B
      )
     ) t12
group by itemcode, name;



select sum(p.itemcode + p.name +b.totalprice)
from PRODUCTINFO p inner join BUYINFO b
on p.itemcode = b.itemcode
group by p.itemcode;

select b.itemcode, p.name, sum(b.buyqty), sum(b.totalprice) total from PRODUCTINFO p , BUYINFO b where b.itemcode = p.itemcode group by b.itemcode, p.name order by total desc;

select *  
from PRODUCTINFO inner join  BUYINFO using (itemcode)
order by itemcode;