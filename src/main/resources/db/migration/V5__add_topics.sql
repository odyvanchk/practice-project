
create table topics
(
    id_topic serial
        constraint topics_pk
            primary key,
    name char(150) not null
);

create table subtopics
(
    id_subtopic serial
        constraint subtopics_pk
            primary key,
    name char(150) not null,
    topic int,
    CONSTRAINT fk_subtopics_topics FOREIGN KEY (topic)
        REFERENCES public.topics (id_topic) MATCH SIMPLE
);

drop table if exists tasks;
create table tasks
(
    id_task bigserial
        constraint tasks_pk
            primary key,
    CONSTRAINT fk_tasks_topics FOREIGN KEY (topic)
        REFERENCES public.topics (id_topic) MATCH SIMPLE,
    CONSTRAINT fk_tasks_subtopics FOREIGN KEY (topic)
        REFERENCES public.subtopics (id_subtopic) MATCH SIMPLE,
    topic int,
    sub_topic int,
    description char(500)
);