create or replace PACKAGE PKG_PRODUCTO AS 
/*==============================================================*/
/* SISTEMA:     SISTEMA DE INVENTRIO - PACKAGE UTIL           	*/
/* PAQUETE:     PKG_PRODUCTO.sql     	                        */
/* DESCRIPCION: UTILIDADES PARA: SISTEMA       			        */
/* AUTOR:       ING. FERNANDO RUIZ						       */
/* FECHA:       02/07/2024                                      */
/*==============================================================*/
    PROCEDURE SP_LISTAR
    (
        P_CURSOR    OUT SYS_REFCURSOR,
        P_NOMBRE    IN  PRODUCTO.NOMBRE%TYPE
    );
    
     PROCEDURE SP_LISTAR_TODO
    (
        P_CURSOR    OUT SYS_REFCURSOR
        );

    PROCEDURE SP_INSERTAR
    (
        P_ID_PRODUCTO        OUT PRODUCTO.PRODUCTO_ID%TYPE,
        P_NOMBRE        IN  PRODUCTO.NOMBRE%TYPE,
        P_PRECIO        IN  PRODUCTO.PRECIO%TYPE,
        P_STOCK         IN  PRODUCTO.STOCK%TYPE

    );

    PROCEDURE SP_ACTUALIZAR
    (
        P_ID_PRODUCTO        IN  PRODUCTO.PRODUCTO_ID%TYPE,
        P_NOMBRE        IN  PRODUCTO.NOMBRE%TYPE,
        P_PRECIO        IN  PRODUCTO.PRECIO%TYPE,
        P_STOCK         IN  PRODUCTO.STOCK%TYPE

    );

    PROCEDURE SP_ELIMINAR
    (
        P_ID_PRODUCTO        IN  PRODUCTO.PRODUCTO_ID%TYPE
    );

   PROCEDURE SP_BUSCAR_X_ID_PRODUCTO
    (
        P_CURSOR    OUT SYS_REFCURSOR,
        P_ID_PRODUCTO    IN  PRODUCTO.PRODUCTO_ID%TYPE
    );
    
    
END PKG_PRODUCTO;
