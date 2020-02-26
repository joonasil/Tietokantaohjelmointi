-- Harjoitustyön tapahtumat/ raportit


-- T1 Lisätään asiakkaalle xx uusi työkohde
INSERT INTO tyokohde (kohdeID, omistajaID, kohdetyyppi, osoite)
VALUES ($asiakasid, $omistajaID, $kohdetyyppi, $osoite);

-- esimerkki
INSERT INTO tyokohde VALUES (DEFAULT, 3, 'Omakotitalo', 'Katutiekuja x');

-- T2: Tallennetaan työkohteeseen liittyvät tuntityöt ja käytetyistä tarvikkeista tiedot päivän päätteeksi.
INSERT INTO tyosuoritus (suoritusid, sopimusid, tyolaji, pvm, tuntilkm, aleprosentti)
VALUES ($suoritusid, $sopimusid, $tyolaji, $pvm, $tuntilkm, $aleprosentti);

-- esimerkki
INSERT INTO tyosuoritus (DEFAULT, 5, 'asennustyo', '2020-02-25', 4, 0);

-- T3: Muodosta muistutuslasku laskuista, joita ei ole maksettu ja joiden eräpäivä umpeutunut, 
-- ja joista ei ole aiemmin lähetetty muistutuslaskua.


-- T4: Muodosta karhulasku (kolmas) muistutuslaskuista, joita ei ole maksettu ja joiden eräpäivä umpeutunut
-- T5: Tavarantoimittaja lähettää uuden hinnaston (tekstimuodossa). Pitää korvata olemassa olevat sekä pitää poistaa vanhat ja lisätä uudet. Vanhat tuotteet ja tarvikkeet on toimitettava historiakansioon
-- Raportit


-- R1: Muodosta hinta-arvio kohteeseen x, joka sisältää suunnittelua 3 tuntia, asennustyötä 12 tuntia, 3 metriä sähköjohtoa sekä yhden pistorasian.
-- R2: Tuntityölasku tarvittavine tietoineen
-- - asiakastiedot (kohteen osoite voi olla eri kuin asiakkaan osoite)
-- - tarvikkeet (vähintään 2 erityyppistä)
-- - tuntierittely (vähintään 2 erityyppistä)
-- - kokonaissumma
-- - kotitalousvähennyskelpoisuus
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