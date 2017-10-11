CREATE TABLE IF NOT EXISTS USER(ID INT PRIMARY KEY NOT NULL, CREATION_DATE TIMESTAMP NOT NULL, NAME VARCHAR(255) NOT NULL, EMAIL VARCHAR(255) NOT NULL, PASSWORD VARCHAR(255) NOT NULL);

CREATE TABLE IF NOT EXISTS HIGHSCORE(ID INT PRIMARY KEY NOT NULL, CREATION_DATE TIMESTAMP NOT NULL, USER_ID INT NOT NULL REFERENCES USER(ID), DATE_OF_HIGHSCORE TIMESTAMP NOT NULL, POINTS INT NOT NULL, DURATION_FOR_HIGHSCORE INT NOT NULL);

CREATE TABLE IF NOT EXISTS SETTINGS(ID INT PRIMARY KEY NOT NULL, CREATION_DATE TIMESTAMP NOT NULL, USER_ID INT NOT NULL REFERENCES USER(ID), PRIVATE_SETTINGS VARCHAR(255) NOT NULL);
