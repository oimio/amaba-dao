

use amaba;

select u.txusrnom,u.txusrprenom, sp.cdsport from amaba.usr u
inner join usrsport usp on u.idusr=usp.idusr
inner join sport sp on sp.idsport=usp.idsport and sp.cdsport='BASKET'
inner join (
  select u1.idusr,txusrnom,txusrprenom, cdsport from amaba.usr u1
  inner join usrsport usp on u1.idusr=usp.idusr 
  inner join sport sp on sp.idsport=usp.idsport 
  where sp.cdsport='NATATION'
) as tmp on tmp.idusr=u.idusr
inner join (
  select u2.idusr,txusrnom,txusrprenom, cdsport from amaba.usr u2
  inner join usrsport usp on u2.idusr=usp.idusr 
  inner join sport sp on sp.idsport=usp.idsport 
  where sp.cdsport='FOOT'
) as tmp2 on tmp2.idusr=u.idusr
;


select u.idusr,u.txusrnom,u.txusrprenom, sp.cdsport from amaba.usr u
inner join usrsport usp on u.idusr=usp.idusr
inner join sport sp on sp.idsport=usp.idsport
inner join usrprofil up on (up.idusr= u.idusr and up.lodivorce=1)
inner join adress ad on (ad.idusr=u.idusr)
inner join ville v on (v.idville=ad.idville)
inner join canton can on (can.idcanton=v.idville)
WHERE exists(
  select 1 from amaba.usr u1
  inner join usrsport usp1 on u1.idusr=usp1.idusr
  inner join sport sp1 on sp1.idsport=usp1.idsport and sp1.cdsport='BASKET'
  where u1.idusr = u.idusr
)
and exists(
  select 1 from amaba.usr u2
  inner join usrsport usp2 on u2.idusr=usp2.idusr
  inner join sport sp2 on sp2.idsport=usp2.idsport and sp2.cdsport='NATATION'
  where u2.idusr = u.idusr
)
and u.cdsexe='M'
;
select u.txusrnom,u.txusrprenom, sp.cdsport from amaba.usr u
inner join usrsport usp on u.idusr=usp.idusr
inner join sport sp on sp.idsport=usp.idsport;

select * from ville;