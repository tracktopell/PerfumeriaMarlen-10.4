SELECT ES.ID,ES.TIPO_MOV,ES.CLIENTE_ID,ES.SUCURSAL_ID,ES.FECHA_CREO,ES.NUMERO_TICKET,SUM(ESD.CANTIDAD*ESD.PRECIO_VENTA) AS SUBTOTAL
FROM ENTRADA_SALIDA ES,ENTRADA_SALIDA_DETALLE ESD 
WHERE 1=1 
AND ES.ID=ESD.ENTRADA_SALIDA_ID 
AND ES.NUMERO_TICKET IS NULL
GROUP BY ES.ID;


SELECT ES.ID,ES.TIPO_MOV,ES.SUCURSAL_ID,S.CLAVE,ES.FECHA_CREO,ES.USUARIO_EMAIL_CREO,C.RAZON_SOCIAL,C.NOMBRE_ESTABLECIMIENTO,FP.DESCRIPCION,MP.DESCRIPCION,ES.NUMERO_TICKET,ES.CAJA,ES.IMPORTE_RECIBIDO,ES.PORCENTAJE_DESCUENTO_CALCULADO,ES.PORCENTAJE_DESCUENTO_EXTRA,CFD.NUM_CFD,CFD.CALLING_ERROR_RESULT 
FROM SUCURSAL S,CLIENTE C,FORMA_DE_PAGO FP,METODO_DE_PAGO MP, ENTRADA_SALIDA ES LEFT JOIN CFD ON ES.CFD_ID = CFD.ID 
WHERE 1=1 
AND ES.SUCURSAL_ID=S.ID 
AND ES.CLIENTE_ID=C.ID 
AND ES.FORMA_DE_PAGO_ID=FP.ID 
AND ES.METODO_DE_PAGO_ID=MP.ID 
AND ES.ID=4455;

SELECT ES.ID,ESD.CANTIDAD,ESD.PRODUCTO_CODIGO_BARRAS,ESD.PRECIO_VENTA FROM ENTRADA_SALIDA ES,ENTRADA_SALIDA_DETALLE ESD WHERE 1=1 AND ESD.ENTRADA_SALIDA_ID=ES.ID AND ES.ID=4455;

SELECT ES.ID,ES.ESTADO_ID,ES.TIPO_MOV,ES.SUCURSAL_ID,S.CLAVE,ES.FECHA_CREO,ES.USUARIO_EMAIL_CREO,C.RAZON_SOCIAL,C.NOMBRE_ESTABLECIMIENTO,FP.DESCRIPCION,MP.DESCRIPCION,ES.NUMERO_TICKET,ES.CAJA,ES.IMPORTE_RECIBIDO,ES.PORCENTAJE_DESCUENTO_CALCULADO,ES.PORCENTAJE_DESCUENTO_EXTRA,CFD.NUM_CFD,CFD.CALLING_ERROR_RESULT 
FROM SUCURSAL S,CLIENTE C,FORMA_DE_PAGO FP,METODO_DE_PAGO MP, ENTRADA_SALIDA ES LEFT JOIN CFD ON ES.CFD_ID = CFD.ID 
WHERE 1=1 AND ES.SUCURSAL_ID=S.ID AND ES.CLIENTE_ID=C.ID AND ES.FORMA_DE_PAGO_ID=FP.ID AND ES.METODO_DE_PAGO_ID=MP.ID AND ES.SUCURSAL_ID>1 ORDER BY ES.ID DESC LIMIT 25;

SELECT ES.ID,ES.ESTADO_ID,ES.TIPO_MOV,ES.SUCURSAL_ID,S.CLAVE,ES.FECHA_CREO,ES.USUARIO_EMAIL_CREO,ES.CLIENTE_ID
FROM SUCURSAL S,ENTRADA_SALIDA ES
WHERE 1=1 
AND ES.SUCURSAL_ID=S.ID
AND ES.SUCURSAL_ID>1;

SELECT ID,CLAVE FROM SUCURSAL;

SELECT ES.* FROM ENTRADA_SALIDA ES WHERE ID = 4455;

UPDATE ENTRADA_SALIDA SET COMENTARIOS='ESTE MERO' WHERE ID = 5801;

SELECT * FROM SUCURSAL;

UPDATE SUCURSAL SET DESCUENTO_MAYOREO_HABILITADO=1 WHERE ID IN(2,3,4,5);
UPDATE SUCURSAL SET DESCUENTO_MAYOREO_HABILITADO=0 WHERE ID IN(1,6);

UPDATE ENTRADA_SALIDA SET CLIENTE_ID=1 WHERE SUCURSAL_ID>1;

SELECT * FROM CLIENTE WHERE ID < 5;

SELECT * FROM METODO_DE_PAGO;

DESC ENTRADA_SALIDA;

DESC ENTRADA_SALIDA_DETALLE;

SELECT ES.ID,
       ES.TIPO_MOV,
       ESD.ID,
       CC.ID,
       CC.TIPO_EVENTO,
       CC.SUCURSAL_ID,
       CC.CAJA,
       CC.SALDO_INICIAL,
       ESD.CANTIDAD,
       ESD.ALMACEN_ID,
       ESD.PRODUCTO_CODIGO_BARRAS,
       ESD.PRECIO_VENTA,
       SUM(-1 * ESD.CANTIDAD * ESD.PRECIO_VENTA) AS DEV
FROM   ENTRADA_SALIDA ES,
       ENTRADA_SALIDA_DETALLE ESD,
       CORTE_CAJA     CC
WHERE  1=1
AND    ES.ID           = ESD.ENTRADA_SALIDA_ID
AND    ES.TIPO_MOV     = 21
AND    CC.TIPO_EVENTO  = 2
AND    CC.SUCURSAL_ID  = 2
AND    CC.CAJA         = 1
AND    CC.ID           = ?
AND    ES.FECHA_CREO   >= CC.FECHA
ORDER BY ES.ID,ESD.ID;


SELECT * FROM ENTRADA_SALIDA_DETALLE ESD WHERE ESD.ENTRADA_SALIDA_ID = 2000;

SELECT ES.ID,ES.CLIENTE_ID,ES.FECHA_CREO,SUM(ESD.CANTIDAD*ESD.PRECIO_VENTA) AS SUBTOTAL,SUM(1) AS NUM_DET, SUM(CANTIDAD) TOT_PRODS, ES.NUMERO_TICKET,ES.FACTOR_IVA,ES.PORCENTAJE_DESCUENTO_EXTRA,ES.PORCENTAJE_DESCUENTO_CALCULADO,ES.IMPORTE_RECIBIDO
FROM ENTRADA_SALIDA ES,ENTRADA_SALIDA_DETALLE ESD
WHERE 1=1
AND ES.ID=ESD.ENTRADA_SALIDA_ID
GROUP BY ES.ID;

DESC ENTRADA_SALIDA;
