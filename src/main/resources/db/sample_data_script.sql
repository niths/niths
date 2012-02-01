USE niths;

INSERT INTO courses (name, description) VALUES("Mobil apputvikling", "Mobil apputvikling har blitt et stort forretningsområde, der både profesjonelle og amatører kan tjene gode penger på applikasjonene sine.");
INSERT INTO courses (name, description) VALUES("Programmering", "I dag brukes IT og Internett i alt fra handel, kommunikasjon, overvåking og spill til betaling.");
INSERT INTO courses (name, description) VALUES("Spillprogrammering", "Du som søker dette studiet, ønsker å jobbe med avansert programmering og har interesse for simulerings- og spillopplevelser");
INSERT INTO courses (name, description) VALUES("Spilldesign", "En spilldesigner spesialiserer seg på å skape interaktive opplevelser, og har ansvar for spillets regler og mekanikker. ");
INSERT INTO courses (name, description) VALUES("3D-grafikk", "3D-modeller benyttes blant annet i film, spill, reklame, kunst, design og visualisering.");
INSERT INTO courses (name, description) VALUES("Interaktivt design", "Det er i dag viktig å ha brukervennlige webløsninger som tilfredsstiller brukernes behov og forventninger.");
INSERT INTO courses (name, description) VALUES("Digital markedsføring", "En ny generasjon av sosiale elektroniske nettverk gjør Internett til en av våre viktigste kommunikasjonskanaler.");
INSERT INTO courses (name, description) VALUES("E-business", "Det er i dag mangel på høgskoleutdannede personer som har kunnskap om teknologiske forretningsløsninger og hvordan disse best fungerer.");
INSERT INTO courses (name, description) VALUES("Industribachelor", "Våre studenter har nå mulighet for å kombinere en jobb i IT-industrien med en bachelor ved NITH. Vi har inngått et samarbeid med to av landets største konsulent-selskaper, Accenture og Avanade.");

INSERT INTO students (first_name, last_name) VALUES("Andre", "Dockinsen");
INSERT INTO students (first_name, last_name) VALUES("Bendik", "Rostad");
INSERT INTO students (first_name, last_name) VALUES("Liv", "Kolås");
INSERT INTO students (first_name, last_name) VALUES("Øyvind", "Ødegård");

INSERT INTO committees (name) VALUES("Linx utvalget");
INSERT INTO committees (name) VALUES("Microsoft utvalget");
INSERT INTO committees (name) VALUES("KIT");
INSERT INTO committees (name) VALUES("MDF");


INSERT INTO committee_events (name) VALUES("awesome");
INSERT INTO committee_events (name) VALUES("super");
INSERT INTO committee_events (name) VALUES("asdasd");
INSERT INTO committee_events (name) VALUES("23asd");
INSERT INTO committee_events (name) VALUES("zjkzhck");
INSERT INTO committee_events (name) VALUES("asdokihju");


insert into committees_committee_events (committees_id, events_id) values(1, 1);
insert into committees_committee_events (committees_id, events_id) values(1, 2);
insert into committees_committee_events (committees_id, events_id) values(1, 3);
insert into committees_committee_events (committees_id, events_id) values(1, 4);
insert into committees_committee_events (committees_id, events_id) values(2, 5);
insert into committees_committee_events (committees_id, events_id) values(2, 6);