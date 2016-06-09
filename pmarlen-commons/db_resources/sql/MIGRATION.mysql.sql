DROP TABLE IF EXISTS EVENTO_SINCRONIZACION;

INSERT INTO METODO_DE_PAGO VALUES (8,'EFECTIVO + TARJETA');

ALTER TABLE SUCURSAL ADD COLUMN CLAVE              VARCHAR(16)  NULL;
ALTER TABLE SUCURSAL ADD COLUMN PROHIBIDO_VENT_REG INTEGER(1)   NULL;
ALTER TABLE SUCURSAL ADD COLUMN PROHIBIDO_VENT_OPO INTEGER(1)   NULL;

ALTER TABLE USUARIO  ADD COLUMN CLAVE             INTEGER(4)   NULL;
ALTER TABLE USUARIO  ADD COLUMN EMAIL_ALTERNATIVO VARCHAR(64)  NULL;
ALTER TABLE USUARIO  ADD COLUMN SUCURSAL_ID       VARCHAR(64)  NULL;

-- ALTER TABLE USUARIO  ADD CONSTRAINT FOREIGN KEY (SUCURSAL_ID) REFERENCES SUCURSAL(ID);

ALTER TABLE PRODUCTO        ADD COLUMN DESCONTINUADO    INT(1)   NULL;
ALTER TABLE PRODUCTO        ADD COLUMN POCO             INT(4)   NULL;


ALTER TABLE ENTRADA_SALIDA  ADD COLUMN ELEM_DET         INT(4)   NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN TOT_PRODS        INT(6)   NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN SUB_TOTAL_1RA    DOUBLE   NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN SUB_TOTAL_OPO    DOUBLE   NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN SUB_TOTAL_REG    DOUBLE   NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN DESC_REDONDEO    DOUBLE   NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN REDONDEO_HAB     INT(1)   NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN TOTAL            DOUBLE   NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN TOTAL_COBRADO    DOUBLE   NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN CAMBIO           DOUBLE   NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN PEDIDO_SUCURSAL  INT(1)   NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN ES_ID_DEV        INTEGER(10) NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN SUCURSAL_ID_TRA_ORI    INTEGER(10) NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN SUCURSAL_ID_TRA_DES    INTEGER(10) NULL;
ALTER TABLE ENTRADA_SALIDA  ADD COLUMN ES_ID_TRA_ORI    INTEGER(10) NULL;

-- SELECT concat('ALTER TABLE ', TABLE_NAME, ' DROP FOREIGN KEY ', CONSTRAINT_NAME, ';') FROM information_schema.key_column_usage WHERE CONSTRAINT_SCHEMA = 'PMDB104_DEVE' AND referenced_table_name IS NOT NULL;
-- ALTER TABLE ENTRADA_SALIDA  ADD CONSTRAINT FOREIGN KEY (ES_ID_DEV)       REFERENCES ENTRADA_SALIDA(ID);
-- ALTER TABLE ENTRADA_SALIDA  ADD CONSTRAINT FOREIGN KEY (ES_ID_TRA_ORI)   REFERENCES ENTRADA_SALIDA(ID);
-- ALTER TABLE ENTRADA_SALIDA  ADD CONSTRAINT FOREIGN KEY (ES_ID_TRA_DES)   REFERENCES ENTRADA_SALIDA(ID);

ALTER TABLE ENTRADA_SALIDA_DETALLE ADD COLUMN DEV          INTEGER(10)  NULL;
ALTER TABLE ENTRADA_SALIDA_DETALLE ADD COLUMN ESD_ID_DEV   INTEGER(10)  NULL;

ALTER TABLE ENTRADA_SALIDA CHANGE FECHA_CREO FECHA_CREO TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
UPDATE ENTRADA_SALIDA SET NUMERO_TICKET = NULL WHERE NUMERO_TICKET='0';
UPDATE ENTRADA_SALIDA SET NUMERO_TICKET = NULL WHERE NUMERO_TICKET LIKE'-5%';
UPDATE ENTRADA_SALIDA SET FACTOR_IVA = 0.16;

INSERT INTO USUARIO VALUES('sucursalPM901@perfumeriamarlen.com.mx',1,'PMCAJA VERSION 901','0e4810805328801c5209f24388f8b482',0,NULL, 1);
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('sucursalPM901@perfumeriamarlen.com.mx','pmarlenuser');

INSERT INTO USUARIO VALUES('esanchez@perfumeriamarlen.com.mx'     ,1,'ESPERANZA SANCHEZ PINEDA','562cbc1526f2857b8d57c3a6c284c752',0,NULL, 4);

INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('esanchez@perfumeriamarlen.com.mx','pmarlenuser');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('esanchez@perfumeriamarlen.com.mx','stock');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('esanchez@perfumeriamarlen.com.mx','sales');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('esanchez@perfumeriamarlen.com.mx','sucadmin');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('esanchez@perfumeriamarlen.com.mx','finances');

UPDATE USUARIO SET NOMBRE_COMPLETO='HILDA MIRANDA',ABILITADO=1,PASSWORD='c60a66d09fc0bcd341b7dcf8961a48c2' WHERE EMAIL='hmiranda@perfumeriamarlen.com.mx';

INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('hmiranda@perfumeriamarlen.com.mx','pmarlenuser');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('hmiranda@perfumeriamarlen.com.mx','stock');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('hmiranda@perfumeriamarlen.com.mx','sales');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('hmiranda@perfumeriamarlen.com.mx','sucadmin');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('hmiranda@perfumeriamarlen.com.mx','finances');

INSERT INTO USUARIO VALUES('mvaldovinos@perfumeriamarlen.com.mx',1,'MARLEN VALDOVINOS TABLA','17da80fb4aa1f3a354f9b60ab5b35da1',0,NULL, 3);

INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('mvaldovinos@perfumeriamarlen.com.mx','pmarlenuser');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('mvaldovinos@perfumeriamarlen.com.mx','stock');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('mvaldovinos@perfumeriamarlen.com.mx','sales');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('mvaldovinos@perfumeriamarlen.com.mx','sucadmin');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('mvaldovinos@perfumeriamarlen.com.mx','finances');

INSERT INTO USUARIO VALUES('carubio@perfumeriamarlen.com.mx',1,'CASANDRA AIDE RUBIO IBARRA','be17af19c3cd3724abfe669db89d0f61',0,NULL, 3);

INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('carubio@perfumeriamarlen.com.mx','pmarlenuser');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('carubio@perfumeriamarlen.com.mx','stock');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('carubio@perfumeriamarlen.com.mx','sales');

INSERT INTO USUARIO VALUES('apacheco@perfumeriamarlen.com.mx',1,'ANAHY GUADALUPE PACHECO AHUMADA','23aa3d279b3f2b6488c98edd387d34b0',0,NULL, 3);

INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('apacheco@perfumeriamarlen.com.mx','pmarlenuser');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('apacheco@perfumeriamarlen.com.mx','stock');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('apacheco@perfumeriamarlen.com.mx','sales');

INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('zrocha@perfumeriamarlen.com.mx','sucadmin');

INSERT INTO USUARIO VALUES('alortega@perfumeriamarlen.com.mx',1,'AMERICA LIZBETH ORTEGA ZABALA','8187ec60e4928a0dc51c72f2881a1ca5',0,NULL, 5);

INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('alortega@perfumeriamarlen.com.mx','pmarlenuser');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('alortega@perfumeriamarlen.com.mx','stock');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('alortega@perfumeriamarlen.com.mx','sales');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('alortega@perfumeriamarlen.com.mx','sucadmin');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('alortega@perfumeriamarlen.com.mx','finances');

INSERT INTO USUARIO VALUES('gaortega@perfumeriamarlen.com.mx',1,'GUADALUPE AILYN ORTEGA ZABALA','be17af19c3cd3724abfe669db89d0f61',0,NULL, 5);

INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('gaortega@perfumeriamarlen.com.mx','pmarlenuser');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('gaortega@perfumeriamarlen.com.mx','stock');
INSERT INTO USUARIO_PERFIL(EMAIL,PERFIL) VALUES('gaortega@perfumeriamarlen.com.mx','sales');


UPDATE USUARIO SET SUCURSAL_ID=1 WHERE EMAIL='dgarcia@perfumeriamarlen.com.mx';
UPDATE USUARIO SET SUCURSAL_ID=1 WHERE EMAIL='ecastaneda@perfumeriamarlen.com.mx';
UPDATE USUARIO SET SUCURSAL_ID=1 WHERE EMAIL='root@perfumeriamarlen.com.mx';
UPDATE USUARIO SET SUCURSAL_ID=1 WHERE EMAIL='uleon@perfumeriamarlen.com.mx';

UPDATE USUARIO SET SUCURSAL_ID=2 WHERE EMAIL='zrocha@perfumeriamarlen.com.mx';
UPDATE USUARIO SET SUCURSAL_ID=3 WHERE EMAIL='mvaldovinos@perfumeriamarlen.com.mx';
UPDATE USUARIO SET SUCURSAL_ID=5 WHERE EMAIL='alortega@perfumeriamarlen.com.mx';
UPDATE USUARIO SET SUCURSAL_ID=3 WHERE EMAIL='aestrada@perfumeriamarlen.com.mx';

UPDATE USUARIO SET PASSWORD='621f4c484ee94da8946242f57bbed127' WHERE EMAIL='dgarcia@perfumeriamarlen.com.mx';
UPDATE USUARIO SET PASSWORD='3fee4c7bf3edd33b7c8aa4d111fb72a4' WHERE EMAIL='zrocha@perfumeriamarlen.com.mx';

UPDATE USUARIO SET CLAVE = RAND()*9000+1000;

UPDATE SUCURSAL SET CLAVE='PMAE', NOMBRE='PERFUMERIA MARLEN S.A. DE C.V. ALMACEN ESPERANZA',          DIRECCION='CALLE ORQUIDEAS MZ. 5, LT. 6, COL. LA ESPERANZA, MPO. DE TECAMAC, EDO. DE MEX. C.P. 55748',                    TELEFONOS='55-3872-3953' WHERE ID=1;
UPDATE SUCURSAL SET CLAVE='PMS1', NOMBRE='PERFUMERIA MARLEN S.A. DE C.V. SUCURSAL SN. MARTIN',        DIRECCION='CALLE FRANCISCO VILLA MZ. 98, LT.3, NO. 121, COL. SAN MARTIN AZCATEPEC, MPO. TECAMAC. EDO. DE MÉX. C.P. 55740',TELEFONOS='55-5936-7894' WHERE ID=2;

INSERT INTO SUCURSAL( ID,ID_PADRE,CLAVE,NOMBRE                                               ,DIRECCION          ,TELEFONOS   ,USUARIO_SICOFI,PASSWORD_SICOFI,SERIE_SICOFI,COMENTARIOS,DESCUENTO_MAYOREO_HABILITADO)
              VALUES(  3,       1,'PMS2','PERFUMERIA MARLEN S.A. DE C.V. SUCURSAL TECAMÁC'    ,'CALLE FELIPE VILLANUEVA  NO. 30, LOCAL A, COL. CENTRO, MUNICIPIO DE TECAMAC, EDO. DE MÉX. C.P. 554740'                   ,'55-5934-3788' ,'SINUSUARIO'  ,'SINPASSWORD'  ,'XXX'  ,NULL       ,NULL);
INSERT INTO SUCURSAL( ID,ID_PADRE,CLAVE,NOMBRE                                               ,DIRECCION          ,TELEFONOS   ,USUARIO_SICOFI,PASSWORD_SICOFI,SERIE_SICOFI,COMENTARIOS,DESCUENTO_MAYOREO_HABILITADO)
              VALUES(  4,       1,'PMS3','PERFUMERIA MARLEN S.A. DE C.V. SUCURSAL OJO DE AGUA','CALZADA DE LA HACIENDA NO. 20, MZ. 57, LOCAL 1, COL. HACIENDA OJO DE AGUA, MUNICIPIO DE TECAMAC, EDO. DE MÉX. C.P. 55760','55-5938-3573','SINUSUARIO'  ,'SINPASSWORD'  ,'XXX'  ,NULL       ,NULL);
INSERT INTO SUCURSAL( ID,ID_PADRE,CLAVE,NOMBRE                                               ,DIRECCION          ,TELEFONOS   ,USUARIO_SICOFI,PASSWORD_SICOFI,SERIE_SICOFI,COMENTARIOS,DESCUENTO_MAYOREO_HABILITADO)
              VALUES(  5,       1,'PMS4','PERFUMERIA MARLEN S.A. DE C.V. SUCURSAL REYES ACOZAC','CALLE REFORMA NO. 2, LOCAL 1 COL. CENTRO DE REYES ACOZAC, PLAZA COMERCIAL  “PASAJE REFORMA” MUNICIPIO DE TECÁMAC, ESTADO DE MÉXICO, C.P.L 55755'  ,'01596-9241076','SINUSUARIO'  ,'SINPASSWORD'  ,'XXX'  ,NULL       ,NULL);
INSERT INTO SUCURSAL( ID,ID_PADRE,CLAVE,NOMBRE                                               ,DIRECCION          ,TELEFONOS   ,USUARIO_SICOFI,PASSWORD_SICOFI,SERIE_SICOFI,COMENTARIOS,DESCUENTO_MAYOREO_HABILITADO)
              VALUES(  6,    NULL,'PMCD','PERFUMERIA MARLEN S.A. DE C.V. CENTRO DE DISTRIBUCIÓN TECAMAC'         ,'CALLE CENTENARIO DE LA EDUCACION MZ. 24, LT. 10, COL. CENTRO, MPO. TECAMAC, EDO. DE MEX. C.P. 55740'    ,'55-5936-2597','SINUSUARIO'  ,'SINPASSWORD'  ,'XXX'  ,NULL       ,NULL);
INSERT INTO SUCURSAL( ID,ID_PADRE,CLAVE,NOMBRE                                               ,DIRECCION          ,TELEFONOS   ,USUARIO_SICOFI,PASSWORD_SICOFI,SERIE_SICOFI,COMENTARIOS,DESCUENTO_MAYOREO_HABILITADO)
              VALUES(  7,       1,'PMS5','PERFUMERIA MARLEN S.A. DE C.V. SUCURSAL NUEVA ????','CALLE X, ESTADO DE MÉXICO, C.P. 55755'  ,'55-1234-5678','SINUSUARIO'  ,'SINPASSWORD'  ,'XXX'  ,NULL       ,NULL);

UPDATE SUCURSAL SET DESCUENTO_MAYOREO_HABILITADO=1 WHERE ID IN(2,3,4,5,6);
UPDATE SUCURSAL SET DESCUENTO_MAYOREO_HABILITADO=0 WHERE ID IN(1,7);

UPDATE SUCURSAL SET PROHIBIDO_VENT_REG=1,PROHIBIDO_VENT_OPO=1;
UPDATE SUCURSAL SET PROHIBIDO_VENT_REG=NULL,PROHIBIDO_VENT_OPO=NULL WHERE ID IN(1);
UPDATE SUCURSAL SET PROHIBIDO_VENT_REG=1,PROHIBIDO_VENT_OPO=NULL WHERE ID IN(2);

INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES ( 7, 1, 3);
INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES ( 8, 2, 3);
INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES ( 9, 3, 3);

INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES (10, 1, 4);
INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES (11, 2, 4);
INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES (12, 3, 4);

INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES (13, 1, 5);
INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES (14, 2, 5);
INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES (15, 3, 5);

INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES (16, 1, 6);
INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES (17, 2, 6);
INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES (18, 3, 6);

INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES (19, 1, 7);
INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES (20, 2, 7);
INSERT INTO ALMACEN (ID , TIPO_ALMACEN , SUCURSAL_ID) VALUES (21, 3, 7);


DROP TABLE IF EXISTS CORTE_CAJA;

CREATE TABLE     CORTE_CAJA     (
  ID               INTEGER     (10)  NOT NULL AUTO_INCREMENT ,
  TIPO_EVENTO      INTEGER     (1)   NOT NULL ,
  FECHA            TIMESTAMP         NOT NULL DEFAULT 0,
  FECHA_UAP        TIMESTAMP         NOT NULL DEFAULT 0,
  SUCURSAL_ID      INTEGER     (10)  NOT NULL ,
  CAJA             INTEGER     (3)   NULL ,
  USUARIO_EMAIL    VARCHAR     (64)  NULL ,
  SALDO_INICIAL    DOUBLE            NULL ,
  SALDO_FINAL      DOUBLE            NULL ,
  USUARIO_AUTORIZO VARCHAR     (64)  NULL ,
  COMENTARIOS      VARCHAR     (255) NULL ,
  PRIMARY KEY (ID)
)ENGINE=InnoDB;

ALTER TABLE CORTE_CAJA ADD  CONSTRAINT FOREIGN KEY (USUARIO_EMAIL   ) REFERENCES USUARIO(EMAIL);
ALTER TABLE CORTE_CAJA ADD  CONSTRAINT FOREIGN KEY (USUARIO_AUTORIZO) REFERENCES USUARIO(EMAIL);
