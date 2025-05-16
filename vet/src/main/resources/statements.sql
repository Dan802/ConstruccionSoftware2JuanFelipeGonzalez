create database cs2vet;

insert into person (age, document, name, role) values 
(34, 12345678, "Mariano Ernesto", "Administrador"),
(24, 12345679, "Alfonso Rodriguez", "Veterinario"), 
(14, 12345670, "Amparo González", "Vendedor"),
(4, 101, "Don Ramón", "Dueño");

insert into login (login_id, person_id, user_name, password) values 
(1, 12345678, "mariano", "mariano1234"),
(2, 12345679, "alfonso", "alfonso1234"), 
(3, 12345670, "amparo", "amparo1234");

insert into pet (age, weight, document_owner, pet_id, description, name, race, specie) values
(4, 23, 101, 1, "Mascota bonita", "Poppins", "criolla", "gata");