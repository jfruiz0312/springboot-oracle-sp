
INSERT INTO PRODUCTO(PRODUCTO_ID,NOMBRE,PRECIO,STOCK) VALUES(SEQ_PRODUCTO.NEXTVAL,'Televisor',2500,10);
INSERT INTO PRODUCTO(PRODUCTO_ID,NOMBRE,PRECIO,STOCK) VALUES(SEQ_PRODUCTO.NEXTVAL,'Radio',250,5);

commit;

SELECT PRODUCTO_ID,NOMBRE,PRECIO,STOCK,ESTADO FROM PRODUCTO;
describe producto;