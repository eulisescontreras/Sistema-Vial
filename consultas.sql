/*1) Promedio de grúas solicitadas diariamente
*/

select pro.promedio_fecha('2014-01-01','2014-12-01');

/*2) Municipio donde se han accidentan más vehículos. 
*/
select y.municipio from
(select count(p.municipio) as max_munc, p.municipio  from  pro.percance p group by (p.municipio)) as y,
(select max(x.max_munc) as max_munc from
(select count(p.municipio) as max_munc, p.municipio  from  pro.percance p group by (p.municipio)) as x) as w where y.max_munc=w.max_munc ; 


/*3) Lista de ciudadanos que han utilizado el servicio de grúas y nunca han comprado una póliza económica. */

(select c.nombre_ciu,c.ci_ciu from pro.vehiculo v,pro.ciudadano c 
  where v.placa not in(select p.placa from pro.vehiculo_tiene_poliza p) and c.ci_ciu = v.ci_ciu);

/*4) Reporte de la siniestralidad de cada asegurado cuya póliza se haya
vencido en diciembre de 2014.
*/
select p.fecha, p.hora, p.ci_ciu, p.nro_persona_ac, p.ninos, p.causa, p.prioridad, p.nro_persona_ter,
 p.av_calle, p.municipio, p.parroquia, p.ciudad, p.km_aut, p.nombre_aut, p.calificacion 
 from 
(select c.ci_ciu from pro.vehiculo_tiene_poliza p,pro.ciudadano c,pro.vehiculo v
	where (p.f_vencimiento BETWEEN '2014-12-01' and '2014-12-31') and c.ci_ciu = v.ci_ciu and v.placa= p.placa) as x,pro.percance p
 where p.ci_ciu=x.ci_ciu  order by p.ci_ciu,p.fecha;

