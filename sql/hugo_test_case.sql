select * from  `amaba`.`usr` u
/*inner join usrsport us on us.idusr=u.idusr*/
inner join usradress a on a.idusr=u.idusr
where u.idusr=3;

select * from usradress;