#SpaceZ

Individual

Java EE

PostgreSQL

Intellij IDEA
-JBoss/Wildfly

Database Name = spacez
Schema = public
Table Name = launch

Command to create table:---
CREATE TABLE IF NOT EXISTS public.launch
(
    "Launch_ID" integer NOT NULL,
    "Mission_Name" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    "SpaceCraft_Model" character varying(50) COLLATE pg_catalog."default" NOT NULL,
    "Date" date NOT NULL,
    "Time" time without time zone NOT NULL,
    "Description" text COLLATE pg_catalog."default",
    "Is_Launch" boolean NOT NULL,
    CONSTRAINT launch_pkey PRIMARY KEY ("Launch_ID")
)
