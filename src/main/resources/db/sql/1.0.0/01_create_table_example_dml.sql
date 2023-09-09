--liquibase formatted sql
--changeset deal:nomeDaApi-Sprint_DML logicalFilePath:01_create_table_example_dml
--comment: Criação da tabela exemplo
--IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='example' and xtype='U')
--CREATE TABLE example (
--    id int NOT NULL IDENTITY(1,1),
--    description varchar(100) NOT NULL,
--    active bit NOT NULL default 0,
--    limit_uses int,
--    start_date datetime,
--    discount_type smallint,
--    price decimal(10,6),
--    CONSTRAINT example_pkey PRIMARY KEY (id)
--);
--
--ALTER TABLE IF EXISTS example
--    OWNER to "usuarioAplicacao";
--
--CREATE INDEX IF NOT EXISTS ix_example_id
--    ON example USING btree
--    (id ASC NULLS LAST)
--    TABLESPACE pg_default;
