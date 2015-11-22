echo "SELECT ES.ID,ES.ESTADO_ID,ES.TIPO_MOV,ES.SUCURSAL_ID,S.CLAVE,ES.FECHA_CREO,ES.USUARIO_EMAIL_CREO,C.RAZON_SOCIAL,C.NOMBRE_ESTABLECIMIENTO,FP.DESCRIPCION,MP.DESCRIPCION,ES.NUMERO_TICKET,ES.CAJA,ES.IMPORTE_RECIBIDO,ES.PORCENTAJE_DESCUENTO_CALCULADO,ES.PORCENTAJE_DESCUENTO_EXTRA,CFD.NUM_CFD,CFD.CALLING_ERROR_RESULT FROM SUCURSAL S,CLIENTE C,FORMA_DE_PAGO FP,METODO_DE_PAGO MP, ENTRADA_SALIDA ES LEFT JOIN CFD ON ES.CFD_ID = CFD.ID WHERE 1=1 AND ES.SUCURSAL_ID=S.ID AND ES.CLIENTE_ID=C.ID AND ES.FORMA_DE_PAGO_ID=FP.ID AND ES.METODO_DE_PAGO_ID=MP.ID AND ES.ID=$1;"| mysql --default-character-set=utf8 -u root -ppmarlen01admin PMDB104_DEVE
echo "SELECT ES.ID,ESD.CANTIDAD,ESD.PRODUCTO_CODIGO_BARRAS,ESD.PRECIO_VENTA FROM ENTRADA_SALIDA ES,ENTRADA_SALIDA_DETALLE ESD WHERE 1=1 AND ESD.ENTRADA_SALIDA_ID=ES.ID AND ES.ID=$1;" | mysql --default-character-set=utf8 -u root -ppmarlen01admin PMDB104_DEVE