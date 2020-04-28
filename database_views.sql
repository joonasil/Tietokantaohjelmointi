-- Views

-- Kaikki sopimukset asiakkaineen ja kohteineen
Select ts.sopimusid, a.nimi, t.kohdetyyppi, t.osoite, ts.sopimuksentila, ts.selite
FROM asiakas a, tyokohde t, tyosopimus ts
WHERE a.asiakasid = t.omistajaid
	AND t.kohdeid = ts.kohdeid
	;

-- Kaikki sopimukseen liittyvä
Select
	s.sopimusid, 
	a.nimi, 
	tk.kohdetyyppi, 
	tk.osoite, 
	s.sopimuksentila, 
	s.selite,
	s.pvm,
	s.tyyppi,
	s.tarvikkeidenhinta + s.tyonhinta AS sopimuksensumma,
	(s.tyonHinta / 1.24) AS kotitalousvähennyskelpoinenosa
	FROM asiakas a right outer join tyokohde tk on a.asiakasid = tk.omistajaid
		right outer join tyosopimus s on s.kohdeid = tk.kohdeid
		join tyosuoritus ts on ts.sopimusid = s.sopimusid
	where s.sopimusid = 1
	;
	
-- Testi
SELECT DISTINCT *
FROM asiakas a JOIN tyokohde tk JOIN tyosopimus ts JOIN lasku l JOIN tyosuoritus tsuor JOIN tuntityo tt JOIN tyosuorituksentuntityo tstt JOIN tarvike t JOIN tarvikeluettelo tl
WHERE a.asiakasid = tk.omistajaid
	AND tk.kohdeid = ts.kohdeid
	AND tsuor.sopimusid = ts.sopimusid
	AND tsuor.suoritusid = tl.suoritusid
	AND tl.tarvikeid = t.tarvikeid
	AND tsuor.suoritusid = tstt.suoritusid
	AND tt.tyontyyppi = tstt.tyontyyppi
	AND ts.sopimusid = 1
	;
	
	
-- TEsti2
SELECT s sopimusid, a.nimi, a.osoite, 
FROM asiakas a JOIN tyokohde tk JOIN tyosopimus s JOIN lasku l JOIN tyosuoritus ts JOIN tuntityo tt JOIN tyosuorituksentuntityo tstt JOIN tarvike t JOIN tarvikeluettelo tl
WHERE a.asiakasid = tk.omistajaid
	AND tk.kohdeid = ts.kohdeid
	AND tsuor.sopimusid = ts.sopimusid
	AND tsuor.suoritusid = tl.suoritusid
	AND tl.tarvikeid = t.tarvikeid
	AND tsuor.suoritusid = tstt.suoritusid
	AND tt.tyontyyppi = tstt.tyontyyppi
	AND ts.sopimusid = 1
	;
	
	