An example of re-using a Slick driver with another database.

This uses MonetDB, and assumes you have created and started a `voc` database as per [their tutorial](https://www.monetdb.org/Documentation/UserGuide/Tutorial).

With that `voc` database in place:

```
$ sbt run
[info] Set current project to slick-monetdb-generic
[info] Running Example
DEBUG slick.jdbc.JdbcBackend.statement - Preparing statement: select "number", "boatname", "master", "tonnage", "departure_harbour" from "voyages" where "departure_harbour" = 'Ceylon' limit 5
Voyage(4255,Some(HONKOOP),Some(Daniel Deune),Some(1150),Some(Ceylon))
Voyage(4256,Some(WESTERVELD),Some(Swerus Vrolijk),Some(1100),Some(Ceylon))
Voyage(5568,Some(LOOSDUINEN),Some(Daniel de Bak),Some(340),Some(Ceylon))
Voyage(5569,Some(WASSENDE MAAN),None,Some(400),Some(Ceylon))
Voyage(5570,Some(VLAARDINGEN),None,Some(400),Some(Ceylon))
```
