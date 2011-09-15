
use amaba;

select dtUsrNaissance,u.idusr,u.txusrnom,u.txusrprenom, sp.cdsport from amaba.usr u
/*inner join usrsport usp on u.idusr=usp.idusr
inner join sport sp on sp.idsport=usp.idsport
left outer join usrprofil up on (up.idusr= u.idusr and up.lodivorce=1)*/
WHERE 1=1
and exists(
  select 1 from usradress ad
  inner join ville v on (v.idville=ad.idville)
  inner join canton can on (can.idcanton=v.idville and can.idcanton in(1,2))
  where ad.idusr = u.idusr
)
/*and exists(
  select 1 from usrsport usp1
  inner join sport sp1 on sp1.idsport=usp1.idsport and sp1.cdsport='BASKET'
  where usp1.idusr = u.idusr
)*/
and exists(
  select 1 from usrsport usp2
  inner join sport sp2 on sp2.idsport=usp2.idsport and sp2.cdsport='NATATION'
  where usp2.idusr = u.idusr
)
and exists(
  select 1 from usrinteret ui
  inner join interet i on i.idinteret=ui.idinteret and i.cdinteret='CINE'
  where ui.idusr = u.idusr
)
and exists(
  select 1 from usrprofil pr
  where pr.idusr = u.idusr
  and pr.lomarie=1
  and pr.lodivorce=1
  and pr.loavecenfant=1
)
/*and exists(
  select 1 from usrinteret ui
  inner join interet i on i.idinteret=ui.idinteret and i.cdinteret='SPORT'
  where ui.idusr = u.idusr
)*/
/*and u.idSexe=1*/
/** Age */
and (now() - dtUsrNaissance) > 40
;
select * from usr u2
inner join usrinteret ui on u2.idusr=ui.idusr
inner join interet i on i.idinteret=ui.idinteret 
;
/*
GOMES   FOOT BASKET NATATION        CINE SPORT VOYAGE
DUPOND  BASKET NATATION TENNIS      CINE VOYAGE
MARET   NATATION                    CINE SPORT

*/
select u.txusrnom,u.txusrprenom, sp.cdsport from amaba.usr u
inner join usrsport usp on u.idusr=usp.idusr
inner join sport sp on sp.idsport=usp.idsport;
select * from usrprofil;
select * from ville;
select * from usradress;
