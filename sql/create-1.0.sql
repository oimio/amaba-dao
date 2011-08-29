SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `amaba` ;
CREATE SCHEMA IF NOT EXISTS `amaba` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `amaba` ;

-- -----------------------------------------------------
-- Table `amaba`.`sexe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`sexe` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`sexe` (
  `idSexe` INT NOT NULL ,
  `cdSexe` VARCHAR(1) NOT NULL COMMENT 'M ou F' ,
  PRIMARY KEY (`idSexe`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`usr`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`usr` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`usr` (
  `idUsr` INT NOT NULL AUTO_INCREMENT ,
  `txUsrNom` VARCHAR(45) NOT NULL ,
  `txUsrPrenom` VARCHAR(45) NOT NULL ,
  `txUsrEmail` VARCHAR(150) NOT NULL ,
  `dtUsrNaissance` DATE NOT NULL ,
  `idSexe` INT NOT NULL COMMENT 'm ou f' ,
  PRIMARY KEY (`idUsr`) ,
  INDEX `FK_USR_SEXE` (`idSexe` ASC) ,
  CONSTRAINT `FK_USR_SEXE`
    FOREIGN KEY (`idSexe` )
    REFERENCES `amaba`.`sexe` (`idSexe` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`pays`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`pays` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`pays` (
  `idPays` INT NOT NULL AUTO_INCREMENT ,
  `cdPays` VARCHAR(5) NOT NULL ,
  PRIMARY KEY (`idPays`) ,
  INDEX `idx_cd_pays` (`cdPays` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`canton`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`canton` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`canton` (
  `idCanton` INT NOT NULL AUTO_INCREMENT ,
  `txCanton` VARCHAR(100) NULL ,
  `cdCanton` VARCHAR(5) NOT NULL ,
  `idPays` INT NOT NULL ,
  PRIMARY KEY (`idCanton`) ,
  INDEX `idx_cd_canton` (`cdCanton` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`ville`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`ville` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`ville` (
  `idVille` INT NOT NULL AUTO_INCREMENT ,
  `txNpa` VARCHAR(10) NULL ,
  `txVille` VARCHAR(100) NULL ,
  `idCanton` INT NOT NULL ,
  `idDepartement` INT NULL ,
  `idPays` INT NOT NULL ,
  PRIMARY KEY (`idVille`) ,
  INDEX `FK_VILLE_PAYS` (`idPays` ASC) ,
  INDEX `FK_VILLE_CANTON` (`idCanton` ASC) ,
  CONSTRAINT `FK_VILLE_PAYS`
    FOREIGN KEY (`idPays` )
    REFERENCES `amaba`.`pays` (`idPays` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_VILLE_CANTON`
    FOREIGN KEY (`idCanton` )
    REFERENCES `amaba`.`canton` (`idCanton` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`adress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`adress` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`adress` (
  `idAdre` INT NOT NULL AUTO_INCREMENT ,
  `txAdreRue` VARCHAR(255) NOT NULL ,
  `txComplement` VARCHAR(255) NULL ,
  `idUsr` INT NOT NULL ,
  `idVille` INT NOT NULL ,
  PRIMARY KEY (`idAdre`) ,
  INDEX `fk_adress` (`idUsr` ASC) ,
  INDEX `fk_adress_ville` (`idVille` ASC) ,
  CONSTRAINT `fk_adress`
    FOREIGN KEY (`idUsr` )
    REFERENCES `amaba`.`usr` (`idUsr` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_adress_ville`
    FOREIGN KEY (`idVille` )
    REFERENCES `amaba`.`ville` (`idVille` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`traduction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`traduction` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`traduction` (
  `idTraduction` INT NOT NULL AUTO_INCREMENT ,
  `cdKey` VARCHAR(10) NOT NULL ,
  `cdLangue` VARCHAR(2) NOT NULL ,
  `txTraduction` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`idTraduction`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`departement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`departement` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`departement` (
  `idDepartement` INT NOT NULL AUTO_INCREMENT ,
  `cdDepartement` VARCHAR(10) NOT NULL ,
  `idPays` INT NOT NULL ,
  PRIMARY KEY (`idDepartement`) ,
  INDEX `idx_cd_dept` (`cdDepartement` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`usrProfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`usrProfil` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`usrProfil` (
  `idUsrProfil` INT NOT NULL AUTO_INCREMENT ,
  `idUsr` INT NOT NULL ,
  `cdHomoBi` VARCHAR(10) NULL COMMENT 'Hetero, hmo ou Bi' ,
  `loMarie` VARCHAR(1) NULL ,
  `loDivorce` VARCHAR(1) NULL ,
  `loVeuf` VARCHAR(1) NULL ,
  `loAvecEnfant` VARCHAR(1) NULL ,
  `nbEnfant` SMALLINT NULL ,
  `loEchangiste` VARCHAR(1) NULL ,
  `loPartouze` VARCHAR(1) NULL ,
  `loSerieux` VARCHAR(1) NULL COMMENT 'Recherche relation serieuse' ,
  `loUnSoir` VARCHAR(1) NULL COMMENT 'Recherche pour un soir' ,
  PRIMARY KEY (`idUsrProfil`) ,
  INDEX `FK_usr_usrProfil` (`idUsrProfil` ASC) ,
  CONSTRAINT `FK_usr_usrProfil`
    FOREIGN KEY (`idUsrProfil` )
    REFERENCES `amaba`.`usr` (`idUsr` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`sport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`sport` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`sport` (
  `idSport` INT NOT NULL AUTO_INCREMENT ,
  `cdSport` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`idSport`) ,
  UNIQUE INDEX `IDX_CDSPORT` (`cdSport` ASC, `idSport` ASC) ,
  UNIQUE INDEX `cdSport_UNIQUE` (`cdSport` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`usrSport`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`usrSport` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`usrSport` (
  `idUsrSport` INT NOT NULL AUTO_INCREMENT ,
  `idUsr` INT NOT NULL ,
  `idSport` INT NOT NULL ,
  PRIMARY KEY (`idUsrSport`) ,
  INDEX `FK_USR_SPORT` (`idSport` ASC) ,
  INDEX `FK_USRSP_USR` (`idUsr` ASC) ,
  CONSTRAINT `FK_USR_SPORT`
    FOREIGN KEY (`idSport` )
    REFERENCES `amaba`.`sport` (`idSport` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USRSP_USR`
    FOREIGN KEY (`idUsr` )
    REFERENCES `amaba`.`usr` (`idUsr` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`religion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`religion` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`religion` (
  `idReligion` INT NOT NULL AUTO_INCREMENT ,
  `cdReligion` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`idReligion`) ,
  UNIQUE INDEX `IDX_RELIGION` (`idReligion` ASC, `cdReligion` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`usrReligion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`usrReligion` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`usrReligion` (
  `idUsrReligion` INT NOT NULL AUTO_INCREMENT ,
  `idUsr` INT NOT NULL ,
  `idReligion` INT NOT NULL ,
  PRIMARY KEY (`idUsrReligion`) ,
  INDEX `FK_USR_SPORT` (`idReligion` ASC) ,
  INDEX `FK_USRSP_USR` (`idUsr` ASC) ,
  CONSTRAINT `FK_USR_RELIGION`
    FOREIGN KEY (`idReligion` )
    REFERENCES `amaba`.`religion` (`idReligion` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USRSP_RELIGION`
    FOREIGN KEY (`idUsr` )
    REFERENCES `amaba`.`usr` (`idUsr` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`usrAuth`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`usrAuth` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`usrAuth` (
  `idUsrAuth` INT NOT NULL AUTO_INCREMENT ,
  `txPassword` VARCHAR(20) NOT NULL ,
  `txKey` VARCHAR(50) NOT NULL COMMENT 'La clee dans l email envoye pour valider email' ,
  `isValid` VARCHAR(1) NOT NULL DEFAULT 0 ,
  `idUsr` INT NOT NULL ,
  PRIMARY KEY (`idUsrAuth`) ,
  INDEX `USRAUTH_USR` (`idUsr` ASC) ,
  CONSTRAINT `USRAUTH_USR`
    FOREIGN KEY (`idUsr` )
    REFERENCES `amaba`.`usr` (`idUsr` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`interet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`interet` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`interet` (
  `idInteret` INT NOT NULL AUTO_INCREMENT ,
  `cdInteret` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`idInteret`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`usrInteret`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`usrInteret` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`usrInteret` (
  `idUsrInteret` INT NOT NULL AUTO_INCREMENT ,
  `idUsr` INT NOT NULL ,
  `idInteret` INT NOT NULL ,
  PRIMARY KEY (`idUsrInteret`) ,
  INDEX `FK_USR_SPORT` (`idInteret` ASC) ,
  INDEX `FK_USRSP_USR` (`idUsr` ASC) ,
  CONSTRAINT `FK_USR_INTERET`
    FOREIGN KEY (`idInteret` )
    REFERENCES `amaba`.`interet` (`idInteret` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USRINT_INTERET`
    FOREIGN KEY (`idUsr` )
    REFERENCES `amaba`.`usr` (`idUsr` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`texteLibre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`texteLibre` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`texteLibre` (
  `idTexteLibre` INT NOT NULL AUTO_INCREMENT ,
  `cdTexteLibre` VARCHAR(10) NOT NULL COMMENT 'Ex description, recherche' ,
  `txTexteLibre` TEXT NOT NULL ,
  `idUsr` INT NOT NULL ,
  PRIMARY KEY (`idTexteLibre`) ,
  INDEX `IDX_CD_TXT_LIBRE` (`cdTexteLibre` ASC) ,
  INDEX `FK_USR_TXT_LIBRE` (`idUsr` ASC) ,
  CONSTRAINT `FK_USR_TXT_LIBRE`
    FOREIGN KEY (`idUsr` )
    REFERENCES `amaba`.`usr` (`idUsr` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`usrMessage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`usrMessage` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`usrMessage` (
  `idMessage` INT NOT NULL AUTO_INCREMENT ,
  `idUsrFrom` INT NOT NULL ,
  `idUsrTo` INT NOT NULL ,
  `txSujet` VARCHAR(100) NOT NULL ,
  `txMessage` VARCHAR(300) NOT NULL ,
  PRIMARY KEY (`idMessage`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`messageStatut`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`messageStatut` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`messageStatut` (
  `idMessageStatut` INT NOT NULL AUTO_INCREMENT ,
  `cdStatut` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`idMessageStatut`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`usrMessageStatut`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`usrMessageStatut` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`usrMessageStatut` (
  `idUsrMessageStatut` INT NOT NULL AUTO_INCREMENT ,
  `idMessage` INT NOT NULL ,
  `idUsr` INT NOT NULL ,
  `idMessageStatut` INT NOT NULL ,
  `dtStatut` DATETIME NOT NULL ,
  PRIMARY KEY (`idUsrMessageStatut`) ,
  INDEX `FK_MESSAGE` (`idMessage` ASC) ,
  INDEX `FK_MESS_STATUT` (`idMessageStatut` ASC) ,
  CONSTRAINT `FK_MESSAGE`
    FOREIGN KEY (`idMessage` )
    REFERENCES `amaba`.`usrMessage` (`idMessage` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_MESS_STATUT`
    FOREIGN KEY (`idMessageStatut` )
    REFERENCES `amaba`.`messageStatut` (`idMessageStatut` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`musique`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`musique` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`musique` (
  `idMusique` INT NOT NULL AUTO_INCREMENT ,
  `cdMusique` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`idMusique`) ,
  UNIQUE INDEX `IDX_CD_MUSI` (`cdMusique` ASC, `idMusique` ASC) ,
  UNIQUE INDEX `cdMusique_UNIQUE` (`cdMusique` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`usrMusique`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`usrMusique` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`usrMusique` (
  `idUsrMusique` INT NOT NULL AUTO_INCREMENT ,
  `idUsr` INT NOT NULL ,
  `idMusique` INT NOT NULL ,
  PRIMARY KEY (`idUsrMusique`) ,
  INDEX `FK_USR_MUSIQUE` (`idUsr` ASC) ,
  INDEX `FK_USRMUSI_MUSI` (`idMusique` ASC) ,
  CONSTRAINT `FK_USR_MUSIQUE`
    FOREIGN KEY (`idUsr` )
    REFERENCES `amaba`.`usr` (`idUsr` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USRMUSI_MUSI`
    FOREIGN KEY (`idMusique` )
    REFERENCES `amaba`.`musique` (`idMusique` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`profession`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`profession` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`profession` (
  `idProfession` INT NOT NULL AUTO_INCREMENT ,
  `cdProfession` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`idProfession`) ,
  UNIQUE INDEX `IDX_PROF` (`idProfession` ASC, `cdProfession` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`usrProfession`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`usrProfession` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`usrProfession` (
  `idUsrProfession` INT NOT NULL AUTO_INCREMENT ,
  `idUsr` INT NOT NULL ,
  `idProfession` INT NOT NULL ,
  PRIMARY KEY (`idUsrProfession`) ,
  INDEX `FK_USR_PROF` (`idUsr` ASC) ,
  INDEX `FK_USRPROF_PROF` (`idProfession` ASC) ,
  CONSTRAINT `FK_USR_PROF`
    FOREIGN KEY (`idUsr` )
    REFERENCES `amaba`.`usr` (`idUsr` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USRPROF_PROF`
    FOREIGN KEY (`idProfession` )
    REFERENCES `amaba`.`profession` (`idProfession` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`preference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`preference` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`preference` (
  `idPreference` INT NOT NULL AUTO_INCREMENT ,
  `cdPreference` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`idPreference`) ,
  UNIQUE INDEX `cdPreference_UNIQUE` (`cdPreference` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`usrPreference`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`usrPreference` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`usrPreference` (
  `idUsrPreference` INT NOT NULL ,
  `idUsr` INT NOT NULL ,
  `idPreference` INT NOT NULL ,
  PRIMARY KEY (`idUsrPreference`) ,
  INDEX `FK_USRPREF_PREF` (`idPreference` ASC) ,
  INDEX `FK_USRPREF_USR` (`idUsr` ASC) ,
  CONSTRAINT `FK_USRPREF_PREF`
    FOREIGN KEY (`idPreference` )
    REFERENCES `amaba`.`preference` (`idPreference` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USRPREF_USR`
    FOREIGN KEY (`idUsr` )
    REFERENCES `amaba`.`usr` (`idUsr` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amaba`.`usrPhysique`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amaba`.`usrPhysique` ;

CREATE  TABLE IF NOT EXISTS `amaba`.`usrPhysique` (
  `idUsrPhysique` INT NOT NULL AUTO_INCREMENT ,
  `nbPoids` INT NULL ,
  `nbTaille` INT NULL ,
  `cdCheveux` VARCHAR(10) NULL COMMENT 'BRUN,BLOND,ROUX' ,
  `idUsr` INT NOT NULL ,
  PRIMARY KEY (`idUsrPhysique`) ,
  INDEX `FK_USR_PHYS` (`idUsr` ASC) ,
  CONSTRAINT `FK_USR_PHYS`
    FOREIGN KEY (`idUsr` )
    REFERENCES `amaba`.`usr` (`idUsr` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `amaba`.`sexe`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`sexe` (`idSexe`, `cdSexe`) VALUES (1, 'M');
INSERT INTO `amaba`.`sexe` (`idSexe`, `cdSexe`) VALUES (2, 'F');

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`usr`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`usr` (`idUsr`, `txUsrNom`, `txUsrPrenom`, `txUsrEmail`, `dtUsrNaissance`, `idSexe`) VALUES (1, 'Gomes', 'Rodolphe', 'rodolphe.gomes@gmail.com', '1975-02-15', 1);
INSERT INTO `amaba`.`usr` (`idUsr`, `txUsrNom`, `txUsrPrenom`, `txUsrEmail`, `dtUsrNaissance`, `idSexe`) VALUES (2, 'Dupond', 'Paul', 'paul@gmail.com', '1988-01-01', 1);
INSERT INTO `amaba`.`usr` (`idUsr`, `txUsrNom`, `txUsrPrenom`, `txUsrEmail`, `dtUsrNaissance`, `idSexe`) VALUES (3, 'Maret', 'Elodie', 'hugo@gmail.com', '1981-01-20', 2);

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`pays`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`pays` (`idPays`, `cdPays`) VALUES (1, 'CH');
INSERT INTO `amaba`.`pays` (`idPays`, `cdPays`) VALUES (2, 'FR');
INSERT INTO `amaba`.`pays` (`idPays`, `cdPays`) VALUES (3, 'BE');
INSERT INTO `amaba`.`pays` (`idPays`, `cdPays`) VALUES (4, 'IT');
INSERT INTO `amaba`.`pays` (`idPays`, `cdPays`) VALUES (5, 'DE');
INSERT INTO `amaba`.`pays` (`idPays`, `cdPays`) VALUES (6, 'EN');

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`canton`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`canton` (`idCanton`, `txCanton`, `cdCanton`, `idPays`) VALUES (1, 'Valais', 'VS', 1);
INSERT INTO `amaba`.`canton` (`idCanton`, `txCanton`, `cdCanton`, `idPays`) VALUES (2, 'Vaud', 'VD', 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`ville`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`ville` (`idVille`, `txNpa`, `txVille`, `idCanton`, `idDepartement`, `idPays`) VALUES (1, '1926', 'Fully', 1, NULL, 1);
INSERT INTO `amaba`.`ville` (`idVille`, `txNpa`, `txVille`, `idCanton`, `idDepartement`, `idPays`) VALUES (2, '1020', 'Lausanne', 2, NULL, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`adress`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`adress` (`idAdre`, `txAdreRue`, `txComplement`, `idUsr`, `idVille`) VALUES (1, '29 rue promenade', NULL, 1, 1);
INSERT INTO `amaba`.`adress` (`idAdre`, `txAdreRue`, `txComplement`, `idUsr`, `idVille`) VALUES (2, 'rue caroline', NULL, 2, 2);
INSERT INTO `amaba`.`adress` (`idAdre`, `txAdreRue`, `txComplement`, `idUsr`, `idVille`) VALUES (3, '29 rue promenade', NULL, 3, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`traduction`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (1, 'BRUN', 'FR', 'Brun');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (2, 'BRUN', 'EN', 'EN_Brun');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (3, 'BRUN', 'DE', 'DE_Brun');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (4, 'BRUN', 'IT', 'IT_Brun');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (NULL, 'CINE', 'FR', 'Cinéma');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (NULL, 'CINE', 'EN', 'Cinéma');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (NULL, 'CINE', 'DE', 'Cinéma');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (NULL, 'CINE', 'IT', 'Cinéma');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (NULL, 'M', 'EN', 'Masculin');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (NULL, 'M', 'FR', 'Masculin');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (NULL, 'M', 'DE', 'Masculin');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (NULL, 'M', 'IT', 'Masculin');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (NULL, 'F', 'FR', 'Féminin');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (NULL, 'F', 'EN', 'Féminin');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (NULL, 'F', 'DE', 'Féminin');
INSERT INTO `amaba`.`traduction` (`idTraduction`, `cdKey`, `cdLangue`, `txTraduction`) VALUES (NULL, 'F', 'IT', 'Féminin');

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`usrProfil`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`usrProfil` (`idUsrProfil`, `idUsr`, `cdHomoBi`, `loMarie`, `loDivorce`, `loVeuf`, `loAvecEnfant`, `nbEnfant`, `loEchangiste`, `loPartouze`, `loSerieux`, `loUnSoir`) VALUES (1, 1, 'HETERO', '1', '1', '0', '1', 2, '0', '1', '1', NULL);
INSERT INTO `amaba`.`usrProfil` (`idUsrProfil`, `idUsr`, `cdHomoBi`, `loMarie`, `loDivorce`, `loVeuf`, `loAvecEnfant`, `nbEnfant`, `loEchangiste`, `loPartouze`, `loSerieux`, `loUnSoir`) VALUES (2, 2, 'HOMO', '0', '1', '0', '0', 0, '1', '0', '0', NULL);

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`sport`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`sport` (`idSport`, `cdSport`) VALUES (1, 'FOOT');
INSERT INTO `amaba`.`sport` (`idSport`, `cdSport`) VALUES (2, 'BASKET');
INSERT INTO `amaba`.`sport` (`idSport`, `cdSport`) VALUES (3, 'NATATION');
INSERT INTO `amaba`.`sport` (`idSport`, `cdSport`) VALUES (4, 'ESCALADE');
INSERT INTO `amaba`.`sport` (`idSport`, `cdSport`) VALUES (5, 'COURSE');
INSERT INTO `amaba`.`sport` (`idSport`, `cdSport`) VALUES (6, 'TENNIS');
INSERT INTO `amaba`.`sport` (`idSport`, `cdSport`) VALUES (7, 'EQUITATION');
INSERT INTO `amaba`.`sport` (`idSport`, `cdSport`) VALUES (8, 'ROLLER');
INSERT INTO `amaba`.`sport` (`idSport`, `cdSport`) VALUES (9, 'VTT');
INSERT INTO `amaba`.`sport` (`idSport`, `cdSport`) VALUES (10, 'ROLLER');
INSERT INTO `amaba`.`sport` (`idSport`, `cdSport`) VALUES (11, 'DANSE');

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`usrSport`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`usrSport` (`idUsrSport`, `idUsr`, `idSport`) VALUES (1, 1, 1);
INSERT INTO `amaba`.`usrSport` (`idUsrSport`, `idUsr`, `idSport`) VALUES (2, 1, 2);
INSERT INTO `amaba`.`usrSport` (`idUsrSport`, `idUsr`, `idSport`) VALUES (3, 1, 3);
INSERT INTO `amaba`.`usrSport` (`idUsrSport`, `idUsr`, `idSport`) VALUES (4, 2, 2);
INSERT INTO `amaba`.`usrSport` (`idUsrSport`, `idUsr`, `idSport`) VALUES (5, 2, 3);
INSERT INTO `amaba`.`usrSport` (`idUsrSport`, `idUsr`, `idSport`) VALUES (6, 2, 6);
INSERT INTO `amaba`.`usrSport` (`idUsrSport`, `idUsr`, `idSport`) VALUES (7, 3, 3);

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`religion`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`religion` (`idReligion`, `cdReligion`) VALUES (1, 'CATHO');
INSERT INTO `amaba`.`religion` (`idReligion`, `cdReligion`) VALUES (2, 'MUSULM');
INSERT INTO `amaba`.`religion` (`idReligion`, `cdReligion`) VALUES (3, 'BOUDIST');

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`usrReligion`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`usrReligion` (`idUsrReligion`, `idUsr`, `idReligion`) VALUES (1, 1, 1);
INSERT INTO `amaba`.`usrReligion` (`idUsrReligion`, `idUsr`, `idReligion`) VALUES (2, 2, 2);

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`usrAuth`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`usrAuth` (`idUsrAuth`, `txPassword`, `txKey`, `isValid`, `idUsr`) VALUES (1, 'test', 'cdshskjhfioeuoeu', '1', 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`interet`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (1, 'CINE');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (2, 'SPORT');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (3, 'VOYAGE');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (4, 'SPORT_MECA');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (5, 'SEXE');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (6, 'MODE');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (7, 'JEUXVIDEO');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (8, 'INFORMATI');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (9, 'THEATRE');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (10, 'PEINTURE');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (11, 'MONTAGNE');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (12, 'DANSE');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (13, 'INTERNET');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (14, 'CUISINE');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (15, 'LITERATURE');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (16, 'PLONGEE');
INSERT INTO `amaba`.`interet` (`idInteret`, `cdInteret`) VALUES (17, 'MUSIQUE');

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`usrInteret`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`usrInteret` (`idUsrInteret`, `idUsr`, `idInteret`) VALUES (1, 1, 1);
INSERT INTO `amaba`.`usrInteret` (`idUsrInteret`, `idUsr`, `idInteret`) VALUES (2, 1, 2);
INSERT INTO `amaba`.`usrInteret` (`idUsrInteret`, `idUsr`, `idInteret`) VALUES (3, 1, 3);
INSERT INTO `amaba`.`usrInteret` (`idUsrInteret`, `idUsr`, `idInteret`) VALUES (4, 2, 1);
INSERT INTO `amaba`.`usrInteret` (`idUsrInteret`, `idUsr`, `idInteret`) VALUES (5, 3, 1);
INSERT INTO `amaba`.`usrInteret` (`idUsrInteret`, `idUsr`, `idInteret`) VALUES (6, 3, 2);
INSERT INTO `amaba`.`usrInteret` (`idUsrInteret`, `idUsr`, `idInteret`) VALUES (7, 2, 3);

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`texteLibre`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`texteLibre` (`idTexteLibre`, `cdTexteLibre`, `txTexteLibre`, `idUsr`) VALUES (1, 'PRESENTA', 'Je suis Ingénieur Informatique, 26 ans', 1);
INSERT INTO `amaba`.`texteLibre` (`idTexteLibre`, `cdTexteLibre`, `txTexteLibre`, `idUsr`) VALUES (2, 'RECHERCHE', 'Je recherche de amis pour jouer au foot...', 1);
INSERT INTO `amaba`.`texteLibre` (`idTexteLibre`, `cdTexteLibre`, `txTexteLibre`, `idUsr`) VALUES (3, 'KO_RECH', 'Je ne recherche pas une relation sérieuse', 1);
INSERT INTO `amaba`.`texteLibre` (`idTexteLibre`, `cdTexteLibre`, `txTexteLibre`, `idUsr`) VALUES (4, 'PRESENTA', 'J\'ai 24 ans sans emploi', 2);

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`usrMessage`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`usrMessage` (`idMessage`, `idUsrFrom`, `idUsrTo`, `txSujet`, `txMessage`) VALUES (1, 1, 2, 'Salut', 'Hello comment vas-tu ??');
INSERT INTO `amaba`.`usrMessage` (`idMessage`, `idUsrFrom`, `idUsrTo`, `txSujet`, `txMessage`) VALUES (2, 2, 1, 'Hey !!!!', 'Nickel et toi quoi de neuf ???');
INSERT INTO `amaba`.`usrMessage` (`idMessage`, `idUsrFrom`, `idUsrTo`, `txSujet`, `txMessage`) VALUES (2, 2, 1, 'Bonne reception', 'J ai eu ton message ca roule :)');

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`messageStatut`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`messageStatut` (`idMessageStatut`, `cdStatut`) VALUES (1, 'NON_LU');
INSERT INTO `amaba`.`messageStatut` (`idMessageStatut`, `cdStatut`) VALUES (2, 'LU');
INSERT INTO `amaba`.`messageStatut` (`idMessageStatut`, `cdStatut`) VALUES (3, 'ENVOYE');
INSERT INTO `amaba`.`messageStatut` (`idMessageStatut`, `cdStatut`) VALUES (4, 'SUPPRIME');

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`usrMessageStatut`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`usrMessageStatut` (`idUsrMessageStatut`, `idMessage`, `idUsr`, `idMessageStatut`, `dtStatut`) VALUES (1, 1, 1, ENVOYE, '2011-08-12 17:00:05');
INSERT INTO `amaba`.`usrMessageStatut` (`idUsrMessageStatut`, `idMessage`, `idUsr`, `idMessageStatut`, `dtStatut`) VALUES (2, 1, 2, NON_LU, '2011-08-12 17:00:05');

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`musique`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`musique` (`idMusique`, `cdMusique`) VALUES (1, 'POP');
INSERT INTO `amaba`.`musique` (`idMusique`, `cdMusique`) VALUES (2, 'CLASSIC');
INSERT INTO `amaba`.`musique` (`idMusique`, `cdMusique`) VALUES (3, 'RAP');
INSERT INTO `amaba`.`musique` (`idMusique`, `cdMusique`) VALUES (4, 'RNB');
INSERT INTO `amaba`.`musique` (`idMusique`, `cdMusique`) VALUES (5, 'REGGAE');
INSERT INTO `amaba`.`musique` (`idMusique`, `cdMusique`) VALUES (6, 'TECHNO');
INSERT INTO `amaba`.`musique` (`idMusique`, `cdMusique`) VALUES (7, 'ROCK');
INSERT INTO `amaba`.`musique` (`idMusique`, `cdMusique`) VALUES (8, 'FUNCK');
INSERT INTO `amaba`.`musique` (`idMusique`, `cdMusique`) VALUES (10, 'MONDE');

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`usrMusique`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`usrMusique` (`idUsrMusique`, `idUsr`, `idMusique`) VALUES (1, 1, 1);
INSERT INTO `amaba`.`usrMusique` (`idUsrMusique`, `idUsr`, `idMusique`) VALUES (2, 1, 2);
INSERT INTO `amaba`.`usrMusique` (`idUsrMusique`, `idUsr`, `idMusique`) VALUES (3, 1, 3);
INSERT INTO `amaba`.`usrMusique` (`idUsrMusique`, `idUsr`, `idMusique`) VALUES (4, 2, 1);
INSERT INTO `amaba`.`usrMusique` (`idUsrMusique`, `idUsr`, `idMusique`) VALUES (4, 2, 5);
INSERT INTO `amaba`.`usrMusique` (`idUsrMusique`, `idUsr`, `idMusique`) VALUES (4, 2, 3);

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`profession`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`profession` (`idProfession`, `cdProfession`) VALUES (1, 'MEDECIN');
INSERT INTO `amaba`.`profession` (`idProfession`, `cdProfession`) VALUES (2, 'DENTISTE');
INSERT INTO `amaba`.`profession` (`idProfession`, `cdProfession`) VALUES (3, 'NOTAIRE');
INSERT INTO `amaba`.`profession` (`idProfession`, `cdProfession`) VALUES (4, 'AVOCAT');
INSERT INTO `amaba`.`profession` (`idProfession`, `cdProfession`) VALUES (5, 'FONCTIONNA');
INSERT INTO `amaba`.`profession` (`idProfession`, `cdProfession`) VALUES (6, 'INFORMAT');
INSERT INTO `amaba`.`profession` (`idProfession`, `cdProfession`) VALUES (7, 'INDEP');
INSERT INTO `amaba`.`profession` (`idProfession`, `cdProfession`) VALUES (8, 'INFIRMIERE');
INSERT INTO `amaba`.`profession` (`idProfession`, `cdProfession`) VALUES (9, 'VENDEUSE');

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`preference`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`preference` (`idPreference`, `cdPreference`) VALUES (1, 'CACHER_PHOTOS');
INSERT INTO `amaba`.`preference` (`idPreference`, `cdPreference`) VALUES (2, 'CACHER_EMAIL');
INSERT INTO `amaba`.`preference` (`idPreference`, `cdPreference`) VALUES (3, 'CACHER_PROFESSION');

COMMIT;

-- -----------------------------------------------------
-- Data for table `amaba`.`usrPhysique`
-- -----------------------------------------------------
START TRANSACTION;
USE `amaba`;
INSERT INTO `amaba`.`usrPhysique` (`idUsrPhysique`, `nbPoids`, `nbTaille`, `cdCheveux`, `idUsr`) VALUES (NULL, 71, 171, 'BRUN', 1);
INSERT INTO `amaba`.`usrPhysique` (`idUsrPhysique`, `nbPoids`, `nbTaille`, `cdCheveux`, `idUsr`) VALUES (NULL, 99, 185, 'BLOND', 2);

COMMIT;
