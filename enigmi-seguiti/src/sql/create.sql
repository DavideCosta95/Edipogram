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

create table connessioni
(
    id bigserial
        constraint connessioni_pk
            primary key,
    utente varchar not null,
    tipo varchar not null
);

CREATE MATERIALIZED VIEW enigmi_seguiti
AS
select c.utente AS utente,
       e.id AS id_enigma,
       e.autore AS autore_enigma,
       e.tipo AS tipo_enigma,
       e.tipo_specifico AS tipo_specifico_enigma,
       e.titolo AS titolo_enigma,
       e.testo AS testo_enigma
from connessioni c JOIN enigmi e ON c.tipo = e.tipo;

CREATE UNIQUE INDEX ON enigmi_seguiti (utente, id_enigma);

CREATE OR REPLACE FUNCTION refresh_enigmi_seguiti()
    RETURNS TRIGGER LANGUAGE plpgsql
AS $$
BEGIN
    REFRESH MATERIALIZED VIEW CONCURRENTLY enigmi_seguiti;
    RETURN NULL;
END $$;

CREATE TRIGGER refresh_mat_view_after_enigma_insert
    AFTER INSERT OR UPDATE OR DELETE
    ON enigmi
    FOR EACH STATEMENT
EXECUTE PROCEDURE refresh_enigmi_seguiti();

CREATE TRIGGER refresh_mat_view_after_connessione_insert
    AFTER INSERT OR UPDATE OR DELETE
    ON connessioni
    FOR EACH STATEMENT
EXECUTE PROCEDURE refresh_enigmi_seguiti();