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
INSERT INTO tyosopimus VALUES (DEFAULT, 2, 'urakka' null, null, 'Tarjous Villelle mökin kaapeloinnista');

INSERT INTO tyotarjous VALUES (5, '2020-02-25', false);

INSERT INTO lasku VALUES (DEFAULT, 1, '2019-11-21', '2020-01-31', null, null);
INSERT INTO lasku VALUES (DEFAULT, 2, '2019-12-31', '2020-01-31', '2020-01-21', null);
INSERT INTO lasku VALUES (DEFAULT, 2, '2019-01-15', '2020-03-31', null, null);
INSERT INTO lasku VALUES (DEFAULT, 4, '2019-11-15', '2020-12-31', null, null);
INSERT INTO lasku VALUES (DEFAULT, 4, '2019-15-01', '2020-02-31', null, 4);

INSERT INTO tuntityohinnasto VALUES ('suunnittelu', 55, 24);
INSERT INTO tuntityohinnasto VALUES ('tyo', 45, 24);
INSERT INTO tuntityohinnasto VALUES ('aputyo', 35, 24);
INSERT INTO tuntityohinnasto VALUES ('matkakorvaus', 30, 24);