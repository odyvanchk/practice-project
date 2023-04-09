set schema 'foreign_courses';

ALTER TABLE lessons
    ADD COLUMN topic character(500);