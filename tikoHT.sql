CREATE SCHEMA tikoht;
SET SCHEMA 'tikoht';

-- Uudet tietotyypit sopimuslajille ja yksiköille
CREATE TYPE sopimuslaji AS ENUM ('urakka', 'tunti');
CREATE TYPE yksikot AS ENUM ('kpl', 'kg', 'm', 'cm', 'g', 'l', 'kela');

CREATE TABLE asiakas (
	asiakasID SERIAL PRIMARY KEY,
	nimi VARCHAR (50) NOT NULL,
	osoite VARCHAR (100) NOT NULL
);

CREATE TABLE tyokohde (
	kohdeID SERIAL PRIMARY KEY,
	omistajaID INT,
	kohdetyyppi VARCHAR (50) NOT NULL,
	osoite VARCHAR (100) NOT NULL,
	FOREIGN KEY (omistajaID) REFERENCES asiakas(asiakasID)
);

CREATE TABLE tyosopimus (
	sopimusID SERIAL PRIMARY KEY,
	kohdeID INT,
	tyyppi SOPIMUSLAJI NOT NULL,
	tyonHinta NUMERIC,
	tarvikkeidenHinta NUMERIC,
	-- laskujen määrä -> arvo 1 = ei osamaksua, arvo 4 = maksetaan 4 osassa
	osamaksu INT NOT NULL,
	pvm DATE,
	selite VARCHAR (50),
	CONSTRAINT jos_urakkatyo_hinnat_NOTNULL
		-- tuntisopimuksessa tarvikeHinta ja tyonHinta tyhjät
		CHECK ( (tyyppi = 'tunti' AND tyonHinta IS NULL 
			AND tarvikkeidenHinta IS NULL )
			-- urakkasopimuksessa tarvikkeiden ja työn hinnat merkattu
			OR (tyyppi = 'urakka' AND tyonHinta IS NOT NULL 
			AND tarvikkeidenHinta IS NOT NULL )),
	FOREIGN KEY (kohdeID) REFERENCES tyokohde(kohdeID)
);

CREATE TABLE tyotarjous (
	sopimusID INT,
	pvm DATE NOT NULL,
	hyvaksytty BOOLEAN,
	FOREIGN KEY (sopimusID) REFERENCES tyosopimus(sopimusID)
);

-- Laskussa tarkistus, ettei laskun eräpäivä ole myöhemmin kuin laskun pvm
CREATE TABLE lasku (
	laskuID SERIAL PRIMARY KEY,
	sopimusID INT,
	pvm DATE NOT NULL,
	erapaiva DATE NOT NULL,
	maksettuPvm DATE,
	edeltavaLasku INT,
	CONSTRAINT check_dates CHECK (erapaiva > pvm),
	FOREIGN KEY (edeltavaLasku) REFERENCES lasku(laskuID),
	FOREIGN KEY (sopimusID) REFERENCES tyosopimus(sopimusID)
);

CREATE TABLE tuntityohinnasto (
	tyyppi VARCHAR (50) NOT NULL UNIQUE,
	hinta NUMERIC NOT NULL,
	alv INT NOT NULL,
	PRIMARY KEY(tyyppi, hinta, alv)
);

CREATE TABLE tyosuoritus (
	suoritusID SERIAL PRIMARY KEY,
	sopimusID INT,
	tyolaji VARCHAR (50),
	pvm DATE NOT NULL,
	tuntiLkm INT,
	aleProsentti INT,
	FOREIGN KEY (sopimusID) REFERENCES tyosopimus(sopimusID)
);

CREATE TABLE tarvike (
	tarvikeID SERIAL PRIMARY KEY,
	nimi VARCHAR (50),
	sisaanostohinta NUMERIC,
	myyntihinta NUMERIC,
	yksikko yksikot,
	varastoLkm INT,
	alv INT
);

CREATE TABLE tarvikeluettelo (
	suoritusID INT,
	tarvikeID INT,
	lkm INT,
	aleProsentti INT,
	FOREIGN KEY (suoritusID) REFERENCES tyosuoritus(suoritusID),
	FOREIGN KEY (tarvikeID) REFERENCES tarvike(tarvikeID)
);