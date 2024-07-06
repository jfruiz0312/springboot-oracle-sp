create or replace PACKAGE BODY PKG_PRODUCTO AS
/*==============================================================*/
/* SISTEMA:     SISTEMA DE INVENTRIO - PACKAGE UTIL           	*/
/* PAQUETE:     PKG_PRODUCTO.sql     	                        */
/* DESCRIPCION: UTILIDADES PARA: SISTEMA       			        */
/* AUTOR:       ING. FERNANDO RUIZ						        */
/* FECHA:       02/07/2024                                      */
/*==============================================================*/
  PROCEDURE SP_LISTAR(
     P_CURSOR OUT SYS_REFCURSOR,
     P_NOMBRE IN  PRODUCTO.NOMBRE%TYPE
  ) AS
  BEGIN
      OPEN 
            P_CURSOR
        FOR
            SELECT  
                    PRODUCTO_ID,
                    NOMBRE,
                    PRECIO,
                    STOCK,
                    ESTADO
            FROM 
                    PRODUCTO
            WHERE 
                        UPPER(NOMBRE) LIKE '%'||UPPER(P_NOMBRE)||'%'
                    AND ESTADO='1';
  END SP_LISTAR;

 
PROCEDURE SP_LISTAR_TODO(
     P_CURSOR OUT SYS_REFCURSOR
  ) AS
  BEGIN
      OPEN 
            P_CURSOR
        FOR
            SELECT  
                    PRODUCTO_ID,
                    NOMBRE,
                    PRECIO,
                    STOCK,
                    ESTADO
            FROM 
                    PRODUCTO
            WHERE 
                       ESTADO='1';
  END SP_LISTAR_TODO;
  

  PROCEDURE SP_INSERTAR
    (
        P_ID_PRODUCTO        OUT PRODUCTO.PRODUCTO_ID%TYPE,
        P_NOMBRE        IN  PRODUCTO.NOMBRE%TYPE,
        P_PRECIO        IN  PRODUCTO.PRECIO%TYPE,
        P_STOCK         IN  PRODUCTO.STOCK%TYPE

    ) AS
  BEGIN

    SELECT
        SEQ_PRODUCTO.NEXTVAL
    INTO
        P_ID_PRODUCTO
    FROM
        DUAL;

    INSERT INTO PRODUCTO(
        PRODUCTO_ID,
        NOMBRE,
        PRECIO,
        STOCK,
        ESTADO
    )
    VALUES
    (
        P_ID_PRODUCTO,
        P_NOMBRE,
        P_PRECIO,
        P_STOCK,

        '1'
    );

  END SP_INSERTAR;

  PROCEDURE SP_ACTUALIZAR
    (
        P_ID_PRODUCTO   IN  PRODUCTO.PRODUCTO_ID%TYPE,
        P_NOMBRE        IN  PRODUCTO.NOMBRE%TYPE,
        P_PRECIO        IN  PRODUCTO.PRECIO%TYPE,
        P_STOCK         IN  PRODUCTO.STOCK%TYPE
    ) AS
  BEGIN

    UPDATE 
        PRODUCTO
    SET
        NOMBRE          =   P_NOMBRE,
        PRECIO          =   P_PRECIO,
        STOCK           =   P_STOCK

    WHERE
        PRODUCTO_ID          =   P_ID_PRODUCTO;

  END SP_ACTUALIZAR;

    PROCEDURE SP_ELIMINAR
    (
        P_ID_PRODUCTO        IN  PRODUCTO.PRODUCTO_ID%TYPE
    ) AS
  BEGIN

    UPDATE 
        PRODUCTO
    SET
        ESTADO          =   '0'
    WHERE
        PRODUCTO_ID          =   P_ID_PRODUCTO;

  END SP_ELIMINAR;

 PROCEDURE SP_BUSCAR_X_ID_PRODUCTO
    (
        P_CURSOR    OUT SYS_REFCURSOR,
        P_ID_PRODUCTO    IN  PRODUCTO.PRODUCTO_ID%TYPE
    )AS
    BEGIN
    OPEN 
            P_CURSOR
        FOR
            SELECT  
                    PRODUCTO_ID,
                    NOMBRE,
                    PRECIO,
                    STOCK,
                    ESTADO
            FROM 
                    PRODUCTO
            WHERE 
                    PRODUCTO_ID  =  P_ID_PRODUCTO;

    END SP_BUSCAR_X_ID_PRODUCTO;

    
END PKG_PRODUCTO;