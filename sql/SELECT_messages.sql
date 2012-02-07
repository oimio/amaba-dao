/*
1	NON_LU
2	LU
3	ENVOYE
4	SUPPRIME
*/
use amaba;
delete from usrmessage;
select 
 MS.IDMESSAGE AS IDMESSAGE, TXSUJET AS SUJET,TXMESSAGE, mS.DTSTATUT AS DT, MS.IDMESSAGESTATUT AS STATUT, M.IDUSRTO AS IDUSRTO,M.STATUT AS MSTATUT
from usrmessage m
inner join usrmessagestatut ms on m.idmessage=ms.idmessage
inner join messagestatut st on st.idmessagestatut=ms.idmessagestatut
and ms.idusr=3
and ms.idmessagestatut=4
/*group by IDMESSAGE*/
order by dt desc, idmessage


;

;
select * from usrmessage;
select * from usrmessage m
inner join usrmessagestatut ms on m.idmessage=ms.idmessage where ms.idusr=3;
select * from messagestatut;