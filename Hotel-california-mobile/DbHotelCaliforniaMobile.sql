BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Hotel" (
	"hotelId"	INTEGER,
	"razonSocial"	TEXT NOT NULL,
	"cuil"	INTEGER NOT NULL UNIQUE,
	"domicilio"	TEXT,
	"localidad"	TEXT,
	"provincia"	TEXT,
	"cp"	INTEGER,
	"telefono"	INTEGER,
	"categoria"	TEXT NOT NULL,
	"email"	TEXT NOT NULL,
	PRIMARY KEY("hotelId" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Habitacion" (
	"habitacionId"	INTEGER,
	"numero"	INTEGER NOT NULL UNIQUE,
	"precio"	INTEGER DEFAULT 0,
	"descripcion"	TEXT,
	"tipoHabitacion"	TEXT NOT NULL,
	PRIMARY KEY("habitacionId" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Cliente" (
	"clienteId"	INTEGER,
	"usuario"	TEXT NOT NULL,
	"email"	TEXT NOT NULL UNIQUE,
	"password"	TEXT NOT NULL,
	"fechaDeNacimiento"	TEXT NOT NULL,
	"activo"	BLOB NOT NULL DEFAULT 1,
	PRIMARY KEY("clienteId" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Reserva" (
	"reservaId"	INTEGER,
	"habitacionId"	INTEGER NOT NULL,
	"clienteId"	INTEGER NOT NULL,
	"chechIn"	TEXT NOT NULL,
	"checkOut"	TEXT NOT NULL,
	"notificadoAlCliente"	BLOB DEFAULT 0,
	"anulada"	BLOB DEFAULT 0,
	"pagada"	BLOB DEFAULT 0,
	FOREIGN KEY("clienteId") REFERENCES "Cliente"("clienteId"),
	FOREIGN KEY("habitacionId") REFERENCES "Habitacion"("habitacionId"),
	PRIMARY KEY("reservaId" AUTOINCREMENT)
);
INSERT INTO "Hotel" ("hotelId","razonSocial","cuil","domicilio","localidad","provincia","cp","telefono","categoria","email") VALUES (1,'Hotel California',30123456780,'Diagonal San Martín 650','La Falda','Córdoba',5172,3548485451,'4 estrellas','administracion@hotelcalifornia.com.ar');
INSERT INTO "Habitacion" ("habitacionId","numero","precio","descripcion","tipoHabitacion") VALUES (1,102,10000,'','Doble'),
 (2,105,15000,'','Triple'),
 (3,201,18000,'','Cuadruple');
INSERT INTO "Cliente" ("clienteId","usuario","email","password","fechaDeNacimiento","activo") VALUES (1,'Mariana Juarez','mariana.juarez@gmail.com','mj1234','1960/06/06',1),
 (2,'Domingo Ferreyra','domingo.ferreyra@gmail.com','df1234','1954/07/19',1),
 (3,'Sofía Barrionuevo','sofia.barrionuevo@gmail.com','sb1234','1990/01/15',1);
INSERT INTO "Reserva" ("reservaId","habitacionId","clienteId","chechIn","checkOut","notificadoAlCliente","anulada","pagada") VALUES (1,1,3,'2023/10/15','2023/10/20',0,0,0);
COMMIT;
