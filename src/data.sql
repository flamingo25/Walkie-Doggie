/*  USER  */

/* Userprofile erstellen */
INSERT INTO userprofile (address, city, dayOfBirth, email, firstname, phone, lastname, version, zip, image_id) VALUES ("Alte Poststraße 147", "Graz", "1990-12-25", "felix@hauer.at", "Felix", 06641234567, "Hauer", 0, 8020, null);
INSERT INTO userprofile (address, city, dayOfBirth, email, firstname, phone, lastname, version, zip, image_id) VALUES ("Eckerstraße 30i/3/10", "Graz", "1980-03-14", "sandra@maier.at", "Sandra", 06647654321, "Maier", 0, 8020, null);
INSERT INTO userprofile (address, city, dayOfBirth, email, firstname, phone, lastname, version, zip, image_id) VALUES ("Alte Poststraße 147", "Graz", "1983-08-18", "beate@neuer.at", "Beate", 066434001193, "Neuer", 0, 8020, null);
INSERT INTO userprofile (address, city, dayOfBirth, email, firstname, phone, lastname, version, zip, image_id) VALUES ("Grazer Straße 18/3", "Weiz", "1994-09-01", "tina@lustig.at", "Tina", 06649482059, "Lustig", 0, 8160, null);
INSERT INTO userprofile (address, city, dayOfBirth, email, firstname, phone, lastname, version, zip, image_id) VALUES ("Europaplatz 12", "Graz", "1981-04-10", "sven@neubauer.at", "Sven", 067629384578, "Neubauer", 0, 8010, null);
INSERT INTO userprofile (address, city, dayOfBirth, email, firstname, phone, lastname, version, zip, image_id) VALUES ("Weizer Straße 17b", "Gleisdorf", "1991-11-28", "birgit@huber.at", "Birgit", 066022948294, "Huber", 0, 8200, null);

/* User erstellen */
INSERT INTO users (enabled, password, username, userProfile_id) VALUES (1, "password", "admin", 1);
INSERT INTO users (enabled, password, username, userProfile_id) VALUES (1, "password", "user", 2);
INSERT INTO users (enabled, password, username, userProfile_id) VALUES (1, "password", "employee", 3);
INSERT INTO users (enabled, password, username, userProfile_id) VALUES (1, "password", "tina", 4);
INSERT INTO users (enabled, password, username, userProfile_id) VALUES (1, "password", "sven", 5);
INSERT INTO users (enabled, password, username, userProfile_id) VALUES (1, "password", "birgit", 6);

/* Userrollen zuweisen */
INSERT INTO user_roles (role) VALUES ("ROLE_USER");
INSERT INTO user_roles (role) VALUES ("ROLE_EMPLOYEE");
INSERT INTO user_roles (role) VALUES ("ROLE_ADMIN");

/* User ADMIN */
INSERT INTO users_user_roles (users_id, userRoles_id) VALUES (1, 1);
INSERT INTO users_user_roles (users_id, userRoles_id) VALUES (1, 2);
INSERT INTO users_user_roles (users_id, userRoles_id) VALUES (1, 3);
/* User EMPLOYEE */
INSERT INTO users_user_roles (users_id, userRoles_id) VALUES (3, 1);
INSERT INTO users_user_roles (users_id, userRoles_id) VALUES (3, 2);
/* User USER */
INSERT INTO users_user_roles (users_id, userRoles_id) VALUES (2, 1);
/* User TINA */
INSERT INTO users_user_roles (users_id, userRoles_id) VALUES (4, 1);
/* User SVEN */
INSERT INTO users_user_roles (users_id, userRoles_id) VALUES (5, 1);
INSERT INTO users_user_roles (users_id, userRoles_id) VALUES (5, 2);
/* User BIRGIT */
INSERT INTO users_user_roles (users_id, userRoles_id) VALUES (6, 1);
INSERT INTO users_user_roles (users_id, userRoles_id) VALUES (6, 2);





#################################################################

/*    IMPFUNGEN    */

/* Auffüllen */
/* Gemischte Impfungen: */
INSERT INTO vaccination (name, version) VALUES ("Tollwut", 0);
/* Impfungen für Hunde: */
INSERT INTO vaccination (name, version) VALUES ("Staupe", 0);
INSERT INTO vaccination (name, version) VALUES ("Parvovirose", 0);
INSERT INTO vaccination (name, version) VALUES ("Leptospirose", 0);
INSERT INTO vaccination (name, version) VALUES ("H.C.C", 0);
INSERT INTO vaccination (name, version) VALUES ("Zwingerhusten", 0);
INSERT INTO vaccination (name, version) VALUES ("Boreliose", 0);
INSERT INTO vaccination (name, version) VALUES ("Canines Herpesvirus", 0);
/* Impfungen für Katzen: */
INSERT INTO vaccination (name, version) VALUES ("Katzenseuche", 0);
INSERT INTO vaccination (name, version) VALUES ("Katzenschnupfen", 0);
INSERT INTO vaccination (name, version) VALUES ("FeLV", 0);
INSERT INTO vaccination (name, version) VALUES ("FIV", 0);

#################################################################

/*   VERTRÄGLICHKEITEN   */

/* Auffüllen */
INSERT INTO compatibility (name, version) VALUES ("Hunde", 0);
INSERT INTO compatibility (name, version) VALUES ("Katzen", 0);
INSERT INTO compatibility (name, version) VALUES ("Kinder", 0);

#################################################################


/*   TIERARTEN    */

/* Auffüllen */
INSERT INTO species (name, version) VALUES ("Hund", 0);
INSERT INTO species (name, version) VALUES ("Katze", 0);
INSERT INTO species (name, version) VALUES ("Kleintier", 0);

#################################################################

/*   TIERE        */

/* Auffüllen */
/* Bessie: */
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (2, "Mischling", 1, 1, "Schwarz", "Bessie ist eine wundervolle, freundliche Hundedame, die es liebt, bschäftigt zu werden. Doch auch wenn sie längere Zeit alleine bleiben muss, ist das kein Problem für sie! Sie liebt Tiere jeglicher Art und verzaubert mit ihrem Wesen jeden Menschen.", "Weiblich", "Bessie", 0, 1);
/*Cosmo: */
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (5, "Berner-Sennenhund", 0, 1, "Schwarz, Weiß", "Cosmo ist einsehr aktiver, freundlicher Berner-Sennenhund, derlange Spaziergänge und ausgedehnte Spieleinheiten liebt.Mit seiner freundlichen Art trotz seiner Größe macht ersich äußerst Beliebt.", "Männlich", "Cosmo", 0, 1);
/*Snorre: */
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (8, "Mischling", 1, 1, "Goldbraun", "Snorre ist ein ca. 8 jähriger,sehr lernwilliger junger Mischling, welcher bereits einevielzahl von Tricks beherrscht. Er ist stets offen für neuesund liebt es, draußen zu toben.", "Männlich", "Snorre", 0, 1);
/*Elli: */
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (13, "Westie", 0, 0, "Weiß", "Elli ist eine schon ältereHundedame, die ganz besonders verschmust ist. Durch ihre beidseitige Blindheitist ihr Geruchssinn sehr ausgeprägt und sie findetsich sehr schnell in ihrer Umgebung zurecht. Dieses 'Handicap'hält sie auch nicht davon ab, längere Spaziergänge zu unternehmen.", "Weiblich", "Elli", 0, 1);
/*Jimmy: */
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (4, "Terrier-Mix", 1, 1, "Schwarz, Grau, Braun", "Jimmy ist ein Mischling,bei dem man schon am Temperament merkt, dass ein Terriermitspielt. Trotz dieser 'Sturheit' ist er sehr liebenswürdigund zutraulich. Neben langen Spaziergängen liebt er es, mit anderen Tieren zu spielen.", "Männlich", "Jimmy", 0, 1);
/*Olga: */
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (4, "Meerschweinchen", 1, 1, "Schwarz", "Olga wird dieses Meerschweinchengenannt, welches auf ein liebevolles Zuhause wartet. Sie kennt sowohl Hunde und Katzen, als auch Hühner und Ziegen. Besonders gerne isst sie Gemüse.", "Weiblich", "Olga", 0, 3);
/*Garfield: */
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (12, "Kater", 1, 1, "Orange", "Garfield siehtgenau so aus wie der berühmte Kater aus dem Comic, under isst genauso gerne (auch Lasagne). Er ist etwas schwierig,doch mit Zeit und Liebe wird er immer zutraulicher. Er wartetnur noch auf 'SEINE' Familie.", "Männlich", "Garfield", 0, 2);
/*Mogli: */
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (2, "Kater", 1, 1, "Schwarz", "Mogli ist ein kleinesGlückskind. Er wurde mitten auf der Autobahn gefunden und zu unsgebracht. Nach ersten Checks und der Erstbehandlungwurde er von uns liebevoll aufgezogen. Damit sein Glück perfekt wird fehlt nur noch eine liebevolle Familie.", "Männlich", "Mogli", 0, 2);
/*Senta: */
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (10, "Mischling", 0, 1, "Braun","Senta ist eine Mischlingsdame, welche auch gerne mal alleine bleiben kann. Sie freut sich allerdings immer, wenn man sich mit ihr beschäftigt, auch wenn es nur für kurze Zeit ist. Andere Hunde und Katzen mag sie nicht so gerne, mit Kindern hat sie absolut keine Probleme.", "Weiblich", "Senta", 0, 1);
/*Wilma: */
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (2, "Katze", 1, 1, "Getigert", "Wilma ist eine Katze, welche immer auf der Suche nach Nähe zu 'ihrer' Familie ist. Sie wartet auf den perfekten Platz für immer.", "Weiblich", "Wilma", 0, 1);
/*Banja: */
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (6, "Westie", 0, 0, "Weiß", "Banja ist eine sehr verspielte Westie-Dame, welche ebenso verschmust wie aufgeweckt ist.Sie liebt Spaziergänge über alles, doch sie würde auch stundenlange Streicheleinheiten über sich ergehen lassen.", "Weiblich", "Banja", 0, 1);
/*Cookie: */
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (3, "Australian Shepherd", 0, 0, "Red Merle", "Cookie ist eine wunderschöne Australian Shepherd Dame, welche sehr talentiert im Hundesport ist. Sie braucht einen Besitzer, welcher sich gerne aktiv mit ihr beschäftigt.", "Weiblich", "Cookie", 0, 1);
/*Aramis: */ 
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (6, "Kleinspitz", 0, 0, "Schwarz", "Aramis ist ein Kleinspitz, der sich gerne mit Menschen und Tieren jeglicher Art beschäftigt. Er liebt jede Art von Beschäftigung, kann sichaber auch gut selbst beschäftigen.", "Männlich", "Aramis", 0, 1);
/*Mailo: */
INSERT INTO animals (age, breed, castrated, chipped, color, description, gender, name, version, species_id) VALUES (5, "Jack Russell Terrier", 0, 0, "Tricolor Weiß, Braun, Schwarz", "Mailo ist ein Jack Russell Terrier, der nicht nur optisch ein Traumhund ist. Durch sein offenes Wesen kommt er mit jedem klar, doch man sollte seinen innerlichen Terrier nicht außer Acht lassen.", "Männlich", "Mailo", 0, 1);

#################################################################

/*    IMPFUNGEN DER TIERE   */

/* Auffüllen */
/* Tier 1 - Hund Bessie*/
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (1, 1);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (1, 2);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (1, 3);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (1, 4);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (1, 5);
/* Tier 2 - Hund Cosmo*/
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (2, 1);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (2, 4);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (2, 5);
/* Tier 3 - Hund Snorre*/
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (3, 1);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (3, 2);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (3, 3);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (3, 4);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (3, 5);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (3, 8);
/* Tier 4 - Hund Elli*/ 
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (4, 1);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (4, 8);
/* Tier 5 - Meerschweinchen  Olga*/
/* Tier 6 -Kater Garfield */
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (6, 1);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (6, 9);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (6, 12);
/* Tier 7 - Kater Mogli */
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (7, 9);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (7, 10);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (7, 11);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (7, 12);
/* Tier 8 - Hund Senta*/
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (8, 1);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (8, 4);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (8, 5);
/* Tier 9 - Katze Wilma */
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (9, 1);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (9, 9);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (9, 10);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (9, 11);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (9, 12);
/* Tier 10 - Hund Banja */
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (10, 1);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (10, 3);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (10, 4);
/* Tier 11 - Hund Cookie */
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (11, 1);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (11, 3);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (11, 4);
/* Tier 12 - Hund Aramis */
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (12, 1);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (12, 2);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (12, 3);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (12, 4);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (12, 5);
/* Tier 13 - Hund Mailo */
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (13, 1);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (13, 2);
INSERT INTO animals_vaccination (animals_id, vaccinations_id) VALUES (13, 6);

#################################################################

/*   VERTRÄGLICHKEITEN DER TIERE   */
/* Löschen */
#DELETE FROM animals_compatibility;
/* Auffüllen */
/* Tier 1 - Hund Bessie*/
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (1, 1);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (1, 2);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (1, 3);
/* Tier 2- Hund Cosmo */
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (2, 1);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (2, 2);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (2, 3);
/* Tier 3 - Hund Jimmy*/
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (3, 1);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (3, 3);
/* Tier 4 - Hund Elli*/
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (4, 1);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (4, 2);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (4, 3);
/* Tier 5 - Meerschweinchen Olga*/
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (5, 1);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (5, 2);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (5, 3);
/* Tier 6 - Kater Garfield */
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (6, 1);
/* Tier 7 - Kater Mogli*/
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (7, 1);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (7, 2);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (7, 3);
/* Tier 8 - Hund Senta */
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (8, 3);
/* Tier 9 - Katze Wilma*/
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (9, 1);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (9, 2);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (9, 3);
/* Tier 10 - Hund Banja */
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (10, 1);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (10, 3);
/* Tier 11 - Hund Cookie */
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (11, 1);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (11, 2);
/* Tier 12 - Hund Aramis */
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (12, 1);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (12, 2);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (12, 3);
/* Tier 13 - Hund Mailo */
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (13, 1);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (13, 2);
INSERT INTO animals_compatibility (animals_id, compatibilities_id) VALUES (13, 3);