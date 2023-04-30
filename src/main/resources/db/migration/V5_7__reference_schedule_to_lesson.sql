ALTER TABLE IF EXISTS foreign_courses.lessons
    ADD COLUMN id_schedule bigint NOT NULL;
ALTER TABLE IF EXISTS foreign_courses.lessons
    ADD CONSTRAINT lessons_id_schedule_fkey FOREIGN KEY (id_schedule)
    REFERENCES foreign_courses.teachers_schedules (id_schedule) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;