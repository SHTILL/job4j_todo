CREATE TABLE public.tasks
(
    id integer NOT NULL,
    "desc" text COLLATE pg_catalog."default" NOT NULL,
    created date NOT NULL DEFAULT now(),
    done date,
    CONSTRAINT tasks_pkey PRIMARY KEY (id)
)