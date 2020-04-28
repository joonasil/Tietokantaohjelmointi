CREATE SCHEMA tikoht;
SET SCHEMA 'tikoht';

-- Uudet tietotyypit sopimuslajille ja yksiköille
CREATE TYPE sopimuslaji AS ENUM ('urakka', 'tunti');
CREATE TYPE yksikot AS ENUM ('kpl', 'kg', 'm', 'cm', 'g', 'l', 'kela');
CREATE TYPE sopimustila AS ENUM ('luonnos', 'tarjous', 'hyvaksytty');

CREATE TABLE asiakas (
	asiakasid SERIAL PRIMARY KEY,
	nimi VARCHAR (50) NOT NULL,
	osoite VARCHAR (100) NOT NULL
);

CREATE TABLE tyokohde (
	kohdeid SERIAL PRIMARY KEY,
	omistajaid INT,
	kohdetyyppi VARCHAR (50) NOT NULL,
	osoite VARCHAR (100) NOT NULL,
	FOREIGN KEY (omistajaid) REFERENCES asiakas(asiakasid)
);

CREATE TABLE tyosopimus (
	sopimusid SERIAL PRIMARY KEY,
	kohdeid INT,
	tyyppi SOPIMUSLAJI NOT NULL,
	tyonhinta NUMERIC,
	tarvikkeidenhinta NUMERIC,
	-- laskujen määrä -> arvo 1 = ei osamaksua, arvo 4 = maksetaan 4 osassa
	osamaksu INT,
	pvm DATE,
	sopimuksentila SOPIMUSTILA NOT NULL, -- ('luonnos', 'tarjous', 'hyvaksytty')
	selite VARCHAR (50),
	CONSTRAINT jos_urakkatyo_hinnat_NOTNULL
		-- tuntisopimuksessa tarvikeHinta ja tyonHinta tyhjät
		CHECK ( (tyyppi = 'tunti' AND tyonHinta IS NULL
			AND tarvikkeidenhinta IS NULL )
			-- urakkasopimuksessa tarvikkeiden ja työn hinnat merkattu
			OR (tyyppi = 'urakka' AND tyonhinta IS NOT NULL
			AND tarvikkeidenhinta IS NOT NULL )),
	FOREIGN KEY (kohdeid) REFERENCES tyokohde(kohdeid)
);

-- Laskussa tarkistus, ettei laskun eräpäivä ole myöhemmin kuin laskun pvm
CREATE TABLE lasku (
	laskuid SERIAL PRIMARY KEY,
	sopimusid INT,
	pvm DATE NOT NULL,
	erapaiva DATE NOT NULL,
	maksettupvm DATE,
	edeltavalasku INT,
	muistutuslkm INT, -- Johdetaan laskemalla rekursiivisesti edeltävien laskujen määrä
	viivastyskulut NUMERIC,
	CONSTRAINT check_dates CHECK (erapaiva > pvm),
	FOREIGN KEY (edeltavaLasku) REFERENCES lasku(laskuid),
	FOREIGN KEY (sopimusid) REFERENCES tyosopimus(sopimusid)
);

CREATE TABLE tuntityo (
	tyontyyppi VARCHAR (50) PRIMARY KEY,
	hinta NUMERIC NOT NULL,
	alv INT NOT NULL
);

CREATE TABLE tyosuoritus (
	suoritusid SERIAL PRIMARY KEY,
	sopimusid INT,
	suorituspvm DATE NOT NULL,
	FOREIGN KEY (sopimusid) REFERENCES tyosopimus(sopimusid)
);

CREATE TABLE tyosuorituksenTuntityo (
	suoritusid INT,
	tyontyyppi VARCHAR (50),
	tuntilkm NUMERIC NOT NULL,
	aleprosentti NUMERIC,
	FOREIGN KEY (suoritusid) REFERENCES tyosuoritus(suoritusid),
	FOREIGN KEY (tyontyyppi) REFERENCES tuntityo(tyontyyppi)
);

CREATE TABLE tarvike (
	tarvikeid SERIAL PRIMARY KEY,
	nimi VARCHAR (50),
	sisaanostohinta NUMERIC,
	myyntihinta NUMERIC,
	yksikko yksikot,
	varastoLkm INT,
	alv INT
);

CREATE TABLE tarvikeluettelo (
	suoritusid INT,
	tarvikeid INT,
	lkm INT,
	aleprosentti INT,
	FOREIGN KEY (suoritusid) REFERENCES tyosuoritus(suoritusid),
	FOREIGN KEY (tarvikeid) REFERENCES tarvike(tarvikeid)
);

