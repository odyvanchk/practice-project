ALTER TABLE foreign_courses.teachers_schedules
    ALTER COLUMN time_start TYPE timestamp without time zone
    USING current_date + time_start;