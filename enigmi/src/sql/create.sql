create table enigmi
(
    id bigserial
        constraint enigmi_pk
            primary key,
    autore varchar not null,
    tipo varchar not null,
    tipo_specifico varchar not null,
    titolo varchar not null,
    testo text[] not null,
    soluzione text[] not null
);

