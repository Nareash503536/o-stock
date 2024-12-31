
CREATE TABLE IF NOT EXISTS public.organization
(
    organization_id text COLLATE pg_catalog."default" NOT NULL,
    name text COLLATE pg_catalog."default",
    contact_name text COLLATE pg_catalog."default",
    contact_email text COLLATE pg_catalog."default",
    contact_phone text COLLATE pg_catalog."default",
    CONSTRAINT organization_pkey PRIMARY KEY (organization_id)
    )

    TABLESPACE pg_default;

ALTER TABLE public.organization
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.license
(
    license_id text COLLATE pg_catalog."default" NOT NULL,
    organization_id text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    product_name text COLLATE pg_catalog."default" NOT NULL,
    license_type text COLLATE pg_catalog."default" NOT NULL,
    comment text COLLATE pg_catalog."default",
    CONSTRAINT license_pkey PRIMARY KEY (license_id),
    CONSTRAINT license_organization_id_fkey FOREIGN KEY (organization_id)
    REFERENCES public.organization (organization_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
    )

    TABLESPACE pg_default;

ALTER TABLE public.license
    OWNER to postgres;
