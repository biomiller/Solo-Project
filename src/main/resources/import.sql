SET REFERENTIAL_INTEGRITY FALSE;

TRUNCATE TABLE USER;
TRUNCATE TABLE DECK;
TRUNCATE TABLE EVENT;
TRUNCATE TABLE EVENT_USER;

SET REFERENTIAL_INTEGRITY TRUE;

ALTER TABLE USER ALTER COLUMN USERID RESTART WITH 1;
ALTER TABLE DECK ALTER COLUMN DECKID RESTART WITH 1;
ALTER TABLE EVENT ALTER COLUMN EVENTID RESTART WITH 1;


INSERT INTO USER(name, email, password) VALUES ('Owen', 'dummyemail@mail.com', 'password');
INSERT INTO DECK(name,format,cards) VALUES ('DraftM1901', 'M19', '1 Angel of the Dawn, 1 Boggart Brute, 2 Cavalry Drillmaster, 1 Dismissive Pyromancer, 1 Gallant Cavalry, 1 Goblin Instigator, 1 Goblin Motivator, 1 Goblin Trashmaster, 2 Guttersnipe, 1 Inferno Hellion, 1 Lena, Selfless Champion, 1 Leonin Vanguard, 1 Oreskos Swiftclaw, 1 Viashino Pyromancer, 1 Volley Veteran, 1 Act of Treason, 1 Take Vengeance, 1 Tormenting Voice, 1 Shock, 2 Sure Strike, 10 Mountain, 7 Plains');
INSERT INTO DECK(name,format,cards) VALUES ('DraftM1902', 'M19', '1 Boggart Brute, 2 Bristling Boar, 2 Colossal Dreadmaw, 1 Daggerback Basilisk, 1 Demanding Dragon, 1 Draconic Disciple, 1 Druid of the Cowl, 1 Giant Spider, 1 Hungering Hydra, 2 Onakke Ogre, 1 Reclamation Sage, 2 Viashino Pyromancer, 1 Fiery Finish, 2 Rabid Bite, 1 Titanic Growth, 1 Oakenform, 1 Prodigious Growth, 1 Talons of Wildwood, 10 Forest, 7 Mountain' );
-- INSERT INTO USER_DECK(user_userId,decks_deckId) VALUES(1,1)
update DECK set userId = 1 where deckId = 1;
update DECK set userId = 1 where deckId = 2;

INSERT INTO USER(name, email, password) VALUES ('John', 'fakeemail@mail.com', '1234');
INSERT INTO DECK(name,format,cards) VALUES ('DraftM1903', 'M19', '1 Angel of the Dawn, 1 Bone Dragon, 1 Diamond Mare, 1 Diregraf Ghoul, 2 Gallant Cavalry, 1 Pegasus Courser, 1 Phylactery Lich, 1 Skymarch Bloodletter, 1 Trusty Packbeast, 1 Two-Headed Zombie, 2 Walking Corpse, 1 Duress, 1 Lich''s Caress, 1 Rise from the Grave, 2 Take Vengeance, 1 Murder, 1 Strangling Spores, 1 Chaos Wand, 2 Explosive Apparatus, 1 Manalith, 7 Plains, 9 Swamp');
INSERT INTO DECK(name,format,cards) VALUES ('DraftM1904', 'M19', '1 Aven Wind Mage, 1 Aviation Pioneer, 1 Cavalry Drillmaster, 1 Gallant Cavalry, 1 Gearsmith Guardian, 1 Herald of Faith, 2 Omenspeaker, 2 Pegasus Courser, 1 Remorseful Cleric, 1 Scholar of Stars, 2 Star-Crowned Stag, 1 Valiant Knight, 1 Divination, 1 Sift, 1 Disperse, 1 Mighty Leap, 1 Totally Lost, 1 Marauder''s Axe, 1 Knightly Valor, 1 Luminous Bonds, 8 Island, 9 Plains');
UPDATE DECK SET userId = 2 WHERE deckId = 3;
UPDATE DECK SET userId = 2 WHERE deckId = 4;

INSERT INTO EVENT(name, format, location, eventDate) VALUES ('M19 Prerelease', 'Sealed', 'Rogue Gaming', '2018-07-08');
INSERT INTO EVENT_USER (eventId, userId) VALUES (1,1);
INSERT INTO EVENT_USER (eventId, userId) VALUES (1,2);

INSERT INTO EVENT(name, format, location, eventDate) VALUES ('M19 Draft', 'Limited', 'Rogue Gaming', '2018-07-09');
INSERT INTO EVENT_USER (eventId, userId) VALUES (2,2);


