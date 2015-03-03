/* 
	09/02/2015 - Versão inicial
	

 */
DROP TABLE gps_user_position;

DROP TABLE gps_position;

DROP TABLE gps_user;

select * from gps_position;

truncate gps_position;


create table gps_position(
	id INT PRIMARY KEY AUTO_INCREMENT,
	gpsId INT NOT NULL,
	posX DOUBLE NOT NULL,
	posy DOUBLE NOT NULL,
	date datetime NOT NULL
);

create table gps_user(
	idUser INT PRIMARY KEY,
	login VARCHAR (10),
	pwd VARCHAR (10)
);

create table gps_user_position(
	id INT PRIMARY KEY,
	idUser INT,
	idGps INT,
	CONSTRAINT fk_user FOREIGN KEY (idUser) REFERENCES gps_user(idUser),
	CONSTRAINT fk_gps FOREIGN KEY (idGps) REFERENCES gps_position(idGps)
);