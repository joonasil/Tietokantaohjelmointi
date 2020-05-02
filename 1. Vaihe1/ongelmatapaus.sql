select * from lasku;
-- tulostaulu
 laskuid | sopimusid |    pvm     |  erapaiva  | maksettupvm | edeltavalasku
---------+-----------+------------+------------+-------------+---------------
       1 |         1 | 2019-11-21 | 2020-01-31 |             |
       2 |         2 | 2019-12-31 | 2020-01-31 | 2020-01-21  |
       3 |         2 | 2019-01-15 | 2020-03-31 |             |
       4 |         4 | 2019-11-15 | 2020-12-31 |             |
       5 |         4 | 2019-01-15 | 2020-02-28 |             |             4
       6 |         4 | 2019-02-15 | 2020-03-15 |             |             5
(6 rows)



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
-- tulostaulu
 laskuid | edeltavalasku | monesko
---------+---------------+---------
       4 |               |       1
       5 |             4 |       1
       5 |               |       2
       6 |             4 |       2
       6 |               |       3
(5 rows)


-- HALUTTU TULOSTAULU 
 laskuid | edeltavalasku | monesko
---------+---------------+---------
       4 |               |       1
       5 |             4 |       2
       6 |             5 |       3
(3 rows)