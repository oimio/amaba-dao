/** Enum profession */
INSERT INTO PROFESSION (idprofession, cdprofession) select 1, 'ACCUEIL' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 2, 'ACHAT' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 3, 'ADMIN' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 4, 'ASSU' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 5, 'BANQUE' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 6, 'BAT' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 7, 'CADRE' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 8, 'COMMER' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 9, 'COM' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 10, 'COMPTA' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 11, 'QUALITE' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 12, 'EDU' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 13, 'HORLO' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 14, 'HOTEL' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 15, 'HUMA' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 16, 'IMMO' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 17, 'INDUSTRIE' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 18, 'INF' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 19, 'JURI' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 20, 'LOGISTIC' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 21, 'MARKETING' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 22, 'MEDICAL' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 23, 'NEGOCE' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 24, 'SECURITE' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 25, 'RH' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 26, 'SERVICE' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 27, 'SOIN' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 28, 'TRAD' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 29, 'TRANS' from dual;
INSERT INTO PROFESSION (idprofession, cdprofession) select 30, 'VENTE' from dual;

/**Traductions */

﻿insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "AR" as cdCle, "FR" as cdLangue, "Appenzell Rhodes-Extérieures" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "AR" as cdCle, "DE" as cdLangue, "Appenzell Rhodes-Extérieures" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "AR" as cdCle, "IT" as cdLangue, "Appenzell Rhodes-Extérieures" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "AI" as cdCle, "FR" as cdLangue, "Appenzell Rhodes-Intérieures" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "AI" as cdCle, "DE" as cdLangue, "Appenzell Rhodes-Intérieures" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "AI" as cdCle, "IT" as cdLangue, "Appenzell Rhodes-Intérieures" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "AG" as cdCle, "FR" as cdLangue, "Argovie" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "AG" as cdCle, "DE" as cdLangue, "Argovie" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "AG" as cdCle, "IT" as cdLangue, "Argovie" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "BE" as cdCle, "FR" as cdLangue, "Berne" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "BE" as cdCle, "DE" as cdLangue, "Berne" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "BE" as cdCle, "IT" as cdLangue, "Berne" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "BL" as cdCle, "FR" as cdLangue, "Bâle-campagne" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "BL" as cdCle, "DE" as cdLangue, "Bâle-campagne" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "BL" as cdCle, "IT" as cdLangue, "Bâle-campagne" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "BS" as cdCle, "FR" as cdLangue, "Bâle-ville" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "BS" as cdCle, "DE" as cdLangue, "Bâle-ville" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "BS" as cdCle, "IT" as cdLangue, "Bâle-ville" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "FR" as cdCle, "FR" as cdLangue, "Fribourg" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "FR" as cdCle, "DE" as cdLangue, "Fribourg" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "FR" as cdCle, "IT" as cdLangue, "Fribourg" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "GE" as cdCle, "FR" as cdLangue, "Genève" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "GE" as cdCle, "DE" as cdLangue, "Genève" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "GE" as cdCle, "IT" as cdLangue, "Genève" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "GL" as cdCle, "FR" as cdLangue, "Glaris" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "GL" as cdCle, "DE" as cdLangue, "Glaris" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "GL" as cdCle, "IT" as cdLangue, "Glaris" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "GR" as cdCle, "FR" as cdLangue, "Grisons" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "GR" as cdCle, "DE" as cdLangue, "Grisons" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "GR" as cdCle, "IT" as cdLangue, "Grisons" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "JU" as cdCle, "FR" as cdLangue, "Jura" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "JU" as cdCle, "DE" as cdLangue, "Jura" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "JU" as cdCle, "IT" as cdLangue, "Jura" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "LU" as cdCle, "FR" as cdLangue, "Lucerne" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "LU" as cdCle, "DE" as cdLangue, "Lucerne" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "LU" as cdCle, "IT" as cdLangue, "Lucerne" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "NE" as cdCle, "FR" as cdLangue, "Neuchâtel" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "NE" as cdCle, "DE" as cdLangue, "Neuchâtel" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "NE" as cdCle, "IT" as cdLangue, "Neuchâtel" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "NW" as cdCle, "FR" as cdLangue, "Nidwald" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "NW" as cdCle, "DE" as cdLangue, "Nidwald" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "NW" as cdCle, "IT" as cdLangue, "Nidwald" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "OW" as cdCle, "FR" as cdLangue, "Obwald" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "OW" as cdCle, "DE" as cdLangue, "Obwald" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "OW" as cdCle, "IT" as cdLangue, "Obwald" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "SG" as cdCle, "FR" as cdLangue, "Saint-Gall" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "SG" as cdCle, "DE" as cdLangue, "Saint-Gall" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "SG" as cdCle, "IT" as cdLangue, "Saint-Gall" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "SH" as cdCle, "FR" as cdLangue, "Schaffouse" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "SH" as cdCle, "DE" as cdLangue, "Schaffouse" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "SH" as cdCle, "IT" as cdLangue, "Schaffouse" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "SZ" as cdCle, "FR" as cdLangue, "Schwytz" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "SZ" as cdCle, "DE" as cdLangue, "Schwytz" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "SZ" as cdCle, "IT" as cdLangue, "Schwytz" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "SO" as cdCle, "FR" as cdLangue, "Soleure" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "SO" as cdCle, "DE" as cdLangue, "Soleure" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "SO" as cdCle, "IT" as cdLangue, "Soleure" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "TI" as cdCle, "FR" as cdLangue, "Tessin" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "TI" as cdCle, "DE" as cdLangue, "Tessin" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "TI" as cdCle, "IT" as cdLangue, "Tessin" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "TG" as cdCle, "FR" as cdLangue, "Thurgovie" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "TG" as cdCle, "DE" as cdLangue, "Thurgovie" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "TG" as cdCle, "IT" as cdLangue, "Thurgovie" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "UR" as cdCle, "FR" as cdLangue, "Uri" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "UR" as cdCle, "DE" as cdLangue, "Uri" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "UR" as cdCle, "IT" as cdLangue, "Uri" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "VS" as cdCle, "FR" as cdLangue, "Valais" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "VS" as cdCle, "DE" as cdLangue, "Valais" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "VS" as cdCle, "IT" as cdLangue, "Valais" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "VD" as cdCle, "FR" as cdLangue, "Vaud" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "VD" as cdCle, "DE" as cdLangue, "Vaud" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "VD" as cdCle, "IT" as cdLangue, "Vaud" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "ZG" as cdCle, "FR" as cdLangue, "Zoug" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "ZG" as cdCle, "DE" as cdLangue, "Zoug" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "ZG" as cdCle, "IT" as cdLangue, "Zoug" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "ZH" as cdCle, "FR" as cdLangue, "Zurich" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "ZH" as cdCle, "DE" as cdLangue, "Zurich" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "CANTON", "ZH" as cdCle, "IT" as cdLangue, "Zurich" as txTraduction;

/** INTERETS*/

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "CINE" as cdCle, "FR" as cdLangue, "Cinéma" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "SPORT" as cdCle, "FR" as cdLangue, "Sport" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "VOYAGE" as cdCle, "FR" as cdLangue, "Voyage" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "SPORT_MECA" as cdCle, "FR" as cdLangue, "Sports mécaniques" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "SEXE" as cdCle, "FR" as cdLangue, "Sexe / Rencontres" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "MODE" as cdCle, "FR" as cdLangue, "Mode" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "JEUXVIDEO" as cdCle, "FR" as cdLangue, "Jeux vidéo" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "INFORMATI" as cdCle, "FR" as cdLangue, "Informatique" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "THEATRE" as cdCle, "FR" as cdLangue, "Théâtre" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "PEINTURE" as cdCle, "FR" as cdLangue, "Peinture" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "MONTAGNE" as cdCle, "FR" as cdLangue, "Montage" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "DANSE" as cdCle, "FR" as cdLangue, "Danse" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "INTERNET" as cdCle, "FR" as cdLangue, "Internet" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "CUISINE" as cdCle, "FR" as cdLangue, "Cuisine" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "LITERATURE" as cdCle, "FR" as cdLangue, "Litérature" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "PLONGEE" as cdCle, "FR" as cdLangue, "Plongée" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "MUSIQUE" as cdCle, "FR" as cdLangue, "Musique" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "CINE" as cdCle, "DE" as cdLangue, "Cinéma" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "SPORT" as cdCle, "DE" as cdLangue, "Sport" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "VOYAGE" as cdCle, "DE" as cdLangue, "Voyage" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "SPORT_MECA" as cdCle, "DE" as cdLangue, "Sports mécaniques" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "SEXE" as cdCle, "DE" as cdLangue, "Sexe / Rencontres" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "MODE" as cdCle, "DE" as cdLangue, "Mode" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "JEUXVIDEO" as cdCle, "DE" as cdLangue, "Jeux vidéo" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "INFORMATI" as cdCle, "DE" as cdLangue, "Informatique" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "THEATRE" as cdCle, "DE" as cdLangue, "Théâtre" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "PEINTURE" as cdCle, "DE" as cdLangue, "Peinture" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "MONTAGNE" as cdCle, "DE" as cdLangue, "Montage" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "DANSE" as cdCle, "DE" as cdLangue, "Danse" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "INTERNET" as cdCle, "DE" as cdLangue, "Internet" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "CUISINE" as cdCle, "DE" as cdLangue, "Cuisine" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "LITERATURE" as cdCle, "DE" as cdLangue, "Litérature" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "PLONGEE" as cdCle, "DE" as cdLangue, "Plongée" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "MUSIQUE" as cdCle, "DE" as cdLangue, "Musique" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "CINE" as cdCle, "IT" as cdLangue, "Cinéma" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "SPORT" as cdCle, "IT" as cdLangue, "Sport" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "VOYAGE" as cdCle, "IT" as cdLangue, "Voyage" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "SPORT_MECA" as cdCle, "IT" as cdLangue, "Sports mécaniques" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "SEXE" as cdCle, "IT" as cdLangue, "Sexe / Rencontres" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "MODE" as cdCle, "IT" as cdLangue, "Mode" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "JEUXVIDEO" as cdCle, "IT" as cdLangue, "Jeux vidéo" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "INFORMATI" as cdCle, "IT" as cdLangue, "Informatique" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "THEATRE" as cdCle, "IT" as cdLangue, "Théâtre" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "PEINTURE" as cdCle, "IT" as cdLangue, "Peinture" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "MONTAGNE" as cdCle, "IT" as cdLangue, "Montage" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "DANSE" as cdCle, "IT" as cdLangue, "Danse" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "INTERNET" as cdCle, "IT" as cdLangue, "Internet" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "CUISINE" as cdCle, "IT" as cdLangue, "Cuisine" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "LITERATURE" as cdCle, "IT" as cdLangue, "Litérature" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "PLONGEE" as cdCle, "IT" as cdLangue, "Plongée" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "INTERET", "MUSIQUE" as cdCle, "IT" as cdLangue, "Musique" as txTraduction;

/** sport*/
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "FOOT" as cdCle, "FR" as cdLangue, "Football" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "FOOT" as cdCle, "DE" as cdLangue, "Football" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "FOOT" as cdCle, "IT" as cdLangue, "Football" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "BASKET" as cdCle, "FR" as cdLangue, "Basketball" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "BASKET" as cdCle, "DE" as cdLangue, "Basketball" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "BASKET" as cdCle, "IT" as cdLangue, "Basketball" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "NATATION" as cdCle, "FR" as cdLangue, "Natation" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "NATATION" as cdCle, "DE" as cdLangue, "Natation" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "NATATION" as cdCle, "IT" as cdLangue, "Natation" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "ESCALADE" as cdCle, "FR" as cdLangue, "Escalade" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "ESCALADE" as cdCle, "DE" as cdLangue, "Escalade" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "ESCALADE" as cdCle, "IT" as cdLangue, "Escalade" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "COURSE" as cdCle, "FR" as cdLangue, "Course à pied" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "COURSE" as cdCle, "DE" as cdLangue, "Course à pied" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "COURSE" as cdCle, "IT" as cdLangue, "Course à pied" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "TENNIS" as cdCle, "FR" as cdLangue, "Tennis" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "TENNIS" as cdCle, "DE" as cdLangue, "Tennis" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "TENNIS" as cdCle, "IT" as cdLangue, "Tennis" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "EQUITATION" as cdCle, "FR" as cdLangue, "Equitation" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "EQUITATION" as cdCle, "DE" as cdLangue, "Equitation" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "EQUITATION" as cdCle, "IT" as cdLangue, "Equitation" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "VOLLEY" as cdCle, "FR" as cdLangue, "Volley" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "VOLLEY" as cdCle, "DE" as cdLangue, "Volley" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "VOLLEY" as cdCle, "IT" as cdLangue, "Volley" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "VTT" as cdCle, "FR" as cdLangue, "Vélo - VTT" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "VTT" as cdCle, "DE" as cdLangue, "VTT" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "VTT" as cdCle, "IT" as cdLangue, "VTT" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "ROLLER" as cdCle, "FR" as cdLangue, "Roller" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "ROLLER" as cdCle, "DE" as cdLangue, "Roller" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "ROLLER" as cdCle, "IT" as cdLangue, "Roller" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "DANSE" as cdCle, "FR" as cdLangue, "Danse" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "DANSE" as cdCle, "DE" as cdLangue, "Danse" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "DANSE" as cdCle, "IT" as cdLangue, "Danse" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "PLONGEE" as cdCle, "FR" as cdLangue, "Plongée" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "PLONGEE" as cdCle, "DE" as cdLangue, "Plongée" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "PLONGEE" as cdCle, "IT" as cdLangue, "Plongée" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "VOILE" as cdCle, "FR" as cdLangue, "Voile" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "VOILE" as cdCle, "DE" as cdLangue, "Voile" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "SPORT", "VOILE" as cdCle, "IT" as cdLangue, "Voile" as txTraduction;

/** religion*/
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "CATHO" as cdCle, "FR" as cdLangue, "Christianisme" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "CATHO" as cdCle, "DE" as cdLangue, "Christianisme" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "CATHO" as cdCle, "IT" as cdLangue, "Christianisme" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "MUSULM" as cdCle, "FR" as cdLangue, "Islam" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "MUSULM" as cdCle, "DE" as cdLangue, "Islam" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "MUSULM" as cdCle, "IT" as cdLangue, "Islam" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "BOUDI" as cdCle, "FR" as cdLangue, "Bouddhisme" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "BOUDI" as cdCle, "DE" as cdLangue, "Bouddhisme" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "BOUDI" as cdCle, "IT" as cdLangue, "Bouddhisme" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "HIND" as cdCle, "FR" as cdLangue, "Hindouisme" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "HIND" as cdCle, "DE" as cdLangue, "Hindouisme" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "HIND" as cdCle, "IT" as cdLangue, "Hindouisme" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "JUDAI" as cdCle, "FR" as cdLangue, "Judaïsme" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "JUDAI" as cdCle, "DE" as cdLangue, "Judaïsme" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "JUDAI" as cdCle, "IT" as cdLangue, "Judaïsme" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "CONF" as cdCle, "FR" as cdLangue, "Confucianisme et Taoïsme" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "CONF" as cdCle, "DE" as cdLangue, "Confucianisme et Taoïsme" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "CONF" as cdCle, "IT" as cdLangue, "Confucianisme et Taoïsme" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "ATHE" as cdCle, "FR" as cdLangue, "Non-croyants" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "ATHE" as cdCle, "DE" as cdLangue, "Non-croyants" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "RELIGION", "ATHE" as cdCle, "IT" as cdLangue, "Non-croyants" as txTraduction;

/** music */
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "POP" as cdCle, "FR" as cdLangue, "Pop / Variété internationale" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "POP" as cdCle, "DE" as cdLangue, "Pop / Variété internationale" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "POP" as cdCle, "IT" as cdLangue, "Pop / Variété internationale" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "CLASSIC" as cdCle, "FR" as cdLangue, "Classic" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "CLASSIC" as cdCle, "DE" as cdLangue, "Classic" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "CLASSIC" as cdCle, "IT" as cdLangue, "Classic" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "RAP" as cdCle, "FR" as cdLangue, "Rap Hip Hop" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "RAP" as cdCle, "DE" as cdLangue, "Rap Hip Hop" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "RAP" as cdCle, "IT" as cdLangue, "Rap Hip Hop" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "FUNK" as cdCle, "FR" as cdLangue, "Soul Funk" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "FUNK" as cdCle, "DE" as cdLangue, "Soul Funk" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "FUNK" as cdCle, "IT" as cdLangue, "Soul Funk" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "DISCO" as cdCle, "FR" as cdLangue, "Disco" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "DISCO" as cdCle, "DE" as cdLangue, "Disco" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "DISCO" as cdCle, "IT" as cdLangue, "Disco" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "TECHNO" as cdCle, "FR" as cdLangue, "Techno Electro" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "TECHNO" as cdCle, "DE" as cdLangue, "Techno Electro" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "TECHNO" as cdCle, "IT" as cdLangue, "Techno Electro" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "MONDE" as cdCle, "FR" as cdLangue, "Musiques du monde" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "MONDE" as cdCle, "DE" as cdLangue, "Musiques du monde" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "MONDE" as cdCle, "IT" as cdLangue, "Musiques du monde" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "JAZZ" as cdCle, "FR" as cdLangue, "Jazz" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "JAZZ" as cdCle, "DE" as cdLangue, "Jazz" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "JAZZ" as cdCle, "IT" as cdLangue, "Jazz" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "CHFRAN" as cdCle, "FR" as cdLangue, "Chanson française" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "CHFRAN" as cdCle, "DE" as cdLangue, "Chanson française" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "CHFRAN" as cdCle, "IT" as cdLangue, "Chanson française" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "REGGAE" as cdCle, "FR" as cdLangue, "Reggae" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "REGGAE" as cdCle, "DE" as cdLangue, "Reggae" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "REGGAE" as cdCle, "IT" as cdLangue, "Reggae" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "HARD" as cdCle, "FR" as cdLangue, "Hard rock / Metal" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "HARD" as cdCle, "DE" as cdLangue, "Hard rock / Metal" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "HARD" as cdCle, "IT" as cdLangue, "Hard rock / Metal" as txTraduction;

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "BLUES" as cdCle, "FR" as cdLangue, "Blues Country Folk" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "BLUES" as cdCle, "DE" as cdLangue, "Blues Country Folk" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "MUSIC", "BLUES" as cdCle, "IT" as cdLangue, "Blues Country Folk" as txTraduction;
/** Profession */

insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "ACCUEIL" as cdCle, "FR" as cdLangue, "Accueil téléphonique et Réception" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "ACHAT" as cdCle, "FR" as cdLangue, "Achat" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "ADMIN" as cdCle, "FR" as cdLangue, "Administration et Secrétariat" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "ASSU" as cdCle, "FR" as cdLangue, "Assurances" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "BANQUE" as cdCle, "FR" as cdLangue, "Banque, Finance et Economie" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "BAT" as cdCle, "FR" as cdLangue, "Bâtiment, Construction et Paysages" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "CADRE" as cdCle, "FR" as cdLangue, "Cadre" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "COMMER" as cdCle, "FR" as cdLangue, "Commercial et Support" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "COM" as cdCle, "FR" as cdLangue, "Communication et Graphique" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "COMPTA" as cdCle, "FR" as cdLangue, "Comptabilité et Révision" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "QUALITE" as cdCle, "FR" as cdLangue, "Contrôle et Assurance Qualité" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "EDU" as cdCle, "FR" as cdLangue, "Education et Enseignement" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "HORLO" as cdCle, "FR" as cdLangue, "Horlogerie et Bijouterie" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "HOTEL" as cdCle, "FR" as cdLangue, "Hôtellerie, Restauration et Tourisme" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "HUMA" as cdCle, "FR" as cdLangue, "Humanitaire et Social" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "IMMO" as cdCle, "FR" as cdLangue, "Immobilier" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "INDUSTRIE" as cdCle, "FR" as cdLangue, "Industrie et Technique" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "INF" as cdCle, "FR" as cdLangue, "Informatique, Réseau et Télécom" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "JURI" as cdCle, "FR" as cdLangue, "Juridiques et Fiscales" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "LOGISTIC" as cdCle, "FR" as cdLangue, "Logistique, Shipping et Import/Export" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "MARKETING" as cdCle, "FR" as cdLangue, "Marketing" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "MEDICAL" as cdCle, "FR" as cdLangue, "Médicale, Biologie et Pharmacie" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "NEGOCE" as cdCle, "FR" as cdLangue, "Négoce" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "SECURITE" as cdCle, "FR" as cdLangue, "Professions de Sécurité" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "RH" as cdCle, "FR" as cdLangue, "Ressources humaines" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "SERVICE" as cdCle, "FR" as cdLangue, "Service Public" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "SOIN" as cdCle, "FR" as cdLangue, "Soin et Beauté" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "TRAD" as cdCle, "FR" as cdLangue, "Traduction et Interprétariat" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "TRANS" as cdCle, "FR" as cdLangue, "Transport" as txTraduction;
insert into traduction (cdType, cdCle,cdLangue,txTraduction) select "PROFESSION", "VENTE" as cdCle, "FR" as cdLangue, "Vente au détail et Commerces" as txTraduction;



/** Mock des adresses*/
INSERT INTO `amaba`.`usradress` (`idUsr`, `idCanton`, `STATUT`) VALUES (1, 27, 'A');
INSERT INTO `amaba`.`usradress` (`idUsr`, `idCanton`, `STATUT`) VALUES (2, 1, 'A');
INSERT INTO `amaba`.`usradress` (`idUsr`, `idCanton`, `STATUT`) VALUES (3, 4, 'A');
INSERT INTO `amaba`.`usradress` (`idUsr`, `idCanton`, `STATUT`) VALUES (4, 5, 'A');