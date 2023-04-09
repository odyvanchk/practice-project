ALTER TABLE IF EXISTS foreign_courses.lessons DROP COLUMN IF EXISTS status;

ALTER TABLE IF EXISTS foreign_courses.lessons
    ADD COLUMN status character varying(10) NOT NULL;
ALTER TABLE IF EXISTS foreign_courses.lessons DROP CONSTRAINT IF EXISTS lessons_status_fkey;


CREATE TABLE IF Not EXISTS foreign_courses.black_lists
(
    user_id bigint NOT NULL,
    blocked_user_id bigint NOT NULL,
    PRIMARY KEY (blocked_user_id, user_id)
);

ALTER TABLE IF EXISTS foreign_courses.teachers_schedules DROP COLUMN IF EXISTS week_day;

ALTER TABLE IF EXISTS foreign_courses.teachers_schedules DROP COLUMN IF EXISTS time_finish;

ALTER TABLE foreign_courses.teachers_schedules
    ALTER COLUMN time_start TYPE time without time zone ;

ALTER TABLE IF EXISTS foreign_courses.teachers_schedules
    ADD COLUMN is_available boolean NOT NULL DEFAULT true;