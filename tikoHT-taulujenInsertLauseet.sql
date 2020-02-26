INSERT INTO asiakas VALUES (DEFAULT, 'Essi Esimerkki', 'Tiekatu 1');

INSERT INTO asiakas VALUES (DEFAULT, 'Ville Vakio', 'Kotitie 10');
INSERT INTO asiakas VALUES (DEFAULT, 'Antti Asiakas', 'PinniC nurkkapöytä');
INSERT INTO asiakas VALUES (DEFAULT, 'Otto Oletus', 'Puistokatu 14 A 36');

INSERT INTO tyokohde values (DEFAULT, 1, 'Omakotitalo', 'Tiekatu 1');
INSERT INTO tyokohde values (DEFAULT, 1, 'Kesämökki', 'Pikkuvehnänpeltokatu 108');
INSERT INTO tyokohde values (DEFAULT, 3, 'Kylmävarasto', 'Katukatu 6');
INSERT INTO tyokohde values (DEFAULT, 3, 'Omakotitalo', 'Varastotie 9 A17');

INSERT INTO tyosopimus VALUES (DEFAULT, 1, 'urakka', 1, '2019-10-31', 'Terassin sähköpistokkeet');
INSERT INTO tyosopimus VALUES (DEFAULT, 1, 'tunti', 2, '2019-10-15', 'Kellarin lämmitys');
INSERT INTO tyosopimus VALUES (DEFAULT, 3, 'urakka', 1, '2020-01-20', 'Ulkosaunan sähköistys');
INSERT INTO tyosopimus VALUES (DEFAULT, 4, 'urakka', 4, '2019-10-31', 'Autotallin sähköjen uudelleenveto');
INSERT INTO tyosopimus VALUES (DEFAULT, 2, 'urakka', null, null, 'Tarjous Villelle mökin kaapeloinnista');

INSERT INTO tyotarjous VALUES (5, '2020-02-25', false);

INSERT INTO lasku VALUES (DEFAULT, 1, '2019-11-21', '2020-01-31', null, null);
INSERT INTO lasku VALUES (DEFAULT, 2, '2019-12-31', '2020-01-31', '2020-01-21', null);
INSERT INTO lasku VALUES (DEFAULT, 2, '2019-01-15', '2020-03-31', null, null);
INSERT INTO lasku VALUES (DEFAULT, 4, '2019-11-15', '2020-12-31', null, null);
INSERT INTO lasku VALUES (DEFAULT, 4, '2019-01-15', '2020-02-28', null, 4);

INSERT INTO tuntityohinnasto VALUES ('suunnittelu', 55, 24);
INSERT INTO tuntityohinnasto VALUES ('asennustyo', 45, 24);
INSERT INTO tuntityohinnasto VALUES ('aputyo', 35, 24);
INSERT INTO tuntityohinnasto VALUES ('matkakorvaus', 30, 24);

INSERT INTO tyosuoritus VALUES (DEFAULT, 1, 'suunnittelu', '2019-12-12', 3, 0);
INSERT INTO tyosuoritus VALUES (DEFAULT, 1, 'asennustyo', '2019-12-13', 9, 0);
INSERT INTO tyosuoritus VALUES (DEFAULT, 3, 'asennustyo', '2019-12-22', 3, 0);
INSERT INTO tyosuoritus VALUES (DEFAULT, 3, 'asennustyo', '2019-12-23', 8, 0);
INSERT INTO tyosuoritus VALUES (DEFAULT, 3, 'asennustyo', '2019-12-27', 5, 0);
INSERT INTO tyosuoritus VALUES (DEFAULT, 4, 'suunnittelu', '2020-01-15', 2, 10);
INSERT INTO tyosuoritus VALUES (DEFAULT, 4, 'matkakorvaus', '2020-01-15', 1, 0);
INSERT INTO tyosuoritus VALUES (DEFAULT, 4, 'asennustyo', '2020-01-15', 6, 10);

-- Jätetään alkuun myyntihinnat tyhjiksi ja päivitetään myöhemmin
INSERT INTO tarvike VALUES (DEFAULT, 'Jatkopistorasia 2-osainen rinnakkain IP44', 4.99, null, 'kpl', 52, 24);
INSERT INTO tarvike VALUES (DEFAULT, 'Pistotulppa IP20', 1.99, null, 'kpl', 19, 24);
INSERT INTO tarvike VALUES (DEFAULT, 'Mekaaninen termostaatti', 31.2, null, 'kpl', 9, 24);
INSERT INTO tarvike VALUES (DEFAULT, 'Tele-Fonika MMJ 3 x 1,5 mm² kaapeli kelalta', 0.99, null, 'm', 1024, 24);
INSERT INTO tarvike VALUES (DEFAULT, 'Automaattisulake 10A 380V', 7.5, null, 'kpl', 121, 24);
INSERT INTO tarvike VALUES (DEFAULT, 'Sähköasennukset hölmöille -kirja 2014 WSOY', 35.5, null, 'kpl', 4, 10);
INSERT INTO tarvike VALUES (DEFAULT, 'Automaattisulake 12A 240V', 5.5, null, 'kpl', 12, 24);

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