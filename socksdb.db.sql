BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Storage" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"socks"	INTEGER NOT NULL UNIQUE,
	"added"	TEXT NOT NULL,
	"retired"	TEXT,
	"usage"	INTEGER,
	FOREIGN KEY("socks") REFERENCES "Socks"("id")
);
CREATE TABLE IF NOT EXISTS "Socks" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"size"	REAL NOT NULL,
	"colour"	INTEGER NOT NULL,
	"type"	INTEGER NOT NULL,
	"name"	text NOT NULL,
	"manufacture"	int NOT NULL,
	FOREIGN KEY("type") REFERENCES "Type"("id"),
	FOREIGN KEY("manufacture") REFERENCES "Manufacture"("id")
);
CREATE TABLE IF NOT EXISTS "Manufacture" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS "Material" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS "SocksMaterial" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"socks"	INTEGER NOT NULL,
	"material"	INTEGER NOT NULL,
	"percentage"	INTEGER NOT NULL,
	FOREIGN KEY("material") REFERENCES "Material"("id"),
	FOREIGN KEY("socks") REFERENCES "Socks"("id")
);
CREATE TABLE IF NOT EXISTS "Type" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"name"	TEXT NOT NULL UNIQUE
);
INSERT INTO "Storage" VALUES (1,1,'May 03, 2021','Sep 04, 2019',10);
INSERT INTO "Storage" VALUES (5,2,'Mar 20, 2019','Nov 20, 2020',9);
INSERT INTO "Storage" VALUES (6,3,'Mar 20, 2019','Sep 03, 2020',9);
INSERT INTO "Socks" VALUES (1,14.5,255,1,'Носки1',1);
INSERT INTO "Socks" VALUES (2,15.5,205,2,'Носки2',1);
INSERT INTO "Socks" VALUES (3,16.5,155,3,'Носки3',1);
INSERT INTO "Socks" VALUES (4,15.6,255255255,2,'носки4',1);
INSERT INTO "Socks" VALUES (5,16.4,248,2,'Носки5',3);
INSERT INTO "Manufacture" VALUES (1,'Житомир');
INSERT INTO "Manufacture" VALUES (2,'Китай');
INSERT INTO "Manufacture" VALUES (3,'Жашков');
INSERT INTO "Material" VALUES (0,'Material');
INSERT INTO "Material" VALUES (1,'Шерсть');
INSERT INTO "Material" VALUES (2,'Синтетика');
INSERT INTO "Material" VALUES (3,'Космопластик');
INSERT INTO "SocksMaterial" VALUES (7,1,1,49);
INSERT INTO "SocksMaterial" VALUES (8,1,2,33);
INSERT INTO "SocksMaterial" VALUES (9,2,1,40);
INSERT INTO "SocksMaterial" VALUES (10,2,2,40);
INSERT INTO "SocksMaterial" VALUES (11,3,1,30);
INSERT INTO "SocksMaterial" VALUES (12,3,2,60);
INSERT INTO "SocksMaterial" VALUES (13,3,3,10);
INSERT INTO "Type" VALUES (1,'Теплые');
INSERT INTO "Type" VALUES (2,'Обычные');
INSERT INTO "Type" VALUES (3,'Спортивные');
CREATE TRIGGER CompositionConstraint
insert on SocksMaterial
begin
select CASE
when ((new.percentage) > (100 - (select sum(percentage) from SocksMaterial where socks = NEW.socks)))
then raise(abort, "percentage > 100")
end;
end;
COMMIT;
