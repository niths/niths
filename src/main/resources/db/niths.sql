-- $ mysql < niths-sql.sql

CREATE DATABASE niths;

USE niths;

CREATE TABLE committees (
  id INT PRIMARY KEY NOT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(50) NOT NULL,
  leader INT NOT NULL
);

CREATE TABLE committee_events (
  id INT PRIMARY KEY NOT NULL,
  name VARCHAR(50) NOT NULL,
  committee_id INT NOT NULL,
  description VARCHAR(500) NOT NULL,
  time DATE NOT NULL
);

CREATE TABLE students (
  id INT PRIMARY KEY NOT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(500) NOT NULL,
  course_id INT NOT NULL
);

CREATE TABLE courses (
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(500) NOT NULL
);


ALTER TABLE students
ADD CONSTRAINT course_id FOREIGN KEY (course_id)
REFERENCES courses (id);

ALTER TABLE committees
ADD CONSTRAINT leader_id FOREIGN KEY (leader)
REFERENCES students (id);

ALTER TABLE committee_events
ADD CONSTRAINT committee_id FOREIGN KEY (committee_id)
REFERENCES committees (id);

















##########################################################333
OLD:
########################################################33
CREATE DATABASE Niths;

USE Niths;

CREATE TABLE Committees (
  id INT PRIMARY KEY NOT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(50) NOT NULL,
  leader INT NOT NULL
);

CREATE TABLE CommitteeEvents (
  id INT PRIMARY KEY NOT NULL,
  name VARCHAR(50) NOT NULL,
  committee_id INT,
  description VARCHAR(500) NOT NULL,
  time DATE NOT NULL
);

CREATE TABLE Students (
  id INT PRIMARY KEY NOT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(500) NOT NULL,
  course_id INT NOT NULL
);

CREATE TABLE Courses (
  id INT PRIMARY KEY NOT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(500) NOT NULL
);

ALTER TABLE Committees
ADD CONSTRAINT leader_id FOREIGN KEY (leader)
REFERENCES Students (id);

ALTER TABLE CommitteeEvents
ADD CONSTRAINT committee_id FOREIGN KEY (committee_id)
REFERENCES Committees (id);

ALTER TABLE Students
ADD CONSTRAINT course_id FOREIGN KEY (course_id)
REFERENCES Courses (id);
