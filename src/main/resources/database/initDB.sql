BEGIN;


CREATE TABLE IF NOT EXISTS public.lessons
(
    id_student integer,
    id_teacher integer NOT NULL,
    date_time timestamp with time zone NOT NULL,
    status smallint NOT NULL,
    id_lesson bigserial NOT NULL,
    CONSTRAINT lessons_pkey PRIMARY KEY (id_lesson)
);

CREATE TABLE IF NOT EXISTS public.lessons_statuses
(
    id_lesson_status smallserial NOT NULL,
    lesson_status character varying(60) NOT NULL,
    CONSTRAINT lessons_statuses_pkey PRIMARY KEY (id_lesson_status),
    CONSTRAINT unq_lesson_name UNIQUE (lesson_status)
);

CREATE TABLE IF NOT EXISTS public.teachers_descriptions
(
    id_teacher integer NOT NULL,
    country smallint,
    experience integer,
    description character varying(1000) COLLATE pg_catalog."default",
    cost_per_our money,
    photo_ref character varying(400) COLLATE pg_catalog."default",
    is_native boolean,
    english_level character(10),
    CONSTRAINT teachers_descriptions_pkey PRIMARY KEY (id_teacher)
);

CREATE TABLE IF NOT EXISTS public.user_roles
(
    id_user_role smallserial NOT NULL,
    user_role character varying(60) NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (id_user_role),
    CONSTRAINT unq_role_name UNIQUE (user_role)
);

CREATE TABLE IF NOT EXISTS public.users
(
    id_user serial NOT NULL,
    login character varying(20) COLLATE pg_catalog."default" NOT NULL,
    password_hash character varying(256) COLLATE pg_catalog."default" NOT NULL,
    email character varying(320) COLLATE pg_catalog."default" NOT NULL,
    id_user_role integer NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id_user),
    CONSTRAINT unq_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS public.user_has_role
(
    id_user integer NOT NULL,
    id_role smallint NOT NULL,
    CONSTRAINT pk_role_user PRIMARY KEY (id_user, id_role)
);

CREATE TABLE IF NOT EXISTS public.users_countries
(
    id_country smallserial NOT NULL,
    name character varying(150) NOT NULL,
    PRIMARY KEY (id_country),
    CONSTRAINT unq_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS public.refresh_sessions
(
    id_refresh serial NOT NULL,
    id_user integer NOT NULL,
    refresh_token character varying NOT NULL,
    expires_in timestamp with time zone NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    fingerprint character varying(200) NOT NULL,
    PRIMARY KEY (id_refresh)
);

CREATE TABLE IF NOT EXISTS public.teachers_schedules
(
    id_teacher integer NOT NULL,
    id_schedule serial NOT NULL,
    week_day integer NOT NULL,
    time_start time with time zone NOT NULL,
    time_finish time with time zone NOT NULL,
    PRIMARY KEY (id_schedule)
);

ALTER TABLE IF EXISTS public.lessons
    ADD CONSTRAINT fk_status FOREIGN KEY (status)
        REFERENCES public.lessons_statuses (id_lesson_status) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.lessons
    ADD CONSTRAINT fk_student FOREIGN KEY (id_student)
        REFERENCES public.users (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.lessons
    ADD CONSTRAINT fk_teacher FOREIGN KEY (id_teacher)
        REFERENCES public.users (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.teachers_descriptions
    ADD CONSTRAINT fk_user_id FOREIGN KEY (id_teacher)
        REFERENCES public.users (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;
CREATE INDEX IF NOT EXISTS teachers_descriptions_pkey
    ON public.teachers_descriptions(id_teacher);


ALTER TABLE IF EXISTS public.teachers_descriptions
    ADD CONSTRAINT fk_country_id FOREIGN KEY (country)
        REFERENCES public.users_countries (id_country) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.users
    ADD CONSTRAINT fk_role FOREIGN KEY (id_user_role)
        REFERENCES public.user_roles (id_user_role) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.user_has_role
    ADD CONSTRAINT fk_user_id FOREIGN KEY (id_user)
        REFERENCES public.users (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.user_has_role
    ADD CONSTRAINT fk_role_id FOREIGN KEY (id_role)
        REFERENCES public.user_roles (id_user_role) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.refresh_sessions
    ADD CONSTRAINT fk_user_is FOREIGN KEY (id_user)
        REFERENCES public.users (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


ALTER TABLE IF EXISTS public.teachers_schedules
    ADD CONSTRAINT fk_teacher FOREIGN KEY (id_teacher)
        REFERENCES public.users (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;


insert into lessons_statuses VALUES
                           ('BOOKED'),
                           ('COMPLETED'),
                           ('CANCELLED');

insert into user_roles (user_role) VALUES
                                       ( 'TEACHER'),
                                       ( 'STUDENT');

END;