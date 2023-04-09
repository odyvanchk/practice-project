BEGIN;
CREATE SCHEMA IF NOT EXISTS foreign_courses;
set schema 'foreign_courses';


CREATE TABLE IF NOT EXISTS user_roles
(
    id_user_role smallserial NOT NULL,
    user_role character varying(60) NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (id_user_role),
    CONSTRAINT unq_role_name UNIQUE (user_role)
);

CREATE TABLE IF NOT EXISTS users
(
    id_user serial NOT NULL,
    login character varying(20) NOT NULL,
    password_hash character varying(256) NOT NULL,
    email character varying(320) NOT NULL,
    is_email_confirm boolean default false,
    CONSTRAINT users_pkey PRIMARY KEY (id_user),
    CONSTRAINT unq_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS lessons_statuses
(
    id_lesson_status smallserial NOT NULL,
    lesson_status character varying(60) NOT NULL,
    CONSTRAINT lessons_statuses_pkey PRIMARY KEY (id_lesson_status),
    CONSTRAINT unq_lesson_name UNIQUE (lesson_status)
);


CREATE TABLE IF NOT EXISTS lessons
(
    id_lesson bigserial NOT NULL,
    id_student integer REFERENCES users (id_user),
    id_teacher integer NOT NULL REFERENCES users (id_user),
    date_time timestamp without time zone NOT NULL,
    status smallint NOT NULL REFERENCES lessons_statuses (id_lesson_status),
    CONSTRAINT lessons_pkey PRIMARY KEY (id_lesson)
);


CREATE TABLE IF NOT EXISTS users_countries
(
    id_country smallserial NOT NULL,
    name character varying(150) NOT NULL,
    PRIMARY KEY (id_country),
    CONSTRAINT unq_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS languages
(
    id serial NOT NULL,
    name character varying(150) NOT NULL,
    CONSTRAINT lang_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS teachers_descriptions
(
    id_teacher integer NOT NULL REFERENCES users (id_user),
    country smallint REFERENCES users_countries (id_country),
    experience integer,
    description character varying(1000) COLLATE pg_catalog."default",
    cost_per_our money,
    photo_ref character varying(400) COLLATE pg_catalog."default",
    is_native boolean,
    language_tough integer REFERENCES languages (id),
    english_level character(10),
    CONSTRAINT teachers_descriptions_pkey PRIMARY KEY (id_teacher)
);


CREATE TABLE IF NOT EXISTS user_has_role
(
    id_user integer NOT NULL REFERENCES users (id_user),
    id_role smallint NOT NULL REFERENCES user_roles (id_user_role),
    CONSTRAINT pk_role_user PRIMARY KEY (id_user, id_role)
);



CREATE TABLE IF NOT EXISTS refresh_sessions
(
    id_refresh serial NOT NULL,
    id_user integer NOT NULL REFERENCES users (id_user),
    refresh_token character varying NOT NULL,
    expires_in timestamp without time zone NOT NULL,
    created_at timestamp without time zone NOT NULL DEFAULT now(),
    fingerprint character varying(200) NOT NULL,
    PRIMARY KEY (id_refresh)
);

CREATE TABLE IF NOT EXISTS teachers_schedules
(
    id_schedule serial NOT NULL,
    id_teacher integer NOT NULL REFERENCES users (id_user),
    week_day integer NOT NULL,
    time_start time with time zone NOT NULL,
    time_finish time with time zone NOT NULL,
    PRIMARY KEY (id_schedule)
);

insert into lessons_statuses (lesson_status) VALUES
                                                 ('BOOKED'),
                                                 ('COMPLETED'),
                                                 ('CANCELLED');

insert into user_roles (user_role) VALUES
                                       ( 'TEACHER'),
                                       ( 'STUDENT');

insert into languages (name) VALUES
                                       ( 'english'),
                                       ( 'italian'),
                                       ( 'french');

END;