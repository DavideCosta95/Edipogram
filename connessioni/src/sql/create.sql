create table connessioni
(
    id bigserial
        constraint connessioni_pk
            primary key,
    utente varchar not null,
    tipo varchar not null
);
