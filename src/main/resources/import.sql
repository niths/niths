USE niths;

INSERT INTO roles(role_name) values("ROLE_ADMIN");
INSERT INTO roles(role_name) values("ROLE_USER");
--insert into students(email, role_id) values('degyvi09@nith.no', 1);
--insert into students(email, role_id) values('kriand09@nith.no', 1);

INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday,room_number) values('PG111', "Java 1", "Innføring i java", '10:00', '11:00', "Monday","81"); 
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday,room_number) values('PG211', "Java 2", "Innføring i java 2", '11:00', '12:00', "Monday","43");
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday,room_number) values('BU410', "E-Business", "Skolen sparer penger", '10:00', '11:00', "Tuesday","12");
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday,room_number) values('PJ111', "Gruppearbeid", "Arbeid i grupper", '14:00', '17:00', "Friday","34");
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday,room_number) values('DB110', "Database 01", "Arbeid i grupper", '14:00', '17:00', "Friday","34");
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday,room_number) values('PB210', "Database 02", "Arbeid i grupper", '14:00', '17:00', "Friday","34");
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday,room_number) values('PG650', "J2E del 01", "Arbeid i grupper", '14:00', '17:00', "Friday","34");
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday,room_number) values('PG660', "J2E del 02", "Arbeid i grupper", '14:00', '17:00', "Friday","34");
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday,room_number) values('PJ600', "Hovedprosjekt", "Arbeid i grupper", '14:00', '17:00', "Friday","34");


INSERT INTO courses (name, description) VALUES("Mobil apputvikling", "Mobil apputvikling har blitt et stort forretningsområde, der både profesjonelle og amatører kan tjene gode penger på applikasjonene sine.");
INSERT INTO courses (name, description) VALUES("Programmering", "I dag brukes IT og Internett i alt fra handel, kommunikasjon, overvåking og spill til betaling.");
INSERT INTO courses (name, description) VALUES("Spillprogrammering", "Du som søker dette studiet, ønsker å jobbe med avansert programmering og har interesse for simulerings- og spillopplevelser");
INSERT INTO courses (name, description) VALUES("Spilldesign", "En spilldesigner spesialiserer seg på å skape interaktive opplevelser, og har ansvar for spillets regler og mekanikker. ");
INSERT INTO courses (name, description) VALUES("3D-grafikk", "3D-modeller benyttes blant annet i film, spill, reklame, kunst, design og visualisering.");
INSERT INTO courses (name, description) VALUES("Interaktivt design", "Det er i dag viktig å ha brukervennlige webløsninger som tilfredsstiller brukernes behov og forventninger.");
INSERT INTO courses (name, description) VALUES("Digital markedsføring", "En ny generasjon av sosiale elektroniske nettverk gjør Internett til en av våre viktigste kommunikasjonskanaler.");
INSERT INTO courses (name, description) VALUES("E-business", "Det er i dag mangel på høgskoleutdannede personer som har kunnskap om teknologiske forretningsløsninger og hvordan disse best fungerer.");
INSERT INTO courses (name, description) VALUES("Industribachelor", "Våre studenter har nå mulighet for å kombinere en jobb i IT-industrien med en bachelor ved NITH. Vi har inngått et samarbeid med to av landets største konsulent-selskaper, Accenture og Avanade.");


insert into courses_subjects values(1,1);
insert into courses_subjects values(1,2);
insert into courses_subjects values(1,3);
insert into courses_subjects values(2,4);
insert into courses_subjects values(2,1);
insert into courses_subjects values(3,1);
insert into courses_subjects values(3,2);
insert into courses_subjects values(3,3);
insert into courses_subjects values(4,4);
insert into courses_subjects values(4,1);
insert into courses_subjects values(4,2);
insert into courses_subjects values(4,3);
insert into courses_subjects values(4,5);


INSERT INTO students (first_name, last_name, birthday,email,gender,description,phone_number,grade,password) VALUES("Andre", "Kristensen", '2012-02-02',"mail@mail.com",'M',"super awesome","81549300",3,"super secret password *******");
INSERT INTO students (first_name, last_name, birthday,email,gender,description,phone_number,grade,password) VALUES("Bendik", "Rostad", '2012-02-02',"MMMM@mail.com",'M',"super awesome","81249300",3,"super secret password *******");
INSERT INTO students (first_name, last_name, birthday,email,gender,description,phone_number,grade,password) VALUES("Liv", "Kolås", '2012-02-02',"tea@mail.com",'F',"super awesome","15492300",3,"super secret password *******");
INSERT INTO students (first_name, last_name, birthday,email,gender,description,phone_number,grade,password) VALUES("Øyvind", "Ødegård",'2012-02-02',"free@mail.com",'M',"super awesome","41549300",3,"super secret password *******");

INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Wolfe@nith.no','Abraham','Branch','1986-06-08','12003434','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('rallii09@nith.no','Xavier','Rodgers','1979-05-21','88501167','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Richard@nith.no','Julie','Lynch','1988-05-10','23532544','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Lynn@nith.no','Autumn','Conley','1975-11-08','08636004','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Oliver@nith.no','Cally','Burks','1991-04-22','81859501','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Norris@nith.no','Dora','Chase','1988-12-24','20606126','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Tran@nith.no','Teagan','Mccullough','1991-02-04','90087505','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Strickland@nith.no','Curran','Mcintosh','1978-09-23','41738341','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Caldwell@nith.no','Jared','Austin','1970-04-19','24624683','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Shepherd@nith.no','Kennan','Hale','1988-03-01','32901065','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Horne@nith.no','Sheila','Burks','1971-04-14','84225427','3');	
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Vincent@nith.no','Amery','Hale','1987-06-15','58726950','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Larsen@nith.no','Cullen','Harris','1993-02-18','57982890','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Cohen@nith.no','Keith','Shields','1975-06-04','65664609','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Ramirez@nith.no','Henry','Cherry','1987-02-15','77293338','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Graham@nith.no','Jaime','Finch','1980-06-08','26357544','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Barber@nith.no','Charles','Kennedy','1979-11-02','60418635','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Visso@nith.no','Dai','Reese','1971-09-29','99692225','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Kings@nith.no','Colt','Olson','1973-06-23','15115262','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Chandler@nith.no','Vladimir','Chaney','1978-05-08','87723202','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Nolan@nith.no','Jelani','Knapp','1979-10-30','94080151','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Oneil@nith.no','Tanek','Cohen','1972-01-18','90422830','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Calderon@nith.no','Roth','Vazquez','1986-11-08','44160310','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Cortez@nith.no','Simone','Weaver','1972-02-18','34191510','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Mcintosh@nith.no','Mufutau','Savage','1988-02-08','46808292','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Boyle@nith.no','Sandra','Puckett','1973-07-23','40732736','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Hunt@nith.no','Georgia','Greene','1975-08-08','97453748','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Fletcher@nith.no','Naida','Drake','1975-04-02','57518493','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Pratt@nith.no','Jana','Pennington','1987-06-14','49364904','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Hudson@nith.no','Andrew','Burks','1984-07-13','14967782','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Gutierrez@nith.no','Amelia','Gilliam','1989-10-28','73621395','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Estes@nith.no','Kenyon','Rice','1973-07-29','22197209','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Wood@nith.no','Charlotte','Hamilton','1991-07-14','74627055','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Dennis@nith.no','Maile','Montgomery','1978-06-02','57289462','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Small@nith.no','Edan','Joyner','1982-11-03','01471919','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Ramsey@nith.no','Evan','Buck','1991-07-23','87639495','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Crane@nith.no','Xavier','Castillo','1979-06-13','60349490','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Gilbert@nith.no','Kyra','Stafford','1982-07-01','28765974','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Hampton@nith.no','Mariam','Mercer','1976-08-10','87199516','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Kidd@nith.no','Denton','Mccarty','1982-05-09','90563356','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Gay@nith.no','Logan','Reeves','1983-11-28','29193951','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Orr@nith.no','Graiden','Phillips','1978-09-29','23398548','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Boone@nith.no','Bo','Wilkerson','1982-10-14','40653936','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Leblanc@nith.no','Daphne','Fitzgerald','1992-05-19','50427690','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Hancock@nith.no','Virginia','Rollins','1979-08-02','84545049','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Boones@nith.no','Catherine','Lane','1984-02-19','98193728','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Orsr@nith.no','Finn','Mcgee','1987-11-19','31635614','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Perez@nith.no','Sacha','Mercado','1972-05-02','83594044','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Michael@nith.no','Preston','Gentry','1982-08-29','35212375','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Harmon@nith.no','Shana','Barron','1985-02-12','90407365','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Dunlap@nith.no','Buffy','Bush','1971-06-06','39535869','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Spence@nith.no','Christian','Mullins','1979-08-09','28272182','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Everett@nith.no','Mannix','Vega','1988-02-23','69432945','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Allen@nith.no','Rosalyn','Marks','1975-05-28','85433596','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Cruz@nith.no','Valentine','Morin','1976-03-17','32854141','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Warner@nith.no','Amity','Gordon','1981-04-07','99989848','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Wells@nith.no','Ferdinand','Randolph','1980-02-27','51393767','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Jefferson@nith.no','Kane','Kim','1976-05-20','67676015','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Barnett@nith.no','Audrey','Warner','1986-05-25','89728994','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Griffith@nith.no','Victor','Head','1988-11-11','10114361','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Fleming@nith.no','Priscilla','Pacheco','1988-04-03','39239738','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Bryant@nith.no','Coby','Mcdaniel','1971-10-04','00004455','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Mcneil@nith.no','Lisandra','Washington','1987-08-25','94007971','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Wong@nith.no','Bianca','Stewart','1983-07-11','78749809','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Bean@nith.no','Camden','Golden','1991-02-06','45991781','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Webster@nith.no','Ezekiel','Skinner','1973-05-27','88978465','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Guy@nith.no','Alfonso','Craft','1982-10-30','20110245','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Hernandez@nith.no','Chantale','Anderson','1975-11-15','14123302','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Bauer@nith.no','Abraham','Aguilar','1989-08-12','46570692','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Casey@nith.no','Quinlan','Pitts','1979-12-29','48322823','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Hess@nith.no','Eve','Morrow','1978-11-12','76887982','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Johnston@nith.no','Zorita','Logan','1983-03-31','09771045','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Reynolds@nith.no','Mallory','Caldwell','1975-01-25','60484051','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Herring@nith.no','Caesar','Kent','1989-02-15','39784735','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Carver@nith.no','Kamal','Schneider','1989-04-12','26314120','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Rice@nith.no','Simone','Moon','1992-11-05','84858801','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Salazar@nith.no','Arsenio','Fields','1991-12-11','54825008','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Hicks@nith.no','Yen','Cohen','1975-08-08','52270138','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Conrad@nith.no','Alexandra','Hughes','1985-11-11','05693628','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Alvarez@nith.no','Cyrus','Nash','1990-03-21','01475491','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Dalton@nith.no','Rowan','Roberson','1980-10-16','32694045','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Bishop@nith.no','Anne','Warren','1980-10-30','76886947','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('simsam92nith.no','Clementine','Obrien','1980-03-31','45817791','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Mcdowell@nith.no','Daniel','Huffman','1978-08-22','65026423','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Beard@nith.no','Garth','Shaw','1975-04-11','96506170','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Home@nith.no','Hiram','Henderson','1983-03-06','21527674','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Price@nith.no','Keely','Schultz','1992-09-24','29130319','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Roberson@nith.no','Brendan','Mcdonald','1973-07-06','98653896','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Lamb@nith.no','Ursa','Pate','1980-04-03','16134816','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Guerrero@nith.no','Zoe','Ruiz','1978-12-23','71779838','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Vargas@nith.no','Alisa','Anderson','1991-08-20','74679641','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Little@nith.no','Anjolie','Riddle','1977-03-22','36410497','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Reilly@nith.no','Alma','Massey','1978-01-21','57426824','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Walters@nith.no','Darius','Estes','1976-08-18','55390864','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Bray@nith.no','Giselle','Lawrence','1991-05-27','55213769','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Butler@nith.no','Harlan','Carrillo','1991-12-08','04533669','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Adams@nith.no','Rhea','Elliott','1984-10-20','85736381','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Camacho@nith.no','Lucian','Durham','1987-07-04','89230341','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Barnes@nith.no','Angela','Daugherty','1980-04-04','70190019','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('Summers@nith.no','Malik','Fischer','1987-12-18','21412731','3');

INSERT INTO committees (name,description) VALUES("Linx utvalget","Linux");
INSERT INTO committees (name,description) VALUES("Apple utvalget","Apple GOGO");
INSERT INTO committees (name,description) VALUES("Microsoft utvalget","Microsoft");
INSERT INTO committees (name,description) VALUES("KIT", "kvinner og it");
INSERT INTO committees (name,description) VALUES("MDF", "media design og foto");

INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (1, 1);
INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (2, 2);
INSERT INTO committee_leaders (committees_id, leaders_id)VALUES (3, 3);
INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (4, 4);
INSERT INTO committee_leaders (committees_id, leaders_id)VALUES (4, 5);
INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (5, 40);
INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (5, 30);
INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (5, 20);
INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (5, 10);
INSERT INTO committee_leaders (committees_id, leaders_id)VALUES (3, 55);

INSERT INTO events (name,description,startTime,endTime,tags) VALUES("LUG Distro"," distro fest",'2012-03-09 11:05:32','2012-03-09 11:05:32',"Linux, utvalg");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Fest i Kroa","Valentine party",'2012-03-09 11:05:32','2012-03-09 11:05:32',"kroa, utvalg");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Workshop"," Android workshop",'2012-03-09 11:05:32','2012-03-09 11:05:32',"Androworkshop");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Årsmøte"," Obligatorisk",'2012-03-09 11:05:32','2012-03-09 11:05:32',"årsmøte, møte");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("KIT-møte","Damenes møte",'2012-03-09 11:05:32','2012-03-09 11:05:32',"kit, møte");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Fadderuke kickoff","kickoff fest",'2012-03-09 11:05:32','2012-03-09 11:05:32',"fadderuke, kroa");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Fadderuke party","party",'2012-03-09 11:05:32','2012-03-09 11:05:32',"fadderuke");

INSERT INTO events (name,description,startTime,endTime,tags) VALUES("LUG Distro2"," distro fest",'2012-03-09 11:05:32','2012-03-09 11:05:32',"Linux, utvalg");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Fest i Kroa3","Valentine party",'2012-03-09 11:05:32','2012-03-09 11:05:32',"kroa, utvalg");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Workshopxx"," Android workshop",'2012-03-09 11:05:32','2012-03-09 11:05:32',"Androworkshop");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Årsmøte4"," Obligatorisk",'2012-03-09 11:05:32','2012-03-09 11:05:32',"årsmøte, møte");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("KIT-møte4","Damenes møte",'2012-03-09 11:05:32','2012-03-09 11:05:32',"kit, møte");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Fadderuke5 kickoff","kickoff fest",'2012-03-09 11:05:32','2012-03-09 11:05:32',"fadderuke, kroa");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Fadderuke5 party","party",'2012-03-09 11:05:32','2012-03-09 11:05:32',"fadderuke");


INSERT INTO events (name,description,startTime,endTime,tags) VALUES("LUG Distrod"," distro fest",'2012-03-09 11:05:32','2012-03-09 11:05:32',"Linux, utvalg");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Fest i Kroad","Valentine party",'2012-03-09 11:05:32','2012-03-09 11:05:32',"kroa, utvalg, hjerte");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Workshop2x"," Android workshop",'2012-03-09 11:05:32','2012-03-09 11:05:32',"Androworkshop,ruben");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Årsmøte2x"," Obligatorisk",'2012-03-09 11:05:32','2012-03-09 11:05:32',"årsmøte, møte,bøker");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("KIT-møte3x","Damenes møte",'2012-03-09 11:05:32','2012-03-09 11:05:32',"kit, møte, alkoholfritt");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Fadderukezx3 kickoff","kickoff fest",'2012-03-09 11:05:32','2012-03-09 11:05:32',"fadderuke, kroa");
INSERT INTO events (name,description,startTime,endTime,tags) VALUES("Fadderukex3 party","party",'2012-03-09 11:05:32','2012-03-09 11:05:32',"fadderuke, fest");


insert into student_orientation_groups (group_id) values(1);
insert into student_orientation_groups (group_id) values(2);
insert into student_orientation_groups (group_id) values(3);
insert into student_orientation_groups (group_id) values(4);
insert into student_orientation_groups (group_id) values(5);
insert into student_orientation_groups (group_id) values(1);
insert into student_orientation_groups (group_id) values(2);
insert into student_orientation_groups (group_id) values(3);
insert into student_orientation_groups (group_id) values(4);
insert into student_orientation_groups (group_id) values(5);

insert into student_orientation_groups (group_id) values(1);
insert into student_orientation_groups (group_id) values(2);
insert into student_orientation_groups (group_id) values(3);
insert into student_orientation_groups (group_id) values(4);
insert into student_orientation_groups (group_id) values(5);
insert into student_orientation_groups (group_id) values(1);
insert into student_orientation_groups (group_id) values(2);
insert into student_orientation_groups (group_id) values(3);
insert into student_orientation_groups (group_id) values(4);
insert into student_orientation_groups (group_id) values(5);

insert into students_student_orientation_groups  values(1, 1);
insert into students_student_orientation_groups  values(2, 2);
insert into students_student_orientation_groups  values(3, 3);
insert into students_student_orientation_groups  values(4, 4);
insert into students_student_orientation_groups  values(5, 5);
insert into students_student_orientation_groups  values(6, 6);
insert into students_student_orientation_groups  values(7, 7);
insert into students_student_orientation_groups  values(8, 8);
insert into students_student_orientation_groups  values(9, 9);
insert into students_student_orientation_groups  values(10, 10);

insert into students_student_orientation_groups  values(10, 11);
insert into students_student_orientation_groups  values(22, 12);
insert into students_student_orientation_groups  values(13, 13);
insert into students_student_orientation_groups  values(44, 14);
insert into students_student_orientation_groups  values(15, 15);
insert into students_student_orientation_groups  values(36, 16);
insert into students_student_orientation_groups  values(37, 17);
insert into students_student_orientation_groups  values(48, 18);
insert into students_student_orientation_groups  values(39, 19);
insert into students_student_orientation_groups  values(32, 20);

insert into students_committees values(1, 1);
insert into students_committees values(1, 2);
insert into students_committees values(2, 1);
insert into students_committees values(3, 1);

insert into students_committees values(7, 1);
insert into students_committees values(6, 2);
insert into students_committees values(5, 1);
insert into students_committees values(4, 1);

insert into students_committees values(8, 4);
insert into students_committees values(9, 2);
insert into students_committees values(10, 3);
insert into students_committees values(12, 1);


insert into students_committees values(71, 3);
insert into students_committees values(16, 2);
insert into students_committees values(25, 5);
insert into students_committees values(24, 3);

insert into students_courses values(1, 1);
insert into students_courses values(2, 2);
insert into students_courses values(3, 1);
insert into students_courses values(4, 3);

insert into committees_events values(1, 1);
insert into committees_events values(1, 2);
insert into committees_events values(1, 3);
insert into committees_events values(1, 4);
insert into committees_events values(2, 5);
insert into committees_events values(2, 6);
