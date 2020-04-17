INSERT INTO asiakas VALUES (1, 'Essi Esimerkki', 'Tiekatu 1');
INSERT INTO asiakas VALUES (2, 'Ville Vakio', 'Kotitie 10');
INSERT INTO asiakas VALUES (3, 'Antti Asiakas', 'PinniC nurkkapöytä');
INSERT INTO asiakas VALUES (4, 'Otto Oletus', 'Puistokatu 14 A 36');

INSERT INTO tyokohde values (1, 1, 'Omakotitalo', 'Tiekatu 1');
INSERT INTO tyokohde values (2, 1, 'Kesämökki', 'Pikkuvehnänpeltokatu 108');
INSERT INTO tyokohde values (3, 3, 'Kylmävarasto', 'Katukatu 6');
INSERT INTO tyokohde values (4, 3, 'Omakotitalo', 'Varastotie 9 A17');

INSERT INTO tyosopimus VALUES (1, 1, 'tunti', null, null, 1, '2019-10-31', 'hyvaksytty', 'Terassin sähköpistokkeet');
INSERT INTO tyosopimus VALUES (2, 1, 'urakka', 700.00, 249.95, 2, '2019-10-15', 'hyvaksytty', 'Kellarin lämmitys');
INSERT INTO tyosopimus VALUES (3, 3, 'tunti', null, null, 1, '2020-01-20', 'hyvaksytty', 'Ulkosaunan sähköistys');
INSERT INTO tyosopimus VALUES (4, 4, 'tunti', null, null, 4, '2019-10-31', 'hyvaksytty', 'Autotallin sähköjen uudelleenveto');
INSERT INTO tyosopimus VALUES (5, 2, 'tunti', null, null, null, '2020-02-25', 'tarjous', 'Tarjous Villelle mökin kaapeloinnista');
INSERT INTO tyosopimus VALUES (6, 3, 'urakka', 1200.00, 400.00, null, null, 'tarjous', 'Urakkatarjous  kylmävaraston jäähdytyslaitteista');

INSERT INTO lasku VALUES (1, 1, '2019-11-21', '2020-01-31', null, null, 0, 0);
INSERT INTO lasku VALUES (2, 2, '2019-12-31', '2020-01-31', '2020-01-21', null, 0, 0);
INSERT INTO lasku VALUES (3, 2, '2019-01-15', '2020-03-31', null, null, 0, 0);
INSERT INTO lasku VALUES (4, 4, '2019-11-15', '2020-12-31', null, null, 0, 0);
INSERT INTO lasku VALUES (5, 4, '2019-01-15', '2020-02-28', null, 4, 1, 5);
INSERT INTO lasku VALUES (6, 4, '2019-02-15', '2020-03-15', null, 5, 2, 10);

INSERT INTO tuntityo VALUES ('suunnittelu', 55, 24);
INSERT INTO tuntityo VALUES ('asennustyo', 45, 24);
INSERT INTO tuntityo VALUES ('aputyo', 35, 24);
INSERT INTO tuntityo VALUES ('matkakorvaus', 30, 24);

INSERT INTO tyosuoritus VALUES (1, 1, '2019-12-12');
INSERT INTO tyosuoritus VALUES (2, 1, '2019-12-13');
INSERT INTO tyosuoritus VALUES (3, 3, '2019-12-22');
INSERT INTO tyosuoritus VALUES (4, 3, '2019-12-23');
INSERT INTO tyosuoritus VALUES (5, 3, '2019-12-27');
INSERT INTO tyosuoritus VALUES (6, 4, '2020-01-15');
INSERT INTO tyosuoritus VALUES (7, 4, '2020-01-15');
INSERT INTO tyosuoritus VALUES (8, 4, '2020-01-15');

INSERT INTO tyosuorituksenTuntityo VALUES (1, 'suunnittelu', 3, 0);
INSERT INTO tyosuorituksenTuntityo VALUES (2, 'asennustyo', 9, 0);
INSERT INTO tyosuorituksenTuntityo VALUES (3, 'asennustyo', 3, 0);
INSERT INTO tyosuorituksenTuntityo VALUES (4, 'asennustyo', 8, 0);
INSERT INTO tyosuorituksenTuntityo VALUES (5, 'asennustyo', 5, 0);
INSERT INTO tyosuorituksenTuntityo VALUES (6, 'suunnittelu', 2, 10);
INSERT INTO tyosuorituksenTuntityo VALUES (7, 'matkakorvaus', 1, 0);
INSERT INTO tyosuorituksenTuntityo VALUES (8, 'asennustyo', 6, 10);

-- Jätetään alkuun myyntihinnat tyhjiksi ja päivitetään myöhemmin
INSERT INTO tarvike VALUES (1, 'Jatkopistorasia 2-osainen rinnakkain IP44', 4.99, null, 'kpl', 52, 24);
INSERT INTO tarvike VALUES (2, 'Pistotulppa IP20', 1.99, null, 'kpl', 19, 24);
INSERT INTO tarvike VALUES (3, 'Mekaaninen termostaatti', 31.2, null, 'kpl', 9, 24);
INSERT INTO tarvike VALUES (4, 'Tele-Fonika MMJ 3 x 1,5 mm² kaapeli kelalta', 0.99, null, 'm', 1024, 24);
INSERT INTO tarvike VALUES (5, 'Automaattisulake 10A 380V', 7.5, null, 'kpl', 121, 24);
INSERT INTO tarvike VALUES (6, 'Sähköasennukset hölmöille -kirja 2014 WSOY', 35.5, null, 'kpl', 4, 10);
INSERT INTO tarvike VALUES (7, 'Automaattisulake 12A 240V', 5.5, null, 'kpl', 12, 24);

-- Asetetaan kaikille tuotteille myyntihinnaksi sisäänostohinta * 1.5
UPDATE tarvike SET myyntihinta = 1.5 * sisaanostohinta;

INSERT INTO tarvikeluettelo VALUES (2, 1, 4, 7);
INSERT INTO tarvikeluettelo VALUES (2, 2, 1, 7);
INSERT INTO tarvikeluettelo VALUES (2, 4, 16, 7);
INSERT INTO tarvikeluettelo VALUES (3, 1, 1, 0);
INSERT INTO tarvikeluettelo VALUES (4, 3, 8, 0);
INSERT INTO tarvikeluettelo VALUES (5, 6, 12, 0);
INSERT INTO tarvikeluettelo VALUES (5, 2, 10, 5);
INSERT INTO tarvikeluettelo VALUES (5, 7, 10, 5);
INSERT INTO tarvikeluettelo VALUES (8, 6, 1, 15);
INSERT INTO tarvikeluettelo VALUES (8, 2, 6, 0);
INSERT INTO tarvikeluettelo VALUES (8, 5, 8, 0);
