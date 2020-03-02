-- Harjoitustyön tapahtumat/ raportit


-- T1 Lisätään asiakkaalle xx uusi työkohde
INSERT INTO tyokohde (kohdeID, omistajaID, kohdetyyppi, osoite)
VALUES ($asiakasid, $omistajaID, $kohdetyyppi, $osoite);

Lue asiakasID realatiosta asiakas
Lue lastValue relaatiosta tyokohde_kohdeid_seq
uusiKohde := 

-- esimerkki
INSERT INTO tyokohde VALUES (DEFAULT, 3, 'Omakotitalo', 'Katutiekuja x');

-- T2: Tallennetaan työkohteeseen liittyvät tuntityöt ja käytetyistä tarvikkeista tiedot päivän päätteeksi.
INSERT INTO tyosuoritus (suoritusid, sopimusid, tyolaji, pvm, tuntilkm, aleprosentti)
VALUES ($suoritusid, $sopimusid, $tyolaji, $pvm, $tuntilkm, $aleprosentti);

-- esimerkki
INSERT INTO tyosuoritus (DEFAULT, 5, 'asennustyo', '2020-02-25', 4, 0);

-- T3: Muodosta muistutuslasku laskuista, joita ei ole maksettu ja joiden eräpäivä umpeutunut, 
-- ja joista ei ole aiemmin lähetetty muistutuslaskua.

-- haetaan kaikki laskut, jotka erääntyneet ja joita ei maksettu
SELECT * 
FROM lasku
WHERE erapaiva < '2020-03-01' AND maksettupvm IS NULL;

-- Haetaan rekursiivisesti kaikki laskua edeltävät laskut
WITH RECURSIVE el(laskuid, edeltavaLasku, monesko) AS (
		SELECT l.laskuid, l.edeltavaLasku, 1
		FROM lasku l
		WHERE laskuid IN (select edeltavaLasku from lasku)
	UNION
		SELECT l.laskuid, el.edeltavaLasku, el.monesko + 1
		FROM lasku l, el
		WHERE l.edeltavaLasku = el.laskuid AND l.edeltavaLasku IS NOT NULL
)
SELECT * 
FROM el
ORDER BY el.laskuid, el.monesko;



-- T4: Muodosta karhulasku (kolmas) muistutuslaskuista, joita ei ole maksettu ja joiden eräpäivä umpeutunut
-- T5: Tavarantoimittaja lähettää uuden hinnaston (tekstimuodossa). Pitää korvata olemassa olevat sekä pitää poistaa vanhat ja lisätä uudet. Vanhat tuotteet ja tarvikkeet on toimitettava historiakansioon
-- Raportit


-- R1: Muodosta hinta-arvio kohteeseen x, joka sisältää suunnittelua 3 tuntia, asennustyötä 12 tuntia, 3 metriä sähköjohtoa sekä yhden pistorasian.

SELECT sum(summa) yhteensa FROM
-- suunnittelutyö * 3
(
SELECT tyyppi as tuote, 3 as kpl, hinta as kplHinta, (hinta * 3) as summa, null
FROM tuntityohinnasto
WHERE tyyppi = 'suunnittelu'
UNION
-- asennustyö * 12
SELECT tyyppi as tuote, 12 as kpl, hinta as kplHinta, (hinta * 12) as summa, null
FROM tuntityohinnasto
WHERE tyyppi = 'asennustyo'
UNION
-- sähköjohto * 3m
SELECT nimi as tuote, 3 as kpl, myyntihinta as kplHinta, (myyntihinta * 3) as summa, null
FROM tarvike
WHERE tarvikeId = 4
UNION
-- Pistorasia
SELECT nimi as tuote, 1 as kpl, myyntihinta as kplHinta, (myyntihinta * 1) as summa, null
FROM tarvike
WHERE tarvikeId = 1
) AS tarjous
;

-- R2: Tuntityölasku tarvittavine tietoineen
-- - asiakastiedot (kohteen osoite voi olla eri kuin asiakkaan osoite)
-- - tarvikkeet (vähintään 2 erityyppistä)
-- - tuntierittely (vähintään 2 erityyppistä)
-- - kokonaissumma
-- - kotitalousvähennyskelpoisuus

-- Asiakkaan tiedot
SELECT a.nimi, a.osoite, ts.


-- R3: Kuten R2, mutta lisäksi
-- - suunnittelutyölle on annettu 10% alennus
-- - sähköjohdolle on annettu 10% alennus
-- - muille tarvikkeille annettu 20% alennus
-- - Opaskirjan (10 euroa – huom. eri alv)
-- - Alennukset kohdistuvat alv-verottomaan hintaan

-- R4: Urakkatarjous, joka sisältää
-- - asiakkaan ja työkohteen tiedot
-- - Arvioidun työn osuuden (peruste 5 tuntia suunnittelua, 20 tuntia asennustyötä; annetaan 10% alennus)
-- - Tarvikkeiden osuudet (3 eri tyyppiä, ainakin 2 kutakin)
-- - Alv-erittely

-- R5: Muodosta hyväksytystä urakkatarjouksesta kaksi samansuuruista laskua siten, että toinen laskutetaan heti ja toinen ensi vuoden tammikuun 1 päivä.