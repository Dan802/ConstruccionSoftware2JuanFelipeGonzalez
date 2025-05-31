create database cs2vet;

use cs2vet;

insert into person (age, document, name, role) values 
(34, 12345678, "Mariano Ernesto", "ADMINISTRADOR"),
(24, 12345679, "Alfonso Rodriguez", "VETERINARIO"), 
(14, 12345670, "Amparo González", "VENDEDOR"),
(4, 101, "Don Ramón", "DUENO");

insert into login (login_id, person_id, user_name, password) values 
(1, 12345678, "mariano", "mariano1234"),
(2, 12345679, "alfonso", "alfonso1234"), 
(3, 12345670, "amparo", "amparo1234");

insert into pet (age, weight, document_owner, pet_id, description, name, breed, specie) values
(4, 23, 101, 1, "Mascota bonita", "Poppins", "criolla", "gata");