CREATE DATABASE auxiliovial20 template template1;
\c auxiliovial20

/*Creacion de los usuarios*/
CREATE USER operador_vial2 WITH PASSWORD '123';
CREATE USER coordinador_vial2 WITH PASSWORD '321';


/*Creacion de el esquema */
CREATE SCHEMA pro;

CREATE DOMAIN pro.cadena varchar;
CREATE DOMAIN pro.fecha date;
CREATE DOMAIN pro.hora time;
CREATE DOMAIN pro.tipo_g varchar CHECK(VALUE IN ('particular','proteccion civil'));
CREATE DOMAIN pro.tipo_t varchar CHECK(VALUE IN ('frenos','electroauto','latoneria y pintura','mecanica general')) DEFAULT 'otros';
CREATE DOMAIN pro.calif varchar CHECK(VALUE IN('malo','regular','bueno','excelente'));
CREATE DOMAIN pro.priori integer CHECK(VALUE IN (0,1,2,3,4) );

CREATE TABLE pro.ciudadano(
  ci_ciu integer PRIMARY KEY,
  nombre_ciu pro.cadena NOT NULL,
  edad_ciu integer NOT NULL,
  tlf numeric NOT NULL UNIQUE
);

CREATE TABLE pro.operador(
  ci_oper integer PRIMARY KEY,
  nombre_oper pro.cadena NOT NULL
);

CREATE TABLE pro.percance(
  fecha pro.fecha,
  hora pro.hora,
  ci_ciu integer,
  nro_persona_ac integer NOT NULL,
  ninos integer ,
  causa pro.cadena NOT NULL,
  prioridad pro.priori,
  nro_persona_ter integer,
  av_calle pro.cadena,
  municipio pro.cadena,
  parroquia pro.cadena,
  ciudad pro.cadena,
  km_aut integer,
  nombre_aut pro.cadena,
  calificacion pro.calif,
  PRIMARY KEY(fecha, hora, ci_ciu),
  FOREIGN KEY (ci_ciu) REFERENCES pro.ciudadano ON DELETE CASCADE ON UPDATE CASCADE 
);

CREATE TABLE pro.vehiculo(
  placa pro.cadena PRIMARY KEY ,
  marca pro.cadena NOT NULL,
  modelo pro.cadena NOT NULL,
  color pro.cadena NOT NULL,
  ci_ciu integer,
  FOREIGN KEY (ci_ciu) REFERENCES pro.ciudadano ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE pro.vehiculo_tiene_poliza(
  codigo pro.cadena PRIMARY KEY,
  placa pro.cadena,
  precio float NOT NULL,
  f_vencimiento pro.fecha NOT NULL,
  f_realizacion pro.fecha NOT NULL,
  descripcion pro.cadena NOT NULL,
  FOREIGN KEY (placa) REFERENCES pro.vehiculo ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE pro.taller(
  id pro.cadena PRIMARY KEY,
  nombre_e pro.cadena NOT NULL,
  tipo pro.tipo_t NOT NULL,
  tiempo_p pro.hora NULL,
  cambia_caucho integer NULL
);

CREATE TABLE pro.se_repara(
  placa pro.cadena,
  id pro.cadena,
  descripcion pro.cadena NOT NULL,
  precio float NOT NULL,
  fecha pro.fecha NOT NULL,
  PRIMARY KEY(placa,id),
  FOREIGN KEY (placa) REFERENCES pro.vehiculo ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id) REFERENCES pro.taller ON DELETE CASCADE ON UPDATE CASCADE
  
);

CREATE TABLE pro.grua(
  placa pro.cadena PRIMARY KEY,
  marca pro.cadena NOT NULL,
  serial_ca pro.cadena NOT NULL,
  serial_ch pro.cadena NOT NULL,
  carga float NOT NULL,
  modelo pro.cadena NOT NULL,
  tamano float NOT NULL,
  ano integer NOT NULL,
  tipo_g pro.tipo_g NOT NULL

);

CREATE TABLE pro.asigna(
  fecha pro.fecha,
  hora pro.hora,
  ci_ciu integer,
  ci_oper integer,
  placa pro.cadena,
  PRIMARY KEY (fecha, hora, ci_ciu),
  FOREIGN KEY (fecha, hora, ci_ciu) REFERENCES pro.percance ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (ci_oper) REFERENCES pro.operador ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (placa) REFERENCES pro.grua ON DELETE CASCADE ON UPDATE CASCADE
  
);

CREATE TABLE pro.llama(
  ci_ciu integer,
  ci_oper integer,
  fecha pro.fecha,
  PRIMARY KEY(ci_ciu, ci_oper, fecha),
  FOREIGN KEY (ci_ciu) REFERENCES pro.ciudadano ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (ci_oper) REFERENCES pro.operador ON DELETE CASCADE ON UPDATE CASCADE
  
);

CREATE TABLE pro.ref_direccion(
  fecha pro.fecha,
  hora pro.hora,
  ci_ciu integer,
  referencia pro.cadena,
  PRIMARY KEY(fecha, hora, ci_ciu, referencia),
  FOREIGN KEY (fecha, hora, ci_ciu) REFERENCES pro.percance ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE pro.conductor_grua(
  placa pro.cadena,
  conductor pro.cadena,
  PRIMARY KEY (placa, conductor),
  FOREIGN KEY (placa) REFERENCES pro.grua ON DELETE CASCADE ON UPDATE CASCADE 
);

CREATE TABLE pro.repuestos_reparacion(
  repuesto pro.cadena,
  placa pro.cadena,
  fecha pro.fecha,
  id pro.cadena,
  PRIMARY KEY(repuesto, placa, fecha, id),
  FOREIGN KEY (placa) REFERENCES pro.vehiculo ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id) REFERENCES pro.taller ON DELETE CASCADE ON UPDATE CASCADE  
);

CREATE TABLE pro.mecanicos(
  fecha pro.fecha,
  placa pro.cadena,
  nombre pro.cadena,
  id pro.cadena,
  PRIMARY KEY(fecha, placa, nombre,id),
  FOREIGN KEY (placa) REFERENCES pro.vehiculo ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (id) REFERENCES pro.taller ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE pro.tlf_taller(
  id pro.cadena,
  tlf numeric, /*se cambio el entero a cadena porque el rango es muy grande RECORDAR*/
  PRIMARY KEY(id, tlf),
  FOREIGN KEY (id) REFERENCES pro.taller ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO pro.ciudadano VALUES (20123456, 'Jose', 25, 04121234560);
INSERT INTO pro.ciudadano VALUES (21450620, 'Maria', 20 , 04144567891);
INSERT INTO pro.ciudadano VALUES (7383560, 'Eduardo', 50, 04167895431);
INSERT INTO pro.ciudadano VALUES (5678900, 'Timmy', 70, 04129876541);
INSERT INTO pro.ciudadano VALUES (24567843, 'Hilary', 19, 04167653450);
INSERT INTO pro.ciudadano VALUES (19650234, 'Pedro', 28, 04120356234);
INSERT INTO pro.ciudadano VALUES (20383677, 'Glery', 23, 04126848490);
INSERT INTO pro.ciudadano VALUES (20382020, 'Freddy', 24, 0414567891);
INSERT INTO pro.ciudadano VALUES (20840175, 'Liliam', 25, 04269876541);
INSERT INTO pro.ciudadano VALUES (10456890, 'Julian', 45, 04249876543);
INSERT INTO pro.ciudadano VALUES (11203489, 'Xiggi', 38, 04120001234);
INSERT INTO pro.ciudadano VALUES (12000876, 'Antonio', 35, 04142037771);
INSERT INTO pro.ciudadano VALUES (13987654, 'Robert', 32, 04162223344);
INSERT INTO pro.ciudadano VALUES (14908765, 'Laura', 31, 04123401112  );
INSERT INTO pro.ciudadano VALUES (15000789, 'Karlexis', 29, 04249900001);
INSERT INTO pro.ciudadano VALUES (16123954, 'Ali', 27, 04147632891);
INSERT INTO pro.ciudadano VALUES (17246801, 'Victoria', 26, 04123650489);
INSERT INTO pro.ciudadano VALUES (18000891, 'Teresa', 25, 04128090654);
INSERT INTO pro.ciudadano VALUES (19001456, 'Junior', 24, 0424567896);
INSERT INTO pro.ciudadano VALUES (20650430, 'Jesus', 23, 04141789322);
INSERT INTO pro.ciudadano VALUES (16895077, 'Gerardo', 31, 04144339506);
INSERT INTO pro.ciudadano VALUES (23795053, 'Eulises', 20, 04243621691);
INSERT INTO pro.ciudadano VALUES (16895078, 'Tulio', 29, 04124936907);
INSERT INTO pro.ciudadano VALUES (14573059, 'Gerald', 35, 04127425141);
INSERT INTO pro.ciudadano VALUES (4457232, 'Rosa', 62, 04164498077);

INSERT INTO pro.operador VALUES (21000000, 'Luis');
INSERT INTO pro.operador VALUES (22001200, 'Patricia');
INSERT INTO pro.operador VALUES (13123000, 'Carlos');
INSERT INTO pro.operador VALUES (14456900, 'Gerardo');
INSERT INTO pro.operador VALUES (15009345, 'Eulises');
INSERT INTO pro.operador VALUES (16560432, 'Mercedes');
INSERT INTO pro.operador VALUES (17200456, 'Alejandro');
INSERT INTO pro.operador VALUES (18009435, 'Elianthony');
INSERT INTO pro.operador VALUES (19123987, 'Lucia');
INSERT INTO pro.operador VALUES (10899991, 'Anais');
INSERT INTO pro.operador VALUES (11678903, 'Rebecca');
INSERT INTO pro.operador VALUES (12345000, 'Miriam');
INSERT INTO pro.operador VALUES (13000222, 'Victor');
INSERT INTO pro.operador VALUES (12222890, 'Nereida');
INSERT INTO pro.operador VALUES (15567540, 'Leonardo');
INSERT INTO pro.operador VALUES (16876900, 'Angel');
INSERT INTO pro.operador VALUES (17324001, 'Monica');
INSERT INTO pro.operador VALUES (18888654, 'Ricardo');
INSERT INTO pro.operador VALUES (19885643, 'Yuliana');
INSERT INTO pro.operador VALUES (20060031, 'Sebastian');

INSERT INTO pro.percance VALUES ('01-25-2014','07:15:25',19650234,6,3,'se le espicho un caucho',0,2,'la rosa','girardot','girardot','cagua',-1,'','bueno');
INSERT INTO pro.percance VALUES ('05-25-2014','12:32:25',11203489,9,5,'se le daño el motor',1,3,'bolivar','girardot','girardot','guacara',-1,'','excelente');
INSERT INTO pro.percance VALUES ('01-25-2014','09:21:30',13987654,6,3,'se quedo sin gasolina',2,2,'cedeño','miranda','miranda','miranda',-1,'','regular');
INSERT INTO pro.percance VALUES ('01-14-2014','01:15:25',18000891,7,5,'cambio de chasis',0,1,'la floresta','libertador','libertador','libertador',-1,'','regular');
INSERT INTO pro.percance VALUES ('01-27-2014','11:21:25',20650430,8,4,'se quedo sin gasolina',1,3,'libertador','carlos arvelo','carlos arvelo','carlos arvelo',-1,'','excelente');
INSERT INTO pro.percance VALUES ('01-29-2014','05:15:32',12000876,5,2,'parabrisa roto',2,2,'jaramillo','san diego','san diego','san diego',-1,'','bueno');
INSERT INTO pro.percance VALUES ('01-12-2014','12:18:43',19650234,10,7,'se accidento',0,2,'ricaurte','valencia','valencia','valencia',-1,'','regular');
INSERT INTO pro.percance VALUES ('04-14-2014','03:21:25',7383560,5,1,'se le daño el tanque de gasolina',2,3,'negro primero','naguanagua','naguanagua','naguanagua',-1,'','malo');
INSERT INTO pro.percance VALUES ('07-18-2014','05:12:25',20123456,7,5,'hay que limpiarle los bornes',0,1,'bolivar','bejuma','bejuma','bejuma',-1,'','bueno');
INSERT INTO pro.percance VALUES ('09-10-2014','02:32:25',14908765,8,2,'no tiene frenos',0,5,'amazonas','campo carabobo','campo carabobo','campo carabobo',-1,'','regular');
INSERT INTO pro.percance VALUES ('12-19-2014','10:22:25',13987654,9,4,'caucho sin tuercas',0,4,'nisperos','sucre','sucre','sucre',-1,'','excelente');
INSERT INTO pro.percance VALUES ('11-11-2014','06:54:21',21450620,13,6,'se le daño el faro',0,6,'los mangos','taguanes','taguanes','taguanes',-1,'','regular');
INSERT INTO pro.percance VALUES ('05-30-2014','08:43:43',12000876,12,8,'se le rayo la puerta',1,3,'','','','',1024,'regional','bueno');
INSERT INTO pro.percance VALUES ('05-24-2014','09:32:12',15000789,11,3,'sin gasolina',0,7,'ceiba','tapa tapa','tapa tapa','tapa tapa',-1,'','regular');
INSERT INTO pro.percance VALUES ('04-12-2014','07:21:45',19650234,19,9,'parabrisa roto',0,9,'saman','puerto cabello','puerto cabello','puerto cabello',-1,'','malo');
INSERT INTO pro.percance VALUES ('03-16-2014','08:15:21',10456890,6,3,'bloqueo de puertas',2,2,'libertad','ciudad alianza','ciudad alianza','ciudad alianza',-1,'','regular');
INSERT INTO pro.percance VALUES ('02-18-2014','01:15:25',18000891,7,1,'daño de caja',0,5,'','','','',550,'regional','regular');
INSERT INTO pro.percance VALUES ('01-19-2014','01:12:23',12000876,8,5,'bateria dañada',0,2,'rojo','los guayos','los guayos','los guayos',-1,'','malo');
INSERT INTO pro.percance VALUES ('07-15-2014','08:19:32',15000789,9,6,'correa de tiempo dañada',0,2,'tuerca','san joaquin','san joaquin','cagua',-1,'','regular');
INSERT INTO pro.percance VALUES ('08-07-2014','11:56:51',18000891,10,8,'direccion hidraulica dañada',0,1,'','','','',750,'regional','bueno');
INSERT INTO pro.percance VALUES ('08-07-2014','11:48:51',16895077,5,2,'empacadura del motor quemada',0,2,'las quintas de naguanagua','naguanagua','naguanagua','valencia',-1,'','regular');
INSERT INTO pro.percance VALUES ('08-07-2014','01:42:59',23795053,3,2,'carter fracturado',0,0,'las quintas de naguanagua','naguanagua','naguanagua','valencia',-1,'','regular');
INSERT INTO pro.percance VALUES ('12-16-2014','02:33:22',16895078,4,1,'bomba de la gasolina dañada',3,2,'las quintas de naguanagua','naguanagua','naguanagua','valencia',-1,'','regular');
INSERT INTO pro.percance VALUES ('05-30-2014','06:56:09',14573059,8,2,'problema electrico',4,5,'las quintas de naguanagua','naguanagua','naguanagua','valencia',-1,'','regular');
INSERT INTO pro.percance VALUES ('05-24-2014','03:42:35',4457232,3,2,'alternador dañado',0,0,'las quintas de naguanagua','naguanagua','naguanagua','valencia',-1,'','regular');


INSERT INTO pro.vehiculo VALUES ('xxx456','chevrolet','spark','blanco',19650234);
INSERT INTO pro.vehiculo VALUES ('yyy7r4','daewoo','cielo','verde',20123456);
INSERT INTO pro.vehiculo VALUES ('pour65','chevrolet','optra','azul',12000876);
INSERT INTO pro.vehiculo VALUES ('uut709','cadillac','escalade','rojo',14908765);
INSERT INTO pro.vehiculo VALUES ('ppty6r','mazda','demio','amarillo',18000891);
INSERT INTO pro.vehiculo VALUES ('ggggtyu4','toyota','corolla','beige',13987654);
INSERT INTO pro.vehiculo VALUES ('uyrioo8','honda','legend','naranja',15000789);
INSERT INTO pro.vehiculo VALUES ('67iiyut4','audi','a4','morado',7383560);
INSERT INTO pro.vehiculo VALUES ('was876','chevrolet','silverado','plateado',21450620);
INSERT INTO pro.vehiculo VALUES ('eug78j','chevrolet','corsa','azul cielo',16123954);
INSERT INTO pro.vehiculo VALUES ('ler876','dodge','caliber','negro',16123954);
INSERT INTO pro.vehiculo VALUES ('hggryu7','lexus','ls','gris',12000876);
INSERT INTO pro.vehiculo VALUES ('kjht565','seat','leon','rosa',20383677);
INSERT INTO pro.vehiculo VALUES ('xar234','ford','mercury','blanco',13987654);
INSERT INTO pro.vehiculo VALUES ('poiu877','mitsubish','lancer','rojo',20382020);
INSERT INTO pro.vehiculo VALUES ('09uikuu','mercedes benz','sprinter','gris',10456890);
INSERT INTO pro.vehiculo VALUES ('oty98y','renault','symbol','verde',11203489);
INSERT INTO pro.vehiculo VALUES ('rrrt876','renault','fuego','dorado',24567843);
INSERT INTO pro.vehiculo VALUES ('wert98y','toyota','meru','vinotinto',5678900);

INSERT INTO pro.vehiculo_tiene_poliza VALUES ('TR01','wert98y',20000,'01-25-2013','07-28-2011','poliza para choques');
INSERT INTO pro.vehiculo_tiene_poliza VALUES ('TR02','xar234',30000,'05-25-2012','07-28-2011','poliza para choques');
INSERT INTO pro.vehiculo_tiene_poliza VALUES ('TR03','rrrt876',40000,'03-25-2012','01-28-2011','poliza para choques');
INSERT INTO pro.vehiculo_tiene_poliza VALUES ('TR04','09uikuu',20000,'08-30-2012','01-21-2010','poliza para choques');
INSERT INTO pro.vehiculo_tiene_poliza VALUES ('TR05','xxx456',110000,'03-25-2012','01-19-2011','poliza para choques');
INSERT INTO pro.vehiculo_tiene_poliza VALUES ('TR06','yyy7r4',100000,'01-01-2009','01-21-2010','poliza para choques');
INSERT INTO pro.vehiculo_tiene_poliza VALUES ('TR07','pour65',90000,'08-05-2009','09-17-2013','poliza para choques');
INSERT INTO pro.vehiculo_tiene_poliza VALUES ('TR08','was876',70000,'08-30-2012','01-21-2010','poliza para choques');
INSERT INTO pro.vehiculo_tiene_poliza VALUES ('TR09','kjht565',20000,'08-30-2012','01-21-2010','poliza para choques');
INSERT INTO pro.vehiculo_tiene_poliza VALUES ('TR10','hggryu7',20000,'12-15-2014','01-21-2013','poliza para choques');


INSERT INTO pro.taller VALUES ('xxxx','luis garcia','frenos');
INSERT INTO pro.taller VALUES ('yyyy','eulises contreras','latoneria y pintura','17:23:54');
INSERT INTO pro.taller VALUES ('xxyy','jorge rodriguez','latoneria y pintura','09:23:54',3);
INSERT INTO pro.taller VALUES ('yyxx','tomas aguilar','latoneria y pintura','00:00:00',2);
INSERT INTO pro.taller VALUES ('xyxy','manuel lopez','electroauto');
INSERT INTO pro.taller VALUES ('yxyx','jose abreu','latoneria y pintura','14:34:45');
INSERT INTO pro.taller VALUES ('wwww','carlos jose','mecanica general','00:00:00',2);
INSERT INTO pro.taller VALUES ('tttt','jhonsai cheng','latoneria y pintura','01:32:45');
INSERT INTO pro.taller VALUES ('wwtt','jose rodriguez','latoneria y pintura','04:21:06',3);
INSERT INTO pro.taller VALUES ('wtwt','falon colon','frenos','00:00:00',1);
INSERT INTO pro.taller VALUES ('twtw','jhon ross','latoneria y pintura','06:35:23',2);
INSERT INTO pro.taller VALUES ('llll','cesar manrique','latoneria y pintura','10:32:12');
INSERT INTO pro.taller VALUES ('pppp','jorge mora','mecanica general');
INSERT INTO pro.taller VALUES ('llpp','elsa tovar','latoneria y pintura','01:34:15');
INSERT INTO pro.taller VALUES ('ttww','gary oldman','latoneria y pintura','09:24:31');
INSERT INTO pro.taller VALUES ('ppll','lewis contreras','frenos');
INSERT INTO pro.taller VALUES ('plpl','kristopher perdomo','electroauto');

INSERT INTO pro.se_repara VALUES ('xxx456','xxxx','se repararon los frenos',2000000,'02-01-2014');
INSERT INTO pro.se_repara VALUES ('yyy7r4','xxxx','se repararon los frenos',230000,'08-14-2013');
INSERT INTO pro.se_repara VALUES ('pour65','xxxx','se repararon los frenos',300000,'12-22-2013');
INSERT INTO pro.se_repara VALUES ('hggryu7','twtw','se reparo una aboyadura y se pinto de color verde',5000000,'05-12-2013');
INSERT INTO pro.se_repara VALUES ('was876','pppp','se cambiaron las bujias',23400000,'11-21-2011');
INSERT INTO pro.se_repara VALUES ('ler876','xxxx','se repararon los frenos',2000000,'03-30-2014');
INSERT INTO pro.se_repara VALUES ('kjht565','xxxx','se repararon los frenos',2000000,'05-01-2014');
INSERT INTO pro.se_repara VALUES ('67iiyut4','xxxx','se repararon los frenos',2000000,'02-11-2014');
INSERT INTO pro.se_repara VALUES ('oty98y','xxxx','se repararon los frenos',2000000,'05-28-2014');
INSERT INTO pro.se_repara VALUES ('xxx456','pppp','se repararon los frenos',2000000,'09-17-2014');
INSERT INTO pro.se_repara VALUES ('xxx456','twtw','se repararon los frenos',2000000,'11-21-2014');
INSERT INTO pro.se_repara VALUES ('09uikuu','xxxx','se repararon los frenos',2000000,'02-12-2014');
INSERT INTO pro.se_repara VALUES ('hggryu7','xxxx','se repararon los frenos',2000000,'10-18-2014');

INSERT INTO pro.grua VALUES ('cyzerew','chevrolet','xxxxx','yyyyyy',25,'sqlyt',43,1994,'particular');
INSERT INTO pro.grua VALUES ('cyzewes','daewoo','wecrte','dvbseeas',12,'dfsvcxa',54,1992,'proteccion civil');
INSERT INTO pro.grua VALUES ('ctrdsaa','optra','mdydne','1237sn',29,'sdjamw',32,1987,'particular');
INSERT INTO pro.grua VALUES ('515118ewf','xzt','xxsdfsdas','ysdsa32',54,'ssdwe3',45,1996,'particular');
INSERT INTO pro.grua VALUES ('258sda','arauca','2589sdxxx','872sweyyy',34,'s87sdfyt',32,1981,'proteccion civil');
INSERT INTO pro.grua VALUES ('25893g3g','toyota','xytr78','treq8y',32,'ghytds8',15,1978,'particular');
INSERT INTO pro.grua VALUES ('c897hlw','honda','87gugyt','lksjr87',34,'24687g',26,1991,'proteccion civil');
INSERT INTO pro.grua VALUES ('784dsw','arauca','terwaxx','sa43yyyy',25,'sqsdwlyt',33,1976,'particular');
INSERT INTO pro.grua VALUES ('783saw','optra','porycx','tgbfs8',14,'gtsa5',20,1954,'particular');
INSERT INTO pro.grua VALUES ('c897tw','chevrolet','yrtebe','yyxxyy',43,'254sdfl',14,1991,'particular');
INSERT INTO pro.grua VALUES ('874fdsw','optra','xx05df','porycnmm',32,'aspfire',34,1934,'proteccion civil');
INSERT INTO pro.grua VALUES ('cyyhgfv','toyota','pkljnr87','yteba85',32,'scvfdr',54,1987,'particular');
INSERT INTO pro.grua VALUES ('c987541kgv','volvo','nhyrca4','ysdas1',65,'ydf542',23,1957,'particular');
INSERT INTO pro.grua VALUES ('cmfvfhbn12','mercury','mlkdge87','0000y',43,'slounhv',87,1911,'particular');
INSERT INTO pro.grua VALUES ('cy025j896','mitsubish','mnve21','00yyewyy',21,'ytfdx',32,1998,'proteccion civil');
INSERT INTO pro.grua VALUES ('0cyr0ew00','mercedes benz','mjeta6','5874sdal',28,'mlpokjn',34,1914,'particular');
INSERT INTO pro.grua VALUES ('cyz5874k','jaguar','yrta23','ymlskaoe5',30,'4875mmu',12,1922,'particular');
INSERT INTO pro.grua VALUES ('b74sa2','dodge','dytr257','0sea',32,'s2584ds',43,1921,'particular');
INSERT INTO pro.grua VALUES ('cy025896','audi','mnsdve21','00yyewyy',21,'ytfdx',32,1998,'proteccion civil');
INSERT INTO pro.grua VALUES ('rtrw00','saturn','mjesdta6','58sdsa74sdal',43,'mlpsdaokjn',54,1932,'particular');

INSERT INTO pro.asigna VALUES ('01-25-2014','07:15:25',19650234,21000000,'cyzewes');
INSERT INTO pro.asigna VALUES ('05-25-2014','12:32:25',11203489,22001200,'515118ewf');
INSERT INTO pro.asigna VALUES ('01-25-2014','09:21:30',13987654,13123000,'ctrdsaa');
INSERT INTO pro.asigna VALUES ('01-14-2014','01:15:25',18000891,14456900,'258sda');
INSERT INTO pro.asigna VALUES ('01-27-2014','11:21:25',20650430,18009435,'c897hlw');
INSERT INTO pro.asigna VALUES ('01-29-2014','05:15:32',12000876,11678903,'783saw');
INSERT INTO pro.asigna VALUES ('01-12-2014','12:18:43',19650234,20060031,'874fdsw');
INSERT INTO pro.asigna VALUES ('04-14-2014','03:21:25',7383560,12222890,'c987541kgv');
INSERT INTO pro.asigna VALUES ('07-18-2014','05:12:25',20123456,11678903,'cy025j896');
INSERT INTO pro.asigna VALUES ('09-10-2014','02:32:25',14908765,18888654,'cy025896');
INSERT INTO pro.asigna VALUES ('12-19-2014','10:22:25',13987654,21000000,'c897tw');
INSERT INTO pro.asigna VALUES ('11-11-2014','06:54:21',21450620,12345000,'cyz5874k');
INSERT INTO pro.asigna VALUES ('05-30-2014','08:43:43',12000876,18888654,'rtrw00');
INSERT INTO pro.asigna VALUES ('05-24-2014','09:32:12',15000789,13123000,'b74sa2');
INSERT INTO pro.asigna VALUES ('04-12-2014','07:21:45',19650234,17324001,'0cyr0ew00');

INSERT INTO pro.llama VALUES (20123456,21000000,'04-14-2014');
INSERT INTO pro.llama VALUES (21450620,22001200,'01-25-2014');
INSERT INTO pro.llama VALUES (7383560,13123000,'09-10-2014');
INSERT INTO pro.llama VALUES (5678900,21000000,'01-01-2009');
INSERT INTO pro.llama VALUES (24567843,14456900,'11-21-2014');
INSERT INTO pro.llama VALUES (21450620,15009345,'05-30-2014');
INSERT INTO pro.llama VALUES (20383677,18009435,'02-12-2014');
INSERT INTO pro.llama VALUES (20382020,19123987,'01-27-2014');
INSERT INTO pro.llama VALUES (20840175,16560432,'02-01-2014');
INSERT INTO pro.llama VALUES (10456890,11678903,'01-01-2009');
INSERT INTO pro.llama VALUES (11203489,10899991,'01-25-2014');
INSERT INTO pro.llama VALUES (12000876,12345000,'02-18-2014');
INSERT INTO pro.llama VALUES (20382020,16560432,'01-27-2014');
INSERT INTO pro.llama VALUES (16123954,13000222,'01-01-2009');
INSERT INTO pro.llama VALUES (20382020,10899991,'05-30-2014');

INSERT INTO pro.ref_direccion VALUES ('01-25-2014','07:15:25',19650234,'cagua edo aragua corinsa');
INSERT INTO pro.ref_direccion VALUES ('05-25-2014','12:32:25',11203489,'valencia edo carabobo naguanagua');
INSERT INTO pro.ref_direccion VALUES ('01-25-2014','09:21:30',13987654,'cagua edo aragua la encrucijada');
INSERT INTO pro.ref_direccion VALUES ('01-14-2014','01:15:25',18000891,'maracay edo aragua las delicias');
INSERT INTO pro.ref_direccion VALUES ('01-27-2014','11:21:25',20650430,'maracay edo aragua casanova godoy');
INSERT INTO pro.ref_direccion VALUES ('01-29-2014','05:15:32',12000876,'maracay edo aragua av.soublete');
INSERT INTO pro.ref_direccion VALUES ('01-12-2014','12:18:43',19650234,'los tques edo miranda santa rosa');
INSERT INTO pro.ref_direccion VALUES ('04-14-2014','03:21:25',7383560,'cagua edo aragua la gran mariscal');
INSERT INTO pro.ref_direccion VALUES ('07-18-2014','05:12:25',20123456,'valencia edo carabobo forum');
INSERT INTO pro.ref_direccion VALUES ('09-10-2014','02:32:25',14908765,'maracay edo aragua c.c global');
INSERT INTO pro.ref_direccion VALUES ('12-19-2014','10:22:25',13987654,'maracay edo aragua av fza.aereas');
INSERT INTO pro.ref_direccion VALUES ('11-11-2014','06:54:21',21450620,'maracay edo aragua av bolivar');
INSERT INTO pro.ref_direccion VALUES ('05-30-2014','08:43:43',12000876,'cagua edo aragua pollo los llanos');
INSERT INTO pro.ref_direccion VALUES ('05-24-2014','09:32:12',15000789,'los teques edo miranda los overos');
INSERT INTO pro.ref_direccion VALUES ('04-12-2014','07:21:45',19650234,'los teques edo miranda los nuevos teques');
INSERT INTO pro.ref_direccion VALUES ('03-16-2014','08:15:21',10456890,'cagua edo aragua mcdonald');
INSERT INTO pro.ref_direccion VALUES ('02-18-2014','01:15:25',18000891,'maracay edo aragua c.c los aviadores');
INSERT INTO pro.ref_direccion VALUES ('01-19-2014','01:12:23',12000876,'maracay edo aragua c.c parque aragua');
INSERT INTO pro.ref_direccion VALUES ('07-15-2014','08:19:32',15000789,'maracay edo aragua c.c hyper jumbo');
INSERT INTO pro.ref_direccion VALUES ('08-07-2014','11:56:51',18000891,'maracay edo aragua c.c galeria');

INSERT INTO pro.conductor_grua VALUES ('cyzerew','Luis Salas');
INSERT INTO pro.conductor_grua VALUES ('cyzewes','Carlos Aponte');
INSERT INTO pro.conductor_grua VALUES ('ctrdsaa','Glery Cardoza');
INSERT INTO pro.conductor_grua VALUES ('515118ewf','Eulises Contreras');
INSERT INTO pro.conductor_grua VALUES ('258sda','Gerardo Garcia');
INSERT INTO pro.conductor_grua VALUES ('25893g3g','Lewis Contreras');
INSERT INTO pro.conductor_grua VALUES ('c897hlw','Jhon Trabolta');
INSERT INTO pro.conductor_grua VALUES ('784dsw','Samuel Herrera');
INSERT INTO pro.conductor_grua VALUES ('783saw','Campo Elias');
INSERT INTO pro.conductor_grua VALUES ('c897tw','Jose gregorio Rivas');
INSERT INTO pro.conductor_grua VALUES ('874fdsw','Elio Vegas');
INSERT INTO pro.conductor_grua VALUES ('cyyhgfv','Oscar D´Jesus');
INSERT INTO pro.conductor_grua VALUES ('c987541kgv','Martin Newton');
INSERT INTO pro.conductor_grua VALUES ('cmfvfhbn12','Leonardo Di caprio');
INSERT INTO pro.conductor_grua VALUES ('cy025j896','Samuel L´Jackson');
INSERT INTO pro.conductor_grua VALUES ('0cyr0ew00','Tom Cruise');
INSERT INTO pro.conductor_grua VALUES ('cyz5874k','Estevan Fontaines');
INSERT INTO pro.conductor_grua VALUES ('b74sa2','Ciro Moreno');
INSERT INTO pro.conductor_grua VALUES ('cy025896','Nicolas Hernandez');
INSERT INTO pro.conductor_grua VALUES ('rtrw00','Andres Corro');

INSERT INTO pro.repuestos_reparacion VALUES ('bateria','xxx456','11-11-2014','xxxx');
INSERT INTO pro.repuestos_reparacion VALUES ('caucho','67iiyut4','03-25-2012','llll');
INSERT INTO pro.repuestos_reparacion VALUES ('puerta','ggggtyu4','12-19-2014','ttww');
INSERT INTO pro.repuestos_reparacion VALUES ('caja','ler876','08-14-2013','llll');
INSERT INTO pro.repuestos_reparacion VALUES ('bugias','kjht565','01-19-2011','llpp');
INSERT INTO pro.repuestos_reparacion VALUES ('motor','xar234','01-29-2014','wtwt');
INSERT INTO pro.repuestos_reparacion VALUES ('tanque de gasolina','67iiyut4','01-27-2014','wtwt');
INSERT INTO pro.repuestos_reparacion VALUES ('bateria','xxx456','01-29-2014','wtwt');
INSERT INTO pro.repuestos_reparacion VALUES ('motor','rrrt876','12-19-2014','ttww');
INSERT INTO pro.repuestos_reparacion VALUES ('radiador','ler876','01-19-2011','tttt');
INSERT INTO pro.repuestos_reparacion VALUES ('tubo de escape','yyy7r4','01-12-2014','llpp');
INSERT INTO pro.repuestos_reparacion VALUES ('caucho','eug78j','01-29-2014','wtwt');
INSERT INTO pro.repuestos_reparacion VALUES ('caja','xar234','08-14-2013','tttt');
INSERT INTO pro.repuestos_reparacion VALUES ('bujias','kjht565','12-19-2014','wtwt');
INSERT INTO pro.repuestos_reparacion VALUES ('tanque de aceite','kjht565','12-19-2014','tttt');
INSERT INTO pro.repuestos_reparacion VALUES ('faro delantero','ggggtyu4','12-19-2014','xxxx');
INSERT INTO pro.repuestos_reparacion VALUES ('motor','kjht565','01-29-2014','ttww');
INSERT INTO pro.repuestos_reparacion VALUES ('puerta','rrrt876','08-14-2013','llpp');
INSERT INTO pro.repuestos_reparacion VALUES ('parabrisas','xar234','01-12-2014','wtwt');
INSERT INTO pro.repuestos_reparacion VALUES ('rines','eug78j','12-19-2014','xxxx');

INSERT INTO pro.mecanicos VALUES ('02-01-2014','ggggtyu4','Juan Ojeda','wwww');
INSERT INTO pro.mecanicos VALUES ('01-30-2014','oty98y','Luis Leon','ttww');
INSERT INTO pro.mecanicos VALUES ('03-16-2014','09uikuu','Daniel Rosquete','yxyx');
INSERT INTO pro.mecanicos VALUES ('05-30-2014','xar234','Pedro Linares','ttww');
INSERT INTO pro.mecanicos VALUES ('07-15-2014','ler876','Aldo Reyes','xxxx');
INSERT INTO pro.mecanicos VALUES ('05-25-2014','xar234','Amadis Garcia','yxyx');
INSERT INTO pro.mecanicos VALUES ('01-12-2014','poiu877','Anibal Guerra','wwww');
INSERT INTO pro.mecanicos VALUES ('01-14-2014','was876','Omar Contreras','llll');
INSERT INTO pro.mecanicos VALUES ('09-10-2014','oty98y','Ludwin Contreras','plpl');
INSERT INTO pro.mecanicos VALUES ('05-25-2014','uyrioo8','Eloy Blanco','tttt');
INSERT INTO pro.mecanicos VALUES ('03-16-2014','wert98y','Andres Contreras','yyxx');
INSERT INTO pro.mecanicos VALUES ('02-01-2014','09uikuu','Marco Antonio','tttt');
INSERT INTO pro.mecanicos VALUES ('01-14-2014','ler876','Diego Elias','yyxx');
INSERT INTO pro.mecanicos VALUES ('07-15-2014','poiu877','Alejandro Contreras','xyxy');
INSERT INTO pro.mecanicos VALUES ('01-12-2014','67iiyut4','Oleary Contreras','llll');
INSERT INTO pro.mecanicos VALUES ('05-30-2014','eug78j','Ramon Hernandez','xxxx');
INSERT INTO pro.mecanicos VALUES ('01-19-2014','ggggtyu4','Kique Dominguez','xyxy');
INSERT INTO pro.mecanicos VALUES ('01-30-2014','xar234','Jose Ochoa','yyxx');
INSERT INTO pro.mecanicos VALUES ('05-25-2014','ler876','Ali Ramirez','plpl');
INSERT INTO pro.mecanicos VALUES ('08-07-2014','67iiyut4','Eulises Rengifo','ppll');

INSERT INTO pro.tlf_taller VALUES ('xxxx',02443621691);
INSERT INTO pro.tlf_taller VALUES ('yyyy',02443621854);
INSERT INTO pro.tlf_taller VALUES ('xxyy',02443026587);
INSERT INTO pro.tlf_taller VALUES ('yyxx',02443985471);
INSERT INTO pro.tlf_taller VALUES ('xyxy',02443974158);
INSERT INTO pro.tlf_taller VALUES ('yxyx',02443579471);
INSERT INTO pro.tlf_taller VALUES ('wwww',02441597471);
INSERT INTO pro.tlf_taller VALUES ('tttt',02443972350);
INSERT INTO pro.tlf_taller VALUES ('wwtt',02443000555);
INSERT INTO pro.tlf_taller VALUES ('wtwt',02449777858);
INSERT INTO pro.tlf_taller VALUES ('twtw',02443966331);
INSERT INTO pro.tlf_taller VALUES ('llll',02444411587);
INSERT INTO pro.tlf_taller VALUES ('pppp',02443934121);
INSERT INTO pro.tlf_taller VALUES ('llpp',02443371952);
INSERT INTO pro.tlf_taller VALUES ('ttww',02441826347);
INSERT INTO pro.tlf_taller VALUES ('ppll',02443888741);
INSERT INTO pro.tlf_taller VALUES ('plpl',02446925147);
INSERT INTO pro.tlf_taller VALUES ('pppp',02440014121);
INSERT INTO pro.tlf_taller VALUES ('llpp',02441122952);
INSERT INTO pro.tlf_taller VALUES ('ttww',02441009827);
INSERT INTO pro.tlf_taller VALUES ('ppll',02443846778);
INSERT INTO pro.tlf_taller VALUES ('plpl',02446014789);


GRANT ALL ON pro.ciudadano TO coordinador_vial2;
GRANT ALL ON pro.operador TO coordinador_vial2;
GRANT ALL ON pro.percance TO coordinador_vial2;
GRANT ALL ON pro.vehiculo TO coordinador_vial2;
GRANT ALL ON pro.vehiculo_tiene_poliza TO coordinador_vial2;
GRANT ALL ON pro.taller TO coordinador_vial2;
GRANT ALL ON pro.se_repara TO coordinador_vial2;
GRANT ALL ON pro.grua TO coordinador_vial2;
GRANT ALL ON pro.asigna TO coordinador_vial2;
GRANT ALL ON pro.llama TO coordinador_vial2;
GRANT ALL ON pro.ref_direccion TO coordinador_vial2;
GRANT ALL ON pro.conductor_grua TO coordinador_vial2;
GRANT ALL ON pro.repuestos_reparacion TO coordinador_vial2;
GRANT ALL ON pro.mecanicos TO coordinador_vial2;
GRANT ALL ON pro.tlf_taller TO coordinador_vial2;

GRANT INSERT ON pro.percance TO operador_vial2;
GRANT SELECT ON pro.grua TO operador_vial2;
GRANT SELECT ON pro.ciudadano TO operador_vial2;

CREATE OR REPLACE FUNCTION pro.promedio_fecha(fecha1 pro.fecha,fecha2 pro.fecha)  RETURNS numeric AS   $$
declare dias numeric;
declare cant_per numeric;
begin
 cant_per :=sum(x.cant_dia) from (select fecha,count(*) as cant_dia from pro.grua g, pro.asigna a 
where g.placa=a.placa and a.fecha>=fecha1 and a.fecha<=fecha2 group by (fecha)) as x;
dias :=fecha2-fecha1+1;
return cant_per/dias;
 
end
$$ language plpgsql;




/*consulta 1*/
select pro.promedio_fecha('2014-01-01','2014-12-01');

/*consulta 2*/
select y.municipio from
(select count(p.municipio) as max_munc, p.municipio  from  pro.percance p group by (p.municipio)) as y,
(select max(x.max_munc) as max_munc from
(select count(p.municipio) as max_munc, p.municipio  from  pro.percance p group by (p.municipio)) as x) as w where y.max_munc=w.max_munc ; 

/*consulta 3*/
(select c.nombre_ciu,c.ci_ciu from pro.vehiculo v,pro.ciudadano c 
  where v.placa not in(select p.placa from pro.vehiculo_tiene_poliza p) and c.ci_ciu = v.ci_ciu);
  
  
/*consulta 4*/

select p.fecha, p.hora, p.ci_ciu, p.nro_persona_ac, p.ninos, p.causa, p.prioridad, p.nro_persona_ter,
 p.av_calle, p.municipio, p.parroquia, p.ciudad, p.km_aut, p.nombre_aut, p.calificacion 
 from 
(select c.ci_ciu from pro.vehiculo_tiene_poliza p,pro.ciudadano c,pro.vehiculo v
	where (p.f_vencimiento BETWEEN '2014-12-01' and '2014-12-31') and c.ci_ciu = v.ci_ciu and v.placa= p.placa) as x,pro.percance p
 where p.ci_ciu=x.ci_ciu  order by p.ci_ciu,p.fecha;
