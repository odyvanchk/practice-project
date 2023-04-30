ALTER TABLE IF EXISTS foreign_courses.teachers_schedules
    ADD CONSTRAINT date_time_id_fk UNIQUE (id_teacher, time_start);