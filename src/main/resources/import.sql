--USE niths;
--------------------------------------------------------------------
----------------------ROLES-----------------------------------------
INSERT INTO roles(role_name) VALUES('ROLE_ADMIN');
INSERT INTO roles(role_name) VALUES('ROLE_SR');
INSERT INTO roles(role_name) VALUES('ROLE_COMMITTEE_LEADER');
INSERT INTO roles(role_name) VALUES('ROLE_FADDER_LEADER');
INSERT INTO roles(role_name) VALUES('ROLE_ANONYMOUS');
INSERT INTO roles(role_name) VALUES('ROLE_STUDENT');
INSERT INTO roles(role_name) VALUES('ROLE_DEVELOPER');
---------------------------------------------------------------------
--------------------------Subjects-----------------------------------
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday) VALUES('PG111', 'Java 1', 'Innføring i java', '10:00', '11:00', 'Monday'); 
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday) VALUES('PG211', 'Java 2', 'Innføring i java 2', '11:00', '12:00', 'Monday');
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday) VALUES('BU410', 'E-Business', 'Skolen sparer penger', '10:00', '11:00', 'Tuesday');
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday) VALUES('PJ111', 'Gruppearbeid', 'Arbeid i grupper', '14:00', '17:00', 'Friday');
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday) VALUES('DB110', 'Database 01', 'Arbeid i grupper', '14:00', '17:00', 'Friday');
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday) VALUES('PB210', 'Database 02', 'Arbeid i grupper', '14:00', '17:00', 'Friday');
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday) VALUES('PG650', 'J2E del 01', 'Arbeid i grupper', '14:00', '17:00', 'Friday');
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday) VALUES('PG660', 'J2E del 02', 'Arbeid i grupper', '14:00', '17:00', 'Friday');
INSERT INTO subjects (subject_code, name, description, start_time, end_time, weekday) VALUES('PJ600', 'Hovedprosjekt', 'Arbeid i grupper', '14:00', '17:00', 'Friday');
---------------------------------------------------------------------
--------------------------courses------------------------------------
INSERT INTO courses (name, description) VALUES('Mobil apputvikling', 'Mobil apputvikling har blitt et stort forretningsområde, der både profesjonelle og amatører kan tjene gode penger på applikasjonene sine.');
INSERT INTO courses (name, description) VALUES('Programmering', 'I dag brukes IT og Internett i alt fra handel, kommunikasjon, overvåking og spill til betaling.');
INSERT INTO courses (name, description) VALUES('Spillprogrammering', 'Du som søker dette studiet, ønsker å jobbe med avansert programmering og har interesse for simulerings- og spillopplevelser');
INSERT INTO courses (name, description) VALUES('Spilldesign', 'En spilldesigner spesialiserer seg på å skape interaktive opplevelser, og har ansvar for spillets regler og mekanikker. ');
INSERT INTO courses (name, description) VALUES('3D-grafikk', '3D-modeller benyttes blant annet i film, spill, reklame, kunst, design og visualisering.');
INSERT INTO courses (name, description) VALUES('Interaktivt design', 'Det er i dag viktig å ha brukervennlige webløsninger som tilfredsstiller brukernes behov og forventninger.');
INSERT INTO courses (name, description) VALUES('Digital markedsføring', 'En ny generasjon av sosiale elektroniske nettverk gjør Internett til en av våre viktigste kommunikasjonskanaler.');
INSERT INTO courses (name, description) VALUES('E-business', 'Det er i dag mangel på høgskoleutdannede personer som har kunnskap om teknologiske forretningsløsninger og hvordan disse best fungerer.');
INSERT INTO courses (name, description) VALUES('Industribachelor', 'Våre studenter har nå mulighet for å kombinere en jobb i IT-industrien med en bachelor ved NITH. Vi har inngått et samarbeid med to av landets største konsulent-selskaper, Accenture og Avanade.');
---------------------------------------------------------------------
--------------------------courses_subjects---------------------------
INSERT INTO courses_subjects VALUES(1,1);
INSERT INTO courses_subjects VALUES(1,2);
INSERT INTO courses_subjects VALUES(1,3);
INSERT INTO courses_subjects VALUES(2,4);
INSERT INTO courses_subjects VALUES(2,1);
INSERT INTO courses_subjects VALUES(3,1);
INSERT INTO courses_subjects VALUES(3,2);
INSERT INTO courses_subjects VALUES(3,3);
INSERT INTO courses_subjects VALUES(4,4);
INSERT INTO courses_subjects VALUES(4,1);
INSERT INTO courses_subjects VALUES(4,2);
INSERT INTO courses_subjects VALUES(4,3);
INSERT INTO courses_subjects VALUES(4,5);
---------------------------------------------------------------------
--------------------------Students-----------------------------------
INSERT INTO students (first_name, last_name, birthday,email,gender,description,phone_number,grade) VALUES('Øyvind', 'Ødegård','1990-02-02','degyvi09@nith.no','M','super awesome','41549300',3);
INSERT INTO students (first_name, last_name, birthday,email,gender,description,phone_number,grade) VALUES('Andre', 'Dockinsen', '1989-08-07','kriand09@nith.no','M','super awesome','81549300',3);
INSERT INTO students (first_name, last_name, birthday,email,gender,description,phone_number,grade) VALUES('Bendik', 'Rostad', '2012-02-02','rosben09@nith.no','M','super awesome','81249300',3);
INSERT INTO students (first_name, last_name, birthday,email,gender,description,phone_number,grade) VALUES('Liv', 'Kolås', '2012-02-02','tea@mail.com','F','super awesome','15492300',3);
---------------------------------------------------------------------
--------------------------Students_roles-----------------------------------
INSERT INTO students_roles(students_id, roles_id) VALUES(1, 1);
INSERT INTO students_roles(students_id, roles_id) VALUES(1, 2);
INSERT INTO students_roles(students_id, roles_id) VALUES(1, 3);
INSERT INTO students_roles(students_id, roles_id) VALUES(2, 1);
INSERT INTO students_roles(students_id, roles_id) VALUES(2, 2);
INSERT INTO students_roles(students_id, roles_id) VALUES(2, 4);
INSERT INTO students_roles(students_id, roles_id) VALUES(3, 1);
INSERT INTO students_roles(students_id, roles_id) VALUES(3, 3);
INSERT INTO students_roles(students_id, roles_id) VALUES(4, 1);
---------------------------------------------------------------------
--------------------------Room---------------------------------------
INSERT INTO rooms(room_name) VALUES('38');
INSERT INTO rooms(room_name) VALUES('39');
INSERT INTO rooms(room_name) VALUES('40');
INSERT INTO rooms(room_name) VALUES('41');
INSERT INTO rooms(room_name) VALUES('43 / 44');
INSERT INTO rooms(room_name) VALUES('45');
INSERT INTO rooms(room_name) VALUES('77');
INSERT INTO rooms(room_name) VALUES('78');
INSERT INTO rooms(room_name) VALUES('79 / 80');
INSERT INTO rooms(room_name) VALUES('81');
INSERT INTO rooms(room_name) VALUES('82');
INSERT INTO rooms(room_name) VALUES('Stillerom');
INSERT INTO rooms(room_name) VALUES('84');
INSERT INTO rooms(room_name) VALUES('Auditoriet');
INSERT INTO rooms(room_name) VALUES('Battlestation');
INSERT INTO rooms(room_name) VALUES('Biblioteket');
INSERT INTO rooms(room_name) VALUES('Kantina');
INSERT INTO rooms(room_name) VALUES('Vrimleområdet');
INSERT INTO rooms(room_name) VALUES('Fagstab');
---------------------------------------------------------------------
--------------------------accesspoints-------------------------------
INSERT INTO accesspoints(address) VALUES('00:21:55:60:e7:d0');
INSERT INTO accesspoints(address) VALUES('00:24:97:f2:92:e0');
INSERT INTO accesspoints(address) VALUES('00:24:97:f2:91:70');
INSERT INTO accesspoints(address) VALUES('00:18:74:d3:4b:90');
INSERT INTO accesspoints(address) VALUES('00:26:cb:d1:2e:10');
INSERT INTO accesspoints(address) VALUES('00:26:cb:d1:2d:a0');
INSERT INTO accesspoints(address) VALUES('00:17:0f:e7:2d:60');
INSERT INTO accesspoints(address) VALUES('00:21:55.60.e6:10');
INSERT INTO accesspoints(address) VALUES('dc:7b:94:34:94:90');
INSERT INTO accesspoints(address) VALUES('00:17:df:a6:c8:80');
INSERT INTO accesspoints(address) VALUES('00:17:df:a7:7c:50');
INSERT INTO accesspoints(address) VALUES('dc:7b:94:a4:2e:80');INSERT INTO accesspoints(address) VALUES('00:26:cb:d1:2b:40');
INSERT INTO accesspoints(address) VALUES('00:23:04:88:d4:c0');
INSERT INTO accesspoints(address) VALUES('00:17:df:a7:7a:b0');
INSERT INTO accesspoints(address) VALUES('00:24:97:f2:93:60');
INSERT INTO accesspoints(address) VALUES('00:23:04:88:c1:20');
INSERT INTO accesspoints(address) VALUES('00:19:07:07:79:80');
INSERT INTO accesspoints(address) VALUES('00:24:97:f2:93:40');
---------------------------------------------------------------------
--------------------------accessfields-------------------------------
INSERT INTO accessfields(min_range, max_range) VALUES(53, 64);
INSERT INTO accessfields(min_range, max_range) VALUES(52, 65);
INSERT INTO accessfields(min_range, max_range) VALUES(63, 75);
INSERT INTO accessfields(min_range, max_range) VALUES(59, 65);
INSERT INTO accessfields(min_range, max_range) VALUES(58, 76);
INSERT INTO accessfields(min_range, max_range) VALUES(50, 70);
INSERT INTO accessfields(min_range, max_range) VALUES(62, 71);
INSERT INTO accessfields(min_range, max_range) VALUES(48, 66);
INSERT INTO accessfields(min_range, max_range) VALUES(47, 62);
INSERT INTO accessfields(min_range, max_range) VALUES(65, 73);
INSERT INTO accessfields(min_range, max_range) VALUES(46, 62);
INSERT INTO accessfields(min_range, max_range) VALUES(52, 72);
INSERT INTO accessfields(min_range, max_range) VALUES(43, 65);
INSERT INTO accessfields(min_range, max_range) VALUES(38, 59);
INSERT INTO accessfields(min_range, max_range) VALUES(51, 62);
INSERT INTO accessfields(min_range, max_range) VALUES(59, 76);
INSERT INTO accessfields(min_range, max_range) VALUES(61, 71);
INSERT INTO accessfields(min_range, max_range) VALUES(61, 80);
INSERT INTO accessfields(min_range, max_range) VALUES(61, 70);
INSERT INTO accessfields(min_range, max_range) VALUES(61, 71);
INSERT INTO accessfields(min_range, max_range) VALUES(55, 62);
INSERT INTO accessfields(min_range, max_range) VALUES(50, 69);
---------------------------------------------------------------------
--------------------------accessfields_accesspoints------------------
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(1, 1);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(2, 2);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(3, 3);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(3, 4);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(4, 5);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(5, 6);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(6, 7);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(7, 8);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(8, 9);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(9, 10);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(9, 11);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(9, 12);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(10, 13);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(11, 14);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(12, 15);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(13, 16);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(17, 17);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(15, 18);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(16, 19);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(14, 20);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(18, 21);
INSERT INTO accessfields_accesspoints (accesspoint_id, accessfield_id) VALUES(19, 22);
---------------------------------------------------------------------
--------------------------rooms_accessfields-----------------------------------
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(1, 1);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(2, 2);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(3, 3);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(4, 4);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(5, 5);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(6, 6);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(7, 7);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(8, 8);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(9, 10);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(11, 11);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(10, 12);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(12, 13);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(13, 14);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(14, 14);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(15, 14);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(21, 16);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(16, 17);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(17, 18);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(18, 18);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(19, 18);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(20, 18);
INSERT INTO rooms_accessfields(accessfield_id, room_id) VALUES(22, 19);
---------------------------------------------------------------------
--------------------------Students-----------------------------------
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('herale09@nith.no','Abraham','Branch','1986-06-08','12003434','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('breale@nith.no','Xavier','Rodgers','1979-05-21','88501167','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('houale11@nith.no','Julie','Lynch','1988-05-10','23532544','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('hegagn09@nith.no','Autumn','Conley','1975-11-08','08636004','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('hanand10@nith.no','Cally','Burks','1991-04-22','81859501','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('sanane09@nith.no','Dora','Chase','1988-12-24','20606126','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('tveari10@nith.no','Teagan','Mccullough','1991-02-04','90087505','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('mykbrd10@nith.no','Curran','McINTOsh','1978-09-23','41738341','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('ileayh09@nith.no','Jared','Austin','1970-04-19','24624683','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('blistog@nith.no','Kennan','Hale','1988-03-01','32901065','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('reicar09@nith.no','Sheila','Burks','1971-04-14','84225427','3');	
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('mllchr09@nith.no','Amery','Hale','1987-06-15','58726950','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('ellchr09@nith.no','Cullen','Harris','1993-02-18','57982890','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('pauchr09@nith.no','Keith','Shields','1975-06-04','65664609','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('habeli09@nith.no','Henry','Cherry','1987-02-15','77293338','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('schesp09@nith.no','Jaime','Finch','1980-06-08','26357544','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('enefre09@nith.no','Charles','Kennedy','1979-11-02','60418635','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('lishei09@nith.no','Dai','Reese','1971-09-29','99692225','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('nymlar09@nith.no','Colt','Olson','1973-06-23','15115262','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('mobmad@nith.no','Vladimir','Chaney','1978-05-08','87723202','1');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('niemar09@nith.no','Jelani','Knapp','1979-10-30','94080151','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('skaodd09@nith.no','Tanek','Cohen','1972-01-18','90422830','2');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('myknik09@nith.no','Roth','Vazquez','1986-11-08','44160310','3');
INSERT INTO students (email,first_name,last_name,birthday,phone_number,grade) VALUES ('omaola09@nith.no','Simone','Weaver','1972-02-18','34191510','2');
---------------------------------------------------------------------
--------------------------committees---------------------------------
INSERT INTO committees (name,description) VALUES('Linx utvalget','Linux');
INSERT INTO committees (name,description) VALUES('Apple utvalget','Apple GOGO');
INSERT INTO committees (name,description) VALUES('Microsoft utvalget','Microsoft');
INSERT INTO committees (name,description) VALUES('KIT', 'kvinner og it');
INSERT INTO committees (name,description) VALUES('MDF', 'media design og foto');
---------------------------------------------------------------------
--------------------------committees_leaders-------------------------
INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (1, 1);
INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (2, 2);
INSERT INTO committee_leaders (committees_id, leaders_id)VALUES (3, 3);
INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (4, 4);
INSERT INTO committee_leaders (committees_id, leaders_id)VALUES (4, 5);
INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (5, 6);
INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (5, 7);
INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (5, 8);
INSERT INTO committee_leaders(committees_id, leaders_id) VALUES (5, 9);
INSERT INTO committee_leaders (committees_id, leaders_id) VALUES (3, 10);
---------------------------------------------------------------------
--------------------------events-------------------------------------
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('LUG Distro',' distro fest','2012-04-19 11:05:32','2012-03-09 11:05:32','Linux, utvalg');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Fest i Kroa','Valentine party','2012-05-09 11:05:32','2012-03-09 11:05:32','kroa, utvalg');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Workshop',' Android workshop','2012-04-20 11:05:32','2012-03-09 11:05:32','Android workshop');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Årsmøte',' Obligatorisk','2012-03-09 11:05:32','2012-03-09 11:05:32','årsmøte, møte');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('KIT-møte','Damenes møte','2012-03-09 11:05:32','2012-03-09 11:05:32','kit, møte');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Fadderuke kickoff','kickoff fest','2012-03-09 11:05:32','2012-03-09 11:05:32','fadderuke, kroa');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Fadderuke party','party','2012-03-09 11:05:32','2012-03-09 11:05:32','fadderuke');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Ølsmaking','Super cool smaking','2012-04-20 11:05:32','2012-04-11 12:05:32','Linux, utvalg');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Fest i Kroa3','Valentine party','2012-04-11 13:05:32','2012-04-11 16:05:32','kroa, utvalg');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Workshopxx',' Android workshop','2012-04-13 11:05:32','2012-04-13 11:05:32','Androworkshop');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Årsmøte4',' Obligatorisk','2012-04-18 11:05:32','2012-04-12 14:05:32','årsmøte, møte');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('KIT-møte4','Damenes møte','2012-04-19 11:05:32','2012-04-14 11:05:32','kit, møte');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Fadderuke5 kickoff','kickoff fest lorem ipsum ald adskaevmz,xvn en lang beskrivelse skal se hvorda det ser ut','2012-04-16 11:05:32','2012-04-16 11:05:32','fadderuke, kroa');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Fadderuke5 party','party','2012-04-15 10:05:32','2012-04-15 11:05:32','fadderuke');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('LUG Distrod',' distro fest','2012-05-19 11:05:32','2012-03-09 11:05:32','Linux, utvalg');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Fest i Kroad','Valentine party','2012-05-10 11:05:32','2012-03-09 11:05:32','kroa, utvalg, hjerte');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Workshop2x',' Android workshop','2012-05-09 11:05:32','2012-03-09 11:05:32','Androworkshop,ruben');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Årsmøte2x',' Obligatorisk','2012-04-18 11:05:32','2012-03-09 11:05:32','årsmøte, møte,bøker');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('KIT-møte3x','Damenes møte','2012-04-19 11:05:32','2012-03-09 11:05:32','kit, møte, alkoholfritt');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Fadderukezx3 kickoff','kickoff fest','2012-03-09 11:05:32','2012-03-09 11:05:32','fadderuke, kroa');
INSERT INTO events (name,description,startTime,endTime,tags) VALUES('Fadderukex3 party','party','2012-04-22 11:05:32','2012-03-09 11:05:32','fadderuke, fest');
---------------------------------------------------------------------
--------------------------fadder-----------------------------------
INSERT INTO fadder_groups(id, group_number) VALUES (1, 1);
INSERT INTO fadder_groups(id, group_number) VALUES (2, 2);
INSERT INTO fadder_groups(id, group_number) VALUES (3, 3);
INSERT INTO fadder_groups(id, group_number) VALUES (4, 4);
---------------------------------------------------------------------
--------------------------fadder_leaders-----------------------------
INSERT INTO fadder_leaders_students (leaders_id, fadder_groups_id) VALUES(1, 1);
INSERT INTO fadder_leaders_students (leaders_id, fadder_groups_id) VALUES(1, 2);
INSERT INTO fadder_leaders_students (leaders_id, fadder_groups_id) VALUES(2, 4);
INSERT INTO fadder_leaders_students (leaders_id, fadder_groups_id) VALUES(3, 3);
INSERT INTO fadder_leaders_students (leaders_id, fadder_groups_id) VALUES(4, 4);
INSERT INTO fadder_leaders_students (leaders_id, fadder_groups_id) VALUES(5, 4);
INSERT INTO fadder_leaders_students (leaders_id, fadder_groups_id) VALUES(6, 1);
---------------------------------------------------------------------
--------------------------fadder_children----------------------------
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(1, 2);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(1, 6);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(1, 7);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(1, 8);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(1, 9);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(1, 10);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(1, 11);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(1, 12);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(2, 13);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(2, 14);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(2, 15);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(3, 16);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(3, 17);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(3, 18);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(4, 19);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(4, 20);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(4, 21);
INSERT INTO fadder_children_students (fadder_groups_id, fadderChildren_id) VALUES(4, 22);
---------------------------------------------------------------------
--------------------------Students_commitees-------------------------
INSERT INTO students_committees(students_id, committees_id) VALUES(1, 1);
INSERT INTO students_committees(students_id, committees_id) VALUES(1, 2);
INSERT INTO students_committees(students_id, committees_id) VALUES(2, 1);
INSERT INTO students_committees(students_id, committees_id) VALUES(3, 1);
INSERT INTO students_committees(students_id, committees_id) VALUES(7, 1);
INSERT INTO students_committees(students_id, committees_id) VALUES(6, 2);
INSERT INTO students_committees(students_id, committees_id) VALUES(5, 1);
INSERT INTO students_committees(students_id, committees_id) VALUES(4, 1);
INSERT INTO students_committees(students_id, committees_id) VALUES(8, 4);
INSERT INTO students_committees(students_id, committees_id) VALUES(9, 2);
INSERT INTO students_committees(students_id, committees_id) VALUES(10, 3);
INSERT INTO students_committees(students_id, committees_id) VALUES(12, 1);
INSERT INTO students_committees(students_id, committees_id) VALUES(13, 3);
INSERT INTO students_committees(students_id, committees_id) VALUES(14, 2);
INSERT INTO students_committees(students_id, committees_id) VALUES(15, 5);
INSERT INTO students_committees(students_id, committees_id) VALUES(16, 3);
---------------------------------------------------------------------
--------------------------Students_courses---------------------------
INSERT INTO students_courses VALUES(1, 1);
INSERT INTO students_courses VALUES(2, 2);
INSERT INTO students_courses VALUES(3, 1);
INSERT INTO students_courses VALUES(4, 3);
---------------------------------------------------------------------
--------------------------committees_events--------------------------
INSERT INTO committees_events(committees_id, events_id) VALUES(1, 1);
INSERT INTO committees_events(committees_id, events_id) VALUES(1, 2);
INSERT INTO committees_events(committees_id, events_id) VALUES(1, 3);
INSERT INTO committees_events(committees_id, events_id) VALUES(1, 4);
INSERT INTO committees_events(committees_id, events_id) VALUES(2, 5);
---------------------------------------------------------------------
--------------------------developers--------------------------------
INSERT INTO developers(id, name, email) VALUES (1, 'Urtegata', 'urter@mail.com');
INSERT INTO developers(id, name, email) VALUES (2, 'Knut sine apps', 'knut@mail.com');
INSERT INTO developers(id, name, email) VALUES (3, 'Apps AS', 'apps@mail.com');
INSERT INTO developers(id, name, email) VALUES (4, 'Android etc', 'android@mail.com');
INSERT INTO developers(id, name, email, developer_key, developer_token, enabled) VALUES (5, 'NithsTeam', 'nithscommunity@mail.com', 'sob8gvpyk2', '1QnBcxhDpSOBWy/b32uaGd9vIYnxpFRTZ9wLWqsH4ji3yy1m1QuFXy2jfNS29xbZVJdHzZ/wsCQas2KaCkAd6q+xjcWzG/tp6AFEBhriL36BAKpAIZbsAb5uvnIJWbratFCExhxv24M=', 1);
---------------------------------------------------------------------
--------------------------applications--------------------------------
INSERT INTO applications(id, title) VALUES(1, 'Appen');
INSERT INTO applications(id, title) VALUES(2, 'Den andre appen');
INSERT INTO applications(id, title) VALUES(3, 'Angry Birds');
INSERT INTO applications(id, title) VALUES(4, 'Drawsome');
INSERT INTO applications(id, title, application_key, application_token, enabled) VALUES(5, 'fuNITHs','NH3E5WANTJ', '/SwLTWhow4mp5CTtF94a4lI8OtY5J8lXw4L7sYO5jIJqQ1RgUR0D7B40toaU3lzP4Za7TDwgMXKdAlg2ZwCTTR7n9us4Y+8ZZVV2NbWkPD8zPdl1y8hl3CI6ZZ+SjEKrEihppMA8Irw=', 1);
---------------------------------------------------------------------
--------------------------developers_applications--------------------------------
INSERT INTO developers_applications(developers_id, applications_id) VALUES (1, 1);
INSERT INTO developers_applications(developers_id, applications_id) VALUES (1, 2);
INSERT INTO developers_applications(developers_id, applications_id) VALUES (2, 3);
INSERT INTO developers_applications(developers_id, applications_id) VALUES (5, 5);
---------------------------------------------------------------------
--------------------------subjects_tutors--------------------------------
insert into subjects_tutors values (1,1);
---------------------------------------------------------------------
--------------------------locations--------------------------------
insert into locations (latitude, longitude, place) values(12.344, 12.1245, 'Stord');
insert into locations (latitude, longitude, place) values(12.344, 12.1245, 'Oslo');
insert into locations (latitude, longitude, place) values(59.91128, 10.7606, 'NITH');
insert into locations (latitude, longitude, place) values(59.9378, 10.7354, 'Nedre ullevål');
---------------------------------------------------------------------
--------------------------events_location--------------------------------
insert into events_location (location_id, events_id) values(1, 1);
insert into events_location (location_id, events_id) values(1, 2);
insert into events_location (location_id, events_id) values(2, 3);
insert into events_location (location_id, events_id) values(4, 5);
insert into events_location (location_id, events_id) values(4, 4);
insert into events_location (location_id, events_id) values(3, 6);
insert into events_location (location_id, events_id) values(4, 7);
insert into events_location (location_id, events_id) values(3, 8);
insert into events_location (location_id, events_id) values(4, 9);
insert into events_location (location_id, events_id) values(3, 10);
insert into events_location (location_id, events_id) values(4, 11);
insert into events_location (location_id, events_id) values(3, 12);
insert into events_location (location_id, events_id) values(4, 13);
insert into events_location (location_id, events_id) values(3, 14);
---------------------------------------------------------------------
--------------------------feeds------------------------------------
insert into feeds (id, message, published) values(1, 'Kroa åpner 14:00 ', '2012-03-21 13:00');
insert into feeds (id, message, published) values(2, 'Salg i kantina ', '2012-03-21 13:00');
---------------------------------------------------------------------
--------------------------feeds_location--------------------------------
insert into feeds_location (location_id, feeds_id) values(1, 1);
insert into feeds_location (location_id, feeds_id) values(1, 2);
---------------------------------------------------------------------
--------------------------feeds_student--------------------------------
insert into feeds_student (student_id, feeds_id) values(1, 1);
insert into feeds_student (student_id, feeds_id) values(1, 2);
---------------------------------------------------------------------
--------------------------subjects_room--------------------------------
insert into subjects_room (room_id, subjects_id) values(1, 1);
insert into subjects_room (room_id, subjects_id) values(1, 2);
---------------------------------------------------------------------
--------------------------exams--------------------------------
insert into exams (allowed_aid, end_time, exam_type, name, start_time) values('No aid', '2012-03-09 11:05:33', 'Writtend', 'Jave ex', '2012-03-09 11:05:32');
insert into exams (allowed_aid, end_time, exam_type, name, start_time) values('No aid', '2012-03-09 11:05:33', 'Writtend', 'C++ ex', '2012-03-09 11:05:32');
insert into exams (allowed_aid, end_time, exam_type, name, start_time) values('No aid', '2012-03-09 11:05:33', 'Writtend', 'Math ex', '2012-03-09 11:05:32');
---------------------------------------------------------------------
--------------------------exam_subjects--------------------------------
insert into exam_subjects (subjects_id, exams_id) values(1, 1);
insert into exam_subjects (subjects_id, exams_id) values(2, 2);
---------------------------------------------------------------------
--------------------------courses_representatives--------------------------------
insert into courses_representatives (courses_id, representatives_id) values (1, 1);
insert into courses_representatives (courses_id, representatives_id) values (1, 2);
insert into courses_representatives (courses_id, representatives_id) values (2, 3);

INSERT INTO games (id, category, title) VALUES (1, 'PLATFORM', 'Super Mario Galaxy');
INSERT INTO games (id, category, title) VALUES (2, 'BEAT_EM_UP', 'MadWorld');
INSERT INTO games (id, category, title) VALUES (3, 'LIFE_SIMULATION', 'Little Kings Story');
INSERT INTO games (id, category, title) VALUES (4, 'PARTY', 'Wario Ware: Smooth Moves');
INSERT INTO games (id, category, title) VALUES (5, 'FIGHTING', 'Super Smash Bros. Brawl');
INSERT INTO games (id, category, title) VALUES (6, 'ACTION_ADVENTURE', 'No More Heroes');
INSERT INTO games (id, category, title) VALUES (7, 'RACING', 'Mario Kart Wii');
INSERT INTO games (id, category, title) VALUES (8, 'PARTY', 'Mario Party 8');
INSERT INTO games (id, category, title) VALUES (9, 'THIRD_PERSON_SHOOTER', 'Resident Evil 4');
INSERT INTO games (id, category, title) VALUES (10, 'BEAT_EM_UP', 'Marvel Super Hero Squad');
INSERT INTO games (id, category, title) VALUES (11, 'SPORTS', 'Mario & Sonic at the Olympic Games');
INSERT INTO games (id, category, title) VALUES (12, 'RAIL_SHOOTER', 'The House of the Dead: Overkill');
INSERT INTO games (id, category, title) VALUES (13, 'SPORTS', 'Wii Sports');

INSERT INTO games (id, category, title) VALUES (14, 'FIRST_PERSON_SHOOTER', 'Left 4 Dead');
INSERT INTO games (id, category, title) VALUES (15, 'ACTION_ADVENTURE', 'Mirrors Edge');
INSERT INTO games (id, category, title) VALUES (16, 'FIRST_PERSON_SHOOTER', 'Call of Duty 4: Modern Warfare');
INSERT INTO games (id, category, title) VALUES (17, 'SPORTS', 'FIFA 09');
INSERT INTO games (id, category, title) VALUES (18, 'SPORTS', 'FIFA 11');
INSERT INTO games (id, category, title) VALUES (19, 'FIGHTING', 'Super Street Fighter IV');
INSERT INTO games (id, category, title) VALUES (20, 'ACTION_ADVENTURE', 'Batman: Arkham Asylum');
INSERT INTO games (id, category, title) VALUES (21, 'SPORTS', 'NHL 10');
INSERT INTO games (id, category, title) VALUES (22, 'THIRD_PERSON_SHOOTER', 'Alan Wake');
INSERT INTO games (id, category, title) VALUES (23, 'FIRST_PERSON_SHOOTER', 'Halo: Reach');
INSERT INTO games (id, category, title) VALUES (24, 'FIRST_PERSON_SHOOTER', 'Call of Duty: Black Ops');
INSERT INTO games (id, category, title) VALUES (25, 'FIRST_PERSON_SHOOTER', 'Borderlands');

INSERT INTO games (id, category, title) VALUES (26, 'PLATFORM', 'Ratchet & Clank: Size Matters');
INSERT INTO games (id, category, title) VALUES (27, 'SURVIVAL_HORROR', 'Silent Hill: Origins');
INSERT INTO games (id, category, title) VALUES (28, 'ACTION_ADVENTURE', 'God of War');
INSERT INTO games (id, category, title) VALUES (29, 'ROLE_PLAYING_GAME', 'Shin Megami Tensei: Persona 4');
INSERT INTO games (id, category, title) VALUES (30, 'THIRD_PERSON_SHOOTER', 'Resident Evil 4');

INSERT INTO games (id, category, title) VALUES (31, 'FIRST_PERSON_SHOOTER', 'Left 4 Dead');
INSERT INTO games (id, category, title) VALUES (32, 'THIRD_PERSON_SHOOTER', 'Gears of War 2');
INSERT INTO games (id, category, title) VALUES (33, 'SPORTS', 'FIFA 09');
INSERT INTO games (id, category, title) VALUES (34, 'FIGHTING', 'Tekken 6');
INSERT INTO games (id, category, title) VALUES (35, 'FIRST_PERSON_SHOOTER', 'Halo 3');
INSERT INTO games (id, category, title) VALUES (36, 'FIRST_PERSON_SHOOTER', 'The Orange Box');
INSERT INTO games (id, category, title) VALUES (37, 'PUZZLE_PLATFORM', 'Portal 2');
INSERT INTO games (id, category, title) VALUES (38, 'FIGHTING', 'Marvel vs. Capcom 3: Fate of Two Worlds');
INSERT INTO games (id, category, title) VALUES (39, 'ACTION_ADVENTURE', 'Majin and the Forsaken Kingdom');
INSERT INTO games (id, category, title) VALUES (40, 'ACTION_ADVENTURE', 'Lego Indiana Jones: The Original Adventures / Kung Fu Panda');
INSERT INTO games (id, category, title) VALUES (41, 'DIGITAL_PET', 'Kinectimals');
INSERT INTO games (id, category, title) VALUES (42, 'MUSIC', 'Dance Central');

INSERT INTO games (id, category, title) VALUES (43, 'FIRST_PERSON_SHOOTER', 'Left 4 Dead');
INSERT INTO games (id, category, title) VALUES (44, 'ACTION_ADVENTURE', 'Lego Indiana Jones: The Original Adventures / Kung Fu Panda');
INSERT INTO games (id, category, title) VALUES (45, 'FIRST_PERSON_SHOOTER', 'Call of Duty 4: Modern Warfare');
INSERT INTO games (id, category, title) VALUES (46, 'SPORTS', 'FIFA 09');
INSERT INTO games (id, category, title) VALUES (47, 'FIRST_PERSON_SHOOTER', 'Halo 3');
INSERT INTO games (id, category, title) VALUES (48, 'FIRST_PERSON_SHOOTER', 'Left 4 Dead');
INSERT INTO games (id, category, title) VALUES (49, 'SURVIVAL_HORROR', 'Dead Rising');
INSERT INTO games (id, category, title) VALUES (50, 'FIRST_PERSON_SHOOTER', 'Call of Duty: Modern Warfare 2');

INSERT INTO games (id, category, title) VALUES (51, 'FIRST_PERSON_SHOOTER', 'Left 4 Dead');
INSERT INTO games (id, category, title) VALUES (52, 'ACTION_ROLEPLAYING', 'Fable II');
INSERT INTO games (id, category, title) VALUES (53, 'REAL_TIME_TACTICS', 'Tom Clancys EndWar');
INSERT INTO games (id, category, title) VALUES (54, 'FIRST_PERSON_SHOOTER', 'Call of Duty 4: Modern Warfare');
INSERT INTO games (id, category, title) VALUES (55, 'FIRST_PERSON_SHOOTER', 'Call of Duty: Black Ops');
INSERT INTO games (id, category, title) VALUES (56, 'SPORTS', 'FIFA 09');
INSERT INTO games (id, category, title) VALUES (57, 'SPORTS', 'FIFA 10');
INSERT INTO games (id, category, title) VALUES (58, 'SPORTS', 'FIFA 11');
INSERT INTO games (id, category, title) VALUES (59, 'FIRST_PERSON_SHOOTER', 'Halo: Reach');
INSERT INTO games (id, category, title) VALUES (60, 'FIRST_PERSON_SHOOTER', 'Halo 3: ODST');
INSERT INTO games (id, category, title) VALUES (61, 'FIRST_PERSON_SHOOTER', 'Halo: Reach');
INSERT INTO games (id, category, title) VALUES (62, 'LIFE_SIMULATION', 'Viva Pinata');
INSERT INTO games (id, category, title) VALUES (63, 'FIRST_PERSON_SHOOTER', 'Borderlands');
INSERT INTO games (id, category, title) VALUES (64, 'PUZZLE_PLATFORM', 'Portal 2');


INSERT INTO consoles (id, name, locker) VALUES (1, 'Nintendo Wii', 1);
INSERT INTO consoles (id, name, locker) VALUES (2, 'Xbox 360', 2);
INSERT INTO consoles (id, name, locker) VALUES (3, 'Playstation 2', 2);
INSERT INTO consoles (id, name, locker) VALUES (4, 'Xbox 360', 3);
INSERT INTO consoles (id, name, locker) VALUES (5, 'Xbox 360', 4);
INSERT INTO consoles (id, name, locker) VALUES (6, 'Playstation 3', 4);
INSERT INTO consoles (id, name, locker) VALUES (7, 'Xbox 360', 5);

INSERT INTO games_consoles (consoles_id, games_id) VALUES (1,1);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (1,2);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (1,3);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (1,4);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (1,5);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (1,6);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (1,7);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (1,8);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (1,9);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (1,10);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (1,11);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (1,12);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (1,13);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (2,14);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (2,15);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (2,16);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (2,17);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (2,18);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (2,19);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (2,20);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (2,21);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (2,22);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (2,23);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (2,24);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (2,25);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (3,26);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (3,27);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (3,28);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (3,29);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (3,30);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (4,31);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (4,32);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (4,33);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (4,34);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (4,35);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (4,36);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (4,37);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (4,38);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (4,39);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (4,40);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (4,41);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (4,42);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (5,43);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (5,44);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (5,45);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (5,46);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (5,47);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (5,48);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (5,49);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (5,50);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,51);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,52);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,53);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,54);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,55);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,56);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,57);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,58);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,59);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,60);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,61);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,62);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,63);
INSERT INTO games_consoles (consoles_id, games_id) VALUES (7,64);

INSERT INTO loans (id, loan_date, return_date) VALUES (1, '2012-03-09 11:05:32', '2012-03-10 11:05:32');
INSERT INTO loans (id, loan_date, return_date) VALUES (2, '2012-04-09 11:05:32', '2012-04-11 11:05:32');
INSERT INTO loans (id, loan_date, return_date) VALUES (3, '2012-05-09 11:05:32', '2012-05-12 11:05:32');
INSERT INTO loans (id, loan_date, return_date) VALUES (4, '2012-06-09 11:05:32', '2012-06-13 11:05:32');

INSERT INTO loans_consoles (loan_id, console_id) VALUES (1, 2);
INSERT INTO loans_consoles (loan_id, console_id) VALUES (2, 3);
INSERT INTO loans_consoles (loan_id, console_id) VALUES (3, 4);
INSERT INTO loans_consoles (loan_id, console_id) VALUES (4, 1);

INSERT INTO students_loans (student_id, loan_id) VALUES (1, 1);
INSERT INTO students_loans (student_id, loan_id) VALUES (1, 2);
INSERT INTO students_loans (student_id, loan_id) VALUES (2, 3);
INSERT INTO students_loans (student_id, loan_id) VALUES (3, 4);






